/*
 * Copyright 2013 BIESS - ECUADOR Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required by applicable law or agreed to
 * in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilBiessException;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionRegistroCivil;
import ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicio;
import ec.fin.biess.creditos.pq.servicio.RegistroCivilBiessServicioT;
import ec.fin.biess.enumeraciones.AplicativoEnum;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;
import ec.gov.iess.planillaspq.web.util.DireccionIPUtil;
import ec.gov.iess.planillaspq.web.util.UtilDate;

/**
 * Backing Bean que maneja las consultas de los afilidos con el Registro Civil
 * 
 * @author edwin.maza
 * @date 17/01/2014
 */
public class ConsultaAfiliadoRegistroCivilBacking extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8055665324278439817L;
	private static final LoggerBiess log = LoggerBiess.getLogger(ConsultaAfiliadoRegistroCivilBacking.class);
	@EJB(name = "RegistroCivilBiessServicioImpl/local")
	private transient RegistroCivilBiessServicio registroCivilBiessServicio;
	@EJB(name = "RegistroCivilBiessServicioImplT/local")
	private transient RegistroCivilBiessServicioT registroCivilBiessServicioT;
	private String mensaje = null;
	private String okmensaje = null;
	private String cedulafuncionario = null;
	private Date fechaExpedicion = null;
	private Integer dia;
	private Integer mes;
	private Integer anio;
	private String cedula = null;
	private String codigoDactilar = null;
	private boolean mostrarInformacion;
	private boolean errorCodigoDactilar;
	private boolean errorFechaExpedicion;
	/**
	 * Datos Afiliado Registro Civil
	 */
	private String nombre = null;
	private String fechaNacimiento = null;
	private String genero = null;
	private String estadoCivil = null;
	private String nacionalidad = null;
	private String lugarNacimiento = null;
	private String instruccion = null;
	private String fechaDefuncion = null;
	private String conyugue = null;
	private String direccionDomiciliaria = null;
	private String profesion = null;

	/**
	 * @author edwin.maza
	 * @date 17/01/2014
	 */
	public ConsultaAfiliadoRegistroCivilBacking() {
		super();
	}

	/**
	 * Metodo que realiza la consulta con WS del Registro Civil para validar la informacion ingresada
	 * 
	 * @author edwin.maza
	 * @date 17/01/2014
	 */
	public void consultarInformacion() {
		cleanMessages();
		mensaje = null;
		okmensaje = null;
		if (null != dia && null != mes && null != anio) {
			fechaExpedicion = UtilDate.getDate(dia, mes, anio);
		}
		try {
			if (validarDatos()) {
				okmensaje = null;
				mensaje = "Ingrese los datos requeridos (*)";
			} else {
				// Inicio Auditoria
				ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConfirmacionDatosRC(this.cedula, this.codigoDactilar,
						String.valueOf(this.anio), String.valueOf(this.mes), String.valueOf(this.dia));
				super.guardarAuditoria(OperacionEnum.VALIDACION_REGISTRO_CIVIL, parametroEvento, this.cedula);
				// Fin Auditoria
				
				/**
				 * Consulta WS Registro Civil
				 */
				setMostrarInformacion(true);
				InformacionRegistroCivil registroCivil = new InformacionRegistroCivil();
				registroCivil = registroCivilBiessServicioT.consultarWS(cedula, getHttpServletRequest().getRemoteUser(),
						DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()), AplicativoEnum.PQ_INTRANET);
				/**
				 * Datos Match
				 */
				String codigoDactilar = registroCivil.getCodigoDactilar();
				String fechaExpedicion = registroCivil.getFechaExpedicion();
				/**
				 * Datos Mostrados
				 */
				nombre = registroCivil.getNombre();
				fechaNacimiento = registroCivil.getFechaNacimiento();
				genero = registroCivil.getSexo();
				estadoCivil = registroCivil.getEstadoCivil();
				nacionalidad = registroCivil.getNacionalidad();
				lugarNacimiento =registroCivil.getLugarNacimiento();
				instruccion = registroCivil.getInstruccion();
				fechaDefuncion =registroCivil.getFechaDefuncion();
				conyugue = registroCivil.getConyugue();
				direccionDomiciliaria = registroCivil.getDireccionDomiciliaria();
				profesion = registroCivil.getProfesion();
				/*
				 * Validacion de codigo dactilar y fecha expedicion
				 */
				setErrorCodigoDactilar(!codigoDactilar.trim().equalsIgnoreCase(this.codigoDactilar.trim()));
				setErrorFechaExpedicion(!fechaExpedicion.trim().equalsIgnoreCase(
						UtilDate.dateToString(this.fechaExpedicion, "ddMMyyyy")));
			}
		} catch (RegistroCivilBiessException e) {
			setMostrarInformacion(false);
			okmensaje = null;
			mensaje = e.getMessage();
			log.error(e);
		} catch (Exception e) {
			setMostrarInformacion(false);
			okmensaje = null;
			mensaje = "Error al realizar la consulta, intente mas tarde.";
			log.error(e);
		}
	}
	
	/**
	 * Metodo que limpia los campos de ingreso de datos para realizar una nueva consulta
	 */
	public void limpiar() {
		cleanMessages();
		mostrarInformacion = false;
		cedula = null;
		codigoDactilar = null;
		fechaExpedicion = null;
		dia = null;
		mes = null;
		anio = null;		
		okmensaje = null;
		mensaje = null;
	}

	/**
	 * <Metodo que valida los datos ingresados>
	 * 
	 * @author edwin.maza
	 * @date 10/02/2014
	 * @return <Describir parametro>
	 */
	private boolean validarDatos() {
		return cedula == null || cedula.isEmpty() || codigoDactilar == null || codigoDactilar.isEmpty() || anio == null
				|| mes == null || dia == null;
	}

	// Getters and Setters
	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 *            el mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the okmensaje
	 */
	public String getOkmensaje() {
		return okmensaje;
	}

	/**
	 * @param okmensaje
	 *            el okmensaje
	 */
	public void setOkmensaje(String okmensaje) {
		this.okmensaje = okmensaje;
	}

	/**
	 * @return the cedulafuncionario
	 */
	public String getCedulafuncionario() {
		return cedulafuncionario;
	}

	/**
	 * @param cedulafuncionario
	 *            el cedulafuncionario
	 */
	public void setCedulafuncionario(String cedulafuncionario) {
		this.cedulafuncionario = cedulafuncionario;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            el cedula
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the fechaExpedicion
	 */
	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	/**
	 * @param fechaExpedicion
	 *            el fechaExpedicion
	 */
	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	/**
	 * @return the codigoDactilar
	 */
	public String getCodigoDactilar() {
		return codigoDactilar;
	}

	/**
	 * @param codigoDactilar
	 *            el codigoDactilar
	 */
	public void setCodigoDactilar(String codigoDactilar) {
		this.codigoDactilar = codigoDactilar;
	}

	/**
	 * @return the mostrarInformacion
	 */
	public boolean isMostrarInformacion() {
		return mostrarInformacion;
	}

	/**
	 * @param mostrarInformacion
	 *            el mostrarInformacion
	 */
	public void setMostrarInformacion(boolean mostrarInformacion) {
		this.mostrarInformacion = mostrarInformacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the registroCivilBiessServicio
	 */
	public RegistroCivilBiessServicio getRegistroCivilBiessServicio() {
		return registroCivilBiessServicio;
	}

	/**
	 * @param registroCivilBiessServicio
	 *            el registroCivilBiessServicio
	 */
	public void setRegistroCivilBiessServicio(RegistroCivilBiessServicio registroCivilBiessServicio) {
		this.registroCivilBiessServicio = registroCivilBiessServicio;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento
	 *            el fechaNacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 *            el genero
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            el estadoCivil
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 *            el nacionalidad
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the lugarNacimiento
	 */
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	/**
	 * @param lugarNacimiento
	 *            el lugarNacimiento
	 */
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	/**
	 * @return the instruccion
	 */
	public String getInstruccion() {
		return instruccion;
	}

	/**
	 * @param instruccion
	 *            el instruccion
	 */
	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

	/**
	 * @return the fechaDefuncion
	 */
	public String getFechaDefuncion() {
		return fechaDefuncion;
	}

	/**
	 * @param fechaDefuncion
	 *            el fechaDefuncion
	 */
	public void setFechaDefuncion(String fechaDefuncion) {
		this.fechaDefuncion = fechaDefuncion;
	}

	/**
	 * @return the conyugue
	 */
	public String getConyugue() {
		return conyugue;
	}

	/**
	 * @param conyugue
	 *            el conyugue
	 */
	public void setConyugue(String conyugue) {
		this.conyugue = conyugue;
	}

	/**
	 * @return the direccionDomiciliaria
	 */
	public String getDireccionDomiciliaria() {
		return direccionDomiciliaria;
	}

	/**
	 * @param direccionDomiciliaria
	 *            el direccionDomiciliaria
	 */
	public void setDireccionDomiciliaria(String direccionDomiciliaria) {
		this.direccionDomiciliaria = direccionDomiciliaria;
	}

	/**
	 * @return the profesion
	 */
	public String getProfesion() {
		return profesion;
	}

	/**
	 * @param profesion
	 *            el profesion
	 */
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	/**
	 * @return the errorCodigoDactilar
	 */
	public boolean isErrorCodigoDactilar() {
		return errorCodigoDactilar;
	}

	/**
	 * @param errorCodigoDactilar
	 *            el errorCodigoDactilar
	 */
	public void setErrorCodigoDactilar(boolean errorCodigoDactilar) {
		this.errorCodigoDactilar = errorCodigoDactilar;
	}

	/**
	 * @return the errorFechaExpedicion
	 */
	public boolean isErrorFechaExpedicion() {
		return errorFechaExpedicion;
	}

	/**
	 * @param errorFechaExpedicion
	 *            el errorFechaExpedicion
	 */
	public void setErrorFechaExpedicion(boolean errorFechaExpedicion) {
		this.errorFechaExpedicion = errorFechaExpedicion;
	}

	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}

	/**
	 * @param dia
	 *            el dia
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}

	/**
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * @param mes
	 *            el mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio
	 *            el anio
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
}