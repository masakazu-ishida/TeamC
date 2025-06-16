package jp.co.shiftw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dao.UsersDAO;
import jp.co.shiftw.dto.UsersDTO;
import jp.co.shiftw.util.ConnectionUtil;

public class UsersDAOTest {

	@BeforeEach
	void init() {
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

	@Test
	void testLogin() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			UsersDAO dao = new UsersDAO(conn);

			try {

				UsersDTO dto = dao.Login("user", "userpass");

				assertNotNull(dto);

				assertEquals("user", dto.getUser_id());
				assertEquals("userpass", dto.getPassword());
				assertEquals("鳥取一郎", dto.getName());
				assertEquals("鳥取県鳥取市河原町６丁目１０７", dto.getAddress());

			} catch (Exception e) {

				fail(e.getMessage());

			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());

		}

	}

	@Test
	void testLoginisNot() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			UsersDAO dao = new UsersDAO(conn);
			try {

				UsersDTO dto = dao.Login("12345", "6789");
				assertNull(dto);

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				fail(e);
			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}
}
