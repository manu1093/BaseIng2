/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;
import java.text.DateFormat;
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
import java.util.LinkedHashMap;

import database.beans.*;
/**
 *
 * @author Manu
 */

public class Database implements DatabaseS{
    private  static Database DB;
    private Connection conn=null;
    	private Database()
	{
		
	      try {
	         Class.forName("org.postgresql.Driver");
	         conn = DriverManager.getConnection("jdbc:postgresql://dbserver.scienze.univr.it/dblab54","userlab54", "cinquantaquattroSH");//"jdbc:postgresql://localhost/stefano83","stefano83", "434343"
	         conn.setAutoCommit(true);
	         //System.out.println("Opened database successfully");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	   }
        public static Database getInstance(){
            if(DB==null)
                DB=new Database();
            return DB;
        }
	private void closeConnection()
	{
		try {
			this.conn.close();
			System.out.println("Connessione chiusa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
                l.add(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio"),s.getString("descrizione")));
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
            return l;
        }
        public Boolean inserisciProdotto(Prodotto p){ //controllo prodotto esistente!!!
        	
        	try
        	{
        		ResultSet s=this.conn.createStatement().executeQuery("select * from prodotto where codice='"+p.getCodice()+"';");
                if(s.next())
                {
                	if(this.conn.createStatement().executeUpdate("update prodotto set nome='"+p.getNome()+"', pezzi='"+p.getPezzi()
                	+"', url_immagine='"+p.getImmagine().toString()+"',prezzo='"+p.getPrezzo()+"', punti='"+p.getPunti()
                	+"', categoria='"+p.getCategoria()+"', prezzo_premio='"+p.getPuntiVincita()
                	+"', descrizione='"+p.getDescrizione()+"' where codice='"+p.getCodice()+"';")==0)
                	{
                		return true;
                	}
                	
                		return true;
                }
                else
                {
                	try {
                        PreparedStatement pr=this.conn.prepareStatement("INSERT INTO prodotto (codice, nome, pezzi, url_immagine, prezzo, punti,categoria,prezzo_premio,descrizione) VALUES (?,?,?,?,?,?,?,?,?);");
                        pr.setString(1,p.getCodice());
                        pr.setString(2,p.getNome());
                        pr.setInt(3,p.getPezzi());
                        pr.setString(4,p.getImmagine().toString());
                        pr.setFloat(5,p.getPrezzo());
                        pr.setInt(6, p.getPunti());
                        pr.setString(7,p.getCategoria());
                        pr.setInt(8,p.getPuntiVincita());
                        pr.setString(9, p.getDescrizione());
                        if(pr.executeUpdate()<1)
                        	return false;
                        else
                        	return true;
                    	} 
                	catch (SQLException ex) {
                        Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
        	}
        	catch(Exception e)
        	{
        		System.out.println(e.toString()+"Errore SQL inserisciProdotto");
        		return false;
        	}
			
        }
        
        public Prodotto getProdotto(String codice){
           return  this.toProdottiCollection(this.getRsQuery("select * from prodotto where codice='"+codice+"';")).get(0);
            
        }
       
        
        
        public Prodotto carrelloToProdotto(Carrello c)        {
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
        public ArrayList<Prodotto> ProdottiDaOrdinare(int n){
            ArrayList<Prodotto>l=this.toProdottiCollection(this.getRsQuery("select codice,nome,pezzi,url_immagine,prezzo,punti,categoria,prezzo_premio,descrizione from prodotto where pezzi<='"+n+"'  order by pezzi;"));
            return l;
        }
        
    @Override
         public ArrayList<Prodotto> ricercaProdotti(String match){
            //FUNZIONA V2.0
      
        	match=match.toLowerCase();
        ArrayList <Prodotto> l=new ArrayList<Prodotto>();
         try {    
             ResultSet s=this.conn.createStatement().executeQuery("select * from prodotto where prezzo is not null and pezzi>0;");
            
             while(s.next())
                for(int i=0;i<match.split(" ").length;i++){
                    String temp=match.split(" ")[i];
                     if(((" "+s.getString("nome")).toLowerCase()).contains((" "+temp).substring(0,temp.length()-1))){
                         l.add(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio"),s.getString("descrizione")));
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
       
        public ArrayList<Prodotto> prodottiACaso(int n){
            ArrayList<Prodotto>l=this.toProdottiCollection(this.getRsQuery("select * from prodotto where pezzi > 0 ;"));
            while(l.size()>n)
                l.remove((int)(Math.random()*l.size()));
            return l;
        }
        
        
        
         public ArrayList<Prodotto> prodottiPreferiti(int n,Cliente c){//
            return this.prodottiPreferiti(n,c.getCategoria());
        }
        public ArrayList<Prodotto> prodottiPreferiti(int n,String categoriaPreferita){
            ArrayList<Prodotto>l=this.toProdottiCollection(this.getRsQuery("select codice,nome,pezzi,url_immagine,prezzo,prodotto.punti,categoria,prezzo_premio,descrizione from prodotto where categoria='"+categoriaPreferita+"' and pezzi > 0 ;"));
            while(l.size()>n)
                l.remove((int)(Math.random()*l.size()));
            return l;
        }
        
        public ArrayList<Prodotto> prodottoAction(int n,String categoriaPreferita){
            ArrayList<Prodotto>l=this.toProdottiCollection(this.getRsQuery("select codice,nome,pezzi,url_immagine,prezzo,prodotto.punti,categoria,prezzo_premio,descrizione from prodotto where categoria='"+categoriaPreferita+"';"));
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
        	ResultSet s=this.conn.createStatement().executeQuery("select * from utente where nickname='"+h.getNickname()+"' and attivo=FALSE;");
            if(s.next())
            {
            	this.conn.createStatement().execute("delete from utente where nickname='"+h.getNickname()+"';");
            }
            s=this.conn.createStatement().executeQuery("select * from utente where nickname='"+h.getNickname()+"';");
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
	                PreparedStatement t=this.conn.prepareStatement("insert into utente (nickname,pass,tipo) values (?,?,?);");
	                    t.setString(1,nickname);
	                    t.setString(2,password);
	                    t.setString(3,tipo);
	                    t.execute();
	                    return true;
                }
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }
            }
      
    @Override
       public Utente login(String nickname,String pass){
        
          
           
          
            ResultSet s=this.getRsQuery("select * from utente where attivo=TRUE;");
        try {
            while(s.next()){
                if(s.getString(1).equals(nickname) && s.getString(2).equals(pass)){
                    if(s.getString("tipo").equals("cliente"))
                    {
                        return new Cliente(s.getString("via"),s.getString("numero"),s.getString("citta"),s.getString("carta_credito"),s.getString("scadenza_carta"),s.getString("PIN"),s.getInt("punti"),s.getString("categoria_preferita"),s.getString("nickname"),s.getString("pass"));
                    
                    }
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
       
       public void contatoreLogin(String nickname)
       {
    	   PreparedStatement ssss;
		try {
			ssss = this.conn.prepareStatement("insert into login (nickname,data_log,ora_log) values (?,?,?);");
			Calendar c=Calendar.getInstance();
	       	Date date=new Date();
	       	DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	       	ssss.setString(2,dateFormat.format(date));
           ssss.setString(1,nickname);
           dateFormat = new SimpleDateFormat("HH:mm:ss");
           ssss.setString(3,dateFormat.format(date));
           ssss.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
	       	
    	   
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
                h.put(new Prodotto(s.getString("codice"),new URL(s.getString("url_immagine")),s.getString("nome"),s.getInt("pezzi"),s.getFloat("prezzo"),s.getInt("punti"),s.getString("categoria"),s.getInt("prezzo_premio"),s.getString("descrizione")),s.getInt("quantita"));
          
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
                  PreparedStatement ssss=this.conn.prepareStatement("insert into acquisto (data_ac,quantita,utente,id_oggetto,ora_ac) values (?,?,?,?,?);");
                  Calendar c=Calendar.getInstance();
                  
              	  Date date=new Date();
              	  ssss.setDate(1, new java.sql.Date(date.getTime()));
                  ssss.setInt(2, t.getInt(2));
                  ssss.setString(3, t.getString(3));
                  ssss.setString(4, t.getString(4));
                  DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                  ssss.setString(5,dateFormat.format(date) );
                  ssss.execute();
                          
              }
              this.conn.commit();
              db.closeConnection();
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
              db.closeConnection();
            }
            catch (SQLException ex) {
            	Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        	}
            
        }
        
        public ArrayList<Prodotto> visualizzaListaPremi(){           
           return this.toProdottiCollection(this.getRsQuery("select * from Prodotto where prezzo_premio is not null"));       
        }
        
        public Categoria getCategoria(String nome)
        {
        	Categoria c=new Categoria();
        	try {
				ResultSet s=this.conn.createStatement().executeQuery("select descrizione from categoria where nome='"+nome+"';");
				if(s.next())
				{
					c.setNome(nome);
					c.setDescrizione(s.getString("descrizione"));
				}
				else
				{
					c.setNome("");
					c.setDescrizione("");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return c;
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
        
        public Boolean inserisciCategoria(String nome,String descrizione) throws SQLException{
        	if(nome==null || nome=="")
        	{
        		return false;
        	}
        	else{
        		return this.conn.createStatement().execute("insert into categoria (nome,descrizione) values('"+nome+"','"+descrizione+"');");       
        	}
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
       
       
       public void azzeraQuantitaProdotto(String s)
       {
    	   Database db=new Database();
    	   try {
			this.conn.createStatement().executeUpdate("update prodotto set pezzi=0 where codice='"+s+"';");
			db.closeConnection();
    	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
       
        
        public static void main(String args[]) throws SQLException{
          // System.out.println(new Database().ricercaProdotti("notebook R522"));
          // new Database().inserisciUtente(new Cliente("Via Verdi","16","Vicenza","4558523574121245", "12/18","524",123,"telefonia","paolo00","matitanera"));
            //new Database().inserisciUtente(new Impiegato("lav05","merengue"));
           // System.out.println(new Database().login(new Utente("franco89","ciaociao")));
           // System.out.println(new Database().login(new Utente("admin","fghh")));
           // String s=new Database().login(new Impiegato("lav05","merengue")).toString();
           // System.out.println("s = " + s);
           // System.out.println(new Database().prodottiPreferiti(3,"telefonia"));
            /*
            HashMap <Prodotto,Integer> h=new Database().carrelloUtente(null);
           for(Prodotto p:h.keySet()){
               Prodotto pr=p;
               int quantita=h.get(p);
                   
           }*/
            new Database().aggiungiAlCarrello(new Carrello("ag349512","gaetano78",5));
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Inizio statistiche Admin
        
        
        /*
        public HashMap<Cliente,Double> SoldiSpesiClienti(){
            HashMap<Cliente,Double> l=new HashMap<Cliente,Double>();
            ResultSet s=this.getRsQuery("select nickname,pass,via,utente.numero,citta,carta_credito,scadenza_carta,PIN,utente.punti,categoria_preferita,sum(prezzo) as soldi_spesi "
                    + "from Utente,Acquisto,Prodotto "
                    + "where nickname=utente and id_oggetto=codice "
                    + "group by nickname,pass,via,utente.numero,citta,carta_credito,scadenza_carta,PIN,utente.punti,categoria_preferita "
                    + "order by soldi_spesi desc;"   );
         try {
             while(s.next())
                 l.put( new Cliente(s.getString("via"),s.getString("numero"),s.getString("citta"),s.getString("carta_credito"),s.getString("scadenza_carta"),s.getString("PIN"),s.getInt("punti"),s.getString("categoria_preferita"),s.getString("nickname"),s.getString("pass")),s.getDouble("soldi_spesi") );
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return l;
        }
        */
        public HashMap<String,Float> getSoldiSpesiClienti(){
            HashMap<String,Float> l=new HashMap<String,Float>();
            ResultSet s=this.getRsQuery("select nickname,sum(prezzo) as soldi_spesi "
                    + "from Utente,Acquisto,Prodotto "
                    + "where nickname=utente and id_oggetto=codice "
                    + "group by nickname "
                    + "order by soldi_spesi desc;"   );
         try {
             while(s.next())
                 l.put(s.getString("nickname")+" "+s.getFloat("soldi_spesi"),s.getFloat("soldi_spesi") );
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return l;
        }
        
        
        public HashMap<String,Float> getNumeroProdottiVendutiXCategoria(){
              HashMap<String,Float> l=new HashMap<>();
            ResultSet s=this.getRsQuery("select categoria,sum(quantita) as n_prodotti "
                    + "from Prodotto,Acquisto "
                    + "where codice=id_oggetto "
                    + "group by categoria;");
         try {
             while(s.next())
                 l.put(s.getString("categoria")+" "+s.getInt("n_prodotti"),new Float (s.getInt("n_prodotti")));
         } catch (SQLException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return l;
         }
        
        public HashMap<String,Float> getProdottiPiuComprati(){ // i 5 prodotti più comprati
            HashMap<String,Float> l=new HashMap<>();
            int i=5;
          ResultSet s=this.getRsQuery("select id_oggetto,sum(quantita) as n_prodotti "
                  + "from Acquisto "
                  + "group by id_oggetto "
                  + "order by n_prodotti desc;");
       try {
           while(i>0)
           {
        	   s.next();
        	   l.put(s.getString("id_oggetto")+" "+s.getInt("n_prodotti")+" "+this.getProdotto(s.getString("id_oggetto")),new Float (s.getInt("n_prodotti")));
        	   i++;
           }
       } catch (SQLException ex) {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
       }
       return l;
       }
       
        public HashMap<String,Float> getOraPiuLoggata(){ // i 5 prodotti più comprati
            HashMap<String,Float> l=new HashMap<>();
          ResultSet s=this.getRsQuery("select substring(ora_log from 1 for 2) as ora,count(*) as conto "
          		+ "from login "
          		+ "group by ora "
          		+ "order by conto;");
       try {
           while(s.next())
           {
        	   l.put(s.getString("ora"),new Float (s.getInt("conto")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
       }
       return l;
       }

    
       
        
}
