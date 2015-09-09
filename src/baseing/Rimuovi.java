package baseing;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Carrello;
import database.Database;

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
		String codice=(String)request.getAttribute("codice");
		String nickname=(String)request.getAttribute("nickname");
		System.out.println(codice);
		System.out.println(nickname);
		Carrello c=new Carrello(codice,nickname,1);
		Database db=new Database();
		db.rimuoviDalCarrello(c);
		response.sendRedirect("/BaseIng2/Carrello.jsp?codice=\""+codice+"\"&nickname=\""+nickname+"\"&quantita=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
