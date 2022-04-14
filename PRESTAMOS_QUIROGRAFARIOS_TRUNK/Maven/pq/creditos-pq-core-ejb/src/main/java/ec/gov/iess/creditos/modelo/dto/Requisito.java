package ec.gov.iess.creditos.modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ec.gov.iess.creditos.enumeracion.IdentificadorRequisito;
import ec.gov.iess.creditos.enumeracion.TipoRequisito;
import ec.gov.iess.creditos.enumeracion.TipoRequisitoSimulacionEnum;

/**
 * @author Daniel Cardenas
 * @author cvillarreal
 * 
 */
public class Requisito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -374843916063221638L;
	private String descripcion;
	private boolean aprobado;
	private String observacion;
	private String url;
	private List<TipoRequisito> tipoRequisitos  = new ArrayList<TipoRequisito>();
	private boolean tieneMensajeAdicional = false;
	private String mensajeAdicional = "";
	private IdentificadorRequisito identificador = IdentificadorRequisito.PRECALIFICACION;
	private Long idRequisito;
	private TipoRequisitoSimulacionEnum tipoRequisitoSimulacion = TipoRequisitoSimulacionEnum.NO_BLOQUEANTE;
	
	
	/**
	 * @return the idRequisito
	 */
	public Long getIdRequisito() {
		return idRequisito;
	}

	/**
	 * @param idRequisito the idRequisito to set
	 */
	public void setIdRequisito(Long idRequisito) {
		this.idRequisito = idRequisito;
	}

	public Requisito() {
	}

	public Requisito(String descripcion, boolean aprobado, String observacion,
			String url) {
		super();
		this.descripcion = descripcion;
		this.aprobado = aprobado;
		this.observacion = observacion;
		this.url = url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String nombre) {
		this.descripcion = nombre;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuffer msg = new StringBuffer();

		msg.append("RES : " + this.isAprobado() + " REQ : " + this.descripcion
				+ " OBS : " + this.observacion);

		return msg.toString();
	}

	
	/**
	 * @return the tipoRequisitos
	 */
	public List<TipoRequisito> getTipoRequisitos() {
		return tipoRequisitos;
	}

	/**
	 * @param tipoRequisitos the tipoRequisitos to set
	 */
	public void setTipoRequisitos(List<TipoRequisito> tipoRequisitos) {
		this.tipoRequisitos = tipoRequisitos;
	}

	/**
	 * @return the tieneMensajeAdicional
	 */
	public boolean isTieneMensajeAdicional() {
		return tieneMensajeAdicional;
	}

	/**
	 * @param tieneMensajeAdicional the tieneMensajeAdicional to set
	 */
	public void setTieneMensajeAdicional(boolean tieneMensajeAdicional) {
		this.tieneMensajeAdicional = tieneMensajeAdicional;
	}

	/**
	 * @return the mensajeAdicional
	 */
	public String getMensajeAdicional() {
		return mensajeAdicional;
	}

	/**
	 * @param mensajeAdicional the mensajeAdicional to set
	 */
	public void setMensajeAdicional(String mensajeAdicional) {
		this.mensajeAdicional = mensajeAdicional;
	}

	/**
	 * @return the identificador
	 */
	public IdentificadorRequisito getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(IdentificadorRequisito identificador) {
		this.identificador = identificador;
	}

	public TipoRequisitoSimulacionEnum getTipoRequisitoSimulacion() {
		return tipoRequisitoSimulacion;
	}

	public void setTipoRequisitoSimulacion(TipoRequisitoSimulacionEnum tipoRequisitoSimulacion) {
		this.tipoRequisitoSimulacion = tipoRequisitoSimulacion;
	}

}