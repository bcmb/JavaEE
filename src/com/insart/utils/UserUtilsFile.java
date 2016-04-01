package com.insart.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.insart.model.User;

public class UserUtilsFile implements UserUtils {
	private final static Logger logger = Logger.getLogger(UserUtilsFile.class);
	
	@Override
	@SuppressWarnings("finally")
	public boolean addUser(String username, String password) {
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
	
	@Override
	public User getUser(String username) {
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
		        	logger.error(ex.getMessage(), ex);
		        }
		        catch(IOException ex) {
		        	logger.error(ex.getMessage(), ex);
		        }
			}
		}
		return user;
	}

	@Override
	public boolean createSession(String userName, String sessionId) {
		return false;
	}

	@Override
	public boolean dropSession(String userName) {
		return false;
	}
	
}
