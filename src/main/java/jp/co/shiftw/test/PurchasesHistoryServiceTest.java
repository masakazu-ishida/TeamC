package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.dto.PurchaseDetailsDTO;
import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.service.PurchasesHistoryService;
import jp.co.shiftw.util.ConnectionUtil;

class PurchasesHistoryServiceTest {
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

	// 検索欄が空だった場合全件検索が出来るかのテスト
	@Test
	void testSearchPurchasesByUserIdAllIsCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				List<PurchasesDTO> list = PurchasesHistoryService.searchPurchasesByUserId(conn, null);
				assertEquals(2, list.size());
				PurchasesDTO purchaces = list.get(0);
				assertEquals(1, purchaces.getPurchaseId());
				assertEquals("user", purchaces.getPurchasedUser());
				assertEquals("2020-10-20", purchaces.getPurchasedDate().toString());

				List<PurchaseDetailsDTO> purchaseDetails = purchaces.getPurchaseDetails();
				assertEquals(1, purchaseDetails.size());

				PurchaseDetailsDTO details = purchaseDetails.get(0);
				ItemsDTO item = details.getItem();
				assertEquals("麦わら帽子", item.getName());
				assertEquals("黄色", item.getColor());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(4980, item.getPrice());

				assertEquals(1, details.getAmount());
				assertEquals("東京都", purchaces.getDestination());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	// 対象のUserIdから検索が出来るかのテスト
	@Test
	void testSearchPurchasesByUserIdIsCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				List<PurchasesDTO> list = PurchasesHistoryService.searchPurchasesByUserId(conn, "user");
				assertEquals(1, list.size());
				PurchasesDTO purchaces = list.get(0);
				assertEquals(1, purchaces.getPurchaseId());
				assertEquals("user", purchaces.getPurchasedUser());
				assertEquals("2020-10-20", purchaces.getPurchasedDate().toString());

				List<PurchaseDetailsDTO> purchaseDetails = purchaces.getPurchaseDetails();
				assertEquals(1, purchaseDetails.size());

				PurchaseDetailsDTO details = purchaseDetails.get(0);
				ItemsDTO item = details.getItem();
				assertEquals("麦わら帽子", item.getName());
				assertEquals("黄色", item.getColor());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(4980, item.getPrice());

				assertEquals(1, details.getAmount());
				assertEquals("東京都", purchaces.getDestination());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testSearchPurchasesByUserIdIsNotCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				List<PurchasesDTO> list = PurchasesHistoryService.searchPurchasesByUserId(conn, "存在しないuserId");
				assertEquals(0, list.size());
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
