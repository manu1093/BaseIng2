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
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>
<%
//Parametri iniziali
String codice=request.getParameter("codice");


if (!(utente.getNickname().isEmpty() || utente.getNickname()==""))//utente loggato
{
	//Istanzio la lista carrello
	Database db=new Database();
	ArrayList<Carrello> lista_carrello= new ArrayList<Carrello>();
	lista_carrello=db.visualizzaCarrello(utente);
		
	if (!(codice==null || codice==""))
	{
		out.println("Ciao "+utente.getNickname());
		out.print("Hai scelto l'oggetto ");
	
		//Istanzio la lista prodotti 
		ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
		lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
		
	
		//Inizializzo la stampa a video dei prodotti a caso (sarà da perfezionare con la scelta della categoria preferita dell'utente)
		//la lista è lista_prodotti (presente nella session)
		//!!!!Attenzione all'indirizzo della form!
		if (lista.isEmpty())	//succede mai
		{
			lista=db.ProdottiPreferiti(3, utente);
			System.out.print("Lista vuota");
			response.sendRedirect("/BasiIng2/Logout");
		}
		else
		{
			
			Prodotto p= new Prodotto();
			p=db.getProdotto(codice);
			out.print("<FORM METHOD=POST ACTION=\"/BaseIng2/Aggiungi?codice="+p.getCodice()+"\">");
			out.println("<table border=1>");
			out.print("<tr><td>Nome: "+p.getNome()+"</td>");
			out.print("<td><img src=\""+p.getImmagine()+"\" width=200 height=200></td>");
			out.print("<td>Prezzo "+p.getPrezzo()+"</td></tr>");
			out.print("<tr><td>Quantità da acquistare <select name=\"quantita\"> ");
			int i=1;
			while (i<=p.getPezzi())
			{
				out.println("<option value="+i+">"+i+"</option>");
				i++;
			}
			out.print("</select>");
			out.print(" su "+p.getPezzi()+" disponibili </td></tr>");
			out.print("<tr><td>Acquisterai con la tua carta di credito "+ utente.getCartaCredito()+"</td> ");
			out.print("<td><INPUT TYPE=\"submit\" value=\"aggiungi al carrello\"></td></tr>");
			out.print("</table></FORM>");
				
		}
	}
	else
	{
		out.println("Ciao "+utente.getNickname());
		out.print("<br><br> ");
	}
		
		if (lista_carrello.isEmpty())
		{
			
			out.print("<h3 align=center>Il tuo carrello è ancora vuoto</h3>");
		}
		else
		{
			out.println("Il tuo carrello attuale");
		
			for(Carrello p: lista_carrello)
			{
					if (p.getProdotto()==null)
						continue;
					else
					{
						out.println("<br>Nome: "+p.getProdotto());
						out.println("<FORM METHOD=POST ACTION=\"/BaseIng2/Rimuovi?codice="+p.getProdotto()+"\">");
						//out.println(p.getProdotto());
						out.println("<br><img src=\""+db.getProdotto(p.getProdotto()).getImmagine().toString()+"\" width=200 height=200>");
						out.println("<br>Prezzo "+db.getProdotto(p.getProdotto()).getPrezzo());
						out.print("<br>Quantità da acquistare: "+p.getQuantita());
						out.print("<br>su +"+db.getProdotto(p.getProdotto()).getPezzi()+"disponibili");
						out.print("<br><INPUT TYPE=\"submit\" value=\"Rimuovi dal carrello\"></FORM>");
					}
					
			}
			
			out.println("<br><br><FORM METHOD=GET ACTION=\"/BaseIng2/Acquistato.jsp\">");
			out.println("Acquisterai con la tua carta di credito "+ utente.getCartaCredito());
			out.print("<INPUT TYPE=\"submit\" value=\"compra adesso\"></FORM>");
		}
	out.print("<br><br>"+indietro);
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
			System.out.print("Lista vuota");
			response.sendRedirect("/BasiIng2/Logout");
		}
		else
		{
			out.println("<table border=1>");
			Prodotto p= new Prodotto();
			Database db=new Database();
			p=db.getProdotto(codice);
			out.println("<tr><td><br>Nome: "+p.getNome()+"</td>");
			out.println("<td>URL immagine "+p.getImmagine()+"</td>");
			out.println("<td><img src=\""+p.getImmagine()+"\" width=200 height=200></td>");
			out.println("<td>Prezzo "+p.getPrezzo()+"</td>");
			out.println("</table>");
			out.print("<br>Quantità acquistabile <select name=\"quantita\"> ");
			out.println("<option value="+p.getPezzi()+">"+p.getPezzi()+"</option></select>");
			
			out.print("<BR><BR>"+not_logged);
		}
}

%>

</body>
</html>