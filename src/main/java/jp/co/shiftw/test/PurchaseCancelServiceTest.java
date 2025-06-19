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
import jp.co.shiftw.service.ItemsSearchService;
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

	//存在するpurchaseIdでキャンセル処理を行った結果検索で表示されなくなり, 在庫が変化しているかのテスト
	@Test
	void testCancelPurchaseIsCorrect() {
		// キャンセルされる商品の元の在庫数を調べる
		ItemsDTO itemsDTO = ItemsSearchService.findByItemId(2);
		int stockBefore = itemsDTO.getStock();

		// キャンセルする商品の購入品数を調べる
		PurchasesDTO purchasesDTO = PurchasesHistoryService.searchPurchasesByPurchaseId(2);
		List<PurchaseDetailsDTO> purchaseDetails = purchasesDTO.getPurchaseDetails();
		PurchaseDetailsDTO purchaseDetailsDTO = purchaseDetails.get(0);
		int amount = purchaseDetailsDTO.getAmount();

		PurchaseCancelService.cancelPurchase(2); //キャンセル処理を実行

		List<PurchasesDTO> purchasesDTOs = PurchasesHistoryService.searchPurchasesByUserId("user2"); //userの注文(本来は2つだが1つをキャンセルした)を取得
		assertEquals(0, purchasesDTOs.size()); //取得件数が減っているかどうか確認

		purchasesDTO = PurchasesHistoryService.searchPurchasesByPurchaseId(2); //先ほどキャンセルした注文を取得
		assertNull(purchasesDTO); //取得出来ていないことを確認

		// 商品の在庫数がキャンセルされた商品の注文個数分増えているかの確認
		itemsDTO = ItemsSearchService.findByItemId(2);
		int stockAfter = itemsDTO.getStock();

		assertEquals(stockBefore + amount, stockAfter);
	}

	//存在しないpurchaseIdでキャンセル処理を行った結果何も変わらないことを確認
	@Test
	void testCanselPurchaseIsNotCorrect() {
		PurchaseCancelService.cancelPurchase(9999);//キャンセル処理を実行

		List<PurchasesDTO> purchasesDTOs = PurchasesHistoryService.searchPurchasesByUserId(null); //全件検索
		assertEquals(3, purchasesDTOs.size()); //取得件数が変わっていないかの確認

	}
}
