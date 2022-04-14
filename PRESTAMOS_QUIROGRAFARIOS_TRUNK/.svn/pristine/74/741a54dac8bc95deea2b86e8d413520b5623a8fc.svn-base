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
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.MigracionPrestamoHostDao;
import ec.gov.iess.creditos.modelo.persistencia.MigracionPrestamoHost;
import ec.gov.iess.creditos.modelo.persistencia.pk.MigracionPrestamoHostPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * 
 * Implementacion Dao Ejb para los datos de migracion de cerditos Host
 * 
 * @version 1.0
 * @author cvillarreal
 * 
 */
@Stateless
public class MigracionPrestamoHostDaoImpl extends
		GenericEjbDao<MigracionPrestamoHost, MigracionPrestamoHostPK> implements
		MigracionPrestamoHostDao {

	LoggerBiess log = LoggerBiess.getLogger(MigracionPrestamoHostDaoImpl.class);

	/**
	 * @param type
	 */
	public MigracionPrestamoHostDaoImpl() {
		super(MigracionPrestamoHost.class);
	}

	@SuppressWarnings("unchecked")
	public List<MigracionPrestamoHost> listaPrestamoVigentesHost(String cedula,List<String> estadoMigrado) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" cedula:" + cedula);
			log.debug(" estadoMigrado:" + estadoMigrado);
		}

		Query query = em
				.createNamedQuery("MigracionPrestamoHost.consultarEnMora");
		query.setParameter("cedula", cedula);
		query.setParameter("estadoMigrado", estadoMigrado);

		return query.getResultList();
	}

	public boolean tienePrestamoVigentesHost(String cedula,List<String> estadoMigrado) {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" cedula:" + cedula);
			log.debug(" estadoMigrado:" + estadoMigrado);
		}

		List<MigracionPrestamoHost> resultado = this
				.listaPrestamoVigentesHost(cedula,estadoMigrado);

		return !resultado.isEmpty();
	}
}
