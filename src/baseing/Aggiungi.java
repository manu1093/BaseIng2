package baseing;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Carrello;
import database.Cliente;
import database.Database;

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
		
		HttpSession session =request.getSession();
		String codice=(String)request.getParameter("codice");
		String nickname=(String)request.getParameter("nickname");
		Cliente cl=new Cliente();
		cl=(Cliente)session.getAttribute("utente");
		session.setAttribute("utente", cl);
		int quantita=Integer.parseInt(request.getParameter("quantita"));
		System.out.println(codice);
		System.out.println(nickname);
		System.out.println(quantita);
		Carrello c=new Carrello(codice,nickname,quantita);
		Database db=new Database();
		db.aggiungiAlCarrello(c);
		response.sendRedirect("/BaseIng2/Carrello.jsp?codice="+codice+"&nickname="+nickname+"&quantita="+quantita);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
