
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="grafica.*"%>
<%@page import="database.beans.*"%>
<%@page import="grafica.plot.*"%>
<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<jsp:useBean id="admin" class="database.beans.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>
<jsp:useBean id="grafica" class="grafica.Grafica" scope="session"/>
<jsp:setProperty name="grafica" property="*"/>
<jsp:useBean id="pulsante" class="grafica.Pulsante" scope="session"/>
<jsp:setProperty name="pulsante" property="*"/>
<html>
    <head>
        <title>Admin</title>
    </head>
    <font face="Calibri" size="6" color="blue">
<% 
Data db= Data.getInstance();
out.println(grafica.getIntestazione());

if (!(admin.getNickname().isEmpty() || admin.getNickname()==""))
{

out.println(grafica.getFormDisabilitaUtente());

out.println(grafica.getFormAbilitaUtente());


pulsante.setPagina("iscrizioneAdmin.jsp");//bottone Iscrizione personale
pulsante.setAttributi("");
pulsante.setLabel("Iscrizione personale di servizio");
pulsante.setMetodo("GET");
out.println(pulsante.getBottone());



String s="";
s=s+"<h3 align=center> Statistiche sui prodotti  </h3>";
out.println(s);
ArrayList <Graph>graphList=new ArrayList <Graph>();
session.setAttribute("gl", graphList);
Graph plot1=new DrawPieChart(db.getNumeroProdottiVendutiXCategoria(),"Categorie più gettonate dell'anno");
graphList.add(plot1);
out.println("<img src=\"/BaseIng2/DrawGraphServlet?numero=0\">");
 

Graph plot2=new DrawPieChart(db.getSoldiSpesiClienti(),"Migliori Clienti dell'anno");
graphList.add(plot2);
out.println("<img src=\"/BaseIng2/DrawGraphServlet?numero=1\">");

Graph plot3=new DrawHistogram(db.getProdottiPiuComprati(),"Prodotti più comprati");
graphList.add(plot3);
out.println("<img src=\"/BaseIng2/DrawGraphServlet?numero=2\">");

Graph plot4=new DrawHistogram(db.getOraPiuLoggata(),"Ora di maggiore accesso");
graphList.add(plot4);
out.println("<img src=\"/BaseIng2/DrawGraphServlet?numero=3\">");




out.println("<br>"+logged);
}
else
{
	//non loggato
}


out.println(grafica.getChiusura());
%>
</font>
</html>