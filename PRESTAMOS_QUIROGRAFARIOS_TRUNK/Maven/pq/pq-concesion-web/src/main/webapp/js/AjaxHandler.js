/******************************************************************************************************
 *  Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 *  Todos los derechos reservados.
 * ****************************************************************************************************
 *	Funciones para invocaciones asíncronas AJAX desde una JSP
 */
/****************************************************************************************************** 
 *											CONTROL DE CAMBIOS
 *	Autor					Fecha					Incidente					Descripción del cambio
 *	--------------------------------------------------------------------------------------------------
 *	Luis Cañizares			30/10/2010				INC-5375					Versión inicial
 *
 ******************************************************************************************************
 */
var http = crearRequest();
var zonaRespuesta

/*************************************************************************
* LCANIZARES: Crea un objeto request para exploradores que soporten AJAX
*************************************************************************/
function crearRequest(){
	var request;
	if(window.XMLHttpRequest){
		
		request = new XMLHttpRequest();
		}
		else if(window.ActiveXObject){
		//Para IE5x
		request = new ActiveXObject("Microsoft.XMLHTTP");
		}
	return request;
}

/*************************************************************************
* LCANIZARES: Envia un objeto request para exploradores que soporten AJAX
*    		 metodo:	Comando HTTP (GET, POST)
*     			url: 	Destino del request
* zonaRespuestaAjax:	Ubicación donde colocar la respuesta       	 
*************************************************************************/
function enviarRequest(metodo, url, zonaRespuestaAjax){
	if(metodo == 'post' || metodo == 'POST'){
		zonaRespuesta = zonaRespuestaAjax;
		http.open(metodo,url,true);
		http.onreadystatechange = manejarRespuesta;
		http.send();
		
	}
}

/*************************************************************************
* LCANIZARES: Extrae el contenido cuando la respuesta es OK
*************************************************************************/
function manejarRespuesta(){
	if(http.readyState == 4 && http.status == 200){
		var response = http.responseText;
		if(response){
			document.getElementById(zonaRespuesta).innerHTML = response;
			}
	}
}