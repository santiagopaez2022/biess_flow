/*
 * Copyright 2012 BANCO DEL INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import ec.fin.biess.auditoria.cliente.exception.RegistroAuditoriaException;
import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.modelo.persistencia.Prestamo;
import ec.gov.iess.creditos.pq.excepcion.PrestamoException;
import ec.gov.iess.creditos.pq.servicio.AdministracionOrdenCompraProveedorServicio;
import ec.gov.iess.creditos.pq.servicio.PrestamoServicio;
import ec.gov.iess.cuentabancaria.servicio.CuentaBancariaAfiliadoServicio;
import ec.gov.iess.hl.exception.DivisionPoliticaException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.Sucursal;
import ec.gov.iess.hl.servicio.DivisionPoliticaServicio;
import ec.gov.iess.hl.servicio.SucursalServicio;
import ec.gov.iess.planillaspq.modelo.persistencia.reportes.EstadoERC;
import ec.gov.iess.planillaspq.web.backing.dm.DesbloqueoClienteDM;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.UtilDate;
import ec.gov.iess.planillaspq.web.util.UtilReport;

/**
 * Clase controlador Desbloqueo Cliente
 * 
 * @author pjarrin
 * 
 */
public class DesbloqueoClienteBacking extends UtilReport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DesbloqueoClienteDM desbloqueoDM;

	@EJB(name = "PrestamoServicioImpl/local")
	private PrestamoServicio prestamoServicio;

	@EJB(name = "AdministracionOrdenCompraProveedorServicioImpl/local")
	private AdministracionOrdenCompraProveedorServicio administracionordencompraservicio;

	// cambio para control de fraudes 19/08/2010
	@EJB(name = "CuentaBancariaAfiliadoServicioImpl/local")
	private CuentaBancariaAfiliadoServicio cuentabancariaafiliadoservicio;

	@EJB(name = "SucursalServicioImpl/local")
	private SucursalServicio sucursalservicio;

	@EJB(name = "DivisionPoliticaServicioImpl/local")
	private DivisionPoliticaServicio divisionpoliticaservicio;

	private String cedulafuncionario = null;

	private static final LoggerBiess log = LoggerBiess
			.getLogger(DesbloqueoClienteBacking.class);

	// parametros para la consulta
	private String primeravez = null;
	
  	public DesbloqueoClienteBacking() {
  	}

	/**
	 * Inicializa los datos
	 */
	public void init() {
		getDesbloqueoDM().setCedula(null);
		getDesbloqueoDM().setFechaDesde(new Date());
		getDesbloqueoDM().setFechaHasta(new Date());
		getDesbloqueoDM().setListaPrestamo(null);
		getDesbloqueoDM().setEstadoPrestamo(null);
	}

	/**
	 * Funcion para consultar los prestamos en estado de bloqueo
	 */
	public void consultarPrestamosEstadoBloqueo() {
		try {
			List<Prestamo> lprestamos = new ArrayList<Prestamo>();
			if (null == desbloqueoDM.getEstadoPrestamo()
					|| desbloqueoDM.getEstadoPrestamo().compareTo("") == 0
					|| desbloqueoDM.getEstadoPrestamo().compareTo("ERC") == 0) {
				lprestamos.addAll(prestamoServicio.obtenerCreditoPorParametros(
						desbloqueoDM.getFechaDesde(),
						desbloqueoDM.getFechaHasta(), desbloqueoDM.getCedula(),
						"ERC", "S"));
			}
			if (null == desbloqueoDM.getEstadoPrestamo()
					|| desbloqueoDM.getEstadoPrestamo().compareTo("") == 0
					|| desbloqueoDM.getEstadoPrestamo().compareTo("ECC") == 0) {
				lprestamos.addAll(prestamoServicio.obtenerCreditoPorParametros(
						desbloqueoDM.getFechaDesde(),
						desbloqueoDM.getFechaHasta(), desbloqueoDM.getCedula(),
						"ECC", "C"));
			}
			desbloqueoDM.setListaPrestamo(lprestamos);
			
			// Inicio Auditoria
			String estado = desbloqueoDM.getEstadoPrestamo();
			if (null == estado || estado.isEmpty()) {
				estado = "Todos";
			}
			
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultarExcelDesbloqueo(this.desbloqueoDM.getCedula(), estado,
					UtilDate.dateToString(this.desbloqueoDM.getFechaDesde(), "dd/MM/yyyy"),
					UtilDate.dateToString(this.desbloqueoDM.getFechaHasta(), "dd/MM/yyyy"));
			super.guardarAuditoria(OperacionEnum.CONSULTAR_DESBLOQUEO_CLIENTE, parametroEvento, this.desbloqueoDM.getCedula());
			// Fin auditoria
			
		} catch (PrestamoException e) {
			addErrorMessage("No se pudo obtener información de Préstamos bloqueados.");
			log.error("Se ha producido un error en la consulta de créditos: " + e.getMessage());
		} catch (Exception e) {
			addErrorMessage("No se pudo obtener información de Préstamos bloqueados.");
			log.error("Se ha producido un error en la consulta de créditos: " + e.getMessage());
		}
	}

	/**
	 * Desbloquea el credito en estado ERC
	 */
	public void desBloquearPrestamo() {

		Prestamo pqBlq = (Prestamo) desbloqueoDM.getTblSolicitud().getRowData();
		String estadoPrestamoAnterior = pqBlq.getEstadoPrestamo().getCodestpre();
		Object objfun = this.context().getELContext().getELResolver()
				.getValue(this.context().getELContext(), null, "funcionario");
		this.cedulafuncionario = ((FuncionarioHandler) objfun).getCedula();
		try {
			if (pqBlq.getEstadoPrestamo().getCodestpre().equals("VIG")
					&& (pqBlq.getValidacionRegistroCivil().equals("S") || pqBlq.getValidacionRegistroCivil().equals("C"))) {
				prestamoServicio.desbloquearPrestamoNovacion(pqBlq,this.cedulafuncionario);
			} else {
				administracionordencompraservicio.desbloquearPrestamo(pqBlq,
						this.cedulafuncionario);
			}
			desbloqueoDM.getListaPrestamo().remove(pqBlq);

			addInfoMessage("Se ha Desbloqueado el préstamo del afiliado : "
					+ pqBlq.getAfiliado().getApenomafi() + " con la cédula : "
					+ pqBlq.getNumafi());
			
			// Inicio Auditoria
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosDesbloqueoPrestamo(pqBlq.getNumafi(),
					estadoPrestamoAnterior, UtilDate.dateToString(desbloqueoDM.getFechaDesde(), "dd/MM/yyyy"),
					UtilDate.dateToString(desbloqueoDM.getFechaHasta(), "dd/MM/yyyy"), String.valueOf(pqBlq.getPrestamoPk().getCodprecla()),
					String.valueOf(pqBlq.getPrestamoPk().getCodpretip()), String.valueOf(pqBlq.getPrestamoPk().getNumpreafi()),
					String.valueOf(pqBlq.getPrestamoPk().getOrdpreafi()));
			super.guardarAuditoria(OperacionEnum.DESBLOQUEAR_CLIENTE, parametroEvento, pqBlq.getNumafi());
			// Fin auditoria
		} catch (Exception e) {
			log.error("Error en desbloqueo de prestamo", e);
			addErrorMessage("Error en el proceso de bloqueo del crédito");
		}
	}

	/**
	 * Retorna la fecha bancaria
	 * 
	 * @return
	 */
	public Date getFechaBancaria() {
		Prestamo pqpda = (Prestamo) desbloqueoDM.getTblSolicitud().getRowData();
		String cedula = pqpda.getNumafi();

		try {
			desbloqueoDM.setCuentaBancaria(cuentabancariaafiliadoservicio.findCuentaValidaAfiliado(cedula));
			
		if (desbloqueoDM.getCuentaBancaria() == null || desbloqueoDM.getCuentaBancaria().getCodigo()==null) {
			desbloqueoDM.setFechaBancaria(null);
			return null;
		}
			desbloqueoDM.setFechaBancaria(desbloqueoDM.getCuentaBancaria().getFechaRegistro());

		} catch (Exception e) {
			desbloqueoDM.setFechaBancaria(null);
			addErrorMessage("Error en obtener la fecha bancaria.");
		}

		return desbloqueoDM.getFechaBancaria();
	}

	public String getPrimeraVez() {
		if (desbloqueoDM.getCuentaBancaria() == null)
			return null;
		if (desbloqueoDM.getCuentaBancaria().getFechaActualizacion() == null) {
			this.primeravez = "PRIMERA VEZ";
		} else {
			this.primeravez = "ACTUALIZACION";
		}
		return this.primeravez;
	}

	/**
	 * Retorna la sucursal
	 * 
	 * @return
	 */
	public Sucursal getSucursalfila() {
		
		Prestamo pqpda = (Prestamo) desbloqueoDM.getTblSolicitud().getRowData();
		String rucemp = pqpda.getRucemp();
		String codsucu = pqpda.getCodsuc();

		try {
			desbloqueoDM.setSucursal(sucursalservicio.consultarSucursal(rucemp,
					codsucu));
			
			if(desbloqueoDM.getSucursal() ==null){
				desbloqueoDM.setSucursal(new Sucursal());
			}
		} catch (SucursalException e) {
			desbloqueoDM.setSucursal(new Sucursal());
			addErrorMessage("Error consultando la sucursal donde trabaja el afiliado");
		} catch (Exception e) {
			desbloqueoDM.setSucursal(new Sucursal());
			addErrorMessage("Error consultando la sucursal donde trabaja el afiliado");
		}
		
		return desbloqueoDM.getSucursal();
	}

	/**
	 * Retorna la division politica
	 * 
	 * @return
	 */
	public DivisionPolitica getDivipoliticaPatrono() {
		if (desbloqueoDM.getSucursal() != null && desbloqueoDM.getSucursal().getCoddivpol() != null) {
			try {
				desbloqueoDM.setDivisionPolitica(divisionpoliticaservicio.consultaDivisionPoliticaPorId(desbloqueoDM
								.getSucursal().getCoddivpol()));
			} catch (DivisionPoliticaException e) {
				desbloqueoDM.setDivisionPolitica(null);
				addErrorMessage("Error consultando la division politica de la sucursal del afiliado");
			} catch (Exception e) {
				desbloqueoDM.setDivisionPolitica(null);
				addErrorMessage("Error consultando la division politica de la sucursal del afiliado");
			}
		} else {
			desbloqueoDM.setDivisionPolitica(null);
		}
		return desbloqueoDM.getDivisionPolitica();
	}

	public String getCedulafuncionario() {
		return cedulafuncionario;
	}

	public void setCedulafuncionario(String cedulafuncionario) {
		this.cedulafuncionario = cedulafuncionario;
	}

	/**
	 * Exporta a pdf la lista mostrada en pantalla de registros en estado ERC
	 */
	public void exportarEstadoERC() {
		try {
			if (desbloqueoDM.getListaPrestamo() != null
					&& desbloqueoDM.getListaPrestamo().size() > 0) {
				exportXlsReport(llenarParametros(), llenarLista(), "EstadoERC");
				
				String estado = desbloqueoDM.getEstadoPrestamo();
				if (null == estado || estado.isEmpty()) {
					estado = "Todos";
				} 
				
				// Inicio Auditoria
				ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultarExcelDesbloqueo(this.desbloqueoDM.getCedula(), estado,
						UtilDate.dateToString(this.desbloqueoDM.getFechaDesde(), "dd/MM/yyyy"),
						UtilDate.dateToString(this.desbloqueoDM.getFechaHasta(), "dd/MM/yyyy"));
				super.guardarAuditoria(OperacionEnum.EXPORTAR_EXCEL_DESBLOQUEO, parametroEvento, this.desbloqueoDM.getCedula());
				// Fin auditoria
			}
		} catch (RegistroAuditoriaException e) {
			log.error("Error al guardar auditoria en DesbloqueoClienteBacking " + e);
		} catch (Exception e) {
			log.error("Se presento un error al exportar a Excel", e);
		}

	}

	/**
	 * Llena la lista del reporte registros en estado erc
	 * 
	 * @return
	 */
	private List<EstadoERC> llenarLista() {
		List<EstadoERC> lista = null;
		lista = new ArrayList<EstadoERC>();
		for (Prestamo p : desbloqueoDM.getListaPrestamo()) {
			EstadoERC eERC = new EstadoERC();
			if (p.getCuentaBancariaAnterior() != null) {
				eERC.setCuentaAnterior(p.getCuentaBancariaAnterior()
						.getNumeroCuentaAnterior());
				eERC.setInstitucionAnterior(p.getCuentaBancariaAnterior()
						.getEntidadFinancieraAnterior());
				eERC.setFechaRegistroAnterior(p.getCuentaBancariaAnterior()
						.getFechaRegistroAnterior());
				eERC.setTipoAnterior(p.getCuentaBancariaAnterior()
						.getTipoCuentaAnterior());
			}
			Sucursal s = obtenerSucursal(p);
			eERC.setNumeroCedula(p.getNumafi());
			eERC.setMontoCredito(p.getMntpre());
			eERC.setNumeroCuenta(p.getNumctaban());
			eERC.setFechaRegistro(obtenerFechaCuentaBancaria(p));
			eERC.setActualizacion(obtenerPrimeraVez(p));
			if (p.getInstitucionfinanciera() != null) {
				eERC.setNombreInstitucion(p.getInstitucionfinanciera()
						.getNomemp());
			}
			if (p.getAfiliado() != null) {
				eERC.setNombreCompleto(p.getAfiliado().getApenomafi());
				eERC.setNumeroTelefonicoAsegurado(p.getAfiliado().getTelafi());
				if (p.getAfiliado().getDivisionPolitica() != null
						&& p.getAfiliado().getDivisionPolitica()
								.getDivisionPolitica() != null) {
					eERC.setCuidadAsegurado(p.getAfiliado()
							.getDivisionPolitica().getDivisionPolitica()
							.getNomdivpol());
					if (p.getAfiliado().getDivisionPolitica()
							.getDivisionPolitica().getDivisionPolitica() != null) {
						eERC.setProvinciaAsegurado(p.getAfiliado()
								.getDivisionPolitica().getDivisionPolitica()
								.getDivisionPolitica().getNomdivpol());
					}
				}
			}
			if (s != null) {
				DivisionPolitica dp = obtenerDivipoliticaPatrono(s);
				eERC.setNumeroTelefonicoPatrono(s.getTelsuc());
				if (dp != null && dp.getDivisionPolitica() != null) {
					eERC.setCuidadPatrono(dp.getDivisionPolitica()
							.getNomdivpol());
					if (dp.getDivisionPolitica().getDivisionPolitica() != null) {
						eERC.setProvinciaPatrono(dp.getDivisionPolitica()
								.getDivisionPolitica().getNomdivpol());
					}
				}

			}
			if (p.getKspcotsri() != null) {
				eERC.setNombreEmpresa(p.getKspcotsri().getRazsoc());
			}
			/* Estado del credito */
			eERC.setEstadoCredito(p.getEstadoPrestamo().getCodestpre());
			lista.add(eERC);
		}
		return lista;
	}

	/**
	 * Obtiene una sucursal en base al prestamo
	 * 
	 * @param p
	 * @return
	 */
	private Sucursal obtenerSucursal(Prestamo p) {
		String rucemp = p.getRucemp();
		String codsucu = p.getCodsuc();
		Sucursal su = null;

		try {
			su = sucursalservicio.consultarSucursal(rucemp, codsucu);
		} catch (SucursalException e) {
			log.error("Error consultando la sucursal donde trabaja el afiliado");
		} catch (Exception e) {
			log.error("Error consultando la sucursal donde trabaja el afiliado");
		}
		return su;
	}

	/**
	 * Obtiene la division politica del patrono en base a la sucursal
	 * 
	 * @param s
	 * @return
	 */
	private DivisionPolitica obtenerDivipoliticaPatrono(Sucursal s) {
		DivisionPolitica dp = null;
		try {
			dp = divisionpoliticaservicio.consultaDivisionPoliticaPorId(s
					.getCoddivpol());
		} catch (DivisionPoliticaException e) {
			log.error("Error consultando la division politica de la sucursal del afiliado");
		} catch (Exception e) {
			log.error("Error consultando la division politica de la sucursal del afiliado");
		}
		return dp;
	}

	/**
	 * Obtiene la fecha de la cuenta bancaria
	 * 
	 * @param p
	 * @return
	 */
	private Date obtenerFechaCuentaBancaria(Prestamo p) {
		String cedula = p.getNumafi();
		try {
			desbloqueoDM.setCuetanBancariaReporte(cuentabancariaafiliadoservicio.findCuentaValidaAfiliado(cedula));

			return desbloqueoDM.getCuetanBancariaReporte() == null ? null : desbloqueoDM.getCuetanBancariaReporte()
					.getFechaRegistro();
		} catch (Exception e) {
			log.error("Error al obtener la fecha de la cuenta bancaria.");
		}

		return null;
	}

	/**
	 * Retorna al reporte si la cuenta bancaria es actualizada o no
	 * 
	 * @param p
	 * @return
	 */
	private String obtenerPrimeraVez(Prestamo p) {
		if (desbloqueoDM.getCuetanBancariaReporte() == null)
			return null;
		if (desbloqueoDM.getCuetanBancariaReporte().getFechaActualizacion() == null) {
			return "NO";
		} else {
			return "SI";
		}
	}

	/**
	 * Llena los parametros del reporte
	 */
	private Map<String, Object> llenarParametros() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PAR_IMAGEN",
				getServletContext().getRealPath("/img/report/biess-logo.png"));
		params.put("PAR_FEC_INI", desbloqueoDM.getFechaDesde());
		params.put("PAR_FEC_FIN", desbloqueoDM.getFechaHasta());
		if (null == desbloqueoDM.getEstadoPrestamo()) {
			params.put("PAR_TITULO", "PRESTAMOS EN ESTADO ERC/ECC");
		} else if (desbloqueoDM.getEstadoPrestamo().equals("ERC")) {
			params.put("PAR_TITULO",
					"PRESTAMOS EN ESTADO ERROR REGISTRO CIVIL (ERC)");
		} else if (desbloqueoDM.getEstadoPrestamo().equals("ECC")) {
			params.put("PAR_TITULO",
					"PRESTAMOS EN ESTADO CAMBIO CUENTA BANCARIA (ECC)");
		}

		return params;
	}

	/**
	 * Setea desbloqueoDM
	 * 
	 * @param desbloqueoDM
	 */
	public void setDesbloqueoDM(DesbloqueoClienteDM desbloqueoDM) {
		this.desbloqueoDM = desbloqueoDM;
	}

	/**
	 * Obtiene desbloqueoDM
	 * 
	 * @return
	 */
	public DesbloqueoClienteDM getDesbloqueoDM() {
		return desbloqueoDM;
	}
	
	/**
	 * Valida que el periodo en tre la fecha desde y hasta no supere un mes.
	 */
	public void validarFechaDesde() {
		// Mejora para limitaciones en consultas.
		Calendar cal = Calendar.getInstance();
		cal.setTime(desbloqueoDM.getFechaDesde());
		cal.add(Calendar.MONTH, 1);
		if (desbloqueoDM.getFechaHasta().after(cal.getTime())) {
			desbloqueoDM.setFechaHasta(cal.getTime());
		}
	}

	/**
	 * Valida que el periodo en tre la fecha desde y hasta no supere un mes.
	 */
	public void validarFechaHasta() {
		// Mejora para limitaciones en consultas.
		Calendar cal = Calendar.getInstance();
		cal.setTime(desbloqueoDM.getFechaHasta());
		cal.add(Calendar.MONTH, -1);
		if (desbloqueoDM.getFechaDesde().before(cal.getTime())) {
			desbloqueoDM.setFechaDesde(cal.getTime());
		}
	}
}
