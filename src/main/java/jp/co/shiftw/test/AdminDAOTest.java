package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.AdminDAO;
import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.AdminDTO;
import jp.co.shiftw.util.ConnectionUtil;

class AdminDAOTest {

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
		//fail("まだ実装されていません");

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			AdminDAO dao = new AdminDAO(conn);

			try {

				AdminDTO dto = dao.Login("admin", "admin");

				assertNotNull(dto);

				assertEquals("admin", dto.getAdminId());
				assertEquals("admin", dto.getPassword());
				assertEquals("管理者", dto.getName());

			} catch (Exception e) {
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());
		}
	}

	@Test
	void testLoginIsNot() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			AdminDAO dao = new AdminDAO(conn);

			try {

				AdminDTO dto = dao.Login("aaaaaa", "iiiiiiii");

				assertNull(dto);

			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}
}
