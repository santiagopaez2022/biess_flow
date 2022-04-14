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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.exceptions.AjustaPlanillaHostException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteException;
import ec.gov.iess.planillaspq.exceptions.PlanillaException;
import ec.gov.iess.planillaspq.exceptions.PlanillaHostException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;
import ec.gov.iess.planillaspq.servicio.AjustePlanillaServicioLocal;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaServicioLocal;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * @author palvarez
 * 
 */
public class AjustePlanillaHostBacking extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3978637372461514481L;

	@EJB(name = "PlanillaServicioImpl/local")
	private PlanillaServicioLocal planillaServicioC;

	@EJB(name = "AjustePlanillaServicioImpl/local")
	private AjustePlanillaServicioLocal ajuplaC;

	@EJB(name = "CambioEstadoServicioImpl/local")
	private CambioEstadoServicioLocal camestC;

	private static final LoggerBiess log = LoggerBiess
			.getLogger(AjustePlanillaHlBacking.class);

	private Integer numeroRegistrosPlanilla;
	private BigDecimal valorPlanilla;
	private String observacion;
	private boolean listoAjustar = false;
	private boolean planillaAjustada = false;
	private String existePlanilla;
	private String ruc;
	private String suc;
	private Long anio;
	private Long mes;
	private String seleccionarAfiliado;
	private String errorAnularComprobante;
	private String errorAjustarPlanilla;
	private List<PlanillaDetalleHost> planillaDetalle;
	private List<PlanillaDetalleHost> planillaDetalleEliminar;
	private Planillas planilla;
	private List<SelectItem> anios;
	private List<SelectItem> meses;
	private FuncionarioHandler funcionario;
	private boolean okOperacion = false;
	private boolean verPopUpGenerar = false;
	private int numRegistros = 0;

	public void init() {
		numeroRegistrosPlanilla = 0;
		valorPlanilla = new BigDecimal(0);
		observacion = null;
		listoAjustar = false;
		planillaAjustada = false;
		existePlanilla = null;
		ruc = "";
		suc = "";
		anio = 0l;
		mes = 0l;
		seleccionarAfiliado = null;
		errorAnularComprobante = null;
		errorAjustarPlanilla = null;
		planillaDetalle = new ArrayList<PlanillaDetalleHost>();
		planillaDetalleEliminar = new ArrayList<PlanillaDetalleHost>();
		planilla = null;
		anios = null;
		meses = null;
		okOperacion = false;
		verPopUpGenerar = false;
	}

	public String EliminarAfiliados() {
		String[] afiliadosSeleccionados = getHttpServletRequest()
				.getParameterValues("eliminados");
		planillaDetalleEliminar = new ArrayList<PlanillaDetalleHost>();
		if (afiliadosSeleccionados == null) {
			seleccionarAfiliado = "TIENE QUE SELECCIONAR AL MENOS UN AFILIADO";
			if (errorAnularComprobante != null)
				errorAnularComprobante = null;
			if (errorAjustarPlanilla != null)
				errorAjustarPlanilla = null;

		} else {
			for (int i = 0; i < afiliadosSeleccionados.length; i++) {
				for (PlanillaDetalleHost det : planillaDetalle) {
					if (det.getPk().getNumafi()
							.equals(afiliadosSeleccionados[i])) {
						planillaDetalleEliminar.add(det);
						planillaDetalle.remove(det);
						numeroRegistrosPlanilla--;
						valorPlanilla = valorPlanilla.subtract(det
								.getValtotdiv());
						break;
					}
				}
			}
			listoAjustar = true;
			if (errorAnularComprobante != null)
				errorAnularComprobante = null;
			if (errorAjustarPlanilla != null)
				errorAjustarPlanilla = null;
			if (seleccionarAfiliado != null)
				seleccionarAfiliado = null;

		}
		return null;
	}

	public String AjustarPlanilla() {
		errorAjustarPlanilla = "OK: LA PLANILLA SE AJUSTO CORRECTAMENTE";
		listoAjustar = false;
		planillaAjustada = true;
		okOperacion = true;
		try {
			if (numRegistros==1){
				errorAjustarPlanilla = "ERROR: NO SU PUEDE AJUSTAR PARA UN SOLO REGISTRO, PROCEDA ANULAR LA PLANILLA";
				okOperacion = false;
				return null;
			}
			ajuplaC.AjustarPlanillaHost(planilla, planillaDetalleEliminar,
					observacion);
		} catch (AjustaPlanillaHostException e) {
			// TODO Auto-generated catch block
			errorAjustarPlanilla = "ERROR: NO SE PUDO AJUSTAR LA PLANILLA PORQUE "
					+ e.getMessage();
			okOperacion = false;
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorAjustarPlanilla = "ERROR: NO SE PUDO AJUSTAR LA PLANILLA ERROR INESPERADO";
			okOperacion = false;
			e.printStackTrace();
		}
		return null;
	}

	public String AnularComprobante() {
		errorAnularComprobante = "OK: EL COMPROBANTE SE ANULO CORRECTAMENTE";
		okOperacion = true;
		try {
			funcionario = (FuncionarioHandler) getSession().getAttribute(
					"funcionario");
			log.info("Detalle de planillaServicio cargado"
					+ funcionario.getCedula());
			camestC.CambioEstadoComprobante(planilla, observacion, "DIVPREHO",
					funcionario.getCedula());
			planilla = null;
		} catch (CambioEstadoComprobanteException e) {
			// TODO Auto-generated catch block
			errorAnularComprobante = "ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE "
					+ e.getMessage();
			okOperacion = false;
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorAnularComprobante = "ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE HAY INCONSISTENCIAS ENTRE LA CABECERA E HISTORICO DE LA PLANILLA";
			okOperacion = false;
			e.printStackTrace();
		}
		return null;
	}
	private boolean validarRucSuc() {
		boolean ok = true;
		if (ruc == null || ruc.length()!=13) {			
			existePlanilla = "INGRESO MAL EL RUC";
			ok  = false;
		}
		if (suc == null || suc.length()!=4) {
			existePlanilla = "INGRESO MAL LA SUCURSAL";
			ok  = false;
		}
		return ok;
	}

	public String ConsultarPlanilla() {
		seleccionarAfiliado = null;
		errorAnularComprobante = null;
		errorAjustarPlanilla = null;
		verPopUpGenerar = true;
		if (!validarRucSuc()) 
			return null;
		
		
		planilla = null;
		if (existePlanilla != null) {
			existePlanilla = null;
		}
		try {
			Planillas plani;
			plani = buscarPlanillas();
			if (plani.getEsttippla().equals("TGL")) {
				existePlanilla = "LA PLANILLA SE ENCUENTRA EN GLOSA, NO PUEDE MODIFICAR LA PLANILLA MIENTRAS SE ENCUENTRE EN ESE ESTADO";
				return null;
			}
			planilla = null;
			planillaDetalle = null;
		} catch (Exception e) {
			existePlanilla = "NO EXISTE LA PLANILLA PARA ESE RUC O SUCURSAL O PERIODO, O YA SE ENCUENTRA CANCELADA O ANULADA";
			verPopUpGenerar = true;
			e.printStackTrace();
			return null;
		}
		if (existePlanilla != null) {
			existePlanilla = null;
		}
		return "buscarplanillahost";

	}

	public String Cancelar() {
		planilla = null;
		planillaDetalle = null;
		listoAjustar = false;
		return null;
	}

	public String Salir() {
		removeBackingBean("planillahost");
		return "principal";
	}

	public String Reset() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("planillahost");
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("AjustePlanillaHostBacking");
		/*
		 * ruc=null; suc=null; anio=null; mes=null;
		 */
		if (existePlanilla != null) {
			existePlanilla = null;
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

	public List<PlanillaDetalleHost> getplanillaDetalle() {
		
			PlanillaDetalleHostPK planillaDetallepk = new PlanillaDetalleHostPK();
			planillaDetallepk.setAniper(anio);
			planillaDetallepk.setCodsuc(suc);
			planillaDetallepk.setCodtippla("PH");
			planillaDetallepk.setMesper(mes);
			planillaDetallepk.setTipper("M");
			planillaDetallepk.setRucemp(ruc);
			if (planilla == null) {
				return new ArrayList<PlanillaDetalleHost>();
			}
				
			planillaDetallepk.setSecpla(planilla.getPk().getSecpla());
			planillaDetalle = new ArrayList<PlanillaDetalleHost>();
			try {
				planillaDetalle = planillaServicioC
						.buscarPorPlanilla(planillaDetallepk);
			} catch (PlanillaHostException e) {
				e.printStackTrace();
				planillaDetalle = new ArrayList<PlanillaDetalleHost>(); 
			}

		
		return planillaDetalle;
	}

	public void setplanillaDetalle(List<PlanillaDetalleHost> planillaDetalle) {
		this.planillaDetalle = planillaDetalle;
	}

	public Planillas getplanilla() {
		if (planilla == null) {
			planilla = buscarPlanillas();
			if (planilla!=null) {
				numeroRegistrosPlanilla = planilla.getNumdetpla();
				valorPlanilla = planilla.getValnorpla();
			}
			
		}
		return planilla;
	}

	public Planillas buscarPlanillas() {
		Planillas planillas = new Planillas();
		PlanillasPK key = new PlanillasPK();
		key.setRucemp(ruc);
		key.setCodsuc(suc);
		key.setAniper(anio);
		key.setMesper(mes);
		key.setCodtippla("PH");
		key.setTipper("M");
		try {
			planillas = planillaServicioC.buscarPlanillaPorRuc(key).get(0);
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

	public void setplanilla(Planillas planilla) {
		this.planilla = planilla;
	}

	public Integer getnumeroRegistrosPlanilla() {
		return numeroRegistrosPlanilla;
	}

	public void setnumeroRegistrosPlanilla(Integer numeroRegistrosPlanilla) {
		this.numeroRegistrosPlanilla = numeroRegistrosPlanilla;
	}

	public BigDecimal getvalorPlanilla() {
		return valorPlanilla;
	}

	public void setvalorPlanilla(BigDecimal valorPlanilla) {
		this.valorPlanilla = valorPlanilla;
	}

	public List<PlanillaDetalleHost> getplanillaDetalleeli() {
		return planillaDetalleEliminar;
	}

	public void setplanillaDetalleeli(
			List<PlanillaDetalleHost> planillaDetalleEliminar) {
		this.planillaDetalleEliminar = planillaDetalleEliminar;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean islistoAjustar() {
		return listoAjustar;
	}

	public void setlistoAjustar(boolean listoAjustar) {
		this.listoAjustar = listoAjustar;
	}

	public String geterrorAnularComprobante() {
		return errorAnularComprobante;
	}

	public void seterrorAnularComprobante(String errorAnularComprobante) {
		this.errorAnularComprobante = errorAnularComprobante;
	}

	public String geterrorAjustarPlanilla() {
		return errorAjustarPlanilla;
	}

	public void seterrorAjustarPlanilla(String errorAjustarPlanilla) {
		this.errorAjustarPlanilla = errorAjustarPlanilla;
	}

	public String getSeleccionarAfiliado() {
		return seleccionarAfiliado;
	}

	public void setSeleccionarAfiliado(String seleccionarAfiliado) {
		this.seleccionarAfiliado = seleccionarAfiliado;
	}

	public String getExisteplanilla() {
		return existePlanilla;
	}

	public void setExisteplanilla(String existePlanilla) {
		this.existePlanilla = existePlanilla;
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

	public boolean isplanillaAjustada() {
		return planillaAjustada;
	}

	public void setplanillaAjustada(boolean planillaAjustada) {
		this.planillaAjustada = planillaAjustada;
	}

	public FuncionarioHandler getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioHandler funcionario) {
		this.funcionario = funcionario;
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
