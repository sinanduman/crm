<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en" ng-app="mamulapp">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="img/favicon.ico">
	<link href='http://fonts.googleapis.com/css?family=Dosis:400,500,600,300' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Roboto:100,400,300,500,700' rel='stylesheet' type='text/css'>

	<title>Irfan Plastik</title>

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="css/docs.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List" %>

<%
	Boolean loggedin = (Boolean) session.getAttribute("loggedin");
	if (loggedin == null || !loggedin) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	User user = (User) session.getAttribute("user");
%>

<jsp:include page="navigate.jsp">
	<jsp:param value="user" name="user"/>
</jsp:include>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-warning">Hammadde Ekleme</label>
	</div>
	<div class="row">
		<form class="form-horizontal" role="form" method="post" action="/HelloWorld/mamul">
			<div class="form-group">
				<label for="mamulad" class="col-xs-3 control-label">Adı: </label>

				<div class="col-xs-8">
					<input type="text" class="form-control" name="mamulad" ng-model="mamulad" placeholder="Mamül Adı">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-3 control-label"></label>

				<div class="col-xs-8">
					<div class="radio">
						<label>
							<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
							Üretim
						</label>
					</div>
					<div class="radio">
						<label>
							<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
							Montaj
						</label>
					</div>
				</div>
			</div>

			<hr class="divider"/>

			<div class="form-group">
				<label for="hammadde" class="col-xs-3 control-label">Hammadde: </label>

				<div class="col-xs-8">
					<select class="form-control" name="hammadde">
						<%
							List<Hammadde> hammadde = (List<Hammadde>) request.getAttribute("hammadde");
							for (Hammadde h : hammadde) {
						%>
						<option value='<%=h.getId()%>'><%=h.getAd() %>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="yarimamul" class="col-xs-3 control-label">Yarımamül: </label>

				<div class="col-xs-8">
					<select class="form-control" name="yarimamul">
						<%
							List<YariMamul> yarimamul = (List<YariMamul>) request.getAttribute("yarimamul");
							for (YariMamul y : yarimamul) {
						%>
						<option value='<%=y.getId()%>'><%=y.getAd() %>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="birim" class="col-xs-3 control-label">Birim: </label>

				<div class="col-xs-8">
					<select class="form-control" name="birim">
						<%
							List<Birim> birim = (List<Birim>) request.getAttribute("birim");
							for (Birim b : birim) {
						%>
						<option value='<%=b.getId()%>'><%=b.getAd() %>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="miktar" class="col-xs-3 control-label">Miktar: </label>

				<div class="col-xs-8">
					<input type="text" class="form-control" name="miktar" ng-model="miktar" placeholder="Kullanılan Miktar">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>

				<div class="col-xs-8">
					<button type="submit" class="btn btn-warning">Bileşen Ekle</button>
				</div>
			</div>
		</form>
	</div>
	<%
		List<Mamul> mamul = (List<Mamul>) request.getAttribute("mamul");
		int sayac = 0;
	%>

	<div class="row">
		<div class="container">
			<div class="col-sm-3"></div>
			<div class="col-sm-8">
				<table class="table table-striped table-bordered">
					<% for (Mamul m : mamul) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<th><label>Kod</label></th>
						<th><label>Adı</label></th>
						<th><label>Aksiyon</label></th>
					</tr>
					<% } %>
					<tr>
						<td><%= m.getId() %>
						</td>
						<td><%= m.getAd() %>
						</td>
						<td>
							<a href="/HelloWorld/mamul/<%= m.getId().toString() %>">
								<button type="submit" class="glyphicon glyphicon-ok btn btn-info"></button>
							</a>
							<a href="/HelloWorld/mamul/<%= m.getId().toString() %>">
								<button type="submit" class="glyphicon glyphicon-remove btn btn-danger"></button>
							</a>
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