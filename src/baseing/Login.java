package baseing;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.catalina.connector.Response;

import database.*;

/**
 * Servlet implementation class database
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        
    }
   
    
   private void redireziona(String url, HttpServletResponse response) throws IOException
   {
	   response.getWriter().append("<script type=\"text/javascript\">function redirectToLogin(){ document.location = '"+url+"' }");
	   response.getWriter().append("setTimeout( redirectToLogin, 300 );</script>");
    

	
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		response.setContentType("text/html");
		//response.getWriter().append("Ciao ").append((CharSequence) request.getParameter("nickname"));
		//response.getWriter().append(" con password ").append((CharSequence) request.getParameter("password"));
		Utente u= new Utente((String) request.getParameter("nickname"),(String) request.getParameter("password"));
		//System.out.println(u);
		Database db=new Database();
		u=db.login(u);
		if(u instanceof Cliente)
		{
			u.setNickname((String) request.getParameter("nickname"));
			session.setAttribute("utente",(Cliente) u);
			session.setAttribute("lista_carrello", db.visualizzaCarrello(u));
			session.setAttribute("lista_prodotti", db.ProdottiPreferiti(3, (Cliente) u ));
			response.sendRedirect("/BaseIng2/index.jsp");
			
		}
		else if (u instanceof Admin)
		{
			
			u.setNickname((String) request.getParameter("nickname"));
			session.setAttribute("utente",(Admin) u);
		}
		else if (u instanceof Impiegato)
		{
			u.setNickname((String) request.getParameter("nickname"));
			session.setAttribute("utente", (Impiegato) u);
		}
		else
		{
			this.redireziona("/BaseIng2/index.jsp", response);
			
		}
		
		
		
		
		
		/*
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			response.getWriter().append("\n\n").append(Servlet.getData(request));
			response.getWriter().append("\n ho appeso la request"); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			response.getWriter().append("\n non ho appeso la request");
			e.printStackTrace();
		}
		connessione conn=new connessione();
		response.getWriter().append(conn.getColumnQuery("SELECT * FROM cliente",(String) request.getParameter("username")));
		response.getWriter().append("<FORM METHOD=GET ACTION=index.jsp>Bottone di ritorno <INPUT TYPE=TEXT NAME=ritorna SIZE=20><P><INPUT TYPE=SUBMIT value=ritorna></FORM>");
		Acquisto a=new Acquisto();
		a.setProdotto("prod_1");
		a.setQuantita(2);
		a.setUtente("giorgy");
		session.setAttribute("acquisto", a);
		session.setAttribute("ritorna2", "bohhhhh");
		response.getWriter().append(session.getAttribute("mario").toString());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}