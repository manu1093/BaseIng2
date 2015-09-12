package grafica;
import database.*;
import database.beans.*;
import java.util.ArrayList;
//PIENO DI CLOSE CONNECTION
public class Grafica {
	private ArrayList<Prodotto> lista;
	private ArrayList<Carrello> listaCarrello;
	private Prodotto prodottoOsservato;
	private Cliente utente; //per la pagina iscrizione
	private ArrayList<Prodotto> lista_premi;
	private int quantita; //per prenotato.jsp
	
	private String intestazione="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"+
"<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
"\n<title>Insert title here</title></head><body>";

	
	public Grafica()
	{
	}
	
	public String getIntestazione()
	{
		return intestazione;
	}
	public void setIntestazione(String codice)
	{
		intestazione=codice;
	}
	public String getChiusura()
	{
		return "</body>\n</html>";
	}
	
	//ricerca prodotti
	public String getFormRicercaPerNome()
	{
		String s="";
		s=s+"<FORM METHOD=GET ACTION=\"/BaseIng2/RicercaPerNome\">\nRicerca l'oggetto: "+
				"<INPUT TYPE=\"text\" NAME=\"oggetto\" SIZE=20>\n<INPUT TYPE=\"submit\" value=\"Ricerca\"></FORM>";
		return s;
	}
	
	
	
	
	
	
	
	
	
	
	public String getFormRicercaPerCategoria()
	{
		String s="";
		s=s+"<FORM METHOD=POST ACTION=\"/BaseIng2/listaCategoria\">";
		s=s+"\nSeleziona la categoria: <select name=\"categoria\">";
		int i=0;
		Database db=Database.getInstance();
		ArrayList<String> listaCategorie=new ArrayList<String>();
		listaCategorie=db.visualizzaCategorie();
		while (i<listaCategorie.size())
		{
			s=s+"\n<option value=\""+listaCategorie.get(i)+"\">"+listaCategorie.get(i)+"</option>";
			i++;
		}
		s=s+"\n</select>";
		s=s+"\n<INPUT TYPE=\"submit\" value=\"Seleziona la categoria da guardare\">";
		s=s+"\n</FORM>";
		
		
		
		//db.closeConnection();
		return s;
	}
	
	
	
	
	public void setListaProdotti(ArrayList<Prodotto> listaProdotti)
	{
		Database db=Database.getInstance();
		if (listaProdotti.isEmpty())
			lista=db.prodottiACaso(3);
		else
			lista=listaProdotti;
		//db.closeConnection();
	}
	public String getListaProdotti()//setta prima lista
	{
		String s="";
		s="<table width=\"75%\" border=1 bordercolor=\"grey\" align=\"center\">\n";
		for(Prodotto p: this.lista)
		{
			if (p.getCodice()==null)
				continue;
			else
			{
				s=s+"<tr><td><center>Nome: "+p.getNome()+"<br><br>Prezzo  € "+p.getPrezzo()+"</center></td>\n";
				//s=s+"\n<td>URL immagine "+p.getImmagine()+"</td>";
				s=s+"\n<td><center><a href=\"/BaseIng2/Carrello.jsp?codice="+p.getCodice()+"\">\n"+
							"<img src=\"" +p.getImmagine()+ "\" width=200 height=200>"+"</a></center></td>";
				s=s+"\n<td><center>Punti<br>"+p.getPunti()+"</center></td>";		
			}
		}
		s=s+"\n</table>";
		
		return s;
	}
	public String getAzione()//setta prima lista
	{
		String s="";
		s="<table width=\"75%\" border=0 bordercolor=\"grey\" align=\"center\"><tr>\n";
		for(Prodotto p: this.lista)
		{
			if (p.getCodice()==null)
				continue;
			else
			{
				s=s+"<br><td><center>"+p.getNome()+"</center>";
				s=s+"\n<center><a href=\""+p.getCodice()+"\">\n";
				s=s+"<img src=\"" +p.getImmagine()+ "\" width=200 height=200>"+"</a></center><br><br>\n";
				s=s+"\n</td>";		
			}
		}
		s=s+"\n</tr></table>";
		
		return s;
	}
	public String getImpiegatoListaProdotti()//setta prima lista
	{
		String s="";
		s="<table width=\"75%\" border=1 align=\"center\">\n";
		for(Prodotto p: this.lista)
		{
			if (p.getCodice()==null)
				continue;
			else
			{
				s=s+"<tr><td>Nome: "+p.getNome()+"</td>\n";
				s=s+"\n<td><a href=\"/BaseIng2/ImpiegatoList.jsp?codice="+p.getCodice()+"\">\n"+
							"<img src=\"" +p.getImmagine()+ "\" width=200 height=200>"+"</a></td>";
				s=s+"\n<td>Numero pezzi "+p.getPezzi()+"</td>";	
				s=s+"<td><form method=POST action=\"/BaseIng2/ImpiegatoRimuoviProdotto?codice="+p.getCodice()+"\">"
				+"<input type=\"submit\" value=\"Rimuovi dal commercio\"></form></td></tr>";
			}
		}
		s=s+"\n</table>";
		return s;
	}
	public void setListaCarrello(ArrayList<Carrello> listaCarrello)
	{
		this.listaCarrello=listaCarrello;
	}
	public String getListaCarrello()//setta prima listaCarrello
	{
		Database db=Database.getInstance();
		String s="";
		s="Il tuo carrello attuale";
                s=s+"<table><tr>";
		for(Carrello p: listaCarrello)
		{
				if (p.getProdotto()==null)
					continue;
				else
				{
					s=s+"<td>Nome: "+p.getProdotto();
					s=s+"\n<FORM METHOD=POST ACTION=\"/BaseIng2/Rimuovi?codice="+p.getProdotto()+"\">";
					s=s+"\n<br><img src=\""+db.getProdotto(p.getProdotto()).getImmagine().toString()+"\" width=200 height=200>";
					s=s+"\n<br>Prezzo "+db.getProdotto(p.getProdotto()).getPrezzo();
					s=s+"\n<br>Quantità da acquistare: "+p.getQuantita();
					s=s+"\n<br>su +"+db.getProdotto(p.getProdotto()).getPezzi()+"disponibili";
					s=s+"\n<br><INPUT TYPE=\"submit\" value=\"Rimuovi dal carrello\"></FORM></td><td>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"&nbsp;"+"</td>";
				}  
				
		}
                s=s+"</tr></table>";
		//db.closeConnection();
		return s;
	}
	
	
	
	public void setProdottoOsservato(Prodotto p)
	{
		prodottoOsservato=p;
	}
	public String getProdottoOsservato()
	{
		String s="";
		s=s+"<FORM METHOD=POST ACTION=\"/BaseIng2/Aggiungi?codice="+prodottoOsservato.getCodice()+"\">";
		s=s+"\n<table border=1>";
		s=s+"\n<tr><td>Nome: "+prodottoOsservato.getNome()+"</td>";
		s=s+"<td><img src=\""+prodottoOsservato.getImmagine()+"\" width=200 height=200></td>";
		s=s+"\n<td>Prezzo "+prodottoOsservato.getPrezzo()+"</td></tr>";
		s=s+"\n<tr><td>Quantità da acquistare <select name=\"quantita\">";
		int i=1;
		while (i<=prodottoOsservato.getPezzi())
		{
			s=s+"\n<option value="+i+">"+i+"</option>";
			i++;
		}
		s=s+"\n</select>";
		s=s+" su "+prodottoOsservato.getPezzi() +" disponibili </td></tr>";
		s=s+"\n<td><INPUT TYPE=\"submit\" value=\"aggiungi al carrello\"></td></tr>";
		s=s+"\n</table></FORM>";
		return s;
	}
	public String getProdottoOsservatoAnonimo()
	{
		String s="";
		s=s+"\n<table border=1>";
		s=s+"\n<tr><td>Nome: "+prodottoOsservato.getNome()+"</td>";
		s=s+"<td><img src=\""+prodottoOsservato.getImmagine()+"\" width=200 height=200></td>";
		s=s+"\n<td>Prezzo "+prodottoOsservato.getPrezzo()+"</td></tr>";
		s=s+"\n</table>";
		s=s+"\n<br>Quantità da acquistare "+
				"<select name=\"quantita\"> ";
		s=s+"\n<option value="+prodottoOsservato.getPezzi()+">"+prodottoOsservato.getPezzi()+"</option>\n</select>";
		s=s+" su "+prodottoOsservato.getPezzi() +" disponibili </td></tr>";
		
		return s;
	}
	
	
	
	
	
	
	public String getFormLogin() //vecchio bottone not_logged
	{
		return "<FORM METHOD=GET ACTION=\"/BaseIng2/Login\">\nNickname: <INPUT TYPE=\"text\" NAME=\"nickname\" SIZE=20><br>Password:"+
	" \n<INPUT TYPE=\"password\" NAME=\"password\" SIZE=20><br>\n<INPUT TYPE=\"submit\"></FORM>";
	}
	
	
	
	public void setCliente(Cliente u)
	{
		this.utente=u;
	}
	public String getFormIscrizione()
	{
		String s="";	
		s=s+"\nBenvenuto nella tua pagina personale, "+utente.getNickname();
		s=s+"\nModifica pure i tuoi dati e salvali<br>";
		s=s+"\n<form method=post action=\"/BaseIng2/InserisciDati?nickname2="+utente.getNickname()+"\">";//diamo la possibilità all'utente di cambiare il nickname, quindi fornisco in nickname2 il vecchio
		s=s+"\nNickname: <input type=\"text\" name=\"nickname\" value=\""+utente.getNickname()+"\" readonly><br>";
		s=s+"\nPassword: <input type=\"password\" name=\"pass\" value=\""+utente.getPass()+"\"><br>";
                s=s+"\nRipeti la Password: <input type=\"password\" name=\"pass2\" value=\"\"><br>";
		s=s+"\nVia: <input type=\"text\" name=\"via\" value=\""+utente.getVia()+"\"><br>";
		s=s+"\nNumero: <input type=\"text\" name=\"numero\" value=\""+utente.getNumero()+"\"><br>";
		s=s+"\nCittà: <input type=\"text\" name=\"citta\" value=\""+utente.getCitta()+"\"><br>";
		s=s+"\nCarta di Credito: <input type=\"text\" name=\"carta_credito\" value=\""+utente.getNumerocarta()+"\"><br>";
		s=s+"\nScadenza carta: <input type=\"text\" name=\"scadenza_carta\" value=\""+utente.getScadenza()+"\"><br>";
		s=s+"\nPIN: <input type=\"text\" name=\"pin\" maxlength=\"4\" value=\""+utente.getPIN()+"\"><br>";
		s=s+"\nPunti: <input type=\"text\" name=\"punti\" value=\""+utente.getPunti()+"\" readonly><br>";
		s=s+"\nCategoria preferita:   ";
		s=s+"\n<select name=\"categoria_preferita2\"> ";
		Database db=Database.getInstance();
		ArrayList<String> l=new ArrayList<String>();
		l=db.visualizzaCategorie();
		int i=0;
		while (i<l.size())
		{
			s=s+"\n<option value=\""+l.get(i)+"\" "; 
			if (utente.getCategoria().equals(l.get(i)))
			{ 
				s=s+"\nselected=\"selected\"";
			}
			s=s+"\n>"+l.get(i)+"</option>";
			i++;
		}
		s=s+"\n</select>";
		
		
		s=s+"\n<input type=\"submit\" value=\"Salva\">";	
		s=s+"\n</form>";
		//db.closeConnection();
		return s;
	}
	public String getFormIscrizioneAnonimo()
	{
		String s="";
		s=s+"Benvenuto, nuovo utente. <br> Completa la seguente scheda per l'iscrizione.<br> ";
		s=s+"\n<form method=post action=\"/BaseIng2/InserisciDati?nickname2=\" >";//diamo la possibilità all'utente di cambiare il nickname, quindi fornisco in nickname2 il vecchio
		s=s+"\nNickname: <input type=\"text\" name=\"nickname\" value=\"\"><br>";
		s=s+"\nPassword: <input type=\"password\" name=\"pass\" value=\"\"><br>";
		s=s+"\nVia: <input type=\"text\" name=\"via\" value=\"\"><br>";
		s=s+"\nNumero: <input type=\"text\" name=\"numero\" value=\"\"><br>";
		s=s+"\nCittà: <input type=\"text\" name=\"citta\" value=\"\"><br>";
		s=s+"\nCarta di Credito: <input type=\"text\" name=\"carta_credito\" value=\"\"><br>";
		s=s+"\nScadenza carta: <input type=\"text\" name=\"scadenza_carta\" value=\"\"><br>";
		s=s+"\nPIN (4 caratteri): <input type=\"password\" name=\"pin\" maxlength=\"4\" value=\"\"><br>";
		s=s+"\nCategoria preferita:   ";
		s=s+"\n<select name=\"categoria_preferita2\"> ";
		Database db=Database.getInstance();
		ArrayList<String> l=new ArrayList<String>();
		l=db.visualizzaCategorie();
		int i=0;
		while (i<l.size())
		{
			s=s+"\n<option value=\""+l.get(i)+"\" "; 
			s=s+">"+l.get(i)+"</option>";
			i++;
		}
		s=s+"\n</select>";
		
		
		s=s+"\n<input type=\"submit\" value=\"Salva\">";	
		s=s+"\n</form>";
		
		//db.closeConnection();
		return s;
	}
	
	
	//sezione lista premi
	public void setListaPremi(ArrayList<Prodotto> lista_premi)
	{
		this.lista_premi=lista_premi;
	}
	public String getListaPremi()
	{
		String s="<center><table><tr>";
		for(Prodotto p: lista_premi)
		{
			
				if (p.getNome()==null || p.getPuntiVincita()==0 )
					continue;
				else
				{
					
					
					if (utente.getPunti()<p.getPunti())
					{//stampa solo l'immagine
						s=s+"\n<td><center><img src=\""+p.getImmagine().toString()+"\" width=200 height=200><br>";
                                                s=s+"\n<br>Nome: "+p.getNome();
						s=s+"\n<br><br> Ti mancano ancora "+ (p.getPunti()-utente.getPunti()) +" per prenotarlo</center></td>";
					}
					else
					{       
                                                
						s=s+"\n<td><center><FORM METHOD=POST ACTION=\"/BaseIng2/Prenotato.jsp?codice="+p.getCodice()+"\">";
						s=s+"\n<img src=\""+p.getImmagine().toString()+"\" width=200 height=200>";
						s=s+"\n<br><br>Punti cadauno: "+p.getPuntiVincita();
						
						s=s+"\n<br>Quantità da acquistare <select name=\"quantita\"> ";
						int i=1;
						while ((i<=p.getPezzi()) && (i<=utente.getPunti()/p.getPuntiVincita()) )
						{
							s=s+"\n<option value="+i+">"+i+"</option>";
							i++;
						}
						s=s+"\n</select>";
						
						s=s+"\n<br><br><INPUT TYPE=\"submit\" value=\"Prenota il premio\"></FORM></center></td>";
					
						
						
					}
					
				}
				
		}
                s=s+"</tr></table></center>";
		return s;
	}
	public void setQuantita(int quantita)
	{
		this.quantita=quantita;
	}
	public String getPremioPrenotato()
	{
		String s="";
		s=s+"\nHai richiesto "+quantita+" unità del seguente prodotto: <br>";
		s=s+"\n<br><img src=\""+prodottoOsservato.getImmagine()+"\" width=200 height=200>";
		return s;
	}
	
	public String getProdottiDaAcquistare() //acquistato.jsp
	{
		String s="<font color=\"blue\"> Grazie "+utente.getNickname()+" <br>per aver acquistato </font><center><table><tr>";
		Database db=Database.getInstance();
		for(Carrello p: listaCarrello)
		{
				if (p.getProdotto()==null)
					continue;
				else
				{
					//out.println(p.getProdotto());
					s=s+"<td><center>\n<img src=\""+db.getProdotto(p.getProdotto()).getImmagine().toString()+"\" width=200 height=200>";
                                        s=s+"\n<br><br>Nome: "+p.getProdotto();
					s=s+"\n<br>Prezzo "+db.getProdotto(p.getProdotto()).getPrezzo();
					s=s+"\n<br>Quantità acquistate: "+p.getQuantita();
					s=s+"</center></td>";
					db.acquistaCarrello(db.getCliente(utente.getNickname()));
					
				}
				
		}
                s=s+"</tr></table></center>";
		//db.closeConnection();
		return s;
	}
	
	public String getInserisciProdotti(Prodotto p)
	{
		String s="";
		s="<FORM METHOD=POST ACTION=\"/BaseIng2/ImpiegatoInserisciProdotto\">";
		s=s+"\nSeleziona la categoria: <select name=\"categoria\">";				//inserire l'ultima voce come "altro" per inserire una nuova categoria? 
		int i=0;
		Database db=Database.getInstance();
		ArrayList<String> listaCategorie=new ArrayList<String>();
		listaCategorie=db.visualizzaCategorie();
		while (i<(listaCategorie.size()))
		{
			s=s+"\n<option value=\""+listaCategorie.get(i)+"\" ";
			System.out.println("grafica getInserisciPrododotti p.getCategoria"+p.getCategoria()+listaCategorie.get(i));
					if (listaCategorie.get(i).equals(p.getCategoria()))
					{
						s=s+"selected=\"selected\"";
					}
					s=s+">"+listaCategorie.get(i)+"</option>";
			i++;
		}
		s=s+"\n</select>";
		s=s+"\n<br>Codice: <input type=\"text\" name=\"codice\" value=\""+p.getCodice()+"\"";
		if (p.getCodice()!=null && p.getCodice()!="")
			s=s+" readonly";
		s=s+"><br>";
		s=s+"\nNome: <input type=\"text\" name=\"nome\" value=\""+p.getNome()+"\"><br>";
		s=s+"\nPezzi: <input type=\"text\" name=\"pezzi\" value=\""+p.getPezzi()+"\"><br>";
		s=s+"\nURL Immagine: <input type=\"text\" name=\"immagine\" value=\""+p.getImmagine().toString()+"\"><br>";
		s=s+"\nPrezzo: <input type=\"text\" name=\"prezzo\" value=\""+p.getPrezzo()+"\"><br>";
		s=s+"\nPunti: <input type=\"text\" name=\"punti\" value=\""+p.getPunti()+"\"><br>";
		s=s+"\n<br>Punti come premio: <input type=\"text\" name=\"puntivincita\" value=\""+p.getPuntiVincita()+"\"><br>";
		s=s+"\n<br>Descrizione: <textarea name=\"descrizione\" rows=\"4\" cols=\"40\">"+p.getDescrizione()+"</textarea><br>";
		s=s+"\n<br><INPUT TYPE=\"submit\" value=\"Inserisci prodotto\"></FORM>";
		//db.closeConnection();
		return s;
	}
	
	public String getFormCreaCategoria() //usato dall'impiegato
	{
		String s="";
		s=s+"Inserici una nuova categoria ";
		s=s+"<FORM METHOD=POST ACTION=\"/BaseIng2/ImpiegatoInserisciCategoria\">";
		s=s+"\n<br><input type=\"text\" name=\"categoria\" value=\"\"><br>";
		s=s+"\n<br><textarea name=\"descrizione\" rows=\"4\" cols=\"40\">";
		
		s=s+"</textarea><br>";
		s=s+"\n<br><INPUT TYPE=\"submit\" value=\"Crea nuova categoria\"></FORM>";
		return s;
	}
	
	public String getFormSogliaProdotti() //usato da impiegato
	{
		String s="";
		s=s+"<br><br>\nProdotti presenti in quantità inferiore a ";
		s=s+"<FORM METHOD=POST ACTION=\"/BaseIng2/ImpiegatoSorvegliaQuantita\">";
		s=s+"\n<input type=\"text\" name=\"quantita\" value=\"\"><br>";
		s=s+"\n<br><INPUT TYPE=\"submit\" value=\"Imposta la soglia\"></FORM>";
		return s;
	}
	
	public String getFormDisabilitaUtente() //usato da admin
	{
		Database db=Database.getInstance();
		ArrayList<String> l=new ArrayList<String>();
		String s="";
		s=s+"\nElimina un account: <br>";
		s=s+"\n<FORM METHOD=GET ACTION=\"/BaseIng2/AdminElimina\"><select name=\"user\">";
		l=db.listaUtentiAbilitati();
		int i=0;
		while (i<l.size())
		{
			String nickname=l.get(i);
			s=s+"\n<option value=\""+nickname+"\">"+nickname+"</option>";
			i++;
		}
		s=s+"\n</select><input type=\"submit\" value=\"Disabilita account\"></form>";
		//db.closeConnection();
		return s;
	}
	public String getFormAbilitaUtente() //usato da admin
	{
		Database db= Database.getInstance();
		int i=0;
		String s="";
		s=s+"\nAbilitare un account: <br>";
		s=s+"\n<FORM METHOD=GET ACTION=\"/BaseIng2/AdminAbilita\"><select name=\"user\">";
		ArrayList<String> la=new ArrayList<String>();
		la=db.listaUtentiDisabilitati();
		i=0;
		while (i<la.size())
		{
			String nickname=la.get(i);
			s=s+"\n<option value=\""+nickname+"\">"+nickname+"</option>";
			i++;
		}
		s=s+"\n</select><input type=\"submit\" value=\"Abilita account\"></form>";
		//db.closeConnection();
		return s;
	}
	
	
}


