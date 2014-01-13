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
<script	src="js/siparis.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
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
			<label class="text-danger">Üretim Takibi</label>
		</div>
		
	<%
		List<SiparisPlan> siparisplan = (List<SiparisPlan>) request.getAttribute("siparisplan");
		List<Calisan> calisan = (List<Calisan>) request.getAttribute("calisan");
		List<Makina> makina = (List<Makina>) request.getAttribute("makina");
		List<HataSebep> hatasebep = (List<HataSebep>) request.getAttribute("hatasebep");
		List<DurusSebep> durussebep = (List<DurusSebep>) request.getAttribute("durussebep");
		int sayac = 0;
	%>	
	<div class="row" style="font-size:12px;">
		<div class="container">
			<div class="col-sm-12">
				<table class="tableplan">
					<% for (SiparisPlan s : siparisplan) { %>
					<% if (sayac++ == 0) { %>
					<tr>
							<td><label class="text-success">Sip No</label></td>
							<td><label class="text-success">Plan No</label></td>
							<td><label class="text-success">Mamul Adı</label></td>
							<td><label class="text-success">Miktar</label></td>
							<td><label class="text-success">Makina/Bant Adı</label></td>
							<td><label class="text-success">Çalışan Adı</label></td>
							<td><label class="text-success">Tarih</label></td>
							<td><label class="text-success">Başlangıç</label></td>
							<td><label class="text-success">Bitiş</label></td>
							<td><label class="text-success">Aksiyon</label></td>
					</tr>
					<% } %>
					<form name="action_form<%=s.getId()%>" id="action_form<%=s.getId()%>">
					<tr id="tr<%=s.getId()%>">
						<td style="text-align:right;"><%= s.getSiparisid() %>&nbsp;</td>
						<td><%= s.getId() %>&nbsp;</td>
						<td nowrap><input type="hidden" name="bilesen" id="bilesen" value="<%= s.getBilesenad() %>">
						<%= s.getBilesenad() %></td>
						<td><input type="text" name="miktar" value="<%= s.getMiktar() %>" style="width:50px;"></td>
						<td>
							<select	id="makina" name="makina">
								<%
								for (Makina m : makina) {
									if(m.getId() ==s.getMakinaid() ){
										out.println("<option value='"+ m.getId() +"' selected>"+ m.getMakinatipad() + " - " + m.getMakinaad() +"</option>");
									}
									else{
										out.println("<option value='"+ m.getId() +"'>"+ m.getMakinatipad() + " - " + m.getMakinaad() +"</option>");
									}
								}
								%>
							</select>
						</td>
						<td nowrap>
							<select id="calisan" name="calisan">
							<%
								for (Calisan c : calisan) {
									if(c.getId()==s.getCalisanid() ){
										out.println("<option value='"+ c.getId() +"' selected>"+ c.getAdsoy() +"</option>");
									}
									else{
										out.println("<option value='"+ c.getId() +"'>"+ c.getAdsoy() +"</option>");
									}
								}
							%>
							</select>
						</td>
						<td nowrap><input type="text" class="plantarih" value="<%=s.getTarih() %>" name="tarih" id="tarih" style="width:90px;"></td>
						<td nowrap>
						<select name="bassaat" id="bassaat" style="width:45px;">
							<%
								for(int i=0;i<24; i++){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									if(temp.equals(s.getBasSaat() )){
										out.print("<option value='"+ temp +"' selected>");
									}
									else{
										out.print("<option value='"+ temp +"'>");
									}
									out.print(temp);
									out.println("</option>");
								}
							%>
						</select>
						<select name="basdakika" id="basdakika" style="width:45px;">
							<%
								for(int i=0;i<60; i=i+5){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									if(temp.equals(s.getBasDakika())){
										out.print("<option value='"+ temp +"' selected>");
									}
									else{
										out.print("<option value='"+ temp +"'>");
									}
									out.print(temp);
									out.println("</option>");
								}
							%>
						</select>
						</td>
						<td nowrap>
						<select name="bitsaat" id="bitsaat" style="width:45px;">
							<%
								for(int i=0;i<24; i++){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									if(temp.equals(s.getBitSaat() )){
										out.print("<option value='"+ temp +"' selected>");
									}
									else{
										out.print("<option value='"+ temp +"'>");
									}
									out.print(temp);
									out.println("</option>");
								}
							%>
						</select>
						<select name="bitdakika" id="bitdakika" style="width:45px;">
							<%
								for(int i=0;i<60; i=i+5){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									if(temp.equals(s.getBitDakika())){
										out.print("<option value='"+ temp +"' selected>");
									}
									else{
										out.print("<option value='"+ temp +"'>");
									}
									out.print(temp);
									out.println("</option>");
								}
							%>
						</select>
						</td>						
						<td>
							<input class="icerikHref" type="button" value="Detay &darr;" onclick="javascript:hideshow('#tr_urtpl_detay<%=s.getId() %>');">
							<input class="updateHref" type="button" id="updateButton<%=s.getId()%>" value="Gün. &rarr;" onclick="javascript:updateGo('/HelloWorld/uretimtakip',<%=s.getId()%>,document.action_form<%=s.getId()%>,1);">
							<input class="okHref" type="button" id="okButton<%=s.getId()%>" value="Tam. &rarr;" onclick="javascript:okGo('/HelloWorld/uretimtakip',<%=s.getId()%>,document.action_form<%=s.getId()%>,2);">
							<input class="deleteHref" type="button" id="deleteButton<%=s.getId()%>" value="Sil &rarr;" onclick="javascript:deleteGo('/HelloWorld/uretimtakip',<%=s.getId()%>,document.action_form<%=s.getId()%>,3);">
						</td>
									
					</tr>
					<tr id="tr_urtpl_detay<%=s.getId() %>" style="display:none;">
						<td colspan="10">
							<table style="width:100%;" class="tableplan">
								<tr>
									<td style="width:20%">
										<label class="text-info">Hata Sebebi</label>
										<select name="hataid" id="hataid" style="width:100px;" class="form-control" >
										<% 
												out.println("<option value='' selected>..Seçiniz..</option>");
										for (HataSebep hs : hatasebep) {
											if ( hs.getId() == s.getHataid() ){
												out.println("<option value='" + hs.getId() + "' selected>"+ hs.getAd() + " ("+ hs.getKod() +")</option>");
											}
											else {
												out.println("<option value='" + hs.getId() + "'>"+ hs.getAd() + " ("+ hs.getKod() +")</option>");
											}
										}
										%>
										</select>
									</td>
									<td style="width:20%">
										<label class="text-info">Hatalı Miktar</label>
										<input type="text" name="hatamiktar" value="<%= s.getHatamiktar() %>" style="width:100px;" class="form-control" >
									</td>
									<td style="width:20%">
										<label class="text-info">Duruş Sebebi</label>
										<select name="durusid" id="durusid" style="width:100px;" class="form-control" >
										<% 
												out.println("<option value='' selected>..Seçiniz..</option>");
										for (DurusSebep ds : durussebep) {
											if ( ds.getId() == s.getDurusid() ){
												out.println("<option value='" + ds.getId() + "' selected>"+ ds.getAd() + " ("+ ds.getKod() +")</option>");
											}
											else {
												out.println("<option value='" + ds.getId() + "'>"+ ds.getAd() + " ("+ ds.getKod() +")</option>");
											}
										}
										%>
										</select>
									</td>
									<td style="width:40%">
										<label class="text-info">Not</label>
										<textarea name="not" id="not" rows="5" class="form-control" placeholder="Not"><%=s.getNot() %></textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</form>
					<% } %>
				</table>
			</div>
		</div>
	</div>

	</div>
	
	<script>
		$(function() {
			$('.plantarih, #tarih').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1
			})
		});
	</script>
</body>

</html>