/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.lang.IllegalArgumentException;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
	         conn = DriverManager.getConnection("jdbc:postgresql://dbserver.scienze.univr.it/dblab54","userlab54", "cinquantaquattroSH");//jdbc:postgresql://localhost/stefano83","stefano83", "434343");
	         conn.setAutoCommit(true);
	         //System.out.println("Opened database successfully");
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
        
        public Prodotto getProdotto(String codice){
           return  this.toProdottiCollection(this.getRsQuery("select * from prodotto where codice='"+codice+"';")).get(0);
            
        }
       
        public Prodotto CarrelloToProdotto(Carrello c)        {
            return this.getProdotto(c.getProdotto());
        }
        
     /*
        public ArrayList<Prodotto> ricercaProdotti2(String match){//FUNZIONA V2.0
            ArrayList <Prodotto> l=new ArrayList<Prodotto>();
             try {    
                 ResultSet s=this.conn.createStatement().executeQuery("select * from prodotto where prezzo is not null and pezzi>0;");
                
                 while(s.next())
                    for(int i=0;i<match.split(" ").length;i++){
                        String temp=match.split(" ")[i];
                         if((" "+s.getString("codice")).contains((" "+temp).substring(0,temp.length()-1))){
                             l.add(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio")));
                             break;
                        }
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
              return l; 
            }
        
        */
        public ArrayList<Prodotto> ricercaProdotti(String match){//FUNZIONA V2.0
        ArrayList <Prodotto> l=new ArrayList<Prodotto>();
         try {    
             ResultSet s=this.conn.createStatement().executeQuery("select * from prodotto where prezzo is not null and pezzi>0;");
            
             while(s.next())
                for(int i=0;i<match.split(" ").length;i++){
                    String temp=match.split(" ")[i];
                     if((" "+s.getString("nome")).contains((" "+temp).substring(0,temp.length()-1))){
                         l.add(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio")));
                         break;
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
          return l; 
        }
       
        public ArrayList<Prodotto> ProdottiACaso(int n){
            ArrayList<Prodotto>l=this.toProdottiCollection(this.getRsQuery("select * from prodotto where pezzi > 0 ;"));
            while(l.size()>n)
                l.remove((int)(Math.random()*l.size()));
            return l;
        }
        
        
        
         public ArrayList<Prodotto> ProdottiPreferiti(int n,Cliente c){//
            return this.ProdottiPreferiti(n,c.getCategoria());
        }
        public ArrayList<Prodotto> ProdottiPreferiti(int n,String categoriaPreferita){
            ArrayList<Prodotto>l=this.toProdottiCollection(this.getRsQuery("select codice,nome,pezzi,url_immagine,prezzo,prodotto.punti,categoria,prezzo_premio from prodotto where categoria='"+categoriaPreferita+"' and pezzi > 0 ;"));
            while(l.size()>n)
                l.remove((int)(Math.random()*l.size()));
            return l;
        }
        
        public void updateCliente(Cliente h, String nickname){
            
            try {
                String tipo = null;
                
                PreparedStatement t=this.conn.prepareStatement("update utente set nickname=?,pass=?,via=?,numero=?,citta=?,carta_credito=?,scadenza_carta=?,pin=?,punti=?,attivo=TRUE,tipo='cliente',categoria_preferita=? where nickname=?;");
                    t.setString(1,h.getNickname());
                    t.setString(2,h.getPass());
                    t.setString(3,h.getVia());
                    t.setString(4,h.getNumero());
                    t.setString(5,h.getCitta());
                    t.setString(6,h.getNumerocarta());
                    t.setString(7,h.getScadenza());
                    t.setString(8,h.getPIN());
                    t.setInt(9,h.getPunti());
                    //t.setString(10, tipo);
                    t.setString(10,h.getCategoria());
                    t.setString(11,nickname);
                    t.execute();
                    
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            }
        
        public Boolean inserisciCliente(Cliente h){
            
        try {
        	ResultSet s=this.conn.createStatement().executeQuery("select * from utente where nickname='"+h.nickname+"' and attivo=FALSE;");
            if(s.next())
            {
            	this.conn.createStatement().execute("delete from utente where nickname='"+h.nickname+"';");
            }
            s=this.conn.createStatement().executeQuery("select * from utente where nickname='"+h.nickname+"';");
            if(s.next())		//non fa nulla
            {
            	return false;
            }
            else				//la login non è presente
            {
           
            PreparedStatement t=this.conn.prepareStatement("insert into utente values(?,?,?,?,?,?,?,?,?,TRUE,'cliente',?);");
                t.setString(1,h.getNickname());
                t.setString(2,h.getPass());
                t.setString(3,h.getVia());
                t.setString(4,h.getNumero());
                t.setString(5,h.getCitta());
                t.setString(6,h.getNumerocarta());
                t.setString(7,h.getScadenza());
                t.setString(8,h.getPIN());
                t.setInt(9,h.getPunti());
                //t.setString(10, tipo);
                t.setString(10,h.getCategoria());
                t.execute();
            }   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return true;
        }
        public Boolean inserisciUtente(String nickname, String password, String tipo){
            
            try {
            	ResultSet s=this.conn.createStatement().executeQuery("select * from utente where nickname='"+nickname+"' and attivo=FALSE;");
                if(s.next())
                {
                	this.conn.createStatement().execute("delete from utente where nickname='"+nickname+"';");
                }
                s=this.conn.createStatement().executeQuery("select * from utente where nickname='"+nickname+"';");
                if(s.next())		//non fa nulla
                {
                	return false;
                }
                else				//la login non è presente
                {
	                PreparedStatement t=this.conn.prepareStatement("insert into utente nickname=?,pass=? and tipo=?;");
	                    t.setString(1,nickname);
	                    t.setString(2,password);
	                    t.setString(3, tipo);
	                    t.execute();
	                    return true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
            }
       public Utente login(Utente t){
            ResultSet s=this.getRsQuery("select * from utente where attivo=TRUE;");
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
       
        public Cliente getCliente(String nickname){
        try {
            ResultSet s=this.getRsQuery("select * from utente where nickname='"+nickname+"';");
            s.next();
            return new Cliente(s.getString("via"),s.getString("numero"),s.getString("citta"),s.getString("carta_credito"),s.getString("scadenza_carta"),s.getString("pin"),s.getInt("punti"),s.getString("categoria_preferita"),s.getString("nickname"),s.getString("pass"));
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
       
       
       
       
        public void aggiungiAlCarrello(Carrello c){//da testare
          
           
        try {
             this.conn.setAutoCommit(false);
             
            if(this.getRsQuery("select * from carrello where id_oggetto='"+c.getProdotto()+"' and utente='"+c.getUtente()+"';").next())
          
            	    this.rimuoviDalCarrello(c);
          
             PreparedStatement t=this.conn.prepareStatement("insert into carrello (quantita,id_oggetto,utente) values (?,?,?);");
            t.setInt(1, c.getQuantita());
            t.setString(2,c.getProdotto());
            t.setString(3,c.getUtente());
            t.execute();
            this.conn.commit();
            this.conn.setAutoCommit(true);
         
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        
        }  
        }
        public void rimuoviDalCarrello(Carrello c) 
        {
        try {
            this.conn.createStatement().execute("delete from carrello where id_oggetto='"+c.getProdotto()+"' and utente='"+c.getUtente()+"';");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
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
       
       public HashMap<Prodotto,Integer> carrelloUtente(Cliente c){
           HashMap <Prodotto,Integer> h=new HashMap<>();
        try {
            
            ResultSet s=this.getRsQuery("select * from prodotto,carrello where id_oggetto=codice and carrello.utente='"+c.getNickname()+"';");
            while(s.next())
                h.put(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio")),s.getInt("quantita"));
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return h;
       }
       
       
       
        public void acquistaCarrello(Cliente u) 
        {// non controllato
        	Database db=new Database();
            try{
                this.conn.setAutoCommit(false);
            	//this.conn.setAutoCommit(true);
                ResultSet t=this.conn.createStatement().executeQuery("select carrello.id,carrello.quantita,carrello.utente,carrello.id_oggetto from carrello,utente,prodotto where utente.nickname='"+u.getNickname()+"' group by carrello.id;");
              
                while(t.next()){
            	
                  this.conn.createStatement().executeUpdate("delete from carrello where id="+t.getInt("id")+";");
                  this.conn.createStatement().executeUpdate("update prodotto set pezzi=pezzi-"+t.getInt(2)+" where codice='"+t.getString(4)+"';");
                  this.conn.createStatement().executeUpdate("update utente set punti=punti+"+((db.getProdotto(t.getString(4))).getPunti())*t.getInt(2)+" where nickname='"+t.getString(3)+"';");
                  PreparedStatement ssss=this.conn.prepareStatement("insert into acquisto (data_ac,quantita,utente,id_oggetto) values (?,?,?,?);");
                  Calendar c=Calendar.getInstance();
              	  Date date=new Date();
              	  ssss.setDate(1, new java.sql.Date(date.getTime()));
                  ssss.setInt(2, t.getInt(2));
                  ssss.setString(3, t.getString(3));
                  ssss.setString(4, t.getString(4));
                  ssss.execute();
                          
              }
              this.conn.commit();
            }
            catch (SQLException ex) {
            	Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        	}
            
        }
        
        public void acquistaPremio(Cliente c,String codice, int quantita) 
        {// non controllato
        	Database db=new Database();
            try{
              this.conn.setAutoCommit(false);
            	//this.conn.setAutoCommit(true);
              this.conn.createStatement().executeUpdate("update prodotto set pezzi=(pezzi-"+quantita+") where codice='"+codice+"';");
              this.conn.createStatement().executeUpdate("update utente set punti=punti-"+((db.getProdotto(codice)).getPuntiVincita())*quantita+" where nickname='"+c.getNickname()+"';");
              this.conn.commit();
              
            }
            catch (SQLException ex) {
            	Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        	}
            
        }
        
        public ArrayList<Prodotto> visualizzaListaPremi(){           
           return this.toProdottiCollection(this.getRsQuery("select * from Prodotto where prezzo_premio is not null"));       
        }
       
        public ArrayList<String> visualizzaCategorie(){
             ArrayList <String> l=new ArrayList<String>();
        try {
           
            ResultSet s=this.getRsQuery("select * from categoria;");
            while(s.next()){
            	//System.out.println("Debug Metodo database.visualizzaCategoria "+s.getRow());
                l.add(s.getString("nome"));
                //System.out.println(s.getString("nome"));
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
        
        
        public ArrayList<String> listaUtentiDisabilitati(){
            ArrayList <String> l=new ArrayList<String>();
             try {    
	            	 ResultSet s=this.conn.createStatement().executeQuery("select nickname from utente where attivo=FALSE;");
	                
	                 while(s.next())
	                 {
	                   l.add(new String(s.getString(1)));
	                 }
             	 } 
             catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } 
             return l; 
            }
        public ArrayList<String> listaUtentiAbilitati(){
            ArrayList <String> l=new ArrayList<String>();
             try {    
	            	 ResultSet s=this.conn.createStatement().executeQuery("select nickname from utente where attivo=TRUE;");
	                
	                 while(s.next())
	                 {
	                   l.add(new String(s.getString(1)));
	                 }
             	 } 
             catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } 
             return l; 
            }
       public void disabilitaUtente(String nickname)
       {
    	   try {
			this.conn.createStatement().executeUpdate("update utente set attivo=FALSE where nickname='"+nickname+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
       }
       public void abilitaUtente(String nickname)
       {
    	   try {
			this.conn.createStatement().executeUpdate("update utente set attivo=TRUE where nickname='"+nickname+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
       }
       
       public void refreshUtente(HttpSession session, Cliente utente)
       {
    	    Database db=new Database();
    		Utente u= new Utente(utente.getNickname(),utente.getPass());
    		session.removeAttribute("utente");
    		session.setAttribute("utente", (Cliente) db.login(u));
       }
       public void refreshAdmin(HttpSession session, Admin utente)
       {
    	    Database db=new Database();
    		Utente u= new Utente(utente.getNickname(),utente.getPass());
    		session.removeAttribute("utente");
    		session.setAttribute("utente", (Admin) db.login(u));
       }
       public void refreshImpiegato(HttpSession session, Impiegato utente)
       {
    	    Database db=new Database();
    		Utente u= new Utente(utente.getNickname(),utente.getPass());
    		session.removeAttribute("utente");
    		session.setAttribute("utente", (Impiegato) db.login(u));
       }
        
        public static void main(String args[]) throws SQLException{
          // System.out.println(new Database().ricercaProdotti("notebook R522"));
          // new Database().inserisciUtente(new Cliente("Via Verdi","16","Vicenza","4558523574121245", "12/18","524",123,"telefonia","paolo00","matitanera"));
            //new Database().inserisciUtente(new Impiegato("lav05","merengue"));
           // System.out.println(new Database().login(new Utente("franco89","ciaociao")));
           // System.out.println(new Database().login(new Utente("admin","fghh")));
           // String s=new Database().login(new Impiegato("lav05","merengue")).toString();
           // System.out.println("s = " + s);
           // System.out.println(new Database().ProdottiPreferiti(3,"telefonia"));
            /*
            HashMap <Prodotto,Integer> h=new Database().carrelloUtente(null);
           for(Prodotto p:h.keySet()){
               Prodotto pr=p;
               int quantita=h.get(p);
                   
           }*/
            new Database().aggiungiAlCarrello(new Carrello("ag349512","gaetano78",5));
        }
}
