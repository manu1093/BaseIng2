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
public class Admin extends Utente{

    public Admin() {
        super();
    }

    public Admin(String nickname, String pass,boolean attivo) {
        super(nickname, pass, attivo);
    }
    public Admin(String nickname, String pass) {
        super(nickname, pass);
    }
    
    public String toString(){
        return "amministratore:"+super.nickname;
    }
}
