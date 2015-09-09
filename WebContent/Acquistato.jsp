<%@page import="java.util.ArrayList"%>
<%@page import="database.Prodotto"%>
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
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>

<%
String codice=(String) session.getAttribute("codice");
String nickname=(String) session.getAttribute("nickname");
if (nickname==null || nickname=="")
{
	response.sendRedirect("/BaseIng2/Logout");
}
else
{
	out.print("Grazie "+nickname+" <br>per aver acquistato n. "+request.getParameter("quantita")+" pezzi del seguente articolo");	
}
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
	out.println("<table>");
	for(Prodotto p: lista)
	{
		if (codice.equals((String)p.getCodice()))
		{
			out.println("<tr><td>Nome: "+p.getNome()+"</td>");
			//out.println("<td>URL immagine "+p.getImmagine()+"</td>");
			out.println("<td><img src=\""+p.getImmagine()+"\" width=200 height=200></td>");
			out.println("<td>Costo complessivo"+p.getPrezzo()*(Float.parseFloat(request.getParameter("quantita")))+"</td></tr>");
		}
	}
	out.println("</table>");
}
out.print("<BR><BR>"+logged);
out.print("<BR><BR>"+indietro);
%>
</body>
</html>