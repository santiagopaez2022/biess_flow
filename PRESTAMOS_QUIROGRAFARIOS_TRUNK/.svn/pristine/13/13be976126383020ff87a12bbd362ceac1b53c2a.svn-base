/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;

import ec.gov.iess.creditos.modelo.persistencia.pk.SolicitudCreditoPK;

/**
 * 
 * Datos de la solicitud de credito
 * 
 * @version 1.0
 * @author cvillarreal 03/10/2011
 * @author Daniel Cardenas
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Kscretsolicitudes")
/*
 * consultas de objetos
 */
@NamedQueries({

		@NamedQuery(name = "SolicitudCredito.solicitudVigenteSolicitante", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.solicitudCreditoPK.codtipsolser = :tipoSolicitud "
				+ "AND o.numafi = :cedula AND o.codestsolser not in ('CAN','VEN')"),

		@NamedQuery(name = "SolicitudCredito.solicitudVigenteConyugue", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.solicitudCreditoPK.codtipsolser = :tipoSolicitud "
				+ "AND o.numafi = :cedula AND o.codestsolser not in ('CAN','VEN')"),

		@NamedQuery(name = "SolicitudCredito.solicitudByEsatdoAndCedula", query = "SELECT distinct o FROM SolicitudCredito o JOIN o.solicitanteList s "
				+ "WHERE o.solicitudCreditoPK.codtipsolser in (:tiposSolicitud) "
				+ "AND s.numafi = :cedula AND o.codestsolser in (:estados)"),

		@NamedQuery(name = "SolicitudCredito.solicitudPk", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.solicitudCreditoPK.codtipsolser = :tipoSolicitud "
				+ "AND o.solicitudCreditoPK.numsolser = :numeroSolictud "),
		@NamedQuery(name = "SolicitudCredito.findByCedulaSinEstado", query = "SELECT o FROM SolicitudCredito o JOIN o.solicitanteList s "
				+ "WHERE o.solicitudCreditoPK.codtipsolser in (:tipoSolicitud) "
				+ "AND s.numafi = :cedula "
				+ "AND o.codestsolser not in (:listaEstados) "),
		@NamedQuery(name = "SolicitudCredito.findByCedulaConEstado", query = "SELECT o FROM SolicitudCredito o JOIN o.solicitanteList s "
				+ "WHERE o.solicitudCreditoPK.codtipsolser in (:tipoSolicitud) "
				+ "AND s.numafi = :cedula "
				+ "AND o.codestsolser in (:listaEstados) "),
		@NamedQuery(name = "SolicitudCredito.findSolicitudParaGenerarXml", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.solicitudCreditoPK.codtipsolser in (:listaTipoSolicitud) "
				+ "AND o.codestsolser in (:listaEstados) "
				+ "AND o.penoafi not in (:listaNoCargados) "
				+ "AND o.nuttran is null order by o.solicitudCreditoPK.numsolser"),
		@NamedQuery(name = "SolicitudCredito.findSolicitudParaGenerarXmlPorPenoAfi", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.solicitudCreditoPK.codtipsolser in (:listaTipoSolicitud) "
				+ "AND o.codestsolser in (:listaEstados) "
				+ "AND o.penoafi in (:listaPenoAfi) "
				+ "AND o.nuttran is null order by o.solicitudCreditoPK.numsolser"),

		@NamedQuery(name = "SolicitudCredito.findSolicitudPorEstados", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.codestsolser in (:listaEstados) "),
		@NamedQuery(name = "SolicitudCredito.findByNut", query = "select o from SolicitudCredito o where o.nut = :nut"),
		@NamedQuery(name = "SolicitudCredito.findByNuts", query = "select o from SolicitudCredito o where o.nut in (:nuts)"),
		@NamedQuery(name = "SolicitudCredito.findByNuttran", query = "select o from SolicitudCredito o where o.nuttran = :nuttran and o.codestsolser = 'APR'"),
		@NamedQuery(name = "SolicitudCredito.consultarTipoSolicitudProcesoRealizado", query = "SELECT o FROM SolicitudCredito o JOIN o.procesoRealizadoList p "
				+ "WHERE o.solicitudCreditoPK.codtipsolser in (:tipoSolicitud) "
				+ "AND o.codestsolser in (:listaEstados) "
				+ "AND p.estadoProceso.id in (:listaProceosRealizados) "),
		@NamedQuery(name = "SolicitudCredito.consultarDepositoSolicitadoActual", query = "SELECT o FROM SolicitudCredito o JOIN o.detallesolicitudList d "
				+ "WHERE o.nut=:nut AND o.codestsolser = 'SOL' AND d.numeroDeposito=:numeroDeposito"),
		@NamedQuery(name = "SolicitudCredito.consultarDepositoSolicitadoActualDetalle", query = "SELECT o FROM SolicitudCredito o JOIN o.depositoSolicitudList d "
				+ "WHERE o.nut=:nut AND o.codestsolser = 'APR' AND d.numeroPago=:numeroPago"),
		@NamedQuery(name = "SolicitudCredito.gastosAdminsitrativosPorCedula", query = "SELECT o FROM SolicitudCredito o INNER JOIN o.solicitanteList s "
				+ "WHERE o.codestsolser IN ('GAP','REC') "
				+ "AND s.numafi=:cedula "
				+ "AND o.solicitudCreditoPK.codtipsolser in (:codTipSolSerList)"),
		@NamedQuery(name = "SolicitudCredito.solicitudTramite", query = "SELECT o FROM SolicitudCredito o INNER JOIN o.solicitanteList s "
				+ "WHERE o.codestsolser IN (:solicitudList) "
				+ "AND s.numafi=:cedula "
				+ "AND o.solicitudCreditoPK.codtipsolser in (:codTipSolSerList)"),
		@NamedQuery(name = "SolicitudCredito.solicitudesSinCargos", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.codestsolser IN (:estadosList) "
				+ "AND o.solicitudCreditoPK.codtipsolser in (:tipoSolicitud) and o.estRec is null "
				+ "order by nut"),
		@NamedQuery(name = "SolicitudCredito.obtenerSolicitudVigente", query = "SELECT o FROM SolicitudCredito o "
				+ "WHERE o.codestsolser='VIG' AND o.numafi=:cedula "
				+ "AND o.solicitudCreditoPK.codtipsolser in (:tipoSolicitud)"),
		@NamedQuery(name = "SolicitudCredito.obtenerSolicitudLiquidacionAprobada", query = "SELECT distinct(o) FROM SolicitudCredito o "
				+ " join o.liquidacionGastoList l"
				+ " WHERE l.estado = 'GEN' "
				+ " AND o.solicitudCreditoPK.codtipsolser in (:tipoSolicitud)") })
/*
 * consultas nativas
 */
@NamedNativeQueries({

		@NamedNativeQuery(name = "SolicitudCredito.consultarSolicitudesSinAgenciaPorTipoSolicitud", query = "SELECT kscretsolicitudes.nut as NUT "
				+ "FROM kscretsolicitudes,  "
				+ "cre_detallesolicitud_tbl "
				+ "WHERE cre_detallesolicitud_tbl.codtipsolser = kscretsolicitudes.codtipsolser "
				+ "AND cre_detallesolicitud_tbl.numsolser = kscretsolicitudes.numsolser  "
				+ "AND cre_detallesolicitud_tbl.CODAGN is null "
				+ "AND kscretsolicitudes.codtipsolser IN (:lista1) ", resultSetMapping = "solicitudId"),

		@NamedNativeQuery(name = "SolicitudCredito.consultarSolicitudesSinPorcentajeParticipacionPorTipoSolicitud", query = "SELECT kscretsolicitudes.nut as NUT "
				+ "FROM kscretsolicitudes,  "
				+ "cre_solicitante_tbl  "
				+ "WHERE cre_solicitante_tbl.numsolser = kscretsolicitudes.numsolser "
				+ "AND cre_solicitante_tbl.codtipsolser = kscretsolicitudes.codtipsolser "
				+ "AND cre_solicitante_tbl.PORPAR IS NULL  "
				+ "AND kscretsolicitudes.codtipsolser IN (:lista1) ", resultSetMapping = "solicitudId"),

		@NamedNativeQuery(name = "SolicitudCredito.consultarTipoSolicitudUltimoProcesoRealizado", resultClass = SolicitudCredito.class, query = "select o.* from KSCRETSOLICITUDES o,"
				+ " cre_procesosrealizados_tbl p where o.numsolser=p.numsolser and o.codtipsolser=p.codtipsolser and "
				+ "o.CODTIPSOLSER in (:tipoSolicitud) and o.CODESTSOLSER in (:listaEstados) and p.CODESTPRO in (:listaProceosRealizados) and "
				+ "p.secpro in (select max(secpro) from cre_procesosrealizados_tbl p1 where "
				+ "p1.numsolser = o.numsolser and p1.codtipsolser = o.codtipsolser)"
				+ "order by o.nut"),
		@NamedNativeQuery(name = "SolicitudCredito.consultarMaximoDesembolso", query = "SELECT o.* FROM KSCRETSOLICITUDES o "
				+ " WHERE o.nut= :nut "
				+ " AND o.codestsolser = 'APR' "
				+ " AND (SELECT nvl(max(s.NUMDEPDES),0) FROM CRE_DETALLEDEPOSITO_TBL s "
				+ " where s.numsolser=o.numsolser "
				+ " and s.codtipsolser=o.codtipsolser ) <> :numeroPago ", resultClass = SolicitudCredito.class),
		@NamedNativeQuery(name = "SolicitudCredito.obtenerPrestamoVigente", query = "SELECT o.* FROM KSCRETSOLICITUDES o, CRE_SOLICITANTE_TBL c "
				+ " WHERE o.NUMSOLSER=c.NUMSOLSER "
				+ " AND o.CODTIPSOLSER=c.CODTIPSOLSER "
				+ " AND o.CODESTSOLSER='VIG' "
				+ " AND c.NUMAFI=:cedula "
				+ " AND o.CODTIPSOLSER IN (:tipoSolicitud) order by fecsolser desc", resultClass = SolicitudCredito.class),

		@NamedNativeQuery(name = "SolicitudCredito.obtenerSolicitudAprobada", query = "SELECT o.* FROM KSCRETSOLICITUDES o "
				+ " WHERE o.CODESTSOLSER='APR' "
				+ " AND o.NUMAFI=:cedula "
				+ " AND o.CODTIPSOLSER IN (:idTipoProducto) order by fecsolser desc", resultClass = SolicitudCredito.class) })
/*
 * mapeo de consultas nativas
 */
@SqlResultSetMappings({ @SqlResultSetMapping(name = "solicitudId", entities = { @EntityResult(entityClass = SolicitudCredito.class) }) })
public class SolicitudCredito implements Serializable {

	@EmbeddedId
	private SolicitudCreditoPK solicitudCreditoPK;

	@Column(nullable = false)
	private String codestsolser;
	private String codfun;
	private String codfunapr;
	private String codofiies;
	@Column(nullable = false)
	private Date fecsolser;
	private Date fectersolser;
	private String numafi;
	private Long numsecinmhip;

	private String obssolser;
	private String penoafi;
	private BigDecimal sueafisol;
	private Long nut;
	private Long nuttran;

	// geguiguren: solicitado para almacenar IESS o BIESS
	@Column(name = "INSORG")
	private String institucionOrigen;

	@Transient
	private String nombreProyecto; // utilizado en Creditos al Constructor

	// Campos de recaudacion
	private String estRec;
	private Date fecRec;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<SolicitanteCredito> solicitanteList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<DetalleSolicitud> detallesolicitudList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<PrecondicionesSolicitud> precondicionesList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<Referencia> referenciaList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	@OrderBy("id")
	private List<ProcesoRealizado> procesoRealizadoList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	@OrderBy("fechaAnulacion ASC")
	private List<NovedadAnulacion> anulaciones;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	@OrderBy("numeroPago ASC")
	private List<DepositoSolicitud> depositoSolicitudList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<DatoProyectoConstructor> datoProyectoConstructorList;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<OperacionSustitucionHipoteca> operacionesSustitucionHipoteca;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<DetalleBienesSolicitud> detalleBienesAccesorios;

	@OneToMany(mappedBy = "solicitudCredito", cascade = CascadeType.ALL)
	private List<LiquidacionGasto> liquidacionGastoList;

	@Transient
	private boolean seleccionado;

	public List<ProcesoRealizado> getProcesoRealizadoList() {
		return procesoRealizadoList;
	}

	public void setProcesoRealizadoList(
			List<ProcesoRealizado> procesoRealizadoList) {
		this.procesoRealizadoList = procesoRealizadoList;
	}

	public SolicitudCredito() {

	}

	/**
	 * @return the solicitudCreditoPK
	 */
	public SolicitudCreditoPK getSolicitudCreditoPK() {
		return solicitudCreditoPK;
	}

	/**
	 * @return the codestsolser
	 */
	public String getCodestsolser() {
		return codestsolser;
	}

	/**
	 * @return the codfun
	 */
	public String getCodfun() {
		return codfun;
	}

	/**
	 * @return the codfunapr
	 */
	public String getCodfunapr() {
		return codfunapr;
	}

	/**
	 * @return the codofiies
	 */
	public String getCodofiies() {
		return codofiies;
	}

	/**
	 * @return the fecsolser
	 */
	public Date getFecsolser() {
		return fecsolser;
	}

	/**
	 * @return the fectersolser
	 */
	public Date getFectersolser() {
		return fectersolser;
	}

	/**
	 * @return the numafi
	 */
	public String getNumafi() {
		return numafi;
	}

	/**
	 * @return the numsecinmhip
	 */
	public Long getNumsecinmhip() {
		return numsecinmhip;
	}

	/**
	 * @return the obssolser
	 */
	public String getObssolser() {
		return obssolser;
	}

	/**
	 * @return the penoafi
	 */
	public String getPenoafi() {
		return penoafi;
	}

	/**
	 * @return the sueafisol
	 */
	public BigDecimal getSueafisol() {
		return sueafisol;
	}

	/**
	 * @param solicitudCreditoPK
	 *            the solicitudCreditoPK to set
	 */
	public void setSolicitudCreditoPK(SolicitudCreditoPK solicitudCreditoPK) {
		this.solicitudCreditoPK = solicitudCreditoPK;
	}

	/**
	 * @param codestsolser
	 *            the codestsolser to set
	 */
	public void setCodestsolser(String codestsolser) {
		this.codestsolser = codestsolser;
	}

	/**
	 * @param codfun
	 *            the codfun to set
	 */
	public void setCodfun(String codfun) {
		this.codfun = codfun;
	}

	/**
	 * @param codfunapr
	 *            the codfunapr to set
	 */
	public void setCodfunapr(String codfunapr) {
		this.codfunapr = codfunapr;
	}

	/**
	 * @param codofiies
	 *            the codofiies to set
	 */
	public void setCodofiies(String codofiies) {
		this.codofiies = codofiies;
	}

	/**
	 * @param fecsolser
	 *            the fecsolser to set
	 */
	public void setFecsolser(Date fecsolser) {
		this.fecsolser = fecsolser;
	}

	/**
	 * @param fectersolser
	 *            the fectersolser to set
	 */
	public void setFectersolser(Date fectersolser) {
		this.fectersolser = fectersolser;
	}

	/**
	 * @param numafi
	 *            the numafi to set
	 */
	public void setNumafi(String numafi) {
		this.numafi = numafi;
	}

	/**
	 * @param numsecinmhip
	 *            the numsecinmhip to set
	 */
	public void setNumsecinmhip(Long numsecinmhip) {
		this.numsecinmhip = numsecinmhip;
	}

	/**
	 * @param obssolser
	 *            the obssolser to set
	 */
	public void setObssolser(String obssolser) {
		this.obssolser = obssolser;
	}

	/**
	 * @param penoafi
	 *            the penoafi to set
	 */
	public void setPenoafi(String penoafi) {
		this.penoafi = penoafi;
	}

	/**
	 * @param sueafisol
	 *            the sueafisol to set
	 */
	public void setSueafisol(BigDecimal sueafisol) {
		this.sueafisol = sueafisol;
	}

	/**
	 * @return the detallesolicitudList
	 */
	public List<DetalleSolicitud> getDetallesolicitudList() {
		return detallesolicitudList;
	}

	/**
	 * @param detallesolicitudList
	 *            the detallesolicitudList to set
	 */
	public void setDetallesolicitudList(
			List<DetalleSolicitud> detallesolicitudList) {
		this.detallesolicitudList = detallesolicitudList;
	}

	/**
	 * @return the precondicionesList
	 */
	public List<PrecondicionesSolicitud> getPrecondicionesList() {
		return precondicionesList;
	}

	/**
	 * @param precondicionesList
	 *            the precondicionesList to set
	 */
	public void setPrecondicionesList(
			List<PrecondicionesSolicitud> precondicionesList) {
		this.precondicionesList = precondicionesList;
	}

	/**
	 * @return the solicitanteList
	 */
	public List<SolicitanteCredito> getSolicitanteList() {
		return solicitanteList;
	}

	/**
	 * @param solicitanteList
	 *            the solicitanteList to set
	 */
	public void setSolicitanteList(List<SolicitanteCredito> solicitanteList) {
		this.solicitanteList = solicitanteList;
	}

	/**
	 * Determina el proceso realizado al final
	 * 
	 * @return
	 */
	@Transient
	public ProcesoRealizado getUltimoEstado() {
		if (null == this.procesoRealizadoList
				|| this.procesoRealizadoList.size() == 0) {
			return null;
		}

		ProcesoRealizado procesoRealizado = null;

		for (ProcesoRealizado item : this.procesoRealizadoList) {
			if (procesoRealizado == null) {
				procesoRealizado = item;
				continue;
			}

			if (item.getId().compareTo(procesoRealizado.getId()) > 0) {
				procesoRealizado = item;
			}
		}

		return procesoRealizado;
	}

	/**
	 * @return the referenciaList
	 */
	public List<Referencia> getReferenciaList() {
		return referenciaList;
	}

	/**
	 * @param referenciaList
	 *            the referenciaList to set
	 */
	public void setReferenciaList(List<Referencia> referenciaList) {
		this.referenciaList = referenciaList;
	}

	/**
	 * @return the nut
	 */
	public Long getNut() {
		return nut;
	}

	/**
	 * @param nut
	 *            the nut to set
	 */
	public void setNut(Long nut) {
		this.nut = nut;
	}

	/**
	 * @return the nuttran
	 */
	public Long getNuttran() {
		return nuttran;
	}

	/**
	 * @param nuttran
	 *            the nuttran to set
	 */
	public void setNuttran(Long nuttran) {
		this.nuttran = nuttran;
	}

	/**
	 * @return the anulaciones
	 */
	public List<NovedadAnulacion> getAnulaciones() {
		return anulaciones;
	}

	/**
	 * @param anulaciones
	 *            the anulaciones to set
	 */
	public void setAnulaciones(List<NovedadAnulacion> anulaciones) {
		this.anulaciones = anulaciones;
	}

	public List<DepositoSolicitud> getDepositoSolicitudList() {
		return depositoSolicitudList;
	}

	public void setDepositoSolicitudList(
			List<DepositoSolicitud> depositoSolicitudList) {
		this.depositoSolicitudList = depositoSolicitudList;
	}

	/**
	 * @return the estRec
	 */
	public String getEstRec() {
		return estRec;
	}

	/**
	 * @param estRec
	 *            the estRec to set
	 */
	public void setEstRec(String estRec) {
		this.estRec = estRec;
	}

	/**
	 * @return the fecRec
	 */
	public Date getFecRec() {
		return fecRec;
	}

	/**
	 * @param fecRec
	 *            the fecRec to set
	 */
	public void setFecRec(Date fecRec) {
		this.fecRec = fecRec;
	}

	/**
	 * @return the datoProyectoConstructorList
	 */
	public List<DatoProyectoConstructor> getDatoProyectoConstructorList() {
		return datoProyectoConstructorList;
	}

	/**
	 * @param datoProyectoConstructorList
	 *            the datoProyectoConstructorList to set
	 */
	public void setDatoProyectoConstructorList(
			List<DatoProyectoConstructor> datoProyectoConstructorList) {
		this.datoProyectoConstructorList = datoProyectoConstructorList;
	}

	/**
	 * @return the nombreProyecto
	 */
	public String getNombreProyecto() {
		if (null != datoProyectoConstructorList) { // &&
			// !datoProyectoConstructorList.isEmpty()
			datoProyectoConstructorList.size();
			nombreProyecto = datoProyectoConstructorList.get(0)
					.getNombreProyecto();
			return nombreProyecto;
		}
		return null;
	}

	/**
	 * @param nombreProyecto
	 *            the nombreProyecto to set
	 */
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	/**
	 * @return the operacionesSustitucionHipoteca
	 */
	public List<OperacionSustitucionHipoteca> getOperacionesSustitucionHipoteca() {
		return operacionesSustitucionHipoteca;
	}

	/**
	 * @param operacionesSustitucionHipoteca
	 *            the operacionesSustitucionHipoteca to set
	 */
	public void setOperacionesSustitucionHipoteca(
			List<OperacionSustitucionHipoteca> operacionesSustitucionHipoteca) {
		this.operacionesSustitucionHipoteca = operacionesSustitucionHipoteca;
	}

	/**
	 * @return the institucionOrigen
	 */
	public String getInstitucionOrigen() {
		return institucionOrigen;
	}

	/**
	 * @param institucionOrigen
	 *            the institucionOrigen to set
	 */
	public void setInstitucionOrigen(String institucionOrigen) {
		this.institucionOrigen = institucionOrigen;
	}

	/**
	 * @return the detalleBienesAccesorios
	 */
	public List<DetalleBienesSolicitud> getDetalleBienesAccesorios() {
		return detalleBienesAccesorios;
	}

	/**
	 * @param detalleBienesAccesorios
	 *            the detalleBienesAccesorios to set
	 */
	public void setDetalleBienesAccesorios(
			List<DetalleBienesSolicitud> detalleBienesAccesorios) {
		this.detalleBienesAccesorios = detalleBienesAccesorios;
	}

	/**
	 * @return the liquidacionGastoList
	 */
	public List<LiquidacionGasto> getLiquidacionGastoList() {
		if (liquidacionGastoList == null) {
			liquidacionGastoList = new ArrayList<LiquidacionGasto>();
		}
		return liquidacionGastoList;
	}

	/**
	 * @param liquidacionGastoList
	 *            the liquidacionGastoList to set
	 */
	public void setLiquidacionGastoList(
			List<LiquidacionGasto> liquidacionGastoList) {
		this.liquidacionGastoList = liquidacionGastoList;
	}

	/**
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado
	 *            the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}