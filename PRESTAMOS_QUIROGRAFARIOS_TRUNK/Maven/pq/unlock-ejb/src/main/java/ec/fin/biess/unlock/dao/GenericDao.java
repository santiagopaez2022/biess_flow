package ec.fin.biess.unlock.dao;



public interface GenericDao<T extends Object> {

	/**
	 * Guarada el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public T guardar(T entidad);

	/**
	 * Persite el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void insertar(T entidad);

	/**
	 * Actualiza el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void actualizar(T entidad);

	/**
	 * Elimina el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void eliminar(T entidad);

}
