package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.ConnectionUtil;
import jp.co.sars.util.TestBase;

class ItemsInCartDAOTest extends TestBase {

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

	//カートテーブル内にデータがある場合のテスト
	@Test
	void testfindById1() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			conn.setAutoCommit(false);

			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			//テスト用データの挿入1件目
			ItemsInCartDTO cart1 = new ItemsInCartDTO();
			cart1.setUserId("user1");
			cart1.setItemId(1);
			cart1.setAmount(2);
			cart1.setBookedDate(java.sql.Date.valueOf("2025-04-12"));
			dao.insert(cart1);//インサート

			//テスト用データ2件目
			ItemsInCartDTO cart2 = new ItemsInCartDTO();
			cart2.setUserId("user1");
			cart2.setItemId(2);
			cart2.setAmount(3);
			cart2.setBookedDate(java.sql.Date.valueOf("2025-04-12"));
			dao.insert(cart2); //インサート

			List<ItemsInCartDTO> cart = dao.findById("user1");//ここから確認開始をする
			assertNotNull(cart);
			assertEquals(2, cart.size());

			//一件目の確認
			ItemsInCartDTO item1 = cart.get(0);
			assertEquals("user1", item1.getUserId());
			assertEquals(1, item1.getItemId());
			assertEquals(2, item1.getAmount());
			assertEquals("2025-04-12", item1.getBookedDate().toString());

			//結合先(itemsテーブル)のデータ

			assertNotNull(item1.getItems(), "結合されたItemsDTOがnull");
			assertEquals("麦わら帽子", item1.getItems().getName());
			assertEquals("黄色", item1.getItems().getColor());
			assertEquals("日本帽子製造", item1.getItems().getManufacturer());
			assertEquals(4980, item1.getItems().getPrice());

			//二件目の確認
			ItemsInCartDTO item2 = cart.get(1);
			assertEquals("user1", item2.getUserId());
			assertEquals(2, item2.getItemId());
			assertEquals(3, item2.getAmount());
			assertEquals("2025-04-12", item2.getBookedDate().toString());

			//二件目の結合先データ
			assertNotNull(item2.getItems(), "結合されたItemsDTOがnull");
			assertEquals("ストローハット", item2.getItems().getName());
			assertEquals("茶色", item2.getItems().getColor());
			assertEquals("(株)ストローハットジャパン", item2.getItems().getManufacturer());
			assertEquals(3480, item2.getItems().getPrice());

			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//主キーが存在しない場合のテスト
	@Test
	void testfindById2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			List<ItemsInCartDTO> cart = dao.findById("999999ABVS?(%#$");

			assertNotNull(cart);
			assertEquals(0, cart.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testInsert() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO cart = new ItemsInCartDTO();

			cart.setUserId("user1");
			cart.setItemId(2);
			cart.setAmount(2);
			cart.setBookedDate(java.sql.Date.valueOf("2025-04-12"));

			int result = dao.insert(cart);

			assertEquals(1, result);

			List<ItemsInCartDTO> cartList = dao.findById("user1");
			//件数の確認
			assertNotNull(cartList);
			assertEquals(1, cartList.size(), "データが見つかりません");

			//入れたものを取り出して確認
			ItemsInCartDTO savedCart = cartList.get(0);

			assertNotNull(savedCart);
			assertEquals("user1", savedCart.getUserId());
			assertEquals(2, savedCart.getItemId());
			assertEquals(2, savedCart.getAmount());
			assertEquals("2025-04-12", savedCart.getBookedDate().toString());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testUpdate() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			conn.setAutoCommit(false);

			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO cart = new ItemsInCartDTO();

			//テスト用データ1件目
			ItemsInCartDTO cart1 = new ItemsInCartDTO();
			cart1.setUserId("user1");
			cart1.setItemId(2);
			cart1.setAmount(2);
			cart1.setBookedDate(java.sql.Date.valueOf("2025-04-12"));
			dao.insert(cart1);//インサート

			cart.setAmount(4);
			cart.setUserId("user1");
			cart.setItemId(2);

			int result = dao.update(cart);

			assertEquals(1, result);

			List<ItemsInCartDTO> cartList = dao.findById("user1");
			//件数の確認
			assertNotNull(cartList);
			assertEquals(1, cartList.size(), "データが見つかりません");

			//入れたものを取り出す
			ItemsInCartDTO savedCart = cartList.get(0);

			assertNotNull(savedCart);
			assertEquals("user1", savedCart.getUserId());
			assertEquals(2, savedCart.getItemId());
			assertEquals(4, savedCart.getAmount());
			assertEquals("2025-04-12", savedCart.getBookedDate().toString());

			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testDelete() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			conn.setAutoCommit(false);

			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO cart = new ItemsInCartDTO();

			ItemsInCartDTO cart1 = new ItemsInCartDTO();
			cart1.setUserId("user1");
			cart1.setItemId(1);
			cart1.setAmount(2);
			cart1.setBookedDate(java.sql.Date.valueOf("2025-04-12"));
			dao.insert(cart1);//インサート

			cart.setUserId("user1");

			int result = dao.delete("user1", 1);

			assertEquals(1, result);

			List<ItemsInCartDTO> cartList = dao.findById("user1");

			assertNotNull(cartList);
			assertEquals(0, cartList.size());

			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}
}
