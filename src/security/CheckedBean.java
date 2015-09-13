/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package security;

import database.beans.Bean;
import java.util.HashMap;

public class CheckedBean implements Security{
   private String errore="";
   private Bean b;
    public  CheckedBean(Bean b) throws ErrorePericoloInjection, ErroreCampoVuoto{
        HashMap <String,String> m=b.toSpecial();
        for(String s:m.keySet()){
            this.checkInjection(m.get(s));
            if(m.get(s).equals(""))
                throw new ErroreCampoVuoto(s);
        }
    }
    
    public  boolean checkInjection(String s) throws ErrorePericoloInjection {
            String c[]={"\"","\'","(",")","select","SELECT","INSERT","insert","*","&","|","$","\\"};
        for (String c1 : c) {
            if (s.contains(c1)) {
                 errore=c1;
               throw new ErrorePericoloInjection(c1);
              
            }
        }
            return true;
       }
    
}
