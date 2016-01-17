package com.insart.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.User;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(AuthServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		logger.error("This is message from logger");
		User user = getUser(username);
		if (user != null) {
			if (username.equals(user.getUsername()) & password.equals(user.getPassword())) {
				RequestDispatcher view = request.getRequestDispatcher("loggedin.html");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("wrongdetails.html");
				view.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private User getUser(String username) {
		File profiles = new File("D:\\users\\");
		User user = new User();
		for (File usr : profiles.listFiles()) {
			if (usr.getName().equals(username + ".txt")) {
				String line = null;
				try {
		            FileReader fileReader = new FileReader(usr.getAbsolutePath());
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            int i = 0;
		            while((line = bufferedReader.readLine()) != null) {
		            	if (i == 0) {
		            		user.setUsername(line);
		            		i++;
		            	} else {
		            		user.setPassword(line);
		            	}
		            }
		            bufferedReader.close();         
		        }
		        catch(FileNotFoundException ex) { 
		        	logger.error(ex.getMessage());
		        }
		        catch(IOException ex) {
		        	logger.error(ex.getMessage());
		        }
			}
		}
		return user;
	}

}
