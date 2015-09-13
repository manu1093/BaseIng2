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
import security.CheckedBean;
import security.ErroreCampoVuoto;
import security.ErrorePericoloInjection;
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
		Data db=Data.getInstance();
               try{
                if(request.getParameter("pass2").equals(request.getParameter("pass"))){                
		if(!( nickname==null))//loggato
		{
		    Cliente c=new Cliente(request.getParameter("via"), request.getParameter("numero"), request.getParameter("citta"), request.getParameter("carta_credito"), request.getParameter("scadenza_carta"), request.getParameter("pin"), Integer.parseInt(request.getParameter("punti")), request.getParameter("categoria_preferita2"), request.getParameter("nickname"), request.getParameter("pass"));
                        new CheckedBean(c);
			db.updateCliente(c,nickname);
			Refresh.refreshUtente(session, c);
			response.sendRedirect("/BaseIng2/index.jsp");
		}
		else
		{
                    
			Cliente c=new Cliente(request.getParameter("via"), request.getParameter("numero"), request.getParameter("citta"), request.getParameter("carta_credito"), request.getParameter("scadenza_carta"), request.getParameter("pin"), 10, request.getParameter("categoria_preferita2"), request.getParameter("nickname"), request.getParameter("pass"));
                       if(c.getCartaCredito().equals("")||c.getNickname().equals("")||c.getPass().equals("")){
                            new CheckedBean(c);

                            if(db.inserisciCliente(c))
                        {
                            session.setAttribute("utente", c);
                        }
                        Refresh.refreshUtente(session, c);
                       }
		    response.sendRedirect("/BaseIng2/index.jsp");
                     
			
		}
                }
                else
                    response.sendRedirect("/BaseIng2/paginaPersonale.jsp");
               }catch(ErrorePericoloInjection | NullPointerException | ErroreCampoVuoto e){
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
