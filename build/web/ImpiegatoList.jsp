<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="grafica.*"%>
<%@page import="database.beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:useBean id="grafica" class="grafica.Grafica" scope="session"/>
<jsp:setProperty name="grafica" property="*"/>
<jsp:useBean id="pulsante" class="grafica.Pulsante" scope="session"/>
<jsp:setProperty name="pulsante" property="*"/>
<jsp:useBean id="impiegato" class="database.beans.Impiegato" scope="session"/>
<jsp:setProperty name="impiegato" property="*"/>
<html>
    <head>
        <title>Impiegato</title>
    </head>
      
    <body>
   
    <font face="Calibri" size="6" color="blue">

<%
String quantita=request.getParameter("quantita");
%>
<%
if (request.getParameter("codice")==null)
{
	Prodotto p=new Prodotto();
	out.println(grafica.getInserisciProdotti(p));	//scrive il form di inserimento prodotti, se è passato un codice prodotto fa l'aggiornamento nel database
}
else
{
	Data db= Data.getInstance();
	Prodotto p=new Prodotto();
	p=db.getProdotto(request.getParameter("codice"));
	out.println(grafica.getInserisciProdotti(p));
	//db.closeConnection();
	
}


out.println(grafica.getFormCreaCategoria());	//form per creare una nuova categoria
out.println("Inserisci una nuova action");
out.println(grafica.getFormInserisciAction());
out.println("<br/>Rimuovi una  action<br/>");
out.println(grafica.getFormRimuoviAction());
%>

<%
if (!(request.getParameter("errore")==null))
	out.println("<br>Errore di inserimento!");
%>


<%



out.println(grafica.getFormSogliaProdotti());

%>

<div id="box-8">
<%
pulsante.setPagina("Logout");//bottone Premi
pulsante.setAttributi("");
pulsante.setLabel("Logout");
pulsante.setMetodo("GET");
out.println(pulsante.getBottone());
%>
</div>

<%
Data db= Data.getInstance();
if(quantita==null || quantita=="")
{
	grafica.setListaProdotti(db.ProdottiDaOrdinare(10));		//setto e stampo la lista carrello
}
else
{
	grafica.setListaProdotti(db.ProdottiDaOrdinare(Integer.parseInt(quantita)));
}
//db.closeConnection();
out.print(grafica.getImpiegatoListaProdotti());



out.println(grafica.getChiusura());

%>

</font>
</html>