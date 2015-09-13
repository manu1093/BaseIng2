/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Manu
 */
public abstract class Database {
    protected Connection conn=null;
    	protected Database(String driver,String connection,String utente,String pass)
	{
		
	      try {
	         Class.forName(driver);
	         conn = DriverManager.getConnection(connection,utente, pass);//"jdbc:postgresql://localhost/stefano83","stefano83", "434343"
	         conn.setAutoCommit(true);
	         //System.out.println("Opened database successfully");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
        protected void closeConnection()
	{
		try {
			this.conn.close();
			System.out.println("Connessione chiusa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
