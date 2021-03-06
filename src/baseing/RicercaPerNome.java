package baseing;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;

/**
 * Servlet implementation class RicercaPerNome
 */
@WebServlet("/RicercaPerNome")
public class RicercaPerNome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaPerNome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oggetto=request.getParameter("oggetto");
		HttpSession session=request.getSession();
		if (oggetto==null || oggetto=="")
		{
			Database db=new Database();
			ArrayList<Prodotto> lista_prodotto=new ArrayList<Prodotto>();
			lista_prodotto=db.ProdottiACaso(3);
			session.removeAttribute("lista_prodotti");
			session.setAttribute("lista_prodotti", lista_prodotto);
			response.sendRedirect("/BaseIng2/index.jsp");
		}
		else
		{
			Database db=new Database();
			ArrayList<Prodotto> lista_prodotto=new ArrayList<Prodotto>();
			lista_prodotto=db.ricercaProdotti(oggetto);
			session.removeAttribute("lista_prodotti");
			session.setAttribute("lista_prodotti", lista_prodotto);
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
