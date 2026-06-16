package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.ConnectionUtil;
import jp.co.sars.util.TestBase;

class PurchasesDAOTest extends TestBase {

	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

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

	//主キーが存在する場合のテスト
	@Test
	void testfindByUser1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> list = dao.findByUser("user1");

			PurchasesDTO dto = list.get(0);

			assertNotNull(dto);
			assertEquals(1, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-16", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(false, dto.isCancel());

			//PurchaseDetails内のデータ
			assertEquals(4, dto.getList().get(0).getAmount());
			assertEquals(7, dto.getList().get(0).getItems().getItemId());

			//items内のデータ
			assertEquals("ハンチング帽", dto.getList().get(0).getItems().getName());
			assertEquals("黄色", dto.getList().get(0).getItems().getColor());
			assertEquals("日本帽子製造", dto.getList().get(0).getItems().getManufacturer());
			assertEquals(1980, dto.getList().get(0).getItems().getPrice());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//主キーが存在しない場合のテスト

	@Test
	void testfindByUser2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> user = dao.findByUser("user999");

			assertNotNull(user);
			assertEquals(0, user.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//主キーが存在する場合のテスト
	@Test
	void testfindByPurchaseId1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> list = dao.findByPurchaseId(1);

			PurchasesDTO dto = list.get(0);

			assertNotNull(dto);
			assertEquals(1, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-16", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(false, dto.isCancel());

			//PurchaseDetails内のデータ
			assertEquals(4, dto.getList().get(0).getAmount());
			assertEquals(7, dto.getList().get(0).getItems().getItemId());

			//items内のデータ
			assertEquals("ハンチング帽", dto.getList().get(0).getItems().getName());
			assertEquals("黄色", dto.getList().get(0).getItems().getColor());
			assertEquals("日本帽子製造", dto.getList().get(0).getItems().getManufacturer());
			assertEquals(1980, dto.getList().get(0).getItems().getPrice());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//主キーが存在しない場合のテスト
	@Test
	void testfindByPurchaseId2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> id = dao.findByPurchaseId(999);

			assertNotNull(id);
			assertEquals(0, id.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//新しく会員テーブルにユーザを登録してからテストする
	@Test
	void testinsert() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {

			conn.setAutoCommit(false);

			String sqlUser = "INSERT INTO users (user_id, password, name, address) VALUES ('user10', 'userpass123', '鳥取十郎', '鳥取県鳥取市xxx-xxx')";
			try (PreparedStatement psUser = conn.prepareStatement(sqlUser)) {
				psUser.executeUpdate();
			}

			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = new PurchasesDTO();
			dto.setPurchasedUser("user10");
			dto.setPurchasedDate(java.sql.Date.valueOf("2026-06-16"));
			dto.setDestination(null);
			dto.setCancel(false);

			int result = dao.insert(dto);

			assertEquals(4, result);

			//データを入れただけなのでこのテーブルのデータだけ表示
			String sqlCheck = "SELECT * FROM purchases WHERE purchased_user = ?";
			try (PreparedStatement Check = conn.prepareStatement(sqlCheck)) {
				Check.setString(1, "user10");
				try (ResultSet rsCheck = Check.executeQuery()) {

					assertNotNull(dto);
					assertEquals(4, dto.getPurchaseId());
					assertEquals("user10", dto.getPurchasedUser());
					assertEquals("2026-06-16", dto.getPurchasedDate().toString());
					assertEquals(null, dto.getDestination());
					assertEquals(false, dto.isCancel());

				}
			}

			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testcancelUpdate() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = new PurchasesDTO();

			int purchaseId = 1;

			int result = dao.cancelUpdate(purchaseId);

			assertEquals(1, result);

			List<PurchasesDTO> list = dao.findByUser("user1");

			dto = list.get(0);

			assertNotNull(dto);
			assertEquals(1, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-16", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(true, dto.isCancel());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}
}
