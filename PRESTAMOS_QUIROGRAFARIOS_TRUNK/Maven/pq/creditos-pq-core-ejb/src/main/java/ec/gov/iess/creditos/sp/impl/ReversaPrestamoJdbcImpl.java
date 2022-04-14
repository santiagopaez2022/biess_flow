/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */

package ec.gov.iess.creditos.sp.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;




import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.excepcion.ReversaPrestamoExcepcion;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.sp.ReversaPrestamo;
import ec.gov.iess.creditos.sp.ReversaValoresComprometidos;
import ec.gov.iess.creditos.sp.ReversarPrestamoJdbc;

/**
 * Implementacion, metodos para reversar prestamo novado
 *  
 * @version $Revision: 1.3 
 * @author andres cantos
 */
@Stateless
public class ReversaPrestamoJdbcImpl implements ReversarPrestamoJdbc {
	
	LoggerBiess log = LoggerBiess.getLogger(ReversaPrestamoJdbcImpl.class);
	
	@Resource(mappedName = "java:credito-pq-DS")
	DataSource dataSource;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void reversaValorescomprometidos(PrestamoPk prestamoPk)throws ReversaPrestamoExcepcion {
		ReversaValoresComprometidos reversa = new ReversaValoresComprometidos(dataSource);
		Map results = reversa.execute(prestamoPk);
		
		String codigoError = (String) results.get("AOCCODERR");
		if (codigoError == null) codigoError = "";
		String mensajeError = (String) results.get("AOCDESERR");
		log.info("Resultados del procedimiento KSCREKPROCESOS_CR.PROLIBGARPQCTAIND  coderror = "+ codigoError );
		log.info("Resultados del procedimiento KSCREKPROCESOS_CR.PROLIBGARPQCTAIND  msgerror = "+ mensajeError );
		
		if (!"1".equals(codigoError.trim())) {
			throw new ReversaPrestamoExcepcion(mensajeError);
		}
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void ejecutarreversa(PrestamoPk prestamoPk)throws ReversaPrestamoExcepcion {
		ReversaPrestamo reversa = new ReversaPrestamo(dataSource);
		Map results = reversa.execute(prestamoPk);
		
		String codigoError = (String) results.get("AOCRESPRO");
		if (codigoError == null) codigoError = "";
		String mensajeError = (String) results.get("AOCMENERR");
		log.info("Resultados del procedimiento CRE_REVERSANOVACIONPQ_PKG.CRE_EJECUTAREVERSA_PRC  coderror = "+ codigoError );
		log.info("Resultados del procedimiento CRE_REVERSANOVACIONPQ_PKG.CRE_EJECUTAREVERSA_PRC  msgerror = "+ mensajeError );
		if (!"1".equals(codigoError.trim())) {
			throw new ReversaPrestamoExcepcion(mensajeError);
		}
	}





}