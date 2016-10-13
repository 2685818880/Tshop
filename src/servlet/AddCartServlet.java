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
		//��ȡ�������õ�ͼ���ID��Ȼ������Ȿ��
		int id = Integer.parseInt(request.getParameter("id"));
		//����ҵ�񷽷�  ʵ�����󣬸���ͼ��ID������
		BookBO bo = new BookBO();
		Book b = bo.findBookById(id);
		//���ﳵ����ȡSession��������Ʒ�������һ�ι������Ҫnewһ�����ﳵ
		ShoppingCart map =(ShoppingCart) session.getAttribute("map");
		if(map==null){//˵���ǵ�һ�ι���
			map = new ShoppingCart();
		}
		//����Ʒ��ӵ����ﳵ
		map.addCart(b);
		//��cart���浽session
		session.setAttribute("map", map);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("��ӹ��ﳵ�ɹ�������������ࣺ"+map.getItems().size());
		out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='showCart'>�鿴���ﳵ</a>");
		out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:history.back();'>��������</a>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
