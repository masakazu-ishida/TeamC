package jp.co.sars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.dto.ItemsInCartDTO;

public class ItemsInCartDAO {
	private Connection con;

	public ItemsInCartDAO(Connection con) {
		this.con = con;
	}

	//カートに追加
	public int insert(ItemsInCartDTO cart) throws SQLException {
		String sql = "INSERT INTO public.items_in_cart(\n"
				+ "	user_id, item_id, amount,booked_date)\n"
				+ "	VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, cart.getUserId());//会員ID
			ps.setInt(2, cart.getItemId());//商品ID
			ps.setInt(3, cart.getAmount());//数量
			ps.setDate(4, cart.getBookedDate());//日付
			return ps.executeUpdate();
		}

	}

	//数量の更新
	public int update(ItemsInCartDTO cart) throws SQLException {
		String sql = "UPDATE public.items_in_cart SET amount = ? WHERE user_id = ? AND item_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cart.getAmount());//数量
			ps.setString(2, cart.getUserId());//会員ID
			ps.setInt(3, cart.getItemId());//商品ID
			return ps.executeUpdate();//指定して更新をする(サーブレット側で)
		}
	}

	//カートから削除
	public int delete(String userId, int itemId) throws SQLException {
		String sql = "DELETE FROM items_in_cart WHERE user_id = ?  AND item_id = ?;";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);//会員ID
			ps.setInt(2, itemId);//商品ID
			return ps.executeUpdate();//カートから商品を削除
		}
	}

	//会員IDで検索して商品IDが一致するものを内部結合して表示
	public List<ItemsInCartDTO> findById(String userId) throws SQLException {
		String sql = "SELECT \n"
				+ "    i.name,i.color,i.manufacturer,i.price,c.amount,\n"
				+ "	c.item_id,c.user_id,c.booked_date\n"
				+ "FROM items_in_cart c INNER JOIN items i ON c.item_id = i.item_id\n"
				+ "WHERE \n"
				+ "    c.user_id = ?";
		List<ItemsInCartDTO> cartList = new ArrayList<>();//リストで取得
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, userId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					//mapRowで1行分のデータを作る
					ItemsInCartDTO cart = mapRow(rs);

					cartList.add(cart);//カートリストに追加

				}
			}
		}
		return cartList;
	}

	//複合キーで検索して商品IDが一致するものを内部結合して表示
	public ItemsInCartDTO findByItem(String userId, int itemId) throws SQLException {
		String sql = "SELECT i.name,i.color,i.manufacturer,i.price,c.amount,c.item_id,c.user_id,c.booked_date \n"
				+ "FROM items_in_cart c INNER JOIN items i ON c.item_id = i.item_id WHERE c.user_id = ? AND c.item_id = ?";
		ItemsInCartDTO cart = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, userId);
			ps.setInt(2, itemId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					cart = mapRow(rs);
					return cart;

				}
			}
		}
		return null;//見つからなかったらnull
	}

	private ItemsInCartDTO mapRow(ResultSet rs) throws SQLException {
		ItemsInCartDTO cart = new ItemsInCartDTO();
		cart.setUserId(rs.getString("user_id"));
		cart.setItemId(rs.getInt("item_id"));
		cart.setAmount(rs.getInt("amount"));
		cart.setBookedDate(rs.getDate("booked_date"));

		//結合先のデータ
		ItemsDTO items = new ItemsDTO();
		items.setItemId(rs.getInt("item_id"));
		items.setName(rs.getString("name"));
		items.setManufacturer(rs.getString("manufacturer"));
		items.setColor(rs.getString("color"));
		items.setPrice(rs.getInt("price"));
		cart.setItems(items);

		return cart;
	}
}
