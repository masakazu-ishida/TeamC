package jp.co.sars.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.TestBase;

public class PurchaseCancelConfirmServiceTest extends TestBase {
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
	void PurchaseCancelConfirmService() {
		int purchaseId = 1; //注文ID
		try {
			PurchaseCancelConfirmService service = new PurchaseCancelConfirmService();
			PurchasesDTO dto = service.execute(purchaseId);

			assertNotNull(dto);
			assertEquals(1, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-16", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(false, dto.isCancel());

			for (PurchaseDetailsDTO detail : dto.getList()) {
				assertEquals(4, detail.getAmount());
				assertEquals(7, detail.getItems().getItemId());
				assertEquals("ハンチング帽", detail.getItems().getName());
				assertEquals("黄色", detail.getItems().getColor());
				assertEquals("日本帽子製造", detail.getItems().getManufacturer());
				assertEquals(1980, detail.getItems().getPrice());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void PurchaseCancelConfirmServiceNull() {
		int purchaseId = 999; //注文ID
		try {
			PurchaseCancelConfirmService service = new PurchaseCancelConfirmService();
			PurchasesDTO dto = service.execute(purchaseId);

			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
