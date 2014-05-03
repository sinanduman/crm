<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="crm.irfan.User"%>
<%
	User user = (User) session.getAttribute("user");
	Boolean loggedin = (Boolean) session.getAttribute("loggedin");
	if (loggedin == null || user == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	else{
	%>
	<jsp:include page="navigate.jsp">
		<jsp:param value="user"	name="user" />
	</jsp:include>
	<%
	}
%>