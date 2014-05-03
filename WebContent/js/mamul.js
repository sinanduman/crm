var mamulislemtip = {
	add:"Bileşen Ekle",
	update:"Bileşen Güncelle"
};
var mamulApp = angular.module('mamulApp', []);
mamulApp.controller('MamulCtrl',['$scope', function($scope){
	var show = true;
	var uid = 0;

	$scope.bilesenler = [];
	$scope.tip = [
		{id:1, ad:"Hammadde"},
		{id:2, ad:"Yarımamül"}
	];
	$scope.birimler = [
		{id:0, ad:"Gram"},
		{id:1, ad:"Adet"},
		{id:2, ad:"KG"}
	];
	$scope.on = function(){
		document.mamulform.birimid_select.selectedIndex	= $scope.birimler[0].id;
		document.mamulform.birimid.value				= document.mamulform.birimid_select.value;
		show = true;
	};
	$scope.off = function(){
		document.mamulform.birimid_select.selectedIndex	= $scope.birimler[1].id;
		document.mamulform.birimid.value				= document.mamulform.birimid_select.value;
		show = false;
	};
	$scope.showState = function(){
		return show;
	};
	$scope.saveBilesen = function() {
		if(!is_positive(document.mamulform.miktar.value)){
			alert("Bileşen Miktarı NÜMERİK bir değer olmalı!");
			return false;	
		}
		if($scope.yenibilesen.id == null) {
			$scope.yenibilesen.id = uid++;
			$scope.yenibilesen.hammaddead	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].text;
			$scope.yenibilesen.hammaddeid	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].value;
			$scope.yenibilesen.yarimamulad	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].text;
			$scope.yenibilesen.yarimamulid	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].value;
			$scope.yenibilesen.birimad		= document.mamulform.birimid_select.options[document.mamulform.birimid_select.selectedIndex].text;
			$scope.yenibilesen.birimid		= document.mamulform.birimid_select.options[document.mamulform.birimid_select.selectedIndex].value;
			if(show){
				$scope.yenibilesen.uretimtipid	= $scope.tip[0].id;
				$scope.yenibilesen.uretimtip	= $scope.tip[0].ad;
				$scope.yenibilesen.bilesenid	= $scope.yenibilesen.hammaddeid;
				$scope.yenibilesen.bilesenad	= $scope.yenibilesen.hammaddead;
			}
			else{
				$scope.yenibilesen.uretimtipid	= $scope.tip[1].id;
				$scope.yenibilesen.uretimtip	= $scope.tip[1].ad;
				$scope.yenibilesen.bilesenid	= $scope.yenibilesen.yarimamulid;
				$scope.yenibilesen.bilesenad	= $scope.yenibilesen.yarimamulad;
			}
			$scope.bilesenler.push($scope.yenibilesen);
		} else {
			for(i in $scope.bilesenler) {
				if($scope.bilesenler[i].id == $scope.yenibilesen.id) {
					$scope.yenibilesen.hammaddead 	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].text;
					$scope.yenibilesen.hammaddeid 	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].value;
					$scope.yenibilesen.yarimamulad	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].text;
					$scope.yenibilesen.yarimamulid	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].value;
					$scope.yenibilesen.birimad		= document.mamulform.birimid_select.options[document.mamulform.birimid_select.selectedIndex].text;
					$scope.yenibilesen.birimid		= document.mamulform.birimid_select.options[document.mamulform.birimid_select.selectedIndex].value;
					if(show){
						$scope.yenibilesen.uretimtipid	= $scope.tip[0].id;
						$scope.yenibilesen.uretimtip	= $scope.tip[0].ad;
						$scope.yenibilesen.bilesenid	= $scope.yenibilesen.hammaddeid;
						$scope.yenibilesen.bilesenad	= $scope.yenibilesen.hammaddead;
					}
					else{
						$scope.yenibilesen.uretimtipid	= $scope.tip[1].id;
						$scope.yenibilesen.uretimtip	= $scope.tip[1].ad;
						$scope.yenibilesen.bilesenid	= $scope.yenibilesen.yarimamulid;
						$scope.yenibilesen.bilesenad	= $scope.yenibilesen.yarimamulad;
					}
					$scope.bilesenler[i] = $scope.yenibilesen;
				}
			}
		}
		$scope.yenibilesen = {};
		document.mamulform.bilesen_ekle.textContent = mamulislemtip.add;
	};

	$scope.saveMamul = function() {	
		if($scope.bilesenler.length==0) {
			alert("En az BİR bileşen eklenmelidir!");
		} else {
			var mamulad = document.mamulform.mamulad.value;
			var mamulkod = document.mamulform.mamulkod.value;
			var mamulcevrim = document.mamulform.mamulcevrim.value;

			if($.trim(mamulad)=="" || $.trim(mamulkod)=="" || $.trim(mamulcevrim)=="" ){
				alert("Mamül ADI, KODU veya ÇEVRİMSÜRESİ boş olamaz!");
				return false;
			}
			else{
				if(!is_positive(mamulcevrim)){
					alert("Çevrimsüresi NÜMERİK bir değer olmalı!");
					return false;
				}
				else{
					document.mamulform.submit();
				}
			}
		}
	};

	$scope.edit = function(id) {
		for(i in $scope.bilesenler) {
			if($scope.bilesenler[i].id == id) {
				document.mamulform.birimid_select.value	= $scope.bilesenler[i].birimid;
				document.mamulform.birimid.value		= $scope.bilesenler[i].birimid;
				
				/*1:Hammadde, 2:Yarimamul */
				if($scope.bilesenler[i].uretimtipid==2){
					$scope.bilesenler[i].uretimtip		= $scope.tip[1].ad;
					$scope.bilesenler[i].uretimtipid	= $scope.tip[1].id;
					document.mamulform.yarimamul.value	= $scope.bilesenler[i].yarimamulid;
					document.mamulform.bilesenTipRadio[1].checked = true;
					$scope.off();
					$scope.showState();
				}
				else{
					$scope.bilesenler[i].uretimtip		= $scope.tip[0].ad;
					$scope.bilesenler[i].uretimtipid	= $scope.tip[0].id;
					document.mamulform.hammadde.value	= $scope.bilesenler[i].hammaddeid;
					document.mamulform.bilesenTipRadio[0].checked = true;
					$scope.on();
					$scope.showState();	
				}
				$scope.yenibilesen = angular.copy($scope.bilesenler[i]);
				document.mamulform.bilesen_ekle.textContent = mamulislemtip.update;
			}
		}
	};
	$scope.del = function(id) {
		for(i in $scope.bilesenler) {
			if($scope.bilesenler[i].id == id) {
				$scope.bilesenler.splice(i,1);
				$scope.yenibilesen = {};
				document.mamulform.bilesen_ekle.textContent = mamulislemtip.add;
			}
		}
	};
}]);

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
	};
}

function updateMamulGo(url,id,action_form,islem){
	var f_bilesenid	= id;
	var f_firmaid	= action_form.liste_firmaid.value;
	var f_kod		= $.trim(action_form.liste_kod.value);
	var f_ad		= $.trim(action_form.liste_ad.value);
	var f_figur		= $.trim(action_form.liste_figurid.value);
	var f_cevrimsure= $.trim(action_form.liste_cevrimsuresi.value);
	var f_islemid	= islem;
	var alert_mesaj	= f_kod + " Kodlu " + f_ad;
	if(f_kod=="" || f_ad==""  || f_cevrimsure=="" ){
		alert("Mamül ADI, KODU veya ÇEVRİMSÜRESİ boş olamaz!");
		return false;
	}
	if(!is_positive(f_cevrimsure)){
		alert("Çevrimsüresi NÜMERİK bir değer olmalı!");
		return false;
	}
	if(confirm(alert_mesaj +"\n\n" + "Mamül bilgisini GÜNCELLEMEK istediğinden"+"\n\n"+"Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				bilesenid	: f_bilesenid, 
				firmaid		: f_firmaid, 
				bilesenkod	: f_kod,
				bilesenad	: f_ad,
				islemid		: f_islemid,
				figur		: f_figur,
				cevrimsuresi: f_cevrimsure
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
				alert(alert_mesaj +" Mamül bilgisi başarıyla GÜNCELLENDİ" );
			}
			else{
				alert("Hata:"+ msg + ":" );
			}
		});
	}
}

function deleteMamulGo(url,id,action_form,islem){
	var f_bilesenid	= id;
	var f_kod		= $.trim(action_form.liste_kod.value);
	var f_ad		= $.trim(action_form.liste_ad.value);
	var f_islemid	= islem;
	var alert_mesaj	= f_kod + " Kodlu " + f_ad;
	if(confirm(alert_mesaj +"\n\n" + "Mamül bilgisini SİLMEK istediğinden" +"\n\n" + "Emin misin?")){
		$.ajax({
			url: url,
			type: "POST",
			data: { 
				bilesenid:	f_bilesenid, 
				islemid:	f_islemid
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
				alert( f_kod + " Kodlu " + f_ad +" Mamül bilgisi başarıyla SİLİNDİ" );
			}
			else{
				alert("Hata: "+ msg );
			}
		});
	}
}