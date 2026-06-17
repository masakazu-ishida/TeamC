package jp.co.sars.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.TestBase;

class CartServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	@Test
	void testdisplayCart() throws SQLException, ServletException {
		CartService service = new CartService();
		try {

			Map<String, Object> displayCart = service.execute("user1");
			assertEquals(2, displayCart.size());

			List<ItemsInCartDTO> cart = (List<ItemsInCartDTO>) displayCart.get("cart");

			assertNotNull(cart);
			assertEquals(1, cart.size());

			ItemsInCartDTO item = cart.get(0);

			assertNotNull(item);
			assertEquals("user1", item.getUserId());
			assertEquals(5, item.getItemId());
			assertEquals(3, item.getAmount());
			assertEquals("2026-06-16", item.getBookedDate().toString());

			assertEquals("野球帽", item.getItems().getName());
			assertEquals("緑色", item.getItems().getColor());
			assertEquals("日本帽子製造", item.getItems().getManufacturer());
			assertEquals(2500, item.getItems().getPrice());
			assertEquals(1, item.getItems().getCategoryId());
			assertEquals(17, item.getItems().getStock());
			assertEquals(true, item.getItems().isRecommended());

			int userPrice = (int) displayCart.get("userPrice");
			assertEquals(7500, userPrice);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//カート内にデータがない場合
	@Test
	void testdisplayCart2() throws SQLException, ServletException {
		CartService service = new CartService();
		try {
			Map<String, Object> displayCart = service.execute("user2");

			assertEquals(2, displayCart.size());

			List<ItemsInCartDTO> cart = (List<ItemsInCartDTO>) displayCart.get("cart");

			assertNotNull(cart);
			assertEquals(0, cart.size());

			int userPrice = (int) displayCart.get("userPrice");
			assertEquals(0, userPrice);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}
}
