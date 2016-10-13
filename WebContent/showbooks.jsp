<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page import="bean.Usr"/>
<jsp:directive.page import="bean.Book"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'showBooks.jsp' starting page</title>
   </head>
    <body> 
    <center>
    <c:forEach items="${booklist}" var="books">
    	<c:out value="${books.name}"></c:out> &nbsp;&nbsp;
    	<c:out value="${books.author}"></c:out>&nbsp;&nbsp;
    	<c:out value="${books.price}"></c:out>&nbsp;&nbsp;
    	<a href="addCart?id=${books.id}">¹ºÂò</a> 
    	<br>
    </c:forEach>
    </center>
  </body>
</html>
