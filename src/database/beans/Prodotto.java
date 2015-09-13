/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author Manu
 */
public class Prodotto implements Bean{
    private String codice;
    private String nome;
     private int pezzi;
    private URL immagine;
    private float prezzo;
    private int punti;
    private String categoria;
    private int puntiVincita;
    private String descrizione;
    
    public Prodotto() throws MalformedURLException{
        codice="";
        immagine=new URL("http://");
        nome="";
        categoria="";
        
    }


    public Prodotto(String codice, URL immagine, String nome, int pezzi, float prezzo, int punti,String categoria,int puntiVincita,String descrizione) 
    {
        this.codice = codice;
        this.immagine = immagine;
        this.nome = nome;
        this.pezzi = pezzi;
        this.prezzo = prezzo;
        this.punti = punti;
        this.categoria=categoria;
        this.puntiVincita=puntiVincita;
        this.descrizione=descrizione;
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
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    @Override
    public String toString(){
       return this.nome; 
    }

     public HashMap<String, String> toSpecial() {
       HashMap <String,String>m=new HashMap<String,String>();
       m.put("categoria", categoria);
       m.put("codice",codice);
       m.put("descrizione",descrizione);
      m.put("nome",nome);
      
       return m;
    }
}

