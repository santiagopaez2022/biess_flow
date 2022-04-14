package ec.gov.iess.planillaspq.web.bean;

import java.io.Serializable;

import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.hl.modelo.Empleador;

/**
 * Clase usada para guardar los datos de un prestamos ya concedido. Esta clase
 * es usada solamente para la ocnuslta de prestamos.
 * 
 * @author jvaca
 * 
 */
public class PrestamoBean implements Serializable {

	private static final long serialVersionUID = 5911878537669138971L;

	private Prestamo prestamo;
	private Empleador institucionFinanciera;

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Empleador getInstitucionFinanciera() {
		return institucionFinanciera;
	}

	public void setInstitucionFinanciera(Empleador institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}

}
