package ec.fin.biess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.TipoCuentaBiess;

@Local
public interface TipoCuentaBiessServicio {

	/**
	 * Obtiene todos los tipos de cuentas.
	 * 
	 * @return List<TipoCuentaBiess>.
	 */
	public List<TipoCuentaBiess> obtenerTodos();

	/**
	 * Obtienen un tipo por codigo.
	 * 
	 * @param codigo
	 *            - codigo tipo cuenta.
	 * 
	 * @return TipoCuentaBiess.
	 */
	public TipoCuentaBiess obtenerPorCodigo(String codigo);
}
