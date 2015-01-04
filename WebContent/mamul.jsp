<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.entity.*, java.util.List" %>
<!doctype html>
<html lang="en" ng-app="mamulApp">
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
	@SuppressWarnings("unchecked")
	List<Firma> firma				= (List<Firma>) request.getAttribute("firma");
	@SuppressWarnings("unchecked")
	List<Bilesen> hammadde			= (List<Bilesen>) request.getAttribute("hammadde");
	@SuppressWarnings("unchecked")
	List<Bilesen> yarimamul			= (List<Bilesen>) request.getAttribute("yarimamul");
	@SuppressWarnings("unchecked")
	List<Mamul> mamul				= (List<Mamul>) request.getAttribute("mamul");
	@SuppressWarnings("unchecked")
	List<Mamul> mamultum			= (List<Mamul>) request.getAttribute("mamultum");
	@SuppressWarnings("unchecked")
	List<MamulBilesen> mamulbilesen	= (List<MamulBilesen>) request.getAttribute("mamulbilesen");
	String message					= (String) request.getAttribute("message");
	Long token						= (Long) request.getAttribute("token");
	String admin					= (String) session.getAttribute("admin");
%>

<script>
	var mamul = [
	<%String delimeter = "";
	for (Mamul m : mamultum) {
		out.println(delimeter 
	+ " { id:" + m.getId()
	+ ",mamulid:" + m.getId() 
	+ ",mamulad:'" + m.getAd().replaceAll("'", "") + "'"
	+ ",mamulkod:'" + m.getKod() + "'"
	+ ",hammadde:'" + m.getHammadde().replaceAll("'", "") + "'"
	+ ",hammaddegkrno:'" + m.getHammaddegkrno() + "'"
	+ ",yarimamul:'" + m.getYarimamul().replaceAll("'", "") + "'"
	+ ",yarimamulgkrno:'" + m.getYarimamulgkrno() + "'"
	+ ",firmaid:" + m.getFirmaid() 
	+ ",firmaad:'" + m.getFirmaad().replaceAll("'", "") + "'"
	+ ",cevrimsure:" + m.getCevrimsuresi() + "}");
		delimeter = ",";
	}%>];
	var mamul2 = [
	<%delimeter = "";
	for (Mamul m : mamultum) {
		out.println(delimeter + " { label:'" + m.getKod() + " ["+m.getAd().replaceAll("'", "") +"]',id:" + m.getId() + "}");
		delimeter = ",";
	}%>];

</script>

<div class="container" ng-controller="MamulCtrl">
	<div class="row text-danger" style="text-align:center;">
		<label class="text-danger rounded col-xs-6">Mamül Tanımı</label>
	</div>
	<form class="form-horizontal" name="mamulform" id="mamulform" method="post" action="mamul">
		<div class="row">
			<div class="form-group col-xs-8">
				<label for="mamulkod" class="col-xs-3 control-label text-baslik">Mamül Kodu: </label>
				<div>
					<input type="text" class="form-control big" name="mamulkod" id="mamulkod" ng-model="mamulkod" placeholder="Mamül Kodu" autocomplete="off">
				</div>
			</div>			
			<div class="form-group col-xs-8">
				<label for="mamulad" class="col-xs-3 control-label text-baslik">Mamül Adı: </label>
				<div>
					<input type="text" class="form-control big" name="mamulad" id="mamulad" ng-model="mamulad" placeholder="Mamül Adı" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="mamulcevrim" class="col-xs-3 control-label text-baslik">Çevrim Süresi(sn): </label>
				<div>
					<input type="text" class="form-control big" name="mamulcevrim" id="mamulcevrim" ng-model="mamulcevrim" placeholder="Çevrim Süresi" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="mamulfirma" class="col-xs-3 control-label text-baslik">Tedarikçi: </label>
				<div>
					<select class="form-control big" name="mamulfirma" id="mamulfirma">
						<%
							for (Firma f : firma) {							    
							    out.println("<option value='"+ f.getId() + "'>" + f.getAd() + "</option>");
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="mamulfigur" class="col-xs-3 control-label text-baslik">Figür Adedi: </label>
				<div>
					<select class="form-control big" name="mamulfigur" id="mamulfigur">
						<%
						for( int i=1; i<=32; i++ ){
						    out.println("<option value='"+i+"'>"+i +"</option>");
						}
						%>
					</select>
				</div>
			</div>
			
			<div class="form-group col-xs-6">
				<div>
					<div class="radio">
						<label class="text-success strong">
							<input type="radio" name="bilesenTipRadio" class="text-baslik" value="Hammadde" checked ng-click="on()">
							Hammadde
						</label>
					</div>
					<div class="radio">
						<label class="text-danger strong">
							<input type="radio" name="bilesenTipRadio" class="text-baslik" value="Yarımamül" ng-click="off()">
							Yarımamül
						</label>
					</div>
				</div>
			</div>
			<div class="form-group col-xs-8" ng-model="mamulTypeUretim" ng-show="showState()">
				<label for="hammadde" class="col-xs-3 control-label text-baslik">Hammadde: </label>
				<div>
					<select class="form-control big" id="hammadde" name="hammadde">
						<%
							for (Bilesen h : hammadde) {
							    out.println("<option value='"+ h.getId() + "'>" + h.getAd().replaceAll("\"", "") + " [" + h.getKod() + "]" + " - "+ h.getFirmaad() +  "</option>");
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group col-xs-8" ng-model="mamulTypeMontaj" ng-hide="showState()">
				<label for="yarimamul" class="col-xs-3 control-label text-baslik">Yarımamül: </label>
				<div>
					<select class="form-control big" id="yarimamul" name="yarimamul">
						<%
							for (Bilesen y : yarimamul) {
							    out.println("<option value='"+ y.getId() + "'>" + y.getAd().replaceAll("\"", "") + " [" + y.getKod() + "]" + " - "+ y.getFirmaad() + "</option>");
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="birimid" class="col-xs-3 control-label text-baslik">Birim: </label>
				<div>
					<select class="form-control big" id="birimid_select" name="birimid_select" disabled="disabled">
						<%
							for (BirimTip bt : BirimTip.values()) {
							    if( bt.value() != BirimTip.KILOGRAM.value() ){
							        out.println("<option value='"+ bt.value() + "'>" + bt.ad() + "</option>");    
							    }							    
							}
						%>
					</select>
					<input type="hidden" name="birimid" id="birimid" value="1">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<label for="miktar" class="col-xs-3 control-label text-baslik">Miktar: </label>
				<div>
					<input type="text" class="form-control big" name="miktar" ng-model="yenibilesen.miktar" placeholder="Kullanılan Miktar" autocomplete="off">
				</div>
			</div>
			<div class="form-group col-xs-8">
				<div>
					<input type="hidden" ng-model="yenibilesen.yarimamulad" />
					<input type="hidden" ng-model="yenibilesen.hammaddead" />
					<input type="hidden" ng-model="yenibilesen.birimad" />
					<input type="hidden" ng-model="yenibilesen.id" />
					<button type="button" ng-click="saveBilesen()" name="bilesen_ekle" id="bilesen_ekle"  class="btn btn-success">Bileşen Ekle</button>
				</div>
			</div>
		</div>
		<div class="row" style="font-size:12px;">
			<table class="tableplan">
			<tr>
				<td><label class="text-success">Malzeme Adı</label></td>
				<td><label class="text-success">Birim</label></td>
				<td><label class="text-success">Miktar</label></td>
				<td><label class="text-success">Tip</label></td>
				<td class="text-center"><label class="text-success">Aksiyon</label></td>
			</tr>
			<tr ng-repeat="bilesen in bilesenler">
				<td>{{ bilesen.bilesenad }}</td>
				<td>{{ bilesen.birimad }}</td>
				<td>{{ bilesen.miktar }}</td>
				<td>{{ bilesen.uretimtip }}</td>
				<td class="text-center">
					<a href="#" ng-click="edit(bilesen.id)" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a>
					<a href="#" ng-click="del(bilesen.id)" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
					
					<input type="hidden" id="uretimtip_{{$index}}" name="uretimtip_{{$index}}" value="{{bilesen.uretimtipid}}">
					<input type="hidden" id="bilesenid_{{$index}}" name="bilesenid_{{$index}}" value="{{bilesen.bilesenid}}">
					<input type="hidden" id="miktar_{{$index}}" name="miktar_{{$index}}" value="{{bilesen.miktar}}">
					<input type="hidden" id="birimid_{{$index}}" name="birimid_{{$index}}" value="{{bilesen.birimid}}">
				</td>
			</tr>
			</table>
		</div>
		<% 
		if(admin!=null && admin.equals("1")){
		%>
		<div class="row" sytle="margin-bottom:5px;">
			<div class="col-xs-5">
				<button type="button" class="btn btn-danger" ng-click="saveMamul()">Mamül Ekle</button>
				<input type="hidden" id="bilesen_length" name="bilesen_length" ng-model="bilesen_length" value="{{bilesenler.length}}">
				<input type="hidden" name="token" id="token" value="<%= token %>">
			</div>
			
			
			<div class="col-xs-4 text-right">
				<input type="text" class="form-control big" name="mamulkodara" id="mamulkodara" placeholder="Mamül Kodu" autocomplete="off">
				<input type="hidden" name="mamulidara" id="mamulidara">
			</div>
				
			<div class="col-xs-2">
				<button type="button" class="btn btn-danger" name="stokliste" id="stokliste" disabled="disabled">Mamül Getir</button>
				<input type="hidden" name="stoklisteid" id="stoklisteid" value="0">
			</div>
			
		</div>
		<% } %>
	</form>
	
	<!-- ALERT BOX -->
	<%
	if(message.equals("-1")) {
	    %>
		<%
	}
	else if(message.equals("0")) {
	    %>
		<div class="row alert alert-success">
			<strong>Başarılı</strong> olarak eklendi!
		</div>
		<%
	}
	else if(!message.equals("") && message.length() >1){
	    %>
		<div class="row alert alert-danger">
			<strong>Hata! </strong><%= message %>
		</div>
		<%
	}
	%>
	<!-- ALERT BOX -->

	<div class="row" style="font-size:12px; margin-top:5px;">
		<div>
			<table class="tableplan">
				<% int sayac = 0; %>
				<% for (Mamul m : mamul) { %>
				<% if (sayac++ == 0) { %>
				<tr>
					<td><label class="text-success">Mamül Kodu</label></td>
					<td><label class="text-success">Mamül Adı</label></td>
					<td><label class="text-success">Çevrim Süresi</label></td>
					<td><label class="text-success">Figür Adedi</label></td>
					<td><label class="text-success">Firma</label></td>
					<td class="text-center"><label class="text-success">Aksiyon</label></td>
				</tr>
				<% } %>
				<form name="action_form<%=m.getId()%>" id="action_form<%=m.getId()%>">
				<tr id="tr<%=m.getId()%>">
					<td><input type="text" class="form-control" value="<%= m.getKod() %>" name="liste_kod" id="liste_kod"></td>
					<td><input type="text" class="form-control" value="<%= m.getAd().replaceAll("\"", "") %>" name="liste_ad" id="liste_ad"></td>
					<td><input type="text" class="form-control xs" value="<%= m.getCevrimsuresi() %>" name="liste_cevrimsuresi" id="liste_cevrimsuresi"></td>
					<td>
						<div>
							<select class="form-control xs" name="liste_figurid" id="liste_figurid">
							<%
								for( int i=1; i<=32; i++ ){
									if (i == m.getFigur() ){
									    out.println("<option value='"+ i + "' selected>" + i + "</option>");
									}
									else{
										out.println("<option value='"+ i + "'>" + i + "</option>");
									}
								}
							%>
							</select>
						</div>
					</td>
					<td>
						<select class="form-control" id="liste_firmaid" name="liste_firmaid">
						<%
							for (Firma f : firma) {
								if (f.getId() == m.getFirmaid() ){
								    out.println("<option value='"+ f.getId() + "' selected>" + f.getAd() + "</option>");
								}
								else{
									out.println("<option value='"+ f.getId() + "'>" + f.getAd() + "</option>");
								}
							}
						%>
						</select>
					</td>
					<td class="text-center">
						<a href="javascript:hideshow('#tr_mam_detay<%= m.getId() %>');" title="İçerik"><span class="fa fa-chevron-down fa-lg text-success"></span></a>
						<% 
						if(admin!=null && admin.equals("1")){
						%>
						<a href="javascript:updateMamulGo('mamul',<%=m.getId()%>,document.action_form<%=m.getId()%>,1);" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a>
						<a href="javascript:deleteMamulGo('mamul',<%=m.getId()%>,document.action_form<%=m.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
						<% } %>
					</td>
				</tr>
				<tr id="tr_mam_detay<%= m.getId() %>" style="display:none;">
					<td colspan="5">
						<table style="width:100%;" class="tableplan">
							<% int sayacbilesen = 0; %>
							<% for (MamulBilesen j : mamulbilesen) {%>
							<% if ( j.getMamulid().equals(m.getId()) ){ %>
							<% if ( (sayacbilesen++ == 0) ){ %>
							<tr>
								<td><label class="text-danger">Sıra No</label></td>
								<td><label class="text-danger">Bileşen Adı</label></td>
								<td><label class="text-danger">Bileşen Kodu</label></td>
								<td><label class="text-danger">Tür</label></td>
								<td><label class="text-danger">Birim</label></td>
								<td><label class="text-danger">Miktarı</label></td>
							</tr>
							<% } %><%-- if tablo baslik --%>
							<tr>
								<td><%= sayacbilesen %></td>
								<td><%= j.getBilesenad() %></td>
								<td><%= j.getBilesenkod() %></td>
								<td><%= j.getBilesentipad() %></td>
								<td><%= j.getBirimad() %></td>
								<td><%= (Math.round(j.getMiktar()) == j.getMiktar()) ? Math.round(j.getMiktar()) +"" : j.getMiktar() %></td>
							</tr>
							<% } %><%-- if  j== m--%>
							<% } %> <%-- mamulbilesen --%>
						</table>
					</td>
				</tr>
				</form>
				<% } %> <%-- mamul --%>
			</table>			
			<jsp:include page="paging.jsp" flush="true" >
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="mamul" name="pagename"/>
			</jsp:include>
		</div>
	</div>

</div>

<script src="js/angular.min.js"></script>
<script src="js/angular-route.min.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script	src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/irfan.js"></script>
<script src="js/mamul.js"></script>

<script type="text/javascript">
$(function() {
	$('#tarih').datepicker({
		inline: true,
		format : 'dd-mm-yy',
		firstDay:1,
	}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","today");
});
$("#mamulkodara" ).autocomplete({
	source		: mamul2,
	minLength	: 1,
	select		: function(event, ui){
		changefun(ui.item);
	}
});
$("#mamulkodara").change(function() {
	changefun(this);
});

$("#stokliste").click(function() {
	console.log("selam: ");
	$("#stoklisteid").val("1");
	$("#mamulform").submit();
});

function changefun(elem){
	console.log("fun: " + elem);
	console.log("fun val: " + elem.value);	
	var found = false;
	if (elem != null){
		for (i in mamul) {
			var b = elem.value.split("[");
			if(b[0].trim() == mamul[i].mamulkod){
				found = true;
				$("#mamulidara").val(mamul[i].mamulid);
				$("#stokliste").removeAttr("disabled");
			}
		}
	}
	if(!found){
		$("#mamulidara").val("");
		$("#stokliste").attr("disabled","disabled");
	}
}
</script>

</body>
</html>