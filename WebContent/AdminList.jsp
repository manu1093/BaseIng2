<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<jsp:useBean id="admin" class="database.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>
<%! String iscrizioneAdmin="<FORM METHOD=POST ACTION=\"/BaseIng2/iscrizioneAdmin.jsp\"><INPUT TYPE=\"submit\" value=\"Iscrizione Personale di servizio\"></FORM>"; %>


<% 

System.out.println(admin.getNickname()+admin.getNickname());
//if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
//{
//Disabilitare utenti
out.println("Elimina un account: <br>");
out.println("<FORM METHOD=GET ACTION=\"/BaseIng2/AdminElimina\"><select name=\"user\">");
Database db=new Database();
ArrayList<String> l=new ArrayList<String>();
l=db.listaUtentiAbilitati();
int i=0;
while (i<l.size())
{
	String nickname=l.get(i);
	out.println("<option value=\""+nickname+"\">"+nickname+"</option>");
	i++;
}
out.println("</select><input type=\"submit\" value=\"Disabilita account\"></form>");


//Abilitare utenti
out.println("Abilitare un account: <br>");
out.println("<FORM METHOD=GET ACTION=\"/BaseIng2/AdminAbilita\"><select name=\"user\">");
ArrayList<String> la=new ArrayList<String>();
la=db.listaUtentiDisabilitati();
i=0;
while (i<la.size())
{
	String nickname=la.get(i);
	out.println("<option value=\""+nickname+"\">"+nickname+"</option>");
	i++;
}
out.println("</select><input type=\"submit\" value=\"Abilita account\"></form>");
out.println(iscrizioneAdmin);




out.println("<br>"+logged);
/*}
else
{
	//non loggato
}*/
%>


</body>
</html>