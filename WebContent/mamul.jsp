<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List" %>
<!doctype html>
<html lang="en" ng-app="mamulApp">
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
<%
	User user = (User) session.getAttribute("user");
	Boolean loggedin = (Boolean) session.getAttribute("loggedin");
	if (loggedin == null || user == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
%>

<jsp:include page="navigate.jsp">
	<jsp:param value="user" name="user"/>
</jsp:include>

<div class="container" ng-controller="MamulCtrl">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Mamül Bileşen Ekleme</label>
	</div>
	<div class="row" >
		<form class="form-horizontal" role="form" name="mamulform" method="post" action="/HelloWorld/mamul">
			<div class="form-group">
				<label for="mamulad" class="col-xs-3 control-label">Adı: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="mamulad" ng-model="mamulad" placeholder="Mamül Adı">
				</div>
			</div>
			<div class="form-group">
				<label for="mamulkod" class="col-xs-3 control-label">Kodu: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="mamulkod" ng-model="mamulkod" placeholder="Mamül Kodu">
				</div>
			</div>			
			<div class="form-group">
				<label for="mamulcevrim" class="col-xs-3 control-label">Çevrim Süresi(sn): </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="mamulcevrim" ng-model="mamulcevrim" placeholder="Çevrim Süresi">
				</div>
			</div>
			<div class="form-group">
                <label for="mamulfirma" class="col-xs-3 control-label">Tedarikçi: </label>
                <div class="col-xs-8">
                    <select class="form-control" name="mamulfirma">
                        <%
                            List<Firma> firma = (List<Firma>) request.getAttribute("firma");
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
			<hr/>
			<div class="form-group">
				<label class="col-xs-3 control-label"></label>
				<div class="col-xs-8">
					<div class="radio">
						<label>
							<input type="radio" name="optionsRadios" value="Uretim" id="optionsRadios1" checked ng-click="on()">
							Üretim
						</label>
					</div>
					<div class="radio">
						<label>
							<input type="radio" name="optionsRadios" value="Montaj" id="optionsRadios2" ng-click="off()">
							Montaj
						</label>
					</div>
				</div>
			</div>
			<div class="form-group" ng-model="mamulTypeUretim" ng-show="showState()">
				<label for="hammadde" class="col-xs-3 control-label text-success">Hammadde: </label>
				<div class="col-xs-8">
					<select class="form-control" id="hammadde" name="hammadde">
						<%
							List<Bilesen> hammadde = (List<Bilesen>) request.getAttribute("hammadde");
							for (Bilesen h : hammadde) {
						%>
						<option value='<%=h.getId()%>'><%=h.getAd() %>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group" ng-model="mamulTypeMontaj" ng-hide="showState()">
				<label for="yarimamul" class="col-xs-3 control-label text-danger">Yarımamül: </label>
				<div class="col-xs-8">
					<select class="form-control" id="yarimamul" name="yarimamul">
						<%
							List<Bilesen> yarimamul = (List<Bilesen>) request.getAttribute("yarimamul");
							for (Bilesen y : yarimamul) {
						%>
						<option value='<%= y.getId() %>'><%=y.getAd() %>
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
					<select class="form-control" id="birim" name="birim">
						<%
							List<Birim> birim = (List<Birim>) request.getAttribute("birim");
							for (Birim b : birim) {
						%>
						<option value='<%= b.getId() %>'><%=b.getAd() %>
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
					<input type="text" class="form-control" name="miktar" ng-model="yenibilesen.miktar" placeholder="Kullanılan Miktar">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<input type="hidden" ng-model="yenibilesen.yarimamulad" />
					<input type="hidden" ng-model="yenibilesen.hammaddead" />
					<input type="hidden" ng-model="yenibilesen.birimad" />
					<input type="hidden" ng-model="yenibilesen.id" />
					<button type="button" ng-click="saveContact()" class="btn btn-warning">Bileşen Kaydet</button>
				</div>
			</div>
			<table class="table table-striped table-bordered">
			<tr>
				<th>Hammadde/Yarımamül</th>
				<th>Birim</th>
				<th>Tip</th>
				<th>Miktar</th>
				<th>Aksiyon</th>
			</tr>
			<tr ng-repeat="bilesen in bilesenler">
				<td>{{ bilesen.bilesenad }}</td>
				<td>{{ bilesen.birimad }}</td>
				<td>{{ bilesen.uretimtip }}</td>
				<td>{{ bilesen.miktar }}</td>
				<td>
					<a href="#" ng-click="edit(bilesen.id)">Güncelle</a> | 
					<a href="#" ng-click="del(bilesen.id)">Sil</a>
					<input type="hidden" name="uretimtip_{{bilesen.id}}" value="{{ bilesen.uretimtipid }}">
					<input type="hidden" name="bilesen_{{bilesen.id}}" value="{{ bilesen.bilesenid }}">
					<input type="hidden" name="birim_{{bilesen.id}}" value="{{ bilesen.birimid }}">
					<input type="hidden" name="miktar_{{bilesen.id}}" value="{{ bilesen.miktar }}">
				</td>
			</tr>
			</table>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<button type="submit" class="btn btn-warning">Ekle</button>
					<input type="hidden" name="bilesen_length" ng-model="bilesen_length" value="{{ bilesenler.length }}">
				</div>
			</div>

		</form>
	</div>
	<%
		List<Bilesen> mamul = (List<Bilesen>) request.getAttribute("mamul");
		int sayac = 0;
	%>

	<div class="row">
		<div class="container">
			<div class="col-sm-3"></div>
			<div class="col-sm-8">
				<table class="table table-striped table-bordered">
					<% for (Bilesen m : mamul) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<th><label>Kod</label></th>
						<th><label>Adı</label></th>
						<th><label>Çevrim Süresi</label></th>
						<th><label>Aksiyon</label></th>
					</tr>
					<% } %>
					<tr>
						<td><%= m.getId() %>
						</td>
						<td><%= m.getAd() %>
						</td>
						<td><%= m.getCevrimsuresi() %>
						</td>
						<td>
							<a href="/HelloWorld/mamul/<%= m.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-ok btn btn-info"></button>
							</a>
							<a href="/HelloWorld/mamul/<%= m.getId().toString() %>"><button type="submit" class="glyphicon glyphicon-remove btn btn-danger"></button>
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
<script src="js/mamul.js"></script>

</body>
</html>