<%@page import="crm.irfan.Util"%>
<%@page import="crm.irfan.entity.*"%>
<%@page import="java.util.Date, java.util.List"%>
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
<%@ include file="logincheck.jsp" %>

<%
    List<Bilesen> hammadde	= (List<Bilesen>) request.getAttribute("hammadde");
	List<Stok> stok 		= (List<Stok>) request.getAttribute("stok");

	List<Mamul> mamul		= (List<Mamul>) request.getAttribute("mamul");
	List<Firma> firma		= (List<Firma>) request.getAttribute("firma");
	
	Integer totalrecord		= (Integer) request.getAttribute("totalrecord");
	Integer sumagg			= (Integer) request.getAttribute("sumagg");
	Integer currentpage		= (Integer) request.getAttribute("currentpage");
	Integer noofpages		= (Integer) request.getAttribute("noofpages");
	String bilesenid		= (String) request.getAttribute("bilesenid");
	String excelsql			= (String) request.getAttribute("excelsql");
%>

<script type="text/javascript">
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
	    out.println(delimeter + " { value:'"+ b.getKod() + "',label:'" + b.getKod() + " ["+b.getAd().replaceAll("'", "") +"]',id:" + b.getId() + "}");
		delimeter = ",";
	}%>];

</script>

<div class="container">
	<div class="row">
		<div class="text-warning text-center">
			<label class="text-danger rounded">Hammadde / Yarımamül Stok Ekleme</label>
		</div>
	</div>	

	<div class="row">
		<form class="form-inline" method="post" method="post" name="hammaddestokform" id="hammaddestokform" action="hammaddestok">
		<div class="col-xs-5">
			<div class="form-group">
				<label for="bilesenkod" class="text-baslik">Malzeme Kodu: </label>
				<div>
					<input type="text" class="form-control big" name="bilesenkod" id="bilesenkod" placeholder="Malzeme Kodu" autocomplete="off">
					<input type="hidden" name="bilesenid" id="bilesenid">
					<input type="hidden" name="bilesentipid" id="bilesentipid">
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
				<label for="mamulad" class="text-baslik">Malzeme Adı: </label>
				<div>
					<input type="text" class="form-control big" name="bilesenad" id="bilesenad" placeholder="Malzeme Adı" readonly="readonly">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<div class="form-group">
				<label for="mamulad" class="text-baslik">Tedarikçi Adı: </label>
				<div>
					<input type="text" class="form-control big" name="firmaad" id="firmaad" placeholder="Tedarikçi Adı" readonly="readonly">
					<input type="hidden" name="firmaid" id="firmaid">
				</div>
			</div>
			<div class="clearfix"></div>
			
			<%
			String sumaggStr			= "";
			String sumaggTip			= "";
			String malzemeBirimCarpan 	= "1";
			%>
			<%
			sumaggTip 		= BirimTip.ADET.ad();
			sumaggStr		= sumagg.toString();
			if(stok!=null && !stok.isEmpty()){
				if(stok.get(0).getBilesentipid()==1){
				    Double s			= Util.Round((Double.valueOf(sumagg) / Double.parseDouble("1000.0")),3.0);
				    sumaggStr			= s.toString();
				    sumaggTip			= BirimTip.KILOGRAM.ad();
				    malzemeBirimCarpan	= "1000";
				}
			}
			%>
			
			<div class="form-group">
				<label for="miktar" class="text-baslik">Miktarı (<span class="text-danger"><%=sumaggTip %></span>):</label> 
				<div>
					<input type="text" class="form-control normal" name="miktar" id="miktar" placeholder="Miktar" autocomplete="off">
					<input type="hidden" id="malzemebirimad" name="malzemebirimad" class="birim" value="<%=sumaggTip %>">
					<input type="hidden" id="malzemebirimcarpan" name="malzemebirimcarpan" value="<%=malzemeBirimCarpan%>">
				</div>
			</div>
			<%
			    if(stok!=null && !stok.isEmpty() && (sumagg > 0)){
			%>
			<div class="form-group">
				<label for="miktar" class="text-baslik">Stoktan Düş: </label>
				<div>
			     	<input type="checkbox" id="iade" name="iade" value="1" onchange="javascript:changeStokButonText();"><span class="text-danger"></span>
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
			<div><input type="hidden" name="bilesenekleid" id="bilesenekleid" value="0"></div>
			<%
			    String admin = (String) session.getAttribute("admin");
				if(admin!=null && admin.equals("1")){
			%>
			<div class="form-group" style="margin-top:5px;">
				<div>
					<button type="button" class="btn btn-danger" id="hammaddestokekle" name="hammaddestokekle">Ekle</button>
					<span class="text-left text-danger birim" id="hammadde-stok-ekle-kutu"></span>
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
				<th colspan="5" class="text-center" style="vertical-align:middle;">HAMMADDE / YARIMAMÜL STOK TAKİP KARTI</th>
			</tr>
			<%
				if(stok!=null && !stok.isEmpty()){
			%>
			<tr>
				<th>Malzeme Kodu</th>
				<th colspan="2" class="text-center"><label class="text-danger"><%=stok.get(0).getBilesenkod()%> </label></th>
				<th>Envanter Tarihi</th>
				<th><label class="text-danger"><%=Util.getTarihTR(new java.util.Date())%> </label></th>
			</tr>
			<tr>				
				<th>Malzeme Adı</th>
				<th colspan="2" class="text-center"><label class="text-danger"><%=stok.get(0).getBilesenad()%></label></th>
				<th>Envanter Miktarı</th>
				<th><label class="text-danger"><%=sumaggStr%></label> <%=sumaggTip %><input type="hidden" id="sumagg" name="sumagg" value="<%=sumaggStr%>"></th>
			</tr>
			<tr>
				<td width="15%"><label class="text-sevk">TARİH</label></td>
				<td width="18%" class='text-center'><label class="text-sevk">GİREN</label></td>
				<td width="18%" class='text-center'><label class="text-sevk">ÇIKAN</label></td>
				<td width="18%" class='text-center'><label class="text-sevk">KALAN</label></td>
				<td width="31%"><label class="text-sevk">AÇIKLAMA</label></td>
			</tr>
			<%
			    }
			%>
			<%
	    		String giren= "";
				String cikan= "";
				String kalan= "";
				String not	= "";
				int sayac	= 0;
				for (Stok s : stok) {
				    sayac++;
				    not = (!Util.isNotEmptyOrNull(s.getNot()))? "İzl.No:" + s.getGkrno() : s.getNot() + " - İzl.No:" + s.getGkrno();
				    not = not + (!Util.isNotEmptyOrNull(s.getMamulkod())?"":" - " + s.getMamulkod());
					if(s.getIslemyonu()==1){
						giren = "";
						cikan = s.getMiktar().toString();
					}
					else{
						giren = s.getMiktar().toString();
						cikan = "";
					}
					kalan = s.getKalan().toString();
					
					/* Hammadde */
					if(s.getBilesentipid()==1){
					    if(!giren.equals("")){
					        Double g= Util.Round((Double.valueOf(giren) / Double.parseDouble("1000.0")),3.0);
					        //Double g= Double.valueOf(giren);
						    giren	= g.toString();
					    }
					    if(!cikan.equals("")){
					        Double c= Util.Round((Double.valueOf(cikan) / Double.parseDouble("1000.0")),3.0);
					        //Double c= Double.valueOf(cikan);
						    cikan	= c.toString();
					    }
					    if(!kalan.equals("")){
					        Double k= Util.Round((Double.valueOf(kalan) / Double.parseDouble("1000.0")),3.0);
					        //Double k= Double.valueOf(kalan);
						    kalan	= k.toString();
					    }
					}
					out.println("<tr>");
					out.println("<td>"+ Util.getTarihTR((s.getIslemyonu()==1)?s.getCikistarihi():s.getGiristarihi()) +"</td>");
					out.println("<td class='text-right-stok'>"+ giren +"</td>");
					out.println("<td class='text-right-stok'>"+ cikan +"</td>");
					out.println("<td class='text-right-stok'>"+ kalan +"</td>");
					out.println("<td>"+ not +"</td>");
					out.println("</tr>");
				}
			%>
			</table>
			<!-- paging -->
			<%
			    String param1 = "&amp;bilesenid="+ bilesenid;
				String param2 = "&amp;stoklisteid=1";
			%>
			<jsp:include page="paging.jsp" flush="true" >
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="hammaddestok" name="pagename"/>
				<jsp:param value="<%=param1%>" name="param1"/>
				<jsp:param value="<%=param2%>" name="param2"/>
			</jsp:include>
			<%
			    if (sayac <0 ){
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

<script src="js/jquery.min.js" type="text/javascript"></script>
<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script	src="js/bootbox.js" type="text/javascript"></script>
<script src="js/irfan.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
<script src="js/hammaddestok.js?<%=System.currentTimeMillis()%>" type="text/javascript"></script>
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
		$(this).val(ui.item.value);
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
			if(elem.value == bilesen[i].bilesenkod){
				//console.log("elem: " + elem.value);
				//console.log("bilesen: " + bilesen[i]);
				found = true;
				$("#bilesenid").val(bilesen[i].bilesenid);
				$("#bilesenad").val(bilesen[i].bilesenad);
				$("#bilesenkod").val(bilesen[i].bilesenkod);
				$("#firmaid").val(bilesen[i].firmaid);
				$("#firmaad").val(bilesen[i].firmaad);
				$("#stokliste").removeAttr("disabled");
				$("#bilesentipid").val(bilesen[i].bilesentipid);
				if(bilesen[i].bilesentipid==<%= BilesenTip.HAMMADDE.value() %> ){
					$("#malzemebirimad").val("Kg.");
					$("#birimid").val(3); // KG
				}
				else{
					$("#malzemebirimad").val("Adet");
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
		$("#malzemebirimad").val("");
		$("#stokliste").attr("disabled","disabled");
	}
}
</script>


<script type="text/javascript">
$("#stokliste").click(function() {
	$("#stoklisteid").val("1");
	$("#hammaddestokform").submit();
});

$(function() {
	<%
	if(bilesenid!=null){
	%>
		$("#bilesenid").val("<%=bilesenid%>");
		//console.log("mamulid frist: " + $("#mamulid").val());
		findbilesenkod($("#bilesenid").val());
		$("#bilesenkod").change(function() {
			changefun(this);
		});
	<%
	}
	%>
});

function findbilesenkod(elemval){
	//console.log("bilesenid : " + elemval);
	if (elemval != null){
		for (i in bilesen) {
			if(elemval == bilesen[i].bilesenid){
				//console.log("elem: " + elemval);
				//console.log("bilesen: " + bilesen[i]);
				found = true;
				$("#bilesenid").val(bilesen[i].bilesenid);
				$("#bilesenad").val(bilesen[i].bilesenad);
				$("#bilesenkod").val(bilesen[i].bilesenkod);
				$("#firmaid").val(bilesen[i].firmaid);
				$("#firmaad").val(bilesen[i].firmaad);
				$("#stokliste").removeAttr("disabled");
				$("#bilesentipid").val(bilesen[i].bilesentipid);
				if(bilesen[i].bilesentipid==<%= BilesenTip.HAMMADDE.value() %> ){
					$("#malzemebirimad").val("Kg.");
					$("#birimid").val(3); // KG
				}
				else{
					$("#malzemebirimad").val("Adet");
					$("#birimid").val(2); // Adet
				}
			}
		}
	}
}
function changeStokButonText(){
	if ($("#iade").prop("checked")){
		$("#hammaddestokekle").text("Düş");
	}
	else{
		$("#hammaddestokekle").text("Ekle");
	}
}
</script>
</body>
</html>