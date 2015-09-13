package servlet;
import database.Data;
import database.beans.Categoria;
import java.util.ArrayList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.CheckedBean;

/**
 * Servlet implementation class ImpiegatoInserisciCategoria
 */
@WebServlet("/ImpiegatoInserisciCategoria")
public class ImpiegatoInserisciCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpiegatoInserisciCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String categoria="";
	String descrizione="";
	categoria=request.getParameter("categoria");
	descrizione=request.getParameter("descrizione");
	Data db=Data.getInstance();
	try {
            new CheckedBean(new Categoria(categoria,descrizione));
		if (db.inserisciCategoria(categoria,descrizione)==true)
			response.sendRedirect("/BaseIng2/ImpiegatoList.jsp");
		else
			response.sendRedirect("/BaseIng2/ImpiegatoList.jsp?errore=errore");
	} 
	catch (Exception e) {
		response.sendRedirect("/BaseIng2/ImpiegatoList.jsp?errore=errore");
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
