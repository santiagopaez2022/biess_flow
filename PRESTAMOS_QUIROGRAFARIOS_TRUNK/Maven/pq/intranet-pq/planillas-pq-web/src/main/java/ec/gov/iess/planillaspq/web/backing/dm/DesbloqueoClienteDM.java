/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.planillaspq.web.backing.dm;

import java.util.Date;
import java.util.List;

import org.richfaces.component.html.HtmlDataTable;

import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancariaAfiliado;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.Sucursal;

/**
 * Clase Data Manager de Desbloqueo Cliente
 * 
 * @author pjarrin
 * 
 */
public class DesbloqueoClienteDM {

	private Date fechaDesde = new Date();

	private Date fechaHasta = new Date();

	private String cedula;

	private String estadoPrestamo;

	private List<Prestamo> listaPrestamo;

	private HtmlDataTable tblSolicitud;

	private Sucursal sucursal;

	private DivisionPolitica divisionPolitica;

	private Date fechaBancaria;

	private CuentaBancariaAfiliado cuentaBancaria;

	private CuentaBancariaAfiliado cuetanBancariaReporte;

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setListaPrestamo(List<Prestamo> listaPrestamo) {
		this.listaPrestamo = listaPrestamo;
	}

	public List<Prestamo> getListaPrestamo() {
		return listaPrestamo;
	}

	public void setTblSolicitud(HtmlDataTable tblSolicitud) {
		this.tblSolicitud = tblSolicitud;
	}

	public HtmlDataTable getTblSolicitud() {
		return tblSolicitud;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setDivisionPolitica(DivisionPolitica divisionPolitica) {
		this.divisionPolitica = divisionPolitica;
	}

	public DivisionPolitica getDivisionPolitica() {
		return divisionPolitica;
	}

	public void setFechaBancaria(Date fechaBancaria) {
		this.fechaBancaria = fechaBancaria;
	}

	public Date getFechaBancaria() {
		return fechaBancaria;
	}

	public void setCuentaBancaria(CuentaBancariaAfiliado cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public CuentaBancariaAfiliado getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuetanBancariaReporte(
			CuentaBancariaAfiliado cuetanBancariaReporte) {
		this.cuetanBancariaReporte = cuetanBancariaReporte;
	}

	public CuentaBancariaAfiliado getCuetanBancariaReporte() {
		return cuetanBancariaReporte;
	}

	public void setEstadoPrestamo(String estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public String getEstadoPrestamo() {
		return estadoPrestamo;
	}

}
