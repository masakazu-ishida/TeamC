package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dao.PurchaseDetailsDAO;
import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.dto.PurchaseDetailsDTO;
import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.util.ConnectionUtil;

class PurchasesDAOTest {
	@BeforeEach
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

	// findByItemIdのテスト
	// 何も入力せずに検索を行った場合すべての結果を取得できるかのテスト
	@Test
	void testFindByNullId() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn);
				List<PurchasesDTO> list = dao.findByUserId(null);
				assertEquals(3, list.size());
				PurchasesDTO purchaces = list.get(0);
				assertEquals(1, purchaces.getPurchaseId());
				assertEquals("user", purchaces.getPurchasedUser());
				assertEquals("2020-10-20", purchaces.getPurchasedDate().toString());

				List<PurchaseDetailsDTO> purchaseDetails = purchaces.getPurchaseDetails();
				assertEquals(2, purchaseDetails.size());

				PurchaseDetailsDTO purchaseDetailsDTO = purchaseDetails.get(0);
				ItemsDTO item = purchaseDetailsDTO.getItem();
				assertEquals("麦わら帽子", item.getName());
				assertEquals("黄色", item.getColor());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(4980, item.getPrice());

				assertEquals(1, purchaseDetailsDTO.getAmount());
				assertEquals("東京都", purchaces.getDestination());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	// 対象のUserIdから検索が出来るかのテスト
	@Test
	void testFindByUserIdIsCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn);
				List<PurchasesDTO> list = dao.findByUserId("user");
				assertEquals(2, list.size());
				PurchasesDTO purchaces = list.get(0);
				assertEquals(1, purchaces.getPurchaseId());
				assertEquals("user", purchaces.getPurchasedUser());
				assertEquals("2020-10-20", purchaces.getPurchasedDate().toString());

				List<PurchaseDetailsDTO> purchaseDetails = purchaces.getPurchaseDetails();
				assertEquals(2, purchaseDetails.size());

				PurchaseDetailsDTO purchaseDetailsDTO = purchaseDetails.get(0);
				ItemsDTO item = purchaseDetailsDTO.getItem();
				assertEquals("麦わら帽子", item.getName());
				assertEquals("黄色", item.getColor());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(4980, item.getPrice());

				assertEquals(1, purchaseDetailsDTO.getAmount());
				assertEquals("東京都", purchaces.getDestination());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	// 存在しないUserIdで検索した場合リストの数が0になるかどうかのテスト
	@Test
	void testFindByUserIdIsNotCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn);
				List<PurchasesDTO> list = dao.findByUserId("一致しないID名");
				assertEquals(0, list.size());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	// findByPurchaseIdのテスト
	// 存在するpurchaseIdで検索した場合正しく結果を取得出来るかのテスト
	@Test
	void testFindByPurchaseIdIsCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn); //DAOの初期化
				PurchasesDTO purchase = dao.findByPurchaseId(1); //メソッドを実行しDTOを取得

				assertNotNull(purchase); //DTOが正しく取得できているか(Nullでないか)のテスト

				// PurchasesDTOのフィールドが正しくセットされているかのテスト
				assertEquals(1, purchase.getPurchaseId());
				assertEquals("user", purchase.getPurchasedUser());
				assertEquals("2020-10-20", purchase.getPurchasedDate().toString());

				// PurchaseDetailsDTOのリストを取得
				List<PurchaseDetailsDTO> details = purchase.getPurchaseDetails();
				assertEquals(2, details.size()); // Detailsの件数が正しいかテスト

				// リストの中からDetailを一つ取り出し中身を確認する
				PurchaseDetailsDTO detail = details.get(0);

				// DetailからItemを取り出し中身を確認
				ItemsDTO item = detail.getItem();
				assertEquals("麦わら帽子", item.getName());
				assertEquals("黄色", item.getColor());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(4980, item.getPrice());

				assertEquals(1, detail.getAmount());
				assertEquals("東京都", purchase.getDestination());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//存在しないpurchaseIdで検索した場合nullで取得出来るかのテスト
	@Test
	void testFindByPurchaseIdIsNotCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn); //DAOの初期化
				PurchasesDTO purchase = dao.findByPurchaseId(99999); //メソッドを実行しDTOを取得

				assertNull(purchase); //DTOがNullで取得出来ているかのテスト
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//存在するpurchaseIdでキャンセル処理を行った結果検索で表示されなくなるかのテスト
	@Test
	void testCanselIsCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn); //DAOの初期化
				dao.cancel(1);//キャンセル処理を実行

				List<PurchasesDTO> purchasesDTOs = dao.findByUserId("user"); //userの注文(本来は2つだが1つをキャンセルした)を取得
				assertEquals(1, purchasesDTOs.size()); //取得件数が減っているかどうか確認

				PurchasesDTO purchasesDTO = dao.findByPurchaseId(1); //先ほどキャンセルした注文を取得
				assertNull(purchasesDTO); //取得出来ていないことを確認

			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//存在しないpurchaseIdでキャンセル処理を行った結果何も変わらないことを確認
	@Test
	void testCanselIsNotCorrect() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO dao = new PurchasesDAO(conn); //DAOの初期化
				dao.cancel(9999);//キャンセル処理を実行

				List<PurchasesDTO> purchasesDTOs = dao.findByUserId(null); //全件検索
				assertEquals(3, purchasesDTOs.size()); //取得件数が変わっていないかの確認
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	//注文を新規追加出来るのかテスト
	@Test
	void testCreate() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try {
				PurchasesDAO purchasesDAO = new PurchasesDAO(conn); //DAOの初期化
				int purchaceId = purchasesDAO.create("user2", "アメリカ");

				// 注文詳細のデータを追加
				PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
				purchaseDetailsDAO.create(purchaceId, 5, 1);

				List<PurchasesDTO> purchasesDTOs = purchasesDAO.findByUserId("user2"); //検索
				assertEquals(2, purchasesDTOs.size());

				PurchasesDTO purchasesDTO = purchasesDTOs.get(0);

				assertEquals(purchaceId, purchasesDTO.getPurchaseId());
				assertEquals("user2", purchasesDTO.getPurchasedUser());

				java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dateNow = new Date();

				try {
					dateNow = dateFormat.parse("2025-06-20");
				} catch (ParseException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				assertEquals(dateNow, purchasesDTO.getPurchasedDate());
				assertEquals("アメリカ", purchasesDTO.getDestination());
				assertFalse(purchasesDTO.isCancel());
			} catch (Exception e) {
				e.printStackTrace();
				fail(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}
}
