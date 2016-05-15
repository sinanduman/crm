<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.Util,crm.irfan.entity.*,java.util.List" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="img/favicon.ico">
	<title><%=Genel.TITLE%></title>

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
<%@ include file="logincheck.jsp" %>

<%
    List<Irsaliye> irsaliye = (List<Irsaliye>) request.getAttribute("irsaliye");
	List<IrsaliyeBilesen> irsaliyebilesenonaylandi = (List<IrsaliyeBilesen>) request.getAttribute("irsaliyebilesenonaylandi");
	List<Firma> firma = (List<Firma>) request.getAttribute("firma");
	List<Birim> birim = (List<Birim>) request.getAttribute("birim");
%>

<div class="container">
	<div class="row text-warning" style="text-align:center;">
		<label class="text-danger rounded">Sevk Edilen İrsaliyeler</label>
	</div>

	<div class="row" style="font-size:12px;">
		<div>
			<table class="tableplan">
				<%
				    int sayac = 0;
				%>
				<%
				    for (Irsaliye i : irsaliye) {
				%>
				<%
				    if (sayac++ == 0) {
				%>
				<tr>
					<td><label class="text-success">İrsaliye No</label></td>
					<td><label class="text-success">Oluşturma Tarihi</label></td>
					<td><label class="text-success">Gönderim Tarihi</label></td>
					<td><label class="text-success">Müşteri</label></td>
					<td class="text-center"><label class="text-success">Aksiyon</label></td>
				</tr>
				<%
				    }
				%>
				<form name="action_form<%=i.getId()%>" id="action_form<%=i.getId()%>">
				<tr id="tr<%=i.getId()%>">
					<td><%=i.getIrsaliyeno()%></td>
					<td><%=Util.getTarihTR(i.getOlusturmatarihi())%></td>
					<td><%=Util.getTarihTR(i.getGonderimtarihi())%></td>
					<td><%= (i.getFirmaad()==null)?"":i.getFirmaad() %></td>
					
					<td class="text-center">
						<div id="divirsaliye<%= i.getId() %>">
							<a href="javascript:hideshow('#tr_irs_detay<%= i.getId() %>');" title="İçerik"><span class="fa fa-chevron-down fa-lg text-success"></span></a>
						</div>
					</td>
				</tr>
				<tr id="tr_irs_detay<%= i.getId() %>" style="display:none;">
					<td colspan="5">
						<table style="width:100%;" class="tableplan">
							<% int sayacbilesen = 0; %>
							<% for (IrsaliyeBilesen j : irsaliyebilesenonaylandi) {%>
							<% if ( j.getIrsaliyeid().intValue() == i.getId().intValue() ){ %>
							<% if ( (sayacbilesen++ == 0) ){ %>
							<tr>
								<td><label class="text-danger">Sıra No</label></td>
								<td><label class="text-danger">İrsaliye İd</label></td>
								<td><label class="text-danger">Mamül Adı</label></td>
								<td><label class="text-danger">Mamül Kodu</label></td>
								<td><label class="text-danger">Firma</label></td>
								<td><label class="text-danger">GKR.No</label></td>
								<td><label class="text-danger">Miktarı</label></td>
							</tr>
							<% } %><%-- if tablo baslik --%>
							<tr>
								<td><%= sayacbilesen %></td>
								<td><%= j.getIrsaliyeid() %></td>
								<td><%= j.getMamulad() %></td>
								<td><%= j.getMamulkod() %></td>
								<td><%= j.getFirmaad() %></td>
								<td><%= j.getGkrno() %></td>
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
			<jsp:include page="paging.jsp" flush="true" >
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="irsaliyegonder" name="pagename"/>
			</jsp:include>
		</div>
	</div>

</div>

	<script src="js/angular.min.js"></script>
	<script src="js/angular-route.min.js"></script>
	<script	src="js/jquery.min.js" type="text/javascript"></script>
	<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
	<script	src="js/bootstrap.min.js" type="text/javascript"></script>
	<script	src="js/bootbox.js" type="text/javascript"></script>
	<script src="js/irfan.js?<%= System.currentTimeMillis() %>"></script>
	<script src="js/irsaliye.js?<%= System.currentTimeMillis() %>" type="text/javascript"></script>	
</body>
</html>