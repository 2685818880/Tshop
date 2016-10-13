package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBConn;

import bean.Book;
import bean.Usr;
public class BookBO {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps;
	
	public List<Book> findAllBook(){
		List <Book> list = new ArrayList<Book>();
		 con = DBConn.getConn();
			try {
				 st = con.createStatement();
				//执行sql
				 rs = st.executeQuery("select * from book");
				//处理结果集
				while(rs.next()){
					Book  b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
					list.add(b);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConn.close(rs, st, con);
			}
		return list;
	}
	/**
	 * 模糊查询    根据书名查询
	 * @param name
	 * @return
	 */
	public  List<Book>   findBookByName(String col,String name){
		List <Book> list = new ArrayList<Book>();
		 con = DBConn.getConn();
			try {
				String sql ="select * from book where "+col+" like ?";
				/*if("按作者查询".equals(col)){
					sql = "select * from book where author like ?";
				}else{
					sql = "select * from book where name like ?";
				}*/
				 ps = con.prepareStatement(sql);
				 ps.setString(1, "%"+name+"%");
				//执行sql
				// rs = st.executeQuery("select * from book where name like '%"+name+"%'");
				 rs = ps.executeQuery();
				//处理结果集
				while(rs.next()){
					Book  b = new Book(rs.getInt("id"),rs.getString("name"),rs.getString("author"),rs.getDouble("pric"));
					list.add(b);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConn.close(rs, st, con);
			}
		return list;
	}
	
	public Book   findBookById(int id){
		
		 con = DBConn.getConn();
			try {
				String sql ="select * from book where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1,id);
				rs = ps.executeQuery();
				//处理结果集
				if(rs.next()){
					Book  b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
					return b;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConn.close(rs, st, con);
			}
		return null;
	}
}
