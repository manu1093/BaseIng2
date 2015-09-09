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


<%! String not_logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Login\">Nickname: <INPUT TYPE=\"text\" NAME=\"nickname\" SIZE=20><br>Password: <INPUT TYPE=\"password\" NAME=\"password\" SIZE=20><P><INPUT TYPE=\"submit\"></FORM>"; %>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>

<%
if (utente.getNickname().isEmpty()) 
{
	out.println("Benvenuto nuovo utente"+not_logged);
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
	Database db=new Database();
	lista=db.ProdottiACaso(3);

	out.print("<table width=\"75%\" border=1 align=\"center\">");
	for(Prodotto p: lista)
	{
		out.println("<tr><td><br>Nome: "+p.getNome()+"</td>");
		out.println("<td>URL immagine "+p.getImmagine()+"</td>");
		out.println("<td><a href=\"/BaseIng2/Carrello.jsp?codice="+p.getCodice()+"&nickname=\"\"><img src=\""+p.getImmagine()+"\" width=200 height=200>"+"</a></td>");
		out.println("<td>Prezzo "+p.getPrezzo()+"</td>");		
	}
	out.print("</table>");	
	session.setAttribute("lista_prodotti", lista);
}
else
{
	out.print("Benvenuto "+utente.getNickname());
	out.println("<BR>Ecco la tua situazione punti: "+ utente.getPunti());
	Cliente c=new Cliente();
	c=(Cliente)session.getAttribute("utente");
	session.setAttribute("utente", c);
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>(); 
	lista=(ArrayList<Prodotto>)session.getAttribute("lista_prodotti");
	if (lista.isEmpty())
	{
		out.print("Lista vuota");
	}
	else
	{
		out.print("<table width=\"75%\" border=1 align=\"center\">");
	for(Prodotto p: lista)
	{
		out.println("<tr><td><br>Nome: "+p.getNome()+"</td>");
		out.println("<td>URL immagine "+p.getImmagine()+"</td>");
		out.println("<td><a href=\"/BaseIng2/Carrello.jsp?codice="+p.getCodice()+"&nickname="+utente.getNickname()+"\"><img src=\""+p.getImmagine()+"\" width=200 height=200>"+"</a></td>");
		out.println("<td>Prezzo "+p.getPrezzo()+"</td>");		
	}
	out.print("</table>");
	}
	out.print("<BR><BR>"+logged);
	session.setAttribute("lista_prodotti", lista);
}
%>





</body>
</html>