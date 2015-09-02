package com.zlq.easydbutils.test.dao;

import java.sql.SQLException;

import com.zlq.easydbutils.test.domain.User;

public interface UserDao {

public	User getUserByUsername(String username) throws SQLException;

public void addUser(User user) throws SQLException;

}
