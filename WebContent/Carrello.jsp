<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
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
String codice=request.getParameter("codice");		//Parametri iniziali




out.println(grafica.getIntestazione());



if (!(utente.getNickname().isEmpty() || utente.getNickname()==""))//utente loggato
{
	//Istanzio la lista carrello
	Database db=new Database();
	ArrayList<Carrello> lista_carrello= new ArrayList<Carrello>();
	lista_carrello=db.visualizzaCarrello(utente);
		
	if (!(codice==null || codice==""))	//è stato passato un oggetto tramite request
	{
		out.println("Ciao "+utente.getNickname());
		out.print("Hai scelto l'oggetto ");
	
		//Istanzio la lista prodotti 
		ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
		lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
		
	
		//Inizializzo la stampa a video dei prodotti a caso 
		//la lista è lista_prodotti (presente nella session)
		if (lista.isEmpty())	//succede mai
		{
			lista=db.ProdottiPreferiti(6, utente);
			System.out.print("Lista vuota");
			response.sendRedirect("/BasiIng2/Logout");
		}
		else
		{
			
			Prodotto p= new Prodotto();
			p=db.getProdotto(codice);
			
			
			grafica.setProdottoOsservato(p);
			out.println(grafica.getProdottoOsservato());	//Stampa il prodotto osservato
			
			out.println("Potrebbe interessarti anche: ");
			lista=db.ProdottiPreferiti(2, p.getCategoria());
			grafica.setListaProdotti(lista);
			out.println(grafica.getListaProdotti());
			
		}
		db.closeConnection();
	}
	else
	{
		out.println("Ciao "+utente.getNickname());
		out.print("<br><br> ");
	}
		
		if (lista_carrello.isEmpty())
		{
			
			out.print("<h3 align=center>Il tuo carrello è ancora vuoto</h3>");
		}
		else
		{
			grafica.setListaCarrello(lista_carrello);
			out.println(grafica.getListaCarrello());
		
			pulsante.setPagina("Acquistato.jsp");//bottone Carrello
			out.println("Acquisterai con la tua carta di credito "+ utente.getCartaCredito());
			
			
			pulsante.setAttributi("");		//pulsante vai all'acquisto
			pulsante.setLabel("Compra adesso");
			pulsante.setMetodo("GET");
			out.println(pulsante.getBottone());
			
		}
		pulsante.setPagina("index.jsp");//bottone Carrello
		pulsante.setAttributi("");
		pulsante.setLabel("Indietro");
		pulsante.setMetodo("GET");
		out.println(pulsante.getBottone());
	
		pulsante.setPagina("Logout");//bottone Carrello
		pulsante.setAttributi("");
		pulsante.setLabel("Logout");
		pulsante.setMetodo("GET");
		out.println(pulsante.getBottone());
		
}
else //utente anonimo
{
	out.println("Ciao nuovo utente, <br> ti ricordiamo che prima di procedere all'acquisto devi registrarti.<br>");
	
	
	pulsante.setPagina("paginaPersonale.jsp");//bottone pagina iscrizione
	pulsante.setAttributi("");
	pulsante.setLabel("Pagina di Registrazione");
	pulsante.setMetodo("GET");
	out.println(pulsante.getBottone());
	
	
	out.print("Stai osservando l'oggetto: ");
	
		
	Prodotto p= new Prodotto();	//stampa oggetto cliccato in index
	Database db=new Database();
	p=db.getProdotto(codice);
	grafica.setProdottoOsservato(p);
	out.println(grafica.getProdottoOsservatoAnonimo());
	
	out.println("Potrebbe interessarti anche: ");
	ArrayList<Prodotto> lista=db.ProdottiPreferiti(2, p.getCategoria());
	grafica.setListaProdotti(lista);
	out.println(grafica.getListaProdotti());
	
	pulsante.setPagina("index.jsp");	//bottone indietro
	pulsante.setAttributi("");
	pulsante.setLabel("Indietro");
	pulsante.setMetodo("GET");
	out.println(pulsante.getBottone());
	db.closeConnection();
}


out.println(grafica.getChiusura());
%>

