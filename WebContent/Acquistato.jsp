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
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>

<%
String nickname=utente.getNickname();
Database db=new Database();

//Istanzio la lista carrello
ArrayList<Carrello> lista_carrello= new ArrayList<Carrello>();
lista_carrello=db.visualizzaCarrello(utente);


if (nickname==null || nickname=="" || lista_carrello.isEmpty())
{
	response.sendRedirect("/BaseIng2/Logout");
}
else
{
	if (utente.getCartaCredito()==null || utente.getPIN()==null)
		response.sendRedirect("BaseIng2/paginaPersonale.jsp");
	else
	{
		out.print("Grazie "+nickname+" <br>per aver acquistato ");
		
		for(Carrello p: lista_carrello)
		{
				if (p.getProdotto()==null)
					continue;
				else
				{
					out.println("<br>Nome: "+p.getProdotto());
					//out.println(p.getProdotto());
					out.println("<img src=\""+db.getProdotto(p.getProdotto()).getImmagine().toString()+"\" width=200 height=200>");
					out.println("Prezzo "+db.getProdotto(p.getProdotto()).getPrezzo());
					out.print("Quantità acquistate: "+p.getQuantita());
					
					db.acquistaCarrello(db.getCliente(nickname));
					
				}
				
		}
		db.refreshUtente(session, utente);
		out.println("<br><br>"+indietro);
	
	
	}
	
	
	
	
}

%>
</body>
</html>