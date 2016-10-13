package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bo.BookBO;

public class FindAllBookServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("in findAllBook "+con);
		//1 抓取参数
//		System.out.println("in FindAllBookServlet....");
		//2调用业务方法  处理请求
		BookBO bo = new BookBO();
		List<Book>list = bo.findAllBook();
		
		//使用request保存数据
		request.setAttribute("booklist", list);
		//请求转发  转发给ShowBookServlet来展现图书集合
		//获得一个转发对象
		//RequestDispatcher  dispatcher = request.getRequestDispatcher("/showBook");
		RequestDispatcher  dispatcher = request.getRequestDispatcher("/showbooks.jsp");
		//转发跳转
		dispatcher.forward(request, response);
		
		/*Iterator<Book> it = list.iterator();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
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
		out.close();*/
	}

}
