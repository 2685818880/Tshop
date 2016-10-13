package bo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBConn;

import bean.Usr;

public class UsrBO {//业务操作类
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps;
	/**
	 * 登陆业务方法
	 * @param name
	 * @param password
	 * @return  2种   u=null 登陆失败   u是User对象  登陆成功  包含id name auth
	 */
	public  Usr  login(String name,String password){
		Usr u = null;
		 con = DBConn.getConn();
		 //System.out.println("conection = "+con);
		try {
			 st = con.createStatement();
			//执行sql
			 rs = st.executeQuery("select * from usr where name='"+name+"' and password ='"+password+"'");
			//处理结果集
			if(rs.next()){
				u = new Usr();
				u.setId(rs.getInt(1));
				//u.setAuth(rs.getInt("auth"));
				u.setName(rs.getString(2));
				u.setPassword("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(rs, st, con);
		}
		return u;
	}
	/**
	 * 注册方法
	 * @param u
	 * @return
	 */
	public  boolean saveUsr(Usr u){
		boolean flag = false;
		con = DBConn.getConn();
		try {
			//事务处理  设置自动提交为false
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into user values(default,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.execute();
			//提交事务
			con.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//回滚事务
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBConn.close(rs, ps, con);
		}
		return flag;
	}
	/**
	 * 查询所有用户方法
	 * @return List   2种    list对象size()=0 没有记录     list size()!=0 有多条记录 
	 */
	public List<Usr> findAllUsr(){
		List<Usr> list =new ArrayList<Usr>();
		con = DBConn.getConn();
		try {
			ps = con.prepareStatement("select * from user");
			rs = ps.executeQuery();
			//处理结果集
			while(rs.next()){
				Usr u = new Usr(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConn.close(rs, ps, con);
		}
		return list;
	
	}
}
