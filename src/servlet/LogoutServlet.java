package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ע������
		
		//��������Ϣ���    ����һ��һ�����session��������   ������sessionʵ������
		HttpSession session = request.getSession();
		/*session.removeAttribute("usr");//�������߼���usr  �������
		session.removeAttribute("cart");*/
		//......
		
		//����session
		session.invalidate();
		
		
		//�ض�����ת��ҳ
		response.sendRedirect(request.getContextPath()+"/html/login.html");
		
	}

}
