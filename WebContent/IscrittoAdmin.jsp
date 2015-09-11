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
<jsp:useBean id="impiegato" class="database.Impiegato" scope="session"/>
<jsp:setProperty name="impiegato" property="*"/>

<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/AdminList.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>
<%
if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
{
	String nickname=request.getParameter("nickname");
	String password=request.getParameter("pass");
	String tipo=request.getParameter("tipo");
	System.out.println(nickname+password+tipo);
	if ((nickname==null) || (password==null))
		response.sendRedirect("/BaseIng2/iscrizioneAdmin.jsp");
	else
	{
		Database db=new Database();
		if (db.inserisciUtente(nickname, password, tipo))
			{
				out.println("<br>"+indietro);
				db.closeConnection();
			}
		else
		{
			response.sendRedirect("/BaseIng2/iscrizioneAdmin.jsp");
			db.closeConnection();
		}
	}

}
else
{
	response.sendRedirect("/BaseIng2/index.jsp");
}


%>



</body>
</html>