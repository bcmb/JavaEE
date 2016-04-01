package com.insart.servlets.json;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.insart.beans.UserOps;
import com.insart.model.AuthData;
import com.insart.model.RegisterData;
import com.insart.model.User;
import com.insart.utils.UserUtils;
import com.insart.utils.UserUtilsHibernate;
import com.insart.utils.UserUtilsJdbc;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("json/AddUserServlet")
public class AddUserServletJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AddUserServletJson.class);
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServletJson() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		
		User user = new User(username, password);
		UserOps uo = new UserOps(user, new UserUtilsHibernate());
		
		uo.registerUser(username, password, confirmPassword);
		RegisterData data = new RegisterData(user.getUsername(), user.isRegistered(), user.isConfirmPaswordWrong());
		Gson gson = new Gson();
		String jsonData = gson.toJson(data);
		
		Writer w = response.getWriter();
		w.write(jsonData);
		w.flush();
		w.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}	
