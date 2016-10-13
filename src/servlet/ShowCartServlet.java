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

		//��session��ȡ���ﳵ
		HttpSession session = request.getSession();
		ShoppingCart cart =(ShoppingCart)session.getAttribute("map");
		//�ӹ��ﳵȡ��Ʒ����
		Collection<Book> col = cart.getItems();
		//չ����Ʒ����
		Iterator<Book> it = col.iterator();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		/**
		 * ��session��ȡ�û���Ϣ
		 */
		Usr u = (Usr)session.getAttribute("usr");//���ȡʱ�߼���������   ����null
		String name ="�ο�";
		if(u!=null){//��½�û�
			name = u.getName();
			out.println(" ��ǰ�û���"+name);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='logout'>ע��</a>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='html/submitOrder.html'>�ύ����</a>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='findAllBook'>����ѡ��</a>");
		}else{
			out.println(" ��ǰ�û���"+name);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='html/login.html'>���½���ٲ鿴���ﳵ�ύ����</a>");
		}
		
		out.println("<center><h1>���ﳵ�б�</h1><table border=1>");
		out.println("<tr><td>ID</td><td>����</td><td>����</td><td>����</td><td>����</td><td>ɾ��</td></tr>");
		
		while(it.hasNext()){
				Book b = it.next();
				out.println("<tr><td>"+b.getId()+"</td><td>"+b.getName()+"</td>" +
						"<td>"+b.getAuthor()+"</td><td>"+b.getPrice()+"</td>" +
								"<td>"+b.getQuantity()+"</td><td><a href='delCart?id="+b.getId()+"'>ɾ��</a></td></tr>");
		}
		
		out.println("</table>�ܼۣ�"+cart.getTotalPrice()+"</center></BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
