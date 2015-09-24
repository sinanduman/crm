$("#lot, #tarih, #miktar, #iade").change(
		function() {
			if (
					!is_empty($("#lot").val()) && 
					!is_empty($("#tarih").val()) && 
					is_positive($("#miktar").val())
			) {
				mamulStokKontrol(
						$("#tarih").val(), 
						$.trim($("#lot").val()), 
						$.trim($("#miktar").val()), 
						$("#iade").is(":checked"),
						3 // mamul bilesentipid
						);
			}
		});

$("#mamulstokekle").click(function(){
	if(!is_positive($("#mamulid").val()) ){
		alert("MAMUL adı boş olamaz!");
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
			if(is_empty($("#not").val())){
				alert("Stoktan düşme nedeni giriniz!");
				return false;
			}
			if(is_empty($("#lot").val())){
				alert("Mamül İzl. No giriniz!");
				return false;
			}
			if(parseInt($("#miktar").val()) > parseInt($("#sumagg").val())){
				alert("Düşülen Miktar, Envanter Miktarından yüksek olamaz!");
				return false;
			}
			else{
				if(confirm($("#mamulad").val() + " için " +$("#miktar").val() + " adet Stok'tan DÜŞMEYİ onaylıyor musun?")){
					//console.log("miktar: " + $("#miktar").val());
					//console.log("sumagg: " + $("#sumagg").val());
					$("#mamulekleid").val("1");
					$("#mamulstokform").submit();
				}				
			}
		}
		else{
			if(is_empty($("#lot").val())){
				alert("Mamül İzl. No giriniz!");
				return false;
			}
			else {
				if(confirm($("#mamulad").val() + " için " +$("#miktar").val() + " adet Stok EKLEMEYİ onaylıyor musun?")){
					$("#mamulekleid").val("1");
					$("#mamulstokform").submit();
				}				
			}
			
		}
	}
});

function mamulStokKontrol(tarih, gkrno, miktar, iade, bilesentipid){
	var f_tarih			= tarih;
	var f_gkrno			= gkrno;
	var f_miktar		= miktar;
	var f_iade			= iade;
	var f_bilesentipid	= bilesentipid;
	var f_islem	= 6;
	
	$.ajax({
		url: "mamulstok",
		type: "POST",
		data: { 
			tarih			: f_tarih,
			gkrno			: f_gkrno,
			miktar			: f_miktar,
			iade			: f_iade,
			bilesentipid	: f_bilesentipid,
			islemid			: f_islem
		},
		beforeSend: function ( xhr ) {
		},
		success: function(data, textStatus, xhr) {
		},
		error: function(xhr, textStatus, errorThrown) {
			alert("Hata Oluştu: " + textStatus + " , " + errorThrown);
		}
	}).done(function( msg ) {
		if( msg != "0"){
			$( "#mamulstokekle" ).prop( "disabled", true );
			$( "#stok-ekle-kutu" ).text(msg);
		}
		else{
			$( "#mamulstokekle" ).prop( "disabled", false );
			$( "#stok-ekle-kutu" ).text("");
		}
	});
}