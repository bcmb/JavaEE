package com.insart.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.User;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		PrintWriter out = response.getWriter();
		if (!password.equals(confirmPassword)) {
			RequestDispatcher view = request.getRequestDispatcher("confirmpassword.html");
			view.forward(request, response);
		} else {
			if (addUser(username, password)) {
				RequestDispatcher view = request.getRequestDispatcher("useradded.html");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("somethingwrong.html");
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
	
	@SuppressWarnings("finally")
	private boolean addUser(String username, String password) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("D:\\users\\" + username + ".txt", "UTF-8");
			writer.println(username);
			writer.println(password);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			logger.error(e.getMessage());;
			return false;
		} finally {
			writer.close();
			return true;
		}
	
	}
}	
