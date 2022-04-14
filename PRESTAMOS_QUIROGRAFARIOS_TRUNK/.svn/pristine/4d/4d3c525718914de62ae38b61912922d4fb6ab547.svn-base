localStorage.openpages = Date.now();
//localStorage.initial_page = 1;
var onLocalStorageEvent = function(e) {	
	//var msg="Estimado Cliente: \n\nEl sistema detect\u00f3 un nuevo inicio de sesi\u00f3n, por lo que proceder\u00E1 a cerrar la sesión anterior. \nPara continuar usando el sistema por favor ingrese nuevamente su usuario y clave del IESS."
	if (e.key == "openpages") {
		localStorage.page_available = Date.now();
	}
	if (e.key == "page_available") {		
		forzarLogoutJsFunction();
		localStorage.clear();		
	}	
	if (e.key != "page_available" && e.key != "openpages") {
		window.location = document.getElementById('redirMainPage').href
		+ "/pages/login.jsp"
	}
};

window.addEventListener('storage', onLocalStorageEvent, false);

