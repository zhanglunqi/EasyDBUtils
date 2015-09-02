package com.zlq.easydbutils.test.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zlq.easydbutils.EasyDataSource;

/**
 * 这个工具类主要提供从数据库连接池获取连接的方法
 * 使用C3P0数据库连接池
 * @author lunqi
 *
 */
public class MyEasyDataSource4c3p0 implements EasyDataSource{
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	/**
	 * 返回数据源
	 * @return
	 */
	public  DataSource getDataSource(){
		return dataSource;
	}
	/**
	 * 返回一个从数据库连接池获取的连接
	 * @return
	 * @throws SQLException 
	 */
	public  Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
