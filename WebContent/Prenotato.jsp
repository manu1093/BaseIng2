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
<body>



<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<%! String not_logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>";  %>
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>

<%
try{
String codice=request.getParameter("codice");
int quantita=Integer.parseInt(request.getParameter("quantita"));
Database db=new Database();
Prodotto p=new Prodotto();
p=db.getProdotto(codice);

out.println("Hai richiesto "+quantita+" unità del seguente prodotto: <br>");
out.println("<br><img src=\""+p.getImmagine()+"\" width=200 height=200>");
db.acquistaPremio(utente, codice, quantita);
db.refreshUtente(session, utente);
out.println(indietro);
}
catch(Exception e)
{
	response.sendRedirect("/BaseIng2/Premi.jsp");	
}









%>



</body>
</html>