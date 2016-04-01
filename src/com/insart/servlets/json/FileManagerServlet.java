package com.insart.servlets.json;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.insart.beans.FSExplorerBean;
import com.insart.beans.Node;

@WebServlet("json/FileManagerServlet")
public class FileManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(FileManagerServlet.class);

	@EJB
	private FSExplorerBean fsExplorerBean;
	
    public FileManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("name");
		
		List<Node> files = fsExplorerBean.createFolder(fileName);
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(files);
		
		Writer w = response.getWriter();
		w.write(jsonData);
		w.flush();
		w.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
