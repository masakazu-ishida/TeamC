package jp.co.sars.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.sars.dto.UserDTO;
import jp.co.sars.util.ConnectionUtil;
import jp.co.sars.util.TestBase;

/**
 * DAOの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 * 
 * 単体テストの観点
 * ・網羅率：ブランチカバレッジ
 * ・検索系：DTOのフィールドに値が期待どおり格納されるか？Listの場合期待する件数が格納されるか？
 * ・更新系：DBの状態が期待どおりか？
 * 
 */
class UserDAOTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	以下の後始末メソッドは何も考えずまるごとコピー
	 * テストメソッドが全て完了した後、必ず実行され、
	 * DBのコネクションがクローズされる。なお、クローズされるのは
	 * /init_data.sqlで初期化するコネクション
	 * */
	@AfterAll
	public static void cleanUp() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Test
	void testFindByAll() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UserDAO dao = new UserDAO(conn);
			List<UserDTO> userList = dao.findAll();

			assertNotNull(userList);
			assertEquals(4, userList.size());

			for (UserDTO user : userList) {
				assertEquals("user1", user.getUserId());
				assertEquals("userpass1", user.getPassword());
				assertEquals("鳥取一郎", user.getName());
				assertEquals("鳥取県鳥取市河原町６丁目１０７", user.getAddress());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 主キーが存在する場合のテスト
	 */
	@Test
	void testFindById1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UserDAO dao = new UserDAO(conn);
			UserDTO user = dao.findById("user1");

			assertNotNull(user);
			assertEquals("user1", user.getUserId());
			assertEquals("userpass1", user.getPassword());
			assertEquals("鳥取一郎", user.getName());
			assertEquals("鳥取県鳥取市河原町６丁目１０７", user.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 主キーが存在しない場合のテスト
	 */
	@Test
	void testFindById2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UserDAO dao = new UserDAO(conn);
			UserDTO user = dao.findById("999999ABVS?(%#$");

			assertNull(user);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	void testInsert() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UserDAO dao = new UserDAO(conn);
			UserDTO user = new UserDTO();
			user.setUserId("user10");
			user.setPassword("password10");
			user.setName("島根一郎");
			user.setAddress("島根県奥出雲町");

			int result = dao.insert(user);

			assertEquals(1, result);

			user = dao.findById("user10");

			assertNotNull(user);
			assertEquals("user10", user.getUserId());
			assertEquals("password10", user.getPassword());
			assertEquals("島根一郎", user.getName());
			assertEquals("島根県奥出雲町", user.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testUpdate() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UserDAO dao = new UserDAO(conn);
			UserDTO user = new UserDTO();
			user.setUserId("user1");
			user.setPassword("password10");
			user.setName("鳥取太郎");
			user.setAddress("鳥取県日南町茶屋");

			int result = dao.update(user);

			assertEquals(1, result);

			user = dao.findById("user1");

			assertNotNull(user);
			assertEquals("user1", user.getUserId());
			assertEquals("password10", user.getPassword());
			assertEquals("鳥取太郎", user.getName());
			assertEquals("鳥取県日南町茶屋", user.getAddress());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	@Test
	void testDelete() {
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			UserDAO dao = new UserDAO(conn);
			UserDTO user = new UserDTO();
			user.setUserId("user4");

			int result = dao.delete("user4");

			assertEquals(1, result);

			user = dao.findById("user4");

			assertNull(user);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}
}
