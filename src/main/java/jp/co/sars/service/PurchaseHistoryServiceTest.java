package jp.co.sars.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.TestBase;

public class PurchaseHistoryServiceTest extends TestBase {
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

	@Test
	void TestPurchaseHistoryService() {
		String userId = "user1"; //ユーザID
		try {
			PurchaseHistoryService service = new PurchaseHistoryService();
			List<PurchasesDTO> dto = service.execute(userId);

			assertNotNull(dto);
			assertEquals(1, dto.size());
			assertEquals(3, dto.get(0).getList().size());

			for (PurchasesDTO list : dto) {
				assertEquals(1, list.getPurchaseId());
				assertEquals("user1", list.getPurchasedUser());
				assertEquals("2026-06-16", list.getPurchasedDate().toString());
				assertEquals(null, list.getDestination());
				assertEquals(4, list.getList().get(0).getAmount());
				assertEquals(7, list.getList().get(0).getItems().getItemId());
				assertEquals("ハンチング帽", list.getList().get(0).getItems().getName());
				assertEquals("黄色", list.getList().get(0).getItems().getColor());
				assertEquals("日本帽子製造", list.getList().get(0).getItems().getManufacturer());
				assertEquals(1980, list.getList().get(0).getItems().getPrice());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//ユーザID検索で結果がない場合
	@Test
	void TestPurchaseHistoryServiceNull() {
		String userId = "user4"; //ユーザID
		try {
			PurchaseHistoryService service = new PurchaseHistoryService();
			List<PurchasesDTO> dto = service.execute(userId);

			assertNotNull(dto);
			assertEquals(0, dto.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
