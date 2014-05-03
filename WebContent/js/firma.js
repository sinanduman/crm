function updateFirmaGo(url,id,action_form,islem){
	var f_firmaid		= id;
	var f_firmaad		= $.trim(action_form.liste_firmaad.value);
	var f_firmatel		= $.trim(action_form.liste_firmatel.value);
	var f_firmaadres	= $.trim(action_form.liste_firmaadres.value);
	var f_islemid		= islem;
	var alert_mesaj		= f_firmaad + "" + "";
	if(is_empty(f_firmaad)){
		alert("Firma ADI boş olamaz!");
		return false;
	}
	if(confirm(alert_mesaj +"\n\n" + "Firma bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				id:		f_firmaid, 
				ad:		f_firmaad, 
				tel:	f_firmatel, 
				adres:	f_firmaadres,
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
				alert( alert_mesaj +" Firma bilgisi başarıyla GÜNCELLENDİ" );
			}
			else{
				alert("Hata:"+ msg + ":" );
			}
		});
	}
}
function deleteFirmaGo(url,id,action_form,islem){
	var f_firmaid		= id;
	var f_firmaad		= $.trim(action_form.liste_firmaad.value);
	var f_islemid		= islem;
	var alert_mesaj		= f_firmaad + "" + "";
	if(confirm(alert_mesaj +"\n\n" + "Firma bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				id:		f_firmaid, 
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
				alert( alert_mesaj +" Firma bilgisi başarıyla SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}