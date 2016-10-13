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

		//���涩������
		
		//1.ץȡ����    �ռ�����Ϣ 
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		//2.��ȡ���� �� ������ ����Ҫ��������Ϣ
		//������uid(session)  totalprice(cart--session)   
		//�����bookid  bookname  quantity (cart--session)
		HttpSession session = request.getSession();
		Usr u = (Usr)session.getAttribute("usr");
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		//3.����ҵ�񷽷�  ���涩��   OrdersBO
		OrderBO bo = new OrderBO();
		String ordernum = bo.addOrder(u.getId(),username,address,tel,cart);
		//4.��Ӧ    ���߿ͻ��˳ɹ�  ���������ʲô
		String result ="��������ʧ��";
		if(ordernum!=null){
			//�����ﳵ���  �� �����ﳵ��session
			cart.clearCart();
			//session.removeAttribute("cart");
			result ="��������ɹ����������Ϊ��"+ordernum;
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
