package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;

import ec.fin.biess.conozcasucliente.modelo.persistence.Cliente;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancaria;

/**
 * @author Daniel Cardenas 03/10/2011
 * 
 */
public class Solicitante implements Serializable {

	private static final long serialVersionUID = 7512619421219362623L;

	// datos personales
	protected DatosPersonales datosPersonales;

	// cta bancaria
	protected CuentaBancaria cuentaBancaria;

	protected boolean confirmacionMail = false;

	// empleador
	protected Empleador empleador;

	// tipo J - Jubilado, A - Afiliado
	protected TipoPrestamista tipo;

	// aprobado
	protected boolean aprobado = false;

	// origen de jubilado
	protected OrigenJubilado origenJubilado;

	// Solo se usa con jubilados. Guarda el valor total de renta
	// que precibe en su nómina de Origen
	protected double rentaTotalNomina;

	// Dato si es enfermo terminal
	protected boolean esEnfermoTerminal;

	
	
	//Se Aumenta Para Obtener el numero de cargasFamiliares Del Afiliado
	protected int numeroCargas;
	
	// INC-2300 Requerimiento R24
	protected Cliente cliente;
	
	// INC-2460 Conozca a su cliente
	private Cliente clienteDatosComplementarios;
	
	public int getNumeroCargas() {
		return numeroCargas;
	}

	public void setNumeroCargas(int numeroCargas) {
		this.numeroCargas = numeroCargas;
	}
	
	public double getRentaTotalNomina() {
		return rentaTotalNomina;
	}

	public void setRentaTotalNomina(double rentaTotalNomina) {
		this.rentaTotalNomina = rentaTotalNomina;
	}

	public Solicitante() {
	}

	public TipoPrestamista getTipo() {
		return tipo;
	}

	public void setTipo(TipoPrestamista tipo) {
		this.tipo = tipo;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public DatosPersonales getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(DatosPersonales datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	public Empleador getEmpleador() {
		return empleador;
	}

	public void setEmpleador(Empleador empleador) {
		this.empleador = empleador;
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuenta) {
		this.cuentaBancaria = cuenta;
	}

	public OrigenJubilado getOrigenJubilado() {
		return origenJubilado;
	}

	public void setOrigenJubilado(OrigenJubilado origenJubilado) {
		this.origenJubilado = origenJubilado;
	}

	public boolean isConfirmacionMail() {
		return confirmacionMail;
	}

	public void setConfirmacionMail(boolean confirmacionMail) {
		this.confirmacionMail = confirmacionMail;
	}

	public boolean isEsEnfermoTerminal() {
		return esEnfermoTerminal;
	}

	public void setEsEnfermoTerminal(boolean esEnfermoTerminal) {
		this.esEnfermoTerminal = esEnfermoTerminal;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteDatosComplementarios() {
		return clienteDatosComplementarios;
	}

	public void setClienteDatosComplementarios(Cliente clienteDatosComplementarios) {
		this.clienteDatosComplementarios = clienteDatosComplementarios;
	}

}