<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List" %>

<%@ include file="logincheck.jsp" %>

<%
	List<Firma> firma = (List<Firma>) request.getAttribute("firma");
	String message = (String) request.getAttribute("message");
%>

<div class="container">
	<div class="row text-danger" style="text-align:center;">
		<div class="col-xs-8">
			<label class="text-danger rounded">Firma Ekleme</label>
		</div>
	</div>
	<div class="row">
		<form class="form-horizontal" method="post" action="firma">
			<div class="form-group col-xs-8">
				<label for="adsoy" class="col-xs-3 control-label text-baslik">Firma Ekleme: </label>
				<div>
					<input type="text" class="form-control big" name="firma_ad" id="firma_ad" placeholder="Firma Adı" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="gorev" class="col-xs-3 control-label text-baslik">Telefonu: </label>
				<div>
					<input type="text" class="form-control big" name="firma_tel" id="firma_tel" placeholder="Firma Telefonu" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="gorev" class="col-xs-3 control-label text-baslik">Adresi: </label>
				<div>
					<input type="text" class="form-control big" name="firma_adres" id="firma_adres" placeholder="Firma Adresi" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div>
					<button type="submit" class="btn btn-danger">Ekle</button>
				</div>
			</div>
		</form>
	</div>
	<!-- ALERT BOX -->
	<% 
	if(message.equals("0")) {
	    %>
		<div class="row col-xs-8 alert alert-success">
			<strong>Başarılı</strong> olarak eklendi!
		</div>
		<%
	}
	else if(!message.equals("") && message.length()>1){
	    %>
		<div class="row col-xs-8 alert alert-danger">
			<strong>Hata!</strong><%= message %>
		</div>
		<%
	}
	%>
	<!-- ALERT BOX -->
	<%
		int sayac = 0;
	%>
	<div class="row" style="font-size:12px;">
		<div class="col-sm-8">
			<table class="tableplan">
				<% for (Firma f : firma) { %>
				<% if (sayac++ == 0) { %>
				<tr>
					<td><label class="text-success">Firma Ad</label></td>
					<td><label class="text-success">Telefon</label></td>
					<td><label class="text-success">Adres</label></td>
					<td><label class="text-success">Aksiyon</label></td>
				</tr>
				<% } %>
				<form name="action_form<%=f.getId()%>" id="action_form<%=f.getId()%>">
				<tr id="tr<%=f.getId() %>">
					<td><input type="text" class="form-control" value="<%= f.getAd() %>" name="liste_firmaad" id="liste_firmaad" autocomplete="off"></td>
					<td><input type="text" class="form-control" value="<%= f.getTelefon() %>" name="liste_firmatel" id="liste_firmatel" autocomplete="off"></td>
					<td><input type="text" class="form-control" value="<%= f.getAdres() %>" name="liste_firmaadres" id="liste_firmaadres" autocomplete="off"></td>
					<td>
						<div id="div<%= f.getId() %>" class="text-center">
							<a href="javascript:updateFirmaGo('firma',<%=f.getId()%>,document.action_form<%=f.getId()%>,1);" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a> 
							<a href="javascript:deleteFirmaGo('firma',<%=f.getId()%>,document.action_form<%=f.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
						</div>
					</td>
				</tr>
				</form>
				<% } %>
			</table>
			<jsp:include page="paging.jsp" flush="true" >
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="firma" name="pagename"/>
			</jsp:include>
		</div>
		<div class="col-sm-1"></div>
	</div>


</div>

<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/irfan.js" type="text/javascript"></script>
<script	src="js/firma.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
</body>

</html>
