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
	 * ���涩������
	 * @param uid
	 * @param cart
	 * @return
	 */
	public  String  addOrder(int uid,String username,String address,String tel,ShoppingCart cart){
		 con = DBConn.getConn();
		 String ordernum = "";
			try {
				//���������Զ��ύ
				con.setAutoCommit(false);
				
				//1. �Ȳ���һ��������¼
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
				//2.ȡ������Ķ���id  ���붩����ʱҪʹ���������   ���ݵ�ǰ�û�uid  ��ѯ��󶩵�id �����ղ����id
				 int orderid = -1;//
				ps = con.prepareStatement("select max(id)  from orders where uid=?");
				 ps.setInt(1, uid);
				 rs = ps.executeQuery();
				 if(rs.next()){
					 orderid = rs.getInt(1);
				 }
				 
				 
				//3������������   ѭ�����ﳵ��Ʒ����    
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
				
				//�ύ����
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
