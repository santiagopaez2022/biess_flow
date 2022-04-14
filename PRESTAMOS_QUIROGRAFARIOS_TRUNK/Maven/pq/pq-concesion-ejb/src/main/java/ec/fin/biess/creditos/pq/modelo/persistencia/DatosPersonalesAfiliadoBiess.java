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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity bean tabla CRE_DATOSPERSONALES_TBL.
 * 
 * @author Omar Villanueva
 * @version 1.0
 *
 */
@Entity
@Table(name = "CRE_DATOSPERSONALES_TBL")
@NamedQueries({ @NamedQuery(name = "DatosPersonalesAfiliadoBiess.consultarPorCedula",
		query = "select o from DatosPersonalesAfiliadoBiess o where o.cedula = :cedula order by o.ultimaFecha desc") })
@SequenceGenerator(name = "CRE_DATOSPERSONALES_SEQ", sequenceName = "CRE_DATOSPERSONALES_SEQ", allocationSize = 1)
public class DatosPersonalesAfiliadoBiess implements Serializable {

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
	
	@Column(name = "DP_DISCAPACITADO")
	private String discapacitado;
	

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

	/**
	 * @param discapacitado the discapacitado to set
	 */
	public void setDiscapacitado(String discapacitado) {
		this.discapacitado = discapacitado;
	}

	/**
	 * @return the discapacitado
	 */
	public String getDiscapacitado() {
		return discapacitado;
	}

}
