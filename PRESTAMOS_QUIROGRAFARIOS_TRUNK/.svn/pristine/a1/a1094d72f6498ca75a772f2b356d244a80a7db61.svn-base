function aceptarNumeros(e, field, decimales) {
	key = e.keyCode ? e.keyCode : e.which
    // Teclas especiales
    // Flechas direccionales 39 derecha, 37 izquierda
    // 8 retroceso, copiar 99, pegar 118, 46 suprimir, 36 inicio, 35 fin
    if (key == 39 || key == 37 || key == 8
                    || key == 35 || key == 36)
            return true

            // tab
    if (key == 9)
            return true

            // backspace
    if (key == 8)
            return true
            // 0-9
    if (key > 47 && key < 58) {
            if (field.value == "")
                    return true
            // regexp = /[.]{1}[0-9]{2}$/
            return true;// !(regexp.test(field.value))
    }
    // .
	if (decimales) {
	    if (key == 46) {
	            var split = field.value.split(".");
	            if(split.length > 1){
	                    return false;
	            }
	            if (field.value == "")
	                    return false
	            regexp = /^[0-9]+$/
	            return regexp.test(field.value)
	    }
	}

    // other key
    return false
}

/* Funcion para validar el ingreso de solo numeros */
function soloNumeros(e, field) {
	key = e.keyCode ? e.keyCode : e.which;
    // tab
    if (key == 9)
            return true
    // backspace
    if (key == 8)
            return true
	// 0-9
	if (key > 47 && key < 58) {
		return true;
    }

	// other key
    return false
}

/*************************************************************************
* INC-2272 Pensiones Alimenticias Funcion para cambiar amayusculas.
* 
*************************************************************************/
function permitirAlfanumericoConvertirMayusculas(elEvento, object) {
	// Variables que definen los caracteres permitidos
    var copiarPegar = 'cCvV';
    var numeros = '0123456789';
    var caracteres = ' abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1';
    var slash = '/';
    var punto = '.';
    var numerosCaracteres = numeros + caracteres;
   
    var teclasEspeciales = [8, 37, 39, 46, 17, 35, 36, 32 ];
    // 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha, 17
    // Control, 36 inicio, 35 fin

    valoresPermitidos = numerosCaracteres;

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
    
    //Tecla de espacio
    if (codigoCaracter==32) {
        return false;
    }
    
    for ( var i in teclasEspeciales) {
        if (codigoCaracter == teclasEspeciales[i]) {
            teclaEspecial = true;
            break;
        }
    }

    toUpperCase(object);
    
    // Comprobar si la tecla pulsada se encuentra en los caracteres valoresPermitidos
    // o si es una tecla especial
    return (valoresPermitidos.indexOf(caracter) != -1 || teclaEspecial); 
}


function toUpperCase(object) {
	try {
		if (object) {
			var value = object.value;
			if (value) {
				object.value = value.toUpperCase();
			}
		}
	} catch (exception) {
	}
}

/*************************************************************************
* Reimpresion de FAT.
* 
*************************************************************************/
function imprimirTextoSeleccionado(outputTextFAT) {
	var ficha = document.getElementById(outputTextFAT);
	var ventimp = window.open(' ', 'popimpr');
	ventimp.document.write(ficha.innerHTML);
	ventimp.document.close();
	ventimp.print();
	ventimp.close();
}
