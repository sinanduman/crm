<%@ page import="crm.irfan.Util"%>
<%@ page import="crm.irfan.entity.*"%>
<%@ page import="java.util.Date,java.util.List"%>
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
		List<Mamul> mamul		= (List<Mamul>) request.getAttribute("mamul");
		List<Bilesen> bilesen	= (List<Bilesen>) request.getAttribute("bilesen");
		List<StokRapor> stokrapor=(List<StokRapor>) request.getAttribute("stokrapor");
		List<StokRapor> stokdetay=(List<StokRapor>) request.getAttribute("stokdetay");
		String bilesentip		= (String) request.getAttribute("bilesentip");
		String bilesenid		= (String) request.getAttribute("bilesenid");
		String excelsql			= (String) request.getAttribute("excelsql");
		String tablename		= (String) request.getAttribute("tablename");
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
		+ ",value:'" + m.getKod() + "'"
		+ ",label:'" + m.getKod() + " ["+ m.getAd().replaceAll("'", "") + "]'" + "}");
			delimeter = ",";
		}%>];
		var mamuldef = mamul;
		
		var hammadde = [
   		<%delimeter = "";
   		for (Bilesen b : bilesen) {
   		 	if(b.getBilesentipid() == BilesenTip.YARIMAMUL.value() ){
		        continue;
		    }
   			out.println(delimeter 
   		+ " { id:" + b.getId()
   		+ ",bilesenid:" + b.getId() 
   		+ ",bilesenad:'" + b.getAd().replaceAll("'", "") + "'"
   		+ ",bilesenkod:'" + b.getKod() + "'"
		+ ",value:'" + b.getKod() + "'"
   		+ ",label:'" + b.getKod() + " ["+ b.getAd().replaceAll("'", "") + "]'" + "}");
   			delimeter = ",";
   		}%>];
		
		var yarimamul = [
   		<%delimeter = "";
   		for (Bilesen b : bilesen) {
   		    if(b.getBilesentipid() == BilesenTip.HAMMADDE.value() ){
   		        continue;
   		    }
   			out.println(delimeter 
   		+ " { id:" + b.getId()
   		+ ",bilesenid:" + b.getId() 
   		+ ",bilesenad:'" + b.getAd().replaceAll("'", "") + "'"
   		+ ",bilesenkod:'" + b.getKod() + "'"
		+ ",value:'" + b.getKod() + "'"
   		+ ",label:'" + b.getKod() + " ["+ b.getAd().replaceAll("'", "") + "]'" + "}");
   			delimeter = ",";
   		}%>];

	</script>


	<div class="container">
		<div class="row text-warning text-center">
			<label class="text-danger rounded">Stok Raporu</label>
		</div>
		<div class="row">
			<form class="form-inline" name="raporform" id="raporform" method="post" action="stokrapor">
				<div class="form-group">
					<label for="bilesentipid" class="text-baslik">Ürün Tipi</label>
					<div>
						<select	class="form-control small" id="bilesentipid" name="bilesentipid">
							<%
							    for (BilesenTip bt : BilesenTip.values()) {
									if(bt.value().toString().equals(bilesentip)){
										out.print("<option value='" + bt.value() + "' selected>" + bt.ad() + "</option>");
									}
									else{
										if(bt.value()!=4){
											out.print("<option value='" + bt.value() + "'>" + bt.ad() + "</option>");
										}
									}
								}
							%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="mamulkod" class="text-baslik">Ürün Kodu</label>
					<div>
  						<input type="text" class="form-control normal" name="mamulkod" id="mamulkod" autocomplete="off">
  						<input type="hidden" name="bilesenid" id="bilesenid">
					</div>
				</div>
				
				<div class="form-group">
					<label for="mamulad" class="text-baslik">Ürün Adı</label>
					<div>
						<input type="text" class="form-control normal" name="mamulad" id="mamulad" readonly="readonly">
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
		    if(stokrapor.size()>0){
			    String bilesenad = "";
			    if(bilesentip.equals("1")){
			        bilesenad= "Hammadde";
			    }
			    else if(bilesentip.equals("2")){
			        bilesenad= "Yarımamül";
			    }
			    else{
			        bilesenad= "Mamül";
			    }
		%>
		    <div class="row text-warning" style="text-align: center;margin-top:10px;">
				<label class="text-danger roundedmini"><%=bilesenad%> Stok Raporu</label>
			</div>
	    <%
	        }
	    %>
		<div class="row" style="font-size:12px;">
			<table class="tableplan" width="50%">
				<script type="text/javascript" charset="utf-8">
				var plan = {};
				var plantemp = {};
				</script>
				<%
				    if (stokdetay!=null && stokdetay.size()>0) {
					    stokrapor = stokdetay;
					}
				    for (StokRapor sr : stokrapor) {
				    if (sayac++ == 0) {
				        int colsize	= 20;
				        String birim= (sr.getBilesentipid()==BilesenTip.HAMMADDE.value())?"(Kg)":"(Adet)";
				%>
				<tr>
					<th width="20%">Bileşen Kod</th>
					<th width="20%">Bileşen Ad</th>
					<th width="20%">Firma</th>
					<th width="20%">Tarih</th>
					<%
					if(stokdetay.size()>0){
					%>
						<th width="<%= colsize-10 %>%">Değişim</th>
					<%
					}
					%>
					<th width="<%= colsize %>%" nowrap>Kalan Miktar <%=birim%></th>
				</tr>
				<%
				    }
				%>
				<tr id="tr<%=sr.getId()%>">
					<%
					    String miktar	= sr.getMiktar().toString();
						if(sr.getBilesentipid()==BilesenTip.HAMMADDE.value()){
						    Double m= Util.Round((Double.valueOf(sr.getMiktar()) / 1000.0),2.0);
						    miktar	= m.toString();
						}
					%>
					<td><%=sr.getBilesenkod()%></td>
					<td nowrap><%=sr.getBilesenad()%></td>
					<td><%=sr.getFirmaad()%></td>
					<td><%=sr.getTarih()%></td>					
					<td class="text-right-stok">
						
						<%
						if(sr.getIslemyonu() == -1){
						    if(miktar.equals("0")){
						        out.print( "<span class='text-danger'><strong>"+ miktar +"</strong></span>" );
						    }
						    else{
						    	out.print( miktar );
						    }
						}
						else{
						    if(sr.getIslemyonu()==0){
							    out.print("<span class='text-info'><strong>+ "+ miktar +"</strong></span>");
							}
							else{
							    out.print("<span class='text-danger'><strong>- "+ miktar +"</strong></span>");
							}
						}
						%>
					</td>
					<%
					if(stokdetay.size()>0){
						String kalan	= sr.getKalan().toString();
						if(sr.getBilesentipid()==BilesenTip.HAMMADDE.value()){
						    Double k= Util.Round((Double.valueOf(sr.getKalan()) / 1000.0),2.0);
						    kalan	= k.toString();
						}
					%>
					<td class="text-right-stok"><%=kalan%></td>
					<%
					}
					%>
				</tr>
				<%
				    }
				%>
			</table>
			
			<%--For displaying Previous link except for the 1st page --%>
			<%--For displaying Page numbers.
			The when condition does not display a link for the current page--%>
			<%
			    String param1 = "&amp;bilesentipid="+ bilesentip;
				String param2 = (bilesenid==null || bilesenid.equals(""))?"":"&amp;bilesenid="+ bilesenid;
			%>
			<jsp:include page="paging.jsp">
				<jsp:param value="noofpages" name="noofpages"/>
				<jsp:param value="currentpage" name="currentpage"/>
				<jsp:param value="stokrapor" name="pagename"/>
				<jsp:param value="<%=param1%>" name="param1"/>
				<jsp:param value="<%=param2%>" name="param2"/>
			</jsp:include>
		</div>
		<%
		    if (sayac >0 ){
		%>
				<div class="row text-right">
					<div>
						<form method="post" name="excelform" id="excelform" action="excelstok">
							<button type="button" class="btn btn-danger" name="exceleaktar_stok" id="exceleaktar_stok">Excele Aktar</button>
							<input type="hidden" value="0" name="excelegonder" id="excelegonder">
							<input type="hidden" name="exceltarih" id="exceltarih" value="<%=Util.getTarihTR(new Date())%>">
							<input type="hidden" name="excelsql" id="excelsql" value="<%=excelsql%>">
							<input type="hidden" name="exceldetay" id="exceldetay" value="<%= (stokdetay!=null && stokdetay.size()>0)?1:0 %>">
							<input type="hidden" name="exceldetayid" id="exceldetayid" value="<%=bilesenid%>">
							<input type="hidden" name="excelstoktip" id="excelstoktip" value="<%=bilesentip%>">
							<input type="hidden" name="tablename" id="tablename" value="<%=tablename%>">							
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
	<script src="js/irfan.js" type="text/javascript"></script>
	<script	src="js/rapor.js" type="text/javascript"></script>
	<script>
	$("#bilesentipid").change(function(){
		changebilesenid();
	});
	function changebilesenid(){
		$("#mamulad").val("");
		$("#mamulkod").val("");
		if($("#bilesentipid").val()==1){
			mamul = hammadde;
			 $("#mamulkod" ).autocomplete('option', 'source', hammadde);
		}
		else if ($("#bilesentipid").val()==2){
			mamul = yarimamul;
			$("#mamulkod" ).autocomplete('option', 'source', yarimamul);
		}
		else{
			mamul = mamuldef;
			$("#mamulkod" ).autocomplete('option', 'source', mamuldef);
		}
	}	
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
				//var b = elem.value.split("[");
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
	changebilesenid(); // once, when page is loading
	</script>
</body>
</html>