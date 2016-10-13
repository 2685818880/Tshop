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

		//��ȡɾ����Ʒ��id
		int id = Integer.parseInt(request.getParameter("id"));
		//ȡ���ﳵ
		ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");
		//�ӹ��ﳵɾ��id��Ӧ��Ʒ
		cart.deleteCart(id);
		request.getSession().setAttribute("cart", cart);
		//�ٴ�չ�ֹ��ﳵ����ת��չ�ֹ��ﳵServlet��
		response.sendRedirect(request.getContextPath()+"/showCart");
		
	}

}
