package com.zlq.easydbutils;

import java.sql.Connection;
import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
/**
 * 
 * @author lunqi
 *
 */
public class Runner  {
	
	private static QueryRunner runner = new QueryRunner();
	private  ThreadLocal <Connection> local = null;
	
	 public Runner(ThreadLocal<Connection>  local){
		this.local = local;
	}

	public int[] batch(String sql, Object[][] params) throws SQLException {
		return runner.batch(local.get(),sql, params);
	}


	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		return runner.query(local.get(),sql, rsh, params);
	}

	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		return runner.query(local.get(),sql, rsh);
	}

	public int update(String sql, Object... params) throws SQLException {
		return runner.update(local.get(),sql, params);
	}

	public int update(String sql, Object param) throws SQLException {
		return runner.update(local.get(),sql, param);
	}

	public int update(String sql) throws SQLException {
		return runner.update(local.get(),sql);
	}
	
}
