/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.SucursalDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.dao.GenericDao;

/**
 * 
 * <b> Interface para el proveedor. </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:11 $]
 *          </p>
 */
@Local
public interface ProveedorDao extends GenericDao<Proveedor, Long> {

	/**
	 * 
	 * <b> Método que consulta los proveedores activos. </b>
	 * <p>
	 * [Author: cbastidas, Date: 08/04/2011]
	 * </p>
	 * 
	 * @return List<Proveedor> Retorna una lista de objeto proveedores
	 */
	public List<Proveedor> consultarProveedor();

	/**
	 * 
	 * <b> Consulta el proveedor por numero de RUC. </b>
	 * <p>
	 * [Author: cbastidas, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param rucEmp
	 *            :Ruc del empleador
	 * @return Proveedor Objeto Proveedor
	 */
	public Proveedor consultarProveedorRUC(String rucEmp);
	
	/**
	 * Lista de todos los proveedores
	 * 
	 * @return Lista {@link Proveedor}
	 */
	public List<Proveedor> getListaProveedor();
	
	
	/**
	 * Metdo que retorna un Proveedor
	 * @param codigoTipoProducto
	 * @return {@link Proveedor}
	 */
	public Proveedor obtenerProveedorPorTipoProducto(Long codigoTipoProducto);
	
	/**
	 * Metdo que retorna lista de proveedores
	 * @param codigoTipoProducto
	 * @return List {@link Proveedor}
	 */
	public List<Proveedor> obtenerListaProveedorPorTipoProducto(Long codigoTipoProducto);

	/**
	 * Metodo que obtiene los datos principales de una empresa
	 * 
	 * @param rucemp
	 * @param codsuc
	 * @return SucursalDto
	 */
	public SucursalDto obtenerDatosSucursal(String rucemp, String codsuc);

	/**
	 * Metodo que guarda una instancia de proveedor y devuelve dicha instancia
	 * 
	 * @param proveedor
	 * @return Proveedor
	 */
	public Proveedor guardar(Proveedor proveedor);

	/**
	 * Metodo que obtiene la lista de usuarios que tiene el mismo nombre de usuario
	 * 
	 * @param proveedor
	 * @return List<Proveedor>
	 */
	public List<Proveedor> usuarioExiste(Proveedor proveedor);

	/**
	 * Metodo que obtiene la lista de usuarios que tiene el mismo codigo de acceso
	 * 
	 * @param proveedor
	 * @return List<Proveedor>
	 */
	public List<Proveedor> codigoAccesoExiste(Proveedor proveedor);
	
	/**
	 * Consulta un proveedor dado el RUC y el codpretip
	 * 
	 * @param ruc
	 * @param codpretip
	 * @return
	 */
	Proveedor consultarProveedorRucCodpretip(String ruc, Long codpretip);
	
	/**
	 * Consulta los proveedores activos dado el codpretip
	 * 
	 * @param codpretip
	 * @return
	 */
	List<Proveedor> consultarProveedorActivoCodpretip(Long codpretip);
	
}