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
	<link rel="stylesheet" href="css/siparis.css">
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

<jsp:include page="navigate.jsp">
	<jsp:param value="user" name="user"/>
</jsp:include>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Yarımamül Stok Ekleme</label>
	</div>
	<script type="text/javascript">
		function HammaddeEkleCtrl($scope, $http) {
			$scope.hammaddeEkle = function () {
				return false;
				//alert($scope.adsoy + " : " + $scope.gorev);
				console.log($scope.adsoy + ' : ' + $scope.gorev);
				$.ajax({
					url: '/HelloWorld/ajaxutils',
					method: 'post',
					crossDomain: true,
					data: {username: $scope.adsoy, password: $scope.gorev},
					headers: {
						Accept: "text/plain; charset=utf-8", "Content-Type": "application/x-www-form-urlencoded; charset=utf-8",
					},
					success: function (data, textStatus, xhr) {
						console.log(data);
					},
					error: function (xhr, textStatus, errorThrown) {
						console.log("Hata Oluştu: " + textStatus + " , " + errorThrown);
					}
				}).done(function (msg) {
							if (msg) {
								console.log(name + " ürünün Stok bilgisi başarıyla GÜNCELLENDİ");
							}
							else {
								console.log("Hata: " + msg);
							}
						});
			}
		}
	</script>
	<div class="row" ng-controller="HammaddeEkleCtrl">
		<form class="form-horizontal" role="form" method="post" action="/HelloWorld/yarimamulstok">
			
			<div class="form-group">
				<label for="bilesenid" class="col-xs-3 control-label">Yarımamül: </label>
				<div class="col-xs-8">
					<select class="form-control" id="bilesenid" name="bilesenid">
						<%
							List<Bilesen> yarimamul = (List<Bilesen>) request.getAttribute("yarimamul");
							for (Bilesen y : yarimamul) {
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
				<label for="miktar" class="col-xs-3 control-label">Miktarı: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="miktar" ng-model="miktar" placeholder="Miktar (<%= BirimTip.ADET.ad() %>)">
				</div>
			</div>
			<div class="form-group">
				<label for="irsaliyeno" class="col-xs-3 control-label">İrsaliye No: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="irsaliyeno" ng-model="irsaliyeno" placeholder="İrsaliye">
				</div>
			</div>
			<div class="form-group">
				<label for="lot" class="col-xs-3 control-label">LOT/Batch No: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="lot" ng-model="lot" placeholder="LOT/Batch No:">
				</div>
			</div>
			<div class="form-group">
				<label for="not" class="col-xs-3 control-label">Not: </label>
				<div class="col-xs-8">
					<textarea name="not" id="not" ng-model="not" cols="30" rows="5"  class="form-control" placeholder="Not"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>

				<div class="col-xs-8">
					<button type="submit" class="btn btn-warning">Ekle</button>
				</div>
			</div>
		</form>
	</div>

</div>


<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/siparis.js" type="text/javascript"></script>
</body>

</html>