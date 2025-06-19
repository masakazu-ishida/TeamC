package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseDetailsDAO extends BaseDAO {
	public PurchaseDetailsDAO(Connection conn) {
		super(conn);
	}

	public void create(int purchaseId, int item_id, int amount) throws SQLException {
		String sql = "INSERT INTO purchase_details(purchase_id, item_id, amount) VALUES(?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);
			ps.setInt(2, item_id);
			ps.setInt(3, amount);

			ps.executeUpdate();
		}
	}
}
