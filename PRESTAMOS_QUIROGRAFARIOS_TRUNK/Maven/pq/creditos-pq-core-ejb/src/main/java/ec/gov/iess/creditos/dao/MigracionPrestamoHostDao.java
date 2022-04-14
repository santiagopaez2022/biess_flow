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

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.MigracionPrestamoHost;
import ec.gov.iess.creditos.modelo.persistencia.pk.MigracionPrestamoHostPK;
import ec.gov.iess.dao.GenericDao;

/**
 * Interfaz Dao de la migracion de creditos del HOST
 * 
 * @version 1.0
 * @author cvillarreal 03/10/2011
 * 
 */
@Local
public interface MigracionPrestamoHostDao extends
		GenericDao<MigracionPrestamoHost, MigracionPrestamoHostPK> {

	/**
	 * Consulta una lista de préstamos vigentes en el Host
	 * 
	 * @param cedula
	 *            identificaciond e la persona
	 * 
	 * @return una lista de {@link MigracionPrestamoHost} caso contrario una
	 *         lista vacia
	 * @author cvillarreal
	 */
	public List<MigracionPrestamoHost> listaPrestamoVigentesHost(String cedula,List<String> estadoMigrado);

	/**
	 * Consulta si tiene creditos en mora del Host.
	 * 
	 * @param cedula
	 *            identificacin de la persona
	 * @return true en caso de que tenga creditos pendientes y falso en caso de
	 *         que no se encuentre la cedula en la tabla de migracion
	 * @author cvillarreal
	 */
	public boolean tienePrestamoVigentesHost(String cedula,List<String> estadoMigrado);

}
