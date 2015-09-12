/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.beans.Prodotto;
import database.beans.Utente;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manu
 */
public class CheckedBase implements Security{
    private Database db;
    public CheckedBase(){
        db=Database.getInstance();
    }
    private boolean checkInjection(String s) throws ErrorePericoloInjection {
            String c[]={"\"","\'","(",")","select","SELECT","INSERT","insert","*","&","|","$","\\"};
        for (String c1 : c) {
            if (s.contains(c1)) {
               throw new ErrorePericoloInjection(c1);
            }
        }
            return true;
       }
    @Override
    public Utente login(String nickname, String pass) throws ErrorePericoloInjection {
        if(
            checkInjection(nickname)&&
            checkInjection(pass))
            return db.login(nickname, pass);
        return null;
    }

    @Override
    public ArrayList<Prodotto> ricercaProdotti(String match) throws ErrorePericoloInjection {
        if(checkInjection(match))
            return db.ricercaProdotti(match);
        return null;
    }

    

    
}
