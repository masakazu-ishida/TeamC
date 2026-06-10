package jp.co.sars.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.UserDTO;
import jp.co.sars.util.TestBase;

/**
 * サービスの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 * 単体テストの観点
 * ・網羅率：ブランチカバレッジ
 * ・検索系：DTOのフィールドに値が期待どおり格納されるか？Listの場合期待する件数が格納されるか？
 * ・更新系：DBの状態が期待どおりか？
 * 
 * サービスの場合、処理内容によっては単純に検索メソッドを読んで戻り値を返すだけなど）単体テストと
 * ほぼ同じになるケースもあるが、それでも実施する。
 * DAOテストのテストコードの流用ができるはず。
 * 
 */
class AuthenticateServiceTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * ユーザID・パスワード共に正しい場合のテスト
	 */
	@Test
	void testFindById1() {

		AuthenticateService svc = new AuthenticateService();
		try {
			UserDTO user = svc.execute("user1", "userpass1");

			assertNotNull(user);

			user = svc.execute("user1", "");

			assertNull(user);

		} catch (ServletException e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * ユーザIDが正しいが、パスワードが間違っている場合のテスト
	 */
	@Test
	void testFindById2() {
		AuthenticateService svc = new AuthenticateService();
		try {
			//パスワードが間違い
			UserDTO user = svc.execute("user1", "");
			assertNull(user);

		} catch (ServletException e) {

			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * ユーザIDは間違っているが、パスワードが正しい場合のテスト
	 */
	@Test
	void testFindById3() {
		AuthenticateService svc = new AuthenticateService();
		try {

			//ユーザIDが間違い
			UserDTO user = svc.execute("", "userpass1");
			assertNull(user);

		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * ユーザID・パスワードともに間違っている場合のテスト
	 */
	@Test
	void testFindById4() {
		AuthenticateService svc = new AuthenticateService();
		try {

			//ID・パスワード両方間違い
			UserDTO user = svc.execute("", "");
			assertNull(user);

		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			fail(e);
		}
	}

}
