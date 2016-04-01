package com.insart.servlets.json;

import java.io.IOException;
import java.io.Writer;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.insart.beans.UserOps;
import com.insart.dao.UserManagementHibernateImpl;
import com.insart.model.AuthData;
import com.insart.model.User;
import com.insart.utils.UserUtils;
import com.insart.utils.UserUtilsFile;
import com.insart.utils.UserUtilsHibernate;
import com.insart.utils.UserUtilsJdbc;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("json/AuthServlet")
public class AuthServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(AuthServletJson.class);
   
    @Inject
    User userBean;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServletJson() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username, password);
		
		UserUtils uu = new UserUtilsHibernate();
		UserOps uo = new UserOps(user, uu);
		
		uo.authenticateUser(username, password);
		
		if (user.isAuntheticated()) {
			HttpSession session = request.getSession();
	        session.setAttribute("user", user.getUsername());
	        session.setMaxInactiveInterval(10*60);
	        uu.createSession(user.getUsername(), session.getId());
	        logger.info("user auntheticated");
		}
		
		AuthData data = new AuthData(user.getUsername(), user.isAuntheticated(), false);
		Gson gson = new Gson();
		String jsonData = gson.toJson(data);
		
		Writer w = response.getWriter();
		w.write(jsonData);
		w.flush();
		w.close();
		logger.info("Authentication json data sent");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
