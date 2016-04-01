package com.insart.servlets.json;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.insart.dao.UserManagement;
import com.insart.dao.UserManagementHibernateImpl;
import com.insart.dao.UserManagementJdbcImpl;
import com.insart.model.AuthData;
import com.insart.model.Session;
import com.insart.utils.UserUtilsHibernate;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("json/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	private final static Logger logger = Logger.getLogger(AuthenticationFilter.class);
    
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);
         
        HttpSession currentSession = req.getSession(false);
        
        UserManagement manager = new UserManagementHibernateImpl();
        Session session = new Session();
        String sessionId = null;
        
        
        if (currentSession != null) {
        	session.setSessionId(currentSession.getId());
        	sessionId = manager.getSession(session).getSessionId();
        }
        
        if (sessionId == null) {
        	res.getWriter().print("denied");
        	return;
        } else {
        	chain.doFilter(request, response);
        }
    }
 
    public void destroy() {
    }

}
