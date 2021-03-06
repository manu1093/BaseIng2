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
public abstract class Utente implements Bean{
    protected String nickname;
    protected String pass;
    protected boolean attivo=true;
    
    protected Utente(){
        nickname="";
        pass="";
    }

    protected Utente(String nickname, String pass,boolean attivo) {
        this.nickname = nickname;
        this.pass = pass;
        this.attivo=attivo;
    }
    protected Utente(String nickname, String pass) {
        this.nickname = nickname;
        this.pass = pass;
        
    }

    public  String getNickname() {
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
    @Override
    public HashMap<String,String> toSpecial(){
         HashMap <String,String>m=new HashMap<String,String>();
         m.put("pass", pass);
         m.put("nickname", nickname);
         return m;
    }
    
}
