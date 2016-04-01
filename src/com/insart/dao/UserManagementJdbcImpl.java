package com.insart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.insart.model.Session;
import com.insart.model.User;

public class UserManagementJdbcImpl implements UserManagement {
	private final static Logger logger = Logger.getLogger(UserManagementJdbcImpl.class);
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/files";
	static final String USER = "root";
	static final String PASS = "root";

	@Override
	public User getUser(String userName) {
		User user = new User();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getDbConnection();
			String sql = "SELECT * FROM files.users WHERE username = ?;";
			stmt = conn.prepareStatement(sql);
			logger.error("username from gerUser is =   " + userName);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
		         user.setUsername(rs.getString("username"));
		         user.setPassword(rs.getString("password"));
		    }
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return user;
	}


	@Override
	public boolean addUser(String username, String password) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getDbConnection();
		    String sql = "INSERT INTO files.users (username, password) VALUES (?,?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			result = true;
		}
		return result;
	}

	@Override
	public boolean createSession(String userName, String sessionId) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getDbConnection();
			String sql = "INSERT INTO files.sessions (user_id, session_id) VALUES (?,?) "
					+ "ON DUPLICATE KEY UPDATE user_id=?, session_id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, sessionId);
			stmt.setString(3, userName);
			stmt.setString(4, sessionId);
			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			result = true;
		}
		return result;
	}

	@Override
	public Session getSession(Session s) {
		Session session = new Session();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getDbConnection();
			String sql = "SELECT * FROM files.sessions WHERE session_id=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getSessionId());
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				session.setSessionId(rs.getString("session_id"));
		      }
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return session;
	}

	@Override
	public boolean dropSession(Session s) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getDbConnection();
		    String sql = "DELETE FROM files.sessions WHERE session_id=?;";
		    stmt = conn.prepareStatement(sql);
		    stmt.setString(1, s.getSessionId());
			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
			result = true;
		}
		return result;
	}
	
	private Connection getDbConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER); 
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

}
