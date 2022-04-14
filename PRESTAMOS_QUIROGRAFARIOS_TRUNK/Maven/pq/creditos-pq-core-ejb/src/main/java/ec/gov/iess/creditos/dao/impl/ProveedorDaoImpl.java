/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.gov.iess.creditos.dao.ProveedorDao;
import ec.gov.iess.creditos.enumeracion.EstadoProveedor;
import ec.gov.iess.creditos.modelo.dto.SucursalDto;
import ec.gov.iess.creditos.modelo.persistencia.Proveedor;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * 
 * <b> Implementacion de los métodos del Proveedor </b>
 * 
 * @author cbastidas
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: smanosalvas $, $Date: 2011/05/03 13:28:11 $]
 *          </p>
 */
@Stateless
public class ProveedorDaoImpl extends GenericEjbDao<Proveedor, Long>
		implements ProveedorDao {

	public ProveedorDaoImpl() {
		super(Proveedor.class);
	}

	@SuppressWarnings("unchecked")
	public List<Proveedor> consultarProveedor() {
		List<Proveedor> lista = new ArrayList<Proveedor>();

		Query q = em.createNamedQuery("Proveedor.consultarProveedor");
		q.setParameter("catalogoEstado", EstadoProveedor.EstadoProveedor.getDescripcion());
		q.setParameter("estado", EstadoProveedor.Activo.getDescripcion());
		lista = q.getResultList();
		return lista;
	}

	public Proveedor consultarProveedorRUC(String rucEmp) {
		Query q = em.createNamedQuery("Proveedor.consultarProveedorRuc");
		q.setParameter("rucEmp", rucEmp);
		q.setParameter("catalogoEstado", EstadoProveedor.EstadoProveedor.getDescripcion());
		q.setParameter("estado", EstadoProveedor.Activo.getDescripcion());
		return (Proveedor) q.getSingleResult();
	}

	/** 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#getListaProveedor()
	 */
	@SuppressWarnings("unchecked")
	public List<Proveedor> getListaProveedor() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p FROM Proveedor p WHERE p.estado=:estado");
		Query query = em.createQuery(sql.toString());
		query.setParameter("estado", "ACT");
		return query.getResultList();
	}

	/** 
	 * @see
	 * ec.gov.iess.creditos.dao.ProveedorDao#obtenerProveedorPorTipoProducto
	 * (java.lang.Long)
	 */
	public Proveedor obtenerProveedorPorTipoProducto(Long codigoTipoProducto) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p FROM Proveedor p");
		sql.append(" WHERE p.tipoPrestamoProducto.codigo=:codigoTipoProducto");
		Query query = em.createQuery(sql.toString());
		query.setParameter("codigoTipoProducto", codigoTipoProducto);
		query.setMaxResults(1);
		return (Proveedor) query.getSingleResult();
	}
	
	/**
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#obtenerListaProveedorPorTipoProducto(java.lang.Long)
	 */
	public List<Proveedor> obtenerListaProveedorPorTipoProducto(Long codigoTipoProducto) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p FROM Proveedor p");
		sql.append(" WHERE p.tipoPrestamoProducto.codigo=:codigoTipoProducto");
		sql.append(" AND p.detalleCatalogo.id.dcCodigo = :dcCodigo ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("codigoTipoProducto", codigoTipoProducto);
		query.setParameter("dcCodigo", "ACT");
		return query.getResultList();
	}
	
	/* 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#obtenerDatosSucursal(java.lang.String, java.lang.String)
	 */
	public SucursalDto obtenerDatosSucursal(String rucemp, String codsuc) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" select dessuc nombre, dirsuc direccion, telsuc telefono, cedrepleg cedulaRepLegal, ");
			sql.append("   nomesprepleg nombreRepLegal, telrepleg telefonoRepLegal, rucbandeb rucInstFinanciera, "); 
			sql.append("   numcueban numeroCuenta, tipcueban tipoCuenta,coddivpol codDivisionPolitica   from kspcotsucursales ");
			sql.append(" where rucemp = :rucemp and codsuc = :codsuc ");
			Query query = em.createNativeQuery(sql.toString(), SucursalDto.class);
			query.setParameter("rucemp", rucemp);
			query.setParameter("codsuc", codsuc);
			
			return (SucursalDto) query.getSingleResult();
		} catch (Exception e) {
			return new SucursalDto();
		}
	}

	/* 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#guardar(ec.gov.iess.creditos.modelo.persistencia.Proveedor)
	 */
	public Proveedor guardar(Proveedor proveedor) {
		this.em.persist(proveedor);
		
		return proveedor;
	}
	
	/* 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#usuarioExiste(ec.gov.iess.creditos.modelo.persistencia.Proveedor)
	 */
	public List<Proveedor> usuarioExiste(Proveedor proveedor) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p FROM Proveedor p ");
		sql.append(" WHERE p.usuarioContacto = :usuarioContacto ");
		sql.append(" AND (:idProveedor IS NULL OR p.prIdProveedor <> :idProveedor) ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("usuarioContacto", proveedor.getUsuarioContacto());
		query.setParameter("idProveedor", proveedor.getPrIdProveedor());

		return query.getResultList();		
	}
	
	/* 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#codigoAccesoExiste(ec.gov.iess.creditos.modelo.persistencia.Proveedor)
	 */
	public List<Proveedor> codigoAccesoExiste(Proveedor proveedor) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p FROM Proveedor p ");
		sql.append(" WHERE p.codigoAccesoWS = :codigoAccesoWS ");
		sql.append(" AND (:idProveedor IS NULL OR p.prIdProveedor <> :idProveedor) ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("codigoAccesoWS", proveedor.getCodigoAccesoWS());
		query.setParameter("idProveedor", proveedor.getPrIdProveedor());

		return query.getResultList();		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#consultarProveedorRucCodpretip(java.lang.String, java.lang.Long)
	 */
	@Override
	public Proveedor consultarProveedorRucCodpretip(String ruc, Long codpretip) {
		Query q = em.createNamedQuery("Proveedor.consultarProveedorRucCodpretip");
		q.setParameter("rucEmp", ruc);
		q.setParameter("catalogoEstado", EstadoProveedor.EstadoProveedor.getDescripcion());
		q.setParameter("estado", EstadoProveedor.Activo.getDescripcion());
		q.setParameter("codpretip", codpretip);
		
		return (Proveedor) q.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.dao.ProveedorDao#consultarProveedorActivoCodpretip(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> consultarProveedorActivoCodpretip(Long codpretip) {
		Query q = em.createNamedQuery("Proveedor.consultarProveedorActivoCodpretip");
		q.setParameter("catalogoEstado", EstadoProveedor.EstadoProveedor.getDescripcion());
		q.setParameter("estado", EstadoProveedor.Activo.getDescripcion());
		q.setParameter("codpretip", codpretip);

		return q.getResultList();
	}

}
