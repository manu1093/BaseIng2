<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="grafica.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="grafica" class="grafica.Grafica" scope="session"/>
<jsp:setProperty name="grafica" property="*"/>
<jsp:useBean id="pulsante" class="grafica.pulsante" scope="session"/>
<jsp:setProperty name="pulsante" property="*"/>
<jsp:useBean id="utente" class="database.Cliente" scope="session"/>
<jsp:setProperty name="utente" property="*"/>
<jsp:useBean id="admin" class="database.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>
<jsp:useBean id="impiegato" class="database.Impiegato" scope="session"/>
<jsp:setProperty name="impiegato" property="*"/>

<%

out.println(grafica.getIntestazione());

out.println(grafica.getFormRicercaPerNome()); // form di ricerca per nome



if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
{
	response.sendRedirect("/BaseIng2/AdminList.jsp");	
}
if (utente.getNickname().isEmpty() || utente.getNickname()=="") //non loggato
{
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();//dichiarazioni
	Database db=new Database();
	if (session.getAttribute("lista_prodotti")==null)
	{
		lista=db.ProdottiACaso(6);
	}
	else
	{
		lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
	}
	
	out.println("Benvenuto nuovo utente"+grafica.getFormLogin());//form login
	
	
	
	String descrizione="";		//Brutto, ma visualizza consigli per l'utente in base alla categoria impostata
	try
	{
		descrizione=db.getCategoria((lista.get(0)).getCategoria()).getDescrizione();
		
	}
	catch(Exception e)
	{
		System.err.print(e.getMessage());
		descrizione=db.getCategoria("telefonia").getDescrizione();
	}
	out.println(descrizione);
	
	
	
	out.println(grafica.getFormRicercaPerCategoria());//form scelta categorie
	
	
	session.setAttribute("lista_prodotti", lista);
	grafica.setListaProdotti(lista);
	out.println(grafica.getListaProdotti());
	
	
	pulsante.setPagina("paginaPersonale.jsp");//bottone pagina iscrizione (iscrizione)
	pulsante.setAttributi("");
	pulsante.setMetodo("GET");
	pulsante.setLabel("Pagina di Iscrizione");
	out.println(pulsante.getBottone());
	db.closeConnection();
	
}
else							//loggato
{
	Database db=new Database();				//settaggio iniziale
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
	Cliente c=new Cliente();
	db.refreshUtente(session, utente);
	
	

	out.print("Benvenuto "+utente.getNickname());		//Frasi convenevoli
	out.println("<BR>Ecco la tua situazione punti: "+ utente.getPunti());//può servire?
	lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
			
			
	String descrizione="";		//Brutto, ma visualizza consigli per l'utente in base alla categoria impostata
	try
	{
		descrizione=db.getCategoria((lista.get(0)).getCategoria()).getDescrizione();
	}
	catch(Exception e)
	{
		System.err.print(e.getMessage());
		descrizione=db.getCategoria("telefonia").getDescrizione();
	}
	out.println(descrizione);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	out.println(grafica.getFormRicercaPerCategoria());		//Form ricerca per categorie
	
	
	if (lista.isEmpty() || lista==null)			//Stampa lista prodotti
	{
		lista=db.ProdottiACaso(3);
	}
	else
	{
		grafica.setListaProdotti(lista);		//setto e stampo la lista carrello
		out.print(grafica.getListaProdotti());
	}
	
	
	pulsante.setPagina("Carrello.jsp");//bottone Carrello
	pulsante.setAttributi("codice=");
	pulsante.setLabel("Vai al Carrello");
	pulsante.setMetodo("POST");
	out.println(pulsante.getBottone());
	
	
	pulsante.setPagina("paginaPersonale.jsp");//bottone Pagina Personale
	pulsante.setAttributi("");
	pulsante.setLabel("Pagina Personale");
	pulsante.setMetodo("POST");
	out.println(pulsante.getBottone());
	
	
	pulsante.setPagina("Premi.jsp");//bottone Premi
	pulsante.setAttributi("");
	pulsante.setLabel("Lista Premi");
	pulsante.setMetodo("POST");
	out.println(pulsante.getBottone());
	
	pulsante.setPagina("Logout");//bottone Premi
	pulsante.setAttributi("");
	pulsante.setLabel("Logout");
	pulsante.setMetodo("GET");
	out.println(pulsante.getBottone());
	
	db.closeConnection();
	
}		//fine utente loggato

out.println(grafica.getChiusura());
%>




