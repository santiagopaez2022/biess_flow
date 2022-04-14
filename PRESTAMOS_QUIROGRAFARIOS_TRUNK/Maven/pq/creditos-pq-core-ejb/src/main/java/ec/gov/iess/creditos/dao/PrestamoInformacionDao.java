/*
 * Copyright 2010 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.PrestamoInformacion;
import ec.gov.iess.creditos.modelo.persistencia.pk.PrestamoPk;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b> Interfaz con operaciones para manejo de la informacion de prestamos </b>
 * 
 * @author edwin.maza
 */
@Local
public interface PrestamoInformacionDao extends GenericDao<PrestamoInformacion, PrestamoPk> {

}
