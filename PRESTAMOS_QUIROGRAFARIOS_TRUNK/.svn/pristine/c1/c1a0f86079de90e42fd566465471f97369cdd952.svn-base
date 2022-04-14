package ec.gov.iess.creditos.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.ListaVaciaException;
import ec.gov.iess.creditos.modelo.persistencia.CreditoQuirografarioHost;
import ec.gov.iess.dao.GenericDao;

/**
 * Dao para los creditos quirografarios migrados del HOST
 * 
 * @author cvillarreal
 * @version 1.0
 */
@Local
public interface CreditoQuirografarioHostDao extends
		GenericDao<CreditoQuirografarioHost, Serializable> {

	/**
	 * Consulta los creditos quirografarios migrados del host en base al codpre
	 * que son los codigos de tipos de quirografarios del HOST
	 * 
	 * @param cedula
	 * @param listaCreditos
	 * @return
	 */
	public List<CreditoQuirografarioHost> getCreditosQuirografariosHost(
			String cedula, List<String> listaCreditos)throws ListaVaciaException;

}
