package jp.co.sars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sars.dto.PurchaseDetailsDTO;

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

					dto = mapRow(rs);
					return dto;
				}
			}
		}
		return null;
	}

	// 注文詳細IDの値のデータを削除
	public int detailDelete(int purchaseDetailId) throws SQLException {
		String sql = "DELETE FROM purchase_details WHERE purchase_detail_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseDetailId);
			return ps.executeUpdate();
		}
	}

	// 同じ注文IDの値を一括削除
	public int purchasDelete(int purchaseId) throws SQLException {
		String sql = "DELETE FROM purchase_details WHERE purchase_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);
			return ps.executeUpdate();
		}
	}

	//注文詳細テーブルへ追加
	public int insert(PurchaseDetailsDTO purchaseDetails) throws SQLException {
		String sql = "INSERT INTO public.purchase_details(\n"
				+ "            purchase_id, item_id, amount)\n"
				+ "    VALUES (?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, purchaseDetails.getPurchaseId());//注文ID
			ps.setInt(2, purchaseDetails.getItemId());//商品ID
			ps.setInt(3, purchaseDetails.getAmount());//数量

			int result = 0;
			result = ps.executeUpdate();

			//取得した注文IDを取得する
			ResultSet rs = ps.getGeneratedKeys();
			int purchaseDetailsId = -1;

			if (rs.next()) {

				purchaseDetailsId = rs.getInt("purchase_detail_id");

				purchaseDetails.setPurchaseDetailId(purchaseDetailsId);
			}

			return result;
		}

	}

	private PurchaseDetailsDTO mapRow(ResultSet rs) throws SQLException {
		PurchaseDetailsDTO purchaseDetails = new PurchaseDetailsDTO();
		purchaseDetails.setPurchaseDetailId(rs.getInt("purchase_detail_id"));
		purchaseDetails.setPurchaseId(rs.getInt("purchase_id"));
		purchaseDetails.setItemId(rs.getInt("item_id"));
		purchaseDetails.setAmount(rs.getInt("amount"));

		return purchaseDetails;
	}
}
