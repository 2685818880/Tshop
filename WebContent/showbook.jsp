<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page import="bean.Usr"/>
<jsp:directive.page import="bean.Book"/>
<%@ page import="bo.BookBO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  </head>
  <%
  	List list = (List)request.getAttribute("booklist");
  	Iterator it = list.iterator();
    while(it.hasNext()){
    	Book b = (Book)it.next();
   %>
  <body>
    <%=b.getId()%>&nbsp;<%=b.getName()%>&nbsp;<%=b.getPrice()%><a href='addCart?id=<%=b.getId()%>'>购买</a></br>
    <%} %>
  </body>
</html>
