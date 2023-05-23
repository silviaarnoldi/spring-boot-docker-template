package it.paleocapa.mastroiannim;
public class Ordine {

private String cliente;
private String cibo;
private Cliente clienteObj;
private float prezzoTotale; //nuovo attributo per il prezzo totale dell'ordine

public Ordine(String cliente, String cibo, float prezzoTotale) {
    this.cliente = cliente;
    this.cibo = cibo;
    this.clienteObj = null;
    this.prezzoTotale = prezzoTotale;
}

//getter e setter per gli attributi della classe

public String getCliente() {
    return cliente;
}

public void setCliente(Cliente clienteObj) {
    this.clienteObj = clienteObj;
}

public String getCibo() {
    return cibo;
}

public void setCibo(String cibo) {
    this.cibo = cibo;
}

public Cliente getClienteObj() {
    return clienteObj;
}

public void setClienteObj(Cliente clienteObj) {
    this.clienteObj = clienteObj;
}

public float getPrezzoTotale() {
    return prezzoTotale;
}

public void setPrezzoTotale(float prezzoTotale) {
    this.prezzoTotale = prezzoTotale;
}
}