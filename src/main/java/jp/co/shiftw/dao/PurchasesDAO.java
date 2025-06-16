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
		String sql = "SELECT purchases.purchase_id, purchased_user, purchases.purchased_date, items.\"name\", items.color, items.manufacturer, items.price, purchase_details.amount, purchases.destination  "
				+ " FROM purchases\n"
				+ "	INNER JOIN purchase_details ON purchase_details.purchase_id = purchases.purchase_id\n"
				+ "	INNER JOIN users ON users.user_id = purchases.purchased_user\n"
				+ "	INNER JOIN items ON items.item_id = purchase_details.item_id\n"
				+ "	WHERE users.user_id LIKE ? \n"
				+ "	OR purchased_date BETWEEN ? AND ? \n"
				+ "	ORDER BY purchases.purchased_date DESC,"
				+ "purchases.purchase_id ASC";
		String pattern = adminId;
		if (adminId == null) {
			pattern = "%";
		}

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, pattern);

			if (startDate == null) {
				ps.setDate(2, null);
			} else {
				ps.setDate(2, new java.sql.Date(startDate.getTime()));
			}

			if (endDate == null) {
				ps.setDate(3, null);
			} else {
				ps.setDate(3, new java.sql.Date(endDate.getTime()));
			}

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
					purchase.setPurchasedUser(rs.getString("purchased_user"));
					purchase.setPurchasedDate(rs.getDate("purchased_date"));
					purchase.setDestination(rs.getString("destination"));
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

				purchases.add(purchase);

			}
		}

		return purchases;
	}

}
