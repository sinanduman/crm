<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List" %>
<!doctype html>
<html lang="en" ng-app="irsaliyeApp">
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
	
	<script src="js/angular.min.js"></script>
	<script src="js/angular-route.min.js"></script>
	<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/irfan.js?<%= System.currentTimeMillis() %>"></script>
	<script src="js/irsaliye.js?<%= System.currentTimeMillis() %>"></script>
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

<%	
	List<Irsaliye> irsaliye = (List<Irsaliye>) request.getAttribute("irsaliye");
	List<IrsaliyeBilesen> irsaliyebilesen = (List<IrsaliyeBilesen>) request.getAttribute("irsaliyebilesen");
	List<Bilesen> mamul = (List<Bilesen>) request.getAttribute("mamul");
	List<Firma> firma = (List<Firma>) request.getAttribute("firma");
	List<Birim> birim = (List<Birim>) request.getAttribute("birim");
%>


<script>
	var mamuldb = [
	<%
	String delimeter = "";
	for (Bilesen b : mamul) {
		out.println(delimeter 
		+ " { id:" + b.getId()		
		+ ",mamulid:" + b.getId() 
		+ ",mamulad:'" + b.getAd() 
		+ "',birimid:" + b.getBirimid()
		+ ",birimad:'" + b.getBirimad()
		+ "',firmaid:" + b.getFirmaid()
		+ ",firmaad:'" + b.getFirmaad() + "'}");
		delimeter = ",";
	}%>];
</script>

<div class="container" ng-controller="IrsaliyeCtrl">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger">Sevk Edilecek Mamül</label>
	</div>
	<div class="row" >
		<form class="form-horizontal" role="form" name="irsaliyeform" method="post" action="/irfanpls/irsaliye">
			
			<div class="form-group">
				<label for="mamulid" class="col-xs-3 control-label">Mamül adı: </label>
				<div class="col-xs-8">
					<select class="form-control" id="mamulid" name="mamulid" ng-model="mamulid" ng-init="mamulid=select()">
						<option ng-repeat="mam in mamulJSON" ngSelected="true" value="{{mam.id}}">{{mam.mamulad}} - [{{mam.firmaad}}]</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="miktar" class="col-xs-3 control-label">Miktar: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="miktar" ng-model="yeniirsaliye.miktar" placeholder="Miktar (<%= BirimTip.ADET.ad() %>)">
				</div>
			</div>			
			<div class="form-group" style="display:none;">
				<label for="firmaiddd" class="col-xs-3 control-label">Tedarikçi: </label>
				<div class="col-xs-8">
					<select class="form-control" name="firmaiddd" id="firmaiddd">
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
			<hr/>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<input type="hidden" ng-model="yeniirsaliye.firmaid" />
					<input type="hidden" ng-model="yeniirsaliye.firmaad" />
					<input type="hidden" ng-model="yeniirsaliye.mamulad" />
					<input type="hidden" ng-model="yeniirsaliye.birimad" />
					<input type="hidden" ng-model="yeniirsaliye.id" />
					<button type="button" ng-click="saveIrsaliye()" name="pakete_ekle" id="pakete_ekle" class="btn btn-warning">Pakete Ekle</button>
				</div>
			</div>
			<table class="tableplan">
			<tr>
				<td class="text-success">Mamül</td>
				<td class="text-success">Miktar</td>
				<td class="text-success">Firma</td>
				<td class="text-success">Aksiyon</td>
			</tr>
			<tr ng-repeat="irs in irsaliye">
				<td>{{ irs.mamulad }}</td>
				<td>{{ irs.miktar }} (Adet)</td>
				<td>{{ irs.firmaad }}</td>
				<td>
					<a href="#" ng-click="edit(irs.id)">Güncelle</a> | <a href="#" ng-click="del(irs.id)">Sil</a>
					<input type="hidden" name="mamul_{{irs.id}}" value="{{ irs.mamulid }}">
					<input type="hidden" name="miktar_{{irs.id}}" value="{{ irs.miktar }}">
					<input type="hidden" name="firma_{{irs.id}}" value="{{ irs.firmaid }}">
				</td>
			</tr>
			</table>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<button type="button" class="btn btn-warning" ng-click="savePaket()">Ekle</button>
					<input type="hidden" name="irsaliye_length" ng-model="irsaliye_length" value="{{ irsaliye.length }}">
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
					<% for (Irsaliye i : irsaliye) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<td><label class="text-success">İrsaliye No</label></td>
						<td><label class="text-success">Oluşturma Tarihi</label></td>
						<td><label class="text-success">Gönderim Tarihi</label></td>
						<td><label class="text-success">Aksiyon</label></td>
					</tr>
					<% } %>
					<form name="action_form<%=i.getId()%>" id="action_form<%=i.getId()%>">
					<tr id="tr<%=i.getId()%>">
						<td><%= i.getIrsaliyeno() %></td>
						<td><%= i.getOlusturmatarihi() %></td>
						<td><%= i.getGonderimtarihi() %></td>
						<td>
							<input class="icerikHref" type="button" value="İçerik &darr;" onclick="javascript:hideshow('#tr_irs_detay<%= i.getId() %>');">
							<input class="deleteHref" type="button" value="Sil &rarr;" onclick="javascript:deleteGoIrsaliyePaket('/irfanpls/irsaliye',<%=i.getId()%>,<%=i.getIrsaliyeno()%>,3);">
						</td>
					</tr>
					<tr id="tr_irs_detay<%= i.getId() %>" style="display:none;">
						<td colspan="4">
							<table style="width:100%;" class="tableplan">
								<% int sayacbilesen = 0; %>
								<% for (IrsaliyeBilesen j : irsaliyebilesen) {%>
								<% if ( j.getIrsaliyeid() == i.getId() ){ %>
								<% if ( (sayacbilesen++ == 0) ){ %>
								<tr>
									<td><label class="text-info">Sıra No</label></td>
									<td><label class="text-info">Mamül Adı</label></td>
									<td><label class="text-info">Mamül Kodu</label></td>
									<td><label class="text-info">Miktarı</label></td>
								</tr>
								<% } %><%-- if tablo baslik --%>
								<tr>
									<td><%= sayacbilesen %></td>
									<td><%= j.getMamulad() %></td>
									<td><%= j.getMamulkod() %></td>
									<td><%= j.getMiktar() %></td>
								</tr>
								<% } %><%-- if  i== j --%>
								<% } %><%-- irsaliyebilesen --%>
							</table>
						</td>
					</tr>
					</form>
					<% } %> <%-- irsaliye --%>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>

</div>
</body>
</html>