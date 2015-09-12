/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Manu
 */
public class Acquisto {
    private String utente;
    private String prodotto;
    private int quantita;
    private Date data;
    private String ora;

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public String getOra() {
        return ora;
    }

    public void setOra() {
		Date d=new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        this.ora = dateFormat.format(d);
    }


    public Acquisto() {
        data=new Date();
        prodotto="";
        utente="";
    }
    
}
