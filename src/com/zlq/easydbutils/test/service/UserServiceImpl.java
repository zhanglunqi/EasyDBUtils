package com.zlq.easydbutils.test.service;

import java.sql.SQLException;

import com.zlq.easydbutils.factory.EasyFactory;
import com.zlq.easydbutils.test.dao.UserDao;
import com.zlq.easydbutils.test.domain.User;

public class UserServiceImpl implements UserService {

	//不使用事务的登录
	public void login(String username, String password) {
		UserDao ud  = EasyFactory.newDaoInstance(UserDao.class);
		try {
			User user = ud.getUserByUsername(username);
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//使用事务
	public void addUser(User user){
		//同时插入两个用户
		UserDao ud  = EasyFactory.newDaoInstance(UserDao.class);
		try {
			ud.addUser(user);
			//没使用事务让其产生除0异常
			//在接口上声明注解让其产生除0异常
			//@SuppressWarnings("unused")
			int a = 1/0;
			ud.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
