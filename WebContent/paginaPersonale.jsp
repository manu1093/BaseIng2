<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>

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
