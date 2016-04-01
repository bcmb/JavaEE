package com.insart.servlets.json;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("json/FileUploaderServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*1000,    // 1000 MB 
maxFileSize=1024*1024*500,          // 500 MB
maxRequestSize=1024*1024*100)      // 100 MB
public class FileUploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileUploaderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String applicationPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        //String uploadFilePath = applicationPath + File.separator;// + UPLOAD_DIR;
		      String uploadFilePath = "D:\\users";
	          
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File("D:\\users");
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
	        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
	         
	        String fileName = null;
	        //Get all the parts from request and write it to the file on server
	        for (Part part : request.getParts()) {
	            fileName = "file";
	            part.write(uploadFilePath + File.separator + fileName);
	        }
	  
	        request.setAttribute("message", fileName + " File uploaded successfully!");
	        getServletContext().getRequestDispatcher("/response.jsp").forward(
	                request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
