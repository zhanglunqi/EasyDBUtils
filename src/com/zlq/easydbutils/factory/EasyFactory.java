package com.zlq.easydbutils.factory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Properties;

import com.zlq.easydbutils.EasyDataSource;
import com.zlq.easydbutils.MyEasyDataSource4c3p0;
import com.zlq.easydbutils.Runner;
import com.zlq.easydbutils.annotation.StratTransaction;
/**
 * 
 * @author lunqi
 *
 */
public class EasyFactory {
	public static Properties propService = new Properties();
	public static Properties propDao = new Properties();
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	private static EasyDataSource eds = new MyEasyDataSource4c3p0();
	static{
		try {
			propService.load(EasyFactory.class.getClassLoader().getResourceAsStream("/easyDBUtilsService.properties"));
			propDao.load(EasyFactory.class.getClassLoader().getResourceAsStream("/easyDBUtilsDao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	@SuppressWarnings("unchecked")
	public static <T> T newServiceInstance(Class<T> clazz) {
		try {
			clazz = (Class<T>) Class.forName(propService.getProperty(clazz.getSimpleName()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		T	t_class = null;
		try {
			t_class =clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		 final T t = t_class;
		return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Connection conn = eds.getConnection();
				//加了事务注解
				local.set(conn);
				//System.out.println("=判断注解之前=="+local.get());
				if (method.getAnnotation(StratTransaction.class)!=null) {
					//如果加，获取conn，然后调用dao层某个方法的时候，就自动加上
					conn.setAutoCommit(false);
					try{
						return method.invoke(t, args);
					}catch(Exception e){
						local.get().rollback();
						e.printStackTrace();
					}finally{
						local.get().commit();
						local.get().close();
						//System.out.println("==注解的返回连接到连接池=="+local.get());
					}
				}else{
					try{
						return method.invoke(t, args);
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						local.get().close();
						//System.out.println("==没有注解的返回连接到连接池=="+local.get());
					}
				}
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T newDaoInstance(Class<T> clazz){
		try {
			clazz = (Class<T>) Class.forName(propDao.getProperty(clazz.getSimpleName()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 T t = null;
			try {
				t = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return t;
	}
	
	public static Runner getRunner() {
		//是不是要根据当前线程来判断
		Runner runner = new Runner(local);
		return runner;
	}
}
