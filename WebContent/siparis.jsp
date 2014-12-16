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
<%@ page import="crm.irfan.entity.Bilesen, crm.irfan.entity.Genel, crm.irfan.entity.ResultTip" %>
<%@ page import="crm.irfan.entity.Siparis" %>
<%@ page import="java.util.List" %>

<%@ include file="logincheck.jsp" %>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Sipariş Alma</label>
	</div>
	<div class="row" ng-controller="SiparisEkleCtrl">
		<form class="form-horizontal" role="form" name="siparisform"  id="siparisform" method="post" action="siparis">

			<div class="form-group">
				<label for="bilesen" class="col-xs-3 control-label">Mamül: </label>
				<div class="col-xs-8">
					<select class="form-control" id="bilesen" name="bilesen">
						<%
							List<Bilesen> bilesen = (List<Bilesen>) request.getAttribute("bilesen");
							for (Bilesen b : bilesen) {
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
				<label for="miktar" class="col-xs-3 control-label">Miktarı: </label>

				<div class="col-xs-8">
					<input type="text" class="form-control" name="miktar" id="miktar" ng-model="miktar" placeholder="Miktar">
				</div>
			</div>
			<div class="form-group">
				<label for="not" class="col-xs-3 control-label">Not: </label>
				<div class="col-xs-8">
					<textarea name="not" id="not" cols="30" rows="4" class="form-control" placeholder="Not"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>

				<div class="col-xs-8">
					<button type="button" class="btn btn-warning" ng-click="saveSiparis()">Ekle</button>
				</div>
			</div>
		</form>
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

	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-3"></div>
			<div class="col-sm-8">
				<table class="tableplan">
					<% int sayac = 0; %>
					<% if (result.value() != ResultTip.NORESULT.value()) { %>
					<% for (Siparis s : siparis) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<td><label class="text-info">Mamül Adı</label></td>
						<td><label class="text-info">Sipariş Adedi</label></td>
						<td><label class="text-info">Sipariş Tarihi</label></td>
						<td><label class="text-info">Not</label></td>
					</tr>
					<% } %>
					<tr>
						<td><%= s.getBilesenad() %>
						</td>
						<td><%= s.getMiktar() %>
						</td>
						<td><%= s.getTarih() %>
						</td>
						<td><%= s.getNot() %>
						</td>
					</tr>
					<% } %>
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
<script src="js/irfan.js?<%= System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="js/siparis.js?<%= System.currentTimeMillis() %>" type="text/javascript"></script>
</body>

</html>