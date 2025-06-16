package jp.co.shiftw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class ConnectionUtil {
	public static Connection getConnection(String lookupString)
			throws SQLException, ServletException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(lookupString);

			return ds.getConnection();
		} catch (NamingException e) {
			throw new ServletException(e);
		}

	}
	
	public static Connection getConnectionForJUnit() throws SQLException {
		
		String url = "jdbc:postgresql://localhost:5432/ecsite";
		String user = "ecsite";
		String pass = "ecsite";
	
		return  DriverManager.getConnection(url, user, pass);
	}

}
