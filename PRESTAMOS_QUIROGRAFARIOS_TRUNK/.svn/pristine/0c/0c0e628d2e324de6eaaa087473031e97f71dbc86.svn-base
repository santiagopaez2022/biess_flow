/**
 * 
 */
package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import ec.gov.iess.creditos.enumeracion.EstadoComprobantePago;
import ec.gov.iess.creditos.enumeracion.TipoLiquidacion;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;

/**
 * @author cbastidas
 * 
 */
public class ValidarRequisitosRecaudacion implements Serializable {

	private static final long serialVersionUID = 781477754643953248L;

	/**
	 * 
	 * @param estadoCredito
	 * @param tipoCredito
	 * @param tipoLiquidacion
	 * @param dividendosMora
	 * @param dividendosEco
	 * @param dividendosReg
	 * @param dividendos
	 * @param dividendosEpl
	 * @param estadoComprobante
	 * @param tipoSolicitudFondos
	 * @param estadoSolicitudFondos
	 * @param cumpleImposiciones
	 * @param estadoAfiliadoFondos
	 * @param estadoSolicitudFondosTramite
	 * @param estadoCargoReg
	 * @param estadoCargoPro
	 * @param estadoBloqueado
	 * @param tipoAporte
	 * @param estadoComprobantePago
	 * @param prestamo
	 */

	public ValidarRequisitosRecaudacion(String estadoCredito,
			List<Long> tipoCredito, TipoLiquidacion tipoLiquidacion,
			List<String> dividendosMora, List<String> dividendosEco,
			List<String> dividendosReg, List<String> dividendos,
			List<String> dividendosEpl, List<String> estadoComprobante,
			List<String> tipoSolicitudFondos,
			List<String> estadoSolicitudFondos, String cumpleImposiciones,
			String estadoAfiliadoFondos,
			List<String> estadoSolicitudFondosTramite,
			List<String> estadoCargoReg, List<String> estadoCargoPro,
			List<String> estadoBloqueado, List<String> tipoAporte,
			List<EstadoComprobantePago> estadoComprobantePago, Prestamo prestamo) {
		super();
		this.estadoCredito = estadoCredito;
		this.tipoCredito = tipoCredito;
		this.tipoLiquidacion = tipoLiquidacion;
		this.dividendosMora = dividendosMora;
		this.dividendosEco = dividendosEco;
		this.dividendosReg = dividendosReg;
		this.dividendos = dividendos;
		this.dividendosEpl = dividendosEpl;
		this.estadoComprobante = estadoComprobante;
		this.tipoSolicitudFondos = tipoSolicitudFondos;
		this.estadoSolicitudFondos = estadoSolicitudFondos;
		this.cumpleImposiciones = cumpleImposiciones;
		this.estadoAfiliadoFondos = estadoAfiliadoFondos;
		this.estadoSolicitudFondosTramite = estadoSolicitudFondosTramite;
		this.estadoCargoReg = estadoCargoReg;
		this.estadoCargoPro = estadoCargoPro;
		this.estadoBloqueado = estadoBloqueado;
		this.tipoAporte = tipoAporte;
		this.estadoComprobantePago = estadoComprobantePago;
		this.prestamo = prestamo;
	}
	
	public ValidarRequisitosRecaudacion(String estadoCredito, List<Long> tipoCredito, TipoLiquidacion tipoLiquidacion, List<String> dividendosMora,
			List<String> dividendosEco, List<String> dividendosReg, List<String> dividendos, List<String> dividendosEpl,
			List<String> estadoComprobante, List<String> tipoSolicitudFondos, List<String> estadoSolicitudFondos, String cumpleImposiciones,
			String estadoAfiliadoFondos, List<String> estadoSolicitudFondosTramite, List<String> estadoCargoReg, List<String> estadoCargoPro,
			List<String> estadoBloqueado, List<String> tipoAporte, List<EstadoComprobantePago> estadoComprobantePago, Prestamo prestamo,
			String tipoEmpleador) {
		super();
		this.estadoCredito = estadoCredito;
		this.tipoCredito = tipoCredito;
		this.tipoLiquidacion = tipoLiquidacion;
		this.dividendosMora = dividendosMora;
		this.dividendosEco = dividendosEco;
		this.dividendosReg = dividendosReg;
		this.dividendos = dividendos;
		this.dividendosEpl = dividendosEpl;
		this.estadoComprobante = estadoComprobante;
		this.tipoSolicitudFondos = tipoSolicitudFondos;
		this.estadoSolicitudFondos = estadoSolicitudFondos;
		this.cumpleImposiciones = cumpleImposiciones;
		this.estadoAfiliadoFondos = estadoAfiliadoFondos;
		this.estadoSolicitudFondosTramite = estadoSolicitudFondosTramite;
		this.estadoCargoReg = estadoCargoReg;
		this.estadoCargoPro = estadoCargoPro;
		this.estadoBloqueado = estadoBloqueado;
		this.tipoAporte = tipoAporte;
		this.estadoComprobantePago = estadoComprobantePago;
		this.prestamo = prestamo;
		this.tipoEmpleador = tipoEmpleador;
	}

	public ValidarRequisitosRecaudacion() {
	}

	/**
	 * Validacion estado del Credito
	 */
	private String estadoCredito;
	private List<Long> tipoCredito;

	/**
	 * Tipo de Liquidacion
	 */
	private TipoLiquidacion tipoLiquidacion;

	/**
	 * Validacion estado de dividendos
	 */
	private List<String> dividendosMora;
	private List<String> dividendosEco;
	private List<String> dividendosReg;
	private List<String> dividendos;

	/**
	 * Validacion estado dividendo en EPL
	 */
	private List<String> dividendosEpl;
	private List<String> estadoComprobante;

	/**
	 * Validacion de Fondos de reserva
	 */
	private List<String> tipoSolicitudFondos;
	private List<String> estadoSolicitudFondos;
	/**
	 * Carlos Bastidas: INC-6047 Se pasan como parametros si cumple imposiciones
	 * y el estado del afiliado para realizar el cruce de cuentas"
	 */
	private String cumpleImposiciones;
	private String estadoAfiliadoFondos;
	private List<String> estadoSolicitudFondosTramite;

	/**
	 * Validacion de Cargos
	 */
	private List<String> estadoCargoReg;
	private List<String> estadoCargoPro;

	/**
	 * Validacion de Bloqueos
	 */
	private List<String> estadoBloqueado;

	/**
	 * Comprobar Fecha Inicio de Rendimiento de los aportes
	 */
	private List<String> tipoAporte;

	/**
	 * Estado Comprobante de Pago
	 */
	private List<EstadoComprobantePago> estadoComprobantePago;

	/**
	 * Prestamo
	 */
	private Prestamo prestamo;
	
	private TipoPrestamista tipoPrestamista;
	
	/**
	 * Calculo de la liquidacion 
	 */
	private CalculoLiquidacion calculoLiquidacion;
	
	/**
	 * Tipo de empleador (publico, privado o ministerio de relaciones exteriores) para generacion de comprobantes y
	 * liquidacion anticipada
	 */
	private String tipoEmpleador;
	
	private Calendar fechaValidezLiquidacion;
	private String flujo;  //Afiliado o Funcionario

	public List<String> getDividendosReg() {
		return dividendosReg;
	}

	public void setDividendosReg(List<String> dividendosReg) {
		this.dividendosReg = dividendosReg;
	}

	public List<String> getDividendos() {
		return dividendos;
	}

	public void setDividendos(List<String> dividendos) {
		this.dividendos = dividendos;
	}

	public List<String> getDividendosMora() {
		return dividendosMora;
	}

	public void setDividendosMora(List<String> dividendosMora) {
		this.dividendosMora = dividendosMora;
	}

	public List<String> getDividendosEpl() {
		return dividendosEpl;
	}

	public void setDividendosEpl(List<String> dividendosEpl) {
		this.dividendosEpl = dividendosEpl;
	}

	public List<String> getDividendosEco() {
		return dividendosEco;
	}

	public void setDividendosEco(List<String> dividendosEco) {
		this.dividendosEco = dividendosEco;
	}

	public List<String> getTipoSolicitudFondos() {
		return tipoSolicitudFondos;
	}

	public void setTipoSolicitudFondos(List<String> tipoSolicitudFondos) {
		this.tipoSolicitudFondos = tipoSolicitudFondos;
	}

	public List<String> getEstadoSolicitudFondos() {
		return estadoSolicitudFondos;
	}

	public void setEstadoSolicitudFondos(List<String> estadoSolicitudFondos) {
		this.estadoSolicitudFondos = estadoSolicitudFondos;
	}

	public String getCumpleImposiciones() {
		return cumpleImposiciones;
	}

	public void setCumpleImposiciones(String cumpleImposiciones) {
		this.cumpleImposiciones = cumpleImposiciones;
	}

	public String getEstadoAfiliadoFondos() {
		return estadoAfiliadoFondos;
	}

	public void setEstadoAfiliadoFondos(String estadoAfiliadoFondos) {
		this.estadoAfiliadoFondos = estadoAfiliadoFondos;
	}

	public List<String> getEstadoSolicitudFondosTramite() {
		return estadoSolicitudFondosTramite;
	}

	public void setEstadoSolicitudFondosTramite(
			List<String> estadoSolicitudFondosTramite) {
		this.estadoSolicitudFondosTramite = estadoSolicitudFondosTramite;
	}

	public List<String> getEstadoCargoReg() {
		return estadoCargoReg;
	}

	public void setEstadoCargoReg(List<String> estadoCargoReg) {
		this.estadoCargoReg = estadoCargoReg;
	}

	public List<String> getEstadoCargoPro() {
		return estadoCargoPro;
	}

	public void setEstadoCargoPro(List<String> estadoCargoPro) {
		this.estadoCargoPro = estadoCargoPro;
	}

	public List<String> getEstadoBloqueado() {
		return estadoBloqueado;
	}

	public void setEstadoBloqueado(List<String> estadoBloqueado) {
		this.estadoBloqueado = estadoBloqueado;
	}

	public List<String> getTipoAporte() {
		return tipoAporte;
	}

	public void setTipoAporte(List<String> tipoAporte) {
		this.tipoAporte = tipoAporte;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public List<String> getEstadoComprobante() {
		return estadoComprobante;
	}

	public void setEstadoComprobante(List<String> estadoComprobante) {
		this.estadoComprobante = estadoComprobante;
	}

	public List<EstadoComprobantePago> getEstadoComprobantePago() {
		return estadoComprobantePago;
	}

	public void setEstadoComprobantePago(
			List<EstadoComprobantePago> estadoComprobantePago) {
		this.estadoComprobantePago = estadoComprobantePago;
	}

	public String getEstadoCredito() {
		return estadoCredito;
	}

	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

	public List<Long> getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(List<Long> tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public TipoLiquidacion getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	public void setTipoLiquidacion(TipoLiquidacion tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	public String getTipoEmpleador() {
		return tipoEmpleador;
	}

	public void setTipoEmpleador(String tipoEmpleador) {
		this.tipoEmpleador = tipoEmpleador;
	}

        public TipoPrestamista getTipoPrestamista() {
		return tipoPrestamista;
	}

	public void setTipoPrestamista(TipoPrestamista tipoPrestamista) {
		this.tipoPrestamista = tipoPrestamista;
	}

	public Calendar getFechaValidezLiquidacion() {
		return fechaValidezLiquidacion;
	}

	public void setFechaValidezLiquidacion(Calendar fechaValidezLiquidacion) {
		this.fechaValidezLiquidacion = fechaValidezLiquidacion;
	}

	public String getFlujo() {
		return flujo;
	}

	public void setFlujo(String flujo) {
		this.flujo = flujo;
	}

	public CalculoLiquidacion getCalculoLiquidacion() {
		return calculoLiquidacion;
	}

	public void setCalculoLiquidacion(CalculoLiquidacion calculoLiquidacion) {
		this.calculoLiquidacion = calculoLiquidacion;
	}
}
