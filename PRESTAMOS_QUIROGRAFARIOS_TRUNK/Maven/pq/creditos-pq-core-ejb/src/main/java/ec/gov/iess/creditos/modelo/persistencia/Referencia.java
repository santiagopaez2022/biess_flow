package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Daniel Cardenas
 * 
 */
@Entity
@Table(name = "CRE_REFERENCIASSOLICITUD_TBL")
@SequenceGenerator(name = "cre_referenciassolicitud_seq", sequenceName = "cre_referenciassolicitud_seq", allocationSize = 1)
public class Referencia implements Serializable {

	private static final long serialVersionUID = 670294599674605461L;

	@Id
	@Column(name = "SECREF")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cre_referenciassolicitud_seq")
	private Long id;
	@Column(name = "REFNOM")
	private String nombre;
	@Column(name = "REFTEL")
	private String telefono;
	@Column(name = "REFTELTRA")
	private String telefonoTrabajo;
	@Column(name = "REFTELCEL")
	private String celular;
	@Column(name = "REFREL")
	private String relacion; // familiar, amigo

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns( {
			@JoinColumn(name = "CODTIPSOLSER", referencedColumnName = "CODTIPSOLSER"),
			@JoinColumn(name = "NUMSOLSER", referencedColumnName = "NUMSOLSER") })
	private SolicitudCredito solicitudCredito;

	public Referencia() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombres) {
		this.nombre = nombres;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the telefonoTrabajo
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	/**
	 * @param telefonoTrabajo
	 *            the telefonoTrabajo to set
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the relacion
	 */
	public String getRelacion() {
		return relacion;
	}

	/**
	 * @param relacion
	 *            the relacion to set
	 */
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	/**
	 * @return the solicitudCredito
	 */
	public SolicitudCredito getSolicitudCredito() {
		return solicitudCredito;
	}

	/**
	 * @param solicitudCredito
	 *            the solicitudCredito to set
	 */
	public void setSolicitudCredito(SolicitudCredito solicitudCredito) {
		this.solicitudCredito = solicitudCredito;
	}
}
