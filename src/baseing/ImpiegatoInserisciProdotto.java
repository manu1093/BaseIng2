package baseing;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import database.Prodotto;

/**
 * Servlet implementation class ImpiegatoInserisciProdotto
 */
@WebServlet("/ImpiegatoInserisciProdotto")
public class ImpiegatoInserisciProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpiegatoInserisciProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
		Prodotto p=new Prodotto();
		p.setCategoria(request.getParameter("categoria"));
		p.setCodice(request.getParameter("codice"));
		p.setNome(request.getParameter("nome"));
		p.setPezzi(Integer.parseInt(request.getParameter("pezzi")));
		p.setImmagine(new URL(request.getParameter("immagine")));
		p.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
		p.setPunti(Integer.parseInt(request.getParameter("punti")));
		p.setPuntiVincita(Integer.parseInt(request.getParameter("puntivincita")));
		p.setDescrizione(request.getParameter("descrizione"));
		Database db=new Database();
		if(db.inserisciProdotto(p)==true)
		{
			db.closeConnection();
			response.sendRedirect("/BaseIng2/ImpiegatoList.jsp");
		}
		else{
			db.closeConnection();
			response.sendRedirect("/BaseIng2/ImpiegatoList.jsp?errore=errore");
			}
		}
		catch(Exception e)
		{
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
