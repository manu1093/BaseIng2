package servlet;

import grafica.plot.GenericGraph;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import grafica.*;
import grafica.plot.Graph;
import grafica.plot.InvokerGraph;
import java.util.ArrayList;
/**
 * Servlet implementation class statistica1
 */
@WebServlet("/DrawGraphServlet")
public class DrawGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawGraph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n=Integer.parseInt(request.getParameter("numero"));
		HttpSession session=request.getSession();
               // System.out.println("siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		ArrayList <Graph> p=(ArrayList <Graph>)session.getAttribute("gl");
		if (p==null)
                    System.out.print("");
		else{
			response.setContentType("image/png");
		
			           //      System.out.println("QUI");
			
			new InvokerGraph(400, 400, p.get(n)).graph(response.getOutputStream());
			
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
