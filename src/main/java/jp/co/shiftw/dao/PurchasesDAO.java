package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.dto.PurchaseDetailsDTO;
import jp.co.shiftw.dto.PurchasesDTO;

public class PurchasesDAO extends BaseDAO {

	public PurchasesDAO(Connection conn) {
		super(conn);
	}

	// purchaced_userをキーにPurchasesテーブルを検索
	public List<PurchasesDTO> findByUserId(String parchasedUser) throws SQLException {
		List<PurchasesDTO> purchases = new ArrayList<>();//purchase_idごとのpurchasesを格納するリスト
		String sql = "SELECT purchases.purchase_id, purchased_user, purchases.purchased_date, items.item_id, items.\"name\", items.color, items.manufacturer, items.price, purchase_details.amount, purchases.destination, purchase_details.purchase_detail_id  "
				+ " FROM purchases\n"
				+ "	INNER JOIN purchase_details ON purchase_details.purchase_id = purchases.purchase_id\n"
				+ "	INNER JOIN users ON users.user_id = purchases.purchased_user\n"
				+ "	INNER JOIN items ON items.item_id = purchase_details.item_id\n"
				+ "	WHERE purchases.purchased_user LIKE ? AND purchases.cancel = false \n"
				+ "	ORDER BY purchases.purchased_date DESC,"
				+ "purchases.purchase_id ASC";

		String pattern = parchasedUser;
		if (parchasedUser == null) {
			pattern = "%"; // 検索欄が空の場合は全件検索する
		}

		//SQL文の実行
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, pattern);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int purchaseId = rs.getInt("purchase_id");
				PurchasesDTO purchase = null;

				boolean flg = true;
				for (PurchasesDTO dto : purchases) {
					// 既に作成しているPurchasesDTOがあればそれを使用する
					if (dto.getPurchaseId() == purchaseId) {
						purchase = dto;
						flg = false;
						break;
					}
				}

				//既に作成しているPurchasesDTOがない場合は新規作成
				if (flg) {
					purchase = new PurchasesDTO();
					purchase.setPurchaseId(purchaseId);
					purchase.setPurchasedUser(rs.getString("purchased_user"));
					purchase.setPurchasedDate(rs.getDate("purchased_date"));
					purchase.setDestination(rs.getString("destination"));
				}

				// Purchasesに格納するPurchaseDetailsのリスト
				List<PurchaseDetailsDTO> details = purchase.getPurchaseDetails();

				// リストがまだ作られていない場合は新規作成する
				if (details == null) {
					details = new ArrayList<>();
				}

				ItemsDTO item = new ItemsDTO(); // PurchaseDetailsDTOに格納されるItem

				item.setItemId(rs.getInt("item_id"));
				item.setName(rs.getString("name"));
				item.setColor(rs.getString("color"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setPrice(rs.getInt("price"));

				PurchaseDetailsDTO detail = new PurchaseDetailsDTO();

				detail.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
				detail.setItem(item); // PurchaseDetailsDTOにItemsDTOを格納
				detail.setAmount(rs.getInt("amount"));

				details.add(detail); // List<PurchaseDetailsDTO>にdetailを入れる
				purchase.setPurchaseDetails(details); // PurchaseDTOにリストを入れる

				int index = purchases.indexOf(purchase);

				if (index != -1) {
					purchases.set(purchases.indexOf(purchase), purchase);
				} else {
					purchases.add(purchase); // List<PurchaseDTO>にpurchaseを入れる
				}
			}
		}

		return purchases;
	}

	// purchase_idをキーにPurchasesテーブルを検索
	public PurchasesDTO findByPurchaseId(int purchaseId) throws SQLException {
		PurchasesDTO purchase = null; // 戻り値(検索結果がない場合はnullで返される)
		List<PurchaseDetailsDTO> details = new ArrayList<>(); //複数あるPurchaseDetailsを格納するリスト

		String sql = "SELECT purchases.purchase_id, purchased_user, purchases.purchased_date, items.item_id, items.\"name\", items.color, items.manufacturer, items.price, purchase_details.amount, purchases.destination, purchase_details.purchase_detail_id  "
				+ " FROM purchases\n"
				+ "	INNER JOIN purchase_details ON purchase_details.purchase_id = purchases.purchase_id\n"
				+ "	INNER JOIN users ON users.user_id = purchases.purchased_user\n"
				+ "	INNER JOIN items ON items.item_id = purchase_details.item_id\n"
				+ "	WHERE purchases.purchase_id = ? AND purchases.cancel = false;"; //実行するSQL文
		int count = 0; //検索結果の件数

		// SQLの実行
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, purchaseId); // キーであるpurchaseIdをSQL文にセット

			ResultSet rs = ps.executeQuery(); //SQL文を実行しResultSetに入れる

			while (rs.next()) { //purchaseIdは1件のみだがpurchaseDetailsが複数あるのでwhile文を使用している
				count++; //検索件数を追加
				if (count == 1) { //最初の結果を取得した時にpurchaseの初期化を行う
					purchase = new PurchasesDTO(); // purchaseの初期化

					// purchaseのフィールドをセット
					purchase.setPurchaseId(purchaseId);
					purchase.setPurchasedUser(rs.getString("purchased_user"));
					purchase.setPurchasedDate(rs.getDate("purchased_date"));
					purchase.setDestination(rs.getString("destination"));
				}

				ItemsDTO item = new ItemsDTO(); //PurchaseDetailsDTOに格納されるItem

				// itemのフィールドをセット
				item.setItemId(rs.getInt("item_id"));
				item.setName(rs.getString("name"));
				item.setColor(rs.getString("color"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setPrice(rs.getInt("price"));

				PurchaseDetailsDTO detail = new PurchaseDetailsDTO(); //PurchasesDTOに格納されるPurchaseDetails 

				// detailのフィールドをセット
				detail.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
				detail.setItem(item); // PurchaseDetailsDTOにItemsDTOを格納
				detail.setAmount(rs.getInt("amount"));

				details.add(detail); // List<PurchaseDetailsDTO>にdetailを入れる
			}
		}

		if (count != 0) { //検索結果があった場合はPurchaseDetailsDTOのリストをPurchasesにセット
			purchase.setPurchaseDetails(details);
		}

		return purchase;
	}

	//注文を追加する
	public int create(String purchasedUser, String destination) throws SQLException {
		String sql = "INSERT INTO purchases(purchased_user, purchased_date, destination, cancel) VALUES(?, ?, ?, false)";
		Date dateNow = new Date();
		int purchaseId = -1;

		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, purchasedUser);
			ps.setDate(2, new java.sql.Date(dateNow.getTime()));
			ps.setString(3, destination);

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				purchaseId = rs.getInt(1);
			}
		}

		return purchaseId;
	}

	// 特定の注文IDの注文をキャンセルにする
	public void cancel(int purchaseId) throws SQLException {
		String sql = "UPDATE purchases SET cancel = true WHERE purchase_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);
			ps.executeUpdate();
		}
	}
}
