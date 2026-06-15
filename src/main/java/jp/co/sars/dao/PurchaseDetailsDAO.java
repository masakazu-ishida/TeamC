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

public class PurchaseDetailsDAO {
	private Connection con;

	public PurchaseDetailsDAO(Connection con) {
		this.con = con;
	}

	//注文詳細IDから情報を表示
	public PurchaseDetailsDTO findById(int purchaseDetailId) throws SQLException {
		String sql = "select purchase_detail_id,purchase_id,item_id,amount from purchase_details where purchase_detail_id = ?";
		PurchaseDetailsDTO dto = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseDetailId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					dto = mapRow(rs);
					return dto;
				}
			}
		}
		return null;
	}

	// 注文詳細IDの値のデータを削除
	public int DetailDelete(String purchaseDetailId) throws SQLException {
		String sql = "DELETE FROM purchase_details WHERE purchase_detail_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, purchaseDetailId);
			return ps.executeUpdate();
		}
	}

	// 同じ注文IDの値を一括削除
	public int purchasDelete(String purchaseId) throws SQLException {
		String sql = "DELETE FROM purchase_details WHERE purchase_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, purchaseId);
			return ps.executeUpdate();
		}
	}

	//注文詳細テーブルへ追加
	public int insert(PurchaseDetailsDTO purchaseDetails) throws SQLException {
		String sql = "INSERT INTO public.purchase_details(\n"
				+ "            purchase_id, item_id, amount)\n"
				+ "    VALUES ('?', '?', '?')";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseDetails.getPurchaseId());//注文ID
			ps.setInt(2, purchaseDetails.getItemId());//商品ID
			ps.setInt(3, purchaseDetails.getAmount());//数量
			return ps.executeUpdate();
		}

	}

	//注文者（ユーザID）で一覧を取得する
	public List<PurchaseDetailsDTO> findByPurchasedUser(String purchasedUser) throws SQLException {
		String sql = "select purchase_detail_id,pd.purchase_id,pd.item_id,p.purchased_user,\n"
				+ "purchased_date, name,color,manufacturer,price,amount,destination,cancel\n"
				+ "from purchase_details pd \n"
				+ "inner join purchases p on\n"
				+ "p.purchase_id = pd.purchase_id\n"
				+ "inner join items i on\n"
				+ "pd.item_id = i.item_id\n"
				+ "where p.purchased_user = ?\n"
				+ "order by pd.purchase_detail_id desc";
		List<PurchaseDetailsDTO> purchaseList = new ArrayList<>();//リストで取得
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, purchasedUser);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					//mapRowで1行分のデータを作る
					PurchaseDetailsDTO purchaseDetails = mapRow(rs);

					purchaseList.add(purchaseDetails);//リストに追加

				}
			}
		}
		return purchaseList;
	}

	//注文IDで一覧を取得する
	public List<PurchaseDetailsDTO> findByPurchaseId(int purchaseId) throws SQLException {
		String sql = "select purchase_detail_id,pd.purchase_id,pd.item_id,p.purchased_user,\n"
				+ "purchased_date, name,color,manufacturer,price,amount,destination,cancel\n"
				+ "from purchase_details pd \n"
				+ "inner join purchases p on\n"
				+ "p.purchase_id = pd.purchase_id\n"
				+ "inner join items i on\n"
				+ "pd.item_id = i.item_id\n"
				+ "where pd.purchase_id = ?\n"
				+ "order by pd.purchase_detail_id desc";
		List<PurchaseDetailsDTO> purchaseList = new ArrayList<>();//リストで取得
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, purchaseId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					//mapRowで1行分のデータを作る
					PurchaseDetailsDTO purchaseDetails = mapRow(rs);

					purchaseList.add(purchaseDetails);//リストに追加

				}
			}
		}
		return purchaseList;
	}

	private PurchaseDetailsDTO mapRow(ResultSet rs) throws SQLException {
		PurchaseDetailsDTO purchaseDetails = new PurchaseDetailsDTO();
		purchaseDetails.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
		purchaseDetails.setPurchaseId(rs.getInt("purchase_id"));
		purchaseDetails.setItemId(rs.getInt("item_id"));
		purchaseDetails.setAmount(rs.getInt("amount"));

		//結合先のデータ
		PurchasesDTO purchases = new PurchasesDTO();
		purchases.setPurchaseId(rs.getInt("purchase_id"));
		purchases.setPurchasedUser(rs.getString("purchased_user"));
		purchases.setPurchasedDate(rs.getDate("purchased_date"));
		purchases.setDestination(rs.getString("destination"));
		purchases.setCancel(rs.getBoolean("cancel"));
		purchaseDetails.setPurchases(purchases);

		ItemsDTO items = new ItemsDTO();
		items.setItemId(rs.getInt("item_id"));
		items.setName(rs.getString("name"));
		items.setManufacturer(rs.getString("manufacturer"));
		items.setColor(rs.getString("color"));
		items.setPrice(rs.getInt("price"));
		purchaseDetails.setItems(items);

		return purchaseDetails;
	}
}
