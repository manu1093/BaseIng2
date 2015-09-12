/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author manu
 */
public class ErrorePericoloInjection extends Exception {
    public ErrorePericoloInjection(String s){
        super(s+" non è un carattere valido");
    }
}
