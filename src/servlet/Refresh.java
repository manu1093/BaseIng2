/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.beans.*;
import database.Database;

import javax.servlet.http.HttpSession;

/**
 *
 * @author manu
 */
public class Refresh {
   public static void refreshUtente(HttpSession session, Cliente utente)
       {
    	    Database db=Database.getInstance();
    		
    		session.removeAttribute("utente");
    		session.setAttribute("utente", (Cliente) db.login(utente.getNickname(),utente.getPass()));
    		
       }
       public static void refreshAdmin(HttpSession session, Admin utente)
       {
    	    Database db=Database.getInstance();
    		
    		session.removeAttribute("utente");
    		session.setAttribute("utente", (Admin) db.login(utente.getNickname(),utente.getPass()));
       }
       public static void refreshImpiegato(HttpSession session, Impiegato utente)
       {
    	    Database db=Database.getInstance();
    		
    		session.removeAttribute("utente");
    		session.setAttribute("utente", (Impiegato) db.login(utente.getNickname(),utente.getPass()));
       } 
}
