/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.dto.SucursalDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;

/**
 * Servicio para Proveedor.
 * 
 * @author edwin.maza
 * @version 1.0
 * 
 */
@Local
public interface ProveedorServicio {
	/**
	 * Lista de todos los proveedores
	 * 
	 * @return Lista {@link Proveedor}
	 */
	List<Proveedor> getListaProveedor();

	/**
	 * @param idProveedor
	 * @return
	 */
	Proveedor load(Long idProveedor);

	/**
	 * Metodo que permite insertar un nuevo proveedor
	 */
	void insertar(Proveedor proveedor);

	/**
	 * Metodo que permite actualiazar un registro
	 */
	void actualizar(Proveedor proveedor);

	/**
	 * Metodo que hace un refresh de la entidad
	 * 
	 * @param proveedor
	 */
	public void refresh(Proveedor proveedor);
		
	/**
	 * Metodo que guarda una inatacia de Proveedor y devuelve la misma
	 * 
	 * @param proveedor
	 * @return Proveedor
	 */
	public Proveedor guardar(Proveedor proveedor);
	
	/**
	 * Metodo que obtiene los datos principales de una empresa
	 * 
	 * @param rucemp
	 * @param codsuc
	 * @return SucursalDto
	 */
	public SucursalDto obtenerDatosSucursal(String rucemp, String codsuc);

	/**
	 * Metodo que obtiene un password aleatorio del tamanio indicado
	 * 
	 * @param tamanio
	 * @return String
	 */
	public String getRamdomPassword(int tamanio);
	
	/**
	 * Metodo que encripta el password en SHA-512
	 * 
	 * @param password
	 * @return String
	 */
	public String getEncriptedPassword(String password);

	/**
	 * Metodo que determina si un nombre de usuario ya existe en la lista de proveedores
	 * 
	 * @param proveedor
	 * @return boolean
	 */
	public boolean usuarioExiste(Proveedor proveedor);

	/**
	 * Metodo que determina si un codigo de acceso ya existe en la lista de proveedores
	 * 
	 * @param proveedor
	 * @return boolean
	 */
	public boolean codigoAccesoExiste(Proveedor proveedor);
	
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
