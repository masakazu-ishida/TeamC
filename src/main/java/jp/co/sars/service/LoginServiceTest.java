package jp.co.sars.service;

import static org.junit.Assert.*;

import java.sql.SQLException;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.UserDTO;
import jp.co.sars.util.TestBase;

public class LoginServiceTest extends TestBase {
	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	以下の後始末メソッドは何も考えずまるごとコピー
	 * テストメソッドが全て完了した後、必ず実行され、
	 * DBのコネクションがクローズされる。なお、クローズされるのは
	 * /init_data.sqlで初期化するコネクション
	 * */
	@AfterAll
	public static void cleanUp() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	//ユーザID正しい、パスワード正しい場合
	@Test
	void TestLoginService() {
		String userId = "user1"; //ユーザID
		String password = "userpass1";//パスワード
		try {
			LoginService service = new LoginService();
			UserDTO dto = service.Login(userId, password);
			assertEquals("user1", dto.getUserId());
			assertEquals("userpass1", dto.getPassword());
			assertEquals("鳥取一郎", dto.getName());
			assertEquals("鳥取県鳥取市河原町６丁目１０７", dto.getAddress());
		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//ユーザID正しい、パスワード間違っている場合
	@Test
	void TestLoginServiceNull() {
		String userId = "user1"; //正しいユーザID
		String password = "userpass10";//間違っているパスワード
		try {
			LoginService service = new LoginService();
			UserDTO dto = service.Login(userId, password);
			assertEquals(null, dto);
		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//ユーザIDが間違っている、パスワード正しい場合
	@Test
	void TestLoginServiceNameWrong() {
		String userId = "user10"; //間違っているユーザID
		String password = "userpass1";//正しいパスワード
		try {
			LoginService service = new LoginService();
			UserDTO dto = service.Login(userId, password);
			assertEquals(null, dto);
		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//ユーザID、パスワードが間違っている場合
	@Test
	void TestLoginServiceNamePassWordWrong() {
		String userId = "user10"; //間違っているユーザID
		String password = "userpass10";//間違っているパスワード
		try {
			LoginService service = new LoginService();
			UserDTO dto = service.Login(userId, password);
			assertEquals(null, dto);
		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
