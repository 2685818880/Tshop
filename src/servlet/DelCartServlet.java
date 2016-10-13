package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ShoppingCart;

public class DelCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获取删除商品的id
		int id = Integer.parseInt(request.getParameter("id"));
		//取购物车
		ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");
		//从购物车删除id对应商品
		cart.deleteCart(id);
		request.getSession().setAttribute("cart", cart);
		//再次展现购物车（跳转到展现购物车Servlet）
		response.sendRedirect(request.getContextPath()+"/showCart");
		
	}

}
