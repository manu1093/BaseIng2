package servlet;
import database.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.beans.*;
import database.Database;
/**
 * Servlet implementation class InserisciDati
 */
@WebServlet("/InserisciDati")
public class InserisciDati extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciDati() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String nickname=new String();
		nickname=request.getParameter("nickname2");
		Database db=Database.getInstance();
               
                if(request.getParameter("pass2").equals(request.getParameter("pass"))){                
		if(!(nickname=="" || nickname==null))//loggato
		{
		    Cliente c=new Cliente(request.getParameter("via"), request.getParameter("numero"), request.getParameter("citta"), request.getParameter("carta_credito"), request.getParameter("scadenza_carta"), request.getParameter("pin"), Integer.parseInt(request.getParameter("punti")), request.getParameter("categoria_preferita2"), request.getParameter("nickname"), request.getParameter("pass"));
		    //Cliente(String via, String numero, String citta, String cartaCredito,	String scadenza, String PIN, int punti, String categoria, String nickname, String pass)
			db.updateCliente(c,nickname);
			Refresh.refreshUtente(session, c);
			response.sendRedirect("/BaseIng2/index.jsp");
		}
		else
		{
			Cliente c=new Cliente(request.getParameter("via"), request.getParameter("numero"), request.getParameter("citta"), request.getParameter("carta_credito"), request.getParameter("scadenza_carta"), request.getParameter("pin"), 10, request.getParameter("categoria_preferita2"), request.getParameter("nickname"), request.getParameter("pass"));
		    if(db.inserisciCliente(c))
		    {
		    	session.setAttribute("utente", c);
		    }
		    Refresh.refreshUtente(session, c);
		    response.sendRedirect("/BaseIng2/index.jsp");
			
			
		}
                }
                else
                    response.sendRedirect("/BaseIng2/paginaPersonale.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
