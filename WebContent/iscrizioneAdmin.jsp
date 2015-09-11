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
<jsp:useBean id="admin" class="database.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/AdminList.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
Inserisci un nuovo utente personale interno e scegli il ruolo.
<%
if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
{

	out.println("<form method=GET action=\"/BaseIng2/IscrittoAdmin.jsp\">");
	out.println("Nickname: <input type=\"text\" name=\"nickname\"><br>");
	out.println("Password: <input type=\"password\" name=\"pass\" ><br>");
	
	out.println("Ruolo:   ");
	out.print("<select name=\"tipo\"> ");
	Database db=new Database();
	ArrayList<String> l=new ArrayList<String>();
	out.println("<option value=\"amministratore\">amministratore</option>"); 
	out.println("<option value=\"impiegato\">impiegato</option>"); 
	out.print("</select>");
	
	
	out.println("<input type=\"submit\" value=\"Salva\">");	
	out.println("</form>"); 
	out.println("<br>"+indietro);
	out.println("<br>"+logged);
	db.closeConnection();
}
else
{
	
}

%>
</body>
</html>