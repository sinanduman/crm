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
</head>
<body>
	<%@ include file="logincheck.jsp" %>
	<%
	    List<Firma> firma		= (List<Firma>) request.getAttribute("firma");
			List<Makina> makina		= (List<Makina>) request.getAttribute("makina");
			List<Calisan> calisan	= (List<Calisan>) request.getAttribute("calisan");
			List<Mamul> mamul		= (List<Mamul>) request.getAttribute("mamul");
			List<UretimPlan> stokrapor=(List<UretimPlan>) request.getAttribute("stokrapor");
			String bilesenid		= (String) request.getAttribute("bilesenid");		
			String makinaid			= (String) request.getAttribute("makinaid");
			String calisanid		= (String) request.getAttribute("calisanid");
			String firmaid			= (String) request.getAttribute("firmaid");
			String bas_tarih		= (String) request.getAttribute("bas_tarih");
			String bit_tarih		= (String) request.getAttribute("bit_tarih");
			String excelsql			= (String) request.getAttribute("excelsql");
	%>
	
	<script>
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
		var mamuldef = mamul;
		
	</script>


	<div class="container">
		<div class="row text-warning" style="text-align: center;">
			<label class="text-danger rounded">Üretim Raporu</label>
		</div>
		<div class="row">
			<form class="form-inline" role="form" name="raporform" id="raporform" method="post" action="uretimrapor">
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
					<label for="makinaid" class="text-baslik">Makina/Band</label>
					<div>
						<select	class="form-control small" id="makinaid" name="makinaid">
							<%
							    out.println("<option value=''>...Tüm...</option>");
															for (Makina m : makina) {
																if(m.getId().toString().equals(makinaid)){
																	out.println("<option value='"+ m.getId() +"' selected>"+ m.getMakinaad() +"</option>");
																}
																else{
																	out.println("<option value='"+ m.getId() +"'>"+ m.getMakinaad() +"</option>");
																}
															}
							%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="calisanid" class="text-baslik">Operatör</label>
					<div>
						<select	class="form-control small" id="calisanid" name="calisanid">
							<%
							    out.println("<option value=''>...Tüm...</option>");
															for (Calisan c : calisan) {
															    if(c.getId().toString().equals(calisanid)){
															        out.println("<option value='"+ c.getId() +"' selected>"+ c.getFullName() +"</option>");
															    }
															    else{
															        out.println("<option value='"+ c.getId() +"'>"+ c.getFullName() +"</option>");
															    }
															}
							%>
						</select>
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
				
				<div class="clearfix"></div>
				
				<div class="form-group">
					<label for="mamulkod" class="text-baslik">Mamül Kodu</label>
					<div>
  						<input type="text" class="form-control small" name="mamulkod" id="mamulkod" autocomplete="off">
  						<input type="hidden" name="bilesenid" id="bilesenid">
					</div>
				</div>
				
				<div class="form-group">
					<label for="mamulad" class="text-baslik">Mamül Adı</label>
					<div>
						<input type="text" class="form-control small" name="mamulad" id="mamulad" readonly="readonly">
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
		<%
		    if( stokrapor.size() > 0){
				    String bilesenad =  "Mamül";
		%>
		    <div class="row text-warning" style="text-align: center;margin-top:10px;">
				<label class="text-danger roundedmini"><%=bilesenad%> Üretim Raporu</label>
			</div>
		    <%
		        }
		    %>
		<div class="row" style="font-size:12px;">
			<table class="tableplan">
				<script type="text/javascript" charset="utf-8">
				var plan = {};
				var plantemp = {};
				</script>
				<%
				    for (UretimPlan u : stokrapor) {
				%>
				<%
				    if (sayac++ == 0) {
				%>
				<tr>
					<th>Tarih</th>
					<th>Başl.</th>
					<th>Bit.</th>
					<th>Makina</th>
					<th>Operatör</th>
					<th>Müşteri</th>
					<th>Hammadde</th>
					<th>GKR.No</th>
					<th>Ürün Kodu</th>
					<th>Ürün Adı</th>
					<th>İzl.No</th>
					<th>Planlanan</th>
					<th>Üretilen</th>
					<th>Fark</th>
					<th>Sapma</th>
					<th>Açıklama</th>
				</tr>
				<%
				    }
				%>
				<tr id="tr<%=u.getId()%>">
					<td nowrap><%=u.getTarihTRShort()%></td>
					<td><%=u.getBasZaman()%></td>
					<td><%=u.getBitZaman()%></td>
					<td nowrap><%=u.getMakinaad()%></td>
					<td><%=u.getCalisanShortName()%></td>
					<td><%=u.getFirmaad()%></td>
					<td><%=Util.splitLine(u.getHammaddead(), ";", "<br>")%></td>
					<td><%=Util.splitLine(u.getHammaddeizlno(), ";", "<br>")%></td>
					<td><%= u.getMamulkod() %></td>
					<td><%= u.getMamulad() %></td>
					<td><%= (u.getMamulizlno()==0) ? "" : u.getMamulizlno() %></td>
					<td class="text-right"><%= (u.getPlanlananmiktar()==0) ? "" : u.getPlanlananmiktar() + " Ad." %></td>
					<td class="text-right"><%= u.getUretilenmiktar() + " Ad." %></td>
					<td class="text-right" nowrap><%= (u.getFark()==0) ? "" : u.getFark() + " Ad." %></td>
					<td class="text-right"><%= (u.getSapma()==0) ? "" : "% " + u.getSapma() %></td>					
					<td><%= u.getHataad() %></td>
				</tr>
				<% } %>
			</table>
			
			<%--For displaying Previous link except for the 1st page --%>
			<%--For displaying Page numbers.
			The when condition does not display a link for the current page--%>
			<%
			String param1 = (bilesenid==null || bilesenid.equals(""))?"":"&amp;bilesenid="+ bilesenid;
			String param2 = (makinaid==null || makinaid.equals(""))?"":"&amp;makinaid="+ makinaid;
			String param3 = (calisanid==null || calisanid.equals(""))?"":"&amp;calisanid="+ calisanid;
			String param4 = (firmaid==null || firmaid.equals(""))?"":"&amp;firmaid="+ firmaid;
			String param5 = (bas_tarih==null || bas_tarih.equals(""))?"":"&amp;bas_tarih="+ bas_tarih;
			String param6 = (bit_tarih==null || bit_tarih.equals(""))?"":"&amp;bit_tarih="+ bit_tarih;
			%>
			<jsp:include page="paging.jsp">
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="stokrapor" name="pagename"/>
				<jsp:param value="<%=param1 %>" name="param1"/>
				<jsp:param value="<%=param2 %>" name="param2"/>
				<jsp:param value="<%=param3 %>" name="param3"/>
				<jsp:param value="<%=param4 %>" name="param4"/>
				<jsp:param value="<%=param5 %>" name="param5"/>
				<jsp:param value="<%=param6 %>" name="param6"/>
			</jsp:include>
		</div>
		<%
		if (sayac >0 ){
			%>
				<div class="row text-right">
					<div>
						<form method="post" name="excelform" id="excelform" action="excel">
							<button type="button" class="btn btn-danger" name="exceleaktar_uretimrapor" id="exceleaktar_uretimrapor">Excele Aktar</button>
							<input type="hidden" value="0" name="excelegonder" id="excelegonder">
							<input type="hidden" name="exceltarih" id="exceltarih">
							<input type="hidden" name="excelsql" id="excelsql" value="<%=excelsql%>">
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
	<script src="js/irfan.js?" type="text/javascript"></script>
	<script	src="js/rapor.js?" type="text/javascript"></script>
	<script>
	$(function() {
		$('#bas_tarih').datepicker({
			inline: true,
			format : 'dd-mm-yy',
			firstDay:1,
		}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","<%= (bas_tarih=="")?"today":bas_tarih%>");
	});
	$(function() {
		$('#bit_tarih').datepicker({
			inline: true,
			format : 'dd-mm-yy',
			firstDay:1,
		}).datepicker("option", "dateFormat", "dd-mm-yy").datepicker("setDate","<%= (bit_tarih=="")?"today":bit_tarih%>");
	});
	$("#mamulkod" ).autocomplete({
		source		: mamul,
		minLength	: 1,
		select		: function(event, ui){
			changefun(ui.item);
		}
	});
	$("#mamulkod").change(function() {
		changefun(this);
	});
	function changefun(elem){
		var found = false;
		if (elem != null){
			for (i in mamul) {
				if(elem.value == mamul[i].bilesenkod){
					found = true;
					document.raporform.mamulad.value	= mamul[i].bilesenad;
					document.raporform.bilesenid.value	= mamul[i].bilesenid;
				}
			}
		}	
		if(!found){
			document.raporform.mamulad.value	= "";
			document.raporform.bilesenid.value	= "";
		}
	}
	</script>
</body>
</html>