package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ShoppingCart;

import bean.Usr;
import bo.OrderBO;

public class AddOrderServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//保存订单操作
		
		//1.抓取参数    收件人信息 
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		//2.获取订单 及 订单项 所需要的其他信息
		//订单：uid(session)  totalprice(cart--session)   
		//订单项：bookid  bookname  quantity (cart--session)
		HttpSession session = request.getSession();
		Usr u = (Usr)session.getAttribute("usr");
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		//3.调用业务方法  保存订单   OrdersBO
		OrderBO bo = new OrderBO();
		String ordernum = bo.addOrder(u.getId(),username,address,tel,cart);
		//4.响应    告诉客户端成功  订单编号是什么
		String result ="订单保存失败";
		if(ordernum!=null){
			//将购物车清空  或 将购物车从session
			cart.clearCart();
			//session.removeAttribute("cart");
			result ="订单保存成功，订单编号为："+ordernum;
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println(result);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
