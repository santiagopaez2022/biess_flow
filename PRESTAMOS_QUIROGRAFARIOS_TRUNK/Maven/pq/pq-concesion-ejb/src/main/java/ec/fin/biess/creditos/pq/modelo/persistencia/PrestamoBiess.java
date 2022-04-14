/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ec.gov.iess.creditos.enumeracion.DestinoComercialEnum;

/**
 * Entity bean para la tabla Kscretcreditos.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
@Entity
@Table(name = "Kscretcreditos")
@NamedQueries({
		/* Lista de prestamos de un afiliado dados los estados y tipos. */
		@NamedQuery(name = "PrestamoBiess.prestamosByRange",
				query = " select o from PrestamoBiess o where o.numafi = :cedula "
						+ " and o.estadoPrestamo.obtestpre in (:estadosCredito) "
						+ " and o.prestamoPk.codprecla in (:tiposCredito) order by o.fecpreafi desc "),

		/* Lista de prestamos de un afiliado dados los estados y tipos. */
		@NamedQuery(name = "PrestamoBiess.prestamosByCedulaEstadosTipoCredito",
				query = " select o from PrestamoBiess o where o.numafi = :cedula "
						+ " and o.estadoPrestamo.codestpre in (:estadosCredito) "
						+ " and o.prestamoPk.codprecla in (:tiposCredito) order by o.fecpreafi desc "),
						
		
		/* Obtiene el numero de prestamos de un afiliado dados los estados y tipos */
		@NamedQuery(name = "Prestamo.rowCount",
				query = " select count(o.numafi) from PrestamoBiess o where o.numafi = :cedula "
						+ " and o.estadoPrestamo.obtestpre in (:estadosCredito) "
						+ " and o.prestamoPk.codprecla in (:tiposCredito) order by o.fecpreafi desc "),
		
		/* Obtiene el numero de prestamos de un afiliado dados los estados y tipos */
		@NamedQuery(name = "Prestamo.rowCountIntra",
				query = " select count(o.numafi) from PrestamoBiess o where o.numafi = :cedula "
						+ " and o.estadoPrestamo.codestpre in (:estadosCredito) "
						+ " and o.prestamoPk.codprecla in (:tiposCredito) order by o.fecpreafi desc "),
										
		/* Obtiene prestamos en estado de bloqueo */
		@NamedQuery(name = "PrestamoBiess.prestamosEstadoBloqueo",
				query = " select o from PrestamoBiess o where (o.estadoPrestamo.codestpre = :estadoNormal "
						+ " or (o.estadoPrestamo.codestpre = :estadoVig and o.validacionRegistroCivil = :estadoNovacion)) "
						+ " and o.numafi = :cedula ") ,
		
		@NamedQuery(name = "Prestamo.consultarPorNumOpId", query = "select o from PrestamoBiess o where o.numOperacionSAC = :numOpSAC AND o.numafi = :identificacion")
		

})
public class PrestamoBiess implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrestamoBiessPK prestamoPk;

	@Column(nullable = false)
	private String numafi;

	@Column(nullable = false)
	private Date fecpreafi;

	@Column(nullable = false)
	private Long plzmes;

	@Column(nullable = false)
	private Double valsalcap;

	@Column(name = "cr_reg_civil")
	private String validacionRegistroCivil;

	@ManyToOne
	@JoinColumn(name = "CODESTPRE", referencedColumnName = "CODESTPRE")
	private EstadoPrestamo estadoPrestamo;

	@ManyToOne
	@JoinColumn(name = "CODPRECLA", referencedColumnName = "CODPRECLA", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private ClasePrestamo clasePrestamo;

	@Transient
	private Long nut;
	
	@Transient
	private Long diasMora;
	
	@Transient
	private Date fechaFinPrestamo;
	
	@Column(name = "CR_OPERACIONSAC")
	private Long numOperacionSAC;
	
	@Column(name = "CR_ESTADOPESAC")
	private String estadoSAC;


	//Bandera para prestamos emergentes
	@Column(name = "CR_CREDITO_ESPECIAL")
	private Long creditoEspecial;

	public PrestamoBiess() {
	}

	
	    public DestinoComercialEnum getDestinoComercial() {
	    	Long codigoProducto=null;
	    	if((prestamoPk.getCodpretip()==4 &&  creditoEspecial!=null) || (prestamoPk.getCodpretip()==1 && creditoEspecial!=null) ) {
	    		codigoProducto=Long.valueOf(prestamoPk.getCodpretip().toString().concat(creditoEspecial.toString()));
	    	}else {
	    		codigoProducto=prestamoPk.getCodpretip();
	    	}
	    	
	    	return DestinoComercialEnum.getTiposProductosPq(codigoProducto);
	    }
	    

	/**
	 * @return the creditoPk
	 */
	public PrestamoBiessPK getCreditoPk() {
		return prestamoPk;
	}

	/**
	 * @param creditoPk
	 *            the creditoPk to set
	 */
	public void setCreditoPk(PrestamoBiessPK creditoPk) {
		this.prestamoPk = creditoPk;
	}

	/**
	 * @return the prestamoPk
	 */
	public PrestamoBiessPK getPrestamoPk() {
		return prestamoPk;
	}

	/**
	 * @param prestamoPk
	 *            the prestamoPk to set
	 */
	public void setPrestamoPk(PrestamoBiessPK prestamoPk) {
		this.prestamoPk = prestamoPk;
	}

	/**
	 * @return the numafi
	 */
	public String getNumafi() {
		return numafi;
	}

	/**
	 * @param numafi
	 *            the numafi to set
	 */
	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	/**
	 * @return the fecpreafi
	 */
	public Date getFecpreafi() {
		return fecpreafi;
	}

	/**
	 * @param fecpreafi
	 *            the fecpreafi to set
	 */
	public void setFecpreafi(Date fecpreafi) {
		this.fecpreafi = fecpreafi;
	}

	/**
	 * @return the plzmes
	 */
	public Long getPlzmes() {
		return plzmes;
	}

	/**
	 * @param plzmes
	 *            the plzmes to set
	 */
	public void setPlzmes(Long plzmes) {
		this.plzmes = plzmes;
	}

	/**
	 * @return the valsalcap
	 */
	public Double getValsalcap() {
		return valsalcap;
	}

	/**
	 * @param valsalcap
	 *            the valsalcap to set
	 */
	public void setValsalcap(Double valsalcap) {
		this.valsalcap = valsalcap;
	}

	/**
	 * @return the validacionRegistroCivil
	 */
	public String getValidacionRegistroCivil() {
		return validacionRegistroCivil;
	}

	/**
	 * @param validacionRegistroCivil
	 *            the validacionRegistroCivil to set
	 */
	public void setValidacionRegistroCivil(String validacionRegistroCivil) {
		this.validacionRegistroCivil = validacionRegistroCivil;
	}

	/**
	 * @return the estadoPrestamo
	 */
	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	/**
	 * @param estadoPrestamo
	 *            the estadoPrestamo to set
	 */
	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	/**
	 * @return the clasePrestamo
	 */
	public ClasePrestamo getClasePrestamo() {
		return clasePrestamo;
	}

	/**
	 * @param clasePrestamo
	 *            the clasePrestamo to set
	 */
	public void setClasePrestamo(ClasePrestamo clasePrestamo) {
		this.clasePrestamo = clasePrestamo;
	}

	public Long getNut() {
		return nut;
	}

	public void setNut(Long nut) {
		this.nut = nut;
	}

	public Long getDiasMora() {
		return diasMora;
	}

	public void setDiasMora(Long diasMora) {
		this.diasMora = diasMora;
	}

	public Date getFechaFinPrestamo() {
		return fechaFinPrestamo;
	}

	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

	public Long getNumOperacionSAC() {
		return numOperacionSAC;
	}

	public void setNumOperacionSAC(Long numOperacionSAC) {
		this.numOperacionSAC = numOperacionSAC;
	}

	public String getEstadoSAC() {
		return estadoSAC;
	}

	public void setEstadoSAC(String estadoSAC) {
		this.estadoSAC = estadoSAC;
	}

	public Long getCreditoEspecial() {
		return creditoEspecial;
	}

	public void setCreditoEspecial(Long creditoEspecial) {
		this.creditoEspecial = creditoEspecial;
	}



}
