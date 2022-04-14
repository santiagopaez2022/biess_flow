/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.dao;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * DAO para consultar residuos de cesantias de tipo RESIVM y RENIVM
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Local
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public interface ResiduosCesantiaDao {

	/**
	 * Método que consulta los residuos de cesantias de tipo RESIVM y RENIVM dado un numero de cédula
	 * 
	 * @param cedula
	 * @return
	 */
	public BigDecimal consultarResiduos(String cedula);
}
