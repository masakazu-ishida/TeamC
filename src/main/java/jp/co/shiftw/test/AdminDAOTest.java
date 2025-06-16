package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.AdminDAO;
import jp.co.shiftw.dto.AdminDTO;
import jp.co.shiftw.util.ConnectionUtil;

class AdminDAOTest {

	@Test
	void testLogin() {
		//fail("まだ実装されていません");

		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			AdminDAO dao = new AdminDAO(connection);

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

}
