package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.service.PurchaseCancelService;
import jp.co.shiftw.service.PurchasesHistoryService;
import jp.co.shiftw.util.ConnectionUtil;

class PurchaseCancelServiceTest {
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

	//存在するpurchaseIdでキャンセル処理を行った結果検索で表示されなくなるかのテスト
	@Test
	void testCancelPurchaseIsCorrect() {
		PurchaseCancelService.cancelPurchase(1); //キャンセル処理を実行

		List<PurchasesDTO> purchasesDTOs = PurchasesHistoryService.searchPurchasesByUserId("user"); //userの注文(本来は2つだが1つをキャンセルした)を取得
		assertEquals(1, purchasesDTOs.size()); //取得件数が減っているかどうか確認

		PurchasesDTO purchasesDTO = PurchasesHistoryService.searchPurchasesByPurchaseId(1); //先ほどキャンセルした注文を取得
		assertNull(purchasesDTO); //取得出来ていないことを確認
	}

	//存在しないpurchaseIdでキャンセル処理を行った結果何も変わらないことを確認
	@Test
	void testCanselPurchaseIsNotCorrect() {
		PurchaseCancelService.cancelPurchase(9999);//キャンセル処理を実行

		List<PurchasesDTO> purchasesDTOs = PurchasesHistoryService.searchPurchasesByUserId(null); //全件検索
		assertEquals(3, purchasesDTOs.size()); //取得件数が変わっていないかの確認

	}
}
