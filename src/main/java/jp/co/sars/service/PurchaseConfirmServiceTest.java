package jp.co.sars.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.TestBase;

class PurchaseConfirmServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testfindbyId() {
		PurchaseConfirmService pcs = new PurchaseConfirmService();

		try {
			Map<String, Object> execute = pcs.execute("user1");

			assertNotNull(execute);

			assertEquals(2, execute.size());

			List<ItemsInCartDTO> cart = (List<ItemsInCartDTO>) execute.get("cartList");
			ItemsInCartDTO item = cart.get(0);

			assertEquals(1, item.getItemId());
			assertEquals("麦わら帽子", item.getItems().getName());
			assertEquals("日本帽子製造", item.getItems().getManufacturer());
			assertEquals("黄色", item.getItems().getColor());
			assertEquals(4980, item.getItems().getPrice());
			assertEquals(1, item.getAmount());

			int userPrice = (int) execute.get("userPrice");
			assertEquals(11940, userPrice);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fail(e);
		}

		//fail("まだ実装されていません");
	}

}
