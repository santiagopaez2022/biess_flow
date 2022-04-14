package ec.gov.iess.creditos.modelo.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REC_TIPO_TRANSACCION_TBL")
public class TipoTransaccionRecaudacion implements Serializable {

	private static final long serialVersionUID = 4721775548357201082L;
	@Column(name = "TT_CONCEPTONOTACREDITO")
	private String ttConceptonotacredito;
	@Id
	@Column(name = "TT_IDTIPOTRANSACCION", nullable = false)
	private Long ttIdtipotransaccion;
	@Column(name = "TT_TIPOCOMPROBANTE")
	private String ttTipocomprobante;
	@Column(name = "TT_TIPOPLANILLA")
	private String ttTipoplanilla;
	@Column(name = "TT_TIPOTRANSACCION", nullable = false)
	private String ttTipotransaccion;
	@OneToMany(mappedBy = "tipoTransaccionRecaudacion")
	private List<TransaccionRecaudacion> transaccionRecaudacionList;

	public TipoTransaccionRecaudacion() {
	}

	public TipoTransaccionRecaudacion(String ttConceptonotacredito,
			Long ttIdtipotransaccion, String ttTipocomprobante,
			String ttTipoplanilla, String ttTipotransaccion) {
		this.ttConceptonotacredito = ttConceptonotacredito;
		this.ttIdtipotransaccion = ttIdtipotransaccion;
		this.ttTipocomprobante = ttTipocomprobante;
		this.ttTipoplanilla = ttTipoplanilla;
		this.ttTipotransaccion = ttTipotransaccion;
	}

	public String getTtConceptonotacredito() {
		return ttConceptonotacredito;
	}

	public void setTtConceptonotacredito(String ttConceptonotacredito) {
		this.ttConceptonotacredito = ttConceptonotacredito;
	}

	public Long getTtIdtipotransaccion() {
		return ttIdtipotransaccion;
	}

	public void setTtIdtipotransaccion(Long ttIdtipotransaccion) {
		this.ttIdtipotransaccion = ttIdtipotransaccion;
	}

	public String getTtTipocomprobante() {
		return ttTipocomprobante;
	}

	public void setTtTipocomprobante(String ttTipocomprobante) {
		this.ttTipocomprobante = ttTipocomprobante;
	}

	public String getTtTipoplanilla() {
		return ttTipoplanilla;
	}

	public void setTtTipoplanilla(String ttTipoplanilla) {
		this.ttTipoplanilla = ttTipoplanilla;
	}

	public String getTtTipotransaccion() {
		return ttTipotransaccion;
	}

	public void setTtTipotransaccion(String ttTipotransaccion) {
		this.ttTipotransaccion = ttTipotransaccion;
	}

	public List<TransaccionRecaudacion> getTransaccionRecaudacionList() {
		return transaccionRecaudacionList;
	}

	public void setTransaccionRecaudacionList(
			List<TransaccionRecaudacion> transaccionRecaudacionList) {
		this.transaccionRecaudacionList = transaccionRecaudacionList;
	}

	public TransaccionRecaudacion addTransaccionRecaudacion(
			TransaccionRecaudacion transaccionRecaudacion) {
		getTransaccionRecaudacionList().add(transaccionRecaudacion);
		transaccionRecaudacion.setTipoTransaccionRecaudacion(this);
		return transaccionRecaudacion;
	}

	public TransaccionRecaudacion removeTransaccionRecaudacion(
			TransaccionRecaudacion transaccionRecaudacion) {
		getTransaccionRecaudacionList().remove(transaccionRecaudacion);
		transaccionRecaudacion.setTipoTransaccionRecaudacion(null);
		return transaccionRecaudacion;
	}
}
