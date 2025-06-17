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

				List<ItemsDTO> dtos = dao.findByCond(1, "麦わら帽子");

				assertNotNull(dtos);

				//リストの最初の要素を取得
				ItemsDTO dto = dtos.get(0);

				//CategoriesDTOの取得
				CategoriesDTO category = dto.getCategory();

				assertEquals(1, category.getCategoryId());
				assertEquals("麦わら帽子", dto.getName());

				dtos.size();

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test

	void testNotFindByCond() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				List<ItemsDTO> dtos = dao.findByCond(200, "ホテルニューオータニ");

				assertNotNull(dtos);

				dtos.size();

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

}
