package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/** 
 * <b>
 * Clase que mapea la tabla [CRE_DOCUMENTACIONTIPO_TBL], que trae documentos para Jubilados , 
 * Activos, voluntarios.
 * </b>
 *  
 * @author [Modificacion]Ron@ld Barrer@ rbar.ec@gmail.com
 * @version $Revision: 1.0 $ <p>[$Author: Ron@ld Barrer@ rbar.ec@gmail.com $, $Date: 03/10/2011 $]</p>
*/ 
@Entity
@Table(name = "CRE_DOCUMENTACIONTIPO_TBL")
@NamedQueries( {
		@NamedQuery(name = "DocumentacionRequeridaDetalle.findByProvinciaAndTipoSolicitudAndVendedorTipoPersonaAndTipoBien", query = "SELECT o FROM DocumentacionRequeridaDetalle o WHERE "
				+ "o.idProvincia = :idProvincia AND o.idTipoSolicitud = :idTipoSolicitud AND o.vendedorTipoPersona = :vendedorTipoPersona AND o.tipoBien = :tipoBien ORDER BY o.documentacionRequerida.id"),
		//Modificado para Voluntarios
		@NamedQuery(name = "DocumentacionRequeridaDetalle.ObtenerTipoSolVendTipoPerTipoBienTipoAfivProvin", query = "SELECT o FROM DocumentacionRequeridaDetalle o WHERE "
			+ "o.idProvincia = :idProvincia AND o.idTipoSolicitud = :idTipoSolicitud AND o.vendedorTipoPersona = :vendedorTipoPersona AND o.tipoBien = :tipoBien AND o.tipoAfiliado = :tipoAfiliado ORDER BY o.documentacionRequerida.id"),
		@NamedQuery(name = "DocumentacionRequeridaDetalle.findByTipoSolicitud", query = "SELECT o FROM DocumentacionRequeridaDetalle o WHERE "
		+ " o.idTipoSolicitud = :idTipoSolicitud  ORDER BY o.documentacionRequerida.id") })
public class DocumentacionRequeridaDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4507419635343336897L;

	@Id
	@Column(name = "SECDOCTIP")
	private Long id;

	@Column(name = "CODTIPSOLSER")
	private Long idTipoSolicitud;

	@Column(name = "CODIVPOL")
	private String idProvincia;

	@Column(name = "VENTIPPER")
	private String vendedorTipoPersona;

	@Column(name = "TIPBIEN")
	private String tipoBien;
	
	/**
	 * AJ - Activo Jubilado
	 */
	@Column(name = "DT_TIPOAFILIADO")
	private String tipoAfiliado;
	
	@ManyToOne
	@JoinColumn(name = "CODDOC")
	private DocumentacionRequerida documentacionRequerida;

	/**
	 * @return the documentacionRequerida
	 */
	public DocumentacionRequerida getDocumentacionRequerida() {
		return documentacionRequerida;
	}

	/**
	 * @param documentacionRequerida
	 *            the documentacionRequerida to set
	 */
	public void setDocumentacionRequerida(
			DocumentacionRequerida documentacionRequerida) {
		this.documentacionRequerida = documentacionRequerida;
	}

	public DocumentacionRequeridaDetalle() {

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
	 * @return the idTipoSolicitud
	 */
	public Long getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	/**
	 * @param idTipoSolicitud
	 *            the idTipoSolicitud to set
	 */
	public void setIdTipoSolicitud(Long idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	/**
	 * @return the idProvincia
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia
	 *            the idProvincia to set
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	/**
	 * @return the vendedorTipoPersona
	 */
	public String getVendedorTipoPersona() {
		return vendedorTipoPersona;
	}

	/**
	 * @param vendedorTipoPersona
	 *            the vendedorTipoPersona to set
	 */
	public void setVendedorTipoPersona(String vendedorTipoPersona) {
		this.vendedorTipoPersona = vendedorTipoPersona;
	}

	/**
	 * @return the tipoBien
	 */
	public String getTipoBien() {
		return tipoBien;
	}

	/**
	 * @param tipoBien
	 *            the tipoBien to set
	 */
	public void setTipoBien(String tipoBien) {
		this.tipoBien = tipoBien;
	}

	/**
	 * @return the tipoAfiliado
	 */
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	/**
	 * @param tipoAfiliado the tipoAfiliado to set
	 */
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	
}
