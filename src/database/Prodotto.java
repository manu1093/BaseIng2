/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Manu
 */
public class Prodotto {
    private String codice;
    private String nome;
     private int pezzi;
    private URL immagine;
    private float prezzo;
    private int punti;
    private String categoria;
    private int puntiVincita;
    
    public Prodotto() throws MalformedURLException{
        codice="";
        immagine=new URL("http://");
        nome="";
        categoria="";
        
    }


    public Prodotto(String codice, URL immagine, String nome, int pezzi, float prezzo, int punti,String categoria,int puntiVincita) {
        this.codice = codice;
        this.immagine = immagine;
        this.nome = nome;
        this.pezzi = pezzi;
        this.prezzo = prezzo;
        this.punti = punti;
        this.categoria=categoria;
        this.puntiVincita=puntiVincita;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public URL getImmagine() {
        return immagine;
    }

    public void setImmagine(URL immagine) {
        this.immagine = immagine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPezzi() {
        return pezzi;
    }

    public void setPezzi(int pezzi) {
        this.pezzi = pezzi;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }
    
    public int getPuntiVincita() {
        return puntiVincita;
    }

    public void setPuntiVincita(int puntiVincita) {
        this.puntiVincita = puntiVincita;
    }
    
    @Override
    public String toString(){
       return this.nome; 
    }
}

