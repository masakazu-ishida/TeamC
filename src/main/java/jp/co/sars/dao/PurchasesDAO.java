package jp.co.sars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sars.dto.PurchasesDTO;

public class PurchasesDAO {
	private Connection con;

	public PurchasesDAO(Connection con) {
		this.con = con;
	}

	//ユーザーIDから情報を表示
	public PurchasesDTO findById(String userId) throws SQLException {
		String sql = "SELECT * FROM purchases WHERE purchased_user = ?";
		PurchasesDTO user = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					user = mapRow(rs);
					return user;
				}
			}
		}
		return null;
	}

	public int insert(PurchasesDTO dto) throws SQLException {

		String sql = "INSERT INTO purchases (purchased_user, purchased_date, destination, cancel) \n"
				+ "VALUES (?,?,?,?)";

		//自動で注文IDを生成する
		try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, dto.getPurchasedUser());
			ps.setDate(2, dto.getPurchasedDate());
			ps.setString(3, dto.getDestination());
			ps.setBoolean(4, dto.isCancel());

			ps.executeUpdate();

			//取得した注文IDを取得する
			ResultSet rs = ps.getGeneratedKeys();
			int purchaseId = -1;

			if (rs.next()) {

				purchaseId = rs.getInt("purchase_id");

				dto.setPurchaseId(purchaseId);
			}

			return purchaseId;
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

	private PurchasesDTO mapRow(ResultSet rs) throws SQLException {

		//purchases
		PurchasesDTO purchases = new PurchasesDTO();
		purchases.setPurchaseId(rs.getInt("purchase_id"));
		purchases.setPurchasedUser(rs.getString("purchased_user"));
		purchases.setPurchasedDate(rs.getDate("purchased_date"));
		purchases.setDestination(rs.getString("destination"));
		purchases.setCancel(rs.getBoolean("cancel"));

		return purchases;

	}

}
