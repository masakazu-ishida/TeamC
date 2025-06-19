package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.service.CartDeleteCheckService;

class CartDeleteCheckServiceTest {

	@Test //存在するIDでリストを表示
	void CartItemServiceTest() {

		System.out.println("存在するUserIDとItemIDで表示（Service）");

		CartDTO cartItem = CartDeleteCheckService.cartDeleteCheckService("user", 1);

		ItemsDTO item = cartItem.getItems();

		System.out.println(item.getName()
				+ item.getColor()
				+ item.getManufacturer()
				+ item.getPrice()
				+ cartItem.getAmount());

		assertEquals("麦わら帽子", item.getName());
		assertEquals("黄色", item.getColor());
		assertEquals("日本帽子製造", item.getManufacturer());
		assertEquals(4980, item.getPrice());
		assertEquals(5, cartItem.getAmount());

		System.out.println("--------------------------------------------------");

	}

}
