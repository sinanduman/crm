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
	<link rel="stylesheet" href="css/irfan.css?<%=System.currentTimeMillis()%>">
	<link rel="stylesheet" href="css/fonts.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.css">
	<link rel="stylesheet/less" type="text/css" href="less/datepicker.less">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%@ page import="crm.irfan.entity.*, java.util.List" %>

<%@ include file="logincheck.jsp" %>

<%
	List<Firma> firma = (List<Firma>) request.getAttribute("firma");
	List<Bilesen> hammadde = (List<Bilesen>) request.getAttribute("hammadde");
	Long token		= (Long) request.getAttribute("token");
	String message	= (String) request.getAttribute("message");
	String admin	= (String) session.getAttribute("admin");
%>

<div class="container">
	<form class="form-horizontal" name="hammadde_form" id="hammadde_form" method="post" action="hammadde" onsubmit="return false;">
	<div class="row text-warning" style="text-align:center;">
		<div class="col-xs-8">
			<label class="text-danger rounded">Hammadde ve Yarı Mamül Tanımı</label>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-8">
			<div class="form-group">
				<label for="hamkod" class="col-xs-3 control-label text-baslik">Malzeme Kodu: </label>
				<div>
					<input type="text" class="form-control big" name="hamkod" id="hamkod" placeholder="Malzeme Kodu" autocomplete="off">
				</div>
			</div>
			
			<div class="form-group">
				<label for="hamad" class="col-xs-3 control-label text-baslik">Malzeme Adı: </label>
				<div>
					<input type="text" class="form-control big" name="hamad" id="hamad" placeholder="Malzeme Adı" autocomplete="off">
				</div>
			</div>
			
			<div class="form-group">
				<label for="hamtip" class="col-xs-3 control-label text-baslik">Malzeme Tipi: </label>
				<div>
					<select class="form-control big" name="hamtip" id="hamtip" >
						<%							
							for (BilesenTip bt : BilesenTip.values()) {
								if (BilesenTip.MAMUL.value() != bt.value() ){
								    if(bt.value() != BilesenTip.HAMMADDEYARIMAMUL.value()){
								        out.println("<option value='"+ bt.value() + "'>" + bt.ad() + "</option>");
								    }
								}
							}
						%>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="hambirim" class="col-xs-3 control-label text-baslik">Birim: </label>
				<div>
					<select class="form-control big" name="hambirim" id="hambirim">
						<%							
							for (BirimTip bt : BirimTip.values()) {
								if (BirimTip.KILOGRAM.value() != bt.value() ){
								    out.println("<option value='"+ bt.value() + "'>" + bt.ad() + "</option>");
								}
							}
						%>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="hamfirma" class="col-xs-3 control-label text-baslik">Tedarikçi: </label>
				<div>
					<select class="form-control big" name="hamfirma" id="hamfirma">
						<%
							for (Firma f : firma) {
							    out.println("<option value='"+ f.getId() + "'>" + f.getAd() + "</option>");
							}
						%>
					</select>
				</div>
			</div>
			<% 
			if(admin!=null && admin.equals("1")){
			%>
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div>
					<button type="button" class="btn btn-danger" onclick="hammaddeEkle()" >Ekle</button>
					<input type="hidden" name="token" id="token" value="<%= token %>">
				</div>
			</div>
			<% } %>
		</div>
	</div>
	</form>
	
	<!-- ALERT BOX -->
	<%
	if(message.equals("-1")) {
	    %>
		<%
	}
	else if(message.equals("0")) {
	    %>
		<div class="row col-sm-8 alert alert-success">
			<strong>Başarılı</strong> olarak eklendi!
		</div>
		<%
	}
	else if(!message.equals("") && message.length() >1){
	    %>
		<div class="row col-sm-8 alert alert-danger">
			<strong>Hata! </strong><%= message %>
		</div>
		<%
	}
	%>
	<!-- ALERT BOX -->

	<div class="row" style="font-size:12px;">
		<div class="col-sm-8">
		<table class="tableplan">
			<% int sayac = 0; %>
			<% for (Bilesen h : hammadde) { %>
			<% if (sayac++ == 0) { %>
			<tr>
				<td><label class="text-success">Malzeme Kodu</label></td>
				<td><label class="text-success">Malzeme Adı</label></td>
				<td><label class="text-success">Malzeme Tipi</label></td>
				<td><label class="text-success">Birim</label></td>
				<td><label class="text-success">Tedarikçi</label></td>
				<% 
				if(admin!=null && admin.equals("1")){
				%>
				<td><label class="text-success">Aksiyon</label></td>
				<% } %>
			</tr>
			<% } %>
			<form name="action_form<%=h.getId()%>" id="action_form<%=h.getId()%>">
			<tr id="tr<%=h.getId()%>">
				<td><input type="text" class="form-control" value="<%= h.getKod() %>" name="liste_kod" id="liste_kod" autocomplete="off"></td>
				<td><input type="text" class="form-control" value="<%= h.getAd().replaceAll("\"", "") %>" name="liste_ad" id="liste_ad" autocomplete="off"></td>
				<td><span><%= h.getBilesentipad() %></span></td>
				<td>
					<select class="form-control xs" id="liste_birimid" name="liste_birimid">
					<%
						for (BirimTip bt : BirimTip.values()){
							if (bt.value() == h.getBirimid() ){
							    out.println("<option value='"+ bt.value() + "' selected>" + bt.ad() + "</option>");
							}
							else{
							    if(bt.value() != BirimTip.KILOGRAM.value()){
							        out.println("<option value='"+ bt.value() + "'>" + bt.ad() + "</option>");
							    }
							}
						}
					%>
					</select>
					<%-- h.getBirimad() --%>
				</td>
				<td>
					<select class="form-control" id="liste_firmaid" name="liste_firmaid">
					<%
						for (Firma f : firma) {
							if (f.getId() == h.getFirmaid()){
							    out.println("<option value='"+ f.getId() + "' selected>" + f.getAd() + "</option>");
							}
							else{
								out.println("<option value='"+ f.getId() + "'>" + f.getAd() + "</option>");
							}
						}
					%>
					</select>						
				</td>
				<% 
				if(admin!=null && admin.equals("1")){
				%>
				<td>
					<div id="div<%= h.getId() %>" class="text-center">
						<a href="javascript:updateHammaddeGo('hammadde',<%=h.getId()%>,document.action_form<%=h.getId()%>,1);" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a> 
						<a href="javascript:deleteHammaddeGo('hammadde',<%=h.getId()%>,document.action_form<%=h.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
					</div>
				</td>
				<% } %>
			</tr>
			</form>
			<% } %>
		</table>
		<%--For displaying Previous link except for the 1st page --%>
		<%--For displaying Page numbers.
		The when condition does not display a link for the current page--%>
		<jsp:include page="paging.jsp" flush="true" >
			<jsp:param value="noofpages" name="noofpages"/>
			<jsp:param value="currentpage" name="currentpage"/>
			<jsp:param value="hammadde" name="pagename"/>
		</jsp:include>
		
	</div>
	<div class="col-sm-1"></div>
	</div><!-- row -->
</div><!-- container -->


<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/irfan.js"></script>
<script	src="js/hammadde.js" type="text/javascript"></script>
</body>

</html>