/**
 * Funcion para validar solo numero en un input
 * 
 * @param evt
 * @returns {Boolean}
 */
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
	return true;
}

/**
 * Funcion para validar los caracteres permitidos en un input
 * 
 * @param elEvento
 * @param valoresPermitidos
 * @returns {Boolean}
 */
function permitirValores(elEvento, valoresPermitidos) {
	// Variables que definen los caracteres permitidos
	var copiarPegar = 'cCvV';
	var numeros = '0123456789';
	var caracteres = ' abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1';
	var slash = '/';
	var punto = '.';
	var numerosCaracteres = numeros + caracteres;

	var teclasEspeciales = [ 8, 37, 39, 46, 17, 35, 36, 9 ];
	// 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha, 17
	// Control, 36 inicio, 35 fin, 9 Tab

	// verifica si es la acci�n de copiar o pegar

	// Seleccionar los caracteres a partir del parametro de la funcion
	switch (valoresPermitidos) {
	case 'num':
		valoresPermitidos = numeros;
		break;
	case 'dec':
		valoresPermitidos = numeros + punto;
		break;
	case 'car':
		valoresPermitidos = caracteres;
		break;
	case 'fec':
		valoresPermitidos = numeros + slash;
		break;
	case 'num_car':
		valoresPermitidos = numerosCaracteres;
		break;
	}

	// Obtener la tecla pulsada
	var evento = elEvento || window.event;
	var codigoCaracter = evento.charCode || evento.keyCode;
	var caracter = String.fromCharCode(codigoCaracter);

	// validar si es copiar y pegar
	if (elEvento.ctrlKey && (copiarPegar.indexOf(caracter) != -1)) {
		return true;
	}

	// Comprobar si la tecla pulsada es alguna de las teclas especiales
	// (teclas de borrado y flechas horizontales)
	var teclaEspecial = false;
	for ( var i in teclasEspeciales) {
		if (codigoCaracter == teclasEspeciales[i]) {
			teclaEspecial = true;
			break;
		}
	}

	// Comprobar si la tecla pulsada se encuentra en los caracteres
	// valoresPermitidos
	// o si es una tecla especial
	return (valoresPermitidos.indexOf(caracter) != -1 || teclaEspecial);
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