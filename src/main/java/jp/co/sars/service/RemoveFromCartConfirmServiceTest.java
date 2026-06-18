package jp.co.sars.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.TestBase;

class RemoveFromCartConfirmServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	//削除する商品の情報を取得
	@Test
	void testexecute() {
		RemoveFromCartConfirmService rfcc = new RemoveFromCartConfirmService();

		try {
			ItemsInCartDTO cart = rfcc.execute("user1", 5);

			assertNotNull(cart);

			assertEquals("user1", cart.getUserId());
			assertEquals(3, cart.getAmount());
			assertEquals(5, cart.getItemId());
			assertEquals("2026-06-16", cart.getBookedDate().toString());

			assertEquals("野球帽", cart.getItems().getName());
			assertEquals("日本帽子製造", cart.getItems().getManufacturer());
			assertEquals(1, cart.getItems().getCategoryId());
			assertEquals("緑色", cart.getItems().getColor());
			assertEquals(2500, cart.getItems().getPrice());
			assertEquals(17, cart.getItems().getStock());
			assertEquals(true, cart.getItems().isRecommended());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
