package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import bean.Book;

import util.DBConn;
import util.ShoppingCart;

public class OrderBO {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps;
	/**
	 * 保存订单方法
	 * @param uid
	 * @param cart
	 * @return
	 */
	public  String  addOrder(int uid,String username,String address,String tel,ShoppingCart cart){
		 con = DBConn.getConn();
		 String ordernum = "";
			try {
				//设置事务不自动提交
				con.setAutoCommit(false);
				
				//1. 先插入一个订单记录
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				 ordernum=sdf.format(new Date());
				 ps = con.prepareStatement("insert into orders values(null,?,?,?,?,?,?,?)");
				 ps.setString(1, ordernum);
				 ps.setInt(2, uid);
				 ps.setString(3, username);
				 ps.setString(4, address);
				 ps.setString(5, tel);
				 ps.setDouble(6, cart.getTotalPrice());
				 ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				 ps.execute();
				//2.取出插入的订单id  插入订单项时要使用它做外键   根据当前用户uid  查询最大订单id 即：刚插入的id
				 int orderid = -1;//
				ps = con.prepareStatement("select max(id)  from orders where uid=?");
				 ps.setInt(1, uid);
				 rs = ps.executeQuery();
				 if(rs.next()){
					 orderid = rs.getInt(1);
				 }
				 
				 
				//3插入多个订单项   循环购物车商品集合    
				Collection<Book> col = cart.getItems();
				Iterator <Book> it = col.iterator();
				while(it.hasNext()){
					Book b = it.next();
					ps = con.prepareStatement("insert into orderitem values(null,?,?,?,?)");
					ps.setInt(1, orderid);
					ps.setInt(2, b.getId());
					ps.setString(3, b.getName());
					ps.setInt(4, b.getQuantity());
					ps.execute();
				}
				
				//提交事务
				con.commit();
				return ordernum;
				
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				DBConn.close(rs, st, con);
			}
		return null;
	}
}
