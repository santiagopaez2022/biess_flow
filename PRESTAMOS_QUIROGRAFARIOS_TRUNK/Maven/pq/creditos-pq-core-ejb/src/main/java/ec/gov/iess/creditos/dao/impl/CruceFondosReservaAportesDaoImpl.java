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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.CruceFondosReservaAportesDao;
import ec.gov.iess.creditos.modelo.persistencia.CruceFondosReservaAportes;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author palvarez
 * 
 */
@Stateless
public class CruceFondosReservaAportesDaoImpl extends
		GenericEjbDao<CruceFondosReservaAportes, Long> implements CruceFondosReservaAportesDao {

	public CruceFondosReservaAportesDaoImpl() {
		super(CruceFondosReservaAportes.class);
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.servicio.fondoreserva.dao.impl.AportePortafolio2DaoLocal#findByCedulaPatronal(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CruceFondosReservaAportes> findByNumpreafiYCodigoAporte(String cedula,Long numpreafi,Long codprecla,Long codpretip,Long ordpreafi,
			BigInteger codigoaporte) {
		Query consulta = em
				.createNamedQuery("CruceReservaAportes.obtenerAportesComprometidosPq");
		consulta.setParameter("cedula", cedula);
		consulta.setParameter("numpreafi", numpreafi);
		consulta.setParameter("codprecla", codprecla);
		consulta.setParameter("codpretip", codpretip);
		consulta.setParameter("ordpreafi", ordpreafi);
		consulta.setParameter("codigoaporte", codigoaporte);
		return consulta.getResultList();
	}
	
	
	public BigDecimal  ObtenerValoresComprometidosFondosCruces(String cedula,Long numpreafi,Long codprecla,Long codpretip,Long ordpreafi )
	{		
		Query query = em
		.createNativeQuery(" SELECT NVL(SUM(NVL(CAPITALCOMPROMETIDO,0)),0) "+
		                   " FROM APORTES_PFR2 A,  FRSAFITCRURESCTAIND CI "+
		                   " WHERE A.CEDULA = CI.NUMAFI "+
		                   " AND A.CODIGOAPORTE = CI.CODSEC "+
		                   " AND A.CEDULA = :cedula "+
		                   " AND CI.NUMPREAFI = :numpreafi "+
		                   " AND CI.CODPRETIP = :codpretip "+
		                   " AND CI.ORDPREAFI = :ordpreafi "+
		                   " AND CI.CODPRECLA = :codprecla ");
		query.setParameter("cedula", cedula);
		query.setParameter("numpreafi", numpreafi);
		query.setParameter("codpretip", codpretip);
		query.setParameter("ordpreafi", ordpreafi);
		query.setParameter("codprecla", codprecla);
		return (BigDecimal) query.getSingleResult();
	}
	

}
