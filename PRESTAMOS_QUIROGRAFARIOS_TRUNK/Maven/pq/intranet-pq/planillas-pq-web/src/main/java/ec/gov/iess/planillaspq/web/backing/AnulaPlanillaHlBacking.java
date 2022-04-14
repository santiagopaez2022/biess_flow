/**
 * 
 */
package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.dao.ComprobantePagoPlaDao;
import ec.gov.iess.planillaspq.exceptions.AnulaPlanillaHlException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteException;
import ec.gov.iess.planillaspq.exceptions.PlanillaException;
import ec.gov.iess.planillaspq.modelo.persistencia.ComprobantePagoPla;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaPrestamosDetalle;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaPrestamosDetallePK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;
import ec.gov.iess.planillaspq.servicio.AnulaPlanillaServicioLocal;
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
public class AnulaPlanillaHlBacking extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "PlanillaServicioImpl/local")
	private PlanillaServicioLocal planillaB;

	@EJB(name = "AnulaPlanillaServicioImpl/local")
	private AnulaPlanillaServicioLocal anulaPlanillaServicioLocalB;

	@EJB(name = "PlanillaPrestamoDetalleServicioImpl/local")
	private PlanillaPrestamoDetalleServicioLocal pladetserB;

	@EJB(name = "CambioEstadoServicioImpl/local")
	private CambioEstadoServicioLocal camestB;
	
	@EJB(name = "ComprobantePagoDaoPlaImpl/local")
	private ComprobantePagoPlaDao comprobantePagoPlaDao;

	private static final LoggerBiess log = LoggerBiess
			.getLogger(AnulaPlanillaHlBacking.class);

	private Integer numregpladet;
	private BigDecimal valorpladet;
	private String observacion;
	private boolean plaanu = false;
	private boolean comanu = false;
	private String existeplanilla;
	private String ruc;
	private String suc;
	private Long anio;
	private Long mes;
	private String erroranucom;
	private String nroafi;
	private String erroranupla;
	private List<PlanillaPrestamosDetalle> pladet;
	private List<PlanillaPrestamosDetalle> pladeteli;
	private Planillas pla;
	private List<SelectItem> anios;
	private List<SelectItem> meses;
	private FuncionarioHandler funcionario;
	private boolean okOperacion = false;
	private boolean verPopUpGenerar = false;
	

  	public AnulaPlanillaHlBacking() {
  	}
	

	public void init() {
		
		
		numregpladet = 0;
		valorpladet = new BigDecimal(0);
		observacion = null;
		plaanu = false;
		comanu = false;
		existeplanilla = null;
		ruc = null;
		suc = null;
		anio = 0l;
		mes = 0l;
		erroranucom = null;
		nroafi = null;
		erroranupla = null;
		pladet = new ArrayList<PlanillaPrestamosDetalle>();
		pladeteli = new ArrayList<PlanillaPrestamosDetalle>();
		pla = null;
		anios = null;
		meses = null;
		okOperacion = false;
		verPopUpGenerar = false;
	}

	public String AnularPlanilla() {
		plaanu = true;
		if (erroranucom != null)
			erroranucom = null;
		try {
			funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			log.info("Detalle de planilla cargado" + funcionario.getCedula());
			
			// Inicio Auditoria - Anular planilla
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConfirmaAjusteAnulacionPlanilla(this.ruc, this.suc, String.valueOf(this.anio),
					String.valueOf(this.mes), this.pla.getPk().getCodtippla(), this.pla.getEsttippla(), String.valueOf(this.pla.getPk().getSecpla()));
			super.guardarAuditoria(OperacionEnum.ANULAR_PLANILLA, parametroEvento, String.valueOf(this.pla.getPk().getSecpla()));
			// Fin Auditoria
			
			anulaPlanillaServicioLocalB.AnularPlanillaHl(pla, pladet, observacion, funcionario.getCedula());
			pla.setEsttippla("ANU");
			erroranupla = "OK: LA PLANILLA SE ANULO CORRECTAMENTE";
			okOperacion = true;

		} catch (AnulaPlanillaHlException e) {
			// TODO Auto-generated catch block
			erroranupla = "ERROR: NO SE PUDO ANULAR LA PLANILLA PORQUE " + e.getMessage();
			okOperacion = false;
			log.error("1. Error al anular planilla", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			erroranupla = "ERROR: NO SE PUDO ANULAR LA PLANILLA PORQUE " + e.getMessage();
			okOperacion = false;
			log.error("2. Error al anular planilla", e);
		}

		return null;
	}

	public String AnularComprobante() {
		comanu = true;
		try {
			funcionario = (FuncionarioHandler) getSession().getAttribute("funcionario");
			log.info("Detalle de planilla cargado" + funcionario.getCedula());
			final String tipoComprobante = "DIVPRE";
			
			// Inicio Auditoria - Anular comprobante
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosAnulaComprobante(this.ruc, this.suc, String.valueOf(this.anio),
					String.valueOf(this.mes), this.pla.getPk().getCodtippla(), this.pla.getEsttippla(), tipoComprobante,
					String.valueOf(this.pla.getPk().getSecpla()));
			super.guardarAuditoria(OperacionEnum.ANULAR_COMPROBANTE_PLANILLA, parametroEvento, String.valueOf(this.pla.getPk().getSecpla()));
			// Fin Auditoria
			
			camestB.CambioEstadoComprobante(pla, observacion, tipoComprobante, funcionario.getCedula());
			erroranucom = "OK: EL COMPROBANTE SE ANULO CORRECTAMENTE";
			pla = null;
			okOperacion = true;

		} catch (CambioEstadoComprobanteException e) {
			// TODO Auto-generated catch block
			erroranucom = "ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE " + e.getMessage();
			okOperacion = false;
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			erroranucom = "ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE HAY INCONSISTENCIAS ENTRE LA CABECERA E HISTORICO DE LA PLANILLA";
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
		erroranucom = null;
		erroranupla = null;
		plaanu = false;
		pla = null;
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
			pla = null;
			pladet = null;
			
			ComprobantePagoPla comprobante = comprobantePagoPlaDao.buscarComprobantePorPlanillaAjustePlanilla(plani);

			// Inicio Auditoria - Consultar
			ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosConsultaAjusteAnulacionPlanilla(this.ruc, this.suc,
					String.valueOf(this.anio), String.valueOf(this.mes), plani.getPk().getCodtippla(), plani.getEsttippla(),
					String.valueOf(plani.getPk().getSecpla()), comprobante.getPk().getCodtipcompag());
			super.guardarAuditoria(OperacionEnum.CONSULTAR_ANULACION_PLANILLA, parametroEvento, this.ruc);
			// Fin Auditoria
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			existeplanilla = "NO EXISTE LA PLANILLA PARA ESE RUC O SUCURSAL O PERIODO, O YA SE ENCUENTRA CANCELADA O ANULADA";
			e.printStackTrace();
			verPopUpGenerar = true;
			return null;
		}

		return "buscarplanillahl";
	}

	public String Salir() {
		removeBackingBean("anuplahl");
		return "principal";
	}

	public String Reset() {

		ruc = null;
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
			pladetpk.setSecpla(pla.getPk().getSecpla());
			pladet = new ArrayList<PlanillaPrestamosDetalle>();
			pladet = pladetserB.buscarPorPlanilla(pladetpk);
		}
		return pladet;
	}

	public void setPladet(List<PlanillaPrestamosDetalle> pladet) {
		this.pladet = pladet;
	}

	public Planillas getPla() {
		if (pla == null) {
			pla = buscarPlanillas();
			if (pla != null){
				numregpladet = pla.getNumdetpla();
				valorpladet = pla.getValnorpla();	
			}
			
		}
		return pla;
	}

	public Planillas buscarPlanillas() {
		Planillas planillas = null;
		PlanillasPK key = new PlanillasPK();
		key.setRucemp(ruc);
		key.setCodsuc(suc);
		key.setAniper(anio);
		key.setMesper(mes);
		key.setCodtippla("P");
		key.setTipper("M");
		try {
			planillas = planillaB.buscarPlanillaPorRuc(key).get(0);

			return planillas;
		} catch (PlanillaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public boolean isPlaanu() {
		return plaanu;
	}

	public void setPlaanu(boolean plaanu) {
		this.plaanu = plaanu;
	}

	public String getErroranupla() {
		return erroranupla;
	}

	public void setErroranupla(String erroranupla) {
		this.erroranupla = erroranupla;
	}

	public FuncionarioHandler getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioHandler funcionario) {
		this.funcionario = funcionario;
	}

	public String getErroranucom() {
		return erroranucom;
	}

	public void setErroranucom(String erroranucom) {
		this.erroranucom = erroranucom;
	}

	public boolean isComanu() {
		return comanu;
	}

	public void setComanu(boolean comanu) {
		this.comanu = comanu;
	}

	public boolean getOkOperacion() {
		return okOperacion;
	}

	public void setOkOperacion(boolean okOperacion) {
		this.okOperacion = okOperacion;
	}

	public boolean getVerPopUpGenerar() {
		return verPopUpGenerar;
	}

	public void setVerPopUpGenerar(boolean verPopUpGenerar) {
		this.verPopUpGenerar = verPopUpGenerar;
	}

}
