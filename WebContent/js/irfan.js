var numpattern	= /^[1-9][0-9]*$/;
function is_number(formval){
	if($.trim(formval)==""){
		return false;
	}
	return formval.match(numpattern);
}
function is_empty(formval) {
	if ($.trim(formval) == ""){
		return true;
	}
	else{
		return false;	
	}
	
}
function disableFormElements(action_form){
	var elements = action_form.elements;
	for(var i=0; i< action_form.length; i++ ){
		elements[i].disabled = true;
	}
}
function hideshow(trid){
	$(trid).toggle("slow");
}
$("#go").click(function(){
	var message="";
	if(document.kategori.customerid.value==""){
		message += "Müşteri seçiniz!\n";
	}
	if(document.kategori.branchid.value==""){
		message += "Lokasyon seçiniz!\n";
	}
	if(document.kategori.channelid.value==""){
		message += "Mecra seçiniz!\n";
	}
	if(document.kategori.productid1.value=="" &&
		document.kategori.productid2.value=="" &&
		document.kategori.productid3.value==""
		) {
		message += "En az bir ürün seçiniz!\n";
	}
	if(message!=""){
		alert(message);
	}
	else{
		document.kategori.submit();
	}
});