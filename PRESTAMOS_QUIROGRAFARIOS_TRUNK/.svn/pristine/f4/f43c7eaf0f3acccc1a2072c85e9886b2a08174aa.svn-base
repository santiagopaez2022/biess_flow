/*
 * Copyright 2010 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.SaldoFondosDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.SaldoFondosDetallePK;
import ec.gov.iess.dao.GenericDao;


/** 
 * <b>
 * Objeto Dao para acceder a la capa de persistencia SaldoFondosDetalle
 * </b>
 *  
 * @author Gabriel Eguiguren
 * @version $Revision: 1.3 $ <p>[$Author: etarambis $, $Date: 2010/12/20 16:55:41 $]</p>
*/ 
@Local
public interface SaldoFondosDetalleDao extends GenericDao<SaldoFondosDetalle, SaldoFondosDetallePK> {

	/**
	 * <b> Obtiene el listado del ultimo detalle del saldo para una fecha determinada. </b>
	 * <p>
	 * [Author: Gabriel Eguiguren, Date: 24/11/2010]
	 * </p>
	 * 
	 * @param fecha de consulta
	 * @return
	 */
	public List<SaldoFondosDetalle> consultarDetallePorFecha(Date fecha);
	
	
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
	public List<SaldoFondosDetalle> consultarDetalleSPI(Date fecha, Long codigoCuenta);
	

}