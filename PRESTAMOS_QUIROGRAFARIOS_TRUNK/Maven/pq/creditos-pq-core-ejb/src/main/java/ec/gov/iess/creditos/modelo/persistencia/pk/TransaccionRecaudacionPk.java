/**
 * 
 */
package ec.gov.iess.creditos.modelo.persistencia.pk;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author roberto.guizado
 *
 */
public class TransaccionRecaudacionPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 862096588958050952L;

	@Column(name = "TR_IDTRANSACCION")
	private long idTransaccion;

	@Column(name = "TR_IDTIPOTRANSACCION", nullable = false, insertable = false, updatable = false)
	private long trIdtipotransaccion;

	public TransaccionRecaudacionPk() {
		super();
	}

	public long getIdTransaccion() {
		return this.idTransaccion;
	}

	public void setIdTransaccion(long trIdtransaccion) {
		this.idTransaccion = trIdtransaccion;
	}

	public long getTrIdtipotransaccion() {
		return this.trIdtipotransaccion;
	}

	public void setTrIdtipotransaccion(long trIdtipotransaccion) {
		this.trIdtipotransaccion = trIdtipotransaccion;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof TransaccionRecaudacionPk)) {
			return false;
		}
		TransaccionRecaudacionPk other = (TransaccionRecaudacionPk) o;
		return this.idTransaccion == other.idTransaccion && this.trIdtipotransaccion == other.trIdtipotransaccion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idTransaccion ^ (this.idTransaccion >>> 32)));
		hash = hash * prime + ((int) (this.trIdtipotransaccion ^ (this.trIdtipotransaccion >>> 32)));
		return hash;
	}
}
