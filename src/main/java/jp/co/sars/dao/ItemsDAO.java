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
	public List<ItemsDTO> findByCategory(int categoryId) throws SQLException {
		String sql = "select  items.item_id ,items.name,items.manufacturer,items.category_id,items.color,items.price,items.stock,items.recommended from \n"
				+ "items where category_id=? order by item_id asc ";

		List<ItemsDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, categoryId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ItemsDTO items = mapRow(rs);
					list.add(items);
				}
			}
		}
		return list;
	}

	///テスト用（item_idで検索）
	public ItemsDTO findById(int itemId) throws SQLException {
		String sql = "select  items.item_id ,items.name,items.manufacturer,items.category_id,items.color,items.price,items.stock,items.recommended"
				+ " from items where item_id=?";

		ItemsDTO item = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, itemId);
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
			ps.setInt(2, item.getItemId());
			return ps.executeUpdate();
		}

	}

	///キーワード検索
	public List<ItemsDTO> findAm(String keyword) throws SQLException {
		String sql = "select items.item_id ,items.name,items.manufacturer,items.category_id,items.color,items.price,items.stock,items.recommended\n"
				+ "from items where name like ? order by item_id asc ";

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

	//キーワードカテゴリー検索の両方
	public List<ItemsDTO> findAll2(String keyword, int categoryId) throws SQLException {

		String sql = "select items.item_id ,items.name,items.manufacturer,items.category_id,items.color,items.price,items.stock,items.recommended\n"
				+ " from items where name like ? and items.category_id=? order by item_id asc";

		List<ItemsDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, categoryId);
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
		item.setItemId(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setManufacturer(rs.getString("manufacturer"));
		item.setCategoryId(rs.getInt("category_id"));
		item.setColor(rs.getString("color"));
		item.setPrice(rs.getInt("price"));
		item.setStock(rs.getInt("stock"));
		item.setRecommended(rs.getBoolean("recommended"));

		return item;
	}
}
