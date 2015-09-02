package com.zlq.easydbutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 用户自己实现这个接口完成与数据源的对接
 * @author lunqi
 *
 */
public interface EasyDataSource {
	public DataSource getDataSource();
	public Connection getConnection() throws SQLException;
}
