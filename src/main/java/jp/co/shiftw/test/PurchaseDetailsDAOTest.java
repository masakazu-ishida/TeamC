package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dao.PurchaseDetailsDAO;
import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.dto.PurchaseDetailsDTO;
import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.util.ConnectionUtil;

class PurchaseDetailsDAOTest {
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
	void testCreate() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			dao.create(2, 6, 5);

			PurchasesDAO dao2 = new PurchasesDAO(conn);
			PurchasesDTO dto = dao2.findByPurchaseId(2);

			List<PurchaseDetailsDTO> purchaseDetails = dto.getPurchaseDetails();
			assertEquals(2, purchaseDetails.size());

			PurchaseDetailsDTO purchaseDetailsDTO = purchaseDetails.get(1);
			assertEquals(5, purchaseDetailsDTO.getPurchaseDetailId());
			ItemsDAO dao3 = new ItemsDAO(conn);
			ItemsDTO itemsDTO = dao3.findByItemId(6);

			assertEquals(itemsDTO.getItemId(), purchaseDetailsDTO.getItem().getItemId());
			assertEquals(5, purchaseDetailsDTO.getAmount());
		} catch (Exception e) {
			fail(e.toString());
		}

	}

}
