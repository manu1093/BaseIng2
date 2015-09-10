/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import java.lang.IllegalArgumentException;
import java.sql.PreparedStatement;
import java.util.Calendar;
/**
 *
 * @author Manu
 */

public class Database {
    private Connection conn=null;
    	public Database()
	{
		
	      try {
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432","stefano83", "as0ohlt0");
	         conn.setAutoCommit(true);
	         System.out.println("Opened database successfully");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
	
	
	ResultSet getRsQuery(String query)//restituisce l'intera Lista risultante dalla query
	{
		ResultSet rs=null;
		Statement stmt=null;
		try{
			stmt = conn.createStatement();
	        rs = stmt.executeQuery( query );
	        /*while ( rs.next() ) {
	       	 System.out.println(rs.getInt("codice"));
	       	 System.out.println(rs.getString("nome"));
	           
	        }*/
	        //stmt.close();
	       
		}
		catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
        
		return rs;
	}
       
    @Override
        public void finalize() throws SQLException, Throwable{
          conn.close();
            super.finalize();
        }
        private ArrayList<Prodotto> toProdottiCollection(ResultSet s){
             ArrayList <Prodotto>l=new ArrayList<>();
        try {
            while(s.next())
                l.add(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio")));
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            return l;
        }
        public void inserisciProdotto(Prodotto p){
        try {
            PreparedStatement pr=this.conn.prepareStatement("INSERT INTO prodotto (codice, nome, pezzi, url_immagine, prezzo, punti,categoria) VALUES (?,?,?,?,?,?,?);");
            pr.setString(1,p.getCodice());
            pr.setString(2,p.getNome());
            pr.setInt(3,p.getPezzi());
            pr.setString(4,p.getImmagine().toString());
            pr.setFloat(5,p.getPrezzo());
            pr.setInt(6, p.getPunti());
            pr.setString(7,p.getCategoria());
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public ArrayList<Prodotto> ricercaProdotti(String match){//FUNZIONA
        ArrayList <Prodotto> l=new ArrayList<Prodotto>();
         try {    
             ResultSet s=this.conn.createStatement().executeQuery("select * from prodotto where prezzo is not null and pezzi>0;");
             while(s.next())
                
                     if(s.getString("nome").matches("(.*)"+match+"(.*)"))
                         l.add(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio")));
         } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return l; 
        }
        
        public void disattivaAccount(Utente u) {//DA testare
        try {
           
                this.conn.createStatement().executeUpdate("update utente set attivato=false where nickname="+u.getNickname());
           
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       
        public void inserisciUtente(Utente c){
            
        try {
            String tipo = null;
            Cliente h=new Cliente();
             if(c instanceof Cliente){
                h=(Cliente)c;
                tipo="cliente";                
             }
            if(c instanceof Admin)
                 tipo="amministratore";
            if(c instanceof Impiegato)
                tipo="impiegato";
           
            PreparedStatement t=this.conn.prepareStatement("insert into utente values(?,?,?,?,?,?,?,?,?,true,?,?);");
                t.setString(1,c.getNickname());
                t.setString(2,c.getPass());
                t.setString(3,h.getVia());
                t.setString(4,h.getNumero());
                t.setString(5,h.getCitta());
                t.setString(6,h.getNumerocarta());
                t.setString(7,h.getScadenza());
                t.setString(8,h.getPIN());
                t.setInt(9,h.getPunti());
                t.setString(10, tipo);
                t.setString(11,h.getCategoria());
                t.execute();
                
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
        
       public Utente login(Utente t){
            ResultSet s=this.getRsQuery("select * from utente where attivo ;");
        try {
            while(s.next()){
                if(s.getString(1).equals(t.getNickname()) && s.getString(2).equals(t.getPass())){
                    if(s.getString("tipo").equals("cliente"))
                        return new Cliente(s.getString("via"),s.getString("numero"),s.getString("citta"),s.getString("carta_credito"),s.getString("scadenza_carta"),s.getString("PIN"),s.getInt("punti"),s.getString("categoria_preferita"),s.getString("nickname"),s.getString("pass"));
                    if(s.getString("tipo").equals("amministratore"))
                        return new Admin(s.getString("nickname"),s.getString("pass"));
                    if(s.getString("tipo").equals("impiegato"))
                        return new Impiegato(s.getString("nickname"),s.getString("pass"));
                }
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
       
        public void aggiungiAlCarrello(Carrello c){//da testare
          
           
        try {
             this.conn.setAutoCommit(false);
             
            //if(this.getRsQuery("select * from carrello where id_oggetto="+c.getProdotto()+" and utente="+c.getUtente()).next())
            //    this.rimuoviDalCarrello(c);
            
             PreparedStatement t=this.conn.prepareStatement("insert into carrello(quantita,id_oggetto,utente) values(?,?,?)");
            t.setInt(1, c.getQuantita());
            t.setString(2,c.getProdotto());
            t.setString(3, c.getUtente());
            t.execute();
            this.conn.commit();
            this.conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        public void rimuoviDalCarrello(Carrello c){
             this.getRsQuery("delete carrello where id_oggetto="+c.getProdotto()+" and utente="+c.getUtente());
        } 
       public ArrayList<Carrello> visualizzaCarrello(Utente c){
        ArrayList<Carrello>l=new ArrayList<>();
         try {
                   
            ResultSet t=this.getRsQuery("select * from carrello where utente='"+c.getNickname()+"';");
            while(t.next()){
                l.add(new Carrello(t.getString("id_oggetto"),t.getString("utente"),t.getInt("quantita")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
         return l;
       } 
        public void acquistaCarrello(Cliente u){// non controllato
            try{
                this.conn.setAutoCommit(false);                
              ResultSet t=this.conn.createStatement().executeQuery("select carrello.id,carrello.qunatita,carrello.utente,carrello.id_oggetto from carrello,utente,prodotto where utente.nickname='"+((Utente)u).getNickname()+"';");              
              while(t.next()){
                  this.conn.createStatement().executeUpdate("delete carrello where id="+t.getString("id"));
                  this.conn.createStatement().executeUpdate("update prodotto set quantita=quantità-"+t.getInt(2)+" where codice='"+t.getString(4)+"';");
                  PreparedStatement ssss=this.conn.prepareStatement("insert into acquisto (data_ac,quantita,utente,id_oggetto) values ?,?,?,?;");
                  Calendar c=Calendar.getInstance();
                  ssss.setString(1,c.get(c.get("'"+Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+Calendar.DAY_OF_MONTH)+"'");
                  ssss.setInt(2, t.getInt(2));
                  ssss.setString(3, t.getString(3));
                  ssss.setString(4, t.getString(4));
                 ssss.execute();
                          
              }
              this.conn.commit();
              this.conn.setAutoCommit(true);
            }catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        public ArrayList<Prodotto> visualizzaListaPremi(){           
           return this.toProdottiCollection(this.getRsQuery("select * from Prodotto where punti_vincita is not null"));       
        }
       
        public ArrayList<String> visualizzaCategorie(){
             ArrayList <String> l=new ArrayList<String>();
        try {
           
            ResultSet s=this.getRsQuery("select * from categoria;");
            while(s.next()){
                l.add(s.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
                
        }
        
        public void inserisciCategoria(String s) throws SQLException{
      
            this.conn.createStatement().execute("insert into categoria values('"+s+"');");       
       
        }
        
        public void rimuoviCategoria(String s) throws SQLException{
             this.conn.createStatement().execute("delete from categoria where nome='"+s+"';");
        }
        
        public void refresh(HttpSession session)
        {
        	Database db=new Database();
        	Utente u=new Utente(utente.getNickname(),utente.getPass());
        	session.removeAttribute("utente");
        	session.setAttribute("utente", (Cliente) db.login(u));
        }
        
        
        public static void main(String args[]) throws SQLException{
          // System.out.println(new Database().ricercaProdotti("notebook"));
           new Database().inserisciUtente(new Cliente("Via Verdi","16","Vicenza","4558523574121245", "12/18","524",123,"telefonia","paolo00","matitanera"));
            //new Database().inserisciUtente(new Impiegato("lav05","merengue"));
           // System.out.println(new Database().login(new Utente("franco89","ciaociao")));
           // System.out.println(new Database().login(new Utente("admin","fghh")));
           // String s=new Database().login(new Impiegato("lav05","merengue")).toString();
           // System.out.println("s = " + s);
        }
}
