package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.util.ConnectionUtil;
import jp.co.sars.util.TestBase;

public class PurchaseDetailsDAOTest extends TestBase {

	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@AfterAll
	public static void cleanUp() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	//注文詳細IDで表示する
	@Test
	void testFindById() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			PurchaseDetailsDTO dto = dao.findById(1);

			assertNotNull(dto);

			assertEquals(1, dto.getPurchaseDetailId());
			assertEquals(1, dto.getPurchaseId());
			assertEquals(7, dto.getItemId());
			assertEquals(4, dto.getAmount());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//注文者（ユーザID）で注文履歴を表示する
	@Test
	void testFindById1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			List<PurchaseDetailsDTO> purchaseDetailsList = dao.findByPurchasedUser("user1");

			assertNotNull(purchaseDetailsList);
			assertEquals(3, purchaseDetailsList.size());

			for (PurchaseDetailsDTO dto : purchaseDetailsList) {
				assertEquals("2026-06-15", dto.getPurchases().getPurchasedDate().toString());
				assertEquals("野球帽", dto.getItems().getName());
				assertEquals("緑色", dto.getItems().getColor());
				assertEquals("日本帽子製造", dto.getItems().getManufacturer());
				assertEquals(2500, dto.getItems().getPrice());
				assertEquals(2, dto.getAmount());
				assertEquals(null, dto.getPurchases().getDestination());
				assertEquals(false, dto.getPurchases().isCancel());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//注文IDで注文履歴を表示する
	@Test
	void testFindById2() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			List<PurchaseDetailsDTO> purchaseDetailsList = dao.findByPurchaseId(1);

			assertNotNull(purchaseDetailsList);
			assertEquals(3, purchaseDetailsList.size());

			for (PurchaseDetailsDTO dto : purchaseDetailsList) {
				assertEquals("2026-06-15", dto.getPurchases().getPurchasedDate().toString());
				assertEquals("野球帽", dto.getItems().getName());
				assertEquals("緑色", dto.getItems().getColor());
				assertEquals("日本帽子製造", dto.getItems().getManufacturer());
				assertEquals(2500, dto.getItems().getPrice());
				assertEquals(2, dto.getAmount());
				assertEquals(null, dto.getPurchases().getDestination());
				assertEquals(false, dto.getPurchases().isCancel());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}
}
