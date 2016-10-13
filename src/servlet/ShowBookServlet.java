package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Usr;

public class ShowBookServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得一个Book的集合   List<Book>   迭代展现
		List<Book> list =(List<Book>) request.getAttribute("booklist");
		
		Iterator<Book> it = list.iterator();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		/**
		 * 从session中取用户信息
		 */
		HttpSession session = request.getSession();
		Usr u = (Usr)session.getAttribute("usr");//如果取时逻辑名不存在   返回null
		String name ="游客";
		if(u!=null){//登陆用户
			name = u.getName();
			out.println(" 当前用户："+name);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='logout'>注销</a>");
		}else{
			out.println(" 当前用户："+name);
		}
		
		out.println("<center><h1>图书列表</h1><table border=1>");
		out.println("<tr><td>ID</td><td>书名</td><td>作者</td><td>单价</td><td>购买</td></tr>");
		
		while(it.hasNext()){
				Book b = it.next();
				out.println("<tr><td>"+b.getId()+"</td><td>"+b.getName()+"</td>" +
						"<td>"+b.getAuthor()+"</td><td>"+b.getPrice()+"</td>" +
								"<td><a href='addCart?id="+b.getId()+"'>购买</a></td></tr>");
		}
		
		out.println("</table></center></BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
