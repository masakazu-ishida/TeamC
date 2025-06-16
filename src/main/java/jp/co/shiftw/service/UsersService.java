package jp.co.shiftw.service;

import jp.co.shiftw.dao.UsersDAO;

public class UsersService {

	private UsersDAO usersDAO;

	public UsersService(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

}
