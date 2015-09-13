package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.beans.*;
import database.Data;

/**
 * Servlet implementation class Aggiungi
 */
@WebServlet("/Rimuovi")
public class Rimuovi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rimuovi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session =request.getSession();
		String codice=(String)request.getParameter("codice");
		System.out.println(codice);
		Cliente u=new Cliente();
		u=(Cliente)session.getAttribute("utente");
		System.out.println(u.getNickname());
		Carrello c=new Carrello(codice,u.getNickname(),1);
		Data db=Data.getInstance();
		db.rimuoviDalCarrello(c);
		response.sendRedirect("/BaseIng2/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
