package it.paleocapa.mastroiannim;
public class Cibo {

private String nome;
private float prezzo;

public Cibo(String nome, float prezzo) {
this.nome = nome;
this.prezzo = prezzo;
}

public String getNome() {
return nome;
}

public void setNome(String nome) {
this.nome = nome;
}

public float getPrezzo() {
return prezzo;
}

public void setPrezzo(float prezzo) {
this.prezzo = prezzo;
}
}
