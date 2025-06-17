package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.AdminDTO;
import jp.co.shiftw.service.AdminService;
import jp.co.shiftw.util.ConnectionUtil;

class AdminServiceTest {

	@BeforeEach
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

	@Test
	void testLogin() {
		//fail("まだ実装されていません");

		try {

			AdminDTO dto = AdminService.loginAdmin("admin", "admin");

			assertNotNull(dto);

			assertEquals("admin", dto.getAdminId());
			assertEquals("admin", dto.getPassword());
			assertEquals("管理者", dto.getName());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testLoginIsNot() {

		AdminDTO dto = AdminService.loginAdmin("iiiii", "uuuuuu");

		assertNull(dto);

	}
}
