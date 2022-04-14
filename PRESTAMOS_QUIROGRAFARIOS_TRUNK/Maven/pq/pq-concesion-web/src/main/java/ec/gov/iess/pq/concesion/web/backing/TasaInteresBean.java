package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;

import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.excepcion.TasaInteresExcepcion;
import ec.gov.iess.creditos.pq.servicio.CondicionCalculoServicio;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

public class TasaInteresBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 3554867559344052832L;

	private BigDecimal tasaInteresActual = new BigDecimal(-1);

	@EJB(name = "CondicionCalculoServicioImpl/local")
	CondicionCalculoServicio condicionCalculoServicio;

	/**
	 * @return the tasaInteresActual
	 */
	public BigDecimal getTasaInteresActual() {
		if (tasaInteresActual == null
				|| tasaInteresActual.compareTo(new BigDecimal(-1)) == 0) {
			try {
				tasaInteresActual = condicionCalculoServicio
						.consultarTasaInteres(
								ConstantesCreditos.ID_TASA_BCO_CENTRAL,
								ConstantesCreditos.ID_TASA_ACTORIAL,
								new Date(), ConstantesCreditos.NUMERO_SEMANAS);
			} catch (TasaInteresExcepcion e) {
				tasaInteresActual = new BigDecimal(-1);
			}
		}
		return tasaInteresActual;
	}

	/**
	 * @param tasaInteresActual
	 *            the tasaInteresActual to set
	 */
	public void setTasaInteresActual(BigDecimal tasaInteresActual) {
		this.tasaInteresActual = tasaInteresActual;
	}
}
