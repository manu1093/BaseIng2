/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Date;

/**
 *
 * @author Manu
 */
public class Cliente extends Utente {

    public Cliente() {
    }
   
   
    private String via;
    private String numero;
    private String citta;   
    private String cartaCredito;
    private String scadenza;
    private String PIN;
    private int punti;
    private String categoria;

    public Cliente(String via, String numero, String citta, String cartaCredito, String scadenza, String PIN, int punti, String categoria) {
        this.via = via;
        this.numero = numero;
        this.citta = citta;
        this.cartaCredito = cartaCredito;
        this.scadenza = scadenza;
        this.PIN = PIN;
        this.punti = punti;
        this.categoria = categoria;
    }

    public Cliente(String via, String numero, String citta, String cartaCredito, String scadenza, String PIN, int punti, String categoria, String nickname, String pass) {
        super(nickname, pass);
        this.via = via;
        this.numero = numero;
        this.citta = citta;
        this.cartaCredito = cartaCredito;
        this.scadenza = scadenza;
        this.PIN = PIN;
        this.punti = punti;
        this.categoria = categoria;
    }

   
    
    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumerocarta() {
        return cartaCredito;
    }

    public void setNumerocarta(String numerocarta) {
        this.cartaCredito = numerocarta;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
     public String getCartaCredito() {
        return cartaCredito;
    }

    public void setCartaCredito(String cartaCredito) {
        this.cartaCredito = cartaCredito;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    @Override
    public String toString(){
        return "cliente:"+super.nickname;
    }
}
