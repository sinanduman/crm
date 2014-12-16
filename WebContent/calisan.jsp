<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.entity.Calisan, crm.irfan.entity.Genel,java.util.List" %>
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

<%@ include file="logincheck.jsp" %>

<%
	List<Calisan> calisan = (List<Calisan>) request.getAttribute("calisan");
	String message	= (String) request.getAttribute("message");
	String admin	= (String) session.getAttribute("admin");
%>

<div class="container">
	<div class="row text-danger" style="text-align:center;">
		<div class="col-xs-8">
			<label class="text-danger rounded">Çalışan Ekleme</label>
		</div>
	</div>
	<div class="row">
		<form class="form-horizontal" method="post" name="calisanform" id="calisanform" action="calisan">
			<div class="form-group col-xs-8">
				<label for="ad" class="col-xs-3 control-label text-baslik">Adı: </label>
				<div>
					<input type="text" class="form-control big" name="ad" id="ad" placeholder="Adı" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="soyad" class="col-xs-3 control-label text-baslik">Soyadı: </label>
				<div>
					<input type="text" class="form-control big" name="soyad" id="soyad" placeholder="Soyadı" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="gorev" class="col-xs-3 control-label text-baslik">Görevi: </label>
				<div>
					<input type="text" class="form-control big" name="gorev" id="gorev" placeholder="Görevi" autocomplete="off">
				</div>
			</div>
			<% 
			if(admin!=null && admin.equals("1")){
			%>
			<div class="form-group col-xs-8">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div>
					<button type="submit" class="btn btn-danger">Ekle</button>
				</div>
			</div>
			<% } %>
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
				<% for (Calisan c : calisan) { %>
				<% if (sayac++ == 0) { %>
				<tr>
					<td><label class="text-success">Adı</label></td>
					<td><label class="text-success">Soyadı</label></td>
					<td><label class="text-success">Görev</label></td>
					<% 
					if(admin!=null && admin.equals("1")){
					%>
					<td><label class="text-success">Aksiyon</label></td>
					<% } %>
				</tr>
				<% } %>
				<form name="action_form<%=c.getId()%>" id="action_form<%=c.getId()%>">
				<tr id="tr<%=c.getId() %>">
					<td><input type="text" class="form-control" value="<%= c.getAd() %>" name="liste_calisanad" id="liste_calisanad" autocomplete="off"></td>
					<td><input type="text" class="form-control" value="<%= c.getSoyad() %>" name="liste_calisansoyad" id="liste_calisansoyad" autocomplete="off"></td>
					<td><input type="text" class="form-control" value="<%= c.getGorev() %>" name="liste_calisangorev" id="liste_calisangorev" autocomplete="off"></td>
					<% 
					if(admin!=null && admin.equals("1")){
					%>
					<td>
						<div id="div<%= c.getId() %>" class="text-center">
							<a href="javascript:updateCalisanGo('calisan',<%=c.getId()%>,document.action_form<%=c.getId()%>,1);" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a> 
							<a href="javascript:deleteCalisanGo('calisan',<%=c.getId()%>,document.action_form<%=c.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
						</div>
					</td>
					<% } %>
				</tr>
				</form>
				<% } %>
			</table>
			<jsp:include page="paging.jsp" flush="true" >
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="calisan" name="pagename"/>
			</jsp:include>
		</div>
		<div class="col-sm-1"></div>
	</div>


</div>


<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/irfan.js" type="text/javascript"></script>
<script	src="js/calisan.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>

<script>
$(".calisan").attr("readonly","readonly");
$('.calisan').blur(function(){
	$(this).attr("readonly","readonly");
});
$('.calisan').click(function(){
	$(this).removeAttr("readonly");
});
</script>
</body>

</html>
