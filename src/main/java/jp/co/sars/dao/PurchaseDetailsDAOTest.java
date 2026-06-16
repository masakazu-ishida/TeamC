package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.ConnectionUtil;
import jp.co.sars.util.TestBase;

public class PurchaseDetailsDAOTest extends TestBase {

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

	//注文詳細IDで表示する
	@Test
	void testFindById() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			PurchaseDetailsDTO dto = dao.findById(1);

			assertNotNull(dto);

			assertEquals(1, dto.getPurchaseDetailId());
			assertEquals(1, dto.getPurchaseId());
			assertEquals(7, dto.getItemId());
			assertEquals(4, dto.getAmount());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//注文詳細IDで検索した結果Nullの場合
	@Test
	void testFindByIdNull() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			PurchaseDetailsDTO dto = dao.findById(10);

			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testInsert() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			PurchaseDetailsDTO dto = new PurchaseDetailsDTO();
			dto.setPurchaseId(1);
			dto.setItemId(1);
			dto.setAmount(1);

			int result = dao.insert(dto);
			int PurchaseDetailId = dto.getPurchaseDetailId();

			dto = dao.findById(PurchaseDetailId);

			assertNotNull(dto);
			assertEquals(1, result);
			assertEquals(9, dto.getPurchaseDetailId());
			assertEquals(1, dto.getPurchaseId());
			assertEquals(1, dto.getItemId());
			assertEquals(1, dto.getAmount());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testDetailDelete() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			PurchaseDetailsDTO dto = new PurchaseDetailsDTO();
			dto.setPurchaseId(1);
			dto.setItemId(1);
			dto.setAmount(1);

			dao.insert(dto);
			int purchaseDetailsId = dto.getPurchaseDetailId();

			int result = dao.detailDelete(purchaseDetailsId);

			assertEquals(1, result);

			dto = dao.findById(purchaseDetailsId);

			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testPurchasDelete() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO PurDao = new PurchasesDAO(conn);
			PurchasesDTO PurDto = new PurchasesDTO();
			PurDto.setPurchasedUser("user4");
			PurDto.setPurchasedDate(java.sql.Date.valueOf("2026-06-15"));
			PurDto.setDestination(null);
			PurDto.setCancel(false);

			int purchaseId = PurDao.insert(PurDto);

			PurchaseDetailsDAO dao = new PurchaseDetailsDAO(conn);
			PurchaseDetailsDTO dto = new PurchaseDetailsDTO();
			dto.setPurchaseId(purchaseId);
			dto.setItemId(1);
			dto.setAmount(1);

			dao.insert(dto);
			int purchaseDetailsId = dto.getPurchaseDetailId();

			int result = dao.purchasDelete(purchaseId);

			assertEquals(1, result);

			dto = dao.findById(purchaseDetailsId);

			assertNull(dto);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}
}
