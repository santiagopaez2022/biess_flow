/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.SaldoFondosDetalleDao;
import ec.gov.iess.creditos.modelo.persistencia.SaldoFondosDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoFondosDetallePK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Objeto Dao para la clase SaldoFondosDetalle </b>
 * 
 * @author geguiguren
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2010/12/20 16:55:55 $]
 *          </p>
 */
@Stateless
public class SaldoFondosDetalleDaoImpl extends
		GenericEjbDao<SaldoFondosDetalle, SaldoFondosDetallePK> implements SaldoFondosDetalleDao {

	private static final LoggerBiess log = LoggerBiess
			.getLogger(SaldoFondosDetalleDaoImpl.class);

	/**
	 * @param type
	 */
	public SaldoFondosDetalleDaoImpl() {
		super(SaldoFondosDetalle.class);
	}

	
	/**
	 * <b> Obtiene el listado del ultimo detalle del saldo para una fecha determinada. </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 29/11/2010]
	 * </p>
	 * 
	 * @param fecha
	 *            de consulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SaldoFondosDetalle> consultarDetallePorFecha(Date fecha) {
		
		List<SaldoFondosDetalle> lista = new ArrayList<SaldoFondosDetalle>();
		String fechaTexto = null;

		try {
			log.info(fecha.toString());
			SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
			fechaTexto = dateF.format(fecha);
			log.info(fechaTexto);
			
			Query q = em.createNamedQuery("SaldoFondosDetalle.consultarFondosPorFecha");
			q.setParameter("fecha", fechaTexto);
			lista = q.getResultList();
		
		} catch (Exception e) {
			log.error(e);
		}
		return lista;
	}

	
	/**
	 * <b> Obtiene la informacion SPI del detalle del saldo para una fecha determinada. </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 29/11/2010]
	 * </p>
	 * 
	 * @param fecha
	 *            de consulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SaldoFondosDetalle> consultarDetalleSPI(Date fecha, Long codigoCuenta) {
		List<SaldoFondosDetalle> lista = new ArrayList<SaldoFondosDetalle>();

		Query q = em.createNamedQuery("SaldoFondosDetalle.detalleSPI");
		q.setParameter("fecha", fecha);
		q.setParameter("codigo", codigoCuenta);
		lista = q.getResultList();

		return lista;
	}
}
