package ec.gov.iess.creditos.sp;

import java.util.List;
public interface SolicitudCreditoJdbc {
	public List<String> consultarCedulasConSolicitudDuplicada(
			List<Long> tiposSolicitud, String estado, Integer count);

}
