package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Usr;
import bo.BookBO;
import bo.UsrBO;

public class LoginServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
//		String count = config.getInitParameter("count");
//		System.out.println(count);
//		
//		
//		ServletContext context = config.getServletContext();
//		String userCount = context.getInitParameter("userCount");
//		System.out.println("userCount    "+userCount);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("Service...........................................");
		String method = arg0.getMethod();
		if("GET".equals(method)){
			this.doGet(arg0, arg1);
		}else if("POST".equals(method)){
			this.doPost(arg0, arg1);
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��1���Ƿ���Ҫץȡ����   ��Ҫ�Ļ� ץ����֮ǰ request��������
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("c1");
		String password = request.getParameter("r1");
		//String sel = request.getParameter("sel");
		String[] hobby = request.getParameterValues("hobby");
		//ǰ̨������������������  ץȡ����Ϊnull    ֻҪ���ھͲ���null 
		System.out.println("in loginServle doPost()......."+name);
		System.out.println("in loginServle doPost()......."+password);
		//System.out.println(sel);
		//System.out.println(hobby.length);
		
		response.setContentType("text/html;charset=UTF-8");
		//��Ӧ�������ñ�����out����ǰ����
		//response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		//===========<2>����ҵ���߼����� ��������=======
		/*if("admin".equals(name)&&"1234".equals(password)){
			out.print("�ɹ�");
		}else{
			out.print("ʧ��");
		}*/
		
		//===========jdbc ʵ�ֵ�½===========
		/*boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from usr where name=? and password=?");
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag){
			out.print("�ɹ�");
		}else{
			out.print("ʧ��");
		}*/
		
		//==========ʹ�÷ֲ�  ����bo============
		UsrBO bo = new UsrBO();
		Usr u = bo.login(name, password);
		if(u==null){
			out.println("��½ʧ��");
			response.sendRedirect("http://localhost:8080/shoppingCar_servlet/index.jsp");
		}else{//��½�ɹ�
			//���û���Ϣ���浽session
			/**
			 * getSession(boolean b)
			 * b =true      ֮ǰ��sessionʵ��  ��ֱ��ʹ��    ���û�� �����µ�
			 * b = false    ֮ǰ��sessionʵ��  ��ֱ��ʹ��   ���û��  ���ᴴ���µ�   null
			 * 
			 * getSession() ===> getSession(true)
			 */
			
			BookBO bookbo = new BookBO();
			List<Book>list = bookbo.findAllBook();
			
			//ʹ��request��������
			request.setAttribute("booklist", list);
			HttpSession session = request.getSession(true); 
			session.setAttribute("usr", u);
			session.setAttribute("booklist", list);
			if(u.getAuth()==0){
				out.println("  </br>");
				out.println("  </br>");
				out.println("  <center>");
				
				out.println("��ͨ�û���½�ɹ�,<a href='findAllBook'>չ��ͼ��</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='html/searchBook.html'>��ѯͼ��</a>");
				//request.getRequestDispatcher("findAllBook").forward(request, response);
			}else{
				out.println("����Ա��½�ɹ�");
			}
		}
		out.println("  </center>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	public void destroy() {
		System.out.println("destroy..............................");
	}
}
