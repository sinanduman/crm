function SiparisCntrl($scope) {
	$scope.siparisJSON = siparis;
	$scope.select = function() {
		if($scope.siparisJSON.length>0){
			return $scope.siparisJSON[$scope.siparisJSON.length-1].id;
		}
		return 0;
	};
	$scope.savePlan = function(){
		var baszaman  = document.uretimplanform.bassaat.value + document.uretimplanform.basdakika.value;
		var bitzaman  = document.uretimplanform.bitsaat.value + document.uretimplanform.bitdakika.value;
		if(!is_positive(document.uretimplanform.miktar.value)){
			alert("Planlanan Miktar NÜMERİK bir değer olmalıdır!");
			return false;
		}
		else if (baszaman == bitzaman ){
			alert("Başlangıç ve Bitiş zamanları AYNI olamaz!");
			return false;
		}
		else{
			if(parseInt(baszaman,10) > parseInt(bitzaman,10)){
				if(confirm("GECE çalışması olacak. Onaylıyor musun?")){
					document.uretimplanform.submit();
				}
				else{
					return false;
				}
			}
			else{
				document.uretimplanform.submit();
			}
		}
	};
}

function SiparisEkleCtrl($scope) {
	$scope.saveSiparis = function(){
		if(!is_positive(document.siparisform.miktar.value)){
			alert("Sipariş Miktarı NÜMERİK bir değer olmalıdır!");
			return false;
		}
		else{
			document.siparisform.submit();
		}
	};
}

function updateGo(url,id,action_form,islem){
	var f_siparisplanid	= id;
	var f_bilesen		= action_form.bilesen.value;
	var f_miktar		= $.trim(action_form.miktar.value);
	var f_makinaid		= action_form.makina.value; 
	var f_calisanid		= action_form.calisan.value;
	var f_tarih			= action_form.tarih.value;
	var f_bassaat		= action_form.bassaat.value;
	var f_basdakika		= action_form.basdakika.value;
	var f_bitsaat		= action_form.bitsaat.value;
	var f_bitdakika		= action_form.bitdakika.value;
	var f_hataid		= action_form.hataid.value;
	var f_hatamiktar	= $.trim(action_form.hatamiktar.value);
	var f_durusid		= action_form.durusid.value;
	var f_not			= action_form.not.value;
	var f_islemid		= islem;
	var alert_mesaj		= f_siparisplanid + " Numaralı " + f_bilesen;
	
	if(!is_empty(f_hatamiktar) && !is_positive(f_hatamiktar)){
		alert("Hata miktarı, eğer varsa, NÜMERİK olmalıdır!");
		return false;
	}
	if(is_empty(f_hataid) && is_positive(f_hatamiktar)){
		alert("Hata SEBEBİ seçmelisin!");
		return false;
	}
	if(!is_empty(f_hataid) && !is_positive(f_hatamiktar)){
		alert("Hata MİKTARI seçmelisin!");
		return false;
	}
	if (is_positive(f_miktar)){
		if(confirm(alert_mesaj +"\n\n" + "Üretim Plan bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
			$.ajax({
				url: url,
				type: "POST",
				data: { 
					siparisplanid: f_siparisplanid, 
					miktar: f_miktar, 
					makinaid: f_makinaid, 
					calisanid: f_calisanid,
					tarih: f_tarih,
					bassaat: f_bassaat,
					basdakika: f_basdakika,
					bitsaat: f_bitsaat,
					bitdakika: f_bitdakika,
					hataid: f_hataid,
					hatamiktar: f_hatamiktar,
					durusid: f_durusid,
					not: f_not,
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
					alert( f_bilesen + " ürünün Plan bilgisi başarıyla GÜNCELLENDİ" );
				}
				else{
					alert("Hata:"+ msg + ":" );
				}
			});
		}
	}
	else{
		alert("HATA! Miktar Bilgisine 0'dan farklı SAYISAL bir değer girin!");
	}
}
function okGo(url,id,action_form,islem){
	var f_siparisplanid	= id;
	var f_bilesen		= action_form.bilesen.value;
	var f_miktar		= action_form.miktar.value;
	var f_makinaid		= action_form.makina.value; 
	var f_calisanid		= action_form.calisan.value;
	var f_tarih			= action_form.tarih.value;
	var f_bassaat		= action_form.bassaat.value;
	var f_basdakika		= action_form.basdakika.value;
	var f_bitsaat		= action_form.bitsaat.value;
	var f_bitdakika		= action_form.bitdakika.value;
	var f_islemid		= islem;
	var alert_mesaj		= f_siparisplanid + " Numaralı " + f_bilesen; 
	if (is_positive(f_miktar)){
		if(confirm(alert_mesaj +"\n\n" + "Plan bilgisinin TAMAMLANDIĞINDAN" +"\n\n" + "Emin misin?")){
			$.ajax({
				url: url,
				type: "POST",
				data: { 
					siparisplanid: f_siparisplanid, 
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
					$("#tr"+id).css("background-color","E6E6E6");
					disableFormElements(action_form);
					//$("#tr"+id).css("display","none");
					//$("#tr"+id).css("display","none");
					//$("#tr"+id).css("display","none");
					//$("#tr"+id).css("display","none");
					alert( f_bilesen + " ürünün Üretim Planı TAMAMLANDI" );
				}
				else{
					//$("#tr_"+msg).css('visibility','hidden');
					alert("Hata: "+ msg );
				}
			});
		}
	}
	else{
		alert("HATA! Miktar Bilgisine 0'dan farklı SAYISAL bir değer girin!");
	}
}
function deleteGo(url,id,action_form,islem){
	var f_siparisplanid	= id;
	var f_bilesen		= action_form.bilesen.value;
	var f_miktar		= action_form.miktar.value;
	var f_makinaid		= action_form.makina.value; 
	var f_calisanid		= action_form.calisan.value;
	var f_tarih			= action_form.tarih.value;
	var f_bassaat		= action_form.bassaat.value;
	var f_basdakika		= action_form.basdakika.value;
	var f_bitsaat		= action_form.bitsaat.value;
	var f_bitdakika		= action_form.bitdakika.value;
	var f_islemid		= islem;
	var alert_mesaj		= f_siparisplanid + " Numaralı " + f_bilesen; 
	if (is_positive(f_miktar)){
		if(confirm(alert_mesaj +"\n\n" + "Plan bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
			$.ajax({
				url: url,
				type: "POST",
				data: { 
					siparisplanid: f_siparisplanid, 
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
					//disableFormElements(action_form);
					//$("#tr"+id).css("display","none");
					//$("#tr"+id).css("display","none");
					//$("#tr"+id).css("display","none");
					$("#tr"+id).css("display","none");
					alert( f_bilesen + " ürünün Üretim Planı SİLİNDİ" );
				}
				else{
					alert("Hata: "+ msg );
				}
			});
		}
	}
	else{
		alert("HATA! Miktar Bilgisine 0'dan farklı SAYISAL bir değer girin!");
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