var mamulApp = angular.module('mamulApp', []);
mamulApp.controller('MamulCtrl',['$scope', function($scope){
	var show = true;
	var uid = 0;

	$scope.bilesenler = [];
	$scope.tip = [
		{
			id:1, ad:"Üretim"
		},
		{
			id:2, ad:"Montaj"
		}
	];

	$scope.on = function(){
		show = true;
	}

	$scope.off = function(){
		show = false;
	}

	$scope.showState = function(){
		return show;
	}

	$scope.saveContact = function() {		
		if($scope.yenibilesen.id == null) {
			$scope.yenibilesen.id = uid++;
			$scope.yenibilesen.hammaddead	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].text;
			$scope.yenibilesen.yarimamulad	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].text;
			$scope.yenibilesen.birimad		= document.mamulform.birim.options[document.mamulform.birim.selectedIndex].text;
			$scope.yenibilesen.birimid		= document.mamulform.birim.options[document.mamulform.birim.selectedIndex].value;
			if(show){
				$scope.yenibilesen.uretimtipid	= $scope.tip[0].id;
				$scope.yenibilesen.uretimtip	= $scope.tip[0].ad;
				$scope.yenibilesen.bilesenid	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].value;
				$scope.yenibilesen.bilesenad	= $scope.yenibilesen.hammaddead;
			}
			else{
				$scope.yenibilesen.uretimtipid	= $scope.tip[1].id;
				$scope.yenibilesen.uretimtip	= $scope.tip[1].ad;
				$scope.yenibilesen.bilesenid	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].value;
				$scope.yenibilesen.bilesenad	= $scope.yenibilesen.yarimamulad;
			}
			$scope.bilesenler.push($scope.yenibilesen);
		} else {
			for(i in $scope.bilesenler) {
				if($scope.bilesenler[i].id == $scope.yenibilesen.id) {
					$scope.yenibilesen.hammaddead 	= document.mamulform.hammadde.options[document.mamulform.hammadde.selectedIndex].text
					$scope.yenibilesen.yarimamulad	= document.mamulform.yarimamul.options[document.mamulform.yarimamul.selectedIndex].text
					$scope.yenibilesen.birimad		= document.mamulform.birim.options[document.mamulform.birim.selectedIndex].text
					if(show){
						$scope.yenibilesen.bilesenad = $scope.yenibilesen.hammaddead;
					}
					else{
						$scope.yenibilesen.bilesenad = $scope.yenibilesen.yarimamulad;
					}
					$scope.bilesenler[i] = $scope.yenibilesen;
				}
			}
		}
		$scope.yenibilesen = {};
	}

	$scope.edit = function(id) {
		for(i in $scope.bilesenler) {
			if($scope.bilesenler[i].id == id) {
				console.table($scope.bilesenler[i].id);
				/* 1: Uretim, 2: Montaj */
				if($scope.bilesenler[i].uretimtipid==2){
					$scope.bilesenler[i].uretimtip = $scope.tip[1].ad;
					$scope.off();
					$scope.showState();
				}
				else{
					$scope.bilesenler[i].uretimtip = $scope.tip[0].ad;
					$scope.on();
					$scope.showState();	
				}
				$scope.yenibilesen = angular.copy($scope.bilesenler[i]);
			}
		}
	}
	
	$scope.del = function(id) {
		for(i in $scope.bilesenler) {
			if($scope.bilesenler[i].id == id) {
				$scope.bilesenler.splice(i,1);
				$scope.yenibilesen = {};
			}
		}
	}
}]);