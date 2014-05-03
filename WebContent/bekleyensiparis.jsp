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
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List" %>

<%@ include file="logincheck.jsp" %>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Üretimde/Bekleyen Siparişler</label>
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

	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<table  class="tableplan">
					<% int sayac = 0; %>				
					<% for (Siparis s : siparis) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<td style="text-align:right;"><label class="text-success">Sip No&nbsp;</label></td>
						<td><label class="text-success">Mamül Adı</label></td>
						<td><label class="text-success">Sipariş Adedi</label></td>
						<td><label class="text-success">Planlanan</label></td>
						<td><label class="text-success">Üretilen/Kalan</label></td>
						<td><label class="text-success">Sipariş Tarihi</label></td>
						<td><label class="text-success">Aksiyon</label></td>
					</tr>
					<% } %>
					<tr>
						<td style="text-align:right;"><%= s.getId() %>&nbsp;</td>
						<td><%= s.getBilesenad() %></td>
						<td><%= s.getMiktar() %></td>
						<td><%= s.getPlanmiktar() %></td>
						<td><%= s.getTamammiktar() %>/<%= s.getPlanmiktar()-s.getTamammiktar() %></td>
						<td><%= s.getTarih() %></td>
						<td>
							<%
							String disabled = "";
							if(s.getPlanmiktar()!=0 || s.getTamammiktar()!=0 ){
								disabled = "disabled";	
							}
							%>
							<% %>
							<input class="okHref" type="button" id="okButton<%=s.getId()%>" value=" Tam. &rarr; " onclick="javascript:okGo('uretimtakip',<%=s.getId()%>,document.action_form<%=s.getId()%>,2);">
							<input <%= disabled %> class="deleteHref" type="button" id="deleteButton<%=s.getId()%>" value=" Sil &rarr; " onclick="javascript:deleteGo('uretimtakip',<%=s.getId()%>,document.action_form<%=s.getId()%>,3);">
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
<script src="js/siparis.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
</body>

</html>