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

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.biess.creditos.pq.servicio.ProveedorServicio;
import ec.gov.iess.creditos.dao.ProveedorDao;
import ec.gov.iess.creditos.modelo.dto.SucursalDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.creditos.pq.util.Utilities;

/**
 * @author Edwin Maza
 * @version 1.0
 * 
 */
@Stateless
public class ProveedorServicioImpl implements ProveedorServicio {

	@EJB
	private transient ProveedorDao proveedorDao;

	public List<Proveedor> getListaProveedor() {
		return proveedorDao.getListaProveedor();
	}

	public Proveedor load(Long idProveedor) {
		return proveedorDao.load(idProveedor);
	}

	public void insertar(Proveedor proveedor) {
		proveedorDao.insert(proveedor);
	}

	public void actualizar(Proveedor proveedor) {
		proveedorDao.update(proveedor);
	}

	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#refresh(ec.gov.iess.creditos.modelo.persistencia.Proveedor)
	 */
	public void refresh(Proveedor proveedor) {
		proveedorDao.refresh(proveedor);
	}

	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#guardar(ec.gov.iess.creditos.modelo.persistencia.Proveedor)
	 */
	public Proveedor guardar(Proveedor proveedor) {
		return proveedorDao.guardar(proveedor);
	}
	
	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#obtenerDatosSucursal(java.lang.String, java.lang.String)
	 */
	public SucursalDto obtenerDatosSucursal(String rucemp, String codsuc) {
		return proveedorDao.obtenerDatosSucursal(rucemp, codsuc);
	}

	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#getRamdomPassword(int)
	 */
	public String getRamdomPassword(int tamanio) {
		return Utilities.getRamdomString(tamanio);
	}

	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#getEncriptedPassword(java.lang.String)
	 */
	public String getEncriptedPassword(String password) {
		return Utilities.hashString(password);
	}

	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#usuarioExiste(java.lang.String)
	 */
	public boolean usuarioExiste(Proveedor proveedor) {
		List<Proveedor> lproveedor = proveedorDao.usuarioExiste(proveedor);
		if (null != lproveedor && !lproveedor.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	/* 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#codigoAccesoExiste(ec.gov.iess.creditos.modelo.persistencia.Proveedor)
	 */
	public boolean codigoAccesoExiste(Proveedor proveedor) {
		List<Proveedor> lproveedor = proveedorDao.codigoAccesoExiste(proveedor);
		if (null != lproveedor && !lproveedor.isEmpty()) {
			return true;
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#consultarProveedorRucCodpretip(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public Proveedor consultarProveedorRucCodpretip(String ruc, Long codpretip) {
		return proveedorDao.consultarProveedorRucCodpretip(ruc, codpretip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.fin.biess.creditos.pq.servicio.ProveedorServicio#consultarProveedorActivoCodpretip(java.lang.Long)
	 */
	@Override
	public List<Proveedor> consultarProveedorActivoCodpretip(Long codpretip) {
		return proveedorDao.consultarProveedorActivoCodpretip(codpretip);
	}

}
