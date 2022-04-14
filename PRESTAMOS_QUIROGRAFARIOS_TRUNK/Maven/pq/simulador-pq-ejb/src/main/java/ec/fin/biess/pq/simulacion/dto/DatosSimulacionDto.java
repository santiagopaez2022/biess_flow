package ec.fin.biess.pq.simulacion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TiposProductosPq;
import ec.gov.iess.creditos.modelo.dto.DatosGarantia;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.dto.InformacionGarantias;

/**
 * DTO que contiene parametros para obtener los ParametrosCalculoCreditoDto
 * 
 * @author hugo.mora
 * @date 2017/05/16
 *
 */
public class DatosSimulacionDto implements Serializable {

	private static final long serialVersionUID = 7236282928639139191L;

	private String cedula;

	// El tipo prestamista son las opciones que se escogen en el aplicativo (afiliado, jubilado, zafrero)
	private TipoPrestamista tipoPrestamista;

	// El rol prestamista puede ser afiliado, jubilado, zafrero, afiliado-jubilado
	private TipoPrestamista rolPrestamista;

	private TiposProductosPq tipoProducto;

	// Bandera para indicar si es novacion (true es novacion, false es concesion)
	private boolean esNovacion;

	// Clave primaria del prestamo a novar, tiene valor cuando es novacion, caso contrario es null
	private PrestamoPk prestamoPkNovacion;

	private BigDecimal sueldoPromedioAfiliado;

	private BigDecimal valorCesantia;

	private InformacionPrestacionPensionado informacionPrestacionPensionado;

	/**
	 * Se pasa el prestamo novar en la simulacion
	 */
	private Prestamo prestamoNovar;

	/**
	 * Informacion de las garantias
	 */
	private InformacionGarantias infoGarantia;
	
	/**
	 * Datos garantia
	 */
	private DatosGarantia datGarantia;
	
	/**
	 * Lista de creditos exigibles obtenidos
	 */
	private List<CreditoExigibleDto> listaCreditos;
	
	
	
	// Getters and setters
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

	public TiposProductosPq getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TiposProductosPq tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public boolean isEsNovacion() {
		return esNovacion;
	}

	public void setEsNovacion(boolean esNovacion) {
		this.esNovacion = esNovacion;
	}

	public PrestamoPk getPrestamoPkNovacion() {
		return prestamoPkNovacion;
	}

	public void setPrestamoPkNovacion(PrestamoPk prestamoPkNovacion) {
		this.prestamoPkNovacion = prestamoPkNovacion;
	}

	public BigDecimal getSueldoPromedioAfiliado() {
		return sueldoPromedioAfiliado;
	}

	public void setSueldoPromedioAfiliado(BigDecimal sueldoPromedioAfiliado) {
		this.sueldoPromedioAfiliado = sueldoPromedioAfiliado;
	}

	public BigDecimal getValorCesantia() {
		return valorCesantia;
	}

	public void setValorCesantia(BigDecimal valorCesantia) {
		this.valorCesantia = valorCesantia;
	}

	public InformacionPrestacionPensionado getInformacionPrestacionPensionado() {
		return informacionPrestacionPensionado;
	}

	public void setInformacionPrestacionPensionado(InformacionPrestacionPensionado informacionPrestacionPensionado) {
		this.informacionPrestacionPensionado = informacionPrestacionPensionado;
	}

	public TipoPrestamista getRolPrestamista() {
		return rolPrestamista;
	}

	public void setRolPrestamista(TipoPrestamista rolPrestamista) {
		this.rolPrestamista = rolPrestamista;
	}

	public Prestamo getPrestamoNovar() {
		return prestamoNovar;
	}

	public void setPrestamoNovar(Prestamo prestamoNovar) {
		this.prestamoNovar = prestamoNovar;
	}

	public InformacionGarantias getInfoGarantia() {
		return infoGarantia;
	}

	public void setInfoGarantia(InformacionGarantias infoGarantia) {
		this.infoGarantia = infoGarantia;
	}

	public DatosGarantia getDatGarantia() {
		return datGarantia;
	}

	public void setDatGarantia(DatosGarantia datGarantia) {
		this.datGarantia = datGarantia;
	}

	public List<CreditoExigibleDto> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(List<CreditoExigibleDto> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}



}
