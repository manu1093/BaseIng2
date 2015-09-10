package baseing;
import java.io.IOException;
import java.util.ArrayList;

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
	public int num_prod=3;
	
	
    public Login() {
        super();
        
    }
   
    
   private void redireziona(String url, HttpServletResponse response) throws IOException
   {
	   response.sendRedirect(url);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		response.setContentType("text/html");
		Utente u= new Utente((String) request.getParameter("nickname"),(String) request.getParameter("password"));
		Database db=new Database();
		u=db.login(u);
		if(u instanceof Cliente)
		{
			u.setNickname((String) request.getParameter("nickname"));
			session.setAttribute("utente",(Cliente) u);
			db.refreshUtente(session,(Cliente) u);
			ArrayList<Prodotto> l=new ArrayList<Prodotto>();
			l=db.ProdottiPreferiti(num_prod, (Cliente) u );
			l.addAll(db.ProdottiACaso(num_prod-l.size()));
			session.setAttribute("lista_prodotti", l );
			
			response.sendRedirect("/BaseIng2/index.jsp");
			
		}
		else if (u instanceof Admin)
		{
			session.setAttribute("utente",(Admin) u);
			db.refreshAdmin(session, (Admin) u);
			System.out.println("login.jsp debug admin"+u.getNickname()+u.getPass());
			response.sendRedirect("/BaseIng2/AdminList.jsp");
		}
		else if (u instanceof Impiegato)
		{
			session.setAttribute("utente", (Impiegato) u);
			db.refreshImpiegato(session, (Impiegato) u);
			System.out.println("login.jsp debug impiegato"+u.getNickname()+u.getPass());
		}
		else
		{
			session.setAttribute("lista_prodotti", db.ProdottiACaso(3));
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