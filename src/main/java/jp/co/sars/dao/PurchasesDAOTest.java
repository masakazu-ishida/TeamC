package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	void testfindById1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO dto = dao.findById("user1");

			assertNotNull(dto);
			assertEquals(1, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-15", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(false, dto.isCancel());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//主キーが存在しない場合のテスト

	@Test
	void testFindById2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			PurchasesDTO user = dao.findById("user999");

			assertNull(user);

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
			dto.setPurchasedDate(java.sql.Date.valueOf("2026-06-15"));
			dto.setDestination(null);
			dto.setCancel(false);

			int result = dao.insert(dto);

			assertEquals(4, result);

			dto = dao.findById("user10");

			assertNotNull(dto);
			assertEquals(4, dto.getPurchaseId());
			assertEquals("user10", dto.getPurchasedUser());
			assertEquals("2026-06-15", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(false, dto.isCancel());

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

			dto = dao.findById("user1");

			assertNotNull(dto);
			assertEquals(1, dto.getPurchaseId());
			assertEquals("user1", dto.getPurchasedUser());
			assertEquals("2026-06-15", dto.getPurchasedDate().toString());
			assertEquals(null, dto.getDestination());
			assertEquals(true, dto.isCancel());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}
}
