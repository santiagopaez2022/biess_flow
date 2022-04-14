package ec.fin.biess.creditos.pq.modelo.dto;

/**
 * Clase para capturar las prestaciones de los pensionados, obtenidas del
 * servicio web del IESS a traves del ESB (biess-client-consultaprestacion)
 * 
 * @author christian.gomez
 * 
 */
public class PrestacionPensionado {

	private static final long serialVersionUID = 15487454L;

	private String identificacion;
	private String secuencialprestacion;
	private String tiposeguro;
	private String tipoprestacion;
	private String ingresos;
	private String egresos;
	private String descripcion;

	/**
	 * Constructor.
	 * 
	 * @param identificacion
	 * @param secuencialprestacion
	 * @param tiposeguro
	 * @param tipoprestacion
	 * @param ingresos
	 * @param egresos
	 * @param descripcion
	 */
	public PrestacionPensionado(String identificacion,
			String secuencialprestacion, String tiposeguro,
			String tipoprestacion, String ingresos, String egresos,
			String descripcion) {
		this.identificacion = identificacion;
		this.secuencialprestacion = secuencialprestacion;
		this.tiposeguro = tiposeguro;
		this.tipoprestacion = tipoprestacion;
		this.ingresos = ingresos;
		this.egresos = egresos;
		this.descripcion = descripcion;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the secuencialprestacion
	 */
	public String getSecuencialprestacion() {
		return secuencialprestacion;
	}

	/**
	 * @param secuencialprestacion
	 *            the secuencialprestacion to set
	 */
	public void setSecuencialprestacion(String secuencialprestacion) {
		this.secuencialprestacion = secuencialprestacion;
	}

	/**
	 * @return the tiposeguro
	 */
	public String getTiposeguro() {
		return tiposeguro;
	}

	/**
	 * @param tiposeguro
	 *            the tiposeguro to set
	 */
	public void setTiposeguro(String tiposeguro) {
		this.tiposeguro = tiposeguro;
	}

	/**
	 * @return the tipoprestacion
	 */
	public String getTipoprestacion() {
		return tipoprestacion;
	}

	/**
	 * @param tipoprestacion
	 *            the tipoprestacion to set
	 */
	public void setTipoprestacion(String tipoprestacion) {
		this.tipoprestacion = tipoprestacion;
	}

	/**
	 * @return the ingresos
	 */
	public String getIngresos() {
		return ingresos;
	}

	/**
	 * @param ingresos
	 *            the ingresos to set
	 */
	public void setIngresos(String ingresos) {
		this.ingresos = ingresos;
	}

	/**
	 * @return the egresos
	 */
	public String getEgresos() {
		return egresos;
	}

	/**
	 * @param egresos
	 *            the egresos to set
	 */
	public void setEgresos(String egresos) {
		this.egresos = egresos;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}