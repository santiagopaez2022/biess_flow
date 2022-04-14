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
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.excepcion.TasaInteresDaoException;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.TasaInteresDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.TasaInteresDetallePK;
import ec.gov.iess.dao.GenericDao;

/**
 * @author cvillarreal
 * 
 */
@Local
public interface TasaInteresDetalleDao extends
		GenericDao<TasaInteresDetalle, TasaInteresDetallePK> {

	/**
	 * Consulta el detalle de tasas de interes en un rango de fechas de inicio
	 * de registro
	 * 
	 * @param idtasaInteres
	 *            identificador de la tasa de interes
	 * @param fechaDesde
	 *            fecha de inicio del rango
	 * @param fechaHasta
	 *            fecha final del rango
	 * @return una lista con las tasas de interes
	 * @author cvillarreal
	 */
	public BigDecimal consultaRangoFechasInicialTipoTasaInteres(
			String idtasaInteres, Date fechaDesde, Date fechaHasta)throws TasaInteresDaoException;

	/**
	 * Consulta el promedio de tasa de interes por rango de fecha e ntre la fecha
	 * de registro y fin de registro
	 * 
	 * @param idtasaInteres
	 *            identificador de la tasa de interes
	 * @param fecha
	 *            fecha de consulta
	 * @return el promedio de la tasa de interez
	 * @author cvillarreal
	 */
	public List<TasaInteresDetalle> consultaRangoFechasTipoTasaInteres(
			String idtasaInteres, Date fecha)throws TasaInteresExcepcion;
	
	public BigDecimal consultaPromedioRangoFechasInicialTipoTasaInteres(
			String idtasaInteres, Date fechaDesde, Date fechaHasta)throws TasaInteresExcepcion;
	/**
	 * Consulta la tasa de interés de mora para la generación de la liquidación anticipada 
	 * 
	 * @param idtasaInteres
	 *            identificador de la tasa de interes
	 * @param fecha
	 *            fecha de consulta
	 */
	public BigDecimal consultaInteresMora(String idtasaInteres, Date fecha)
			throws TasaInteresExcepcion;

}
