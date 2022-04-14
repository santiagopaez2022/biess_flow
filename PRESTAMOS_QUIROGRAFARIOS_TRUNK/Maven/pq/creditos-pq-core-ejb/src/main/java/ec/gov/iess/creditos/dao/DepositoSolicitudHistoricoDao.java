/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */

package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DepositoSolicitudHistorico;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author Jenny Sanchez
 * @version $Revision: 1.5 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 15:43:59 $]
 *          </p>
 */
@Local
public interface DepositoSolicitudHistoricoDao extends
		GenericDao<DepositoSolicitudHistorico, Long> {

	/**
	 * 
	 * <b> Método para obtener el historico del detalle de la solicitud por
	 * código de detalle y archivo SPI de desembolso. </b>
	 * <p>
	 * [Author: Jenny Sanchez, Date: 17/02/2011]
	 * </p>
	 * 
	 * @param codDetalleSol
	 *            Código de Detalle de la solicitud
	 * @param spi
	 *            Código del archivo SPI de desembolso.
	 * @return Histórico del detalle de la solicitud.
	 */
	public DepositoSolicitudHistorico obtenerPorSPI(Long codDetalleSol,
			String spi);

	/**
	 * 
	 * @param codDetalleSol
	 * @param spi
	 * @return
	 */
	public DepositoSolicitudHistorico obtenerSPIPorCodDetalleSol(
			Long codDetalleSol);
	
	/**
	 * 
	 * @param codDetalleSol
	 * @return
	 */
	public DepositoSolicitudHistorico obtenerHistPorCodDetalleSol(
			Long codDetalleSol);
}
