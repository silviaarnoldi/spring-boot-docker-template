package it.paleocapa.mastroiannim;
public class Cliente {

private String nome;
private String cognome;
private int cellulare;
private static int credito; //attributo statico per il credito dell'utente

public Cliente(String nome, String cognome, int cellulare, int c) {
    this.nome = nome;
    this.cognome = cognome;
    this.cellulare = cellulare;
    credito = c;
}

//getter e setter per gli attributi della classe

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getCognome() {
    return cognome;
}

public void setCognome(String cognome) {
    this.cognome = cognome;
}

public int getCellulare() {
    return cellulare;
}

public void setCellulare(int cellulare) {
    this.cellulare = cellulare;
}

public static int getCredito() {
    return credito;
}

public static void setCredito(int credito) {
    Cliente.credito = credito;
}
}