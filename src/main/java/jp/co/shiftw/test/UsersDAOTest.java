package jp.co.shiftw.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.UsersDAO;
import jp.co.shiftw.dto.UsersDTO;
import jp.co.shiftw.util.ConnectionUtil;

public class UsersDAOTest {

	@Test
	void testLogin() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			UsersDAO dao = new UsersDAO(conn);

			try {

				UsersDTO dto = dao.Login("user", "userpass");

				assertNotNull(dto);

				assertEquals("user", dto.getUser_id());
				assertEquals("userpass", dto.getPassword());
				assertEquals("鳥取太郎", dto.getName());
				assertEquals("東京都港区赤坂3-3-3", dto.getAddress());

			} catch (Exception e) {

				fail(e.getMessage());

			}
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());

		}

	}
}
