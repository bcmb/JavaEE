package com.insart.servlets;

import java.io.IOException;

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
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AddUserServlet.class);
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		
		User user = new User();
		UserOps uo = new UserOps(user, new UserUtilsFile());
		request.setAttribute("user", user);
		if (!password.equals(confirmPassword)) {
			request.setAttribute("confirmPassword", "true");
			request.getRequestDispatcher("regresult.jsp").forward(request, response);
		} else {
			uo.registerUser(username, password);
			user.setUsername(username);
			request.getRequestDispatcher("regresult.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}	
