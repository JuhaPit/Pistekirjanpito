package fi.softala.jee.demo.d10.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.softala.jee.demo.d10.bean.WebUser;
import fi.softala.jee.demo.d10.dao.DAOPoikkeus;
import fi.softala.jee.demo.d10.dao.webuser.WebUserDAO;

/**
 * Servlet implementation class TarkasteleServlet
 */
@WebServlet("/tarkastele")
public class TarkasteleServlet extends HttpServlet {

	public static final String SESSION_ATTR_WEBUSER = "kayttajatiedot";
	private static final String NEXT_PAGE = "WEB-INF/jsp/secure/nayta.jsp";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TarkasteleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String demo = "";
		String pisteet = "";

		try {
			WebUserDAO dao = new WebUserDAO();
			WebUser user = (WebUser) request.getSession().getAttribute(
					SESSION_ATTR_WEBUSER);
			String username = user.getUsername();
			WebUser kayttaja = dao.hae(username);

			demo = kayttaja.getDemo();
			pisteet = kayttaja.getPisteet();

		} catch (DAOPoikkeus e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// requestiin talteen
		request.setAttribute("demo", demo);
		request.setAttribute("pisteet", pisteet);

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
