var islemtip = {
	add:"Pakete Ekle",
	update:"Paketi Güncelle"
}	
var mamulApp = angular.module('irsaliyeApp', []);
mamulApp.controller('IrsaliyeCtrl',['$scope', function($scope){
	var show = true;
	var uid = 0;

	$scope.mamulJSON = mamuldb;
	$scope.select = function() {
		if($scope.mamulJSON.length>0){
			return $scope.mamulJSON[$scope.mamulJSON.length-1].id;	
		}
		return 0;
	};

	$scope.irsaliye = [];

	$scope.savePaket = function(){
		if(document.irsaliyeform.irsaliye_length.value==0){
			alert("Paket oluşturmak için EN AZ bir ürün eklenmiş olmalı!");
			return false;
		}
		document.irsaliyeform.submit();
	}

	$scope.saveIrsaliye = function() {
		if(!is_positive($.trim(document.irsaliyeform.miktar.value))){
			alert("Miktar NÜMERİK bir değer olmalı!");
			return;
		}
		if($scope.yeniirsaliye.id == null) {
			$scope.yeniirsaliye.id = uid++;
			$scope.yeniirsaliye.mamulid		= document.irsaliyeform.mamulid.options[document.irsaliyeform.mamulid.selectedIndex].value;
			$scope.yeniirsaliye.mamulad		= document.irsaliyeform.mamulid.options[document.irsaliyeform.mamulid.selectedIndex].text;
			$scope.yeniirsaliye.firmaid		= $scope.firmaidgetir($scope.yeniirsaliye.mamulid);
			$scope.yeniirsaliye.firmaad		= $scope.firmaadgetir($scope.yeniirsaliye.mamulid);
			$scope.irsaliye.push($scope.yeniirsaliye);
		} else {
			for(i in $scope.irsaliye) {
				if($scope.irsaliye[i].id == $scope.yeniirsaliye.id) {
					$scope.yeniirsaliye.mamulid 	= document.irsaliyeform.mamulid.options[document.irsaliyeform.mamulid.selectedIndex].value
					$scope.yeniirsaliye.mamulad 	= document.irsaliyeform.mamulid.options[document.irsaliyeform.mamulid.selectedIndex].text
					$scope.yeniirsaliye.firmaid 	= $scope.irsaliye[i].firmaid;
					$scope.yeniirsaliye.firmaad 	= $scope.irsaliye[i].firmaad;
					$scope.irsaliye[i] = $scope.yeniirsaliye;
				}
			}
		}
		$scope.yeniirsaliye = {};
		document.irsaliyeform.pakete_ekle.textContent = islemtip.add;
	}

	$scope.firmaidgetir = function(mamulid) {
		for(i in $scope.mamulJSON) {
			if($scope.mamulJSON[i].mamulid == mamulid) {
				return $scope.mamulJSON[i].firmaid;
			}
		}
	}
	$scope.firmaadgetir = function(mamulid) {
		for(i in $scope.mamulJSON) {
			if($scope.mamulJSON[i].mamulid == mamulid) {
				return $scope.mamulJSON[i].firmaad;
			}
		}
	}
	$scope.edit = function(id) {		
		for(i in $scope.irsaliye) {
			if($scope.irsaliye[i].id == id) {
				$scope.mamulid	= $scope.irsaliye[i].mamulid;
				$scope.mamulad	= $scope.irsaliye[i].mamulad;
				$scope.firmaid	= $scope.irsaliye[i].firmaid;
				$scope.firmaad	= $scope.irsaliye[i].firmaad;
				$scope.miktar	= $scope.irsaliye[i].miktar;
				
				$scope.yeniirsaliye = angular.copy($scope.irsaliye[i]);
				document.irsaliyeform.pakete_ekle.textContent = islemtip.update;
			}
		}
	}
	$scope.del = function(id) {
		for(i in $scope.irsaliye) {
			if($scope.irsaliye[i].id == id) {
				$scope.irsaliye.splice(i,1);
				$scope.yeniirsaliye = {};
				document.irsaliyeform.pakete_ekle.textContent = islemtip.add;
			}
		}
	}
	$scope.index = function(id) {
		for(i in $scope.irsaliye) {
			if($scope.irsaliye[i].mamulid == id) {
				$scope.irsaliye.splice(i,1);
				$scope.yeniirsaliye = {};
			}
		}
	}
}]);
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