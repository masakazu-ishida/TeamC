package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.util.ConnectionUtil;
import jp.co.sars.util.TestBase;

class ItemsDAOTest extends TestBase {

	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	以下の後始末メソッドは何も考えずまるごとコピー
	 * テストメソッドが全て完了した後、必ず実行され、
	 * DBのコネクションがクローズされる。なお、クローズされるのは
	 * /init_data.sqlで初期化するコネクション
	 * */
	@AfterAll
	public static void cleanUp() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Test
	void testFindAll() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);
			List<ItemsDTO> itemList = dao.findAll();

			assertNotNull(itemList);
			assertEquals(20, itemList.size());

			for (ItemsDTO item : itemList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.isRecommended());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testFindById() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);
			ItemsDTO item = dao.findById(1);

			assertNotNull(item);
			assertEquals(1, item.getItem_id());
			assertEquals("麦わら帽子", item.getName());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(1, item.getCategory_id());
			assertEquals("黄色", item.getColor());
			assertEquals(4980, item.getPrice());
			assertEquals(12, item.getStock());
			assertEquals(false, item.isRecommended());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testUpdate() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);
			ItemsDTO item = new ItemsDTO();

			item.setStock(11);
			item.setItem_id(1);

			int result = dao.update(item);

			assertEquals(1, result);

			item = dao.findById1(1);
			assertNotNull(item);
			assertEquals(1, item.getItem_id());
			assertEquals("麦わら帽子", item.getName());
			assertEquals("日本帽子製造", item.getManufacturer());
			assertEquals(1, item.getCategory_id());
			assertEquals("黄色", item.getColor());
			assertEquals(4980, item.getPrice());
			assertEquals(11, item.getStock());
			assertEquals(false, item.isRecommended());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testFindAm() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> itemList = dao.findAm("帽子");

			assertNotNull(itemList);
			assertEquals(2, itemList.size());

			for (ItemsDTO item : itemList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.isRecommended());
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testFindAll2() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> itemList = dao.findAll2("帽子", 1);

			assertNotNull(itemList);
			assertEquals(2, itemList.size());

			for (ItemsDTO item : itemList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.isRecommended());
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

}
