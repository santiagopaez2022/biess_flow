package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;

/**
 * Servicio para logica de negocio de periodos de comprobantes
 * 
 * @author hugo.mora
 * @date 2016/08/03
 *
 */
@Local
public interface PeriodoComprobanteServicio {
	
	/**
	 * Busca un PeriodoComprobante dado el anio del periodo, mes del periodo y tipo empleador
	 * 
	 * @param aniPer
	 * @param mesPer
	 * @param tipoEmpleador
	 *            Puede ser empresa publica (PUB), privada (PRI) o ministerio de relaciones exteriores (MIN)
	 * @return
	 * @throws PeriodoComprobanteException
	 */
	PeriodoComprobante buscarPorPeriodoYEmpleador(Integer aniPer, Integer mesPer, String tipoEmpleador) throws PeriodoComprobanteException;

}
