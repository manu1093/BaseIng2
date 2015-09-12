/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.beans;

/**
 *
 * @author Manu
 */
public class Impiegato extends Utente {

    public Impiegato(String nickname, String pass,boolean attivato) {
        super(nickname, pass, attivato);
    }
    public Impiegato(String nickname, String pass) {
        super(nickname, pass);
    }
    public Impiegato() {
        super();
    }
    @Override
    public String toString(){
        return "impiegato:"+super.nickname;
    }
}
