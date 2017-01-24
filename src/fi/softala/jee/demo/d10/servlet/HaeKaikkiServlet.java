package fi.softala.jee.demo.d10.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.softala.jee.demo.d10.bean.WebUser;
import fi.softala.jee.demo.d10.dao.DAOPoikkeus;
import fi.softala.jee.demo.d10.dao.webuser.WebUserDAO;

/**
 * Servlet implementation class HaeKaikkiServlet
 */
@WebServlet("/naytakaikki")
public class HaeKaikkiServlet extends HttpServlet {
	
	private static final String NEXT_PAGE = "WEB-INF/jsp/secure/naytalista.jsp";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HaeKaikkiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<WebUser> kayttajat;

		try {
			// tietokannasta henkilöt
			WebUserDAO dao = new WebUserDAO();
			kayttajat = dao.haeKaikki();
		} catch (DAOPoikkeus e) {
			throw new ServletException(e);
		}

		// requestiin talteen
		request.setAttribute("kayttajat", kayttajat);

		// jsp hoitaa muotoilun
		request.getRequestDispatcher(NEXT_PAGE).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
