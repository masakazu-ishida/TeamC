package jp.co.shiftw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.ConnectionUtil;

class ItemsDAOTest {

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

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				List<ItemsDTO> dtos = dao.findByCond(2, "鞄B");

				assertNotNull(dtos);

				assertEquals(1, dtos.size());

				//リストの最初の要素を取得
				ItemsDTO dto = dtos.get(0);

				//CategoriesDTOの取得
				CategoriesDTO category = dto.getCategory();

				assertEquals(2, category.getCategoryId());
				assertEquals("鞄B", dto.getName());

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	void testFindByKey() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				List<ItemsDTO> dtos = dao.findByCond(0, "帽子");

				assertNotNull(dtos);

				assertEquals(2, dtos.size());

				//リストの最初の要素を取得
				ItemsDTO dto = dtos.get(0);

				//CategoriesDTOの取得
				CategoriesDTO category = dto.getCategory();

				//				for (ItemsDTO itemsDTO : dtos) {
				//					System.out.println(category.getCategoryId() + dto.getName());
				//				}

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	void testFindByCote() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				List<ItemsDTO> dtos = dao.findByCond(1, "");

				assertNotNull(dtos);

				assertEquals(11, dtos.size());

				//リストの最初の要素を取得
				ItemsDTO dto = dtos.get(0);

				//CategoriesDTOの取得
				CategoriesDTO category = dto.getCategory();

				//				for (ItemsDTO itemsDTO : dtos) {
				//					System.out.println(category.getCategoryId() + dto.getName());
				//				}

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	//検索した値がテーブルになく排除できているか確認するテスト
	void testNotFindByCond() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				List<ItemsDTO> dtos = dao.findByCond(200, "ホテルニューオータニ");

				assertNotNull(dtos);

				assertEquals(0, dtos.size());

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

	//検索した値が正しく取得できているか確認するテスト
	@Test
	@Order(1)
	void testFindByItemId() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				ItemsDTO dto = dao.findByItemId(1);

				assertNotNull(dto);
				assertEquals("麦わら帽子", dto.getName());
				assertEquals("日本帽子製造", dto.getManufacturer());

				//CategoriesDTOの取得
				CategoriesDTO category = dto.getCategory();
				assertEquals(1, category.getCategoryId());
				assertEquals("帽子", category.getName());

				assertEquals("黄色", dto.getColor());
				assertEquals(4980, dto.getPrice());
				assertEquals(12, dto.getStock());
				assertFalse(dto.isRecommended());

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	//検索した値がテーブルになくNullで返ってくるか確認するテスト
	void testNotFindByItemId() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				ItemsDTO dto = dao.findByItemId(99999);

				assertNull(dto);
			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

	@Test
	@Order(2)
	//存在するitemIdに対してに在庫数を増やすことが出来るかのテスト
	void testChangeStockIncrease() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			int itemId = 1; //テストで指定するItemId
			int changeNum = 3; //増減させる値
			ItemsDAO dao = new ItemsDAO(conn);

			try {
				//元の在庫数を調べる
				ItemsDTO dto = dao.findByItemId(itemId);
				int stockBefore = dto.getStock();

				//在庫数を3つ増やす
				dao.changeStock(itemId, changeNum);
				int stockAfter = stockBefore + changeNum;

				// 増やしたあとの在庫数を調べる
				dto = dao.findByItemId(itemId);
				assertEquals(stockAfter, dto.getStock());
			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

	@Test
	//存在するitemIdに対してに在庫数を減らすことが出来るかのテスト
	void testChangeStockDecrease() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			int itemId = 1; //テストで指定するItemId
			int changeNum = -3; //増減させる値
			ItemsDAO dao = new ItemsDAO(conn);

			try {
				//元の在庫数を調べる
				ItemsDTO dto = dao.findByItemId(itemId);
				int stockBefore = dto.getStock();

				//在庫数を3つ減らす
				dao.changeStock(itemId, changeNum);
				int stockAfter = stockBefore + changeNum;

				// 増やしたあとの在庫数を調べる
				dto = dao.findByItemId(itemId);
				assertEquals(stockAfter, dto.getStock());
			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}
	}

	@Test
	//存在するitemIdに対してに在庫数を0より少なくしようとした場合なにも起きないことを確認するテスト
	void testChangeStockOverDecrease() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			int itemId = 1; //テストで指定するItemId
			int changeNum = -3000; //増減させる値
			ItemsDAO dao = new ItemsDAO(conn);

			try {
				//元の在庫数を調べる
				ItemsDTO dto = dao.findByItemId(itemId);
				int stock = dto.getStock();

				//在庫数を3000つ減らす
				dao.changeStock(itemId, changeNum);

				// 在庫数が変わっていないことを調べる
				dto = dao.findByItemId(itemId);
				assertEquals(stock, dto.getStock());
			} catch (Exception e) {
				fail(e.getMessage());
			}
		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

	//存在しないitemIdに対してに在庫数を増やそうとしても何も起きないことのテスト
	void testNotChangeStockIncrease() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			int itemId = 99999; //テストで指定するItemId
			int changeNum = 3; //増減させる値
			ItemsDAO dao = new ItemsDAO(conn);

			try {
				//存在しないitemIdに対して在庫数を3つ増やす
				dao.changeStock(itemId, changeNum);
			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

}
