package fi.softala.jee.demo.d10.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.softala.jee.demo.d10.dao.DAOPoikkeus;
import fi.softala.jee.demo.d10.dao.webuser.WebUserDAO;

/**
 * Servlet implementation class PisteServlet
 */
@WebServlet("/PisteServlet")
public class PisteServlet extends HttpServlet {
	
	private static final String NEXT_PAGE = "WEB-INF/jsp/secure/adminvahvistus.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PisteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String arvioitava = request.getParameter("arvioitava");
		String pisteet = request.getParameter("pisteet");
		
		try {
			WebUserDAO dao = new WebUserDAO();
			dao.paivitaPisteet(arvioitava, pisteet);
		} catch (DAOPoikkeus e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(NEXT_PAGE).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
