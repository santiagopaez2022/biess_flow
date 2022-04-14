/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacionDetalle;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b> Interfaz con operaciones para manejo del detalle de la informacion de prestamos </b>
 * 
 */
@Local
public interface PrestamoInformacionDetalleDao extends GenericDao<PrestamoInformacionDetalle, Long> {

	/**
	 * Obtiene la lista de detalles dado el PK del credito
	 * 
	 * @param prestamoPk
	 * @return List<PrestamoInformacionDetalle>
	 */
	public List<PrestamoInformacionDetalle> obtenerInformacionDetallePorPK(PrestamoPk prestamoPk);
	
}
