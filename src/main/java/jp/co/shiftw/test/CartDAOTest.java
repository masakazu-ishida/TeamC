package jp.co.shiftw.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

class CartDAOTest {

	@Test
	void test() {

		String lookupString = CommonConstants.LOOKUP_NAME;
		String sql = "SELECT * FROM items_in_cart";

		try (Connection conn = ConnectionUtil.getConnection(lookupString);) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {

				System.out.println("aaa");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}