package jp.co.shiftw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.UsersDTO;
import jp.co.shiftw.service.UsersService;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class UsersServiseTest {

	@BeforeEach
	void init() {
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

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
	void testloginUsers() {
		try {

			UsersDTO user = UsersService.loginUsers("user", "userpass");

			assertNotNull(user);

			assertEquals("user", user.getUser_id());
			assertEquals("userpass", user.getPassword());
			assertEquals("鳥取一郎", user.getName());
			assertEquals("鳥取県鳥取市河原町６丁目１０７", user.getAddress());

		} catch (Exception e) {

			fail(e.getMessage());

		}
	}

	@Test
	void testloginUsersisNot() {
		try {

			UsersDTO user = UsersService.loginUsers("user", "1userpass");
			assertNull(user);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}

	}

}
