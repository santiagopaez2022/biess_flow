package ec.fin.biess.creditos.pq.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.consultaprestacion.client.mq.modelo.ConsultaPrestacionResp;
import ec.fin.biess.consultaprestacion.client.mq.modelo.PrestacionType;
import ec.fin.biess.consultaprestacion.client.mq.service.PensionServiceLocal;
import ec.fin.biess.creditos.pq.dao.TipoPrestacionDao;
import ec.fin.biess.creditos.pq.excepcion.PrestacionPensionadosException;
import ec.fin.biess.creditos.pq.helper.AuditoriaHelperPqConcesion;
import ec.fin.biess.creditos.pq.modelo.dto.InformacionPrestacionPensionado;
import ec.fin.biess.creditos.pq.modelo.dto.PrestacionPensionado;
import ec.fin.biess.creditos.pq.modelo.persistencia.TipoPrestacion;
import ec.fin.biess.creditos.pq.modelo.persistencia.TipoPrestacionPk;
import ec.fin.biess.creditos.pq.servicio.ConsultaPrestacionPensionadosServicioLocal;
import ec.fin.biess.creditos.pq.servicio.ServicioAuditoriaPqConcesionEjbLocal;
import ec.fin.biess.tools.integracion.excepciones.ServicioESBExcepcion;
import ec.gov.biess.util.log.LoggerBiess;

/**
 * Implementacion de las consultas al web service de prestaciones de pensionados del iess
 * 
 * @author christian.gomez
 * 
 */
@Stateless
public class ConsultaPrestacionPensionadosServicioImpl implements ConsultaPrestacionPensionadosServicioLocal {

	private transient LoggerBiess log = LoggerBiess.getLogger(ConsultaPrestacionPensionadosServicioImpl.class);

	@EJB(beanName = "PensionServiceImpl")
	private PensionServiceLocal pensionServicioLocal;

	@EJB
	private TipoPrestacionDao tipoPrestacionDao;
	
	@EJB
    private ServicioAuditoriaPqConcesionEjbLocal servicioAuditoria;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.fin.biess.creditos.pq.servicio.ConsultaPrestacionPensionadosServicioLocal#consultarPrestaciones(java.lang.
	 * String, java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InformacionPrestacionPensionado consultarPrestaciones(String identificacion, String direccionIP)
			throws PrestacionPensionadosException {

		List<PrestacionPensionado> prestaciones = new ArrayList<PrestacionPensionado>();
		BigDecimal ingresos = BigDecimal.ZERO;
		BigDecimal egresos =  BigDecimal.ZERO;
		BigDecimal valorAComprometer =  BigDecimal.ZERO;
		String exito = "";
		String observacion = "";

		try {
			// Consumo de ws de prestaciones del IESS.
			ConsultaPrestacionResp resp = pensionServicioLocal.consultarPrestacion("CLI-PQ", identificacion, "QH");

			if ("0".compareTo(resp.getControlRes().getCodErr()) != 0) {
				exito = "false";
				observacion = resp.getControlRes().getCodErr() + " - " + resp.getControlRes().getDesErr();
				throw new PrestacionPensionadosException(resp.getControlRes().getDesErr());
			} else {
				for (PrestacionType preType : resp.getPRESTACIONES().getPRESTACION()) {
					PrestacionPensionado prestacionPensionado = new PrestacionPensionado(preType.getIDENTIFICACION(),
							preType.getSECUENCIALPRESTACION(), preType.getTIPOSEGURO(), preType.getTIPOPRESTACION(),
							preType.getINGRESOS(), preType.getEGRESOS(), null);

					TipoPrestacion tipoPrestacion = tipoPrestacionDao.load(new TipoPrestacionPk(prestacionPensionado
							.getTipoprestacion(), prestacionPensionado.getTiposeguro()));

					prestacionPensionado.setDescripcion(tipoPrestacion.getTipoSeguro().getNombre() + " - "
							+ tipoPrestacion.getNombre());

					prestaciones.add(prestacionPensionado);

					// Calcular ingresos y egresos
					ingresos = ingresos.add(new BigDecimal(prestacionPensionado.getIngresos()));
					egresos = egresos.add(new BigDecimal(prestacionPensionado.getEgresos()));
				}

				valorAComprometer = ingresos.subtract(egresos);

				exito = "true";
				observacion = "Total ingresos: " + ingresos + ", total egresos: " + egresos + ", valor a comprometer: " + valorAComprometer;
			}

			return new InformacionPrestacionPensionado(ingresos.setScale(2, BigDecimal.ROUND_HALF_UP),
					egresos.setScale(2, BigDecimal.ROUND_HALF_UP), valorAComprometer.setScale(2,
							BigDecimal.ROUND_HALF_UP), prestaciones);
		} catch (ServicioESBExcepcion e) {
			log.error("Error al consumir el servicio web de consulta de prestaciones del IESS.", e);
			exito = "false";
			observacion = "Error de ESBException: " + e.getMessage();
			throw new PrestacionPensionadosException(
					"Estimado cliente. Al momento la consulta de los valores de ingresos y egresos no est\u00E1 disponible. Por favor Intente m\u00E1s tarde.");
		} catch (Exception e) {
			exito = "false";
			observacion = "Error al consumir servicio de Prestaciones Pensionados: " + e.getMessage();
			log.error("Error no manejado al consumir servicio Prestaciones Pensionados.", e);
			throw new PrestacionPensionadosException(e.getMessage());
		} finally {
			// Inicio auditoria
			ParametroEvento parametroEvento = AuditoriaHelperPqConcesion.obtenerParametrosConsumeServicioWeb(identificacion, exito, observacion);
			this.servicioAuditoria.guardarAuditoria(OperacionEnum.CONSUMO_SERVICIO_PRESTACIONES, parametroEvento, direccionIP, identificacion);
			// Fin auditoria
		}
	}

}
