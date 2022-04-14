package ec.fin.biess.unlock.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class GenericDaoImpl<T extends Object> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2585975665540836099L;

	protected abstract EntityManager getEntityManager();

	private Class<T> entidad;

	public GenericDaoImpl(Class<T> entidad) {
		this.entidad = entidad;
	}

	/**
	 * Guarada el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public T guardar(T entidad) {

		if (entidad == null) {
			insertar(entidad);
		} else {
			actualizar(entidad);
		}

		return entidad;
	}

	/**
	 * Persite el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void insertar(T entidad) {
		getEntityManager().persist(entidad);
	}

	/**
	 * Actualiza el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void actualizar(T entidad) {
		getEntityManager().merge(entidad);
	}

	/**
	 * Elimina el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void eliminar(T entidad) {
		getEntityManager().remove(getEntityManager().merge(entidad));
	}

	public Class<T> getEntidad() {
		return entidad;
	}

	public void setEntidad(Class<T> entidad) {
		this.entidad = entidad;
	}

}
