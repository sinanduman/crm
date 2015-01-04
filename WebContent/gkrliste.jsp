<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.entity.Bilesen,crm.irfan.entity.BilesenTip,crm.irfan.entity.Genel" %>
<%@ page import="crm.irfan.entity.Stok" %>
<%@ page import="crm.irfan.Util"%>
<%@ page import="java.util.List" %>
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
	@SuppressWarnings("unchecked")
    List<Bilesen> hammadde	= (List<Bilesen>) request.getAttribute("hammadde");
	@SuppressWarnings("unchecked")
	List<Stok> stok 		= (List<Stok>) request.getAttribute("stok");
	Integer totalrecord		= (Integer) request.getAttribute("totalrecord");
	Integer currentpage		= (Integer) request.getAttribute("currentpage");
	Integer noofpages		= (Integer) request.getAttribute("noofpages");
	Integer bilesenid		= (Integer) request.getAttribute("bilesenid");
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
		out.println(delimeter + " { label:'" + b.getKod() + " ["+b.getAd().replaceAll("'", "") +"]',id:" + b.getId() + "}");
		delimeter = ",";
	}%>];

</script>

<div class="container">
	<div class="row text-warning text-center">
		<label class="text-danger rounded">Giriş Kontrol Rapor Listesi</label>
	</div>
	
	<div class="row">
		<form class="form-inline" name="gkrlisteform" id="gkrlisteform" method="post" action="gkrliste">
			
			<div class="form-group">
				<label for="mamulkod" class="text-baslik">Malzeme Kodu</label>
				<div>
 						<input type="text" class="form-control big" name="bilesenkod" id="bilesenkod" autocomplete="off">
 						<input type="hidden" name="bilesenid" id="bilesenid">
				</div>
			</div>
			
			<div class="form-group">
				<label for="bilesenad" class="text-baslik">Malzeme Adı</label>
				<div>
					<input type="text" class="form-control normal" name="bilesenad" id="bilesenad" readonly="readonly">
				</div>
			</div>
			
			<div class="form-group">
				<label for="mamulkod" class="text-success bgsaydam">&nbsp;&nbsp;</label>
				<div>
					<button type="button" class="btn btn-danger" name="raporgetir" id="raporgetir">Rapor Getir</button>
					<input type="hidden" name="raporgetirid" id="raporgetirid" value="0">
				</div>
			</div>
			
			<div class="clearfix"></div>

		</form>
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
				<th>Tarih</th>
				<th>Firma</th>
				<th>Bileşen Kod</th>
				<th>Bileşen Ad</th>
				<th>Miktar</th>
				<th>GKR. No</th>
				<th>İrsaliye No</th>
				<th>Lot/Batch No</th>
				<th>Açıklama</th>
			</tr>
			<%
			    }
			%>
			<tr id="tr<%=s.getId()%>">
				<td><%=(s.getIslemyonu()==0) ? Util.getTarihTR(s.getGiristarihi()) : Util.getTarihTR(s.getCikistarihi())%></td>
				<td><%=s.getFirmaad()%></td>
				<td><%=s.getBilesenkod()%></td>
				<td><%=s.getBilesenad()%></td>
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
				<td><%= s.getGkrno() %></td>
				<td><%= (s.getIrsaliyeno()==null) ? "" : s.getIrsaliyeno() %></td>
				<td><%= (s.getLot()==null) ? "" : s.getLot() %></td>
				<td>
					<%-- 
					if(admin!=null && admin.equals("1") && (s.getIslemyonu()==0)){
					%>
					<a href="javascript:deleteHammaddeStokGo('hammaddestok',<%=s.getId()%>,3);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
					<% } %>
					--%>
				</td>
			</tr>
			<% } %>
		</table>
		<%
		    String param1 = "&amp;bilesenid="+ bilesenid;
		%>
		<jsp:include page="paging.jsp" flush="true" >
			<jsp:param value="noofpages" name="noofpages"/>
			<jsp:param value="currentpage" name="currentpage"/>
			<jsp:param value="currentpage" name="currentpage"/>
			<jsp:param value="hammaddestok" name="pagename"/>
			<jsp:param value="<%=param1%>" name="param1"/>
		</jsp:include>
	</div>

</div>

<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
<script	src="js/bootbox.js" type="text/javascript"></script>
<script src="js/irfan.js" type="text/javascript"></script>
<script src="js/gkrliste.js?<%=System.currentTimeMillis() %>" type="text/javascript"></script>

<script type="text/javascript">
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