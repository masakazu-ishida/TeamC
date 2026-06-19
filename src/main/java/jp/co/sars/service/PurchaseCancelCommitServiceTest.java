package jp.co.sars.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dao.ItemsDAO;
import jp.co.sars.dao.PurchasesDAO;
import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.TestBase;

public class PurchaseCancelCommitServiceTest extends TestBase {
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
	void PurchaseCancelCommitService() {
		int purchaseId = 4; //注文ID
		try {
			PurchaseCancelCommitService service = new PurchaseCancelCommitService();
			service.execute(purchaseId);

			PurchasesDAO dao = new PurchasesDAO(con);
			PurchasesDTO dto = (PurchasesDTO) dao.findByPurchaseId(purchaseId);
			ItemsDAO idao = new ItemsDAO(con);
			ItemsDTO idto = idao.findById(1);
			assertNotNull(dto);
			assertEquals(4, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-17", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(true, dto.isCancel());

			assertNotNull(dto);
			assertEquals(1, idto.getItemId());
			assertEquals("麦わら帽子", idto.getName());
			assertEquals(13, idto.getStock());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	void PurchaseCancelConfirmServiceNull() {
		int purchaseId = 999; //注文ID
		try {
			PurchaseCancelCommitService service = new PurchaseCancelCommitService();
			PurchasesDTO dto = service.execute(purchaseId);

			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
