<%@page import="database.Database"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="utente" class="database.Cliente" scope="session"/>
<jsp:setProperty name="utente" property="*"/>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<%! String not_logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>";  %>
<%
String codice=request.getParameter("codice");
String nickname=request.getParameter("nickname");


if (!(nickname.isEmpty() || nickname==""))//utente loggato
{
	Cliente c=new Cliente();
	c=(Cliente)session.getAttribute("utente");
	session.setAttribute("utente", c);
	out.println("Ciao "+nickname);
	out.print("Hai scelto l'oggetto ");
	
	//Istanzio la lista carrello
	Database db=new Database();
	ArrayList<Carrello> lista_carrello= new ArrayList<Carrello>();
	lista_carrello=db.visualizzaCarrello(utente);
	
	//Istanzio la lista prodotti 
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
	lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
	
	
	//Inizializzo la stampa a video dei prodotti a caso (sarà da perfezionare con la scelta della categoria preferita dell'utente)
	//la lista è lista_prodotti (presente nella session)
	//!!!!Attenzione all'indirizzo della form!
	if (lista.isEmpty())
	{
		out.print("Lista vuota");
		response.sendRedirect("/BasiIng2/Logout");
		out.println("<br>"+logged);
	}
	else
	{
		out.println("<table border=1>");
		for(Prodotto p: lista)
		{
			if (codice.equals(p.getCodice()))
			{
				session.setAttribute("codice", codice);
				session.setAttribute("nickname", nickname);
				out.println("<FORM METHOD=GET ACTION=\"/BaseIng2/Aggiungi?codice="+p.getCodice()+"&nickname="+utente.getNickname()+"\">");
				out.println("<tr><td><br>Nome: "+p.getNome()+"</td>");
				out.println("<td>URL immagine "+p.getImmagine()+"</td>");
				out.println("<td><img src=\""+p.getImmagine()+"\" width=200 height=200></td>");
				out.println("<td>Prezzo "+p.getPrezzo()+"</td>");
				out.print("<br>Quantità da acquistare <select name=\"quantita\"> ");
				int i=1;
				while (i<=p.getPezzi())
				{
					out.println("<option value="+i+">"+i+"</option>");
					i++;
				}
				out.print("su +"+p.getPezzi()+"disponibili");
				out.println("Acquisterai con la tua carta di credito "+ utente.getCartaCredito()+" ");
				out.print("<INPUT TYPE=\"submit\" value=\"aggiungi al carrello\"></FORM>");
			}
			out.println("</table>");
			
		}
		
		session.setAttribute("lista_prodotti", lista);
		
		if (lista_carrello.isEmpty())
		{
			out.print("<h3 align=center>Il tuo carrello è ancora vuoto</h3>");
		}
		else
		{
			
			for(Carrello p: lista_carrello)
			{
					out.println("Il tuo carrello attuale");
					out.println("<FORM METHOD=GET ACTION=\"/BaseIng2/Acquistato.jsp\">");
					out.println("<tr><td><br>Nome: "+p.getProdotto()+"</td>");
					out.println("<td>URL immagine "+db.CarrelloToProdotto(p).getImmagine()+"</td>");
					out.println("<td><img src=\""+db.CarrelloToProdotto(p).getImmagine()+"\" width=200 height=200></td>");
					out.println("<td>Prezzo "+db.CarrelloToProdotto(p).getPrezzo()+"</td>");
					out.print("Quantità da acquistare <select name=\"quantita\"> ");
					int i=1;
					while (i<=db.CarrelloToProdotto(p).getPezzi())
					{
						out.println("<option value="+i+">"+i+"</option>");
						i++;
					}
					out.print("su +"+db.CarrelloToProdotto(p).getPezzi()+"disponibili");
					out.println("Acquisterai con la tua carta di credito "+ utente.getCartaCredito()+" ");
					out.print("<INPUT TYPE=\"submit\" value=\"compra adesso\"></FORM>");
					out.println("<FORM METHOD=GET ACTION=\"/BaseIng2/Rimuovi?quantita=1&nickname="+p.getUtente()+"&codice="+p.getProdotto()+"\">");
					out.print("<INPUT TYPE=\"submit\" value=\"Rimuovi dal carrello\"></FORM>");
					
					
				
				
			}
		session.setAttribute("lista_carrello", lista_carrello);
		}
	}
	out.print("<BR><BR>"+logged);
}
else //utente anonimo
{
	out.println("Ciao nuovo utente, <br> ti ricordiamo che prima di procedere all'acquisto devi registrarti.<br>");
	out.print("Stai osservando l'oggetto: ");
	
	//Istanzio la lista prodotti 
		ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
		lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
		if (lista.isEmpty())
		{
			out.print("Lista vuota");
			response.sendRedirect("/BasiIng2/Logout");
			out.println("<br>"+logged);
		}
		else
		{
			out.println("<table border=1>");
			for(Prodotto p: lista)
			{
				if (codice.equals(p.getCodice()))
				{
					//session.setAttribute("codice", codice);
					//session.setAttribute("nickname", nickname);
					//out.println("<FORM METHOD=GET ACTION=\"/BaseIng2/Aggiungi\">");
					out.println("<tr><td><br>Nome: "+p.getNome()+"</td>");
					out.println("<td>URL immagine "+p.getImmagine()+"</td>");
					out.println("<td><img src=\""+p.getImmagine()+"\" width=200 height=200></td>");
					out.println("<td>Prezzo "+p.getPrezzo()+"</td>");
					out.print("<br>Quantità acquistabile <select name=\"quantita\"> ");
					out.println("<option value="+p.getPezzi()+">"+p.getPezzi()+"</option>");
					//out.println("Acquisterai con la tua carta di credito "+ utente.getCartaCredito()+" ");
					//out.print("<INPUT TYPE=\"submit\" value=\"aggiungi al carrello\"></FORM>");
				}
				
				
			}
			out.println("</table>");
			out.print("<BR><BR>"+not_logged);
	}
}

%>

</body>
</html>