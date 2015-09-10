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
<jsp:useBean id="admin" class="database.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>


<%! String not_logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Login\">Nickname: <INPUT TYPE=\"text\" NAME=\"nickname\" SIZE=20><br>Password: <INPUT TYPE=\"password\" NAME=\"password\" SIZE=20><P><INPUT TYPE=\"submit\"></FORM>"; %>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<%! String carrello="<FORM METHOD=POST ACTION=\"/BaseIng2/Carrello.jsp?codice=\"><INPUT TYPE=\"submit\" value=\"Carrello\"></FORM>"; %>
<%! String lista_premi="<FORM METHOD=POST ACTION=\"/BaseIng2/Premi.jsp\"><INPUT TYPE=\"submit\" value=\"Lista premi\"></FORM>"; %>
<%! String paginaPersonale="<FORM METHOD=POST ACTION=\"/BaseIng2/paginaPersonale.jsp\"><INPUT TYPE=\"submit\" value=\"Pagina Personale\"></FORM>"; %>
<%! String paginaIscrizione="<FORM METHOD=POST ACTION=\"/BaseIng2/paginaPersonale.jsp\"><INPUT TYPE=\"submit\" value=\"Pagina Iscrizione\"></FORM>"; %>


<%
if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
{
	response.sendRedirect("/BaseIng2/AdminList.jsp");	
}
if (utente.getNickname().isEmpty() || utente.getNickname()=="") //non loggato
{
	ArrayList<Prodotto> lista= new ArrayList<Prodotto>();
	Database db=new Database();
	out.println("Benvenuto nuovo utente"+not_logged);
	lista=db.ProdottiACaso(3);
	session.setAttribute("lista_prodotti", lista);

	out.print("<table width=\"75%\" border=1 align=\"center\">");
	for(Prodotto p: lista)
	{
		out.println("<tr><td><br>Nome: "+p.getNome()+"</td>");
		out.println("<td>URL immagine "+p.getImmagine()+"</td>");
		out.println("<td><a href=\"/BaseIng2/Carrello.jsp?codice="+p.getCodice()+"\"><img src=\"" +p.getImmagine()+ "\" width=200 height=200>"+"</a></td>");
		out.println("<td>Prezzo "+p.getPrezzo()+"</td>");		
	}
	out.print("</table>");	
out.println("<br><br>"+paginaIscrizione);
	
	
}
else															//loggato
{
	Database db=new Database();
	/*response.sendRedirect("/BaseIng2/refreshBeanUtente?indirizzo=/BaseIng2/index.jsp"); //aggiorna il bean
	Database db=new Database();
	Utente u= new Utente(utente.getNickname(),utente.getPass());
	session.removeAttribute("utente");
	session.setAttribute("utente", (Cliente) db.login(u));
	*/
	db.refreshUtente(session, utente);
	
	
	
	out.print("Benvenuto "+utente.getNickname());
	out.println("<BR>Ecco la tua situazione punti: "+ utente.getPunti());
	Cliente c=new Cliente();
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
			out.println("<tr><td>Nome: "+p.getNome()+"</td>");
			out.println("<td>URL immagine "+p.getImmagine()+"</td>");
			out.println("<td><a href=\"/BaseIng2/Carrello.jsp?codice="+p.getCodice()+"\"><img src=\""+p.getImmagine()+"\" width=200 height=200>"+"</a></td>");
			out.println("<td>Prezzo "+p.getPrezzo()+"</td></tr>");		
		}
		out.print("</table>");
	}
	
	out.print("<BR><BR>"+carrello+"<br><br>");
	out.print("<BR><BR>"+paginaPersonale+"<br><br>");
	out.print("<BR><BR>"+lista_premi+"<br><br>"+logged);
}
%>





</body>
</html>