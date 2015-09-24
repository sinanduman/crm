$("#lot, #tarih, #miktar, #iade").change(
		function() {
			if (
					!is_empty($("#lot").val()) && 
					!is_empty($("#tarih").val()) && 
					is_positive($("#miktar").val())
			) {
				hammaddeStokKontrol(
						$("#tarih").val(), 
						$.trim($("#lot").val()), 
						$.trim($("#miktar").val()) * parseInt($("#malzemebirimcarpan").val()),
						$("#iade").is(":checked"),
						$("#bilesentipid").val()
						);
			}
		});
$("#hammaddestokekle").click(function(){
	if(!is_positive($("#bilesenid").val()) ){
		alert("BİLEŞEN adı boş olamaz!");
		return false;
	}	
	else if(!is_positive($("#miktar").val())){
		alert("Miktar NÜMERİK bir değer olmalıdır!");
		return false;
	}
	else{
		if($("#iade").is(":checked")){
			//console.log("sumag: 	" + $("#sumagg") );
			//console.log("sumag val: " + parseInt($("#sumagg").val()));
			if(isNaN(parseInt($("#sumagg").val())) ){
				alert("Envanter Miktarı belli değil, stok düşülemez!");
				return false;
			}
			if(is_empty($("#lot").val())){
				alert("LOT/Batch alanına GKR.No giriniz!");
				return false;
			}
			if(is_empty($("#not").val())){
				alert("Stoktan düşme nedeni giriniz!");
				return false;
			}
			if(parseInt($("#miktar").val()) > parseInt($("#sumagg").val())){
				alert("Düşülen Miktar, Envanter Miktarından yüksek olamaz!");
				return false;
			}
			else{
				if(confirm($("#bilesenad").val() + " için " +$("#miktar").val() +" "+ $("#malzemebirimad").val() +" Stok'tan DÜŞMEYİ onaylıyor musun?")){
					//console.log("miktar: " + $("#miktar").val());
					//console.log("sumagg: " + $("#sumagg").val());
					$("#bilesenekleid").val("1");
					$("#hammaddestokform").submit();
				}				
			}
		}
		else{
			if(is_empty($("#irsaliyeno").val()) ){
				alert("İRSALİYE No boş olamaz!");
				return false;
			}
			else if(is_empty($("#lot").val()) ){
				alert("LOT/Batch No boş olamaz!");
				return false;
			}
			else if(confirm($("#bilesenad").val() + " için " +$("#miktar").val() +" "+ $("#malzemebirimad").val() +" Stok EKLEMEYİ onaylıyor musun?")){
				$("#bilesenekleid").val("1");
				$("#hammaddestokform").submit();
			}
			else{
				return false;
			}
		}
	}
});

function updateHammaddeStokGo(url,id,action_form,islem){
	var f_bilesenid	= id;
	var f_birimid	= action_form.liste_birimid.value;
	var f_firmaid	= action_form.liste_firmaid.value;
	var f_kod		= $.trim(action_form.liste_kod.value);
	var f_ad		= $.trim(action_form.liste_ad.value);
	var f_islemid	= islem;
	var alert_mesaj	= f_kod + " Kodlu " + f_ad;
	if(f_kod=="" || f_ad=="" ){
		alert("Malzeme ADI veya KODU boş olamaz!");
		return false;
	}
	if(confirm(alert_mesaj +"\n\n" + "Malzeme bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
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
				alert( f_kod + " Kodlu " + f_ad +" Malzeme bilgisi başarıyla GÜNCELLENDİ" );
			}
			else{
				alert("Hata:"+ msg + ":" );
			}
		});
	}
}
function deleteHammaddeStokGo(url,id,islem){
	var f_stokid	= id;
	var f_islemid	= islem;
	var alert_mesaj	= f_stokid + " No'lu ";
	//console.log(f_stokid, f_islemid);
	if(confirm(alert_mesaj +"\n\n" + "Stok bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				stokid: f_stokid, 
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
				alert( f_stokid + " No'lu Stok bilgisi başarıyla SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}

function hammaddeStokKontrol(tarih, gkrno, miktar, iade, bilesentipid){
	var f_tarih			= tarih;
	var f_gkrno			= gkrno;
	var f_miktar		= miktar;
	var f_iade			= iade;
	var f_bilesentipid	= bilesentipid;
	var f_islem	= 6;
	
	if($("#iade").is(":checked")){
		$.ajax({
			url : "hammaddestok",
			type : "POST",
			data : {
				tarih		: f_tarih,
				gkrno		: f_gkrno,
				miktar		: f_miktar,
				iade		: f_iade,
				bilesentipid: f_bilesentipid,
				islemid 	: f_islem
			},
			beforeSend : function(xhr) {
			},
			success : function(data, textStatus, xhr) {
			},
			error : function(xhr, textStatus, errorThrown) {
				alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
			}
		}).done(function(msg) {
			if (msg != "0") {
				$("#hammaddestokekle").prop("disabled", true);
				$("#hammadde-stok-ekle-kutu").text(msg);
			} else {
				$("#hammaddestokekle").prop("disabled", false);
				$("#hammadde-stok-ekle-kutu").text("");
			}
		});
	}
	else{
		$("#hammaddestokekle").prop("disabled", false);
		$("#hammadde-stok-ekle-kutu").text("");
	}
}