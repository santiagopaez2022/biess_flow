/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Local;
import ec.gov.iess.creditos.modelo.persistencia.GarantiaCesantia;
import ec.gov.iess.dao.GenericDao;



/**
 * Interface DAO usada para administrar la entidad Cesantia 
 * @author pmlopez
 * @version $Revision: 1.2 $
 * 
 */
@Local
public interface GarantiaCesantiaDao extends GenericDao<GarantiaCesantia, String> {
	
	public void update(GarantiaCesantia o);
	/**
	 * @author acantos
	 * consulta el valor disponible considerando aportes extemporaneos
	 * @param cedula
	 * @return
	 */
	public BigDecimal consultarValordisponibleconExtemporaneos(String cedula);
	
	/**
	 * @author acantos
	 * consulta la fecha de la cuentaindividual
	 * @param cedula
	 * @return 
	 */
	public Date consultarFechadecuentaindividual(String cedula);
}