package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.service.CartListService;
import jp.co.shiftw.util.ConnectionUtil;

class CartDAOTest {

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

	@Test //存在するIDでリストを表示
	void CartTest() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			CartDAO dao = new CartDAO(conn);
			List<CartDTO> list = dao.CartList("user");
			assertEquals(3, list.size());

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

				}
				i = i + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test //存在しないIDでリストを表示
	void CartNullTest() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			CartDAO dao = new CartDAO(conn);
			List<CartDTO> list = dao.CartList("usera");
			assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test //存在するIDでリストを表示
	void CartListServiceTest() {

		List<CartDTO> list = CartListService.CartList("user");

		assertEquals(3, list.size());

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

			}
			i = i + 1;
		}

	}

	@Test //存在しないIDでリストを表示
	void CartServiceNullTest() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			CartDAO dao = new CartDAO(conn);
			List<CartDTO> list = dao.CartList("usera");
			assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}