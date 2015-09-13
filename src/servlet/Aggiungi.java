package servlet;

import database.Data;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.beans.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import security.CheckedBean;
import security.ErroreCampoVuoto;
import security.ErrorePericoloInjection;

/**
 * Servlet implementation class Aggiungi
 */
@WebServlet("/Aggiungi")
public class Aggiungi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aggiungi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		String codice=request.getParameter("codice");
		int quantita=Integer.parseInt(request.getParameter("quantita"));
		Cliente u=new Cliente();
		u=(Cliente)session.getAttribute("utente");
		/*System.out.println(request.toString());
		System.out.println(codice);
		System.out.println(u.getNickname());
		System.out.println(quantita);*/
		Carrello c=new Carrello(codice,u.getNickname(),quantita);
            try {
                new CheckedBean(c);
                Data db=Data.getInstance();
		db.aggiungiAlCarrello(c);
		response.sendRedirect("/BaseIng2/index.jsp");
            } catch (ErrorePericoloInjection|ErroreCampoVuoto ex) {
               response.sendRedirect("/BaseIng2/index.jsp");
            }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
