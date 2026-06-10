package jp.co.sars.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.UserDAO;
import jp.co.sars.dto.UserDTO;
import jp.co.sars.util.ConnectionUtil;

/**
 * 認証業務クラス
 * サービスを作る粒度は「処理ごと」がよい
 */
public class AuthenticateService {

	/**
	 * 認証業務を実行するサービスメソッド
	 * このメソッドにより、コントローラーは認証業務の詳細を知る必要がない
	 * @param userId 画面から入力されたユーザID
	 * @param password 画面から入力されたパスワード
	 * @return ユーザのDTO。認証に成功すれば非NULL、認証に失敗すればNULL
	 * @throws ECSiteException
	 */
	public UserDTO execute(String userId, String password) throws ServletException {

		//DBへの接続の設定名
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			//Connectionをコンストラクタに渡す
			UserDAO dao = new UserDAO(conn);

			//主キーによる取得
			UserDTO user = dao.findById(userId);

			//非NULLならユーザIDは正しい
			if (user != null) {
				//パスワードが間違っていれば、ログインやり直しの為、DTOにNULLを設定
				if (!user.getPassword().equals(password)) {
					user = null;
				}
			}
			return user;

		} catch (Exception e) {

			//発生した例外は全部独自例外に変換してスロー
			//コントローラクラスでは常に独自例外のみキャッチさせる事で、コントローラーの記述を単純化させる
			throw new ServletException(e.getCause());
		}
	}

}
