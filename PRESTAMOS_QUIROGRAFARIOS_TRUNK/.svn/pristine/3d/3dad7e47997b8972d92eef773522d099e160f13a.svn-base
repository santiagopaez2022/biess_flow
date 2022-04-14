/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.servicio.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.fin.biess.creditos.pq.dao.ParametroCreditoDao;
import ec.fin.biess.creditos.pq.excepcion.ParametroCreditoException;
import ec.fin.biess.creditos.pq.modelo.persistencia.ParametroCredito;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * Clase que implementa los métodos para consultar parametros de la tabla KSCRETCREPOL
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 *
 */
@Stateless
public class ParametroCreditoServicioImpl implements ParametroCreditoServicio {

	private LoggerBiess log = LoggerBiess.getLogger(ParametroCreditoServicioImpl.class);
	
	@EJB
	private transient ParametroCreditoDao parametroDao;
	
	/* (non-Javadoc)
	 * @see ec.gob.biess.creditos.pq.servicio.ParametroCreditoServicio#obtenerValorSBU()
	 */
	public BigDecimal obtenerValorSBU() throws ParametroCreditoException {
		ParametroCredito param = parametroDao.consultarPorCodigo("SBU");
		if (null == param) {
			log.error("Parametro SBU no existe en la tabla KSCRETCREPOL.");
			throw new ParametroCreditoException("No existe parametro SBU.");
		}
		if (null == param.getValNumPol()) {
			log.error("Valor SBU no fijado en la tabla KSCRETCREPOL.");
			throw new ParametroCreditoException("Valor SBU incorrecto.");
		}
		log.debug("Valor SBU: " + param.getValNumPol().toString());
		
		return param.getValNumPol();
	}

	/* (non-Javadoc)
	 * @see ec.gob.biess.creditos.pq.servicio.ParametroCreditoServicio#obtenerValorNUMSBU()
	 */
	public BigDecimal obtenerValorNUMSBU() throws ParametroCreditoException {
		ParametroCredito param = parametroDao.consultarPorCodigo("NUMSBU");
		if (null == param) {
			log.error("Parametro NUMSBU no existe en la tabla KSCRETCREPOL.");
			throw new ParametroCreditoException("No existe parametro NUMSBU.");
		}
		if (null == param.getValNumPol()) {
			log.error("Valor NUMSBU no fijado en la tabla KSCRETCREPOL.");
			throw new ParametroCreditoException("Valor NUMSBU incorrecto.");
		}
		log.debug("Valor NUMSBU: " + param.getValNumPol().toString());
		
		return param.getValNumPol();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio#obtenerParametroPQMontosMaximos(java.lang.String)
	 */
	public BigDecimal obtenerParametroPQMontosMaximos(String parametro) throws ParametroCreditoException {
		ParametroCredito param = parametroDao.consultarPorCodigo(parametro);
		if (null == param) {
			log.error("Parametro " + parametro + " no existe en la tabla KSCRETCREPOL");
			throw new ParametroCreditoException("No existe parametro " + parametro + ".");
		}
		if (null == param.getValNumPol()) {
			log.error("Valor " + parametro + " no fijado en la tabla KSCRETCREPOL");
			throw new ParametroCreditoException("Valor " + parametro + " incorrecto.");
		}
		return param.getValNumPol();
	}

}
