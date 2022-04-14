/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.auditoria;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import ec.gov.biess.util.log.LoggerBiess;

/**
 * Interceptor usado para auditar las actualizaciones de datos de afiliado.
 * 
 * @author Omar Villanueva
 * @version 1.0
 * 
 */
public class AuditorDatosAfiliado {

	private static LoggerBiess log = LoggerBiess.getLogger(AuditorDatosAfiliado.class);

	@Resource
	EJBContext ctx;

	/**
	 * Metodo que guarda un nuevo registro de auditoria.
	 * 
	 * @param invocation
	 * @return Object
	 * @throws Exception
	 */
	@AroundInvoke
	public Object profile(InvocationContext invocation) throws Exception {
		try {
			return invocation.proceed();
		} catch (Exception e) {
			log.error("Error en AuditorDatosAfiliado >>>", e);
			throw e;
		}
	}
	
}
