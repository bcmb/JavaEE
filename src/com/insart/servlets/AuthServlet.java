package com.insart.servlets;

import java.io.IOException;
import java.io.Writer;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.insart.beans.UserOps;
import com.insart.model.User;
import com.insart.utils.UserUtilsFile;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(AuthServlet.class);
   
    @Inject
    User userBean;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//request.addHeader(arg0); // chnage type to text/json
		Writer w = response.getWriter();
		w.write("true");
		w.flush();
		w.close();
		User user = new User();
		UserOps uo = new UserOps(user, new UserUtilsFile());
		request.setAttribute("user", user);
		uo.authenticateUser(username, password);
		request.getRequestDispatcher("loginresult.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
