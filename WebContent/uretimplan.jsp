<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en"	ng-app>
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
<link rel="stylesheet" href="css/docs.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/datepicker.css">
<link rel="stylesheet/less" type="text/css" href="less/datepicker.less">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script	src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script	src="js/bootstrap.min.js" type="text/javascript"></script>
<script	src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/irfan.js?<%= System.currentTimeMillis() %>"></script>
<script	src="js/siparis.js?<%= System.currentTimeMillis() %>" type="text/javascript"></script>
<script>
	var siparis = [
	<%
	List<Siparis> siparis = (List<Siparis>) request.getAttribute("siparis");
	String delimeter = "";
	for (Siparis s : siparis) {
		out.println(delimeter 
	+ " { id:" + s.getId()		
	+ ",bilesenid:" + s.getBilesenid() 
	+ ",bilesenad:'" + s.getBilesenad() 
	+ "',miktar:" + s.getMiktar()
	+ ",planmiktar:" + s.getPlanmiktar()
	+ ",tarih:'" + s.getTarih()
	+ "',bitistarih:'" + s.getBitistarih()
	+ "',not:'" + s.getNot() + "'}");
		delimeter = ",";
	}%>];
</script>
	
</head>
<body>
	<%
		Boolean loggedin = (Boolean) session.getAttribute("loggedin");
		User user = (User) session.getAttribute("user");
		if (loggedin == null || user == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else{
	%>
	<jsp:include page="navigate.jsp">
		<jsp:param value="user"	name="user" />
	</jsp:include>
	<%
		}
	%>

	<div class="container" ng-controller="SiparisCntrl">
		<div
			class="row text-warning"
			style="text-align: center;">
			<label class="text-danger">Günlük Üretim Planı</label>
		</div>
		<div class="row">
			<form class="form-horizontal" role="form" method="post" action="/HelloWorld/uretimplan">
				<div class="form-group">
					<label for="siparis" class="col-xs-3 control-label">Sipariş:</label>
					<div class="col-xs-8">
						<select	class="form-control" required="required" id="siparis" name="siparis" ng-model="siparisItem" ng-init="siparisItem=select()">
							<option ng-repeat="sip in siparisJSON" ngSelected="true" value="{{sip.id}}">{{sip.bilesenad}} [Sipariş: {{sip.miktar}}, Kalan: {{sip.miktar - sip.planmiktar}}]</option>
  					</select>
					</div>
				</div>
				<div class="form-group">
					<label for="miktar"	class="col-xs-3 control-label">Planlanan Miktar: </label>
					<div class="col-xs-8">
						<input type="text" class="form-control" name="miktar" ng-model="miktar" placeholder="Miktar">
					</div>
				</div>

				<div class="form-group">
					<label for="makina" class="col-xs-3 control-label">Makina/Band:</label>
					<div class="col-xs-8">
						<select	class="form-control" id="makina" name="makina">
							<%
								List<Makina> makina = (List<Makina>) request.getAttribute("makina");
								for (Makina m : makina) {
							%>
							<option value='<%=m.getId()%>'><%= m.getMakinatipad() + " - " + m.getMakinaad() %>
							</option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="calisan" class="col-xs-3 control-label">Operatör:</label>
					<div class="col-xs-8">
						<select	class="form-control" id="calisan" name="calisan">
							<%
								List<Calisan> calisan = (List<Calisan>) request.getAttribute("calisan");
								for (Calisan c : calisan) {
							%>
							<option value='<%=c.getId()%>'><%=c.getAdsoy() + " - " + c.getGorev() %>
							</option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="tarih" class="col-xs-3 control-label">Tarih: </label>
					<div class="col-xs-8">
						<input type="text" class="form-control"	value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" name="tarih" id="tarih" placeholder="Tarih">
					</div>
				</div>

				<div class="form-group">
					<label for="bas_zaman" class="col-xs-3 control-label">Başlangıç: </label>
					<div class="col-xs-8">
						<div>
							<select class="form-control time" name="bassaat" id="bassaat">
								<%
									for(int i=0;i<24; i++){
										String temp = "0"+i;
										temp = temp.substring(temp.length()-2);
										out.print("<option value='"+ temp +"'>");
										out.print((temp));
										out.println("</option>");
									}
								%>
							</select> <strong>:</strong>
							<select class="form-control time" name="basdakika" id="basdakika">
								<%
									for(int i=0;i<60; i=i+5){
										String temp = "0"+i;
										temp = temp.substring(temp.length()-2);
										out.print("<option value='"+ temp +"'>");
										out.print((temp));
										out.println("</option>");
									}
								%>
							</select>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="bit_zaman" class="col-xs-3 control-label">Bitiş: </label>
					<div class="col-xs-8">
						<div>
							<select class="form-control time" name="bitsaat" id="bitsaat">
								<%
									for(int i=0;i<24; i++){
										String temp = "0"+i;
										temp = temp.substring(temp.length()-2);
										out.print("<option value='"+ temp +"'>");
										out.print((temp));
										out.println("</option>");
									}
								%>
							</select> <strong>:</strong>
							<select class="form-control time" name="bitdakika" id="bitdakika">
								<%
									for(int i=0;i<60; i=i+5){
										String temp = "0"+i;
										temp = temp.substring(temp.length()-2);
										out.print("<option value='"+ temp +"'>");
										out.print((temp));
										out.println("</option>");
									}
								%>
							</select>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="not" class="col-xs-3 control-label">Not: </label>
					<div class="col-xs-8">
						<textarea name="not" id="not" cols="30" rows="5" class="form-control" placeholder="Not"></textarea>
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
		List<SiparisPlan> siparisplan = (List<SiparisPlan>) request.getAttribute("siparisplan");
		int sayac = 0;
	%>

	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-3"></div>
			<div class="col-sm-8">
				<table class="tableplan">
					<% for (SiparisPlan s : siparisplan) { %>
					<% if (sayac++ == 0) { %>
					<tr>
						<td><label class="text-success">Mamul Adı</label></td>
						<td><label class="text-success">Miktar</label></td>
						<td><label class="text-success">Makina/Bant Adı</label></td>
						<td><label class="text-success">Çalışan Adı</label></td>
						<td><label class="text-success">Tarih</label></td>
						<td><label class="text-success">Başlangıç</label></td>
						<td><label class="text-success">Bitiş</label></td>
					</tr>
					<% } %>
					<tr>
						<td nowrap><%= s.getBilesenad() %></td>
						<td><%= s.getMiktar() %></td>
						<td><%= s.getMakinaad() %></td>
						<td nowrap><%= s.getCalisanad() %></td>
						<td nowrap><%= s.getTarih() %></td>
						<td><%= s.getBasZaman() %></td>
						<td><%= s.getBitZaman() %></td>
					</tr>
					<% } %>
				</table>
			</div>
		</div>
	</div>

	</div>
	
	<script>
		$(function() {
			$('#tarih').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1
			})
		});
	</script>
</body>

</html>