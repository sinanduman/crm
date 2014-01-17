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
	<div class="row text-danger" style="text-align:center;">
		<label class="text-danger">Makina/Bant Ekleme</label>
	</div>

	<div class="row" ng-controller="CalisanEkleCtrl">
		<form class="form-horizontal" role="form" method="post" action="/irfanpls/makina">
			<div class="form-group">
				<label for="makina_ad" class="col-xs-3 control-label">Makina/Bant Adı: </label>
				<div class="col-xs-8">
					<input type="text" class="form-control" name="makina_ad" ng-model="makina_ad" placeholder="Makina/Bant Adı">
				</div>
			</div>
			<div class="form-group">
				<label for="adsoy" class="col-xs-3 control-label">Makina Tipi: </label>
				<div class="col-xs-8">
					<select class="form-control" id="makina_tip" name="makina_tip">
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
			<div class="form-group">
				<label class="col-xs-3 control-label">&nbsp;</label>
				<div class="col-xs-8">
					<button type="submit" class="btn btn-warning">Ekle</button>
				</div>
			</div>
		</form>
	</div>
	<%
		List<Makina> makina = (List<Makina>) request.getAttribute("makina");
		int sayac = 0;
	%>

	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-3"></div>
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
					<tr>
						<td><%= m.getId() %></td>
						<td><%= m.getMakinaad() %></td>
						<td><%= m.getMakinatipad() %></td>
						<td>
							<input class="updateHref" type="button" value="Gün. &rarr;">
							<input class="deleteHref" type="button" value="Sil &rarr;">
						</td>
					</tr>
					<% } %>
				</table>
				
				<%--For displaying Previous link except for the 1st page --%>
				<%--For displaying Page numbers.
			    The when condition does not display a link for the current page--%>
			    <c:if test="${noofpages > 1}">
				<table id="pagination">
					<tr>
						<c:if test="${currentpage != 1}">
							<td class="link_diger"><a href="makina?page=${currentpage - 1}">Önceki</a></td>
						</c:if>
						<c:forEach begin="1" end="${noofpages}" var="i">
							<c:choose>
								<c:when test="${currentpage eq i}">
									<td class="link_aktif">${i}</td>
								</c:when>
								<c:otherwise>
									<td class="link_diger"><a href="makina?page=${i}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<%--For displaying Next link --%>
						<c:if test="${currentpage lt noofpages}">
							<td class="link_diger"><a href="makina?page=${currentpage + 1}">Sonraki</a></td>
						</c:if>
					</tr>
				</table>
				</c:if>

			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>


</div>


<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/irfan.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>

</body>

</html>
