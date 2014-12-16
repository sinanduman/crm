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
			if(confirm($("#mamulad").val() + " için " +$("#miktar").val() + " adet Stok EKLEMEYİ onaylıyor musun?")){
				$("#mamulekleid").val("1");
				$("#mamulstokform").submit();
			}
			else{
				return false;
			}
		}
	}
});