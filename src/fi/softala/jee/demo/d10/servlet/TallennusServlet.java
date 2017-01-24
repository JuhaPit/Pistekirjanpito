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
 * Servlet implementation class TallennusServlet
 */
@WebServlet("/tallenna_suoritus")
public class TallennusServlet extends HttpServlet {
	
	public static final String SESSION_ATTR_WEBUSER = "kayttajatiedot";
	
	private static final String NEXT_PAGE = "WEB-INF/jsp/secure/vahvistus.jsp";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TallennusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String demo = request.getParameter("linkki");
		
		WebUser user = (WebUser) request.getSession().getAttribute(SESSION_ATTR_WEBUSER);
		
		String username = user.getUsername();
		
	
		try {
			WebUserDAO dao = new WebUserDAO();
			dao.lisaaDemo(demo, username);
		} catch (DAOPoikkeus e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(NEXT_PAGE).forward(request, response);
		
		
		
		
		
		
	}

}
