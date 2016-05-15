<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="crm.irfan.entity.Genel"%>
<!doctype html>
<html lang="en">
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
	<link rel="stylesheet" href="css/irfan.css">
	<link rel="stylesheet" href="css/fonts.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<%@ include file="logincheck.jsp" %>
	<!-- 
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<span style="text-align: center; font-size: 24px;" class="color-red">
					SESSION MESAJ SISTEMI GELISTIR <br>
					<span style="color:indigo">HATA ve DURUS SEBEPLERI ARAYUZU, MODAL, HIDE GELISTIR </span><br>
					<span style="color:chocolate">PAGING SISTEMI GELISTIR </span><br>
				</span>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<span style="text-align: center; color: darkkhaki; font-size: 24px;">
					{{ Haftalık Faaliyet }}</span>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<span style="text-align: center; color: darkkhaki; font-size: 24px;">
					{{ Haftalık Üretim }}</span>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<span style="text-align: center; color: darkkhaki; font-size: 24px;">
					{{ Haftalık Sipariş }}</span>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<span style="text-align: center; color: darkkhaki; font-size: 24px;">
					{{ Haftalık Montaj }}</span>
			</div>
		</div>
	</div>
	 -->

	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>