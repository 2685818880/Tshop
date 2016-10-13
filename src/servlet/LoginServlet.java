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
		//《1》是否需要抓取参数   需要的话 抓参数之前 request编码设置
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("c1");
		String password = request.getParameter("r1");
		//String sel = request.getParameter("sel");
		String[] hobby = request.getParameterValues("hobby");
		//前台发请求参数如果不存在  抓取参数为null    只要存在就不是null 
		System.out.println("in loginServle doPost()......."+name);
		System.out.println("in loginServle doPost()......."+password);
		//System.out.println(sel);
		//System.out.println(hobby.length);
		
		response.setContentType("text/html;charset=UTF-8");
		//响应编码设置必须在out创建前设置
		//response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		//===========<2>调用业务逻辑方法 处理请求=======
		/*if("admin".equals(name)&&"1234".equals(password)){
			out.print("成功");
		}else{
			out.print("失败");
		}*/
		
		//===========jdbc 实现登陆===========
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
			out.print("成功");
		}else{
			out.print("失败");
		}*/
		
		//==========使用分层  调用bo============
		UsrBO bo = new UsrBO();
		Usr u = bo.login(name, password);
		if(u==null){
			out.println("登陆失败");
			response.sendRedirect("http://localhost:8080/shoppingCar_servlet/index.jsp");
		}else{//登陆成功
			//将用户信息保存到session
			/**
			 * getSession(boolean b)
			 * b =true      之前有session实例  就直接使用    如果没有 创建新的
			 * b = false    之前有session实例  就直接使用   如果没有  不会创建新的   null
			 * 
			 * getSession() ===> getSession(true)
			 */
			
			BookBO bookbo = new BookBO();
			List<Book>list = bookbo.findAllBook();
			
			//使用request保存数据
			request.setAttribute("booklist", list);
			HttpSession session = request.getSession(true); 
			session.setAttribute("usr", u);
			session.setAttribute("booklist", list);
			if(u.getAuth()==0){
				out.println("  </br>");
				out.println("  </br>");
				out.println("  <center>");
				
				out.println("普通用户登陆成功,<a href='findAllBook'>展现图书</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='html/searchBook.html'>查询图书</a>");
				//request.getRequestDispatcher("findAllBook").forward(request, response);
			}else{
				out.println("管理员登陆成功");
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
