package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.List;

import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

/**
 * 
 * <b> Permite realizar el paso de datos para la calificación del crédito </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:12 $]
 *          </p>
 */

public class ValidarRequisitosPrecalificacion implements Serializable {
	private static final long serialVersionUID = 6439287588426867900L;

	public ValidarRequisitosPrecalificacion() {

	}

	/**
	 * Párametros de Validación
	 */
	private String cedula;
	private TipoPrestamista tipoPrestamista;
	private Solicitante solicitante;
	private List<String> estadoCreditoCuentaBancaria;
	private List<String> estadoCreditoHl;
	private List<String> estadoSolicitudHlPH;
	private List<Long> codTipSolSerList;
	private List<String> codEstSolSerList;
	private DatosGarantia garantia;
	private boolean novacion;
	private Prestamo prestamoVigenteNovacion;
	private CalculoLiquidacion liquidacionPrestamoVigente;
	private DatosOrdenCompra datosOrdenCompra;

	/**
	 * Constante que define el porcentaje de cancelacion del crédito del pq
	 * vigente para la novacion
	 */
	private final float paramPorcentajeCancelacionPqVigente = 0.5f;

	public float getParamPorcentajeCancelacionPqVigente() {
		return paramPorcentajeCancelacionPqVigente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public TipoPrestamista getTipoPrestamista() {
		return tipoPrestamista;
	}

	public void setTipoPrestamista(TipoPrestamista tipoPrestamista) {
		this.tipoPrestamista = tipoPrestamista;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public List<String> getEstadoCreditoCuentaBancaria() {
		return estadoCreditoCuentaBancaria;
	}

	public void setEstadoCreditoCuentaBancaria(
			List<String> estadoCreditoCuentaBancaria) {
		this.estadoCreditoCuentaBancaria = estadoCreditoCuentaBancaria;
	}

	public List<String> getEstadoCreditoHl() {
		return estadoCreditoHl;
	}

	public void setEstadoCreditoHl(List<String> estadoCreditoHl) {
		this.estadoCreditoHl = estadoCreditoHl;
	}

	public List<String> getEstadoSolicitudHlPH() {
		return estadoSolicitudHlPH;
	}

	public void setEstadoSolicitudHlPH(List<String> estadoSolicitudHlPH) {
		this.estadoSolicitudHlPH = estadoSolicitudHlPH;
	}

	public List<Long> getCodTipSolSerList() {
		return codTipSolSerList;
	}

	public void setCodTipSolSerList(List<Long> codTipSolSerList) {
		this.codTipSolSerList = codTipSolSerList;
	}

	public List<String> getCodEstSolSerList() {
		return codEstSolSerList;
	}

	public void setCodEstSolSerList(List<String> codEstSolSerList) {
		this.codEstSolSerList = codEstSolSerList;
	}

	public DatosGarantia getGarantia() {
		return garantia;
	}

	public void setGarantia(DatosGarantia garantia) {
		this.garantia = garantia;
	}

	public boolean isNovacion() {
		return novacion;
	}

	public void setNovacion(boolean novacion) {
		this.novacion = novacion;
	}

	public Prestamo getPrestamoVigenteNovacion() {
		return prestamoVigenteNovacion;
	}

	public void setPrestamoVigenteNovacion(Prestamo prestamoVigenteNovacion) {
		this.prestamoVigenteNovacion = prestamoVigenteNovacion;
	}

	public CalculoLiquidacion getLiquidacionPrestamoVigente() {
		return liquidacionPrestamoVigente;
	}

	public void setLiquidacionPrestamoVigente(
			CalculoLiquidacion liquidacionPrestamoVigente) {
		this.liquidacionPrestamoVigente = liquidacionPrestamoVigente;
	}

	public DatosOrdenCompra getDatosOrdenCompra() {
		return datosOrdenCompra;
	}

	public void setDatosOrdenCompra(DatosOrdenCompra datosOrdenCompra) {
		this.datosOrdenCompra = datosOrdenCompra;
	}

}
