/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */

package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ec.gov.iess.cuentabancaria.modelo.InstitucionFinanciera;

/**
 * 
 * <b> Clase de persistencia del objeto proveedor. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:11 $]
 *          </p>
 */
@Entity
@Table(name = "CRE_PROVEEDORES_TBL")
@NamedQueries( {
		@NamedQuery(name = "Proveedor.consultarProveedor", query = "SELECT c FROM Proveedor c WHERE "
				+ "c.detalleCatalogo.id.caCatalogo=:catalogoEstado AND c.detalleCatalogo.id.dcCodigo = :estado"),
		@NamedQuery(name = "Proveedor.consultarProveedorRuc", query = "SELECT o FROM Proveedor o "
				+ " WHERE o.prRuc=:rucEmp AND "
				+ "o.detalleCatalogo.id.caCatalogo=:catalogoEstado AND o.detalleCatalogo.id.dcCodigo = :estado"),
		@NamedQuery(name = "Proveedor.consultarProveedorRucCodpretip", query = "SELECT o FROM Proveedor o "
				+ " WHERE o.prRuc=:rucEmp AND "
				+ " o.detalleCatalogo.id.caCatalogo=:catalogoEstado AND o.detalleCatalogo.id.dcCodigo = :estado "
				+ " AND o.tipoPrestamoProducto.codigo = :codpretip "),
		@NamedQuery(name = "Proveedor.consultarProveedorActivoCodpretip", query = "SELECT o FROM Proveedor o "
				+ " WHERE o.detalleCatalogo.id.caCatalogo=:catalogoEstado AND o.detalleCatalogo.id.dcCodigo = :estado "
				+ " AND o.tipoPrestamoProducto.codigo = :codpretip ")
})
public class Proveedor implements Serializable {
	@Id
	@Column(name = "PR_ID_PROVEEDOR")
	private long prIdProveedor;

	@Column(name = "PR_RUC")
	private String prRuc;

	@Column(name = "PR_CODSUC")
	private String codigoSucursal;

	@Column(name = "PR_NOMBRE")
	private String prNombre;

	@Column(name = "PR_CEDULA_REPLEGAL")
	private String prCedulaReplegal;

	@Column(name = "PR_NOMBRE_REPLGAL")
	private String prNombreReplgal;

	@Column(name = "PR_CEDREP_CTACORRIENTE")
	private String prCedrepCtacorriente;

	@Column(name = "PR_TELEFONO_REPLEGAL")
	private String prTelefonoReplegal;

	@Column(name = "PR_NUM_CUENTA")
	private String prNumCuenta;

	@Column(name = "PR_RUC_INSTFINANCIERA")
	private String prRucInstfinanciera;

	@Transient
	private InstitucionFinanciera institucionFinanciera;

	@Column(name = "PR_TIPO_CUENTA")
	private String prTipoCuenta;

	@Column(name = "PR_DIRECCION")
	private String prDireccion;

	@Column(name = "PR_CORREO_CONTACTO")
	private String prCorreoContacto;

	@Column(name = "PR_TELEFONO_PROVEEDOR")
	private String prTelefonoProveedor;

	// asociacion a DetalleCatalogos
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "CA_CATALOGO_ESTADO", referencedColumnName = "CA_CATALOGO",insertable=false,updatable=false),
			@JoinColumn(name = "DC_CODIGO_ESTADO", referencedColumnName = "DC_CODIGO",insertable=false,updatable=false) })
	private DetalleCatalogos detalleCatalogo;

	private static final long serialVersionUID = 1L;
	
	// INC-2272 Pensiones Alimenticias
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PR_CODPRETIP")
	private TipoPrestamo tipoPrestamoProducto;

	//INC-2286 Ferrocarriles
	@Column(name="PR_CODWSPRETIP")
	private String codigoAccesoWS;
	
	@Column(name = "CA_CATALOGO_ESTADO")
	private String estadoCatalogo;

	@Column(name = "DC_CODIGO_ESTADO")
	private String estado;
	
	@Column(name = "PR_RAZON_SOCIAL")
	private String razonSocial;
	
	@Column(name = "PR_CEDULA_CONTACTO")
	private String cedulaContacto;
	
	@Column(name = "PR_NOMBRE_CONTACTO")
	private String NombreContacto;
	
	@Column(name = "PR_TELEFONO_CONTACTO")
	private String telefonoContacto;
	
	@Column(name = "PR_DIRECCION_CONTACTO")
	private String direccionContacto;
	
	@Column(name = "PR_USUARIO_CONTACTO")
	private String usuarioContacto;
	
	@Column(name = "PR_CLAVE_CONTACTO")
	private String claveContacto;
	
	@Column(name = "PR_ESTADO_CONTACTO")
	private String estadoContacto;
	
	@Column(name = "PR_FECHA_ACTUALIZACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	
	@Column(name = "PR_CEDULA_FUNCIONARIO")
	private String cedulaFuncionario;
	

	public Proveedor() {
		super();
	}

	/**
	 * 
	 * <b> Identificador del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return long Identificador del proveedor
	 */
	public long getPrIdProveedor() {
		return this.prIdProveedor;
	}

	/**
	 * 
	 * <b> Identificador del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prIdProveedor
	 *            : Identificador del proveedor
	 */
	public void setPrIdProveedor(long prIdProveedor) {
		this.prIdProveedor = prIdProveedor;
	}

	/**
	 * 
	 * <b> Ruc del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Ruc del Proveedor
	 */
	public String getPrRuc() {
		return this.prRuc;
	}

	/**
	 * 
	 * <b> Ruc del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prRuc
	 *            : Ruc del proveedor
	 */
	public void setPrRuc(String prRuc) {
		this.prRuc = prRuc;
	}

	/**
	 * 
	 * <b> Nombre del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Nombre del proveedor
	 */
	public String getPrNombre() {
		return this.prNombre;
	}

	/**
	 * 
	 * <b> Nombre del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prNombre
	 *            : Nombre del proveedor
	 */
	public void setPrNombre(String prNombre) {
		this.prNombre = prNombre;
	}

	/**
	 * 
	 * <b> Cédula del representante legal. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Cédula del representante legal
	 */
	public String getPrCedulaReplegal() {
		return this.prCedulaReplegal;
	}

	/**
	 * 
	 * <b> Cédula del representante legal. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prCedulaReplegal
	 *            : Cédula del representante legal
	 */
	public void setPrCedulaReplegal(String prCedulaReplegal) {
		this.prCedulaReplegal = prCedulaReplegal;
	}

	/**
	 * 
	 * <b> Nombre del representante legal. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Nombre del representante legal
	 */
	public String getPrNombreReplgal() {
		return this.prNombreReplgal;
	}

	/**
	 * 
	 * <b> Nombre del representante legal. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prNombreReplgal
	 *            : Nombre del representante legal
	 */
	public void setPrNombreReplgal(String prNombreReplgal) {
		this.prNombreReplgal = prNombreReplgal;
	}

	/**
	 * 
	 * <b> Cédula del representante de la cuenta corriente. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Cédula del representante de la cuenta corriente
	 */
	public String getPrCedrepCtacorriente() {
		return this.prCedrepCtacorriente;
	}

	/**
	 * 
	 * <b> Cédula del representante de la cuenta corriente. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prCedrepCtacorriente
	 *            : Cédula del representante de la cuenta corriente
	 */
	public void setPrCedrepCtacorriente(String prCedrepCtacorriente) {
		this.prCedrepCtacorriente = prCedrepCtacorriente;
	}

	/**
	 * 
	 * <b> Teléfono del representante legal. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Teléfono del representante legal
	 */
	public String getPrTelefonoReplegal() {
		return this.prTelefonoReplegal;
	}

	/**
	 * 
	 * <b> ITeléfono del representante legal. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prTelefonoReplegal
	 *            : Teléfono del representante legal
	 */
	public void setPrTelefonoReplegal(String prTelefonoReplegal) {
		this.prTelefonoReplegal = prTelefonoReplegal;
	}

	/**
	 * 
	 * <b> Número de cuenta del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Número de cuenta del proveedor
	 */
	public String getPrNumCuenta() {
		return this.prNumCuenta;
	}

	/**
	 * 
	 * <b> Número de cuenta del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prNumCuenta
	 *            : Número de cuenta del proveedor
	 */
	public void setPrNumCuenta(String prNumCuenta) {
		this.prNumCuenta = prNumCuenta;
	}

	/**
	 * 
	 * <b> Tipo de cuenta del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Tipo de cuenta del proveedor
	 */
	public String getPrTipoCuenta() {
		return this.prTipoCuenta;
	}

	/**
	 * 
	 * <b> Tipo de cuenta del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prTipoCuenta
	 *            : Tipo de cuenta del proveedor
	 */
	public void setPrTipoCuenta(String prTipoCuenta) {
		this.prTipoCuenta = prTipoCuenta;
	}

	/**
	 * 
	 * <b> Dirección del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Dirección del proveedor
	 */
	public String getPrDireccion() {
		return this.prDireccion;
	}

	/**
	 * 
	 * <b> Dirección del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prDireccion
	 *            : Dirección del proveedor
	 */
	public void setPrDireccion(String prDireccion) {
		this.prDireccion = prDireccion;
	}

	/**
	 * 
	 * <b> Correo de contacto. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Correo de contacto
	 */
	public String getPrCorreoContacto() {
		return this.prCorreoContacto;
	}

	/**
	 * 
	 * <b> Correo de contacto. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prCorreoContacto
	 *            : Correo de contacto
	 */
	public void setPrCorreoContacto(String prCorreoContacto) {
		this.prCorreoContacto = prCorreoContacto;
	}

	/**
	 * 
	 * <b> Teléfono del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Teléfono del proveedor
	 */
	public String getPrTelefonoProveedor() {
		return this.prTelefonoProveedor;
	}

	/**
	 * 
	 * <b> Teléfono del proveedor. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prTelefonoProveedor
	 *            : Teléfono del proveedor
	 */
	public void setPrTelefonoProveedor(String prTelefonoProveedor) {
		this.prTelefonoProveedor = prTelefonoProveedor;
	}	

	/**
	 * 
	 * <b> Ruc de institución financiera en el banco central. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return String Ruc de institución financiera en el banco central
	 */
	public String getPrRucInstfinanciera() {
		return prRucInstfinanciera;
	}

	/**
	 * 
	 * <b> Ruc de institución financiera en el banco central. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param prRucInstfinanciera
	 *            : Ruc de institución financiera en el banco central
	 */
	public void setPrRucInstfinanciera(String prRucInstfinanciera) {
		this.prRucInstfinanciera = prRucInstfinanciera;
	}

	/**
	 * 
	 * <b> Institucion financiera en el banco central. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @return InstitucionFinanciera Objeto institución financiera
	 */
	public InstitucionFinanciera getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	/**
	 * 
	 * <b> Institucion financiera en el banco central. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param institucionFinanciera
	 *            : Objeto institución financiera
	 */
	public void setInstitucionFinanciera(
			InstitucionFinanciera institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

	/**
	 * @return the tipoPrestamoProducto
	 */
	public TipoPrestamo getTipoPrestamoProducto() {
		return tipoPrestamoProducto;
	}

	/**
	 * @param tipoPrestamoProducto the tipoPrestamoProducto to set
	 */
	public void setTipoPrestamoProducto(TipoPrestamo tipoPrestamoProducto) {
		this.tipoPrestamoProducto = tipoPrestamoProducto;
	}

	/**
	 * @return codigoAccesoWS
	 */
	public String getCodigoAccesoWS() {
		return codigoAccesoWS;
	}

	/**
	 * @param codigoAccesoWS
	 */
	public void setCodigoAccesoWS(String codigoAccesoWS) {
		this.codigoAccesoWS = codigoAccesoWS;
	}

	/**
	 * @return estadoCatalogo
	 */
	public String getEstadoCatalogo() {
		return estadoCatalogo;
	}

	public void setEstadoCatalogo(String estadoCatalogo) {
		this.estadoCatalogo = estadoCatalogo;
	}

	/**
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return DetalleCatalogos
	 */
	public DetalleCatalogos getDetalleCatalogo() {
		return detalleCatalogo;
	}

	/**
	 * @param detalleCatalogo
	 */
	public void setDetalleCatalogo(DetalleCatalogos detalleCatalogo) {
		this.detalleCatalogo = detalleCatalogo;
	}

	/**
	 * @return the codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @param codigoSucursal the codigoSucursal to set
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the cedulaContacto
	 */
	public String getCedulaContacto() {
		return cedulaContacto;
	}

	/**
	 * @param cedulaContacto the cedulaContacto to set
	 */
	public void setCedulaContacto(String cedulaContacto) {
		this.cedulaContacto = cedulaContacto;
	}

	/**
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return NombreContacto;
	}

	/**
	 * @param nombreContacto the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		NombreContacto = nombreContacto;
	}

	/**
	 * @return the telefonoContacto
	 */
	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	/**
	 * @param telefonoContacto the telefonoContacto to set
	 */
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	/**
	 * @return the direccionContacto
	 */
	public String getDireccionContacto() {
		return direccionContacto;
	}

	/**
	 * @param direccionContacto the direccionContacto to set
	 */
	public void setDireccionContacto(String direccionContacto) {
		this.direccionContacto = direccionContacto;
	}

	/**
	 * @return the usuarioContacto
	 */
	public String getUsuarioContacto() {
		return usuarioContacto;
	}

	/**
	 * @param usuarioContacto the usuarioContacto to set
	 */
	public void setUsuarioContacto(String usuarioContacto) {
		this.usuarioContacto = usuarioContacto;
	}

	/**
	 * @return the claveContacto
	 */
	public String getClaveContacto() {
		return claveContacto;
	}

	/**
	 * @param claveContacto the claveContacto to set
	 */
	public void setClaveContacto(String claveContacto) {
		this.claveContacto = claveContacto;
	}

	/**
	 * @return the estadoContacto
	 */
	public String getEstadoContacto() {
		return estadoContacto;
	}

	/**
	 * @param estadoContacto the estadoContacto to set
	 */
	public void setEstadoContacto(String estadoContacto) {
		this.estadoContacto = estadoContacto;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the cedulaFuncionario
	 */
	public String getCedulaFuncionario() {
		return cedulaFuncionario;
	}

	/**
	 * @param cedulaFuncionario the cedulaFuncionario to set
	 */
	public void setCedulaFuncionario(String cedulaFuncionario) {
		this.cedulaFuncionario = cedulaFuncionario;
	}	

}
