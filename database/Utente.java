/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Manu
 */
public class Utente {
    protected String nickname;
    protected String pass;
    protected boolean attivo=true;
    
    public Utente(){
        nickname="";
        pass="";
    }

    public Utente(String nickname, String pass,boolean attivo) {
        this.nickname = nickname;
        this.pass = pass;
        this.attivo=attivo;
    }
    public Utente(String nickname, String pass) {
        this.nickname = nickname;
        this.pass = pass;
        
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAttivato() {
        return attivo;
    }

    public void setAttivato(boolean attivato) {
        this.attivo = attivato;
    }
   
    @Override
    public String toString(){
        return this.nickname;
    }
    
    
}
