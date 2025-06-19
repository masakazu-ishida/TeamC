package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.CategoriesDAO;
import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.util.ConnectionUtil;

public class CategoriesDAOTest {

	@Test
	void testfindAll() {
		//fail("まだ実装されていません");

		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			CategoriesDAO dao = new CategoriesDAO(connection);

			try {

				List<CategoriesDTO> dto = dao.findAll();

				assertNotNull(dto);

				assertEquals(3, dto.size());

			} catch (Exception e) {
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());
		}

		try (

				Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			CategoriesDAO dao = new CategoriesDAO(connection);

			try {

				List<CategoriesDTO> dtos = dao.findAll();

				CategoriesDTO cate = dtos.get(0);

				assertEquals(0, cate.getCategoryId());
				assertEquals("すべて", cate.getName());

			} catch (Exception e) {
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());
		}
	}

	@Test
	void testFindById() {
		//fail("まだ実装されていません");

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			CategoriesDAO dao = new CategoriesDAO(conn);

			try {

				CategoriesDTO dto = dao.findById(1);

				assertNotNull(dto);

				assertEquals(1, dto.getCategoryId());
				assertEquals("帽子", dto.getName());

			} catch (Exception e) {
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());
		}
	}

}
