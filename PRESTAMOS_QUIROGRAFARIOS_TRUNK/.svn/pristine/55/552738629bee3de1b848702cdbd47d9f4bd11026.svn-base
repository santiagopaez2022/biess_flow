/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.SaldoFondosDao;
import ec.gov.iess.creditos.modelo.persistencia.CuentaFondos;
import ec.gov.iess.creditos.modelo.persistencia.SaldoFondos;
import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoFondosPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Objeto Dao para la clase SaldoFondos </b>
 * 
 * @author geguiguren
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2010/12/20 19:40:12 $]
 *          </p>
 */
@Stateless
public class SaldoFondosDaoImpl extends GenericEjbDao<SaldoFondos, SaldoFondosPK> implements SaldoFondosDao {

	private static final LoggerBiess log = LoggerBiess.getLogger(SaldoFondosDaoImpl.class);

	/**
	 * @param type
	 */
	public SaldoFondosDaoImpl() {
		super(SaldoFondos.class);
	}

	/**
	 * <b> Almacena un nuevo Saldo y su Detalle basado en la cuenta
	 * proporcionada </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 24/11/2010]
	 * </p>
	 * 
	 * @param cuenta
	 *            con la que se relaciona el nuevo saldo
	 * @param ipUsuario
	 *            direccion ip del usuario
	 * @param cedula
	 *            del usuario
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean crearNuevoSaldo(CuentaFondos cuenta, String ipUsuario, String cedula) {

		// nuevo Saldo
		SaldoFondos nuevo = new SaldoFondos();
		SaldoFondosPK pk = new SaldoFondosPK(cuenta.getCodigoCuentaFondo(), cuenta.getFechaProceso());

		nuevo.setClave(pk);
		nuevo.setSaldoInicial(cuenta.getSaldoInicial());
		nuevo.setEstado("REG");
		try {
			log.info("antes nuevo Saldo");
			insert(nuevo);
			log.info("despues nuevo saldo");
		} catch (Exception e) {
			log.error("Error al almacenar nuevo saldo");
			return false;
		}
		return true;
	}

	/**
	 * <b> Obtiene el listado de saldo para una fecha determinada. </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 24/11/2010]
	 * </p>
	 * 
	 * @param fecha
	 *            de consulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SaldoFondos> consultarPorFecha(Date fecha) {
		List<SaldoFondos> lista = new ArrayList<SaldoFondos>();

		Query q = em.createNamedQuery("SaldoFondos.consultarFondosPorFecha");
		q.setParameter("fecha", fecha);
		lista = q.getResultList();

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.dao.SaldoFondosDao#obtenerFondosPH(java.util.List,
	 * java.lang.String, java.util.List, java.math.BigDecimal)
	 */
	@SuppressWarnings("unchecked")
	public List<SaldoFondos> obtenerFondosPH(List<Long> codFondo, String fecha, List<String> estados,
			BigDecimal valorMinimo) {
		Query q = em.createNamedQuery("SaldoFondos.obtenerFondoPH");
		q.setParameter("codFondo", codFondo);
		q.setParameter("fechaAct", fecha);
		q.setParameter("estados", estados);
		q.setParameter("valor", valorMinimo);
		List<SaldoFondos> lista = q.getResultList();
		if (lista == null || lista.isEmpty()) {
			return null;
		}
		return lista;
	}
}
