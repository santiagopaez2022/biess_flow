function validarDigitos(valor) {
	var charpos = valor.search("[^0-9]");
	if (valor.length > 0 && charpos >= 0) {
		if (!strError || strError.length == 0) {
			strError = objValue.name
					+ ": Solo se permiten valores numéricos.";
		}// if
		alert(strError);
		return false;
	}// if
}// numeric

function validarNumeros(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	if ((keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13) {
		return true;
	} else {
		return false;
	}
}

function validarNumerosMonetarios(evt, field) {
	keynum = (document.all) ? evt.keyCode : evt.which;

	var keyCode = evt.keyCode;

	// se captura el contenido del input
	var valor = document.getElementById(field).value;

	// 13 = tecla enter
	// 46 = tecla punto (.)
	// 8 = backspace
	// 37 tecla a la izquierda
	// 39 tecla a la derecha
	// 46 tecla delete
	// Si el usuario pulsa la tecla enter o el punto y no hay ningun otro punto
	if (keynum == 13 || (keynum == 46 && valor.indexOf(".") == -1)
			|| keynum == 8 || keyCode == 37 || keyCode == 39 || keyCode == 46) {
		return true;
	}

	return /\d/.test(String.fromCharCode(keynum));
}

function validarNombres(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	if (!(keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13
			|| keynum == 32) {
		if (keynum == 44 || keynum == 47 || keynum == 59 || keynum == 92
				|| keynum == 124) {// Para que no acepte caracteres especiales
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}

function validarDecimales(evt, field) {
	key = e.keyCode ? e.keyCode : e.which
	// backspace
	if (key == 8)
		return true;
	// 0-9
	if (key > 47 && key < 58) {
		if (field.value == "")
			return true;
		regexp = /[0-9]{7}.[0-9]{2}$/
		return !(regexp.test(field.value));
	}
	// .
	if (key == 46) {
		if (field.value == "")
			return false;
		regexp = /^[0-9]+$/
		return regexp.test(field.value);
	}
	// other key
	return false;
}

function validarNomenclatura(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	if (keynum == 39 || keynum == 32 || keynum == 47 || keynum == 46) {
		return false;
	}
	if (keynum == 45) {// para el -
		return true;
	}
	valida = validarLetrasNomenclatura(evt);
	if (valida) {
		return true;
	} else {
		valida = validarNumeros(evt);
		if (valida) {
			return true;
		}
	}
	return false;
}

function validarLetrasNomenclatura(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;

	patron = /[a-zA-Z]/; // patron

	te = String.fromCharCode(keynum);
	return patron.test(te); // prueba de patron
}

function validaIdentificacionConyuge(elEvento) { // Obtener la tecla pulsada
	var evento = elEvento || window.event;
	var codigoCaracter = evento.charCode || evento.keyCode;
	var caracter = String.fromCharCode(codigoCaracter);
	if (document.getElementById('idForm:selectOneMenuTipoIdentificacionCon').value == 'C') {
		var charCode = (elEvento.which) ? elEvento.which : elEvento.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57))
			return false;
		return true;
	}
}

function validarCaracteresEspeciales(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	// Obtener la tecla pulsada
	var evento = evt || window.event;
	var codigoCaracter = evento.charCode || evento.keyCode;
	var caracter = String.fromCharCode(codigoCaracter);
	var copiarPegar = 'cCvV';
	
	// validar si es copiar y pegar
	if (evt.ctrlKey && (copiarPegar.indexOf(caracter) != -1)) {
		return false;
	}
	
	//No permite el ingreso de simbolo de grados º
	if (keynum == 186 || keynum == 39 || keynum == 47 ) {
		return false;
	}
	if (keynum == 45 || keynum == 32 || keynum == 46 || keynum == 44) {// para el -
		return true;
	}
	valida = validarLetrasNomenclatura(evt);
	if (valida) {
		return true;
	} else {
		valida = validarNumeros(evt);
		if (valida) {
			return true;
		}
	}
	return false;
}
