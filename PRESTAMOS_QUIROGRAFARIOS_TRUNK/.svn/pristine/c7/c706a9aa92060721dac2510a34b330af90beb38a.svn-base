/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.SubProcesoTipoSolicitudDao;
import ec.gov.iess.creditos.modelo.persistencia.SubprocesoTipoSolicitud;
import ec.gov.iess.dao.ejb.GenericEjbDao;

@Stateless
public class SubProcesoTipoSolicitudDaoImpl extends
		GenericEjbDao<SubprocesoTipoSolicitud, Long> implements
		SubProcesoTipoSolicitudDao {
	SubProcesoTipoSolicitudDaoImpl() {
		super(SubprocesoTipoSolicitud.class);
	}

	public SubprocesoTipoSolicitud findByTipoSolicitudSubproceso(
			Long tipoSolicitud, Long codigoSubProceso) {
		Query query = em.createNamedQuery("SubprocesoTipoSolicitud.findByTipoSolicitudSubproceso");
		query.setParameter("idTipoSolicitud", tipoSolicitud);
		query.setParameter("subprocesoSolicitud", codigoSubProceso);
		return (SubprocesoTipoSolicitud)query.getSingleResult();
	}
}
