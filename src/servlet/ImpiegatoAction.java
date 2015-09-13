/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.Data;
import database.beans.Azione;
import database.beans.Categoria;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.CheckedBean;
import security.ErroreCampoVuoto;
import security.ErrorePericoloInjection;

/**
 *
 * @author Manu
 */
@WebServlet("/ImpiegatoAction")
public class ImpiegatoAction extends HttpServlet {

    public ImpiegatoAction() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Data db=Data.getInstance();
        if(request.getParameter("action").equals("inserisci")){
        String nome=request.getParameter("nome");
	String link=request.getParameter("link");
        String messaggio=request.getParameter("messaggio");
        String immagine=request.getParameter("immagine");
        Azione e=new Azione(nome,link,immagine,messaggio);
        
	
	try {
            new CheckedBean(e);
          
		db.inserisciAction(e);
			response.sendRedirect("/BaseIng2/ImpiegatoList.jsp");
		
	} 
	catch (ErrorePericoloInjection | ErroreCampoVuoto ee) {
		response.sendRedirect("/BaseIng2/ImpiegatoList.jsp?errore=errore");
	}
        }
       if(request.getParameter("action").equals("rimuovi")){
           db.rimuoviAction(request.getParameter("azione"));
           response.sendRedirect("/BaseIng2/ImpiegatoList.jsp");
	}
    }
}
