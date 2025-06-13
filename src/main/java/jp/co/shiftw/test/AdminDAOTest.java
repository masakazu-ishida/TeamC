package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class AdminDAOTest {

	@Test
	void testLogin() {
		fail("まだ実装されていません");
		
		try (Connection connection = ConnectionManager.getConnection()) {
			AdminDAO dao = new AdminDAO(connection);

			try {

				DepartmentDTO dto = dao.findById(1);

				assertNotNull(dto);

				assertEquals(1, dto.getDeptId());
				assertEquals("総務部", dto.getDeptName());

			} catch (Exception e) {
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());
	}

}
