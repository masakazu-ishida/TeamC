package jp.co.shiftw.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Connectionの保持と、JUnit単体テストでのSQLの初期化機能を提供するスーパークラス
 * @author mi_co
 *
 */
public class BaseDAO {

	/**
	 * Connectionを保持する。
	 */
	protected Connection conn;

	public BaseDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * SQLファイル内のSQL文を元に、一括でDBへの初期化を行う。
	 * Junitの@BeforeEachで呼び出す。
	 * @param path プロジェクトフォルダ以下の.sqlファイルの格納フォルダを指定。
	 * @return 更新に成功した件数
	 * @throws SQLException
	 * @throws IOException
	 */
	public int insertBatch(String path) throws SQLException, IOException {
		List<String> sqlList = getSQLLine(path);
		int ret = 0;
		try (Statement statamenet = conn.createStatement()) {
			for (String sql : sqlList) {
				//行の文字が改行・スペース等が含まれないものだけSQLとみなす。
				if (!sql.matches("[^\\s]")) {
					statamenet.addBatch(sql);
				}
			}
			int[] successResult = statamenet.executeBatch();
			ret = successResult.length;
			return ret;
		}
	}

	/**
	 * SQLファイルを読み込み、LISTで返す
	 * @return
	 * @throws IOException
	 */
	private List<String> getSQLLine(String path) throws IOException {
		List<String> sqlList = new ArrayList<>();
		Path sqlFilePath = Paths.get("").toAbsolutePath().resolve(path);
		File file = new File(sqlFilePath.toString());
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String str = null;
			do {
				str = br.readLine();
				if (str != null) {
					sqlList.add(str);
				}

			} while (str != null);
		}
		return sqlList;
	}

}
