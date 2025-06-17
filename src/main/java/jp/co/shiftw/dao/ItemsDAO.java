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
		String sql = "select * from items where name = ? and category_id = ? or category_id * ? = 0";

		//検索結果を格納する変数の宣言と初期化
		//ItemsDTO dto = null;

		//リストの作成
		List<ItemsDTO> list = new ArrayList<>();

		//SQL文を実行するオブジェクトの取得
		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, name);
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
				dto.setCategory(cate);

				list.size();

				//listにdtoを格納
				list.add(dto);

			}

		}
		return list;

	}

}
