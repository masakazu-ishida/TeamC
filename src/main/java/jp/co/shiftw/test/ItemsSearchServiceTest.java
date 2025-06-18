package jp.co.shiftw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.service.ItemsSearchService;
import jp.co.shiftw.util.ConnectionUtil;

class ItemsSearchServiceTest {

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

	//検索した値が正しく取得できているか確認するテスト
	@Test
	void testFindByCond() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(1, "ストローハット");

		assertNotNull(dtos);

		assertEquals(3, dtos.size());

		//リストの最初の要素を取得
		ItemsDTO dto = dtos.get(0);

		//CategoriesDTOの取得
		CategoriesDTO category = dto.getCategory();

		assertEquals(1, category.getCategoryId());
		assertEquals("ストローハット", dto.getName());

	}

	@Test
	//検索した値がテーブルになくても排除できるか確認するテスト
	void testNotFindByCond() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(50, "ホテルニューオータニ");

		assertNotNull(dtos);

		assertEquals(0, dtos.size());

	}

	@Test
	//キーワードなしカテゴリ全て
	void testShowAll() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(0, null);

		assertNotNull(dtos);

		assertEquals(20, dtos.size());

	}

	@Test
	//キーワードなしカテゴリ帽子
	void testShowHat() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(1, null);

		assertNotNull(dtos);

		assertEquals(11, dtos.size());

	}

	@Test
	//キーワードなしカテゴリ鞄
	void testShowBag() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(2, null);

		assertNotNull(dtos);

		assertEquals(9, dtos.size());
	}

	@Test
	//キーワードありカテゴリ全て
	void testShowKeywordALL() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(0, "ストローハット");

		assertNotNull(dtos);

		assertEquals(3, dtos.size());
	}

	//@Test
	//キーワードありカテゴリ帽子
	void testShowKeywordHat() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(1, "帽子");

		assertNotNull(dtos);

		assertEquals(2, dtos.size());

	}

	@Test
	//キーワードありカテゴリ鞄
	void testShowKeywordBag() {

		List<ItemsDTO> dtos = ItemsSearchService.findByCond(2, "鞄");

		assertNotNull(dtos);

		assertEquals(9, dtos.size());
	}

}
