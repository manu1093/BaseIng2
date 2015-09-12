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
<html>
    <head>
        <title>ListaPremi</title>
    </head>
    
<%
Database db= Database.getInstance();		//settaggio iniziale
ArrayList<Prodotto> lista_premi= new ArrayList<Prodotto>();
lista_premi=db.visualizzaListaPremi();



out.println(grafica.getIntestazione());
%>
<font face="Verdana" size="4" color="blue">
<%
out.println("Ecco la lista premi");
%>
</font>
<%
grafica.setCliente(utente);		//stampo lista premi
grafica.setListaPremi(lista_premi);
out.println(grafica.getListaPremi());



pulsante.setPagina("index.jsp");//bottone indietro
pulsante.setAttributi("");
pulsante.setLabel("Indietro");
pulsante.setMetodo("GET");
out.println(pulsante.getBottone());

pulsante.setPagina("Logout");//bottone Logout
pulsante.setAttributi("");
pulsante.setLabel("Logout");
pulsante.setMetodo("GET");
out.println(pulsante.getBottone());

out.println(grafica.getChiusura());


//DB.closeConnection();



%>

