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

import ec.gov.iess.creditos.dao.AportesPfr2DaoLocal;
import ec.gov.iess.creditos.modelo.persistencia.AportesPfr2;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * @author wvalencia
 * 
 */
@Stateless
public class AportesPfr2DaoEjb extends
		GenericEjbDao<AportesPfr2, BigInteger> implements AportesPfr2DaoLocal {

	public AportesPfr2DaoEjb() {
		super(AportesPfr2.class);
	}

	/* (non-Javadoc)
	 * @see ec.gov.iess.servicio.fondoreserva.dao.impl.AportePortafolio2DaoLocal#findByCedulaPatronal(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<AportesPfr2> findByCedulaAportesComprometidosPq(String cedula) {
		Query consulta = em
				.createNamedQuery("AportesPfr2.obtenerAportesComprometidosPq");
		consulta.setParameter("cedula", cedula);
		return consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)	
	public List<AportesPfr2> findByCedulaAndPrestamoAportesComprometidosPq(String cedula,
			Long numpreafi,
			Long codpretip,
			Long ordpreafi,
			Long codprecla) {
		Query consulta = em
				.createNamedQuery("AportesPfr2.obtenerAportesComprometidosPqN");
		consulta.setParameter("CEDULA", cedula);
		consulta.setParameter("NUMPREAFI", numpreafi);
		consulta.setParameter("CODPRETIP", codpretip);
		consulta.setParameter("ORDPREAFI", ordpreafi);
		consulta.setParameter("CODPRECLA", codprecla);		
		return consulta.getResultList();
	}

	@Override
	public BigDecimal obtenerTotalFr(String cedula) {
		Query consulta = em.createNamedQuery("AportesPfr2.obtenerTotalFondosReserva");
		consulta.setParameter("cedula", cedula);
		return (BigDecimal) consulta.getSingleResult();

	}

}
