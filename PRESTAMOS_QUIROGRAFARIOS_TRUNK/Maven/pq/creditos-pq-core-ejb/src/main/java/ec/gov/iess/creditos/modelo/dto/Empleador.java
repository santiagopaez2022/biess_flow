package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Daniel Cardenas
 * 
 */
public class Empleador implements Serializable {

	private static final long serialVersionUID = -6834393481661659448L;
	private String empleadorActual;
	private String codigoSucursal;
	private String nombreSucursal;
	private String rucEmpleador;

	// para afiliados
	private Date fechaAfiliacion;
	// para jubilados
	private Date fechaJubilacion;

	private String tipoEmpleador;
	
	private String tipoEmpresa; 

	public String getEmpleadorActual() {
		return empleadorActual;
	}

	public void setEmpleadorActual(String empleadorActual) {
		this.empleadorActual = empleadorActual;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getRucEmpleador() {
		return rucEmpleador;
	}

	public void setRucEmpleador(String rucEmpleador) {
		this.rucEmpleador = rucEmpleador;
	}

	public Date getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(Date fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public Date getFechaJubilacion() {
		return fechaJubilacion;
	}

	public void setFechaJubilacion(Date fechaJubilacion) {
		this.fechaJubilacion = fechaJubilacion;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getTipoEmpleador() {
		return tipoEmpleador;
	}

	public void setTipoEmpleador(String tipoEmpleador) {
		this.tipoEmpleador = tipoEmpleador;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	
}