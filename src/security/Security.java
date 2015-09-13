/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import database.SData;

/**
 *
 * @author manu
 */
public interface Security {

    
    public boolean checkInjection(String s) throws ErrorePericoloInjection;
}
