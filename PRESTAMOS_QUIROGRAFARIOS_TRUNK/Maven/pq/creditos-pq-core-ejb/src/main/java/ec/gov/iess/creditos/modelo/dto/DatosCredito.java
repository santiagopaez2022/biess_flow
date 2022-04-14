/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.focalizados.dto.ProductoDto;
import ec.gov.iess.creditos.modelo.persistencia.AniosPlazoCapitalEndeudamiento;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.EstadoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.TipoDividendo;
import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.hl.modelo.OficinaIess;

/**
 * 
 * <b> Permite incluir los datos generales del credito </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.5 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/10/03 $]
 *          </p>
 */
public class DatosCredito implements Serializable {
	private static final long serialVersionUID = -5921445265846988631L;

	public DatosCredito() {
	}

	private int idTipoCredito;
	private int idVariedadcredito;
	private Date fechaSolicitud;
	private String cedulaAfiliado;
	private String tipoSolicitante;
	private String idDivisionPolitica;
	private PrestamoCalculo prestamoCalculo;
	private InstitucionFinanciera institucionFinanciera;
	private Empleador empleador;
	private BigDecimal tasaInteres;
	private DatosGarantia garantia;
	private TipoPrestamista tipoPrestamista;

	private String genero;
	private Date fechaNacimiento;
	private OrigenJubilado origenJubilado;

	// Datos Prestamo
	private List<DividendoCalculo> dividendoCalculoList;
	private Long numeroPrestamo;
	private BigDecimal valorPeriodoGracia;
	private TipoDividendo tipoDividendo;
	private EstadoDividendoPrestamo estadoDividendoPrestamo;

	private VariablePrestamo variablePrestamo;
	private OficinaIess oficinaIess;
	private SolicitudCredito solicitudCredito;

	// Adicionales
	private Long ordenPrestamo;
	private String tipoPeriodo;
	private String parametroPrestamo;
	private Long anio;
	private Long mes;
	private EstadoPrestamo estadoPrestamo;
	private Long formaPago;
	private String clasePrestamoReal;

	private BigDecimal monto;
	private BigDecimal montoMaximo;
	private int plazo;

	// Novacion
	private boolean esNovacion;
	private Long numeroCanceladoNovacion;
	private Double valorCanceladoNovacion;
	private Prestamo prestamoAnteriorNovacion;
	/**
	 * Carlos Bastidas: INC-6047 Se crea en la cabecera del credito el numero de liquidacion del credito cancelado por
	 * novacion"
	 */
	private Long numeroLiquidacionNovacion;

	// Concesion
	private String parTasaBancoCentral;
	private String parTasaActuarial;
	private int parNumeroSemanas;
	private String parSeguroDesgravamen;
	private int parTipoTablaReferencia;
	private BigDecimal porcentajeSeguroDesgravamen;
	private int plazoMaximoPrestamo;
	private int plazoMaximoMeses;
	private CalculoCredito calculoCredito;
	List<AniosPlazoCapitalEndeudamiento> plazoEndeudamientoList;
	// Nuevas Tasas BIESS
	private String parTasaEfectivaReferencial;
	private String parTasaActuarialIESSSemestral;
	// Datos de Orden de Compra
	private DatosOrdenCompra ordenCompra;
	private BigDecimal ValorSeguroSaldosOrden;
	private Prestamo prestamoOrdenCompra;
	private List<PlazoCredito> listaPlazoCredito;

	// valor para cuota de Buro de Credito
	private BigDecimal cuotaMensualBuro;

	// Garantia
	private Garantia garantiaReal;

	// Si es fallecido
	private boolean esEnfermoTerminal;

	// Para el tipo de tabla de amortizacion DS
	private String tipoTabla;

	//// Motivo Del Prestamo
	private ArrayList<DatosCatalogo> dtocatalogo;

	// INC-2272 Pensiones Alimenticias
	private boolean pagoPensionesAlimenticias;

	private BeneficiarioCredito beneficiarioCredito;

	// INC-2148 Refugiados
	private String visaPasaporteAfiliado;

	private String tipoBeneficiario;
	// INC-2286 Ferrocarriles -Proveedores
	private InstitucionFinanciera institucionFinancieraProveedor;

	private Proveedor proveedor;

	// valor para cuota mensuale maxima
	private BigDecimal cuotaMensualMaxima;

	// Bandera para identificar los prestamos parametrizados con nuevas tasas de interes
	private Long parametrizacion;
	
	// Bandera para validacion de estados en credito
	private Long validacionCredito;

	// Bandera para identificar un credito Biess especial (como biess emergente por el terremoto por ejemplo)
	private Long creditoEspecial;
	
	// Informacion para creditos pq focalizados
	private String codigoActivacion;
	private String codigoActivacionEncripta;
	private List<ProductoDto> listsProductosFocalizados;
	private Integer codigoPuntoVenta;
	private Long idProveedorMeer;
	
	private int edadAsegurado;
	
	// Informacion para creditos Ecuador tu lugar en el mundo
	private String numeroReserva;

	// Requerido para consumo servicio tabla amortizacion SAC
	private String nombreAsegurado;
	private String tipoPeticionTablaSac;
	private String tipoIdentificacionSac;
	private String productoCarga;
	//Para mantener el nut
	private long nut; 

	public ArrayList<DatosCatalogo> getDtocatalogo() {
		return dtocatalogo;
	}

	public void setDtocatalogo(ArrayList<DatosCatalogo> dtocatalogo) {
		this.dtocatalogo = dtocatalogo;
	}

	public int getIdTipoCredito() {
		return idTipoCredito;
	}

	public void setIdTipoCredito(int idTipoCredito) {
		this.idTipoCredito = idTipoCredito;
	}

	public int getIdVariedadcredito() {
		return idVariedadcredito;
	}

	public void setIdVariedadcredito(int idVariedadcredito) {
		this.idVariedadcredito = idVariedadcredito;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getCedulaAfiliado() {
		return cedulaAfiliado;
	}

	public void setCedulaAfiliado(String cedulaAfiliado) {
		this.cedulaAfiliado = cedulaAfiliado;
	}

	public String getTipoSolicitante() {
		return tipoSolicitante;
	}

	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	public String getIdDivisionPolitica() {
		return idDivisionPolitica;
	}

	public void setIdDivisionPolitica(String idDivisionPolitica) {
		this.idDivisionPolitica = idDivisionPolitica;
	}

	public PrestamoCalculo getPrestamoCalculo() {
		return prestamoCalculo;
	}

	public void setPrestamoCalculo(PrestamoCalculo prestamoCalculo) {
		this.prestamoCalculo = prestamoCalculo;
	}

	public InstitucionFinanciera getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	public void setInstitucionFinanciera(InstitucionFinanciera institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

	public Empleador getEmpleador() {
		return empleador;
	}

	public void setEmpleador(Empleador empleador) {
		this.empleador = empleador;
	}

	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public DatosGarantia getGarantia() {
		return garantia;
	}

	public void setGarantia(DatosGarantia garantia) {
		this.garantia = garantia;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public OrigenJubilado getOrigenJubilado() {
		return origenJubilado;
	}

	public void setOrigenJubilado(OrigenJubilado origenJubilado) {
		this.origenJubilado = origenJubilado;
	}

	public TipoPrestamista getTipoPrestamista() {
		return tipoPrestamista;
	}

	public void setTipoPrestamista(TipoPrestamista tipoPrestamista) {
		this.tipoPrestamista = tipoPrestamista;
	}

	// Datos Credito

	public List<DividendoCalculo> getDividendoCalculoList() {
		return dividendoCalculoList;
	}

	public void setDividendoCalculoList(List<DividendoCalculo> dividendoCalculoList) {
		this.dividendoCalculoList = dividendoCalculoList;
	}

	public long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public BigDecimal getValorPeriodoGracia() {
		return valorPeriodoGracia;
	}

	public void setValorPeriodoGracia(BigDecimal valorPeriodoGracia) {
		this.valorPeriodoGracia = valorPeriodoGracia;
	}

	public TipoDividendo getTipoDividendo() {
		return tipoDividendo;
	}

	public void setTipoDividendo(TipoDividendo tipoDividendo) {
		this.tipoDividendo = tipoDividendo;
	}

	public EstadoDividendoPrestamo getEstadoDividendoPrestamo() {
		return estadoDividendoPrestamo;
	}

	public void setEstadoDividendoPrestamo(EstadoDividendoPrestamo estadoDividendoPrestamo) {
		this.estadoDividendoPrestamo = estadoDividendoPrestamo;
	}

	public VariablePrestamo getVariablePrestamo() {
		return variablePrestamo;
	}

	public void setVariablePrestamo(VariablePrestamo variablePrestamo) {
		this.variablePrestamo = variablePrestamo;
	}

	public OficinaIess getOficinaIess() {
		return oficinaIess;
	}

	public void setOficinaIess(OficinaIess oficinaIess) {
		this.oficinaIess = oficinaIess;
	}

	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	// Adicionales

	public Long getOrdenPrestamo() {
		return ordenPrestamo;
	}

	public void setOrdenPrestamo(Long ordenPrestamo) {
		this.ordenPrestamo = ordenPrestamo;
	}

	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public String getParametroPrestamo() {
		return parametroPrestamo;
	}

	public void setParametroPrestamo(String parametroPrestamo) {
		this.parametroPrestamo = parametroPrestamo;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public Long getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Long formaPago) {
		this.formaPago = formaPago;
	}

	public String getClasePrestamoReal() {
		return clasePrestamoReal;
	}

	public void setClasePrestamoReal(String clasePrestamoReal) {
		this.clasePrestamoReal = clasePrestamoReal;
	}

	public void setNumeroPrestamo(Long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	// Novacion
	public void setEsNovacion(boolean esNovacion) {
		this.esNovacion = esNovacion;
	}

	public boolean isEsNovacion() {
		return esNovacion;
	}

	public Prestamo getPrestamoAnteriorNovacion() {
		return prestamoAnteriorNovacion;
	}

	public void setPrestamoAnteriorNovacion(Prestamo prestamoAnteriorNovacion) {
		this.prestamoAnteriorNovacion = prestamoAnteriorNovacion;
	}

	public Long getNumeroCanceladoNovacion() {
		return numeroCanceladoNovacion;
	}

	public void setNumeroCanceladoNovacion(Long numeroCanceladoNovacion) {
		this.numeroCanceladoNovacion = numeroCanceladoNovacion;
	}

	public Double getValorCanceladoNovacion() {
		return valorCanceladoNovacion;
	}

	public void setValorCanceladoNovacion(Double valorCanceladoNovacion) {
		this.valorCanceladoNovacion = valorCanceladoNovacion;
	}

	public Long getNumeroLiquidacionNovacion() {
		return numeroLiquidacionNovacion;
	}

	public void setNumeroLiquidacionNovacion(Long numeroLiquidacionNovacion) {
		this.numeroLiquidacionNovacion = numeroLiquidacionNovacion;
	}

	public String getParTasaBancoCentral() {
		return parTasaBancoCentral;
	}

	public void setParTasaBancoCentral(String parTasaBancoCentral) {
		this.parTasaBancoCentral = parTasaBancoCentral;
	}

	public String getParTasaActuarial() {
		return parTasaActuarial;
	}

	public void setParTasaActuarial(String parTasaActuarial) {
		this.parTasaActuarial = parTasaActuarial;
	}

	public int getParNumeroSemanas() {
		return parNumeroSemanas;
	}

	public void setParNumeroSemanas(int parNumeroSemanas) {
		this.parNumeroSemanas = parNumeroSemanas;
	}

	public String getParSeguroDesgravamen() {
		return parSeguroDesgravamen;
	}

	public void setParSeguroDesgravamen(String parSeguroDesgravamen) {
		this.parSeguroDesgravamen = parSeguroDesgravamen;
	}

	public int getParTipoTablaReferencia() {
		return parTipoTablaReferencia;
	}

	public void setParTipoTablaReferencia(int parTipoTablaReferencia) {
		this.parTipoTablaReferencia = parTipoTablaReferencia;
	}

	public BigDecimal getPorcentajeSeguroDesgravamen() {
		return porcentajeSeguroDesgravamen;
	}

	public void setPorcentajeSeguroDesgravamen(BigDecimal porcentajeSeguroDesgravamen) {
		this.porcentajeSeguroDesgravamen = porcentajeSeguroDesgravamen;
	}

	public int getPlazoMaximoPrestamo() {
		return plazoMaximoPrestamo;
	}

	public void setPlazoMaximoPrestamo(int plazoMaximoPrestamo) {
		this.plazoMaximoPrestamo = plazoMaximoPrestamo;
	}

	public int getPlazoMaximoMeses() {
		return plazoMaximoMeses;
	}

	public void setPlazoMaximoMeses(int plazoMaximoMeses) {
		this.plazoMaximoMeses = plazoMaximoMeses;
	}

	public CalculoCredito getCalculoCredito() {
		return calculoCredito;
	}

	public void setCalculoCredito(CalculoCredito calculoCredito) {
		this.calculoCredito = calculoCredito;
	}

	public List<AniosPlazoCapitalEndeudamiento> getPlazoEndeudamientoList() {
		return plazoEndeudamientoList;
	}

	public void setPlazoEndeudamientoList(List<AniosPlazoCapitalEndeudamiento> plazoEndeudamientoList) {
		this.plazoEndeudamientoList = plazoEndeudamientoList;
	}

	public String getParTasaEfectivaReferencial() {
		return parTasaEfectivaReferencial;
	}

	public void setParTasaEfectivaReferencial(String parTasaEfectivaReferencial) {
		this.parTasaEfectivaReferencial = parTasaEfectivaReferencial;
	}

	public String getParTasaActuarialIESSSemestral() {
		return parTasaActuarialIESSSemestral;
	}

	public void setParTasaActuarialIESSSemestral(String parTasaActuarialIESSSemestral) {
		this.parTasaActuarialIESSSemestral = parTasaActuarialIESSSemestral;
	}

	public DatosOrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(DatosOrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public BigDecimal getValorSeguroSaldosOrden() {
		return ValorSeguroSaldosOrden;
	}

	public void setValorSeguroSaldosOrden(BigDecimal valorSeguroSaldosOrden) {
		ValorSeguroSaldosOrden = valorSeguroSaldosOrden;
	}

	public Prestamo getPrestamoOrdenCompra() {
		return prestamoOrdenCompra;
	}

	public void setPrestamoOrdenCompra(Prestamo prestamoOrdenCompra) {
		this.prestamoOrdenCompra = prestamoOrdenCompra;
	}

	public List<PlazoCredito> getListaPlazoCredito() {
		return listaPlazoCredito;
	}

	public void setListaPlazoCredito(List<PlazoCredito> listaPlazoCredito) {
		this.listaPlazoCredito = listaPlazoCredito;
	}

	public BigDecimal getCuotaMensualBuro() {
		return cuotaMensualBuro;
	}

	public void setCuotaMensualBuro(BigDecimal cuotaMensualBuro) {
		this.cuotaMensualBuro = cuotaMensualBuro;
	}

	public Garantia getGarantiaReal() {
		return garantiaReal;
	}

	public void setGarantiaReal(Garantia garantiaReal) {
		this.garantiaReal = garantiaReal;
	}

	public boolean isEsEnfermoTerminal() {
		return esEnfermoTerminal;
	}

	public void setEsEnfermoTerminal(boolean esEnfermoTerminal) {
		this.esEnfermoTerminal = esEnfermoTerminal;
	}

	/**
	 * @return the pagoPensionesAlimenticias
	 */
	public boolean isPagoPensionesAlimenticias() {
		return pagoPensionesAlimenticias;
	}

	/**
	 * @param pagoPensionesAlimenticias
	 *            the pagoPensionesAlimenticias to set
	 */
	public void setPagoPensionesAlimenticias(boolean pagoPensionesAlimenticias) {
		this.pagoPensionesAlimenticias = pagoPensionesAlimenticias;
	}

	/**
	 * @return the beneficiarioCredito
	 */
	public BeneficiarioCredito getBeneficiarioCredito() {
		return beneficiarioCredito;
	}

	/**
	 * @param beneficiarioCredito
	 *            the beneficiarioCredito to set
	 */
	public void setBeneficiarioCredito(BeneficiarioCredito beneficiarioCredito) {
		this.beneficiarioCredito = beneficiarioCredito;
	}

	/**
	 * @return the visaPasaporteAfiliado
	 */
	public String getVisaPasaporteAfiliado() {
		return visaPasaporteAfiliado;
	}

	/**
	 * @param visaPasaporteAfiliado
	 *            the visaPasaporteAfiliado to set
	 */
	public void setVisaPasaporteAfiliado(String visaPasaporteAfiliado) {
		this.visaPasaporteAfiliado = visaPasaporteAfiliado;
	}

	/**
	 * @return the tipoBeneficiario
	 */
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}

	/**
	 * @param tipoBeneficiario
	 *            the tipoBeneficiario to set
	 */
	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}

	/**
	 * @return {@link InstitucionFinanciera}
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Obtiene el tipo de tabla seleccionada segun amortizacion
	 * 
	 * @return
	 */
	public String getTipoTabla() {
		return tipoTabla;
	}

	/**
	 * setea el tipo de tabla seleccionada para la amortizacion
	 * 
	 * @param tipoTabla
	 */
	public void setTipoTabla(String tipoTabla) {
		this.tipoTabla = tipoTabla;
	}

	public BigDecimal getCuotaMensualMaxima() {
		return cuotaMensualMaxima;
	}

	public void setCuotaMensualMaxima(BigDecimal cuotaMensualMaxima) {
		this.cuotaMensualMaxima = cuotaMensualMaxima;
	}

	public Long getParametrizacion() {
		return parametrizacion;
	}

	public void setParametrizacion(Long parametrizacion) {
		this.parametrizacion = parametrizacion;
	}
	
	public Long getValidacionCredito() {
		return validacionCredito;
	}

	public void setValidacionCredito(Long validacionCredito) {
		this.validacionCredito = validacionCredito;
	}

	public Long getCreditoEspecial() {
		return creditoEspecial;
	}

	public void setCreditoEspecial(Long creditoEspecial) {
		this.creditoEspecial = creditoEspecial;
	}

	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public String getCodigoActivacion() {
		return codigoActivacion;
	}

	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}

	public List<ProductoDto> getListsProductosFocalizados() {
		return listsProductosFocalizados;
	}

	public void setListsProductosFocalizados(List<ProductoDto> listsProductosFocalizados) {
		this.listsProductosFocalizados = listsProductosFocalizados;
	}

	public Integer getCodigoPuntoVenta() {
		return codigoPuntoVenta;
	}

	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	public String getCodigoActivacionEncripta() {
		return codigoActivacionEncripta;
	}

	public void setCodigoActivacionEncripta(String codigoActivacionEncripta) {
		this.codigoActivacionEncripta = codigoActivacionEncripta;
	}

	public Long getIdProveedorMeer() {
		return idProveedorMeer;
	}

	public void setIdProveedorMeer(Long idProveedorMeer) {
		this.idProveedorMeer = idProveedorMeer;
	}

	public int getEdadAsegurado() {
		return edadAsegurado;
	}

	public void setEdadAsegurado(int edadAsegurado) {
		this.edadAsegurado = edadAsegurado;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public String getNombreAsegurado() {
		return nombreAsegurado;
	}

	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}

	public String getTipoPeticionTablaSac() {
		return tipoPeticionTablaSac;
	}

	public void setTipoPeticionTablaSac(String tipoPeticionTablaSac) {
		this.tipoPeticionTablaSac = tipoPeticionTablaSac;
	}

	public String getTipoIdentificacionSac() {
		return tipoIdentificacionSac;
	}

	public void setTipoIdentificacionSac(String tipoIdentificacionSac) {
		this.tipoIdentificacionSac = tipoIdentificacionSac;
	}

	public String getProductoCarga() {
		return productoCarga;
	}

	public void setProductoCarga(String productoCarga) {
		this.productoCarga = productoCarga;
	}

	public long getNut() {
		return nut;
	}

	public void setNut(long nut) {
		this.nut = nut;
	}

}
