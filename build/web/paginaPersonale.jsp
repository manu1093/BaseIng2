<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="database.beans.*"%>
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
        </div>
        <br>
        <font face="Verdana" size ="4" color="blue">
<% 

out.println(grafica.getIntestazione());


if (utente.getNickname().isEmpty() || utente.getNickname()=="") //non loggato
{
	
	out.println(grafica.getFormIscrizioneAnonimo());
	
	pulsante.setPagina("index.jsp");//bottone torna all'index
	pulsante.setAttributi("");
	pulsante.setMetodo("GET");
	pulsante.setLabel("Indietro");
	out.println(pulsante.getBottone());
	
}
else  //loggato
{
	
	grafica.setCliente(utente);
	out.println(grafica.getFormIscrizione());

	pulsante.setPagina("index.jsp");//bottone torna all'index
	pulsante.setAttributi("");
	pulsante.setMetodo("GET");
	pulsante.setLabel("Indietro");
	out.println(pulsante.getBottone());
	
	pulsante.setPagina("Logout");//bottone torna all'index
	pulsante.setAttributi("");
	pulsante.setMetodo("GET");
	pulsante.setLabel("Logout");
	out.println(pulsante.getBottone());


	
	
}







out.println(grafica.getChiusura());
%>
</font>
    </body>
</html>