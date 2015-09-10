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
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>

<%
Database db=new Database();
ArrayList<Prodotto> lista_premi= new ArrayList<Prodotto>();
lista_premi=db.visualizzaListaPremi();
out.println("Ecco la lista premi");


for(Prodotto p: lista_premi)
{
		if (p.getNome()==null)
			continue;
		else
		{
			
			out.println("<br>Nome: "+p.getNome());
			if (utente.getPunti()<p.getPunti())
			{//stampa solo l'immagine
				out.println("<img src=\""+p.getImmagine().toString()+"\" width=200 height=200>");
				out.println("<br> Ti mancano ancora "+ (p.getPunti()-utente.getPunti()) +" per prenotarlo");
			}
			else
			{
				out.print("<FORM METHOD=POST ACTION=\"/BaseIng2/Prenotato.jsp?codice="+p.getCodice()+"\">");
				out.println("<img src=\""+p.getImmagine().toString()+"\" width=200 height=200>");
				out.println("<br>Punti cadauno: "+p.getPuntiVincita());
				
				out.print("<br>Quantità da acquistare <select name=\"quantita\"> ");
				int i=1;
				while ((i<=p.getPezzi()) && (i<=utente.getPunti()/p.getPuntiVincita()) )
				{
					out.println("<option value="+i+">"+i+"</option>");
					i++;
				}
				out.print("</select>");
				
				out.print("<br><INPUT TYPE=\"submit\" value=\"Prenota il premio\"></FORM>");
			
				
				
			}
			
		}
		
}

out.println(indietro);








%>

</html>