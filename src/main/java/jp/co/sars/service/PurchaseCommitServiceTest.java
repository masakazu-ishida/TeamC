package jp.co.sars.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.TestBase;

class PurchaseCommitServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	///住所がnull
	@Test
	void test() {

		PurchaseCommitService purchaseCommitService = new PurchaseCommitService();
		Date date = new Date(System.currentTimeMillis());

		try {
			Map<String, Object> execute = purchaseCommitService.execute("user1", null, date);

			assertNotNull(execute);

			assertEquals(2, execute.size());

			List<ItemsInCartDTO> cart = (List<ItemsInCartDTO>) execute.get("cartList");
			ItemsInCartDTO item = cart.get(0);

			assertEquals(5, item.getItemId());
			assertEquals("野球帽", item.getItems().getName());
			assertEquals("日本帽子製造", item.getItems().getManufacturer());
			assertEquals("緑色", item.getItems().getColor());
			assertEquals(2500, item.getItems().getPrice());
			assertEquals(3, item.getAmount());

			List<PurchasesDTO> purchases = (List<PurchasesDTO>) execute.get("cartList");
			PurchasesDTO dto = purchases.get(0);

			assertEquals("ご自宅", dto.getDestination());

			int userPrice = (int) execute.get("userPrice");
			assertEquals(7500, userPrice);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	//住所欄が記入されている場合のテスト
	@Test
	void test2() {

		PurchaseCommitService purchaseCommitService = new PurchaseCommitService();
		Date date = new Date(System.currentTimeMillis());

		try {
			Map<String, Object> execute = purchaseCommitService.execute("user1", "ああああ", date);

			assertNotNull(execute);

			assertEquals(2, execute.size());

			List<ItemsInCartDTO> cart = (List<ItemsInCartDTO>) execute.get("cartList");
			ItemsInCartDTO item = cart.get(0);

			assertEquals(5, item.getItemId());
			assertEquals("野球帽", item.getItems().getName());
			assertEquals("日本帽子製造", item.getItems().getManufacturer());
			assertEquals("緑色", item.getItems().getColor());
			assertEquals(2500, item.getItems().getPrice());
			assertEquals(3, item.getAmount());

			List<PurchasesDTO> purchases = (List<PurchasesDTO>) execute.get("cartList");
			PurchasesDTO dto = purchases.get(0);

			assertEquals("ああああ", dto.getDestination());

			int userPrice = (int) execute.get("userPrice");
			assertEquals(7500, userPrice);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	///異常系ユーザーIDが存在しない場合
	@Test
	void test3() {

		PurchaseCommitService purchaseCommitService = new PurchaseCommitService();
		Date date = new Date(System.currentTimeMillis());
		try {

			Map<String, Object> execute = purchaseCommitService.execute("user1111", null, date);

			assertNotNull(execute);
			assertEquals(2, execute.size());

			if (execute != null && execute.size() == 2) {
				List<PurchasesDTO> cartList = (List<PurchasesDTO>) execute.get("cartList");
				if (cartList != null && !cartList.isEmpty()) {
					fail("バグ");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

}
