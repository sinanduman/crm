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
<%
	User user = (User) session.getAttribute("user");
	Boolean loggedin = (Boolean) session.getAttribute("loggedin");
	if (loggedin == null || user == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
%>

<%
	List<Firma> firma = (List<Firma>) request.getAttribute("firma");
	List<Bilesen> hammadde = (List<Bilesen>) request.getAttribute("hammadde");
	List<Bilesen> yarimamul = (List<Bilesen>) request.getAttribute("yarimamul");
	List<Bilesen> mamul = (List<Bilesen>) request.getAttribute("mamul");
	List<MamulBilesen> mamulbilesen = (List<MamulBilesen>) request.getAttribute("mamulbilesen");
%>

<jsp:include page="navigate.jsp">
	<jsp:param value="user" name="user"/>
</jsp:include>

<div class="container" ng-controller="MamulCtrl">
	<div class="row text-danger" style="text-align:center;">
		<label class="text-danger">Mamül Ekleme</label>
	</div>
	<div class="row" >
		<form class="form-horizontal" role="form" name="mamulform" method="post" action="/irfanpls/mamul">
			
			<div class="form-group">
				<label for="mamulkod" class="col-xs-3 control-label">Mamül Kodu: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="mamulkod" ng-model="mamulkod" placeholder="Mamül Kodu">
				</div>
			</div>			
			<div class="form-group">
				<label for="mamulad" class="col-xs-3 control-label">Mamül Adı: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="mamulad" ng-model="mamulad" placeholder="Mamül Adı">
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
				<label for="mamuluretimsekli" class="col-xs-3 control-label">Üretim Şekli: </label>
				<div class="col-xs-8">
					<select class="form-control" name="mamuluretimsekli">
						<option value='<%= UretimTip.Makina.value() %>'><%= UretimTip.Makina.name() %></option>
						<option value='<%= UretimTip.Montaj.value() %>'><%= UretimTip.Montaj.name() %></option>
					</select>
				</div>
			</div>
			<hr/>
			<div class="form-group">
				<label class="col-xs-3 control-label"></label>
				<div class="col-xs-8">
					<div class="radio">
						<label class="text-success strong">
							<input type="radio" name="bilesenTipRadio" value="Hammadde" checked ng-click="on()">
							Hammadde
						</label>
					</div>
					<div class="radio">
						<label class="text-danger strong">
							<input type="radio" name="bilesenTipRadio" value="Yarımamül" ng-click="off()">
							Yarımamül
						</label>
					</div>
				</div>
			</div>
			<div class="form-group" ng-model="mamulTypeUretim" ng-show="showState()">
				<label for="hammadde" class="col-xs-3 control-label text-success">Hammadde: </label>
				<div class="col-xs-8">
					<select class="form-control" id="hammadde" name="hammadde">
						<%
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
				<label for="birimid" class="col-xs-3 control-label">Birim: </label>
				<div class="col-xs-8">
					<select class="form-control" id="birimid" name="birimid">
						<%
							for (BirimTip bt : BirimTip.values()) {
								%>
								<option value='<%=bt.value()%>'><%=bt.ad() %></option>
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
					<button type="button" ng-click="saveBilesen()" name="bilesen_ekle" id="bilesen_ekle"  class="btn btn-warning">Bileşen Ekle</button>
				</div>
			</div>
			<table class="tableplan">
			<tr>
				<td class="text-success">Hammadde/Yarımamül</td>
				<td class="text-success">Birim</td>
				<td class="text-success">Miktar</td>
				<td class="text-success">Tip</td>
				<td class="text-success">Aksiyon</td>
			</tr>
			<tr ng-repeat="bilesen in bilesenler">
				<td>{{ bilesen.bilesenad }}</td>
				<td>{{ bilesen.birimad }}</td>
				<td>{{ bilesen.miktar }}</td>
				<td>{{ bilesen.uretimtip }}</td>
				<td>
					<a href="#" ng-click="edit(bilesen.id)">Güncelle</a> | <a href="#" ng-click="del(bilesen.id)">Sil</a>
					<input type="hidden" name="uretimtip_{{bilesen.id}}" value="{{ bilesen.uretimtipid }}">
					<input type="hidden" name="bilesenid_{{bilesen.id}}" value="{{ bilesen.bilesenid }}">
					<input type="hidden" name="miktar_{{bilesen.id}}" value="{{ bilesen.miktar }}">
					<input type="hidden" name="birimid_{{bilesen.id}}" value="{{ bilesen.birimid }}">
				</td>
			</tr>
			</table>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<button type="button" class="btn btn-warning" ng-click="saveMamul()">Mamül Ekle</button>
					<input type="hidden" name="bilesen_length" ng-model="bilesen_length" value="{{ bilesenler.length }}">
				</div>
			</div>

		</form>
	</div>

	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<table class="tableplan">
					<% int sayac = 0; %>
					<% for (Bilesen m : mamul) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<td><label class="text-success">Kod</label></td>
						<td><label class="text-success">Adı</label></td>
						<td><label class="text-success">Çevrim Süresi</label></td>
						<td><label class="text-success">Firma</label></td>
						<td><label class="text-success">Aksiyon</label></td>
					</tr>
					<% } %>
					<form name="action_form<%=m.getId()%>" id="action_form<%=m.getId()%>">
					<tr id="tr<%=m.getId()%>">
						<td><input type="text" value="<%= m.getKod() %>" name="liste_kod" id="liste_kod"></td>
						<td><input type="text" value="<%= m.getAd() %>" name="liste_ad" id="liste_ad"></td>
						<td><input type="text" value="<%= m.getCevrimsuresi() %>" name="liste_cevrimsuresi" id="liste_cevrimsuresi" style="width:80px;"></td>
						<td>
							<select id="liste_firmaid" name="liste_firmaid">
							<%
								for (Firma f : firma) {
									if (f.getId() == m.getFirmaid() ){
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
							<input class="icerikHref" type="button" value="İçerik &darr;" onclick="javascript:hideshow('#tr_mam_detay<%= m.getId() %>');">
							<input class="updateHref" type="button" id="updateButton<%=m.getId()%>" value="Gün. &rarr;" onclick="javascript:updateMamulGo('/irfanpls/mamul',<%=m.getId()%>,document.action_form<%=m.getId()%>,1);">
							<input class="deleteHref" type="button" id="deleteButton<%=m.getId()%>" value="Sil &rarr;" onclick="javascript:deleteMamulGo('/irfanpls/mamul',<%=m.getId()%>,document.action_form<%=m.getId()%>,3);">
						</td>
					</tr>
					<tr id="tr_mam_detay<%= m.getId() %>" style="display:none;">
						<td colspan="5">
							<table style="width:100%;" class="tableplan">
								<% int sayacbilesen = 0; %>
								<% for (MamulBilesen j : mamulbilesen) {%>
								<% if ( j.getMamulid() == m.getId() ){ %>
								<% if ( (sayacbilesen++ == 0) ){ %>
								<tr>
									<td><label class="text-info">Sıra No</label></td>
									<td><label class="text-info">Bileşen Adı</label></td>
									<td><label class="text-info">Bileşen Kodu</label></td>
									<td><label class="text-info">Tür</label></td>
									<td><label class="text-info">Birim</label></td>
									<td><label class="text-info">Miktarı</label></td>
								</tr>
								<% } %><%-- if tablo baslik --%>
								<tr>
									<td><%= sayacbilesen %></td>
									<td><%= j.getBilesenad() %></td>
									<td><%= j.getBilesenkod() %></td>
									<td><%= j.getBilesentipad() %></td>
									<td><%= j.getBirimad() %></td>
									<td><%= j.getMiktar() %></td>
								</tr>
								<% } %><%-- if  j== m--%>
								<% } %> <%-- mamulbilesen --%>
							</table>
						</td>
					</tr>
					</form>
					<% } %> <%-- mamul --%>
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
<script src="js/mamul.js?<%=System.currentTimeMillis()%>"></script>
</body>
</html>