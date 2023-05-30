package it.paleocapa.mastroiannim;
import java.util.*;

public class Bar {
    public  HashMap<String,LinkedList<Ordine>> listaCibo;
    public  HashMap<String,LinkedList<Ordine>> utenti; //modificato per supportare ordini multipli per ogni cliente
    public static LinkedList<Cibo> cib;

    public Bar() {
        this.listaCibo = new HashMap<>();
        this.utenti = new HashMap<>();
        cib = new LinkedList<>();
        crea();
    }
    private void crea(){
        aggiungiNuovoCibo("Panino Hamburger", 2);
        aggiungiNuovoCibo("Panino CordonBlue", 3);
        aggiungiNuovoCibo("Panino Prosciutto", 1);
        aggiungiNuovoCibo("Panino Wurstel", 2);
        aggiungiNuovoCibo("Panino Cotoletta", 2);
        aggiungiNuovoCibo("Panino Arrosto", 2);
    }
    public void aggiungiOrdine(String m, String n, int prezzo) {
        Ordine ordine = new Ordine(m, n, prezzo);
        LinkedList<Ordine> ordini = listaCibo.get(n);
        if (ordini != null) {
            ordini.add(ordine);
            listaCibo.put(n, ordini);
        } else {
            LinkedList<Ordine> nuovaLista = new LinkedList<>();
            nuovaLista.add(ordine);
            listaCibo.put(n, nuovaLista);
        }
        LinkedList<Ordine> ordiniCliente = utenti.get(m); //recupera la lista di ordini del cliente corrispondente
        if (ordiniCliente != null) {
            ordiniCliente.add(ordine); //aggiunge il nuovo ordine alla lista del cliente
            utenti.put(m, ordiniCliente); //aggiorna il valore associato alla chiave del cliente nel HashMap utenti
        } else {
            LinkedList<Ordine> nuovaLista = new LinkedList<>();
            nuovaLista.add(ordine);
            utenti.put(m, nuovaLista);
        }
        Cliente.setCredito(Cliente.getCredito() - prezzo);
    }
    public void getMenu() {
        for (Cibo cibo : cib) {
            System.out.println(cibo.getNome() + " " + cibo.getPrezzo());
        }
    }
    public void aggiungiNuovoCibo(String n, int p) {
        Cibo cibo = new Cibo(n, p);
        cib.add(cibo);
        LinkedList<Ordine> ordini = new LinkedList<>();
        listaCibo.put(n, ordini);
    }


    public int soldiRimasti() {
        return Cliente.getCredito();
    }

    public int totaleOrdine() {
        int totale = 0;
        for (LinkedList<Ordine> ordini : listaCibo.values()) {
            for (Ordine o : ordini) {
                totale += o.getPrezzoTotale();
            }
        }
        return totale;
    }
}