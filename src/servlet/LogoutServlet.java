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

		//注销功能
		
		//将保存信息清除    不是一个一个清除session保存数据   而是让session实例消亡
		HttpSession session = request.getSession();
		/*session.removeAttribute("usr");//将保存逻辑名usr  参数清除
		session.removeAttribute("cart");*/
		//......
		
		//消亡session
		session.invalidate();
		
		
		//重定向跳转首页
		response.sendRedirect(request.getContextPath()+"/html/login.html");
		
	}

}
