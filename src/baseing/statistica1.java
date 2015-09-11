package baseing;

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
import database.*;
/**
 * Servlet implementation class statistica1
 */
@WebServlet("/statistica1")
public class statistica1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public statistica1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nameplot=request.getParameter("plot");
		HttpSession session=request.getSession();
		Plot p=(Plot)session.getAttribute(nameplot);
		if (p==null)
		{
			}
		else
		{
			response.setContentType("image/png");
		
			//OutputStream outputStream = response.getOutputStream();
			
			
			JFreeChart chart = p.getTorta();
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, p.getWidth(), p.getHeight());
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
