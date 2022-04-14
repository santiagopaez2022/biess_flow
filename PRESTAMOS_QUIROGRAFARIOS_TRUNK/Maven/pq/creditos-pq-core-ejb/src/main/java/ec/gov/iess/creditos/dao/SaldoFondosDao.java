/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.CuentaFondos;
import ec.gov.iess.creditos.modelo.persistencia.SaldoFondos;
import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoFondosPK;
import ec.gov.iess.dao.GenericDao;

/**
 * <b> Objeto Dao para acceder a la capa de persistencia SaldoFondos </b>
 * 
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $
 *          <p>
 *          [$Author: etarambis $, $Date: 2010/12/20 16:55:41 $]
 *          </p>
 */
@Local
public interface SaldoFondosDao extends GenericDao<SaldoFondos, SaldoFondosPK> {

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
	public List<SaldoFondos> consultarPorFecha(Date fecha);

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
	public boolean crearNuevoSaldo(CuentaFondos cuenta, String ipUsuario, String cedula);
	
	/**
	 * <b>
	 * Método para obtener los fondos disponibles de PH
	 * </b>
	 * <p>[Author: Jenny Sanchez, Date: 20/12/2010]</p>
	 *
	 * @param codFondo
	 * @param fecha
	 * @param estados
	 * @param valorMinimo
	 * @return
	 */ 
	public List<SaldoFondos> obtenerFondosPH(List<Long> codFondo, String fecha, List<String> estados,
			BigDecimal valorMinimo);
}