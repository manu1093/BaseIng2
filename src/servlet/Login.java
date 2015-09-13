package servlet;
import database.Data;
import security.ErrorePericoloInjection;
import security.CheckedBase;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.catalina.connector.Response;
import database.beans.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class database
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public int num_prod=6;
	
	
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
                String nick=(String)request.getParameter("nickname");
                String pass=(String)request.getParameter("password");
		if(request.getParameter("action").endsWith("invia")){
		Data db=Data.getInstance();
		Utente u=null;
            try {
                u = new CheckedBase().login(nick,pass);
                if(u instanceof Cliente)
		{
			u.setNickname((String) request.getParameter("nickname"));
			session.setAttribute("utente",(Cliente) u);
			Refresh.refreshUtente(session,(Cliente) u);
			db.contatoreLogin(u.getNickname());
			ArrayList<Prodotto> l=new ArrayList<Prodotto>();
			l=db.prodottiPreferiti(num_prod, (Cliente) u );
			l.addAll(db.prodottiACaso(num_prod-l.size()));
			session.setAttribute("lista_prodotti", l );
			
			response.sendRedirect("/BaseIng2/index.jsp");
			
		}
		else if (u instanceof Admin)
		{
			session.setAttribute("admin",(Admin) u);
			Refresh.refreshAdmin(session, (Admin) u);
			System.out.println("login.jsp debug admin"+u.getNickname()+u.getPass());
			response.sendRedirect("/BaseIng2/AdminList.jsp");
		}
		else if (u instanceof Impiegato)
		{
			session.setAttribute("impiegato", (Impiegato) u);
			Refresh.refreshImpiegato(session, (Impiegato) u);
			response.sendRedirect("/BaseIng2/ImpiegatoList.jsp");
		}
		else
		{
			session.setAttribute("lista_prodotti", db.prodottiACaso(3));
			this.redireziona("/BaseIng2/index.jsp", response);
			
		}
		
		
            } catch (ErrorePericoloInjection ex) {
             session.setAttribute("lista_prodotti", db.prodottiACaso(3));
			this.redireziona("/BaseIng2/index.jsp", response);
            }
                }else
                    this.redireziona("/BaseIng2/paginaPersonale.jsp", response);
		
		
		
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