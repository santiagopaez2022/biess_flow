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

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


import ec.fin.biess.creditos.pq.excepcion.BeneficiarioCreditoExcepcion;
import ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.BeneficiarioCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.modelo.persistencia.pk.BeneficiarioCreditoPK;

/**
 * Interfaz para persistencia de beneficiarios de creditos quirografarios.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Stateless
public class BeneficiarioCreditoServicioImpl implements
		BeneficiarioCreditoServicio {

	private LoggerBiess log = LoggerBiess
			.getLogger(BeneficiarioCreditoServicioImpl.class);

	@EJB
	private BeneficiarioCreditoDao beneficiarioCreditoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio#
	 * obtenerPorPeriodo(java.util.Date, java.util.Date)
	 */
	public List<BeneficiarioCredito> obtenerPorPeriodo(Date fecha_ant,
			Date fecha_post) throws BeneficiarioCreditoExcepcion {

		if (log.isDebugEnabled()) {
			log.debug(" Lista de parametros:");
			log.debug(" fecha_ant:" + fecha_ant);
			log.debug(" fecha_post:" + fecha_post);
		}

		return this.beneficiarioCreditoDao.obtenerPorPeriodo(fecha_ant,
				fecha_post);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio#
	 * crearBeneficiarioCredito
	 * (ec.gov.iess.creditos.modelo.persistencia.Prestamo,
	 * ec.gov.iess.creditos.modelo.persistencia.BeneficiarioCredito)
	 */
	public void crearBeneficiarioCredito(Prestamo prestamo,
			BeneficiarioCredito beneficiarioCredito)
			throws BeneficiarioCreditoExcepcion {
		log.debug(" crearBeneficiarioCredito");
		log.debug(" Datos::");
		log.debug(" beneficiarioCredito.getNumeroBeneficiario() : "
				+ beneficiarioCredito.getNumeroBeneficiario());
		log.debug(" beneficiarioCredito.getBeneficiario() : "
				+ beneficiarioCredito.getBeneficiario());
		log.debug(" beneficiarioCredito.getNombreMenor() : "
				+ beneficiarioCredito.getNombreMenor());
		log.debug(" beneficiarioCredito.getNumeroCausa() : "
				+ beneficiarioCredito.getNumeroCausa());
		log.debug(" beneficiarioCredito.getNumeroJuzgado() : "
				+ beneficiarioCredito.getNumeroJuzgado());
		log.debug(" beneficiarioCredito.getProvinciaJuicio() : "
				+ beneficiarioCredito.getProvinciaJuicio());
		log.debug(" beneficiarioCredito.getCiudadJuicio() : "
				+ beneficiarioCredito.getCiudadJuicio());
		log.debug(" beneficiarioCredito.getFechaRegistro() : "
				+ beneficiarioCredito.getFechaRegistro());

		try {
			// Creamos el PK
			BeneficiarioCreditoPK beneficiarioCreditoPK = new BeneficiarioCreditoPK(
					prestamo.getPrestamoPk().getCodprecla(), prestamo
							.getPrestamoPk().getCodpretip(), prestamo
							.getPrestamoPk().getNumpreafi(), prestamo
							.getPrestamoPk().getOrdpreafi());
			beneficiarioCredito.setBeneficiarioCreditoPK(beneficiarioCreditoPK);
			beneficiarioCredito.setFechaRegistro(new Date());

			log.debug(" inicia insert de beneficiario");
			beneficiarioCreditoDao.insert(beneficiarioCredito);
			log.debug(" finaliza insert de beneficiario");
		} catch (Exception e) {
			log.error(" Error al crear los dividendos ", e);
			throw new BeneficiarioCreditoExcepcion(" Error al crear los dividendos ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.BeneficiarioCreditoServicio#obtenerPorPK
	 * (java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public BeneficiarioCredito obtenerPorPK(Long codprecla, Long codpretip,
			Long numpreafi, Long ordpreafi) throws BeneficiarioCreditoExcepcion {
		return beneficiarioCreditoDao.obtenerPorPK(codprecla, codpretip,
				numpreafi, ordpreafi);
	}

}
