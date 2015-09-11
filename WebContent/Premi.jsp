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
Database db=new Database();		//settaggio iniziale
ArrayList<Prodotto> lista_premi= new ArrayList<Prodotto>();
lista_premi=db.visualizzaListaPremi();



out.println(grafica.getIntestazione());


out.println("Ecco la lista premi");


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


db.closeConnection();



%>

