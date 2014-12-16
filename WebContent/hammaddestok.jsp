<%@page import="crm.irfan.Util"%>
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
	<title><%=Genel.TITLE%></title>

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="css/irfan.css">
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
<%@ page import="crm.irfan.entity.Bilesen,crm.irfan.entity.BilesenTip,crm.irfan.entity.Genel" %>
<%@ page import="crm.irfan.entity.Stok" %>
<%@ page import="java.util.List" %>

<%@ include file="logincheck.jsp" %>

<%
    List<Bilesen> hammadde = (List<Bilesen>) request.getAttribute("hammadde");
List<Stok> stok = (List<Stok>) request.getAttribute("stok");
%>

<script type="text/javascript">
	function saveHammaddeStok(){
		if(!is_positive(document.hammaddestokform.miktar.value)){
			alert("Miktar NÜMERİK bir değer olmalıdır!");
			return false;
		}
		else if(is_empty(document.hammaddestokform.irsaliyeno.value) ){
			alert("İRSALİYE No boş olamaz!");
			return false;
		}
		else if(is_empty(document.hammaddestokform.lot.value) ){
			alert("LOT/Batch No boş olamaz!");
			return false;
		}
		else{
			document.hammaddestokform.submit();
		}
	}

	var bilesen = [
	<%String delimeter = "";
	for (Bilesen b : hammadde) {
		out.println(delimeter 
	+ " { id:" + b.getId()
	+ ",bilesenid:" + b.getId() 
	+ ",bilesenad:'" + b.getAd().replaceAll("'", "") + "'"
	+ ",bilesenkod:'" + b.getKod() + "'"
	+ ",bilesentipid:" + b.getBilesentipid()
	+ ",bilesentipad:'" + b.getBilesentipad() + "'"
	+ ",firmaid:" + b.getFirmaid()
	+ ",firmaad:'" + b.getFirmaad().replaceAll("'", "") + "'" + "}");
		delimeter = ",";
	}%>];
	var bilesen2 = [
	<%delimeter = "";
	for (Bilesen b : hammadde) {
		out.println(delimeter + " { label:'" + b.getKod() + " ["+b.getAd().replaceAll("'", "") +"]',id:" + b.getId() + "}");
		delimeter = ",";
	}%>];

</script>

<div class="container">
	<div class="row">
		<form class="form-horizontal" role="form" method="post" name="hammaddestokform" id="hammaddestokform" action="hammaddestok">
		<div class="text-center">
			<label class="text-danger rounded">Hammadde ve Yarı Mamül Stok Ekleme</label>
		</div>
		<!-- sol -->
		<div class="col-xs-6">
			<div class="form-group">
				<label for="bilesenid" class="col-xs-4 control-label text-baslik">Malzeme Kodu: </label>
				<div>
					<input type="text" class="form-control big" name="bilesenkod" id="bilesenkod" autocomplete="off">
					<input type="hidden" name="bilesenid" id="bilesenid">
				</div>
			</div>
			
			<div class="form-group">
				<label for="bilesenad" class="col-xs-4 control-label text-baslik">Malzeme Adı: </label>
				<div>
 					<input type="text" class="form-control big" name="bilesenad" id="bilesenad" readonly="readonly">
				</div>
			</div>
			
			<div class="form-group">
				<label for="firmaad" class="col-xs-4 control-label text-baslik">Tedarikçi Adı: </label>
				<div>
 					<input type="text" class="form-control big" name="firmaad" id="firmaad" readonly="readonly">
 					<input type="hidden" name="firmaid" id="firmaid">
				</div>
			</div>

			<div class="form-group">
				<label for="tarih" class="col-xs-4 control-label text-baslik">Tarih</label>
				<div>
					<input type="text" class="form-control big" name="tarih" id="tarih" placeholder="Tarih">
				</div>
			</div>
			
		</div>
		
		<!-- sag -->
		<div class="col-xs-6">
			<div class="form-group">
				<label for="miktar" class="col-xs-4 control-label text-baslik">Miktarı: </label>
				<div>
					<input type="text" class="form-control big" style="display:inline-block;" name="miktar" id="miktar" placeholder="Miktar" autocomplete="off">
					<!-- <span id="malzemebirimad" class="birim">Kg.</span> -->
				</div>
			</div>
			
			<div class="form-group">
				<label for="miktar" class="col-xs-4 control-label text-baslik">Birim: </label>
				<input type="text" class="form-control big" name="birimid_select" id="birimid_select"  value="Adet" readonly="readonly">
				<input type="hidden" name="birimid" id="birimid" value="2">
			</div>
			<div class="form-group">
				<label for="irsaliyeno" class="col-xs-4 control-label text-baslik">İrsaliye No: </label>
				<div>
					<input type="text" class="form-control big" name="irsaliyeno" id="irsaliyeno" placeholder="İrsaliye No" autocomplete="off">
				</div>
			</div>
			<div class="form-group">
				<label for="lot" class="col-xs-4 control-label text-baslik">LOT/Batch No: </label>
				<div>
					<input type="text" class="form-control big" name="lot" id="lot" placeholder="LOT/Batch No" autocomplete="off">
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<!-- orta -->
		<%
		    String admin = (String) session.getAttribute("admin");
				if(admin!=null && admin.equals("1")){
		%>
		<div>
			<div class="form-group">
				<div class="text-center">
					<button type="button" class="btn btn-danger" onclick="saveHammaddeStok()">Ekle</button>
				</div>
			</div>
		</div>
		<%
		    }
		%>
		</form>

	</div>

	<div class="row text-center">
		<label class="text-danger roundedmini">Malzeme Stok Hareketleri</label>
	</div>
	
	<%
		    int sayac = 0;
		%>
	<div class="row" style="font-size:12px;">
		<table class="tableplan">
			<%
			    for (Stok s : stok) {
			%>
			<%
			    if (sayac++ == 0) {
			%>
			<tr>
				<th>Stok No</th>
				<th>Malzeme Kodu</th>
				<th>Malzeme Adı</th>
				<th>Tipi</th>
				<th>Miktarı</th>
				<th>Hareket Tarihi</th>
				<th>Hareket Tipi</th>
				<th>GKR. No</th>
				<th>İrsaliye No</th>
				<th>Lot/Batch No</th>
				<th>Aksiyon</th>
			</tr>
			<%
			    }
			%>
			<tr id="tr<%=s.getId()%>">
				<td><%=s.getId()%></td>
				<td><%=s.getBilesenkod()%></td>
				<td><%=s.getBilesenad()%></td>
				<td><%=s.getBilesentipad()%></td>
				<%
				    String smiktar = "";
								Double dmkitar = 0.0;
								Integer imiktar= 0;
								if(s.getBilesentipid() == BilesenTip.HAMMADDE.value()){
									if( (Integer.valueOf(s.getMiktar()) / 1000.0) > ( Integer.valueOf(s.getMiktar()) / 1000)){
										dmkitar = (Integer.valueOf(s.getMiktar())) / 1000.0;
										smiktar = dmkitar.toString() + " Kg.";
									}
									else{
										imiktar = (Integer.valueOf(s.getMiktar()))/1000;
										smiktar = imiktar.toString() + " Kg.";
									}
								}
								else{
									smiktar = s.getMiktar().toString() + " Ad.";
								}
				%>
				<td class="text-right"><%=smiktar%></td>
				<td class="text-right"><%=(s.getIslemyonu()==0) ? Util.getTarihTR(s.getGiristarihi()) : Util.getTarihTR(s.getCikistarihi())%></td>
				<td><%= (s.getIslemyonu()==0)?"<span class='text-success'>Giriş</span>":"<span class='text-danger'>Çıkış</span>" %></td>
				<td><%= s.getGkrno() %></td>
				<td><%= (s.getIrsaliyeno()==null) ? "" : s.getIrsaliyeno() %></td>
				<td><%= (s.getLot()==null) ? "" : s.getLot() %></td>
				<td class="text-center">
				
					<% 
					if(admin!=null && admin.equals("1") && (s.getIslemyonu()==0)){
					%>
					<a href="javascript:deleteHammaddeStokGo('hammaddestok',<%=s.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
					<% } %>
				
				</td>
			</tr>
			<% } %>
		</table>
		<jsp:include page="paging.jsp" flush="true" >
			<jsp:param value="noofpages" name="noofpages"/>
			<jsp:param value="currentpage" name="currentpage"/>
			<jsp:param value="hammaddestok" name="pagename"/>
		</jsp:include>
	</div>

</div>

<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
<script	src="js/bootbox.js" type="text/javascript"></script>
<script src="js/irfan.js" type="text/javascript"></script>
<script src="js/hammaddestok.js" type="text/javascript"></script>

<script type="text/javascript">
$(function() {
	$('#tarih').datepicker({
		inline: true,
		format: 'dd-mm-yy',
		firstDay:1,
	}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","today");
});
$("#bilesenkod" ).autocomplete({
	source		: bilesen2,
	minLength	: 1,
	select		: function(event, ui){
		changefun(ui.item);
	}
});
$("#bilesenkod").change(function() {
	changefun(this);
});
function changefun(elem){
	var found = false;
	if (elem != null){
		for (i in bilesen) {
			/* autocomplete alanina birden fazla alan ekler */
			var b = elem.value.split("[");
			if(b[0].trim() == bilesen[i].bilesenkod){
				//console.log("elem: " + elem.value);
				//console.log("bilesen: " + bilesen[i]);
				found = true;
				$("#bilesenid").val(bilesen[i].bilesenid);
				$("#bilesenad").val(bilesen[i].bilesenad);
				$("#firmaid").val(bilesen[i].firmaid);
				$("#firmaad").val(bilesen[i].firmaad);
				$("#bilesentipid").val(bilesen[i].bilesentipid);
				if(bilesen[i].bilesentipid==<%= BilesenTip.HAMMADDE.value() %> ){
					$("#malzemebirimad").text("Kg.");
					$("#birimid_select").val("KG"); // KG
					$("#birimid").val(3); // KG
				}
				else{
					$("#malzemebirimad").text("Adet");
					$("#birimid_select").val("Adet"); // Adet
					$("#birimid").val(2); // Adet
				}
			}
		}
	}
	if(!found){
		$("#bilesenid").val("");
		$("#bilesenad").val("");
		$("#firmaid").val("");
		$("#firmaad").val("");
		$("#bilesentipid").val("");
	}
}
</script>
</body>
</html>