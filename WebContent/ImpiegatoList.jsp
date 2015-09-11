<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="grafica.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:useBean id="grafica" class="grafica.Grafica" scope="session"/>
<jsp:setProperty name="grafica" property="*"/>
<jsp:useBean id="pulsante" class="grafica.pulsante" scope="session"/>
<jsp:setProperty name="pulsante" property="*"/>
<jsp:useBean id="impiegato" class="database.Impiegato" scope="session"/>
<jsp:setProperty name="impiegato" property="*"/>



<% 
out.println(grafica.getIntestazione());
String quantita=request.getParameter("quantita");


if (request.getParameter("codice")==null)
{
	Prodotto p=new Prodotto();
	out.println(grafica.getInserisciProdotti(p));	//scrive il form di inserimento prodotti, se è passato un codice prodotto fa l'aggiornamento nel database
}
else
{
	Database db=new Database();
	Prodotto p=new Prodotto();
	p=db.getProdotto(request.getParameter("codice"));
	out.println(grafica.getInserisciProdotti(p));
	db.closeConnection();
	
}


out.println(grafica.getFormCreaCategoria());	//form per creare una nuova categoria


if (!(request.getParameter("errore")==null))
	out.println("<br>Errore di inserimento!");

pulsante.setPagina("Logout");//bottone Premi
pulsante.setAttributi("");
pulsante.setLabel("Logout");
pulsante.setMetodo("GET");
out.println(pulsante.getBottone());



out.println(grafica.getFormSogliaProdotti());

out.println("<br><br>");


Database db=new Database();
if(quantita==null || quantita=="")
{
	grafica.setListaProdotti(db.ProdottiDaOrdinare(10));		//setto e stampo la lista carrello
}
else
{
	grafica.setListaProdotti(db.ProdottiDaOrdinare(Integer.parseInt(quantita)));
}
db.closeConnection();
out.print(grafica.getImpiegatoListaProdotti());



out.println(grafica.getChiusura());

%>

