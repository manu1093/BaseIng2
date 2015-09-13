package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.beans.*;
import database.Data;

//TOLTO CLOSE CONNECTION
/**
 * Servlet implementation class ListaCategoria
 */
@WebServlet("/listaCategoria")
public class ListaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoria=request.getParameter("categoria");
		HttpSession session=request.getSession();
		if (categoria==null || categoria=="")
		{
			session.removeAttribute("lista_prodotti");
			response.sendRedirect("/BaseIng2/index.jsp");
		}
		else
		{
			Data db=Data.getInstance();
			ArrayList<Prodotto> lista_prodotto=new ArrayList<Prodotto>();
			lista_prodotto=db.prodottiPreferiti(3, categoria);
			session.removeAttribute("lista_prodotti");
			session.setAttribute("lista_prodotti", lista_prodotto);
			//db.closeConnection();
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
