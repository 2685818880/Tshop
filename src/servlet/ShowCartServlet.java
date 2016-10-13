package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Usr;

import util.ShoppingCart;

public class ShowCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//从session中取购物车
		HttpSession session = request.getSession();
		ShoppingCart cart =(ShoppingCart)session.getAttribute("map");
		//从购物车取商品集合
		Collection<Book> col = cart.getItems();
		//展现商品集合
		Iterator<Book> it = col.iterator();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		/**
		 * 从session中取用户信息
		 */
		Usr u = (Usr)session.getAttribute("usr");//如果取时逻辑名不存在   返回null
		String name ="游客";
		if(u!=null){//登陆用户
			name = u.getName();
			out.println(" 当前用户："+name);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='logout'>注销</a>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='html/submitOrder.html'>提交订单</a>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='findAllBook'>继续选书</a>");
		}else{
			out.println(" 当前用户："+name);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='html/login.html'>请登陆后再查看购物车提交订单</a>");
		}
		
		out.println("<center><h1>购物车列表</h1><table border=1>");
		out.println("<tr><td>ID</td><td>书名</td><td>作者</td><td>单价</td><td>数量</td><td>删除</td></tr>");
		
		while(it.hasNext()){
				Book b = it.next();
				out.println("<tr><td>"+b.getId()+"</td><td>"+b.getName()+"</td>" +
						"<td>"+b.getAuthor()+"</td><td>"+b.getPrice()+"</td>" +
								"<td>"+b.getQuantity()+"</td><td><a href='delCart?id="+b.getId()+"'>删除</a></td></tr>");
		}
		
		out.println("</table>总价："+cart.getTotalPrice()+"</center></BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
