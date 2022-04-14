/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.planillaspq.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.fin.biess.creditos.pq.modelo.dto.InformacionIessServicioDto;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.listaobservados.modelo.persistence.BloqueoListaControl;
import ec.gov.iess.creditos.enumeracion.CategoriaTipoProductoPq;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.dto.DatosOrdenCompra;
import ec.gov.iess.creditos.modelo.dto.Garantia;
import ec.gov.iess.creditos.modelo.dto.InstitucionFinanciera;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.dto.ValidarRequisitosPrecalificacion;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancaria;

/**
 * 
 * <b> Datos del solicitante. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.7 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/11 14:04:30 $]
 *          </p>
 */
public class DatosBean implements Serializable {

	private static final long serialVersionUID = -6207571267999738489L;

	private Solicitante solicitante;
	private boolean actualizado = false;
	private TipoPrestamista tipo;
	private boolean prestamoConcedido = false;
	private boolean prestamoEnProceso = false;
	private List<TipoPrestamista> roles = new ArrayList<TipoPrestamista>();
	private DatosGarantia datosGarantia = new DatosGarantia();
	private boolean rolesCargados = false;
	private boolean creditoNovacion = false;
	private DatosOrdenCompra datosOrdenCompra;
	private Garantia garantia;
	private ValidarRequisitosPrecalificacion valReqPrecalificacion;
	// Validacion RC
	private String anio;
	private String mes;
	private String dia;
	private String repAnio;
	private String repMes;
	private String repDia;
	private String codigoDactilar;
	private String repCodigoDactilar;
	private boolean respuesta = false;
	private boolean cambiarBoton = false;
	private boolean aceptaDatos = false;
	private boolean datosCorrecto = false;
	private boolean errorRC = false;
	//Validacion Ama de casa
	private boolean esAmaDeCasa = false;
	private boolean prestamoVigenteAmaCasa = false;
	private List<Prestamo> listaPrestamosERC;
	/* INC-1800. Control PDA para jubilados */
	private List<PrestamoBiess> listaPrestamosECC;
	private Date fechaExpedicion;

	private BigDecimal cuotaEstimadaMensualBuro;

	// Dato para la validacion de registro civil en novacion
	private boolean registroCivilNovacion = false;
	// Suma de lo saldos vigentes
	private BigDecimal sumaSaldoVigente;
	private BigDecimal montoMaximoCredito;

	// Prestamo Novacion Seleccionada
	private Prestamo prestamoNovacionSeleccionado;

	private boolean novacionCredito;

	/* INC-1719 2014-02-19 Agregar preferencias para afiliados discapacitados */
	private Boolean discapacitado = new Boolean(false);
	

	/* INC-1817 Notificaciones asegurados */
	private String emailDb;
	/* INC-2005 Se consideran creditos segun tipo de rol para asegurados afiliados-jubilados*/
	private TipoPrestamista rolPrestamista;
	/* Indica si el proceso de planillas se esta ejecutando */
	private boolean procesoPlanillasRunning;
	/* Indica si el reajuste de tasas de interes se esta ejcutando */
	private boolean reajusteTasaRunning;

	private String mensajePrestacionJubilado;
	private String mensajeValorNegativo;
	
	//INC-2272 Pensiones Alimenticias
	private TiposProductosPq tiposProductosPq;
	
	private CategoriaTipoProductoPq categoriaTipoProductoPq;

	private BeneficiarioCredito beneficiarioCredito;
	
	private CuentaBancaria cuentaBancariaBeneficiarioCredito;
	
	private boolean pagoPensionesAlimenticias = false;
	
	//INC-2148 Refugiados.
	private boolean beneficiarioRefugiado;
		
	private String tipoBeneficiario;
	//INC-2286 Ferrocarriles -Proveedores
	private InstitucionFinanciera institucionFinancieraProveedor;
	
	// INC-2350 Implementacion automatizada de verificacion en lista de control de usuarios PQ.
	private boolean enListaObservadosPEP;
	
	private BloqueoListaControl bloqueoListaControl;
	
	// Bloqueo novacion
	private String diaBloqEnNov;
	private String diaBloqDic; 
	
	// INC-2460 Conozca a su cliente
	private Date fechaUltimaActualizacion;
	private boolean mostrarBotonConcesion = false;
	private boolean mostrarBotonNovacion = false;
	
	/* Guarda informaci�n del proveedor para los creditos categoria servicios */
	private Proveedor proveedor;
	
	// Identifica que el producto seleccionado sea Biess Emergente
	private boolean productoBiessEmergente;
	// Bandera que indica si el producto de biess emergente esta vigente
	private boolean productoBiessEmergenteVigente;
	
	private boolean bloqueoFinesSemana;
	
	private Long idProveedorMeer;
	
	private int plazo;
	
	private InformacionIessServicioDto informacionIessServicio;
	
	private String mascaraEmail;
	
	private String mascaraCelular;
	
	private boolean productoProductivo;
	// CREDITO MUJER
	// Bandera que indica si el producto de biess mujer esta vigente
	private boolean productoBiessMujerVigente;
	
	// CREDITO MUJER
	// Bandera que indica si el producto de biess mujer esta vigente
	private boolean productoBiessMamaVigente;
	private boolean productoBiessMamaSeleccionado;
	
	private String flujoProceso; 
	
	public boolean isEsAmaDeCasa() {
		return esAmaDeCasa;
	}

	public void setEsAmaDeCasa(boolean esAmaDeCasa) {
		this.esAmaDeCasa = esAmaDeCasa;
	}

	public boolean isPrestamoVigenteAmaCasa() {
		return prestamoVigenteAmaCasa;
	}

	public void setPrestamoVigenteAmaCasa(boolean prestamoVigenteAmaCasa) {
		this.prestamoVigenteAmaCasa = prestamoVigenteAmaCasa;
	}

	public boolean isNovacionCredito() {
		return novacionCredito;
	}

	public void setNovacionCredito(boolean novacionCredito) {
		this.novacionCredito = novacionCredito;
	}

	public Prestamo getPrestamoNovacionSeleccionado() {
		return prestamoNovacionSeleccionado;
	}

	public void setPrestamoNovacionSeleccionado(
			Prestamo prestamoNovacionSeleccionado) {
		this.prestamoNovacionSeleccionado = prestamoNovacionSeleccionado;
	}

	public BigDecimal getMontoMaximoCredito() {
		return montoMaximoCredito;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public void setMontoMaximoCredito(BigDecimal montoMaximoCredito) {
		this.montoMaximoCredito = montoMaximoCredito;
	}

	public boolean isCambiarBoton() {
		return cambiarBoton;
	}

	public void setCambiarBoton(boolean cambiarBoton) {
		this.cambiarBoton = cambiarBoton;
	}

	/**
	 * @return the prestamoConcedido
	 */
	public boolean isPrestamoConcedido() {
		return prestamoConcedido;
	}

	/**
	 * @param prestamoConcedido
	 *            the prestamoConcedido to set
	 */
	public void setPrestamoConcedido(boolean prestamoConcedido) {
		this.prestamoConcedido = prestamoConcedido;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isActualizado() {
		return actualizado;
	}

	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}

	public TipoPrestamista getTipo() {
		return tipo;
	}

	public void setTipo(TipoPrestamista tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the roles
	 */
	public List<TipoPrestamista> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<TipoPrestamista> roles) {
		this.roles = roles;
	}

	public DatosGarantia getDatosGarantia() {
		return datosGarantia;
	}

	public void setDatosGarantia(DatosGarantia datosGarantia) {
		this.datosGarantia = datosGarantia;
	}

	public boolean isRolesCargados() {
		return rolesCargados;
	}

	public void setRolesCargados(boolean rolesCargados) {
		this.rolesCargados = rolesCargados;
	}

	public boolean isCreditoNovacion() {
		return creditoNovacion;
	}

	public void setCreditoNovacion(boolean creditoNovacion) {
		this.creditoNovacion = creditoNovacion;
	}

	public DatosOrdenCompra getDatosOrdenCompra() {
		return datosOrdenCompra;
	}

	public void setDatosOrdenCompra(DatosOrdenCompra datosOrdenCompra) {
		this.datosOrdenCompra = datosOrdenCompra;
	}

	/**
	 * @return the cuotaEstimadaMensualBuro
	 */
	public BigDecimal getCuotaEstimadaMensualBuro() {
		return cuotaEstimadaMensualBuro;
	}

	/**
	 * @param cuotaEstimadaMensualBuro
	 *            the cuotaEstimadaMensualBuro to set
	 */
	public void setCuotaEstimadaMensualBuro(BigDecimal cuotaEstimadaMensualBuro) {
		this.cuotaEstimadaMensualBuro = cuotaEstimadaMensualBuro;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public ValidarRequisitosPrecalificacion getValReqPrecalificacion() {
		return valReqPrecalificacion;
	}

	public void setValReqPrecalificacion(
			ValidarRequisitosPrecalificacion valReqPrecalificacion) {
		this.valReqPrecalificacion = valReqPrecalificacion;
	}

	public String getCodigoDactilar() {
		return codigoDactilar;
	}

	public void setCodigoDactilar(String codigoDactilar) {
		if (codigoDactilar != null) {
			this.codigoDactilar = codigoDactilar.toUpperCase();
		} else {
			this.codigoDactilar = codigoDactilar;
		}
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getRepAnio() {
		return repAnio;
	}

	public void setRepAnio(String repAnio) {
		this.repAnio = repAnio;
	}

	public String getRepMes() {
		return repMes;
	}

	public void setRepMes(String repMes) {
		this.repMes = repMes;
	}

	public String getRepDia() {
		return repDia;
	}

	public void setRepDia(String repDia) {
		this.repDia = repDia;
	}

	public String getRepCodigoDactilar() {
		return repCodigoDactilar;
	}

	public void setRepCodigoDactilar(String repCodigoDactilar) {
		if (repCodigoDactilar != null) {
			this.repCodigoDactilar = repCodigoDactilar.toUpperCase();
		} else {
			this.repCodigoDactilar = repCodigoDactilar;
		}
	}

	/**
	 * Setea aceptaDatos
	 * 
	 * @param aceptaDatos
	 */
	public void setAceptaDatos(boolean aceptaDatos) {
		this.aceptaDatos = aceptaDatos;
	}

	/**
	 * retorna aceptaDatos
	 * 
	 * @return
	 */
	public boolean isAceptaDatos() {
		return aceptaDatos;
	}

	/**
	 * Setea datosCorrecto
	 * 
	 * @param datosCorrecto
	 */
	public void setDatosCorrecto(boolean datosCorrecto) {
		this.datosCorrecto = datosCorrecto;
	}

	/**
	 * Retorna datosCorrecto
	 * 
	 * @return
	 */
	public boolean isDatosCorrecto() {
		return datosCorrecto;
	}

	/**
	 * @param errorRC the errorRC to set
	 */
	public void setErrorRC(boolean errorRC) {
		this.errorRC = errorRC;
	}

	/**
	 * @return the errorRC
	 */
	public boolean isErrorRC() {
		return errorRC;
	}

	/**
	 * Setea listaPrestamosERC
	 * 
	 * @param listaPrestamosERC
	 */
	public void setListaPrestamosERC(List<Prestamo> listaPrestamosERC) {
		this.listaPrestamosERC = listaPrestamosERC;
	}

	/**
	 * Retorna listaPrestamosERC
	 * 
	 * @return
	 */
	public List<Prestamo> getListaPrestamosERC() {
		return listaPrestamosERC;
	}

	/**
	 * @return the listaPrestamosECC
	 */
	public List<PrestamoBiess> getListaPrestamosECC() {
		return listaPrestamosECC;
	}

	/**
	 * @param listaPrestamosECC the listaPrestamosECC to set
	 */
	public void setListaPrestamosECC(List<PrestamoBiess> listaPrestamosECC) {
		this.listaPrestamosECC = listaPrestamosECC;
	}

	public boolean isRegistroCivilNovacion() {
		return registroCivilNovacion;
	}

	public void setRegistroCivilNovacion(boolean registroCivilNovacion) {
		this.registroCivilNovacion = registroCivilNovacion;
	}

	public BigDecimal getSumaSaldoVigente() {
		return sumaSaldoVigente;
	}

	public void setSumaSaldoVigente(BigDecimal sumaSaldoVigente) {
		this.sumaSaldoVigente = sumaSaldoVigente;
	}

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	/**
	 * @param emailDb the emailDb to set
	 */
	public void setEmailDb(String emailDb) {
		this.emailDb = emailDb;
	}

	/**
	 * @return the emailDb
	 */
	public String getEmailDb() {
		return emailDb;
	}

	/**
	 * @param discapacitado the discapacitado to set
	 */
	public void setDiscapacitado(Boolean discapacitado) {
		this.discapacitado = discapacitado;
	}

	/**
	 * @return the discapacitado
	 */
	public Boolean getDiscapacitado() {
		return discapacitado;
	}
	
	/**
	 * @param rolPrestamista the rolPrestamista to set
	 */
	public void setRolPrestamista(TipoPrestamista rolPrestamista) {
		this.rolPrestamista = rolPrestamista;
	}

	/**
	 * @return the rolPrestamista
	 */
	public TipoPrestamista getRolPrestamista() {
		return rolPrestamista;
	}

	/**
	 * @return the procesoPlanillasRunning
	 */
	public boolean isProcesoPlanillasRunning() {
		return procesoPlanillasRunning;
	}

	/**
	 * @param procesoPlanillasRunning the procesoPlanillasRunning to set
	 */
	public void setProcesoPlanillasRunning(boolean procesoPlanillasRunning) {
		this.procesoPlanillasRunning = procesoPlanillasRunning;
	}

	/**
	 * @return the reajusteTasaRunning
	 */
	public boolean isReajusteTasaRunning() {
		return reajusteTasaRunning;
	}

	/**
	 * @param reajusteTasaRunning the reajusteTasaRunning to set
	 */
	public void setReajusteTasaRunning(boolean reajusteTasaRunning) {
		this.reajusteTasaRunning = reajusteTasaRunning;
	}

	/**
	 * @return the mensajePrestacionJubilado
	 */
	public String getMensajePrestacionJubilado() {
		return mensajePrestacionJubilado;
	}

	/**
	 * @param mensajePrestacionJubilado the mensajePrestacionJubilado to set
	 */
	public void setMensajePrestacionJubilado(String mensajePrestacionJubilado) {
		this.mensajePrestacionJubilado = mensajePrestacionJubilado;
	}

	/**
	 * @return the mensajeValorNegativo
	 */
	public String getMensajeValorNegativo() {
		return mensajeValorNegativo;
	}

	/**
	 * @param mensajeValorNegativo the mensajeValorNegativo to set
	 */
	public void setMensajeValorNegativo(String mensajeValorNegativo) {
		this.mensajeValorNegativo = mensajeValorNegativo;
	}

	/**
	 * @return the tiposProductosPq
	 */
	public TiposProductosPq getTiposProductosPq() {
		return tiposProductosPq;
	}

	/**
	 * @param tiposProductosPq the tiposProductosPq to set
	 */
	public void setTiposProductosPq(TiposProductosPq tiposProductosPq) {
		this.tiposProductosPq = tiposProductosPq;
	}

	/**
	 * @return the categoriaTipoProductoPq
	 */
	public CategoriaTipoProductoPq getCategoriaTipoProductoPq() {
		return categoriaTipoProductoPq;
	}

	/**
	 * @param categoriaTipoProductoPq the categoriaTipoProductoPq to set
	 */
	public void setCategoriaTipoProductoPq(
			CategoriaTipoProductoPq categoriaTipoProductoPq) {
		this.categoriaTipoProductoPq = categoriaTipoProductoPq;
	}
	
	/**
	 * @return the beneficiarioCredito
	 */
	public BeneficiarioCredito getBeneficiarioCredito() {
		return beneficiarioCredito;
	}

	/**
	 * @param beneficiarioCredito the beneficiarioCredito to set
	 */
	public void setBeneficiarioCredito(BeneficiarioCredito beneficiarioCredito) {
		this.beneficiarioCredito = beneficiarioCredito;
	}

	/**
	 * @return the cuentaBancariaBeneficiarioCredito
	 */
	public CuentaBancaria getCuentaBancariaBeneficiarioCredito() {
		return cuentaBancariaBeneficiarioCredito;
	}

	/**
	 * @param cuentaBancariaBeneficiarioCredito the cuentaBancariaBeneficiarioCredito to set
	 */
	public void setCuentaBancariaBeneficiarioCredito(
			CuentaBancaria cuentaBancariaBeneficiarioCredito) {
		this.cuentaBancariaBeneficiarioCredito = cuentaBancariaBeneficiarioCredito;
	}

	/**
	 * @return the pagoPensionesAlimenticias
	 */
	public boolean isPagoPensionesAlimenticias() {
		return pagoPensionesAlimenticias;
	}

	/**
	 * @param pagoPensionesAlimenticias the pagoPensionesAlimenticias to set
	 */
	public void setPagoPensionesAlimenticias(boolean pagoPensionesAlimenticias) {
		this.pagoPensionesAlimenticias = pagoPensionesAlimenticias;
	}

	/**
	 * @return the beneficiarioRefugiado
	 */
	public boolean isBeneficiarioRefugiado() {
		return beneficiarioRefugiado;
	}

	/**
	 * @param beneficiarioRefugiado the beneficiarioRefugiado to set
	 */
	public void setBeneficiarioRefugiado(boolean beneficiarioRefugiado) {
		this.beneficiarioRefugiado = beneficiarioRefugiado;
	}

	/**
	 * @return the tipoBeneficiario
	 */
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	/**
	 * @param tipoBeneficiario the tipoBeneficiario to set
	 */
	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	/**
	 * @return InstitucionFinanciera
	 */
	public InstitucionFinanciera getInstitucionFinancieraProveedor() {
		return institucionFinancieraProveedor;
	}

	/**
	 * @param institucionFinancieraProveedor
	 */
	public void setInstitucionFinancieraProveedor(InstitucionFinanciera institucionFinancieraProveedor) {
		this.institucionFinancieraProveedor = institucionFinancieraProveedor;
	}

	/**
	 * @return the enListaObservadosPEP
	 */
	public boolean isEnListaObservadosPEP() {
		return enListaObservadosPEP;
	}

	/**
	 * @param enListaObservadosPEP the enListaObservadosPEP to set
	 */
	public void setEnListaObservadosPEP(boolean enListaObservadosPEP) {
		this.enListaObservadosPEP = enListaObservadosPEP;
	}

	/**
	 * @return the bloqueoListaControl
	 */
	public BloqueoListaControl getBloqueoListaControl() {
		return bloqueoListaControl;
	}

	/**
	 * @param bloqueoListaControl the bloqueoListaControl to set
	 */
	public void setBloqueoListaControl(BloqueoListaControl bloqueoListaControl) {
		this.bloqueoListaControl = bloqueoListaControl;
	}	

	public String getDiaBloqEnNov() {
		return diaBloqEnNov;
	}

	public void setDiaBloqEnNov(String diaBloqEnNov) {
		this.diaBloqEnNov = diaBloqEnNov;
	}

	public String getDiaBloqDic() {
		return diaBloqDic;
	}

	public void setDiaBloqDic(String diaBloqDic) {
		this.diaBloqDic = diaBloqDic;
	}
	
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public boolean isMostrarBotonConcesion() {
		return mostrarBotonConcesion;
	}

	public void setMostrarBotonConcesion(boolean mostrarBotonConcesion) {
		this.mostrarBotonConcesion = mostrarBotonConcesion;
	}

	public boolean isMostrarBotonNovacion() {
		return mostrarBotonNovacion;
	}

	public void setMostrarBotonNovacion(boolean mostrarBotonNovacion) {
		this.mostrarBotonNovacion = mostrarBotonNovacion;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public boolean isProductoBiessEmergente() {
		return productoBiessEmergente;
	}

	public void setProductoBiessEmergente(boolean productoBiessEmergente) {
		this.productoBiessEmergente = productoBiessEmergente;
	}

	public boolean isProductoBiessEmergenteVigente() {
		return productoBiessEmergenteVigente;
	}

	public void setProductoBiessEmergenteVigente(boolean productoBiessEmergenteVigente) {
		this.productoBiessEmergenteVigente = productoBiessEmergenteVigente;
	}

	public boolean isBloqueoFinesSemana() {
		return bloqueoFinesSemana;
	}

	public void setBloqueoFinesSemana(boolean bloqueoFinesSemana) {
		this.bloqueoFinesSemana = bloqueoFinesSemana;
	}

	public Long getIdProveedorMeer() {
		return idProveedorMeer;
	}

	public void setIdProveedorMeer(Long idProveedorMeer) {
		this.idProveedorMeer = idProveedorMeer;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	
	public InformacionIessServicioDto getInformacionIessServicio() {
		this.informacionIessServicio.setProductoBiessEmergente(this.isProductoBiessEmergente());
		return informacionIessServicio;
	}

	public void setInformacionIessServicio(InformacionIessServicioDto informacionIessServicio) {
		this.informacionIessServicio = informacionIessServicio;
	}

	public String getMascaraEmail() {
		return mascaraEmail;
	}

	public void setMascaraEmail(String mascaraEmail) {
		this.mascaraEmail = mascaraEmail;
	}

	public String getMascaraCelular() {
		return mascaraCelular;
	}

	public void setMascaraCelular(String mascaraCelular) {
		this.mascaraCelular = mascaraCelular;
	}

	public boolean isPrestamoEnProceso() {
		return prestamoEnProceso;
	}

	public void setPrestamoEnProceso(boolean prestamoEnProceso) {
		this.prestamoEnProceso = prestamoEnProceso;
	}

	public boolean isProductoProductivo() {
		return productoProductivo;
	}

	public void setProductoProductivo(boolean productoProductivo) {
		this.productoProductivo = productoProductivo;
	}

	public boolean isProductoBiessMujerVigente() {
		return productoBiessMujerVigente;
	}

	public void setProductoBiessMujerVigente(boolean productoBiessMujerVigente) {
		this.productoBiessMujerVigente = productoBiessMujerVigente;
	}

	public boolean isProductoBiessMamaVigente() {
		return productoBiessMamaVigente;
	}

	public void setProductoBiessMamaVigente(boolean productoBiessMamaVigente) {
		this.productoBiessMamaVigente = productoBiessMamaVigente;
	}

	public boolean isProductoBiessMamaSeleccionado() {
		return productoBiessMamaSeleccionado;
	}

	public void setProductoBiessMamaSeleccionado(
			boolean productoBiessMamaSeleccionado) {
		this.productoBiessMamaSeleccionado = productoBiessMamaSeleccionado;
	}

	public String getFlujoProceso() {
		return flujoProceso;
	}

	public void setFlujoProceso(String flujoProceso) {
		this.flujoProceso = flujoProceso;
	}

}