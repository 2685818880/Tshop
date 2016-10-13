package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ����Servlet����
 * ��1�������� �̳�HttpServlet
 * ��2����дdoGet  doPost����
 * ��3����web.xml������Servlet
 * 			<servlet>
				<servlet-name>helloWorld</servlet-name>
				<servlet-class>servlet.HelloWorldServlet</servlet-class>
			</servlet>
			<servlet-mapping>
				<servlet-name>helloWorld</servlet-name>
				<url-pattern>/hello</url-pattern>
			</servlet-mapping>
	��4��������Ŀ ����  ����
 * @author XUELI-JIAO
 *
 */
public class HelloWorldServlet  extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
	
		ServletContext context = config.getServletContext();
		String userCount = context.getInitParameter("userCount");
		System.out.println("userCount    "+userCount);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("HelloWorld!");
	}
	
}
