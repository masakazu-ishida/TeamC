package jp.co.sars.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.util.TestBase;

class CartAddServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	//同じ商品IDのものがなかった時
	@Test
	void testaddCartItem() {

		CartAddService service = new CartAddService();

		String userId = "user2";
		int itemId = 1;
		int amount = 2;

		try {
			int result = service.addCartItem(userId, itemId, amount);

			assertEquals(1, result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//同じ商品IDのものがあったとき(数量のみ変更)
	@Test
	void testaddCartItem2() {

		CartAddService service = new CartAddService();

		String userId = "user1";
		int itemId = 5;
		int amount = 2;

		try {
			int result = service.addCartItem(userId, itemId, amount);
			//テストデータにはすでにitemIdが5、数量3が入っています

			assertEquals(1, result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
