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

	public List<PurchasesDTO> findByCond(String adminId, Date startDate, Date endDate) throws SQLException {
		List<PurchasesDTO> purchases = new ArrayList<>();
		String sql = "SELECT purchases.purchace_id, purchased_user, purchases.purchased_date, items.\"name\", items.color, items.manufacturer, items.price, purchase_details.amount, purchases.destination  "
				+ " FROM purchases\n"
				+ "	INNER JOIN purchase_details ON purchase_details.purchase_id = purchases.purchase_id\n"
				+ "	INNER JOIN users ON users.\"name\" = purchases.purchased_user\n"
				+ "	INNER JOIN items ON items.item_id = purchase_details.item_id\n"
				+ "	WHERE purchased_date BETWEEN ? AND ? \n"
				+ " AND users.user_id Like(?)"
				+ "ORDER BY purchases.purchased_date DESC;"
				+ "ORDER BY purchases.purchase_id";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDate(1, new java.sql.Date(startDate.getTime()));
			ps.setDate(2, new java.sql.Date(endDate.getTime()));
			ps.setString(3, adminId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int purchaseId = rs.getInt("purchase_id");
				PurchasesDTO purchase = null;

				boolean flg = true;
				for (PurchasesDTO dto : purchases) {
					if (dto.getPurchaseId() == purchaseId) {
						purchase = dto;
						flg = false;
						break;
					}
				}

				if (flg) {
					purchase = new PurchasesDTO();
					purchase.setPurchaseId(purchaseId);
					purchase.setPurchasedUser(rs.getString("user_id"));
					purchase.setPurchasedDate(rs.getDate("purchased_date"));
				}

				PurchaseDetailsDTO detail = new PurchaseDetailsDTO();

				List<PurchaseDetailsDTO> details = purchase.getPurchaseDetails();

				if (details == null) {
					details = new ArrayList<>();
				}

				ItemsDTO item = new ItemsDTO();

				item.setName(rs.getString("name"));
				item.setColor(rs.getString("color"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setPrice(rs.getInt("price"));

				detail.setItem(item);
				detail.setAmount(rs.getInt("amount"));

				details.add(detail);
				purchase.setPurchaseDetails(details);

			}
		}

		return purchases;
	}

}
