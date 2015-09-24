<%@ page import="crm.irfan.Util"%>
<%@ page import="crm.irfan.entity.*"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="shortcut icon" href="img/favicon.ico">
	<title><%=Genel.TITLE%></title>
	
	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="css/irfan.css">
	<link rel="stylesheet" href="css/fonts.css">
	<link rel="stylesheet" href="css/font-awesome.css">
	<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.css">
	<link rel="stylesheet/less" type="text/css" href="less/datepicker.less">

	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript" charset="utf-8">
		var plan = {};
		var plantemp = {};
	</script>
	<style type="text/css">
	.tableplan tr:nth-of-type(even) {
		background-color: #E1E1B8;
		border-bottom: 1px solid darkkhaki;
	</style>
</head>
<body>
	<%@ include file="logincheck.jsp" %>
	<%
		@SuppressWarnings("unchecked")
		List<Mamul> mamul		= (List<Mamul>) request.getAttribute("mamul");
		@SuppressWarnings("unchecked")
	    List<Firma> firma		= (List<Firma>) request.getAttribute("firma");
		@SuppressWarnings("unchecked")
		List<Irsaliye> irsaliyejson	= (List<Irsaliye>) request.getAttribute("irsaliyejson");
		@SuppressWarnings("unchecked")
		List<IrsaliyeBilesen> irsaliyebilesenonaylandi	= (List<IrsaliyeBilesen>) request.getAttribute("irsaliyebilesenonaylandi");
		
		//Integer mamulid 		= (Integer) request.getAttribute("mamulid");
		String mamulid 			= (String) request.getAttribute("mamulid");
		String irsaliyeid		= (String) request.getAttribute("irsaliyeid");
		String irsaliyeno		= (String) request.getAttribute("irsaliyeno");
		String firmaid			= (String) request.getAttribute("firmaid");
		String tarih			= (String) request.getAttribute("tarih");
		String excelsql			= (String) request.getAttribute("excelsql");
	%>
	
	<script>
		var irsaliyejson = [
		<%String delimeter = "";
		for (Irsaliye ij : irsaliyejson) {
			out.println(delimeter 
		+ " { id:" + ij.getId()
		+ ",irsaliyeid:" + ij.getId()
		+ ",irsaliyeno:'" + ij.getIrsaliyeno() + "'"
		+ ",label:'" + ij.getIrsaliyeno() + "'" + "}");
			delimeter = ",";
		}%>];
		
	</script>
	
	<script type="text/javascript">

		var mamul = [
		<%
		delimeter = "";
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
		<div class="row text-warning" style="text-align: center;">
			<label class="text-danger rounded">Sevk Raporu</label>
		</div>
		<div class="row">
			<form class="form-inline" name="raporform" id="raporform" method="post" action="sevkrapor">
				
				<div class="form-group">
					<label for="mamulkod" class="text-baslik">Mamül Kodu</label>
					<div>
						<input type="text" class="form-control small" name="mamulkod" id="mamulkod" autocomplete="off">
						<input type="hidden" name="mamulid" id="mamulid" value='<%= (mamulid!=null)?mamulid:"" %>'>
					</div>
				</div>
				
				<div class="form-group">
					<label for="mamulad" class="text-baslik">Mamül Adı</label>
					<div>
						<input type="text" class="form-control normal" name="mamulad" id="mamulad" readonly="readonly">
					</div>
				</div>
				
				<div class="form-group">
					<label for="calisanid" class="text-baslik">Firma</label>
					<div>
						<select	class="form-control small" id="firmaid" name="firmaid">
							<%
							    out.println("<option value=''>...Tüm...</option>");
								for (Firma f : firma) {
								    if(f.getId().toString().equals(firmaid)){
								        out.println("<option value='"+ f.getId() +"' selected>"+ f.getAd() +"</option>");
								    }
								    else{
								        out.println("<option value='"+ f.getId() +"'>"+ f.getAd() +"</option>");
								    }
								}
							%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="irsaliyeno" class="text-baslik">İrsaliye No</label>
					<div>
  						<input type="text" class="form-control small" name="irsaliyeno" id="irsaliyeno" value="<%=(irsaliyeno!=null)?irsaliyeno:""%>" placeholder="İrsaliye No" autocomplete="off">
  						<input type="hidden" name="irsaliyeid" id="irsaliyeid" value="<%=(irsaliyeid!=null)?irsaliyeid:""%>">
					</div>
				</div>
				
				<div class="form-group">
					<label for="tarih" class="text-baslik">Gönderim Tarihi</label>
					<div>
						<input type="text" class="form-control small" name="tarih" id="tarih" placeholder="Gönderim Tarihi" value="<%=(tarih!=null)?tarih:""%>">
					</div>
				</div>
				
				<div class="form-group">
					<label for="raporgetir" class="text-success bgsaydam">&nbsp;&nbsp;</label>
					<div>
						<button type="button" class="btn btn-danger" name="raporgetir" id="raporgetir">Rapor Getir</button>
						<input type="hidden" name="raporgetirid" id="raporgetirid" value="0">
					</div>
				</div>
				
				<div class="clearfix"></div>

			</form>
		</div>		
		<div class="row" style="font-size:12px;">
			<table class="tableplan" style="width:100%;">
				<% int sayacbilesen 		= 0; %>
				<% int colorChange 			= 0; %>
				<% int irsaliyeId 			= 0; %>
				<% int yeniIrsaliyeId		= 0; %>
				<% boolean same				= true; %>
				<% String style				= ""; %>				
				<% for (IrsaliyeBilesen j : irsaliyebilesenonaylandi) {%>
				<% if ( (sayacbilesen == 0) ){ %>
				<tr>
					<td><label class="text-success">Gönderim Tarihi</label></td>
					<td><label class="text-success">İrsaliye No</label></td>
					<td><label class="text-success">Sıra No</label></td>
					<td><label class="text-success">Mamül Adı</label></td>
					<td><label class="text-success">Mamül Kodu</label></td>
					<td><label class="text-success">Firma</label></td>
					<td><label class="text-success">GKR.No</label></td>
					<td><label class="text-success">Miktarı (Ad.)</label></td>
				</tr>
				<% } %><%-- if tablo baslik --%>
				
				<%
				irsaliyeId = j.getIrsaliyeid();
				if (yeniIrsaliyeId != irsaliyeId){
				    if (colorChange++ % 2 == 1){
					    style="style=background-color:darkKhaki";
					}
					else{
					    style= "";
					}
				    sayacbilesen=0;
				}
				%>
				<tr <%=style %>>
					<td><%= Util.getTarih(j.getGonderimtarihi()) %></td>
					<td><%= j.getIrsaliyeno() %></td>
					<td><%= ++sayacbilesen %></td>
					<td><%= j.getMamulad() %></td>
					<td><%= j.getMamulkod() %></td>
					<td><%= j.getFirmaad() %></td>
					<td><%= j.getGkrno() %></td>
					<td><%= j.getMiktar() %></td>
				</tr>
				<%
					yeniIrsaliyeId = irsaliyeId;
				%>
				<% } %> <%-- irsaliyebilesen --%>				
			</table>
			
			<%--For displaying Previous link except for the 1st page --%>
			<%--For displaying Page numbers.
			The when condition does not display a link for the current page--%>
			<%
			String param1 = (irsaliyeid==null || irsaliyeid.equals(""))?"":"&amp;irsaliyeid="+ irsaliyeid;
			String param2 = (firmaid==null || firmaid.equals(""))?"":"&amp;firmaid="+ firmaid;
			String param3 = (tarih==null || tarih.equals(""))?"":"&amp;tarih="+ tarih;
			String param4 = (mamulid==null || mamulid.equals(""))?"":"&amp;mamulid="+ mamulid;
			%>
			<jsp:include page="paging.jsp">
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="sevkrapor" name="pagename"/>
				<jsp:param value="<%=param1 %>" name="param1"/>
				<jsp:param value="<%=param2 %>" name="param2"/>
				<jsp:param value="<%=param3 %>" name="param3"/>
				<jsp:param value="<%=param4 %>" name="param4"/>
			</jsp:include>
		</div>
		<%
		if (sayacbilesen > 0 ){
			%>
				<div class="row text-right">
					<div>
						<form method="post" name="excelform" id="excelform" action="excelsevk">
							<button type="button" class="btn btn-danger" name="exceleaktar_sevk" id="exceleaktar_sevk">Excele Aktar</button>
							<input type="hidden" value="0" name="excelegonder" id="excelegonder">
							<input type="hidden" name="excelirsaliyeid" id="excelirsaliyeid" value="<%=(irsaliyeid==null)?"":irsaliyeid%>">
							<input type="hidden" name="excelfirmaid" id="excelfirmaid" value="<%=(firmaid==null)?"":firmaid%>">
							<input type="hidden" name="excelmamulid" id="excelmamulid" value="<%=(mamulid==null)?"":mamulid%>">
							<input type="hidden" name="exceltarih" id="exceltarih" value="<%=(tarih==null)?"":tarih%>">
							<input type="hidden" name="excelsql" id="excelsql" value="<%=(excelsql==null)?"":excelsql%>">
						</form>
					</div>
				</div>
			<%
		}
		%>
		
		
	</div>
	
	<script	src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script	src="js/bootstrap.min.js" type="text/javascript"></script>
	<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
	<script src="js/irfan.js?<%= System.currentTimeMillis() %>" type="text/javascript"></script>
	<script	src="js/rapor.js?<%= System.currentTimeMillis() %>" type="text/javascript"></script>
	<script>
	$(function() {
		$('#tarih').datepicker({
			inline: true,
			format : 'dd-mm-yy',
			firstDay:1,
		}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","<%= (tarih==null)?"":tarih%>");
	
	
		$("#mamulkod" ).autocomplete({
			source		: mamul2,
			minLength	: 1,
			select		: function(event, ui){
				changefunMamul(ui.item);
				$(this).val(ui.item.value);
			}
		});
		
		$("#mamulkod").change(function() {
			changefunMamul(this);
		});
		
		$("#irsaliyeno" ).autocomplete({
			source		: irsaliyejson,
			minLength	: 1,
			select		: function(event, ui){
				changefun(ui.item);
			}
		});
		
		$("#irsaliyeno").change(function() {
			changefun(this);
		});
		
		if($("#mamulid").val() != ""){
			var found_bilesenid = mamul.filter(function(obj) {
			    return obj.id == $("#mamulid").val();
			});
	
			/* var found_bilesenid = $.grep(bilesen, function(v) { return v.id == $("#bilesenid").val(); }); */

			if (found_bilesenid != undefined){
				$("#mamulkod").val( found_bilesenid[0].bilesenkod );
				$("#mamulad").val( found_bilesenid[0].bilesenad );
			}
		}
	
	});
	
	function changefun(elem){
		var found = false;
		if (elem != null){
			for (i in irsaliyejson) {
				if(elem.value == irsaliyejson[i].irsaliyeno){
					found = true;
					document.raporform.irsaliyeid.value	= irsaliyejson[i].irsaliyeid;
				}
			}
		}	
		if(!found){
			document.raporform.irsaliyeid.value	= "";
		}
	}
	
	function changefunMamul(elem){
		
		console.log(elem);
		var found = false;
		if (elem != null){
			for (i in mamul) {
				/* autocomplete alanina birden fazla alan ekler */
				if(elem.value == mamul[i].bilesenkod){
					//console.log("mamulid: " + mamul[i].bilesenid);
					//console.log("mamulad: " + mamul[i].bilesenad);
					//console.log("mamulkod: " + mamul[i].bilesenkod);
					found = true;
					$("#mamulid").val(mamul[i].bilesenid);
					$("#mamulad").val(mamul[i].bilesenad);
					
				}
			}
		}
		if(!found){
			$("#mamulid").val("");
			$("#mamulad").val("");
			$("#mamulkod").val("");
		}
	}
	</script>
</body>
</html>