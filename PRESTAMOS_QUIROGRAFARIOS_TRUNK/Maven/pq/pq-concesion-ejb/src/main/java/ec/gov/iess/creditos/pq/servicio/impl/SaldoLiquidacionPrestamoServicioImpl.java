/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Licensed under the IESS License, Version 1.0 (the "License"); you may not use this 
 * file. You may obtain a copy of the License at http://www.iess.gov.ec Unless required 
 * by applicable law or agreed to in writing, software distributed under the License is 
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.SaldoLiquidacionPrestamoDao;
import ec.gov.iess.creditos.modelo.persistencia.SaldoLiquidacionPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.creditos.pq.servicio.SaldoLiquidacionPrestamoServicio;

/**
 * Incluir aquí la descripción de la clase.
 * 
 * @version $Revision: 1.2 $ [Sep 24, 2007]
 * @author pablo
 */
@Stateless
public class SaldoLiquidacionPrestamoServicioImpl implements
		SaldoLiquidacionPrestamoServicio {

	@EJB
	private SaldoLiquidacionPrestamoDao saldoDao;

	public List<SaldoLiquidacionPrestamo> obtenerSaldosPorLiquidacion(PrestamoPk prestamoPk,List<String> estados) {
		return saldoDao.obtenerPorPrestamoYEstados(prestamoPk, estados);
	}

}
