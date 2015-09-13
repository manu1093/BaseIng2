<%@page import="servlet.Refresh"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.beans.*"%>
<%@page import="database.*"%>
<%@page import="grafica.*"%>
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
        <title>HOME PAGE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="mystyle.css" rel="stylesheet" type="text/css"/>
    </head>
    
    <body>
        <div id="menuservizio">
            
            <div id="box-3">
         <%

        out.println(grafica.getIntestazione());

        out.println(grafica.getFormRicercaPerNome()); // form di ricerca per nome
        %>
            </div>
            
                 <img id="logo" src="Logo.PNG" alt="" height="100%"/>
            
        </div>
           
     <!--lista oggetti a caso-->

<%
if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
{
	response.sendRedirect("/BaseIng2/AdminList.jsp");	
}
if (utente.getNickname().isEmpty() || utente.getNickname()=="") //non loggato
{
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();//dichiarazioni
	Data db= Data.getInstance();
	if (session.getAttribute("lista_prodotti")==null)
	{
		lista=db.prodottiACaso(6);
	}
	else
	{
		lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
	}
        %>
	  <%--FORM PER LOGIN: BOX-1--%>
        <div id ="box-1">
            <font face="Verdana" size="4" color="blue">
            <%
	out.println("Benvenuto nuovo utente"+grafica.getFormLogin());//form login
       /* pulsante.setPagina("paginaPersonale.jsp");//bottone pagina iscrizione (iscrizione)
	pulsante.setAttributi("");
	pulsante.setMetodo("GET");
	pulsante.setLabel("Pagina di Iscrizione");
	out.println(pulsante.getBottone());*/
%>
</font>
        </div>
    <%--FORM ISCRIZIONE: BOX-2--%>
       
<br>
<br>

   
<%
	
	
	String descrizione="";		//Brutto, ma visualizza consigli per l'utente in base alla categoria impostata
	%>
        
    
        <%
        try
	{
		descrizione=db.getCategoria((lista.get(0)).getCategoria()).getDescrizione();
		
	}
	catch(Exception e)
	{
		System.err.print(e.getMessage());
		descrizione=db.getCategoria("telefonia").getDescrizione();
	}
        %>
        
         <font face= "Verdana" size="4" color="green">
        <%
	out.println(descrizione);
	%>
        </font>
        <br>
        <font face ="Verdana" size="4" color="blue">
        <%
	
	
	out.println(grafica.getFormRicercaPerCategoria());//form scelta categorie
	
	%>
                            <div id= "table">
    <%        
     
ArrayList<Azione> azione= new ArrayList<Azione>();
azione=db.listaAction(1);
grafica.setAzione(azione.get(0));
    out.println(grafica.getAzione());
    %>
        </div>

        <%
	session.setAttribute("lista_prodotti", lista);
	grafica.setListaProdotti(lista);
	out.println(grafica.getListaProdotti());
	%>
       
	

        <%
}
else							//loggato
{
	Data db= Data.getInstance();				//settaggio iniziale
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
	Cliente c=new Cliente();
	Refresh.refreshUtente(session, utente);
	%>


        <br>
        <br>
        <font face="verdana" size="5" color="blue">
        <%
	out.print("Benvenuto "+utente.getNickname()+"!");		//Frasi convenevoli
        %>
        </font>
        <br>
        <font face="Calibri" size="5" color="green">
        <%
	out.println("<BR>Ecco la tua situazione punti: "+ utente.getPunti()+"<BR>");//può servire?
	lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
	%>
        </font>
        <%
			
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
        %>
        <font face="Verdana" size="4" color="green">
        <%
	out.println(descrizione);
	%>
        </font>
         <div id= "partesinistra">
             <div id="navbar">
                 <ul>
                     
	<%
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
	%>
        </ul>
        </div>
        <div >
    <%        
     
ArrayList<Azione> azione= new ArrayList<Azione>();
azione=db.listaAction(1);
grafica.setAzione(azione.get(0));
    out.println(grafica.getAzione());
    %>
        </div>
         </div>
        <font face="Calibri" size="5" color="blue">
	<%
	out.println(grafica.getFormRicercaPerCategoria());		//Form ricerca per categorie
	%>
        </font>
        <%
	
	if (lista.isEmpty() || lista==null)			//Stampa lista prodotti
	{
		lista=db.prodottiACaso(3);
	}
	else
	{
		grafica.setListaProdotti(lista);		//setto e stampo la lista carrello
		out.print(grafica.getListaProdotti());
	}
	%>
        
       
        <%
	//db.closeConnection();
	
}		//fine utente loggato



%>
</font>

<%
out.println(grafica.getChiusura());
%>
 
               




