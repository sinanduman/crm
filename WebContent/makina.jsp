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
	List<Makina> makina = (List<Makina>) request.getAttribute("makina");
	String message = (String) request.getAttribute("message");
%>

<div class="container">
	<div class="row text-danger" style="text-align:center;">
		<div class="col-xs-8">
			<label class="text-danger rounded">Makina / Bant Ekleme</label>
		</div>
	</div>

	<div class="row">
		<form class="form-horizontal" method="post" action="makina">
			<div class="form-group col-xs-8">
				<label for="makina_ad" class="col-xs-3 control-label text-baslik">Makina/Bant Adı: </label>
				<div>
					<input type="text" class="form-control big" name="makina_ad" id="makina_ad" placeholder="Makina/Bant Adı" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="adsoy" class="col-xs-3 control-label text-baslik">Makina Tipi: </label>
				<div>
					<select class="form-control big" id="makina_tip" name="makina_tip">
						<%
							for (MakinaTip m : MakinaTip.values()) {
						%>
						<option value='<%= m.value() %>'><%= m.ad() %>
						</option>
						<%
							}
						%>
					</select>
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
				<% for (Makina m : makina) { %>
				<% if (sayac++ == 0) { %>
				<tr>
					<td><label class="text-success">No</label></td>
					<td><label class="text-success">Makina/Bant Adı</label></td>
					<td><label class="text-success">Makina Tipi</label></td>
					<td><label class="text-success">Aksiyon</label></td>
				</tr>
				<% } %>
				<form name="action_form<%=m.getId()%>" id="action_form<%=m.getId()%>">
				<tr id="tr<%=m.getId() %>">
					<td><%= m.getId() %></td>
					<td><input type="text" class="form-control" value="<%= m.getMakinaad() %>" name="liste_makinaad" id="liste_makinaad" autocomplete="off"></td>
					<td>
					<select id="liste_makinatipid" name="liste_makinatipid">
						<%
							for (MakinaTip mt : MakinaTip.values()) {
								if (mt.value()  == m.getMakinatipid() ){
								    out.println("<option value='"+ mt.value() + "' selected>" + mt.ad() + "</option>");
								}
								else{
									out.println("<option value='"+ mt.value() + "'>" + mt.ad() + "</option>");
								}
							}
						%>
					</select>
					</td>
					<td>
						<div id="div<%= m.getId() %>" class="text-center">
							<a href="javascript:updateMakinaGo('makina',<%=m.getId()%>,document.action_form<%=m.getId()%>,1);" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a> 
							<a href="javascript:deleteMakinaGo('makina',<%=m.getId()%>,document.action_form<%=m.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
						</div>
					</td>
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
				<jsp:param value="makina" name="pagename"/>
			</jsp:include>
		</div>
		<div class="col-sm-1"></div>
	</div>


</div>

<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/irfan.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script	src="js/makina.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>

</body>

</html>
