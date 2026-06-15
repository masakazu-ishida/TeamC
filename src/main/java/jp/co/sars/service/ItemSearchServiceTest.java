package jp.co.sars.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.util.TestBase;

class ItemSearchServiceTest extends TestBase {

	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	//カテゴリーとキーワード検索両方のテスト
	@Test
	void testFindAll2() {
		ItemSearchService iSS = new ItemSearchService();
		try {
			String keyword = "帽子";
			String category = "2";
			List<ItemsDTO> itemList = iSS.execute(keyword, category);

			assertNotNull(itemList);

			assertEquals(2, itemList.size());

			ItemsDTO item = itemList.get(0);
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

	//カテゴリーのみの検索
	@Test
	void testfindbyId() {
		ItemSearchService iSS = new ItemSearchService();
		try {
			String category = "2";
			List<ItemsDTO> itemList = iSS.execute("", category);

			assertNotNull(itemList);

			assertEquals(11, itemList.size());

			ItemsDTO item = itemList.get(0);
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
