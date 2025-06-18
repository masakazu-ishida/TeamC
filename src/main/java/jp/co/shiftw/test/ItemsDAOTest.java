package jp.co.shiftw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

				for (ItemsDTO itemsDTO : dtos) {
					System.out.println(category.getCategoryId() + dto.getName());
				}

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

}
