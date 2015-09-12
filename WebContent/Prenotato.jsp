<%@page import="servlet.Refresh"%>
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

<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<%! String not_logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>";  %>


<%
out.println(grafica.getIntestazione());
String codice="";
int quantita=0;
Database db= Database.getInstance();
Prodotto p=new Prodotto();

try{
	codice=request.getParameter("codice");			//recupero valori GET
	quantita=Integer.parseInt(request.getParameter("quantita"));
	p=db.getProdotto(codice);
	
	grafica.setProdottoOsservato(p);	//stampo premio prenotato
	grafica.setQuantita(quantita);
	out.println(grafica.getPremioPrenotato());
	
	
	db.acquistaPremio(utente, codice, quantita);
	Refresh.refreshUtente(session, utente);
	
	
	pulsante.setPagina("index.jsp");//bottone indietro
	pulsante.setAttributi("");
	pulsante.setLabel("Indietro");
	pulsante.setMetodo("GET");
	out.println(pulsante.getBottone());
	//db.closeConnection();
}
catch(Exception e)
{
	response.sendRedirect("/BaseIng2/Premi.jsp");	
}








out.println(grafica.getChiusura());
%>

