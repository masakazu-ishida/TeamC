package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.service.CartAddService;
import jp.co.shiftw.service.CartListService;
import jp.co.shiftw.util.ConnectionUtil;

class CartAddserviceTest {

	@Test
	void test() {
		fail("まだ実装されていません");
	}

	@Test //カートリストに追加されているか
	void CartListAddTest() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			String strDate = "2020-10-20";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = null;

			try {
				expected = dateFormat.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
				fail();
			}

			CartAddService.CartAdd("user", 4, 7, expected);

			List<CartDTO> list = CartListService.cartList("user");

			assertEquals(4, list.size());

			int i = 0;

			for (CartDTO cartDTO : list) {

				ItemsDTO item = cartDTO.getItems();

				System.out.println(item.getName()
						+ item.getColor()
						+ item.getManufacturer()
						+ item.getPrice()
						+ cartDTO.getAmount());
				switch (i) {
				case 0: {
					assertEquals("麦わら帽子", item.getName());
					assertEquals("黄色", item.getColor());
					assertEquals("日本帽子製造", item.getManufacturer());
					assertEquals(4980, item.getPrice());
					assertEquals(5, cartDTO.getAmount());

					break;
				}
				case 1: {
					assertEquals("ストローハット", item.getName());
					assertEquals("茶色", item.getColor());
					assertEquals("(株)ストローハットジャパン", item.getManufacturer());
					assertEquals(3480, item.getPrice());
					assertEquals(1, cartDTO.getAmount());

					break;
				}

				case 2: {
					assertEquals("子ども用麦わら帽子", item.getName());
					assertEquals("赤色", item.getColor());
					assertEquals("東京帽子店", item.getManufacturer());
					assertEquals(2980, item.getPrice());
					assertEquals(3, cartDTO.getAmount());

					break;
				}

				case 3: {
					assertEquals("ストローハット PART2", item.getName());
					assertEquals("青色", item.getColor());
					assertEquals("(株)ストローハットジャパン", item.getManufacturer());
					assertEquals(4480, item.getPrice());
					assertEquals(7, cartDTO.getAmount());

					break;
				}
				}
				i = i + 1;
			}

			System.out.println("--------------------------------------------------");

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
