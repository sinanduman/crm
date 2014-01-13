function YariMamulEkleCtrl($scope) {
	$scope.yarimamulEkle = function(){
		var message	= "";
		if($.trim(document.yarimamul_form.yarimamulkod.value)==""){
			message += "Yarımamül Kodu giriniz!\n";
		}
		if($.trim(document.yarimamul_form.yarimamulad.value)==""){
			message += "Yarımamül Adı giriniz!\n";
		}
		if(message!=""){
			alert(message);
			return false;
		}
		else{
			if(confirm('Yarımamül EKLEMEYİ onaylıyor musun?')){
				document.yarimamul_form.submit();
			}
		}
	}
}
function updateYarimamulGo(url,id,action_form,islem){
	var f_bilesenid	= id;
	var f_birimid	= action_form.liste_birimid.value;
	var f_firmaid	= action_form.liste_firmaid.value;
	var f_kod		= $.trim(action_form.liste_kod.value);
	var f_ad		= $.trim(action_form.liste_ad.value);
	var f_islemid	= islem;
	var alert_mesaj	= f_kod + " Kodlu " + f_ad;
	if(f_kod=="" || f_ad=="" ){
		alert("Yarımamül ADI veya KODU boş olamaz!");
		return false;
	}
	if(confirm(alert_mesaj +"\n\n" + "Yarımamül bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				bilesenid: f_bilesenid, 
				birimid: f_birimid, 
				firmaid: f_firmaid, 
				bilesenkod: f_kod,
				bilesenad: f_ad,
				islemid: f_islemid
			},
			dataType: 'html',
			beforeSend: function ( xhr ) {
			},
			success: function(data, textStatus, xhr) {
			},
			error: function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function( msg ) {
			if(is_number( msg )){
				$("#tr"+id).css('background-color','darkkhaki');
				alert( f_kod + " Kodlu " + f_ad +" Yarımamül bilgisi başarıyla GÜNCELLENDİ" );
			}
			else{
				alert("Hata:"+ msg + ":" );
			}
		});
	}
}
function deleteYarimamulGo(url,id,action_form,islem){
	var f_bilesenid	= id;
	var f_birimid	= action_form.liste_birimid.value;
	var f_firmaid	= action_form.liste_firmaid.value;
	var f_kod		= $.trim(action_form.liste_kod.value);
	var f_ad		= $.trim(action_form.liste_ad.value);
	var f_islemid	= islem;
	var alert_mesaj	= f_kod + " Kodlu " + f_ad;
	if(confirm(alert_mesaj +"\n\n" + "Yarımamül bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				bilesenid: f_bilesenid, 
				islemid: f_islemid
			},
			dataType: "html",
			beforeSend: function ( xhr ) {
			},
			success: function(data, textStatus, xhr) {
			},
			error: function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function( msg ) {
			if(is_number( msg )){
				$("#tr"+id).css("display","none");
				alert( f_kod + " Kodlu " + f_ad +" Yarımamül bilgisi başarıyla SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
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
			dataType: "html",
			beforeSend: function ( xhr ) {
			},
			success: function(data, textStatus, xhr) {
			},
			error: function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function( msg ) {
			if(is_number( msg )){
				$("#tr"+id).css("display","none");
				$("#tr_irs_detay"+id).css("display","none");
				alert( f_irsaliyeno + " NO'lu İrsaliye Bilgisi SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}
function deleteGoOld(url,id){
	if(confirm(id + ' nolu Siparişi SİLMEK istediğinden emin misin?')){
		$.ajax({
			url: url,
			type: 'POST',
			data: { orderid: id },
			dataType: 'html',
			beforeSend: function ( xhr ) {
				// $("#ajaxresult").attr('class','');
				// alert(url+"/"+id);
				// alert($(this));
			},
			success: function(data, textStatus, xhr) {
				//if(!is_number(data)){
				//	alert(data);
				//}
			},
			error: function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function( msg ) {
			if(is_number( msg )){
				alert( msg + " No'lu sipariş SİLİNDİ.");
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}