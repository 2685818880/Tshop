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
		//���һ��Book�ļ���   List<Book>   ����չ��
		List<Book> list =(List<Book>) request.getAttribute("booklist");
		
		Iterator<Book> it = list.iterator();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		/**
		 * ��session��ȡ�û���Ϣ
		 */
		HttpSession session = request.getSession();
		Usr u = (Usr)session.getAttribute("usr");//���ȡʱ�߼���������   ����null
		String name ="�ο�";
		if(u!=null){//��½�û�
			name = u.getName();
			out.println(" ��ǰ�û���"+name);
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='logout'>ע��</a>");
		}else{
			out.println(" ��ǰ�û���"+name);
		}
		
		out.println("<center><h1>ͼ���б�</h1><table border=1>");
		out.println("<tr><td>ID</td><td>����</td><td>����</td><td>����</td><td>����</td></tr>");
		
		while(it.hasNext()){
				Book b = it.next();
				out.println("<tr><td>"+b.getId()+"</td><td>"+b.getName()+"</td>" +
						"<td>"+b.getAuthor()+"</td><td>"+b.getPrice()+"</td>" +
								"<td><a href='addCart?id="+b.getId()+"'>����</a></td></tr>");
		}
		
		out.println("</table></center></BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
