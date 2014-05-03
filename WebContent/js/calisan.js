function updateCalisanGo(url,id,action_form,islem){
	var f_calisanid		= id;
	var f_calisanad		= $.trim(action_form.liste_calisanad.value);
	var f_calisansoyad	= $.trim(action_form.liste_calisansoyad.value);
	var f_calisangorev	= $.trim(action_form.liste_calisangorev.value);
	var f_islemid		= islem;
	var alert_mesaj		= f_calisanad + " " + f_calisansoyad;
	if(is_empty(f_calisanad) || is_empty(f_calisansoyad) ){
		alert("Çalışan ADI veya SOYADI boş olamaz!");
		return false;
	}
	if(confirm(alert_mesaj +"\n\n" + "Çalışan bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				id:		f_calisanid, 
				ad:		f_calisanad, 
				soyad:	f_calisansoyad, 
				gorev:	f_calisangorev, 
				islemid:f_islemid
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
				alert( alert_mesaj +" Çalışan bilgisi başarıyla GÜNCELLENDİ" );
			}
			else{
				alert("Hata:"+ msg + ":" );
			}
		});
	}
}
function deleteCalisanGo(url,id,action_form,islem){
	var f_calisanid		= id;
	var f_calisanad		= $.trim(action_form.liste_calisanad.value);
	var f_calisansoyad	= $.trim(action_form.liste_calisansoyad.value);
	var f_islemid		= islem;
	var alert_mesaj		= f_calisanad + " " + f_calisansoyad;
	if(confirm(alert_mesaj +"\n\n" + "Çalışan bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				id:		f_calisanid, 
				islemid:f_islemid
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
				alert( alert_mesaj +" Çalışan bilgisi başarıyla SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}