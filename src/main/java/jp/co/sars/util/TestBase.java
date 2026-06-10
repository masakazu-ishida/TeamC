package jp.co.sars.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * Test用のスーパークラス
 */
public class TestBase {
	protected static Connection con = null;

	protected void initSQLFiles(String sqlFilePath) throws Exception {
		// 1. データベースへの接続
		if (con == null) {
			con = ConnectionUtil.getConnection(null);

		}
		con.setAutoCommit(false); // 手動コミットモード

		// 2. クラスパスからSQLファイルをテキストとして読み込む
		String sqlScript = "";
		try (InputStream is = getClass().getResourceAsStream(sqlFilePath);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

			// ファイルの全行を1つの文字列にまとめる
			sqlScript = reader.lines().collect(Collectors.joining("\n"));
		} catch (Exception e) {
			fail("初期化用SQLファイルの配置場所 【" + sqlFilePath + "】 が正しいか確認して下さい。");
			con.rollback(); // 途中で失敗したら元に戻す
			throw e;
		}

		// 3. セミコロン「;」で区切って、1文ずつの配列にする
		String[] sqlStatements = sqlScript.split(";");

		// 4. JDBCのStatementを使って、1文ずつ順番に実行する
		try (Statement stmt = con.createStatement()) {
			for (String sql : sqlStatements) {
				String trimmedSql = sql.trim();
				// 空行やコメント行でなければ実行する
				if (!trimmedSql.isEmpty() && !trimmedSql.startsWith("--")) {
					stmt.execute(trimmedSql);
				}
			}
			con.commit(); // すべて成功したらコミットして確定
		} catch (Exception e) {
			fail(e);
			con.rollback(); // 途中で失敗したら元に戻す
			throw e;
		}
	}

}
