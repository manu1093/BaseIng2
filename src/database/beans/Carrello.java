/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.beans;


public class Carrello{
    private String prodotto;
    private String utente;
    private int quantita;

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Carrello(String prodotto, String utente, int quantita) {
        this.prodotto = prodotto;
        this.utente = utente;
        this.quantita = quantita;
    }
            
    
} 
