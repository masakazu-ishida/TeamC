package jp.co.sars.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;

public class ConnectionUtil {

	public static Connection getConnection(String lookupString)
			throws SQLException, ServletException {
		Connection connection = null;
		try {

			boolean assertsEnabled = false;

			// -ea が有効な場合のみ、ここの代入文（assertsEnabled = true）が実行される
			// （assertの条件式自体は常に true になるようにして、エラーを防ぎます）
			assert assertsEnabled = true;

			//JUnit経由の起動かどうかをチェック
			if (assertsEnabled == true) {
				String url = "jdbc:postgresql://localhost:5432/ecsite";
				String username = "ecsite";
				String password = "ecsite";
				connection = DriverManager.getConnection(url, username, password);

			} else {
				//JUnit経由でなければ、Webコンテナ上で起動している
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup(lookupString);
				connection = ds.getConnection();

			}
			return connection;
		} catch (NamingException e) {
			fail(e.getMessage());
			throw new ServletException(e);
		}

	}

}
