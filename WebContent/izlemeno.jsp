<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crm.irfan.entity.Bilesen, crm.irfan.entity.BilesenTip, crm.irfan.entity.Genel" %>
<%@ page import="crm.irfan.entity.Mamul, crm.irfan.entity.IzlemeNo" %>
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
	List<Mamul> mamul		= (List<Mamul>) request.getAttribute("mamul");
	@SuppressWarnings("unchecked")
	List<IzlemeNo> stok = (List<IzlemeNo>) request.getAttribute("stok");
    Integer totalrecord = (Integer) request.getAttribute("totalrecord");
    Integer currentpage = (Integer) request.getAttribute("currentpage");
    Integer noofpages = (Integer) request.getAttribute("noofpages");
    Integer bilesenid = (Integer) request.getAttribute("bilesenid");
    
    String bas_tarih = (String) request.getAttribute("bas_tarih");
    String bit_tarih = (String) request.getAttribute("bit_tarih");
    String excelsql = (String) request.getAttribute("excelsql");
    String admin	= (String) session.getAttribute("admin");
%>

<script type="text/javascript">

	var mamul = [
	<%String delimeter = "";
	for (Mamul m : mamul) {
		out.println(delimeter 
	+ " { id:" + m.getId()
	+ ",bilesenid:" + m.getId() 
	+ ",bilesenad:'" + m.getAd().replaceAll("'", "") + "'"
	+ ",bilesenkod:'" + m.getKod() + "'"
	+ ",label:'" + m.getKod() + "'" + "}");
		delimeter = ",";
	}%>];
	
	var mamul2 = [
		<%delimeter = "";
		for (Mamul m : mamul) {
			out.println(delimeter + " { value:'"+ m.getKod() + "', label:'" + m.getKod() + " ["+m.getAd().replaceAll("'", "") +"]',id:" + m.getId() + "}");
			delimeter = ",";
		}%>];

</script>


<div class="container">
	<div class="row text-warning text-center">
		<label class="text-danger rounded">Mamül İzleme No</label>
	</div>
	
	<div class="row">
		<form class="form-inline" name="izlemenoform" id="izlemenoform" method="post" action="izlemeno">
			<%
				String disabled="";
				String readonly="";
			    if("".equals("")){
			        disabled="disabled='disabled'";
			        readonly="readonly='readonly'";
			    }
			%>
			
			<div class="form-group">
				<label for="tarih" class="text-baslik">Başlangıç Tarihi</label>
				<div>
					<input type="text" style="width:120px;" class="form-control" name="bas_tarih" id="bas_tarih" placeholder="Tarih">
				</div>
			</div>
			
			<div class="form-group">
				<label for="tarih" class="text-baslik">Bitiş Tarihi</label>
				<div>
					<input type="text" style="width:120px;" class="form-control" name="bit_tarih" id="bit_tarih" placeholder="Tarih">
				</div>
			</div>
						
			<div class="form-group">
				<label for="mamulkod" class="text-baslik">Mamül Kodu</label>
				<div>
					<input type="text" class="form-control small" name="mamulkod" id="mamulkod" autocomplete="off">
					<input type="hidden" name="bilesenid" id="bilesenid" value='<%= (bilesenid!=null)?bilesenid:"" %>'>
				</div>
			</div>
			
			<div class="form-group">
				<label for="mamulad" class="text-baslik">Mamül Adı</label>
				<div>
					<input type="text" class="form-control normal" name="mamulad" id="mamulad" readonly="readonly">
				</div>
			</div>
			
			<div class="form-group">
				<label for="gkrno" class="text-baslik">Kullanılmamış İzl. No</label>
				<div>
					<select class="form-control normal" name="gkrno" id="gkrno"></select>
					<input type="hidden" name="stokid" id="stokid">
					<input type="hidden" name="miktarbox" id="miktarbox">
				</div>
			</div>
			
			<div class="form-group">
				<label for="mamulkod" class="text-success bgsaydam">&nbsp;&nbsp;</label>
				<div>
					<% 
					if(admin!=null && admin.equals("1")){
					%>
						<button type="button" class="btn btn-success" name="izlemenouret" id="izlemenouret" disabled="disabled">Yeni İzl. No Üret</button>
					<% 
					}
					%>
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
		<table class="tableplan" id="izlemenotable">
			
			<tr>
				<th>Tarih</th>
				<th>İzleme No</th>
				<th>Mamül Kod</th>
				<th>Mamül Ad</th>
				<th>Miktar</th>
				<% 
				if(admin!=null && admin.equals("1")){
				%>
				<th class="text-center"><label class="text-success">Aksiyon</label></th>
				<% } %>
			</tr>
			<%
			    for (IzlemeNo s : stok) {
			        sayac++;
			%>
			
			<tr id="tr<%=s.getMamulid()%>_<%=s.getGkrno()%>">
				<td><%=Util.getTarihTR(s.getKullanildi_tarih())%></td>
				<td><%=s.getGkrno() %></td>
				<td><%= (s.getMamulkod()==null?"":s.getMamulkod()) %></td>
				<td><%= (s.getMamulad()==null?"":s.getMamulad()) %></td>
				<td class="text-right"><%=s.getMiktar() + " Ad."%></td>
				<% 
				if(admin!=null && admin.equals("1")){
				%>
				<td>
					<%
					if(s.getKullanildi() == 0){
					%>
					<div id="div<%= s.getId() %>" class="text-center">
						<a href="javascript:izlemeNoTest(<%=s.getMamulid()%>,3,<%=s.getKullanildi()%>,'#gkrno',<%=s.getGkrno()%>);" title="İzleme No Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
					</div>
					<% } %>
				</td>
				<% }%>
			</tr>
			<% } %>
		</table>
		<%
		    String param1 = "&amp;bilesenid="+ bilesenid;
		    String param2 = (bas_tarih==null || bas_tarih.equals(""))?"":"&amp;bas_tarih="+ bas_tarih;
			String param3 = (bit_tarih==null || bit_tarih.equals(""))?"":"&amp;bit_tarih="+ bit_tarih;
		%>
		<jsp:include page="paging.jsp" flush="true" >
			<jsp:param value="noofpages" name="noofpages"/>
			<jsp:param value="currentpage" name="currentpage"/>
			<jsp:param value="currentpage" name="currentpage"/>
			<jsp:param value="hammaddestok" name="pagename"/>
			<jsp:param value="<%=param1%>" name="param1"/>
			<jsp:param value="<%=param2%>" name="param2"/>
			<jsp:param value="<%=param3%>" name="param3"/>
		</jsp:include>
	</div>
	<%
	if (sayac >0 ){
		%>
			<div class="row text-right">
				<div>
					<form method="post" name="excelform" id="excelform" action="excelizlemeno">
						<button type="button" class="btn btn-danger" name="exceleaktar_izlemeno" id="exceleaktar_izlemeno">Excele Aktar</button>
						<input type="hidden" value="0" name="excelegonder" id="excelegonder">
						<input type="hidden" name="excelbastarih" id="excelbastarih" value="<%=bas_tarih%>">
						<input type="hidden" name="excelbittarih" id="excelbittarih" value="<%=bit_tarih%>">
						<input type="hidden" name="excelsql" id="excelsql" value="<%=excelsql%>">
					</form>
				</div>
			</div>
		<%
	}
	%>

</div>

<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
<script	src="js/bootbox.js" type="text/javascript"></script>
<script src="js/irfan.js?<%=System.currentTimeMillis() %>" type="text/javascript"></script>
<script src="js/izlemeno.js?<%=System.currentTimeMillis() %>" type="text/javascript"></script>

<script type="text/javascript">
$(function() {
	
	$('#bas_tarih').datepicker({
		inline: true,
		format : 'dd-mm-yy',
		firstDay:1,
	}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","<%= (bas_tarih=="")?"today":bas_tarih%>");
	
	$('#bit_tarih').datepicker({
		inline: true,
		format : 'dd-mm-yy',
		firstDay:1,
	}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","<%= (bit_tarih=="")?"today":bit_tarih%>");
	
	$("#mamulkod" ).autocomplete({
		source		: mamul2,
		minLength	: 1,
		select		: function(event, ui){
			//changefun(ui.item);
			changefun(ui.item);
			$(this).val(ui.item.value);
		}
	});
	
	$("#mamulkod").change(function() {
		changefun(this);
	});
	
	if($("#bilesenid").val() != "0"){
		var found_bilesenid = mamul.filter(function(obj) {
		    return obj.id == $("#bilesenid").val();
		});
		/*
		var found_bilesenid = $.grep(bilesen, function(v) {
		    return v.id == $("#bilesenid").val();
		});
		*/

		if (found_bilesenid != undefined){
			//console.log(found_bilesenid);
			$("#mamulkod").val( found_bilesenid[0].bilesenkod );
			$("#mamulad").val( found_bilesenid[0].bilesenad );
			$("#izlemenouret").prop( "disabled", false );			
			/* 1: Uretim, 6: Kontrol */
			izlemeNoKontrol($("#bilesenid").val(), 6, 0, "#gkrno", 0);
		}
	}
});
function changefun(elem){
	var found = false;
	if (elem != null){
		for (i in mamul) {
			/* autocomplete alanina birden fazla alan ekler */
			if(elem.value == mamul[i].bilesenkod){
				//console.log("mamulid: " + mamul[i].bilesenid);
				//console.log("mamulad: " + mamul[i].bilesenad);
				//console.log("mamulkod: " + mamul[i].bilesenkod);
				found = true;
				$("#bilesenid").val(mamul[i].bilesenid);
				$("#mamulad").val(mamul[i].bilesenad);
				$("#izlemenouret").prop( "disabled", false );				
				/* 1: Uretim, 6: Kontrol */
				izlemeNoKontrol($("#bilesenid").val(), 6, 0, "#gkrno");
				
			}
		}
	}
	if(!found){
		$("#bilesenid").val("");
		$("#mamulad").val("");
		$("#izlemenouret").prop( "disabled", true );
		$("#gkrno option").remove();
	}
}
</script>
</body>
</html>