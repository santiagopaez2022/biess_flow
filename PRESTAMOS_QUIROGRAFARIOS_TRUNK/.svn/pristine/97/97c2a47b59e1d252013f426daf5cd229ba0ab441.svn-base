/******************************************************************************************************
 *  Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 *  Todos los derechos reservados.
 * ****************************************************************************************************
 *	Funciones requeridas para simular un teclado en pantalla
 */
/****************************************************************************************************** 
 *											CONTROL DE CAMBIOS
 *	Autor					Fecha					Incidente					Descripci�n del cambio
 *	--------------------------------------------------------------------------------------------------
 *	Luis Ca�izares			31/08/2010				PENDIENTE					Versi�n inicial
 ******************************************************************************************************
 */


/*************************************************************************
* LCANIZARES: Variable para determinar si estan activadas las mayusculas
*************************************************************************/
var mayuscula;
var RUTA_LETRAS_MAYUSCULAS = "/files/images/keyboard/xl";
var RUTA_LETRAS_MINUSCULAS = "/files/images/keyboard/l";
var EXTENSION_IMAGEN = ".png";

/*************************************************************************
* LCANIZARES: Funcion que coloca el valor digitado en un campo oculto 
*             y un asterisco en el campo de password visible
************************************************************************** 
*/
function mascaraPwd(valor) {
	var campo = document.getElementById("campoIdMascara");
	var campoPwd = document.getElementById("campoPwd");
	campo.value = campo.value + "*";
	if(mayuscula == true) {
		campoPwd.value = campoPwd.value + valor.toUpperCase();
	}else {
		campoPwd.value = campoPwd.value + valor;
	}
}

/*************************************************************************
* LCANIZARES: Funcion que borra el campo de password (el oculto y el visible)
*			  y la c�dula 			 
* 
*************************************************************************/
function borrarTodo() {
	var campoCedula = document.getElementById("campoCedula");
	var campoPassword = document.getElementById("campoPwd");
	var campoMascara = document.getElementById("campoIdMascara");
		
	//campoCedula.value = "Cédula";
	campoCedula._haschanged = false;
	campoPassword.value = "";
	campoMascara.value = "";
	campoCedula="Ingrese la cédula";
}

/*************************************************************************
* LCANIZARES: Funcion que simula el backspace del teclado
* 
*************************************************************************/
function borrarCaracter() {
	var campo = document.getElementById("campoIdMascara");
	var campoPwd = document.getElementById("campoPwd");
	if(campo.value.length > 0) {
		campo.value = campo.value.substring(0,campo.value.length-1);
		campoPwd.value = campoPwd.value.substring(0,campoPwd.value.length-1);
	}
	return;
}

/*************************************************************************
* LCANIZARES: Funcion que valida la existencia de una contrase�a pulsada
* 
*************************************************************************/
function validarContrasena(){
	var campoPwd = document.getElementById("campoPwd");
	if(campoPwd.value.length == 0) {
		alert('Por favor ingrese su contrase�a');
		return false;
	}
	return true;
}	

/*************************************************************************
* LCANIZARES: Funcion que simula el Caps Lock del teclado
* 
*************************************************************************/
function cambiarEstadoMayuscula(contextpath){
	if(mayuscula == true){
		mayuscula = false;
		cambiarMinusculasMayusculas(mayuscula, contextpath);		
	} else {
		mayuscula = true;
		cambiarMinusculasMayusculas(mayuscula, contextpath);		
	}
}

/*************************************************************************
* LCANIZARES: Funcion para simular el efecto de pulsi�n sobre el teclado
* 
*************************************************************************/
function restaurarImagen() { 
	  var i,x;
	  a=document.MM_sr; 
	  for(i=0;a && (i < a.length) && (x=a[i])&& x.oSrc;i++) {
		  x.src=x.oSrc;
	  }
}


/*************************************************************************
* LCANIZARES: Funcion que busca una imagen entre frames
* 
*************************************************************************/
function buscarObjeto(n, d) {
	  var p,i,x;  
	  if(!d){ 
		  d=document;
	  }	 
	  if((p=n.indexOf("?"))>0&&parent.frames.length) {
	    d=parent.frames[n.substring(p+1)].document; 
	    n=n.substring(0,p);
	  }
	  if(!(x=d[n])&&d.all) {
		  x=d.all[n]; 
	  }
	  for (i=0;!x&&i<d.forms.length;i++) {
		  x=d.forms[i][n];
	  }
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++){
		   x=buscarObjeto(n,d.layers[i].document);
	  }
	  if(!x && d.getElementById) {
		  x=d.getElementById(n); 
	 }
	  return x;
}

/*************************************************************************************
* LCANIZARES: Funcion que cambia imagenes en el teclado para simular efecto de pulsion
* 
**************************************************************************************/
function cambiarImagen() { 
	  var i,j=0,x,a=cambiarImagen.arguments; 
	  document.MM_sr=new Array; 
	  for(i=0;i<(a.length-2);i+=3) {
	   if ((x=buscarObjeto(a[i]))!=null){
		   document.MM_sr[j++]=x; 
		   if(!x.oSrc) {
			   x.oSrc=x.src; 
		   }
		   x.src=a[i+2];
		}
	  }
}

/*************************************************************************************
* JAVENDANO / LCANIZARES: Funcion que valida el ingreso de un dominio dado de valores
* 						  en un evento de javascript 
* 
* valoresPermitidos: 'car' 		para caracteres
* 					 'num' 		para n�meros enteros
* 					 'dec' 		para n�meros decimales
* 					 'fec' 		para ingresar formatos de fecha 
* 					 'num_car'	para ingresar valores alfanum�ricos
* 
**************************************************************************************/

function permitirValores(elEvento, valoresPermitidos) {
	// Variables que definen los caracteres permitidos
    var copiarPegar = 'cCvV';
    var numeros = '0123456789';
    var caracteres = ' abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1';
    var slash = '/';
    var punto = '.';
    var numerosCaracteres = numeros + caracteres;
    var guion = '-';
   
    var teclasEspeciales = [ 8, 37, 39, 46, 17, 35, 36 ];
    // 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha, 17
    // Control, 36 inicio, 35 fin

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
    case 'num-guion':
    	valoresPermitidos = numeros + guion;
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

    // Comprobar si la tecla pulsada se encuentra en los caracteres valoresPermitidos
    // o si es una tecla especial
    return (valoresPermitidos.indexOf(caracter) != -1 || teclaEspecial); 
}

/*************************************************************************************
* LCANIZARES: Funcion que cambia las im�genes de may�sculas a min�sculas
* 			  esMayusculas: Valor de verdad o falsedad que determina si se cambia de
* 						  mayusculas a minusculas o viceversas (true = a mayusculas),
* 						  (false = a minusculas)
* 
**************************************************************************************/
function cambiarMinusculasMayusculas(esMayusculas, contextpath){
	var Mayus = document.getElementById("imagenShift");
	if(esMayusculas == true){
		var i = 0, imagen;
		for(i = 0; i<26; i++){
			imagen = document.getElementById('l' + i);
			imagen.style.backgroundImage='url(/pq-concesion-web/files/images/keyboard/xl' + i + EXTENSION_IMAGEN+')';
		}
		document.getElementById("imagenShift").style.backgroundImage='url(/pq-concesion-web/files/images/keyboard/minuscula.png)';
		Mayus.value = "Mayusculas Activado";
	} else {
		var j, imagen;
		for(j = 0; j<26; j++){
			imagen = document.getElementById('l' + j);
			imagen.style.backgroundImage='url(/pq-concesion-web/files/images/keyboard/l' + j + EXTENSION_IMAGEN +')';
		}
		document.getElementById("imagenShift").style.backgroundImage='url(/pq-concesion-web/files/images/keyboard/mayuscula.png)';
		Mayus.value = "Mayusculas desactivado";
	}
}

/*************************************************************************
* Omar Villanueva: Funci�n que valida el ingrese de c�dula y contrase�a
* 
*************************************************************************/
function validarCedulaClave() {
	var cedula = document.getElementById("campoCedula");
	var pwd = document.getElementById("campoPwd");	
		
	if (cedula.value.length == 0 || cedula.value.length < 10 || cedula.value.length == "") {
		document.getElementById("textAlertBox").style.visibility = "visible";
		document.getElementById("textAlertBox").innerHTML = "Por favor ingrese un n\u00famero de c\u00e9dula valido.";
		
		return false;
	} else if (pwd.value.length == 0 || pwd.value.length == "") {
		document.getElementById("textAlertBox").style.visibility = "visible";
		document.getElementById("textAlertBox").innerHTML = "Por favor ingrese su contrase\u00f1a.";
		
		return false;
	} else {
		document.getElementById("formaLogin").submit();
	}
}	
