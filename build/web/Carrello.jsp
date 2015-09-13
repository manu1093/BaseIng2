<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="database.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="grafica" class="grafica.Grafica" scope="session"/>
<jsp:setProperty name="grafica" property="*"/>
<jsp:useBean id="pulsante" class="grafica.Pulsante" scope="session"/>
<jsp:setProperty name="pulsante" property="*"/>
<jsp:useBean id="utente" class="database.beans.Cliente" scope="session"/>
<jsp:setProperty name="utente" property="*"/>
<jsp:useBean id="admin" class="database.beans.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>
<jsp:useBean id="impiegato" class="database.beans.Impiegato" scope="session"/>
<jsp:setProperty name="impiegato" property="*"/>
<!DOCTYPE HTML>
<html>
  
    <head>
        <title>Carrello</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="mystyle.css" rel="stylesheet" type="text/css"/>
    </head>
      <div id="menuservizio">
            
            <div id="box-3">
         <%

        out.println(grafica.getIntestazione());

        out.println(grafica.getFormRicercaPerNome()); // form di ricerca per nome
        %>
            </div>
        </div>
    
<%
String codice=request.getParameter("codice");		//Parametri iniziali

%>

<%
out.println(grafica.getIntestazione());
%>

<%
if (!(utente.getNickname().isEmpty() || utente.getNickname()==""))//utente loggato
{
	//Istanzio la lista carrello
	Data db= Data.getInstance();
	ArrayList<Carrello> lista_carrello= new ArrayList<Carrello>();
	lista_carrello=db.visualizzaCarrello(utente);
		
	if (!(codice==null || codice==""))	//è stato passato un oggetto tramite request
	{
            %>
            <font face="Verdana" size="5" color="blue">
            <%
		out.println("Ciao "+utente.getNickname());
                %>
                </font>
            <font face="Verdana" size="4" color="green">    
                <%
		out.print("Hai scelto l'oggetto ");
                %>
                </font>
	<%
		//Istanzio la lista prodotti 
		ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
		lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
		
	
		//Inizializzo la stampa a video dei prodotti a caso 
		//la lista è lista_prodotti (presente nella session)
		if (lista.isEmpty())	//succede mai
		{
			lista=db.prodottiPreferiti(6, utente);
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
			lista=db.prodottiPreferiti(2, p.getCategoria());
			grafica.setListaProdotti(lista);
			out.println(grafica.getListaProdotti());
			
		}
		
	}
	else
	{
            %>
            <font face="Verdana" size="5" color="blue">
            <%
		out.println("Ciao "+utente.getNickname());
		out.print("<br><br> ");
                %>
                </font>
                <%
	}
		
		if (lista_carrello.isEmpty())
		{
			%>
                        <font face="Verdana" size="5" color="red">
                        <%
			out.print("<h3 align=center>Il tuo carrello è ancora vuoto</h3>");
                        %>
                        </font>
                        <%
		}
		else
		{
			grafica.setListaCarrello(lista_carrello);
			out.println(grafica.getListaCarrello());
		
			pulsante.setPagina("Acquistato.jsp");//bottone Carrello
			out.println("<br><br>Acquisterai con la tua carta di credito "+ utente.getCartaCredito());
			
			
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
                %>
                 <div id="box-6">
                <%
		out.println(pulsante.getBottone());
		%>
                </div>
                <%
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
	Data db=Data.getInstance();
	p=db.getProdotto(codice);
	grafica.setProdottoOsservato(p);
	out.println(grafica.getProdottoOsservatoAnonimo());
	
	out.println("Potrebbe interessarti anche: ");
	ArrayList<Prodotto> lista=db.prodottiPreferiti(2, p.getCategoria());
	grafica.setListaProdotti(lista);
	out.println(grafica.getListaProdotti());
	
	pulsante.setPagina("index.jsp");	//bottone indietro
	pulsante.setAttributi("");
	pulsante.setLabel("Indietro");
	pulsante.setMetodo("GET");
           %>
                 <div id="box-5">
                <%
	out.println(pulsante.getBottone());
	//db.closeConnection();   %>
                </div>
                
                <%
}


out.println(grafica.getChiusura());
%>

