package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.dto.PurchaseDetailsDTO;
import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.service.CartAddService;
import jp.co.shiftw.service.CartListService;
import jp.co.shiftw.service.PurchaseService;
import jp.co.shiftw.util.ConnectionUtil;

class PurchaseServiceTest {
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

	//購入処理がしっかり出来るかのテスト
	@Test
	void testPurchaseAdd() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			String userId = "user2";
			int itemId1 = 7;
			int itemId2 = 10;
			int amount1 = 1;
			int amount2 = 5;

			Date bookedDate;
			String strDate = "2022-01-30";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			bookedDate = dateFormat.parse(strDate);

			// 注文する商品の元の在庫数を確認
			ItemsDAO itemsDAO = new ItemsDAO(conn);
			ItemsDTO item1 = itemsDAO.findByItemId(itemId1);
			ItemsDTO item2 = itemsDAO.findByItemId(itemId2);

			int stock1 = item1.getStock();
			int stock2 = item2.getStock();

			//カートを作成
			CartAddService.CartAdd(userId, itemId1, amount1, bookedDate);
			CartAddService.CartAdd(userId, itemId2, amount2, bookedDate);
			List<CartDTO> cartList = CartListService.cartList(userId); //追加したカートをリストとして取得

			//注文を実行出来ているかを確認
			boolean isPurchased = PurchaseService.purchase(userId, cartList, "群馬県");
			assertTrue(isPurchased);

			//商品の在庫が注文個数分減っているのかを確認
			item1 = itemsDAO.findByItemId(itemId1);
			item2 = itemsDAO.findByItemId(itemId2);
			assertEquals(stock1 - amount1, item1.getStock());
			assertEquals(stock2 - amount2, item2.getStock());

			//カートが削除されていることの確認
			cartList = CartListService.cartList(userId);
			assertEquals(0, cartList.size());

			//注文が正しく追加されているか確認
			PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
			PurchasesDTO purchasesDTO = purchasesDAO.findByPurchaseId(4);

			assertNotNull(purchasesDTO);

			List<PurchaseDetailsDTO> purchaseDetails = purchasesDTO.getPurchaseDetails();
			assertEquals(2, purchaseDetails.size());

		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
