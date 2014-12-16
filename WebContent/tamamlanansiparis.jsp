<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en" ng-app>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="img/favicon.ico">
	<title><%= Genel.TITLE %></title>

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="css/irfan.css?<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" href="css/fonts.css">
	<link rel="stylesheet" href="css/font-awesome.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%@ page import="crm.irfan.User, crm.irfan.entity.Genel, crm.irfan.entity.ResultTip" %>
<%@ page import="crm.irfan.entity.Siparis" %>
<%@ page import="java.util.List" %>

<%
	User user = (User) session.getAttribute("user");
	Boolean loggedin = (Boolean) session.getAttribute("loggedin");
	if (loggedin == null || !loggedin) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>

<jsp:include page="navigate.jsp">
	<jsp:param value="user" name="user"/>
</jsp:include>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Tamamlanan Siparişler</label>
	</div>
	<div class="row" ng-controller="HammaddeEkleCtrl">
	</div>
	
	<% List<Siparis> siparis = (List<Siparis>) request.getAttribute("siparis");  %>
	<% ResultTip result = (ResultTip) request.getAttribute("result");  %>
	<%
	if ( result.value() == ResultTip.NORESULT.value() ){
	%>
	<div class="row">
		<div class="container">
		</div>
	 </div>
	<%	
	}
	else if ( result.value() == ResultTip.OK.value() ){
	%>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="alert alert-success">
		Sipariş başarıyla alındı.
		</div>
		<div class="col-sm-1"></div>
	 </div>
	<%	
	}
	else{
	%>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="alert alert-danger">
		Sipariş alınırken hata oldu!...
		</div>
		<div class="col-sm-1"></div>
	 </div>
	<%
	}
	%>

	<div class="row">
		<div class="container">
			<div class="col-sm-3"></div>
			<div class="col-sm-8">
				<table class="table table-striped table-bordered">
					<% int sayac = 0; %>					
					<% for (Siparis s : siparis) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<th><label>Mamul Adi</label></th>
						<th><label>Sipariş Adedi</label></th>
						<th><label>Sipariş Tarihi</label></th>
						<th><label>Bitiş Tarihi</label></th>
						<th><label>Aksiyon</label></th>
					</tr>
					<% } %>
					<tr>
						<td><%= s.getBilesenad() %>
						</td>
						<td><%= s.getMiktar() %>
						</td>
						<td><%= s.getTarih() %>
						</td>
						<td><%= s.getBitistarih() %>
						</td>
						<td>
							<a href="siparis/<%= s.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-ok btn btn-info"></button></a>
							<a href="siparis/<%= s.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-remove btn btn-danger"></button></a>
						</td>
					</tr>
					<% } %>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
   
</div>


<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>