package jp.co.sars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sars.dto.ItemsDTO;

public class ItemsDAO extends ItemsDTO {
	private Connection con;

	public ItemsDAO(Connection con) {
		this.con = con;
	}

	//全表示
	public List<ItemsDTO> findAll() throws SQLException {
		String spl = "SELECT item_id, name, manufacturer, category_id, color, price, stock, recommended\n"
				+ "	FROM items";

		List<ItemsDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(spl);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				ItemsDTO item = mapRow(rs);
				list.add(item);

			}
		}

		return list;

	}

	///カテゴリー検索
	public ItemsDTO findById(int category_id) throws SQLException {
		String sql = "select *from items where category_id=?";

		ItemsDTO item = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, category_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					item = mapRow(rs);
				}
			}
		}
		return item;
	}

	///テスト用（item_idで検索）
	public ItemsDTO findById1(int item_id) throws SQLException {
		String sql = "select *from items where item_id=?";

		ItemsDTO item = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, item_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					item = mapRow(rs);
				}
			}
		}
		return item;
	}

	//在庫を減らす
	public int update(ItemsDTO item) throws SQLException {
		String sql = "update public.items set stock = ? where item_id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, item.getStock());
			ps.setInt(2, item.getItem_id());
			return ps.executeUpdate();
		}

	}

	///キーワード検索
	public List<ItemsDTO> findAm(String keyword) throws SQLException {
		String sql = "select *from items where name like ? order by item_id ASC ";

		List<ItemsDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + keyword + "%");
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ItemsDTO items = mapRow(rs);
					list.add(items);
				}
			}
		}
		return list;
	}

	private ItemsDTO mapRow(ResultSet rs) throws SQLException {
		ItemsDTO item = new ItemsDTO();
		item.setItem_id(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setManufacturer(rs.getString("manufacturer"));
		item.setCategory_id(rs.getInt("category_id"));
		item.setColor(rs.getString("color"));
		item.setPrice(rs.getInt("price"));
		item.setStock(rs.getInt("stock"));
		item.setRecommended(rs.getBoolean("recommended"));

		return item;
	}
}
