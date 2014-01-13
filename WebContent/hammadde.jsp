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
	<title>Irfan Plastik</title>

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="css/irfan.css?<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" href="css/docs.css">
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

<%
	Boolean loggedin = (Boolean) session.getAttribute("loggedin");
	if (loggedin == null || !loggedin) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	User user = (User) session.getAttribute("user");
%>
<%
	List<Firma> firma = (List<Firma>) request.getAttribute("firma");
	List<Bilesen> hammadde = (List<Bilesen>) request.getAttribute("hammadde");
%>
<jsp:include page="navigate.jsp">
	<jsp:param value="user" name="user"/>
</jsp:include>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Hammadde Ekleme</label>
	</div>
	<div class="row" ng-controller="HammaddeEkleCtrl">
		<form class="form-horizontal" role="form" name="hammadde_form" method="post" action="/HelloWorld/hammadde">
			<div class="form-group">
				<label for="hamkod" class="col-xs-3 control-label">Hammadde Kodu: </label>

				<div class="col-xs-8">
					<input type="text" class="form-control" name="hamkod" ng-model="hamkod" placeholder="Hammadde Kodu">
				</div>
			</div>
			<div class="form-group">
				<label for="hamad" class="col-xs-3 control-label">Hammadde Adı: </label>

				<div class="col-xs-8">
					<input type="text" class="form-control" name="hamad" ng-model="hamad" placeholder="Hammadde Adı">
				</div>
			</div>
			<div class="form-group">
				<label for="hambirim" class="col-xs-3 control-label">Birim: </label>

				<div class="col-xs-8">
					<select class="form-control" name="hambirim">
						<%							
							for (BirimTip bt : BirimTip.values()) {
								if (BirimTip.KILOGRAM.value() == bt.value() ){
									%>
									<option value='<%=bt.value() %>' selected><%=bt.ad() %></option>
									<%
								}
								else{
									%>
									<option value='<%=bt.value()%>'><%=bt.ad() %></option>
									<%
								}
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="hamfirma" class="col-xs-3 control-label">Tedarikçi: </label>
				
				<div class="col-xs-8">
					<select class="form-control" name="hamfirma">
						<%
							for (Firma f : firma) {
						%>
						<option value='<%=f.getId()%>'><%=f.getAd() %>
						</option>
						<%
							}
						%>
					</select>
				</div>

			</div>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<button type="button" class="btn btn-warning" ng-click="hammaddeEkle()" >Ekle</button>
				</div>
			</div>
		</form>
	</div>
	
	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-3"></div>
			<div class="col-sm-8">
				<table class="tableplan">
					<% int sayac = 0; %>
					<% for (Bilesen h : hammadde) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<td><label class="text-success">Hammadde Kodu</label></td>
						<td><label class="text-success">Hammadde Adı</label></td>
						<td><label class="text-success">Birim</label></td>
						<td><label class="text-success">Tedarikçi</label></td>
						<td><label class="text-success">Aksiyon</label></td>
					</tr>
					<% } %>
					<form name="action_form<%=h.getId()%>" id="action_form<%=h.getId()%>">
					<tr id="tr<%=h.getId()%>">
						<td><input type="text" value="<%= h.getKod() %>" name="liste_kod" id="liste_kod"></td>
						<td><input type="text" value="<%= h.getAd() %>" name="liste_ad" id="liste_ad"></td>
						<td>
							<select id="liste_birimid" name="liste_birimid">
							<%
								for (BirimTip bt : BirimTip.values()) {
									if (bt.value() == h.getBirimid() ){
										%>
										<option value='<%=bt.value() %>' selected><%=bt.ad()%></option>
										<%
									}
									else{
										%>
										<option value='<%=bt.value()%>'><%=bt.ad() %></option>
										<%
									}
								}
							%>
							</select>
							<%-- h.getBirimad() --%>
						</td>
						<td>
							<select id="liste_firmaid" name="liste_firmaid">
							<%
								for (Firma f : firma) {
									if (f.getId() == h.getFirmaid()){
										%>
										<option value='<%=f.getId() %>' selected><%=f.getAd() %></option>
										<%
									}
									else{
										%>
										<option value='<%=f.getId()%>'><%=f.getAd() %></option>
										<%
									}
								}
							%>
							</select>						
						</td>
						<td>
							<input class="updateHref" type="button" id="updateButton<%=h.getId()%>" value="Gün. &rarr;" onclick="javascript:updateHammaddeGo('/HelloWorld/hammadde',<%=h.getId()%>,document.action_form<%=h.getId()%>,1);">
							<input class="deleteHref" type="button" id="deleteButton<%=h.getId()%>" value="Sil &rarr;" onclick="javascript:deleteHammaddeGo('/HelloWorld/hammadde',<%=h.getId()%>,document.action_form<%=h.getId()%>,3);">
						</td>
					</tr>
					</form>
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
<script src="js/irfan.js?<%=System.currentTimeMillis()%>"></script>
<script	src="js/hammadde.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
</body>

</html>