<%@page import="crm.irfan.UtilFunctions"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="crm.irfan.User, crm.irfan.entity.*, java.util.List"%>
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
	<title><%= Genel.TITLE %></title>
	
	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="css/irfan.css?">
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
		List<HataSebep> hatasebep = (List<HataSebep>) request.getAttribute("hatasebep");
		List<DurusSebep> durussebep = (List<DurusSebep>) request.getAttribute("durussebep");
		List<Calisan> calisan = (List<Calisan>) request.getAttribute("calisan");
		List<Makina> makina = (List<Makina>) request.getAttribute("makina");
		List<Mamul> mamul = (List<Mamul>) request.getAttribute("mamul");
		List<UretimPlan> uretimplan = (List<UretimPlan>) request.getAttribute("uretimplan");
	%>
	
	<script>
		var mamul = [
		<%		
		String delimeter = "";
		for (Mamul m : mamul) {
			out.println(delimeter 
		+ " { id:" + m.getId()
		+ ",mamulid:" + m.getId() 
		+ ",mamulad:'" + m.getAd().replaceAll("'", "") + "'"
		+ ",mamulkod:'" + m.getKod() + "'"
		+ ",hammadde:'" + m.getHammadde().replaceAll("'", "") + "'"
		+ ",hammaddegkrno:'" + m.getHammaddegkrno() + "'"
		+ ",hammaddeagir:'" + m.getHammaddemiktar() + "'"
		+ ",hammaddemamulbasi:'" + m.getHammaddemamulbasi() + "'"
		+ ",yarimamul:'" + m.getYarimamul().replaceAll("'", "") + "'"
		+ ",yarimamulgkrno:'" + m.getYarimamulgkrno() + "'"
		+ ",yarimamulagir:'" + m.getYarimamulmiktar() + "'"
		+ ",yarimamulmamulbasi:'" + m.getYarimamulmamulbasi() + "'"
		+ ",firmaad:'" + m.getFirmaad().replaceAll("'", "") + "'"
		+ ",cevrimsure:" + m.getCevrimsuresi() + "}");
			delimeter = ",";
		}%>];
		var mamul2 = [
		<%
		delimeter = "";
		for (Mamul m : mamul) {
			out.println(delimeter + " { label:'" + m.getKod() + "'" + ",id:" + m.getId() + "}");
			delimeter = ",";
		}%>];

	</script>


	<div class="container">
		<div class="row text-warning" style="text-align: center;">
			<label class="text-danger rounded">Üretim Takibi</label>
		</div>
		<div class="row">
			<form class="form-inline" role="form" name="uretimtakipform" id="uretimtakipform" method="post" action="uretimtakip">
				<div class="form-group">
					<label for="tarih" class="text-baslik">Tarih</label>
					<div>
						<input type="text" class="form-control xm" name="tarih" id="tarih" placeholder="Tarih">
					</div>
				</div>

				<div class="form-group">
					<label for="bas_zaman" class="text-baslik">Başlangıç</label>
					<div>
						<div>
							<select class="form-control time" name="bassaat" id="bassaat">
							<%
								for(int i=0;i<24; i++){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									out.print("<option value='"+ temp +"'>");
									out.print((temp));
									out.println("</option>");
								}
							%>
							</select><strong>:</strong><select class="form-control time" name="basdakika" id="basdakika">
							<%
								for(int i=0;i<60; i=i+5){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									out.print("<option value='"+ temp +"'>");
									out.print((temp));
									out.println("</option>");
								}
							%>
							</select>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="bit_zaman" class="text-baslik">Bitiş</label>
					<div>
						<div>
							<select class="form-control time" name="bitsaat" id="bitsaat">
							<%
								for(int i=0;i<24; i++){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									out.print("<option value='"+ temp +"'>");
									out.print((temp));
									out.println("</option>");
								}
							%>
							</select><strong>:</strong><select class="form-control time" name="bitdakika" id="bitdakika">
							<%
								for(int i=0;i<60; i=i+5){
									String temp = "0"+i;
									temp = temp.substring(temp.length()-2);
									out.print("<option value='"+ temp +"'>");
									out.print((temp));
									out.println("</option>");
								}
							%>
							</select>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="makinaid" class="text-baslik">Makina/Band</label>
					<div>
						<select	class="form-control" id="makinaid" name="makinaid">
							<%
								for (Makina m : makina) {
								    out.println("<option value='"+ m.getId() +"'>"+ m.getMakinaad() +"</option>");
								}
							%>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="calisanid" class="text-baslik">Operatör</label>
					<div>
						<select	class="form-control" id="calisanid" name="calisanid">
							<%
								for (Calisan c : calisan) {
								    out.println("<option value='"+ c.getId() +"'>"+ c.getFullName() +"</option>");
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
  						<input type="hidden" name="mamulid" id="mamulid">
  						<input type="hidden" name="yarimamulgkrno" id="yarimamulgkrno">
  						<input type="hidden" name="hammaddegkrno" id="hammaddegkrno">
					</div>
				</div>
				
				<div class="form-group">
					<label for="mamulad" class="text-baslik">Mamül Adı</label>
					<div>
						<input type="text" class="form-control small" name="mamulad" id="mamulad" readonly="readonly">
					</div>
				</div>

				<div class="form-group">
					<label for="hammadde" class="text-baslik">Hammadde / Kalan Miktar / Mamül Başı</label> 
					<div>
						<select class="form-control big" name="hammadde" id="hammadde" readonly="readonly"></select>
					</div>
				</div>

				<div class="form-group">
					<label for="yarimamul" class="text-baslik">Yarımamül / Kalan Miktar / Mamül Başı</label>
					<div>
						<select class="form-control big" name="yarimamul" id="yarimamul" readonly="readonly"></select>
					</div>
				</div>
				
				<div class="clearfix"></div>
				
				<div class="form-group">
					<label for="firmaad" class="text-baslik">Firma</label>
					<div>
						<input type="text" class="form-control small" name="firmaad" id="firmaad" readonly="readonly">
					</div>
				</div>
				

				<div class="form-group">
					<label for="uretimadet" class="text-baslik">Üretim Ad.</label>
					<div>
						<input type="text" class="form-control xs text-right" name="uretimadet" id="uretimadet" autocomplete="off">
					</div>
				</div>

				<div class="form-group">
					<label for="hataliadet" class="text-baslik">Hatalı Ad.</label>
					<div>
						<input type="text" class="form-control xs text-right" name="hataliadet" id="hataliadet" autocomplete="off">
					</div>
				</div>

				<div class="form-group">
					<label for="hatasebepid" class="text-baslik">Hata Kodu</label>
					<div>
						<select name="hatasebepid" id="hatasebepid" class="form-control small" >
						<% 
								out.println("<option value='0' selected>...Seçiniz...</option>");
							for (HataSebep hs : hatasebep) {
								out.println("<option value='" + hs.getId() + "'>"+ hs.getAd() + " ("+ hs.getKod() +")</option>");
							}
						%>
						</select>						
					</div>
				</div>

				<div class="form-group">
					<label for="duruszaman" class="text-baslik">Duruş Zamanı</label>
					<div>
						<input type="text" class="form-control xs text-right" name="duruszaman" id="duruszaman"><span class="birim">Dk.</span>
					</div>
				</div>


				<div class="form-group">
					<label for="durussebepid" class="text-baslik">Duruş Kodu</label>
					<div>
						<select name="durussebepid" id="durussebepid" class="form-control small" >
						<% 
								out.println("<option value='0' selected>...Seçiniz...</option>");
							for (DurusSebep ds : durussebep) {
								out.println("<option value='" + ds.getId() + "'>"+ ds.getAd() + " ("+ ds.getKod() +")</option>");
							}
						%>
						</select>						
					</div>
				</div>

				<div class="clearfix"></div>
				
				<div class="form-group" style="margin:10px 0;">
					<div>
						<input type="hidden" name="uretimplanid" id="uretimplanid" value="0">
						<input type="hidden" name="islemid" id="islemid" value="0">
						<button type="button" class="btn btn-danger" name="uretimekle" id="uretimekle">Ekle</button>
						<button type="button" class="btn btn-danger" name="uretimiptal" id="uretimiptal" style="display:none;">İptal</button>
					</div>
				</div>

				<div class="clearfix">
				</div>
			</form>
		</div>
		<%
			List<SiparisPlan> siparisplan = (List<SiparisPlan>) request.getAttribute("siparisplan");
			int sayac = 0;
		%>
		
		<div class="row size12px" style="font-size:12px;">
			<table class="tableplan">
				<script type="text/javascript" charset="utf-8">
				var plan = {};
				var plantemp = {};
				</script>
				<% for (UretimPlan u : uretimplan) { %>
				<script type="text/javascript" charset="utf-8">
					plantemp = {
						id:"<%= u.getId() %>", 
						tarihtr:"<%= u.getTarihTR() %>",
						tarihtrshort:"<%= u.getTarihTRShort() %>",
						baslangic:"<%= u.getBasZaman() %>",
						bitis:"<%= u.getBitZaman() %>",
						makinaad:"<%= u.getMakinaad() %>",
						makinaid:"<%= u.getMakinaid() %>",
						calisanad:"<%= u.getCalisanShortName() %>", 
						calisanid:"<%= u.getCalisanid() %>",
						firmaad:"<%= u.getFirmaad() %>",
						firmaid:"<%= u.getFirmaid() %>",
						hammadde:"<%= u.getHammaddead() %>",
						gkrno:"<%= u.getHammaddeizlno() %>",
						mamulad:"<%= u.getMamulad() %>",
						mamulid:"<%= u.getMamulid() %>",
						mamulkod:"<%= u.getMamulkod() %>",
						uretimadet:"<%= u.getUretilenmiktar() %>",
						hataliadet:"<%= (u.getHatalimiktar()==0) ? "" : u.getHatalimiktar() %>",
						hataid:"<%= u.getHataid() %>",
						hatakod:"<%= u.getHatakodu() %>",
						duruszaman:"<%= (u.getDuruszaman()==0)? "" : u.getDuruszaman() %>",
						durusid:"<%= u.getDurusid() %>",
						duruskod:"<%= u.getDuruskodu() %>",
						izlemeno:"<%= u.getMamulizlno() %>"
					};
					plan[<%= u.getId() %>] = plantemp;
				</script>
				<% if (sayac++ == 0) { %>
				<tr>
					<th>Sıra</th>
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
					<th>Ür.Adet</th>
					<th>Hatalı</th>
					<th>Hata Kd.</th>
					<th>Duruş Zm.</th>
					<th>Duruş Kd.</th>
					<th>Aksiyon</th>
				</tr>
				<% } %>
				<tr id="tr<%= u.getId() %>">
					<td class="text-right"><%= u.getId() %></td>
					<td nowrap><%= u.getTarihTRShort() %></td>
					<td><%= u.getBasZaman() %></td>
					<td><%= u.getBitZaman() %></td>
					<td nowrap><%= u.getMakinaad() %></td>
					<td><%= u.getCalisanShortName() %></td>
					<td><%= u.getFirmaad() %></td>
					<td><%= UtilFunctions.splitLine(u.getHammaddead(), ";", "<br>")  %></td>
					<td><%= UtilFunctions.splitLine(u.getHammaddeizlno(), ";","<br>") %></td>
					<td><%= u.getMamulkod() %></td>
					<td><%= u.getMamulad() %></td>
					<td class="text-right"><%= u.getUretilenmiktar() %></td>
					<td class="text-right"><%= (u.getHatalimiktar()==0) ? "" : u.getHatalimiktar() %></td>
					<td><%= u.getHatakodu() %></td>
					<td><%= (u.getDuruszaman()==0) ? "" : u.getDuruszaman() + " Dk."%></td>
					<td><%= u.getDuruskodu() %></td>					
					<td>
					<div id="div<%= u.getId() %>">
						<a href="javascript:uretimtakipguncelle('#tr<%= u.getId() %>',<%= u.getId() %>);" title="Güncelle"><span class="fa fa-refresh fa-lg text-warning"></span></a>
						<a href="javascript:uretimtakipok(<%= u.getId() %>);" title="Onayla"><span class="fa fa-check-circle fa-lg text-success"></span></a>
						<a href="javascript:uretimtakipsil(<%= u.getId() %>);" title="Sil"><span class="fa fa-minus-circle fa-lg text-danger"></span></a>
					</div>
					</td>
				</tr>
				<% } %>
			</table>
			
			<%--For displaying Previous link except for the 1st page --%>
			<%--For displaying Page numbers.
			The when condition does not display a link for the current page--%>
			<c:if test="${noofpages > 1}">
			<table id="pagination">
				<tr>
					<c:if test="${currentpage != 1}">
						<td class="link_diger"><a href="uretimtakip?page=${currentpage - 1}">Önceki</a></td>
					</c:if>
					<c:forEach begin="1" end="${noofpages}" var="i">
						<c:choose>
							<c:when test="${currentpage eq i}">
								<td class="link_aktif">${i}</td>
							</c:when>
							<c:otherwise>
								<td class="link_diger"><a href="uretimtakip?page=${i}">${i}</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%--For displaying Next link --%>
					<c:if test="${currentpage lt noofpages}">
						<td class="link_diger"><a href="uretimtakip?page=${currentpage + 1}">Sonraki</a></td>
					</c:if>
				</tr>
			</table>
			</c:if>
		</div>
		
		<div class="row" style="font-size:12px;margin-top:10px;">
			<label class="text-danger">Not:</label>
			<br>
			<span class="text-danger">&rarr;</span> <span class="text-success">Aksiyon bölümünde "Onayla" simgesine tıklayınca, listeye yansıyan "Ür.Adet" miktarı, ürün stoğuna eklenir.</span>
			<br>
			<span class="text-danger">&rarr;</span> <span class="text-success">Aksiyon bölümünde "Onayla" simgesine tıklayınca, listeye yansıyan "Ür.Adet" miktarına göre, hammadde ve yarımamül stoğundan düşülür.</span>
			<br>
		</div>
		<div id="okdiv" class="bb-alert alert alert-success" style="display:none;">
	        <span>Confirm result: false</span>
	    </div>
	    <div id="errordiv" class="bb-alert alert alert-danger" style="display:none;">
	        <span>Confirm result: false</span>
	    </div>
	</div>
	
	<script	src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script	src="js/bootstrap.min.js" type="text/javascript"></script>
	<script	src="js/jquery-ui.min.js" type="text/javascript"></script>
	<script	src="js/bootbox.js" type="text/javascript"></script>
	<script src="js/irfan.js?" type="text/javascript"></script>
	<script	src="js/siparis.js?" type="text/javascript"></script>
	<script>
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
			//$("#go").removeAttr("disabled");
			//console.log(ui.item.id);
			//console.log(ui.item.value);
			//console.log(event);
			changefun(ui.item);
		}
	});
	$("#mamulkod").change(function() {
		changefun(this);
	});
	function changefun(elem){
		var found = false;
		var option= "";	
		if (elem != null){
			$("#hammadde option").remove();
			$("#yarimamul option").remove();
			for (i in mamul) {
				if(elem.value == mamul[i].mamulkod){
					found = true;
					var ham_name_ar			= mamul[i].hammadde.split(";");
					var ham_gkrno_ar		= mamul[i].hammaddegkrno.split(";");
					var ham_agir_ar			= mamul[i].hammaddeagir.split(";");
					var ham_mamulbasi_ar	= mamul[i].hammaddemamulbasi.split(";");
					
					var yari_name_ar		= mamul[i].yarimamul.split(";");
					var yari_gkrno_ar		= mamul[i].yarimamulgkrno.split(";");
					var yari_agir_ar		= mamul[i].yarimamulagir.split(";");
					var yari_mamulbasi_ar	= mamul[i].yarimamulmamulbasi.split(";");
					
					document.uretimtakipform.mamulad.value	= mamul[i].mamulad;
					document.uretimtakipform.mamulid.value	= mamul[i].mamulid;
					document.uretimtakipform.firmaad.value	= mamul[i].firmaad;
					document.uretimtakipform.hammadde.value	= mamul[i].hammadde;
					
					option = "";
					var ham_name		= "";
					var ham_miktar		= 0;
					var ham_mamulbasi	= 0;
					for (i in ham_name_ar) {						
						if(ham_name == ""){
							ham_name	= ham_name_ar[i];
							ham_mamulbasi=parseInt(ham_mamulbasi_ar[i],10);
						}
						if(ham_name == ham_name_ar[i]){
							ham_name	= ham_name_ar[i];
							ham_miktar	= ham_miktar + parseInt(ham_agir_ar[i], 10);
							ham_mamulbasi=parseInt(ham_mamulbasi_ar[i],10);
						}
						else {
							option += "<option>" + ham_name +" / "+ ham_miktar / 1000.0 + " Kg / "+ ham_mamulbasi +" Gr</option>";
							ham_name	= ham_name_ar[i];
							ham_miktar	= parseInt(ham_agir_ar[i], 10);
							ham_mamulbasi=parseInt(ham_mamulbasi_ar[i],10);
						}						
						//option += '<option>' + ham_name_ar[i] +"/"+ ham_gkrno_ar[i] +"/"+ ham_agir_ar[i] / 1000.0 + ' Kg.</option>';
					}
					option += "<option>" + ham_name +" / "+ ham_miktar / 1000.0 + " Kg / "+ ham_mamulbasi +" Gr</option>";
					$("#hammadde").append(option);
					
					option = "";
					var yari_name		= "";
					var yari_miktar		= 0;
					var yari_mamulbasi	= 0;
					for (i in yari_name_ar) {
						if(yari_name == ""){
							yari_name	= yari_name_ar[i];
							yari_mamulbasi=parseInt(yari_mamulbasi_ar[i],10);
						}
						if(yari_name == yari_name_ar[i]){
							yari_name	= yari_name_ar[i];
							yari_miktar	= yari_miktar + parseInt(yari_agir_ar[i], 10);
							yari_mamulbasi=parseInt(yari_mamulbasi_ar[i],10);
						}
						else {
							option += "<option>" + yari_name +" / "+ yari_miktar + " Ad / " + yari_mamulbasi+" Ad</option>";
							yari_name	= yari_name_ar[i];
							yari_miktar	= parseInt(yari_agir_ar[i], 10);
							yari_mamulbasi=parseInt(yari_mamulbasi_ar[i],10);
						}						
						//option += '<option>' + yari_name_ar[i] +"/"+ yari_gkrno_ar[i] +"/"+ yari_agir_ar[i] + ' Ad.</option>';
					}
					option += "<option>" + yari_name +" / "+ yari_miktar + " Ad / " + yari_mamulbasi+" Ad</option>";
					$("#yarimamul").append(option);
				}
			}
		}	
		if(!found){
			document.uretimtakipform.mamulad.value	= "";
			document.uretimtakipform.mamulid.value	= "";
			document.uretimtakipform.firmaad.value	= "";
			document.uretimtakipform.hammadde.value	= "";
			$("#hammadde option").remove();
			$("#yarimamul option").remove();
		}
	}
	</script>
</body>

</html>