package com.insart.servlets.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

@WebServlet("json/GrabFilesServlet")
public class GrabFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(GrabFilesServlet.class);

	@EJB
	private FSExplorerBean fsExplorerBean; 

    public GrabFilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file = request.getParameter("file");

			if (file != null) {
				List<Node> files = fsExplorerBean.getDirFiles(file);
				/*if (files.size() == 1) {
					//response.setHeader("Content-disposition","attachment; filename=yourcustomfilename.pdf");

			         // Assume file name is retrieved from database
			         // For example D:\\file\\test.pdf

			         File my_file = new File(files.get(0).getPath());

			         // This should send the file to browser
			         OutputStream out = response.getOutputStream();
			         FileInputStream in = new FileInputStream(my_file);
			         byte[] buffer = new byte[4096];
			         int length;
			         while ((length = in.read(buffer)) > 0){
			            out.write(buffer, 0, length);
			         }
			         in.close();
			         out.flush();
			         } else {*/
				Gson gson = new Gson();
				String jsonData = gson.toJson(files);
				
				Writer w = response.getWriter();
				w.write(jsonData);
				w.flush();
				w.close();
			        // }
				
			} else {
				List<Node> files = fsExplorerBean.getDirFiles();
				
				Gson gson = new Gson();
				String jsonData = gson.toJson(files);
				
				Writer w = response.getWriter();
				w.write(jsonData);
				w.flush();
				w.close();

			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
