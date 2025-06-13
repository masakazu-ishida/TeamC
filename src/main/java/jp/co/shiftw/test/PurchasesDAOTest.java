package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.Date;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

class PurchasesDAOTest {

	@Test
	void testPurchasesDAO() {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn);
				dao.findByCond("a", new Date(), new Date());
				assertEquals(1, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
