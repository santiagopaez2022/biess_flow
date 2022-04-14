<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="javax.jms.Session"%>
<%@ page import="java.util.Random"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:a4j="http://richfaces.org/a4j">

	<head>
		<% final String CONTEXT_PATH = request.getContextPath(); %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>;url=<%= CONTEXT_PATH %>/pages/index.jsp"/>
		<title>
			Pr&#233;stamos Quirografarios | Biess
		</title>

		<!-- Estilos generales -->
		<link href="<%= CONTEXT_PATH %>/files/css/styles.css" rel="stylesheet" type="text/css" />

		<!-- MultiBox para manual de teclado. Mas información en http://phatfusion.net/multibox/-->
		<link href="<%= CONTEXT_PATH %>/css/manual/multibox.css" rel="stylesheet" type="text/css" />
		
		<!-- Stilos para popUp inicial-->
		<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/css/basic_ie.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/css/basic.css" media="screen" />

		<!-- Scripts para el despliegue del manual del teclado -->
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/manual/mootools.js"></script> 
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/manual/overlay.js"></script> 
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/manual/multibox.js"></script> 
	 	<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/manual/CreacionMultibox.js"></script>
	
		<!-- Script de funciones que simulan el teclado virtual -->
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/teclado/TecladoVirtual.js"></script> 
		
		<!-- Script de para poder lanzar el popUp -->
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/Jquery.js"></script>		
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/popUp/basic.js"></script>
		<script type="text/javascript" src="<%= CONTEXT_PATH %>/js/popUp/jquery.simplemodal.js"></script>

		<%!
			//final String CONTEXT_PATH = request.getContextPath();
			/** Declaración de constantes para las rutas relativas hacia las imagenes */
			private static final String RUTA_NUMEROS = "/files/images/keyboard/n";
			private static final String RUTA_LETRAS = "/files/images/keyboard/l";
			private static final String EXTENSION_IMAGEN = ".png";
			private static final String MINUSCULAS = "l";
			private static final String NUMEROS = "n";			
			
			/** Declaración y generación de los vectores que darán la posición de las letras y los números en el teclado */
			private Random generador = new Random();		
			private int indiceTemporal = -1;			
			/** Se guarda posicion y letra */
			private String[] valorLetras = new String[26];
			
			/**
			 * Método que genera un vector de enteros aleatorios y de un tamaño dado
			 *  
			 * @author LCANIZARES
			 * @param int Tamaño del vector
			 * @return int[] Vector de enteros aleatoriamente dispuestos
			 */
			private int[] generarVector(int tamanio) {
				int numero;
				int[] vector = new int[tamanio];
				
				/** Relleno inicial del vector */
				for (int i = 0; i < vector.length; i++) {
					vector[i] = -1;
				}
				/** Relleno aleatorio */
				for (int i = 0; i < vector.length; i++) {
					do {
						numero = generarNumeroRandomico(tamanio);
					} while (yaExiste(vector, numero));
					vector[i] = numero;
				}
				
				return vector;
			} /** Fin método generarVector */
		
			/**
			 * Método que verifica la existencia de un entero dado dentro de un vector
			 * 
			 * @author LCANIZARES
			 * @param int[] Vector de n miembros
			 * @param int Numero que se verifica dentro del vector
			 * @return boolean Condición de verdad de existencia del numero 
			 */
			private boolean yaExiste(int[] vector, int numero) {
				for (int i = 0; i < vector.length; i++) {
					if (vector[i] == numero) {
						return true;
					}
				}
				
				return false;
			} /** Fin método yaExiste */
					
			/**
			 * Método que genera un número aleatorio 
			 * 
			 * @author LCANIZARES
			 * @param int Mayor número posible que puede ser generado 
			 * @return int Número aleatorio
			 */
			private int generarNumeroRandomico(int tamanio) {
				int numeroRandomico;
				numeroRandomico = generador.nextInt();
				numeroRandomico = Math.abs(numeroRandomico);
				numeroRandomico = numeroRandomico % tamanio;
				
				return numeroRandomico++;		
			} /** Fin método generarNumeroRandomico */
		
			/**
			 * Método que genera un vector de letras de la A la Z
			 * 
			 * @author LCANIZARES
			 */
			private void iniciarValoresLetras() {		
				for (int i = 0; i < valorLetras.length; i++) {
					valorLetras[i] = Character.toString((char) (97 + i));
				}
			} /** Fin método iniciarValoresLetras */			
		%>
	</head>
 
	<body oncontextmenu="return false;" onload="document.getElementById('campoPwd').value=''; 
		document.getElementById('campoCedula').placeholder='Ingrese la c&#233;dula'; mayuscula = false;">	
		
		<div id="top">
			<div id="toplinks">
				<a href="mailto:ayudaprestamos@biess.fin.ec">&#191;Necesita ayuda&#63;</a>
			</div>
		</div>

		<div id="bodywrapper"> 
		  <!-- HEADER --> 
		  <div id="header">
		    <h1>Pr&#233;stamos Quirografarios | Biess</h1>
		  </div>
		  
		  <div id="content">
		    <div id="contentwrapper">
		      <div id="leftcollogin"> </div>		      		      
			      <!-- COLUMNA DERECHA -->
			      <div id="rightcollogin">


				        <!-- MODULO DE LOGIN -->
				        <div id="loginmodulewrapper">
				        <form id="formaLogin" action="j_security_check" method="post" onsubmit="return validarCedulaClave();">
				          <div class="form">
				            <div class="loginformwrapper">
				              	<input type="password" style="display: none" id="campoPwd" name="j_password" />				              
				                <input name="j_username" type="text" class="inputarea" id="campoCedula" placeholder="Ingrese la c&#233;dula"  style="width:150px;" 
				                	onkeypress="return permitirValores(event, 'num')" maxlength="10" 
				                	onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;" />
				                <br class="br" />
				                <input name="clave" type="password" class="inputarea" id="campoIdMascara" placeholder="Ingrese la contraseña";
				                	style="width:150px;" readonly="readonly" />			                
				            </div>
				          </div>
				          
				          <!-- CONTENEDOR DE INFORMACION -->
				          <div class="info"> <img src="<%= CONTEXT_PATH %>/files/images/content/title-info.png" width="125" height="18" alt="Información" /><br />
				            <p class="smallfont">Utilice el teclado virtual para digitar su contraseña. Si quiere ver su uso, haga click en el botón <strong>Manual Teclado</strong>.</p>
				            <!-- CONTENEDOR DE ALERTAS (class="alertbox"). Debe estar oculto, hasta presentarse un error en los datos de login ingresados. -->		            
				           
				          </div>
				          					          
				          <!-- CONTENEDOR TECLADO -->
				          <div id="keyboard">
				            <table border="0" cellspacing="0" cellpadding="3">
								<%
									/** Generación de vector de números aleatorios y sus posiciones */
									int[] vectorRandomico = generarVector(10);
									int[] vectorPosiciones = generarVector(10);
									/** Generacion de vector de letras  y sus posiciones */
									int[] vectorRandomicoLetras = generarVector(26);
									int[] vectorPosicionesLetras = generarVector(26);
									/** Generar letras de la A la Z */									
									iniciarValoresLetras();
								%>
							  <!-- Primera fila -->
				              <tr>
								  <%
									  for (int i = 0; i <= 7; i++) {
										  indiceTemporal = vectorRandomicoLetras[vectorPosicionesLetras[i]];
										  String a=valorLetras[indiceTemporal].toString();
										  if(a.equals("y") || a.equals("Y")){
								  %>
								  		<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="y griega"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
				                		<% }else{ %>
										<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="<%=valorLetras[indiceTemporal]%>"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
								  <%
									  }}
								  %>
				                		<td></td>
								  <%
									  for (int j = 0; j <= 4; j++) {
										  indiceTemporal = vectorRandomico[vectorPosiciones[j]];
								  %>				              
				                		<td>
				                		<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			border:0; background-image: url(<%=CONTEXT_PATH + RUTA_NUMEROS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="<%=indiceTemporal%>"
				                			onclick="<%="mascaraPwd(\'" + indiceTemporal + "\')"%>"
				                			id=<%=NUMEROS + indiceTemporal%>></input>
				                		</td>
								  <%
									  }
								  %>
				              </tr>
				              <!-- Segunda fila -->
				              <tr>
								  <%
									  for (int i = 8; i <= 15; i++) {
										  indiceTemporal = vectorRandomicoLetras[vectorPosicionesLetras[i]];
										  String a=valorLetras[indiceTemporal].toString();
										  if(a.equals("y") || a.equals("Y")){
								  %>
								  		<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="y griega"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
				                		<% }else{ %>
										<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="<%=valorLetras[indiceTemporal]%>"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
								  <%
									  }}
								  %>
				                		<td></td>
								  <%
									  for (int j = 5; j <= 9; j++) {
										  indiceTemporal = vectorRandomico[vectorPosiciones[j]];
								  %>				              
				                		<td>
				                		<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			border:0; background-image: url(<%=CONTEXT_PATH + RUTA_NUMEROS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="<%=indiceTemporal%>"
				                			onclick="<%="mascaraPwd(\'" + indiceTemporal + "\')"%>"
				                			id=<%=NUMEROS + indiceTemporal%>></input>
				                		</td>
								  <%
									  }
								  %>
				              </tr>
				              <!-- Tercera fila -->
				              <tr>
								  <%
									  for (int i = 16; i <= 23; i++) {
										  indiceTemporal = vectorRandomicoLetras[vectorPosicionesLetras[i]];
								   String a=valorLetras[indiceTemporal].toString();
										  if(a.equals("y") || a.equals("Y")){
								  %>
								  		<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="y griega"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
				                		<% }else{ %>
										<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="<%=valorLetras[indiceTemporal]%>"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
								  <%
									  }}
								  %>
				                		<td></td>
				                		<td>&nbsp;</td>
				                		<td colspan="4">
				                			<a href="<%= CONTEXT_PATH %>/swf/flashTeclado.swf" id="mb4" class="mb" rel="width:700,height:450" >
				                				<img src="<%= CONTEXT_PATH %>/files/images/keyboard/btn-manualteclado.png" 
				                					alt="Manual del teclado" width="120" height="26" border="0" />
				                			</a>
				                		</td>
				              </tr>
				              <!-- Cuarta fila -->
				              <tr>
								  <%
									  for (int i = 24; i <= 25; i++) {
										  indiceTemporal = vectorRandomicoLetras[vectorPosicionesLetras[i]];
										  String a=valorLetras[indiceTemporal].toString();
										  if(a.equals("y") || a.equals("Y")){
								  %>
								  		<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="y griega"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
				                		<% }else{ %>
										<td>
											<input type="button" style="cursor: pointer; width : 26px; heigth : 26px; background-color:   #1375c6  ; color: white; color:transparent;
				                			 border:0; background-image: url(<%=CONTEXT_PATH + RUTA_LETRAS + indiceTemporal + EXTENSION_IMAGEN%>);"
				                			value="<%=valorLetras[indiceTemporal]%>"
				                			onclick="<%="mascaraPwd(\'" + valorLetras[indiceTemporal] + "\')"%>"
				                			id=<%=MINUSCULAS + indiceTemporal%>></input>
				                		</td>
								  <%
									  }}
								  %>
				                		<td colspan="2">
				                			<input id="imagenShift" type="button"   style="cursor: pointer;  width:58px; height:26px; border:0; color:transparent;
				                			background-image: url(<%= CONTEXT_PATH %>/files/images/keyboard/mayuscula.png);" 
				                			onclick="cambiarEstadoMayuscula('<%= CONTEXT_PATH %>')" value="Mayusculas desactivado"  >	
				                			</input>		                			
				                		</td>
				                		<td>
				                			
				                		</td>				                
				                		<td>
				                			<input type="button"   style="cursor: pointer;  width:26px; height:26px; border:0; color:transparent;
				                			background-image: url(<%= CONTEXT_PATH %>/files/images/keyboard/regresar.png);" 
				                			onclick="borrarCaracter();" value="Borrar un caractér"  >	                				
				                			</input>
				                		</td>
				                		<td colspan="2">				                		
				                			<input type="button"   style="cursor: pointer;  width:58px; height:26px; border:0; color:transparent;
				                			background-image: url(<%= CONTEXT_PATH %>/files/images/keyboard/borrar.png);" 
				                			onclick="borrarTodo();" value="Borrar contraseña"  >	                				
				                			</input>
				                		</td>
				                		<td></td>
				                		<td></td>
				                		<td colspan="4"><input name="" type="submit" class="large button yellow" value="Ingresar"  alt="Ingresar" /></td>
				                		
				              </tr>
				            </table>
				          </div>
				          <div id="loginmodulewrapperMsj">	          
				          <div class="infoLoginMsj">
				          		<!-- MSG ERROR LOGIN -->
				            <% if (null != session.getAttribute("egassemrorrenigol")) { %>
				            	<div id="textAlertBox" class="alertbox">
				            	<%= session.getAttribute("egassemrorrenigol") %>
					          	<% if (session.getAttribute("dekcolblaitrap") != null) { %>
					            	<br />
					            	<br />
					            	<a href="<%= response.encodeURL(CONTEXT_PATH + "/unlock/emailUnlock.jsp") %>">Click aqu&#237; para desbloquear el acceso al aplicativo web de pr&#233;stamos.</a>
				            	<% } %>		
				            	</div>		            	
							<% } %>							
				            <!-- MSG CODIGO DESBLOQUEO ENVIADO -->
				          	<% if (null != session.getAttribute("egassemofninigol")) { %>
				            	<div id="textInfoBox" class="infoboxsmalllogin"><%= session.getAttribute("egassemofninigol") %></div>
				            <% } %>				            
				            <% if (null != session.getAttribute("interruptedSessionMsg")) { %>
				            	<div id="textLogoutBox" class="alertbox" style="font-size:9.5px!important; line-height: 1.2em!important;" class="infoboxsmalllogin"><%= session.getAttribute("interruptedSessionMsg") %></div>
				            <% } %>			            				            
				            
				          </div>
				          </div>
				          </form>
				        </div>		          
		          
		          </div> <!-- Fin rightcollogin -->
		    </div>
		  </div>		    

		  <div id="basic-modal-content">
			<h3 align="center" style="color: red; font-size: 18px;">IMPORTANTE</h3>
			<p
				style="text-align: justify; margin: 4px 4px 4px 4px; color: black; font-size: 18px;">
				Por su seguridad, el BIESS ha implementado nuevos controles en este	proceso.
				Le recomendamos que durante la generaci&#243;n del Cr&#233;dito Quirografario tenga acceso a su 
				correo electr&#243;nico personal registrado en Historia Laboral del IESS.
			</p>
		  </div>
		  
		  <!-- LIBRERIA DEL FOOTER (FOOTER.LIB) --> 
		  <!-- #BeginLibraryItem "/bancalinea/ph/files/Library/footer.lbi" -->
		  <div id="footer">
		    <div id="footertext">
		    	&#169; Banco del Instituto Ecuatoriano de Seguridad Social
		    </div>
		  </div>
	  	  <!-- #EndLibraryItem -->
	  	
	  	</div>
	</body>
</html>