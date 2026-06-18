package jp.co.sars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.dto.PurchasesDTO;

public class PurchasesDAO {
	private Connection con;

	public PurchasesDAO(Connection con) {
		this.con = con;
	}

	//ユーザーIDで表示(最新の注文から順に表示)
	public List<PurchasesDTO> findByUser(String purchasedUser) throws SQLException {
		String sql = "SELECT p.purchase_id, p.purchased_user, p.purchased_date, p.destination, p.cancel, "
				+ "       d.amount, i.item_id, i.name, i.color, i.manufacturer, i.price "
				+ "FROM purchases p "
				+ "INNER JOIN purchase_details d ON p.purchase_id = d.purchase_id "
				+ "INNER JOIN items i ON d.item_id = i.item_id "
				+ "WHERE p.purchased_user = ? ORDER BY p.purchase_id DESC";

		List<PurchasesDTO> purchaseList = new ArrayList<>();
		PurchasesDTO currentPurchases = null;

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, purchasedUser);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					int purchaseId = rs.getInt("purchase_id");
					//ループして最初の一件目、または注文IDが変わったとき
					if (currentPurchases == null || currentPurchases.getPurchaseId() != purchaseId) {

						currentPurchases = new PurchasesDTO();

						currentPurchases.setPurchaseId(purchaseId);
						currentPurchases.setPurchasedUser(rs.getString("purchased_user"));
						currentPurchases.setPurchasedDate(rs.getDate("purchased_date"));
						currentPurchases.setDestination(rs.getString("destination"));
						currentPurchases.setCancel(rs.getBoolean("cancel"));

						currentPurchases.setList(new ArrayList<PurchaseDetailsDTO>());

						//リストに追加
						purchaseList.add(currentPurchases);

					}

					//details
					PurchaseDetailsDTO details = new PurchaseDetailsDTO();
					details.setAmount(rs.getInt("amount"));

					//items
					ItemsDTO items = new ItemsDTO();
					items.setItemId(rs.getInt("item_id"));
					items.setName(rs.getString("name"));
					items.setManufacturer(rs.getString("manufacturer"));
					items.setColor(rs.getString("color"));
					items.setPrice(rs.getInt("price"));

					details.setItems(items);

					//親のリストに詳細を追加する
					currentPurchases.getList().add(details);
				}
			}
		}
		return purchaseList;
	}

	//注文IDで表示(登録した順に表示)
	public List<PurchasesDTO> findByPurchaseId(int purchaseId) throws SQLException {
		String sql = "select p.purchase_id,p.purchased_user,p.purchased_date,p.destination,p.cancel,d.amount,i.item_id,i.name,i.color,i.manufacturer,i.price from purchases p inner join purchase_details d on p.purchase_id = d.purchase_id inner join items i on d.item_id = i.item_id \n"
				+ "where p.purchase_id = ? ORDER BY d.purchase_detail_id ASC";

		List<PurchasesDTO> purchaseList = new ArrayList<>();
		PurchasesDTO currentPurchases = null;

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					//ループして最初の一件目、または注文IDが変わったとき
					if (currentPurchases == null || currentPurchases.getPurchaseId() != purchaseId) {

						currentPurchases = new PurchasesDTO();

						currentPurchases.setPurchaseId(purchaseId);
						currentPurchases.setPurchasedUser(rs.getString("purchased_user"));
						currentPurchases.setPurchasedDate(rs.getDate("purchased_date"));
						currentPurchases.setDestination(rs.getString("destination"));
						currentPurchases.setCancel(rs.getBoolean("cancel"));

						currentPurchases.setList(new ArrayList<PurchaseDetailsDTO>());

						purchaseList.add(currentPurchases);
					}

					PurchaseDetailsDTO details = new PurchaseDetailsDTO();
					details.setAmount(rs.getInt("amount"));

					ItemsDTO items = new ItemsDTO();
					items.setItemId(rs.getInt("item_id"));
					items.setName(rs.getString("name"));
					items.setManufacturer(rs.getString("manufacturer"));
					items.setColor(rs.getString("color"));
					items.setPrice(rs.getInt("price"));

					details.setItems(items);

					currentPurchases.getList().add(details);
				}
			}
		}
		return purchaseList;
	}

	//インサート
	public int insert(PurchasesDTO dto) throws SQLException {

		String sql = "INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) \n"
				+ "VALUES (?,?,?,?)";

		//自動で注文IDを生成する
		try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, dto.getPurchasedUser());
			ps.setDate(2, dto.getPurchasedDate());
			ps.setString(3, dto.getDestination());
			ps.setBoolean(4, dto.isCancel());

			int result = ps.executeUpdate();
			;

			//取得した注文IDを取得する
			ResultSet rs = ps.getGeneratedKeys();
			int purchaseId = -1;

			if (rs.next()) {

				purchaseId = rs.getInt("purchase_id");

				dto.setPurchaseId(purchaseId);
			}

			return result;
		}

	}

	//キャンセルされたらtrueにする処理
	public int cancelUpdate(int purchaseId) throws SQLException {

		String sql = "UPDATE purchases SET cancel = true WHERE purchase_id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			return ps.executeUpdate();
		}
	}

}