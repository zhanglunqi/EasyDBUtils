package com.zlq.easydbutils.test.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zlq.easydbutils.factory.EasyFactory;
import com.zlq.easydbutils.test.domain.User;

public class UserDaoImpl implements UserDao {

	public User getUserByUsername(String username) throws SQLException {
		return EasyFactory.getRunner().query("select * from users where username = ?", new BeanHandler<User>(User.class),username);
	}

	public void addUser(User user) throws SQLException {
		EasyFactory.getRunner().update("insert into users values(null,?,?)",user.getUsername(),user.getPassword());
	}

}
