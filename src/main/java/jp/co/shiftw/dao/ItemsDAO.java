package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.dto.ItemsDTO;

public class ItemsDAO extends BaseDAO {

	public ItemsDAO(Connection conn) {
		super(conn);

	}

	//主キー検索を行うメソッド
	public List<ItemsDTO> findByCond(int categoryId, String name) throws SQLException {

		//キーワードとカテゴリを検索するSQL文 「?」の中の数値が一緒であれば正しい範囲のフィールド値が出る
		String sql = "select * from items where name like ? and (category_id = ? or category_id * ? = 0)";

		//検索結果を格納する変数の宣言と初期化
		//ItemsDTO dto = null;

		//リストの作成
		List<ItemsDTO> list = new ArrayList<>();

		//SQL文を実行するオブジェクトの取得
		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			if (name == null) {

				name = "";
			}

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, categoryId);
			ps.setInt(3, categoryId);

			//SQL文の実行結果をResultSetに返す
			ResultSet rs = ps.executeQuery();

			//カーソルを移動させることでレコードを取得
			while (rs.next()) {
				//ItemsDTOに格納されるcate
				CategoriesDTO cate = new CategoriesDTO();

				//カーソルの指す行で指定した列名の値を取得
				cate.setCategoryId(rs.getInt("category_id"));
				cate.setName(rs.getString("name"));

				ItemsDTO dto = new ItemsDTO();

				dto.setName(rs.getString("name"));
				dto.setItemId(rs.getInt("item_id"));
				dto.setManufacturer(rs.getString("manufacturer"));
				dto.setCategory(cate);
				dto.setColor(rs.getString("color"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setRecommended(false);

				//listにdtoを格納
				list.add(dto);

			}

		}
		return list;

	}

	//主キー検索を行うメソッド
	public List<ItemsDTO> findByCondForPaging(int categoryId, String name, int pageNumber) throws SQLException {

		//キーワードとカテゴリを検索するSQL文 「?」の中の数値が一緒であれば正しい範囲のフィールド値が出る
		String sql = "select * from items where name like ? and (category_id = ? or category_id * ? = 0)"
				+ " OFFSET ? LIMIT 10";

		//検索結果を格納する変数の宣言と初期化
		//ItemsDTO dto = null;

		//リストの作成
		List<ItemsDTO> list = new ArrayList<>();

		//SQL文を実行するオブジェクトの取得
		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			if (name == null) {

				name = "";
			}

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, categoryId);
			ps.setInt(3, categoryId);
			ps.setInt(4, (pageNumber - 1) * 10);

			System.out.println(ps.toString());

			//SQL文の実行結果をResultSetに返す
			ResultSet rs = ps.executeQuery();

			//カーソルを移動させることでレコードを取得
			while (rs.next()) {
				//ItemsDTOに格納されるcate
				CategoriesDTO cate = new CategoriesDTO();

				//カーソルの指す行で指定した列名の値を取得
				cate.setCategoryId(rs.getInt("category_id"));
				cate.setName(rs.getString("name"));

				ItemsDTO dto = new ItemsDTO();

				dto.setName(rs.getString("name"));
				dto.setItemId(rs.getInt("item_id"));
				dto.setManufacturer(rs.getString("manufacturer"));
				dto.setCategory(cate);
				dto.setColor(rs.getString("color"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setRecommended(rs.getBoolean("recommended"));

				//listにdtoを格納
				list.add(dto);

			}

		}
		return list;

	}

	//itemIdからアイテムを検索する
	public ItemsDTO findByItemId(int itemId) throws SQLException {
		ItemsDTO items = null;
		//ItemIdで検索するSQL文 「?」の中の数値が一緒であれば正しい範囲のフィールド値が出る
		String sql = "select item_id, items.name as item_name, manufacturer, items.category_id, categories.name as category_name, color, price, stock, recommended from items "
				+ "inner join categories on items.category_id = categories.category_id where item_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				items = new ItemsDTO();
				items.setItemId(rs.getInt("item_id"));
				items.setName(rs.getString("item_name"));
				items.setManufacturer(rs.getString("manufacturer"));

				CategoriesDTO category = new CategoriesDTO();
				category.setCategoryId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));

				items.setCategory(category);
				items.setColor(rs.getString("color"));
				items.setPrice(rs.getInt("price"));
				items.setStock(rs.getInt("stock"));
				items.setRecommended(rs.getBoolean("recommended"));
			}

		}

		return items;
	}

	//指定した商品の在庫数を変動させる
	public void changeStock(int itemId, int num) throws SQLException {
		// 指定した商品の在庫数を調べる
		ItemsDTO item = this.findByItemId(itemId);
		int stock = item.getStock() + num; // 現在の在庫数を取得して値を増減させる

		if (stock < 0) {
			System.out.println("不正な操作: この操作を実行すると在庫数が0を下回ってしまいます");
			return;
		}

		String sql = "UPDATE items SET stock = ? WHERE item_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, stock);
			ps.setInt(2, itemId);

			ps.executeUpdate();
		}
	}

}
