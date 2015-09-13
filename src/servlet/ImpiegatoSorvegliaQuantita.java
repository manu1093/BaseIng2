package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImpiegatoSorvegliaQuantita
 */
@WebServlet("/ImpiegatoSorvegliaQuantita")
public class ImpiegatoSorvegliaQuantita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpiegatoSorvegliaQuantita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("quantita")==null || request.getParameter("quantita").equals(""))
		{
			
		}
		else
		{
                    try{
                        Integer.valueOf(request.getParameter("quantita"));
                        response.sendRedirect("/BaseIng2/ImpiegatoList.jsp?quantita="+request.getParameter("quantita"));
                    }catch(NumberFormatException e){
                        response.sendRedirect("/BaseIng2/ImpiegatoList.jsp?quantita=10");
                    }
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
