package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * 数据库连接的工具类
 * @author soft
 *
 */
public class DBConn {
	private static Connection con;
	private final static String URL="jdbc:mysql://localhost:3306/school";
	private final static String USER="root";
	private final static String PASSWORD="root";
	//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","root");
	/**
	 * 获得连接的功能方法
	 * @return
	 */
	public static Connection getConn(){
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		/**
		 * JNDI 获得tomcat数据源
		 */
		/*
		try {
			Context ctx = new InitialContext();
			//获得tomcat环境对象
			ctx = (Context) ctx.lookup("java:/comp/env");
			DataSource ds =(DataSource)ctx.lookup("jdbc/mysql");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		return con;
	}
	/**
	 * 关闭功能方法
	 * @param rs
	 * @param st
	 * @param con
	 */
	public static void close(ResultSet rs,Statement st,Connection con){
		try {
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(getConn());
	}

}
