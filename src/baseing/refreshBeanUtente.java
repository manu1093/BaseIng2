package baseing;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;

/**
 * Servlet implementation class refreshBeanUtente
 */
@WebServlet("/refreshBeanUtente")
public class refreshBeanUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public refreshBeanUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//necessita del paramatero indirizzo di ritorno
		HttpSession session=request.getSession();
		//System.out.println(((Cliente)session.getAttribute("utente")).getCategoria());
		Utente c=null;
		if (session.getAttribute("utente") instanceof Cliente)
		{
			c=new Cliente();
			c=(Cliente)session.getAttribute("utente");
		}
		else if (session.getAttribute("utente") instanceof Admin) 
		{
			c=new Admin();
			c=(Admin)session.getAttribute("utente");
		}
		else
		{
			c=new Impiegato();
			c=(Impiegato)session.getAttribute("utente");
		}
		
		session.removeAttribute("utente");
		Database db=new Database();
		session.setAttribute("utente", c);
		
		
		//System.out.println(c.getCategoria());
		response.sendRedirect(request.getParameter("indirizzo"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
