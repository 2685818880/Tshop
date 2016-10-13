<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  <body>
     <center><br><br>用户登录<hr width="30%">
			<form action="loginServlet" method="post" name="form">
				用户：
				<input type="text" name="c1"><br>
					密码：
					<input type="password" name="r1"><br><br>
						<input type="submit" value="提交">
						<input type="reset" value="重置">
			</form>
		</center>
  </body>
</html>
