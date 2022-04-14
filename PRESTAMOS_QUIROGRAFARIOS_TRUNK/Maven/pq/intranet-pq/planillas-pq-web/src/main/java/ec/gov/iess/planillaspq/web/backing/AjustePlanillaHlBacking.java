/**
 * 
 */
package ec.gov.iess.planillaspq.web.backing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.dao.ComprobantePagoPlaDao;
import ec.gov.iess.planillaspq.exceptions.AjustaPlanillaHlException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteException;
import ec.gov.iess.planillaspq.exceptions.PlanillaException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;
import ec.gov.iess.planillaspq.servicio.AjustePlanillaServicioLocal;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaPrestamoDetalleServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaServicioLocal;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * @author palvarez
 * 
 */
public class AjustePlanillaHlBacking extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "PlanillaServicioImpl/local")
	private PlanillaServicioLocal planillaD;

	@EJB(name = "PlanillaPrestamoDetalleServicioImpl/local")
	private PlanillaPrestamoDetalleServicioLocal pladetserD;

	@EJB(name = "AjustePlanillaServicioImpl/local")
	private AjustePlanillaServicioLocal ajuplaD;

	@EJB(name = "CambioEstadoServicioImpl/local")
	private CambioEstadoServicioLocal camestD;
	
	@EJB(name = "ComprobantePagoDaoPlaImpl/local")
	private ComprobantePagoPlaDao comprobantePagoPlaDao;

	private static final LoggerBiess log = LoggerBiess
			.getLogger(AjustePlanillaHlBacking.class);

	private Integer numregpladet;
	private BigDecimal valorpladet;
	private String observacion;
	private boolean listoajustar = false;
	private boolean plaaju = false;
	private String existeplanilla;
	private String ruc;
	private String suc;
	private Long anio;
	private Long mes;
	private String nroafi;
	private String erroranucom;
	private String errorajupla;
	private List<PlanillaPrestamosDetalle> pladet;
	private List<PlanillaPrestamosDetalle> pladeteli;
	private Planillas pla;
	private List<SelectItem> anios;
	private List<SelectItem> meses;
	private FuncionarioHandler funcionario;
	private boolean okOperacion = false;
	private boolean verPopUpGenerar = false;
	private int numRegistros = 0;
	
  	public AjustePlanillaHlBacking() {
  	}
	
	
	public void init (){
		numregpladet = null;
		valorpladet = null;
		observacion = null;
		listoajustar = false;
		plaaju = false;
		existeplanilla = null;
		ruc  = null;
		suc  = null;
		anio  = null;
		mes  = null;
		nroafi  = null;
		erroranucom  = null;
		errorajupla  = null;
		pladet  = null;
		pladeteli  = null;
		pla  = null;
		anios  = null;
		meses  = null;
		okOperacion = false;
		verPopUpGenerar = false;
	}

	public String EliminarAfiliados() {
		String[] afiliadosSeleccionados = getHttpServletRequest().getParameterValues("eliminados");		
		pladeteli = new ArrayList<PlanillaPrestamosDetalle>();
		if (afiliadosSeleccionados == null) {
			nroafi = "TIENE QUE SELECCIONAR AL MENOS UN AFILIADO";
			if (erroranucom != null)
				erroranucom = null;
			if (errorajupla != null)
				errorajupla = null;

		} else {
			try {
				/* Agregar afiliados seleccionados a un map */
				Map<String,Long> afiliadosSeleccionadosMap = addAfiSelToMap(afiliadosSeleccionados);
				/* Recorrer el detalle de la planilla para eliminar los afiliados seleccionados */
				
				// Inicio - Informacion auditoria (1) - Ajustar Planilla
				ParametroEvento parametroEvento = AuditoriaHelper.ajustarPlanilla();
				// Fin - Informacion auditoria (1)
				
				for (PlanillaPrestamosDetalle det : pladet) {
					/* Si el detalle pertenece a algun afiliado en el map, este es eliminado */
					String key = det.getNumafi() + "^" + det.getPk().getNumdiv();
					Long dividendo = afiliadosSeleccionadosMap.get(key); 
					if (null != dividendo && det.getPk().getNumdiv().equals(dividendo)) {
						pladeteli.add(det);
						numregpladet--;
						valorpladet = valorpladet.subtract(det.getValtotdiv());
						
						// Inicio - Informacion auditoria (2)
						ParametroEvento detalle = new ParametroEvento();
						detalle.setNombre("detalle");
						detalle.addVP("ruc", this.ruc).addVP("sucursal", this.suc).addVP("anio", String.valueOf(this.anio)).addVP("mes", String.valueOf(this.mes)).addVP("numafi", det.getNumafi())
								.addVP("numdiv", String.valueOf(det.getPk().getNumdiv())).addVP("secpla", String.valueOf(det.getPk().getSecpla()))
								.addVP("numpreafi", String.valueOf(det.getPk().getNumpreafi())).addVP("codprecla", String.valueOf(det.getPk().getCodprecla()))
								.addVP("codpretip", String.valueOf(det.getPk().getCodpretip())).addVP("ordpreafi", String.valueOf(det.getPk().getOrdpreafi())).addVP("codtippla", det.getPk().getCodtippla());
						
						parametroEvento.getDetalle().add(detalle);
						// Fin - Informacion auditoria (2)
					}
				}
				
				// Inicio - Informacion auditoria (3)
				super.guardarAuditoria(OperacionEnum.AJUSTAR_PLANILLA, parametroEvento, null);
				// Fin - Informacion auditoria (3)
				
				/* Remover elementos elimnados de la lista */
				pladet.removeAll(pladeteli);
			} catch (Exception e) {
				errorajupla = "ERROR: ELIMINANDO REGISTROS DE LA PLANILLA";
				e.printStackTrace();
			}
			listoajustar = true;
			if (erroranucom != null)
				erroranucom = null;
			if (errorajupla != null)
				errorajupla = null;
			if (nroafi != null)
				nroafi = null;
		}
		
		return null;
	}
	
	/**
	 * Metodo que crea un map de los registros que se quieren eliminar.
	 * 
	 * @param afiSel
	 * @return Map<String,Long>
	 */
	private Map<String,Long> addAfiSelToMap(String[] afiSel) {
		Map<String,Long> afiSelMap = new HashMap<String,Long>();
		for (int i = 0; i < afiSel.length; i++) {
			String[] afiseltmp = afiSel[i].split(","); 
			afiSelMap.put(afiseltmp[0].trim() + "^" + afiseltmp[1].trim(), new Long(afiseltmp[1].trim()));
		}
		
		return afiSelMap;
	}

	public boolean getOkOperacion() {
		return okOperacion;
	}

	public void setOkOperacion(boolean okOperacion) {
		this.okOperacion = okOperacion;
	}

	public String AjustarPlanilla() {
		listoajustar = false;
		plaaju = true;
		try {
			if (numRegistros == 1) {
				errorajupla = "ERROR: NO SU PUEDE AJUSTAR PARA UN SOLO REGISTRO, PROCEDA ANULAR LA PLANILLA";
				okOperacion = false;
				return null;
			}
			
			// Inicio Auditoria - Confirmar ajuste
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConfirmaAjusteAnulacionPlanilla(this.ruc, this.suc, String.valueOf(this.anio),
					String.valueOf(this.mes), this.pla.getPk().getCodtippla(), this.pla.getEsttippla(), String.valueOf(this.pla.getPk().getSecpla()));
			super.guardarAuditoria(OperacionEnum.CONFIRMAR_AJUSTE_PLANILLA, parametroEvento, String.valueOf(this.pla.getPk().getSecpla()));
			// Fin Auditoria
			
			ajuplaD.AjustarPlanilla(pla, pladeteli, observacion);
			errorajupla = "OK: LA PLANILLA SE AJUSTO CORRECTAMENTE";
			okOperacion = true;

		} catch (AjustaPlanillaHlException e) {
			errorajupla = "ERROR: NO SE PUDO AJUSTAR LA PLANILLA PORQUE " + e.getMessage();
			okOperacion = false;
			log.error("1. Error al realizar ajuste de planilla ", e);
		} catch (Exception e) {
			errorajupla = "ERROR: NO SE PUDO AJUSTAR LA PLANILLA ERROR INESPERADO";
			okOperacion = false;
			log.error("2. Error al realizar ajuste de planilla ", e);
		}

		return null;
	}

	public String AnularComprobante() {
		try {
			funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			log.info("Detalle de planilla cargado" + funcionario.getCedula());
			final String tipoComprobante = "DIVPRE";
			
			// Inicio Auditoria - Anular comprobante
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAnulaComprobante(this.ruc, this.suc, String.valueOf(this.anio),
					String.valueOf(this.mes), this.pla.getPk().getCodtippla(), this.pla.getEsttippla(), tipoComprobante,
					String.valueOf(this.pla.getPk().getSecpla()));
			super.guardarAuditoria(OperacionEnum.ANULAR_COMPROBANTE_AJUSTE_PLANILLA, parametroEvento, String.valueOf(this.pla.getPk().getSecpla()));
			// Fin Auditoria
			
			camestD.CambioEstadoComprobante(pla, observacion, tipoComprobante, funcionario.getCedula());
			erroranucom = "OK: EL COMPROBANTE SE ANULO CORRECTAMENTE";
			pla = null;
			okOperacion = true;

		} catch (CambioEstadoComprobanteException e) {
			erroranucom = "ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE " + e.getMessage();
			okOperacion = false;
			e.printStackTrace();
		} catch (Exception e) {
			erroranucom = "ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE OCURRIÓ UN ERROR INESPERADO";
			okOperacion = false;
			e.printStackTrace();
		}

		return null;
	}
	
	private boolean validarRucSuc() {
		boolean ok = true;
		if (ruc == null || ruc.length()!=13) {			
			existeplanilla = "INGRESO MAL EL RUC";
			ok  = false;
		}
		if (suc == null || suc.length()!=4) {
			existeplanilla = "INGRESO MAL LA SUCURSAL";
			ok  = false;
		}
		return ok;
	}

	public String ConsultarPlanilla() {
		nroafi = null;
		erroranucom = null;
		errorajupla = null;
		verPopUpGenerar = true;
		
		if (!validarRucSuc()) 
			return null;
		
		if (existeplanilla != null) {
			existeplanilla = null;
		}
		try {
			Planillas plani;
			plani = buscarPlanillas();
			if (plani.getEsttippla().equals("TGL")) {
				existeplanilla = "LA PLANILLA SE ENCUENTRA EN GLOSA, NO PUEDE MODIFICAR LA PLANILLA MIENTRAS SE ENCUENTRE EN ESE ESTADO";
				return null;
			}
			
			ComprobantePagoPla comprobante = comprobantePagoPlaDao.buscarComprobantePorPlanillaAjustePlanilla(plani);
			
			// Inicio Auditoria - Consultar
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultaAjusteAnulacionPlanilla(this.ruc, this.suc,
					String.valueOf(this.anio), String.valueOf(this.mes), plani.getPk().getCodtippla(), plani.getEsttippla(),
					String.valueOf(plani.getPk().getSecpla()), comprobante.getPk().getCodtipcompag());
			super.guardarAuditoria(OperacionEnum.CONSULTAR_AJUSTE_PLANILLA, parametroEvento, this.ruc);
			// Fin Auditoria
			
			pla = null;
			pladet = null;

		} catch (Exception e) {
			existeplanilla = "NO EXISTE LA PLANILLA PARA ESE RUC O SUCURSAL O PERIODO, O YA SE ENCUENTRA CANCELADA O ANULADA";
			e.printStackTrace();
			verPopUpGenerar = true;
			return null;
		}

		if (existeplanilla != null) {
			existeplanilla = null;
		}
		return "buscarplanilla";
	}

	public String Cancelar() {
		pla = null;
		pladet = null;
		listoajustar = false;
		return null;
	}

	public String Salir() {
		removeBackingBean("planillahl");
		return "principal";
	}

	public String BorrarBean() {
		removeBackingBean("planillahl");
		return null;
	}

	public String Reset() {

		ruc = "";
		suc = null;
		anio = null;
		mes = null;
		if (existeplanilla != null) {
			existeplanilla = null;
		}
		return null;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSuc() {
		return suc;
	}

	public void setSuc(String suc) {
		this.suc = suc;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public List<PlanillaPrestamosDetalle> getPladet() {
		if (pladet == null) {
			PlanillaPrestamosDetallePK pladetpk = new PlanillaPrestamosDetallePK();
			pladetpk.setAniper(anio);
			pladetpk.setCodsuc(suc);
			pladetpk.setCodtippla("P");
			pladetpk.setMesper(mes);
			pladetpk.setTipper("M");
			pladetpk.setRucemp(ruc);
			pladet = new ArrayList<PlanillaPrestamosDetalle>();
			pladet = pladetserD.buscarPorPlanilla(pladetpk);
			numRegistros= pladet.size();
		}
		return pladet;
	}

	public void setPladet(List<PlanillaPrestamosDetalle> pladet) {
		this.pladet = pladet;
	}

	public Planillas getPla() {
		if (pla == null) {
			pla = buscarPlanillas();
			if (pla != null) {
				numregpladet = pla.getNumdetpla();
				valorpladet = pla.getValnorpla();	
			}
			
		}
		return pla;
	}

	public Planillas buscarPlanillas() {
		Planillas planillas = new Planillas();
		PlanillasPK key = new PlanillasPK();
		key.setRucemp(ruc);
		key.setCodsuc(suc);
		key.setAniper(anio);
		key.setMesper(mes);
		key.setCodtippla("P");
		key.setTipper("M");
		try {
			planillas = planillaD.buscarPlanillaPorRuc(key).get(0);
			verPopUpGenerar = true;
			return planillas;
		} catch (PlanillaException e) {
			verPopUpGenerar = true;
			e.printStackTrace();
		} catch (Exception e) {
			verPopUpGenerar = true;
			e.printStackTrace();
		} 
		verPopUpGenerar = true;
		return null;
	}

	public void setPla(Planillas pla) {
		this.pla = pla;
	}

	public Integer getNumregpladet() {
		return numregpladet;
	}

	public void setNumregpladet(Integer numregpladet) {
		this.numregpladet = numregpladet;
	}

	public BigDecimal getValorpladet() {
		return valorpladet;
	}

	public void setValorpladet(BigDecimal valorpladet) {
		this.valorpladet = valorpladet;
	}

	public List<PlanillaPrestamosDetalle> getPladeteli() {
		return pladeteli;
	}

	public void setPladeteli(List<PlanillaPrestamosDetalle> pladeteli) {
		this.pladeteli = pladeteli;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isListoajustar() {
		return listoajustar;
	}

	public void setListoajustar(boolean listoajustar) {
		this.listoajustar = listoajustar;
	}

	public String getErroranucom() {
		return erroranucom;
	}

	public void setErroranucom(String erroranucom) {
		this.erroranucom = erroranucom;
	}

	public String getErrorajupla() {
		return errorajupla;
	}

	public void setErrorajupla(String errorajupla) {
		this.errorajupla = errorajupla;
	}

	public String getNroafi() {
		return nroafi;
	}

	public void setNroafi(String nroafi) {
		this.nroafi = nroafi;
	}

	public String getExisteplanilla() {
		return existeplanilla;
	}

	public void setExisteplanilla(String existeplanilla) {
		this.existeplanilla = existeplanilla;
	}

	public List<SelectItem> getAnios() {
		Calendar hoy = new GregorianCalendar();
		Long year = (long) (hoy.get(Calendar.YEAR));
		if (anios == null) {
			anios = new ArrayList<SelectItem>();
			anios.add(new SelectItem(year - 9, String.valueOf(year - 9)));
			anios.add(new SelectItem(year - 8, String.valueOf(year - 8)));
			anios.add(new SelectItem(year - 7, String.valueOf(year - 7)));
			anios.add(new SelectItem(year - 6, String.valueOf(year - 6)));
			anios.add(new SelectItem(year - 5, String.valueOf(year - 5)));
			anios.add(new SelectItem(year - 4, String.valueOf(year - 4)));
			anios.add(new SelectItem(year - 3, String.valueOf(year - 3)));
			anios.add(new SelectItem(year - 2, String.valueOf(year - 2)));
			anios.add(new SelectItem(year - 1, String.valueOf(year - 1)));
			anios.add(new SelectItem(year, String.valueOf(year)));
		}
		return anios;
	}

	public void setAnios(List<SelectItem> anios) {
		this.anios = anios;
	}

	public List<SelectItem> getMeses() {
		if (meses == null) {
			meses = new ArrayList<SelectItem>();
			meses.add(new SelectItem(new Long(1), "Enero"));
			meses.add(new SelectItem(new Long(2), "Febrero"));
			meses.add(new SelectItem(new Long(3), "Marzo"));
			meses.add(new SelectItem(new Long(4), "Abril"));
			meses.add(new SelectItem(new Long(5), "Mayo"));
			meses.add(new SelectItem(new Long(6), "Junio"));
			meses.add(new SelectItem(new Long(7), "Julio"));
			meses.add(new SelectItem(new Long(8), "Agosto"));
			meses.add(new SelectItem(new Long(9), "Septiembre"));
			meses.add(new SelectItem(new Long(10), "Octubre"));
			meses.add(new SelectItem(new Long(11), "Noviembre"));
			meses.add(new SelectItem(new Long(12), "Diciembre"));
		}
		return meses;
	}

	public void setMeses(List<SelectItem> meses) {
		this.meses = meses;
	}

	public boolean isPlaaju() {
		return plaaju;
	}

	public void setPlaaju(boolean plaaju) {
		this.plaaju = plaaju;
	}

	public FuncionarioHandler getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioHandler funcionario) {
		this.funcionario = funcionario;
	}

	public boolean getVerPopUpGenerar() {
		return verPopUpGenerar;
	}

	public void setVerPopUpGenerar(boolean verPopUpGenerar) {
		this.verPopUpGenerar = verPopUpGenerar;
	}

}
