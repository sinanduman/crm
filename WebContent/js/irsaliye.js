$("#irsaliyeekle").click(function(){
	var message="";
	if($("#firmaid").val()==0){
		message += "Müşteri adı giriniz!\n";
	}
	if($("#mamulid").val()==""){
		message += "Mamül kodu giriniz!\n";
	}
	if(!is_positive($("#miktar").val())){
		message += "Sevk miktarı giriniz!\n";
	}
	if(is_empty($("#irsaliyeno").val())){
		message += "İrsaliye No giriniz!\n";
	}
	if(is_positive($("#miktar").val()) && parseInt($("#miktarbox").val()) < parseInt($("#miktar").val()) ){
		message += "Sevk miktarı, Üretim adedinden büyük olamaz!\n";
	}
	if(message!=""){
		alert(message);
	}
	else{
		if($("#islemid").val()=="0"){
			if(confirm("Eklemek istediğine emin misin?")){
				document.irsaliyeform.submit();
			}		
		}
		else if($("#islemid").val()=="1"){
			if(confirm("Güncellemek istediğine emin misin?")){
				document.irsaliyeform.submit();
			}		
		}
		else{
			document.irsaliyeform.submit();
		}		
	}
	
});
$("#irsaliyetamamla").click(function(){
	if(confirm("İrsaliyenin tamamlandığından emin misin?")){
		$("#islemid").val("2");
		document.irsaliyeform.submit();
	}
});
$("#irsaliyeiptal").click(function(){	
	$("#irsaliyeiptal").css("display","none");
	$("#irsaliyeekle").text("İrsaliyeye Ekle");
	$("#irsaliyeekle").removeClass("btn btn-turkuaz").addClass("btn btn-danger");		
	$("#islemid").val("0");
	$("#irsaliyebilesenid").val("0");
	
	$('#tarih').datepicker("setDate","today");
	$("#mamulkod").val("");	
	changefun(document.irsaliyeform.mamulkod);
	
	$("#miktar").val("");
	
	cleartrbackground();
});

$("#gkrno").change(function(){
	// $("#stokid").val($(this).val());
	// var tmp = $(this).val();
	// console.log("tmp: "+ tmp);
	// $("#miktarbox").val(tmp.split(",")[1].split(":")[1]);
	
	var found = false;
	for (i in mamul) {
		if($("#mamulkod").val() == mamul[i].mamulkod && (!found)){
			found = true;
			var gkrno  = mamul[i].gkrno.split(";");
			var miktar = mamul[i].miktar.split(";");
			var stokid = mamul[i].stokid.split(";");
			for(var j = 0; j < gkrno.length; j++) {
				if(gkrno[j]==$(this).val()){
					$("#miktarbox").val(miktar[j]);
					$("#stokid").val(stokid[j]);
				}
			}
		}
	}
	
});

function irsaliyepaketguncelle(sender,bilesenid){
	cleartrbackground();
	$(sender).css("background-color","darkkhaki");

	$("#irsaliyeekle").removeClass("btn btn-danger").addClass("btn btn-turkuaz");
	$("#irsaliyeekle").text("İrsaliyeyi Güncelle");
	$("#irsaliyeiptal").css("display","");
	
	document.irsaliyeform.tarih.value				= paket[bilesenid].tarihtr;
	document.irsaliyeform.bilesenfirmaid.value		= paket[bilesenid].firmaid;
	document.irsaliyeform.bilesenfirmaad.value		= paket[bilesenid].firmaad;
	document.irsaliyeform.mamulkod.value			= paket[bilesenid].mamulkod;
	document.irsaliyeform.mamulad.value				= paket[bilesenid].mamulad;
	document.irsaliyeform.mamulid.value				= paket[bilesenid].mamulid;
	document.irsaliyeform.stokid.value				= paket[bilesenid].stokid;
	document.irsaliyeform.miktar.value				= paket[bilesenid].miktar;
	document.irsaliyeform.not.value					= paket[bilesenid].not;
	document.irsaliyeform.irsaliyeid.value			= paket[bilesenid].irsaliyeid;
	document.irsaliyeform.irsaliyebilesenid.value	= paket[bilesenid].id;
	
	//console.log("gkrno: " + paket[bilesenid].gkrno);
	//console.log(paket[bilesenid].mamulkod);
	
	var found = false;
	$("#gkrno option").remove();
	for (i in mamul) {
		if(paket[bilesenid].mamulkod == mamul[i].mamulkod){
			found = true;
			//console.log(mamul[i].mamulkod);			
			var gkrno  = mamul[i].gkrno.split(";");
			var miktar = mamul[i].miktar.split(";");
			
			var option = "";
			var gkrno_found = false;
			var bilesen_miktar = 0;
			for(var j = 0; j < gkrno.length; j++) {
				if(gkrno[j] == paket[bilesenid].gkrno){
					gkrno_found = true;
					//console.log(gkrno_found);
					miktar[j] = parseInt(miktar[j]) + parseInt(paket[bilesenid].miktar);
					bilesen_miktar = miktar[j];  
				}
				option += '<option value="'+ gkrno[j] + '">' + gkrno[j] +", Adet:" + miktar[j] +'</option>';
			}
			if(!gkrno_found){
				option += '<option value="'+ paket[bilesenid].gkrno + '">' + paket[bilesenid].gkrno +", Adet:" + paket[bilesenid].miktar +'</option>';
				bilesen_miktar = paket[bilesenid].miktar;
			}
			$("#gkrno").append(option);
			
			$("#miktarbox").val(bilesen_miktar);
			
			$("#gkrno option").each(function(index) {
				if($(this).val() == paket[bilesenid].gkrno){
					$("#gkrno").val($(this).val());
					$("#stokid").val($(this).val());					
				}
			});
		}
	}
	if(!found){
		$("#miktarbox").val(paket[bilesenid].miktar);
		var opt = '<option value="'+ paket[bilesenid].gkrno + '">' + paket[bilesenid].gkrno +", Adet:" + paket[bilesenid].miktar +'</option>';
		$("#gkrno").append(opt);
	}
	
	/* 0:insert, 1:update, 2:sil */
	$("#islemid").val("1");
	//changefun(document.irsaliyeform.mamulkod);
}

function deleteGoIrsaliyePaket(url,id,irsaliyeno,islem){
	var f_irsaliyeid	= id;
	var f_irsaliyeno	= irsaliyeno;
	var f_islemid		= islem;
	var alert_mesaj		= f_irsaliyeno + " Numaralı "; 
	if(confirm(alert_mesaj +"\n\n" + "İrsaliyeyi SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				irsaliyeid: f_irsaliyeid, 
				islemid: f_islemid
			},
			beforeSend: function ( xhr ) {
			},
			success: function(data, textStatus, xhr) {
			},
			error: function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function( msg ) {
			if( msg == "0"){
				$("#tr"+id).css("display","none");
				$("#tr_irs_detay"+id).css("display","none");
				alert( f_irsaliyeno + " NO'lu İrsaliye Bilgisi SİLİNDİ" );
				history.go(0);
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}

function okGoIrsaliyePaket(url,id,irsaliyeno,islem){
	var f_irsaliyeid	= id;
	var f_irsaliyeno	= irsaliyeno;
	var f_islemid		= islem;
	var alert_mesaj		= f_irsaliyeno + " Numaralı "; 
	if(confirm(alert_mesaj +"\n\n" + "İrsaliyeyi ONAYLAMAK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				irsaliyeid: f_irsaliyeid, 
				islemid: f_islemid
			},
			beforeSend: function ( xhr ) {
			},
			success: function(data, textStatus, xhr) {
			},
			error: function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function( msg ) {
			if( msg == "0"){
				//$("#tr"+id).css("display","none");
				//$("#tr_irs_detay"+id).css("display","none");
				$("#divirsaliye"+f_irsaliyeid).html("<span class='text-danger'><strong>onaylandı</strong></span>");
				alert( f_irsaliyeno + " NO'lu İrsaliye ONAYLANDI" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}