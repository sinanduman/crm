function updateMakinaGo(url,id,action_form,islem){
	var f_makinaid		= id;
	var f_makinaad		= $.trim(action_form.liste_makinaad.value);
	var f_makinatip		= $.trim(action_form.liste_makinatipid.value);
	var f_islemid		= islem;
	var alert_mesaj		= f_makinaad + "" + "";
	if(is_empty(f_makinaad) ){
		alert("Makina ADI boş olamaz!");
		return false;
	}
	if(confirm(alert_mesaj +"\n\n" + "Makina bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				id:		f_makinaid, 
				ad:		f_makinaad, 
				tip:	f_makinatip, 
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
				alert( alert_mesaj +" Makina bilgisi başarıyla GÜNCELLENDİ" );
			}
			else{
				alert("Hata:"+ msg + ":" );
			}
		});
	}
}
function deleteMakinaGo(url,id,action_form,islem){
	var f_makinaid		= id;
	var f_makinaad		= $.trim(action_form.liste_makinaad.value);
	var f_islemid		= islem;
	var alert_mesaj		= f_makinaad + "" + "";
	if(confirm(alert_mesaj +"\n\n" + "Makina bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				id:		f_makinaid, 
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
				alert( alert_mesaj +" Makina bilgisi başarıyla SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}