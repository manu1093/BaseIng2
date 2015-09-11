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
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>

<%
String nickname=utente.getNickname();	//settaggio iniziale
Database db=new Database();
ArrayList<Carrello> lista_carrello= new ArrayList<Carrello>();
lista_carrello=db.visualizzaCarrello(utente);


out.println(grafica.getIntestazione());

if (nickname==null || nickname=="" || lista_carrello.isEmpty())	//utente non loggato o qui per sbaglio
{
	response.sendRedirect("/BaseIng2/Logout");
}
else
{
	if (utente.getCartaCredito()==null || utente.getPIN()==null)	//devono esserci i dati carta di credito 
		response.sendRedirect("BaseIng2/paginaPersonale.jsp");
	else
	{
		
		grafica.setCliente(utente);		//stampa oggetti del carrello da comprare 
		grafica.setListaCarrello(lista_carrello);
		out.print(grafica.getProdottiDaAcquistare());
		
		db.refreshUtente(session, utente);


		pulsante.setPagina("index.jsp");//bottone Indietro
		pulsante.setAttributi("");
		pulsante.setLabel("Indietro");
		pulsante.setMetodo("GET");
		out.println(pulsante.getBottone());
		
		pulsante.setPagina("Logout");//bottone Premi
		pulsante.setAttributi("");
		pulsante.setLabel("Logout");
		pulsante.setMetodo("GET");
		out.println(pulsante.getBottone());
		
	
	
	}
	
	
	
	
}
db.closeConnection();
out.println(grafica.getChiusura());
%>
