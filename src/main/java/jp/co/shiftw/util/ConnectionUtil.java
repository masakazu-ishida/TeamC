package jp.co.shiftw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * JUnitでのテスト時は、@BeforeEachでは以下のコードを設定します。
 * 特にConnectionUtil.mode = ConnectionUtil.MODE.TEST;が重要です。
 * 
 * 
 * @BeforeEach
	void init() {
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			BaseDAO dao = new BaseDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");

			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	DAOは外からコネクションを受け取るのに対し、サービスは外からコネクションを
	受け取りません。よって、ConnectionUtilに対し動作モードをテストモードに
	設定します。
	
	これによりサービス内で呼び出しているgetConnection(String lookupString)は、JUnit
	でのテスト時はgetConnectionForJUnitから取得します。
	
 * 
 * 
 * @author mi_co
 *
 */
public class ConnectionUtil {

	//動作モードの列挙定数
	public enum MODE {
		EXECUTE, //通常実行
		TEST //テストモード
	};

	static {
		mode = MODE.EXECUTE;
	}
	//デフォルトは通常実行モード（接続プール）
	public static MODE mode;

	public static Connection getConnection(String lookupString)
			throws SQLException, ServletException {
		try {

			//動作モードによってConnecionの取得方法を変更する。
			if (mode == MODE.EXECUTE) {
				//接続プールから取得
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx.lookup(lookupString);
				return ds.getConnection();
			} else {
				//通常にその場で接続（JUnitでのテスト用）
				return getConnectionForJUnit();
			}
		} catch (NamingException e) {
			throw new ServletException(e);
		}

	}

	public static Connection getConnectionForJUnit() throws SQLException {

		String url = "jdbc:postgresql://localhost:5432/ecsite";
		String user = "ecsite";
		String pass = "ecsite";

		return DriverManager.getConnection(url, user, pass);
	}

}
