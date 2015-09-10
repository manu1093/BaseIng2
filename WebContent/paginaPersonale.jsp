<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
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
if (utente.getNickname().isEmpty() || utente.getNickname()=="") //non loggato
{
	out.println("Benvenuto, nuovo utente. <br> Completa la seguente scheda per l'iscrizione.<br> ");
	out.println("<form method=post action=\"/BaseIng2/InserisciDati?nickname2=\" >");//diamo la possibilità all'utente di cambiare il nickname, quindi fornisco in nickname2 il vecchio
	out.println("Nickname: <input type=\"text\" name=\"nickname\" value=\"\"><br>");
	out.println("Password: <input type=\"password\" name=\"pass\" value=\"\"><br>");
	out.println("Via: <input type=\"text\" name=\"via\" value=\"\"><br>");
	out.println("Numero: <input type=\"text\" name=\"numero\" value=\"\"><br>");
	out.println("Città: <input type=\"text\" name=\"citta\" value=\"\"><br>");
	out.println("Carta di Credito: <input type=\"text\" name=\"carta_credito\" value=\"\"><br>");
	out.println("Scadenza carta: <input type=\"text\" name=\"scadenza_carta\" value=\"\"><br>");
	out.println("PIN (4 caratteri): <input type=\"password\" name=\"pin\" maxlength=\"4\" value=\"\"><br>");
	//out.println("Punti: <input type=\"text\" name=\"punti\" value=\"\" readonly><br>");
	out.println("Categoria preferita:   ");
	out.print("<select name=\"categoria_preferita2\"> ");
	Database db=new Database();
	ArrayList<String> l=new ArrayList<String>();
	l=db.visualizzaCategorie();
	int i=0;
	while (i<l.size())
	{
		out.println("<option value=\""+l.get(i)+"\" "); 
		out.print(">"+l.get(i)+"</option>");
		i++;
	}
	out.print("</select>");
	
	
	out.println("<input type=\"submit\" value=\"Salva\">");	
	out.println("</form>");
	
	out.println("<br><br>"+indietro);
	out.println("<br><br>"+logged);
	
	
	
}
else  //loggato
{
	out.println("Benvenuto nella tua pagina personale, "+utente.getNickname());
	out.println("Modifica pure i tuoi dati e salvali<br>");
	out.println("<form method=post action=\"/BaseIng2/InserisciDati?nickname2="+utente.getNickname()+"\">");//diamo la possibilità all'utente di cambiare il nickname, quindi fornisco in nickname2 il vecchio
	out.println("Nickname: <input type=\"text\" name=\"nickname\" value=\""+utente.getNickname()+"\"><br>");
	out.println("Password: <input type=\"password\" name=\"pass\" value=\""+utente.getPass()+"\"><br>");
	out.println("Via: <input type=\"text\" name=\"via\" value=\""+utente.getVia()+"\"><br>");
	out.println("Numero: <input type=\"text\" name=\"numero\" value=\""+utente.getNumero()+"\"><br>");
	out.println("Città: <input type=\"text\" name=\"citta\" value=\""+utente.getCitta()+"\"><br>");
	out.println("Carta di Credito: <input type=\"text\" name=\"carta_credito\" value=\""+utente.getNumerocarta()+"\"><br>");
	out.println("Scadenza carta: <input type=\"text\" name=\"scadenza_carta\" value=\""+utente.getScadenza()+"\"><br>");
	out.println("PIN: <input type=\"text\" name=\"pin\" maxlength=\"4\" value=\""+utente.getPIN()+"\"><br>");
	out.println("Punti: <input type=\"text\" name=\"punti\" value=\""+utente.getPunti()+"\" readonly><br>");
	out.println("Categoria preferita:   ");
	out.print("<select name=\"categoria_preferita2\"> ");
	Database db=new Database();
	ArrayList<String> l=new ArrayList<String>();
	l=db.visualizzaCategorie();
	int i=0;
	while (i<l.size())
	{
		out.println("<option value=\""+l.get(i)+"\" "); 
		if (utente.getCategoria().equals(l.get(i)))
		{ 
			out.print("selected=\"selected\"");
		}
		out.print(">"+l.get(i)+"</option>");
		i++;
	}
	out.print("</select>");
	
	
	out.println("<input type=\"submit\" value=\"Salva\">");	
	out.println("</form>");
	
	out.println("<br><br>"+indietro);
	out.println("<br><br>"+logged);

	
	
}








%>
</body>
</html>