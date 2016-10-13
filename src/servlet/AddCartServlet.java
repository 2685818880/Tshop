package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ShoppingCart;
import bean.Book;
import bo.BookBO;

public class AddCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取参数，拿到图书的ID，然后查找这本书
		int id = Integer.parseInt(request.getParameter("id"));
		//调用业务方法  实现请求，根据图书ID查找书
		BookBO bo = new BookBO();
		Book b = bo.findBookById(id);
		//购物车对象，取Session里面是商品，如果第一次购买就需要new一个购物车
		ShoppingCart map =(ShoppingCart) session.getAttribute("map");
		if(map==null){//说明是第一次购买
			map = new ShoppingCart();
		}
		//将商品添加到购物车
		map.addCart(b);
		//将cart保存到session
		session.setAttribute("map", map);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("添加购物车成功！车内书的种类："+map.getItems().size());
		out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='showCart'>查看购物车</a>");
		out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:history.back();'>继续购物</a>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
