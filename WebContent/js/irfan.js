var numpattern	= /^[0-9][0-9]*$/;
var numpattern2	= /^[1-9][0-9]*$/; /* Sayilabilir miktar icin */
var numpattern3	= /^[\-]?[1-9][0-9]*$/; /* Stok iade için miktar icin */
var numpattern4	= /^(([1-9]*[0-9]([.][0-9]+))|([1-9][0-9]*))$/; /* Hammadde gramaj icin */
function is_number(formval){
	if($.trim(formval)==""){
		return false;
	}
	return numpattern.test(formval);
}
function is_positive(formval){
	if($.trim(formval)==""){
		return false;
	}
	return numpattern2.test(formval);
}
function is_ondalik(formval){
	if($.trim(formval)==""){
		return false;
	}
	return numpattern4.test(formval);
}
function is_iade(formval){
	if($.trim(formval)==""){
		return false;
	}
	return numpattern3.test(formval);
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
	$(trid).toggle("fast");
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
function cleartrbackground(){
	$(".tableplan tr").css("background-color","");
}
var OK = (function() {
    "use strict";

    var elem,
        hideHandler,
        that = {};

    that.init = function(options) {
        elem = $(options.selector);
    };

    that.show = function(text) {
        clearTimeout(hideHandler);

        $("#okdiv").html(text);
        $("#okdiv").delay(200).fadeIn().delay(2000).fadeOut();
    };
    return that;
}());
var Error = (function() {
    "use strict";

    var elem,
        hideHandler,
        that = {};

    that.init = function(options) {
        elem = $(options.selector);
    };

    that.show = function(text) {
        clearTimeout(hideHandler);

        $("#errordiv").html(text);
        $("#errordiv").delay(200).fadeIn().delay(2000).fadeOut();
    };
    return that;
}());
$("#exceleaktar").click(function(){
	if(confirm("Üretim Planı Excel'e aktarılsın mı?\n")){
		$("#excelegonder").val(1);
		$("#exceltarih").val($("#tarih").val());
		$("#excelform").submit();
	}
});
$("#exceleaktar_stok").click(function(){
	if(confirm("Stok Raporu Excel'e aktarılsın mı?\n")){
		$("#excelegonder").val(1);
		$("#excelform").submit();
	}
});
$("#exceleaktar_mamul").click(function(){
	if(confirm("Mamul Stok Raporu Excel'e aktarılsın mı?\n")){
		$("#excelegonder").val(1);
		$("#excelform").submit();
	}
});