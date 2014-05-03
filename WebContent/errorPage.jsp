<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.entity.Genel" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%= Genel.TITLE %></title>
</head>
<body>

<%
	String message = (String) request.getSession().getAttribute("message");
%>


<jsp:include page="login.jsp">
	<jsp:param value="${message}" name="message"/>
</jsp:include>

</body>
</html>