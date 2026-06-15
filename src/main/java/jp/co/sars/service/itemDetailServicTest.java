package jp.co.sars.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.util.TestBase;

class itemDetailServicTest extends TestBase {

	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	//商品詳細の取得
	@Test
	void testFindbyId() {

		itemDetailServic iDS = new itemDetailServic();

		try {
			ItemsDTO item = iDS.execute(1);

			assertNotNull(item);
			assertEquals(1, item.getItemId());
			assertEquals("麦わら帽子", item.getName());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(1, item.getCategoryId());
			assertEquals("黄色", item.getColor());
			assertEquals(4980, item.getPrice());
			assertEquals(12, item.getStock());
			assertEquals(false, item.isRecommended());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fail(e);
		}
	}

}
