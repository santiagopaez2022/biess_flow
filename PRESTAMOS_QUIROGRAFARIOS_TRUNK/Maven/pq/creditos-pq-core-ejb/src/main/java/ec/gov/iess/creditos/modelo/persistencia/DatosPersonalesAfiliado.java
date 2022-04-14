/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b> Clase para persistencia de datos adicionales de una solicitud PH </b>
 * 
 * @author asarmiento 03/10/2011
 * @version $Revision: 1.0 $
 *          <p>
 *          
 */
@Entity
@Table(name = "CRE_DATOSPERSONALES_TBL")
@NamedQueries( {
			@NamedQuery(name = "DatosPersonalesAfiliado.Obtener_cedula",query="SELECT o FROM DatosPersonalesUsuario o " +
				"Where o.cedula = :cedula ")
})
@SequenceGenerator(name = "CRE_DATOSPERSONALES_SEQ", sequenceName = "CRE_DATOSPERSONALES_SEQ", allocationSize = 1)		
public class DatosPersonalesAfiliado implements Serializable {

	

	/**
	* 
	*/
	private static final long serialVersionUID = 7161216621009488497L;

	@Id
	@Column(name = "DP_ID", nullable = false)
	@GeneratedValue(generator = "CRE_DATOSPERSONALES_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	

	@Column(name = "DP_CEDULA", nullable = false)
	private String cedula;

	@Column(name = "DP_NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DP_CODDIVPOL")
	private String codDivPol;

	@Column(name = "DP_DIRECCION")
	private String direccion;

	@Column(name = "DP_TELEFONO")
	private String telefono;

	@Column(name = "DP_CELULAR_REF")
	private String celular;

	@Column(name = "DP_GENERO")
	private String genero;

	@Column(name = "DP_NACIONALIDAD")
	private String nacionalidad;

	@Column(name = "DP_FECHANACIMIENTO")
	private Date fechanacimiento;

	

	@Column(name = "DP_POSEEVIVIENDA")
	private String pVivienda;

	@Column(name = "DP_PROVINCIA")
	private String provincia;

	@Column(name = "DP_CANTON")
	private String canton;

	@Column(name = "DP_PARROQUIA")
	private String parroquia;

	@Column(name = "DP_CARGAFAMILIAR")
	private Integer cargaFamiliar;

	@Column(name = "DP_ESTADOCIVIL")
	private String estadoCivil;
	
	@Column(name = "DP_EMAIL")
	private String email;
	
	@Column(name = "DP_ULTIMAFECHACTUALIZACION")
	private Date ultimaFecha;
	 
	
	public Date getUltimaFecha() {
		return ultimaFecha;
	}

	public void setUltimaFecha(Date ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodDivPol() {
		return codDivPol;
	}

	public void setCodDivPol(String codDivPol) {
		this.codDivPol = codDivPol;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getpVivienda() {
		return pVivienda;
	}

	public void setpVivienda(String pVivienda) {
		this.pVivienda = pVivienda;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public Integer getCargaFamiliar() {
		return cargaFamiliar;
	}

	public void setCargaFamiliar(Integer cargaFamiliar) {
		this.cargaFamiliar = cargaFamiliar;
	}
	
	

	
}
