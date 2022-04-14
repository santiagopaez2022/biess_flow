/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ec.gov.iess.creditos.modelo.persistencia.EstadoDividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.SolicitudCredito;
import ec.gov.iess.creditos.modelo.persistencia.TipoDividendo;
import ec.gov.iess.creditos.modelo.persistencia.VariablePrestamo;
import ec.gov.iess.hl.modelo.OficinaIess;

/**
 * @author cbastidas
 * 
 */
public class DatosPrestamo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2151007023249781096L;

	public DatosPrestamo() {

	}

	/**
	 * @param dividendoCalculoList
	 * @param idTipocredito
	 * @param idVariedadcredito
	 * @param numeroPrestamo
	 * @param tasaInteres
	 * @param valorPeriodoGracia
	 * @param tipoDividendo
	 * @param estadoDividendoPrestamo
	 * @param prestamoCalculo
	 * @param variablePrestamo
	 * @param fechaSolicitud
	 * @param oficinaIess
	 * @param institucionFinanciera
	 * @param solicitudCredito
	 * @param tipoSolicitante
	 * @param empleador
	 * @param idDivisionPolitica
	 */
	public DatosPrestamo(List<DividendoCalculo> dividendoCalculoList,
			int idTipocredito, int idVariedadcredito, long numeroPrestamo,
			BigDecimal tasaInteres, BigDecimal valorPeriodoGracia,
			TipoDividendo tipoDividendo,
			EstadoDividendoPrestamo estadoDividendoPrestamo,
			PrestamoCalculo prestamoCalculo, VariablePrestamo variablePrestamo,
			Date fechaSolicitud, OficinaIess oficinaIess,
			InstitucionFinanciera institucionFinanciera,
			SolicitudCredito solicitudCredito, String tipoSolicitante,
			Empleador empleador, String idDivisionPolitica) {
		this.dividendoCalculoList = dividendoCalculoList;
		this.idTipocredito = idTipocredito;
		this.idVariedadcredito = idVariedadcredito;
		this.numeroPrestamo = numeroPrestamo;
		this.tasaInteres = tasaInteres;
		this.valorPeriodoGracia = valorPeriodoGracia;
		this.tipoDividendo = tipoDividendo;
		this.estadoDividendoPrestamo = estadoDividendoPrestamo;
		this.prestamoCalculo = prestamoCalculo;
		this.variablePrestamo = variablePrestamo;
		this.fechaSolicitud = fechaSolicitud;
		this.oficinaIess = oficinaIess;
		this.institucionFinanciera = institucionFinanciera;
		this.solicitudCredito = solicitudCredito;
		this.tipoSolicitante = tipoSolicitante;
		this.empleador = empleador;
		this.idDivisionPolitica = idDivisionPolitica;
	}

	private List<DividendoCalculo> dividendoCalculoList;
	private int idTipocredito;
	private int idVariedadcredito;
	private long numeroPrestamo;
	private BigDecimal tasaInteres;
	private BigDecimal valorPeriodoGracia;
	private TipoDividendo tipoDividendo;
	private EstadoDividendoPrestamo estadoDividendoPrestamo;

	private PrestamoCalculo prestamoCalculo;
	private VariablePrestamo variablePrestamo;
	private Date fechaSolicitud;
	private OficinaIess oficinaIess;
	private InstitucionFinanciera institucionFinanciera;
	private SolicitudCredito solicitudCredito;
	private String tipoSolicitante;
	private Empleador empleador;
	private String idDivisionPolitica;

	public List<DividendoCalculo> getDividendoCalculoList() {
		return dividendoCalculoList;
	}

	public void setDividendoCalculoList(
			List<DividendoCalculo> dividendoCalculoList) {
		this.dividendoCalculoList = dividendoCalculoList;
	}

	public int getIdTipocredito() {
		return idTipocredito;
	}

	public void setIdTipocredito(int idTipocredito) {
		this.idTipocredito = idTipocredito;
	}

	public int getIdVariedadcredito() {
		return idVariedadcredito;
	}

	public void setIdVariedadcredito(int idVariedadcredito) {
		this.idVariedadcredito = idVariedadcredito;
	}

	public long getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(long numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
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

	public void setEstadoDividendoPrestamo(
			EstadoDividendoPrestamo estadoDividendoPrestamo) {
		this.estadoDividendoPrestamo = estadoDividendoPrestamo;
	}

	public PrestamoCalculo getPrestamoCalculo() {
		return prestamoCalculo;
	}

	public void setPrestamoCalculo(PrestamoCalculo prestamoCalculo) {
		this.prestamoCalculo = prestamoCalculo;
	}

	public VariablePrestamo getVariablePrestamo() {
		return variablePrestamo;
	}

	public void setVariablePrestamo(VariablePrestamo variablePrestamo) {
		this.variablePrestamo = variablePrestamo;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public OficinaIess getOficinaIess() {
		return oficinaIess;
	}

	public void setOficinaIess(OficinaIess oficinaIess) {
		this.oficinaIess = oficinaIess;
	}

	public InstitucionFinanciera getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	public void setInstitucionFinanciera(
			InstitucionFinanciera institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}

	public String getTipoSolicitante() {
		return tipoSolicitante;
	}

	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	public Empleador getEmpleador() {
		return empleador;
	}

	public void setEmpleador(Empleador empleador) {
		this.empleador = empleador;
	}

	public String getIdDivisionPolitica() {
		return idDivisionPolitica;
	}

	public void setIdDivisionPolitica(String idDivisionPolitica) {
		this.idDivisionPolitica = idDivisionPolitica;
	}

}
