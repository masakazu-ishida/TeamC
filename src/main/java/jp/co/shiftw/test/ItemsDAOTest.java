package jp.co.shiftw.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.ConnectionUtil;

class ItemsDAOTest {

	@Test
	void testFindByCond() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			try {

				List<ItemsDTO> dtos = dao.findByCond(1, "麦わら帽子");

				assertNotNull(dtos);

				//リストの最初の要素を取得
				ItemsDTO dto = dtos.get(0);

				//
				CategoriesDTO category = dto.getCategory();

				assertEquals(1, category.getCategoryId());
				assertEquals("麦わら帽子", dto.getName());

			} catch (Exception e) {

				fail(e.getMessage());
			}

		} catch (SQLException e) {

			e.printStackTrace();
			fail(e.getMessage());

		}

	}

}
