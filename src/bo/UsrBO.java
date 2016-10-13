package bo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBConn;

import bean.Usr;

public class UsrBO {//ҵ�������
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps;
	/**
	 * ��½ҵ�񷽷�
	 * @param name
	 * @param password
	 * @return  2��   u=null ��½ʧ��   u��User����  ��½�ɹ�  ����id name auth
	 */
	public  Usr  login(String name,String password){
		Usr u = null;
		 con = DBConn.getConn();
		 //System.out.println("conection = "+con);
		try {
			 st = con.createStatement();
			//ִ��sql
			 rs = st.executeQuery("select * from usr where name='"+name+"' and password ='"+password+"'");
			//��������
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
	 * ע�᷽��
	 * @param u
	 * @return
	 */
	public  boolean saveUsr(Usr u){
		boolean flag = false;
		con = DBConn.getConn();
		try {
			//������  �����Զ��ύΪfalse
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into user values(default,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.execute();
			//�ύ����
			con.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//�ع�����
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
	 * ��ѯ�����û�����
	 * @return List   2��    list����size()=0 û�м�¼     list size()!=0 �ж�����¼ 
	 */
	public List<Usr> findAllUsr(){
		List<Usr> list =new ArrayList<Usr>();
		con = DBConn.getConn();
		try {
			ps = con.prepareStatement("select * from user");
			rs = ps.executeQuery();
			//��������
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
