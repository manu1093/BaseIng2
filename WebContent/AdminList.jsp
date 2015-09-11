
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="grafica.*"%>

<%! String indietro="<FORM METHOD=GET ACTION=\"/BaseIng2/index.jsp\"><INPUT TYPE=\"submit\" value=\"Indietro\"></FORM>"; %>
<%! String logged="<FORM METHOD=GET ACTION=\"/BaseIng2/Logout\"><INPUT TYPE=\"submit\" value=\"Logout\"></FORM>"; %>
<jsp:useBean id="admin" class="database.Admin" scope="session"/>
<jsp:setProperty name="admin" property="*"/>
<jsp:useBean id="grafica" class="grafica.Grafica" scope="session"/>
<jsp:setProperty name="grafica" property="*"/>
<jsp:useBean id="pulsante" class="grafica.pulsante" scope="session"/>
<jsp:setProperty name="pulsante" property="*"/>
<jsp:useBean id="plot1" class="grafica.Plot" scope="session"/>
<jsp:setProperty name="plot1" property="*"/>
<jsp:useBean id="plot2" class="grafica.Plot" scope="session"/>
<jsp:setProperty name="plot2" property="*"/>
<jsp:useBean id="plot3" class="grafica.Plot" scope="session"/>
<jsp:setProperty name="plot3" property="*"/>
<jsp:useBean id="plot4" class="grafica.Plot" scope="session"/>
<jsp:setProperty name="plot4" property="*"/>
<% 
Database db=new Database();
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


plot1.setHeight(400);	//setto e plotto il grafico statistica categoria più venduta
plot1.setWidth(400);
plot1.setTitolo("Categorie più gettonate dell'anno");
plot1.setHashMap(db.getNumeroProdottiVendutiXCategoria());
plot1.setTorta();
out.println("<img src=\"/BaseIng2/statistica1?plot=plot1\">");



plot2.setHeight(400);	//setto e plotto il grafico statistica miglior utente 
plot2.setWidth(400);
plot2.setTitolo("Migliori Clienti dell'anno");
plot2.setHashMap(db.getSoldiSpesiClienti());
plot2.setIsto();
out.println("<img src=\"/BaseIng2/statistica2?plot=plot2\">");

plot3.setHeight(400);	//setto e plotto il grafico statistica miglior utente 
plot3.setWidth(400);
plot3.setTitolo("Prodotti più comprati");
plot3.setHashMap(db.getProdottiPiuComprati());
plot3.setIsto();
out.println("<img src=\"/BaseIng2/statistica2?plot=plot3\">");

plot4.setHeight(400);	//setto e plotto il grafico statistica miglior utente 
plot4.setWidth(400);
plot4.setTitolo("Ora di maggiore accesso");
plot4.setHashMap(db.getOraPiuLoggata());
plot4.setIsto();
out.println("<img src=\"/BaseIng2/statistica2?plot=plot4\">");




out.println("<br>"+logged);
}
else
{
	//non loggato
}
db.closeConnection();

out.println(grafica.getChiusura());
%>

