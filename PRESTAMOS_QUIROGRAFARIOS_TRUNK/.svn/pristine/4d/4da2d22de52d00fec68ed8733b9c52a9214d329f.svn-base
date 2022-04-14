package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.PeriodoComprobanteException;
import ec.gov.iess.creditos.modelo.persistencia.PeriodoComprobante;
import ec.gov.iess.dao.GenericDao;

/**
 * DAO para acceso a datos de la entidad PeriodoComprobante
 * 
 * @author hugo.mora
 * @date 2016/08/03
 *
 */
@Local
public interface PeriodoComprobanteDao extends GenericDao<PeriodoComprobante, Long> {

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
