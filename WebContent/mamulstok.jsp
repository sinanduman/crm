<%@page import="crm.irfan.Util"%>
<%@page import="crm.irfan.entity.*"%>
<%@page import="java.util.Date"%>
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
<%@ page import="java.util.List" %>

<%@ include file="logincheck.jsp" %>

<%
    @SuppressWarnings("unchecked")
List<Mamul> mamul	= (List<Mamul>) request.getAttribute("mamul");
@SuppressWarnings("unchecked")
List<Firma> firma	= (List<Firma>) request.getAttribute("firma");
@SuppressWarnings("unchecked")
List<Stok> stok		= (List<Stok>) request.getAttribute("stok");

Integer totalrecord	= (Integer) request.getAttribute("totalrecord");
Integer sumagg		= (Integer) request.getAttribute("sumagg");
Integer currentpage	= (Integer) request.getAttribute("currentpage");
Integer noofpages	= (Integer) request.getAttribute("noofpages");
String mamulid		= (String) request.getAttribute("mamulid");
String excelsql		= (String) request.getAttribute("excelsql");
%>

<script>
	var mamul = [
	<%String delimeter = "";
	for (Mamul m : mamul) {
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
	for (Mamul m : mamul) {
		out.println(delimeter + " { label:'" + m.getKod() + "'" + ",id:" + m.getId() + "}");
		delimeter = ",";
	}%>];

</script>
<div class="container">
	<div class="row">
		<div class="text-warning text-center">
			<label class="text-danger rounded">Mamül Stok Ekleme</label>
		</div>
	</div>	

	<div class="row">
		<form class="form-inline" method="post" name="mamulstokform" id="mamulstokform" action="mamulstok">
		<div class="col-xs-5">
			<div class="form-group">
				<label for="mamulkod" class="text-baslik">Mamül Kodu: </label>
				<div>
					<input type="text" class="form-control big" name="mamulkod" id="mamulkod" placeholder="Mamül Kodu" autocomplete="off">
					<input type="hidden" name="mamulid" id="mamulid">
				</div>
			</div>
			
			<div class="form-group">
				<label for="stokliste" class="text-success bgsaydam">&nbsp;&nbsp;</label>
				<div>
					<button type="button" class="btn btn-danger" name="stokliste" id="stokliste" disabled="disabled">Stok Kartı Getir</button>
					<input type="hidden" name="stoklisteid" id="stoklisteid" value="0">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="form-group">
				<label for="mamulad" class="text-baslik">Mamül Adı: </label>
				<div>
					<input type="text" class="form-control big" name="mamulad" id="mamulad" placeholder="Mamül Adı" readonly="readonly">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="form-group">
				<label for="miktar" class="text-baslik">Miktarı: </label>
				<div>
					<input type="text" class="form-control normal" name="miktar" id="miktar" placeholder="Miktar (<%=BirimTip.ADET.ad()%>)" autocomplete="off">
				</div>
			</div>
			<%
			    if(stok!=null && !stok.isEmpty()){
			%>
			<div class="form-group">
				<label for="miktar" class="text-baslik">Stoktan Düş: </label>
				<div>
			     	<input type="checkbox" id="iade" name="iade" value="1"><span class="text-danger"></span>
			  	</div>
			</div>
			<%
			    }
			%>
			<div class="clearfix"></div>

			<div class="form-group">
				<label for="irsaliyeno" class="text-baslik">İrsaliye No: </label>
				<div>
					<input type="text" class="form-control big" name="irsaliyeno" id="irsaliyeno" placeholder="İrsaliye No"  autocomplete="off">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="form-group">
				<label for="lot" class="control-label text-baslik">LOT/Batch No: </label>
				<div>
					<input type="text" class="form-control big" name="lot" id="lot" placeholder="LOT/Batch No" autocomplete="off">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="form-group">
				<label for="tarih" class="control-label text-baslik">Tarih</label>
				<div>
					<input type="text" class="form-control big" name="tarih" id="tarih" placeholder="Tarih">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="form-group">
				<label for="not" class="control-label text-baslik">Not: </label>
				<div>
					<textarea name="not" id="not" name="not" rows="3" class="form-control big" placeholder="Not"></textarea>
				</div>
			</div>
			<div class="clearfix"></div>
			
			<%
						    String admin = (String) session.getAttribute("admin");
									if(admin!=null && admin.equals("1")){
						%>
			<div class="form-group" style="margin-top:5px;">
				<div>
					<button type="button" class="btn btn-danger" id="mamulstokekle" name="mamulstokekle">Ekle</button>
					<input type="hidden" name="mamulekleid" id="mamulekleid" value="0">
				</div>
			</div>
			<div class="clearfix"></div>
			<%
			    }
			%>
			
		</div>
		</form>
		<div class="col-xs-7" style="font-size:12px;">
			<table class="tableplan">
			<tr>
				<th class="text-center"><img alt="İrfan Plastik" src="img/ip_logo_mini.png" class="img-rounded"/></th>
				<th colspan="5" class="text-center" style="vertical-align:middle;">MAMÜL STOK TAKİP KARTI</th>
			</tr>
			<%
			    if(stok!=null && !stok.isEmpty()){
			%>
			<tr>
				<th>Ürün Kodu</th>
				<th colspan="2" class="text-center"><label class="text-danger"><%=stok.get(0).getBilesenkod()%> </label></th>
				<th>Envanter Tarihi</th>
				<th><label class="text-danger"><%=Util.getTarihTR(new java.util.Date())%> </label></th>
			</tr>
			<tr>
				<th>Ürün Tanımı</th>
				<th colspan="2" class="text-center"><label class="text-danger"><%=stok.get(0).getBilesenad()%></label></th>
				<th>Envanter Miktarı</th>
				<th><label class="text-danger"><%=sumagg%></label><input type="hidden" id="sumagg" name="sumagg" value="<%=sumagg%>"></th>
			</tr>
			<tr>
				<td width="20%"><label class="text-sevk">TARİH</label></td>
				<td width="15%"><label class="text-sevk">GİREN</label></td>
				<td width="15%"><label class="text-sevk">ÇIKAN</label></td>
				<td width="20%"><label class="text-sevk">KALAN</label></td>
				<td width="30%"><label class="text-sevk">AÇIKLAMA</label></td>
			</tr>
			<%
			    }
			%>
			<%
			    String giren= "";
						String cikan= "";
						String not	= "";
						int sayac	= 0;
						for (Stok s : stok) {
						    sayac++;
						    not = (s.getNot()==null)?"":s.getNot();
							if(s.getIslemyonu()==1){
								giren = "";
								cikan = s.getMiktar();
							}
							else{
								giren = s.getMiktar();
								cikan = "";
							}
							out.println("<tr>");
							out.println("<td>"+ Util.getTarihTR((s.getIslemyonu()==1)?s.getCikistarihi():s.getGiristarihi()) +"</td>");
							out.println("<td class='text-right'>"+ giren +"</td>");
							out.println("<td class='text-right'>"+ cikan +"</td>");
							out.println("<td class='text-right'>"+ s.getKalan() +"</td>");
							out.println("<td>"+ not +"</td>");
							out.println("</tr>");
						}
			%>
			</table>
			<!-- paging -->
			<%
			    String param1 = "&amp;mamulid="+ mamulid;
						String param2 = "&amp;stoklisteid=1";
			%>
			<jsp:include page="paging.jsp" flush="true" >
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="mamulstok" name="pagename"/>
				<jsp:param value="<%=param1%>" name="param1"/>
				<jsp:param value="<%=param2%>" name="param2"/>
			</jsp:include>
			<%
			    if (sayac >0 ){
			%>
					<div class="text-right">
						<div>
							<form method="post" name="excelform" id="excelform" action="excelmamulstok">
								<button type="button" class="btn btn-danger" name="exceleaktar_mamul" id="exceleaktar_mamul">Excele Aktar</button>
								<input type="hidden" value="0" name="excelegonder" id="excelegonder">
								<input type="hidden" name="exceltarih" id="exceltarih" value="<%=Util.getTarihTR(new Date())%>">
								<input type="hidden" name="excelsql" id="excelsql" value="<%=excelsql%>">
							</form>
						</div>
					</div>
				<%
			}
			%>
		</div>
	</div>
</div>

<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/irfan.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script src="js/mamulstok.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>

<script type="text/javascript">
$(function() {
	$('#tarih').datepicker({
		inline: true,
		format : 'dd-mm-yy',
		firstDay:1,
	}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","today");
});
$("#mamulkod" ).autocomplete({
	source		: mamul2,
	minLength	: 1,
	select		: function(event, ui){
		changefun(ui.item);
	}
});
$("#mamulkod").change(function() {
	changefun(this);
});

$("#stokliste").click(function() {
	$("#stoklisteid").val("1");
	$("#mamulstokform").submit();
});

function changefun(elem){
	//console.log("fun: " + elem);
	//console.log("fun val: " + elem.value);	
	var found = false;
	if (elem != null){
		for (i in mamul) {
			if(elem.value == mamul[i].mamulkod){
				found = true;
				//alert("selam kelam");
				$("#mamulid").val(mamul[i].mamulid);
				$("#mamulad").val(mamul[i].mamulad);
				$("#firmaid").val(mamul[i].firmaid);
				$("#firmaad").val(mamul[i].firmaad);
				$("#stokliste").removeAttr("disabled");
			}
		}
	}
	if(!found){
		$("#mamulid").val("");
		$("#mamulad").val("");
		$("#firmaid").val("");
		$("#firmaad").val("");
		$("#stokliste").attr("disabled","disabled");
	}
}
$(function() {
	<%
	if(mamulid!=null){
	%>
	$("#mamulid").val("<%=mamulid%>");
	//console.log("mamulid frist: " + $("#mamulid").val());
	findmamulkod($("#mamulid").val());
	$("#mamulkod").change(function() {
		changefun(this);
	});
	<%
	}
	%>
});

function findmamulkod(elemval){
	//console.log("mamulid : " + elemval);
	if (elemval != null){
		for (i in mamul) {
			if(elemval == mamul[i].mamulid){				
				$("#mamulkod").val(mamul[i].mamulkod);
				$("#mamulad").val(mamul[i].mamulad);
				$("#stokliste").removeAttr("disabled");
				break;
			}
		}
	}
}
</script>
</body>
</html>