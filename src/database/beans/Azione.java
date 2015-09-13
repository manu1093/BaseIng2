/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.beans;

import java.util.HashMap;

/**
 *
 * @author Manu
 */
public class Azione implements Bean{
    private String nome;
    private String link;
    private String immagine;
    private String messaggio;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public Azione(String nome, String link, String immagine, String messaggio) {
        this.nome =  nome;
        this.link = link;
        this.immagine = immagine;
        this.messaggio = messaggio;
    }

    @Override
    public HashMap<String, String> toSpecial() {
       HashMap <String,String>m=new HashMap<String,String>();
       m.put("nome", nome);
       m.put("messaggio", messaggio);
       m.put("link", link);
       m.put("immagine", immagine);
       return m;
    }
}
