/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.beans.Prodotto;
import database.beans.Utente;
import java.util.ArrayList;

/**
 *
 * @author manu
 */
public interface DatabaseS {
    public Utente login(String nickname,String pass) throws ErrorePericoloInjection;
    public ArrayList<Prodotto> ricercaProdotti(String match) throws ErrorePericoloInjection;
}
