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


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.exceptions.AnulaPlanillaHostException;
import ec.gov.iess.planillaspq.exceptions.CambioEstadoComprobanteException;
import ec.gov.iess.planillaspq.exceptions.PlanillaException;
import ec.gov.iess.planillaspq.exceptions.PlanillaHostException;
import ec.gov.iess.planillaspq.modelo.persistencia.PlanillaDetalleHost;
import ec.gov.iess.planillaspq.modelo.persistencia.Planillas;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillaDetalleHostPK;
import ec.gov.iess.planillaspq.modelo.persistencia.pk.PlanillasPK;
import ec.gov.iess.planillaspq.servicio.AnulaPlanillaServicioLocal;
import ec.gov.iess.planillaspq.servicio.AnulaPlanillaServicioRemote;
import ec.gov.iess.planillaspq.servicio.CambioEstadoServicioLocal;
import ec.gov.iess.planillaspq.servicio.PlanillaServicioLocal;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.util.BaseBean;

/**
 * @author palvarez
 *
 */
public class AnulaPlanillaHostBacking extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name="PlanillaServicioImpl/local")
	private PlanillaServicioLocal planillaServicioLocalA;		

	@EJB(name="CambioEstadoServicioImpl/local")
	private CambioEstadoServicioLocal cambioEstadoServicioLocalA;
	
	@EJB(name="AnulaPlanillaServicioImpl/remote")
	private AnulaPlanillaServicioRemote anulaPlanilla;
	
	private static final LoggerBiess log = LoggerBiess.getLogger(AjustePlanillaHlBacking.class);

	private Integer numregpladet;
	private BigDecimal valorpladet;
	private String observacion;
	private boolean plaanu=false;
	private boolean comanu=false;
	private String existeplanilla;
	private String ruc;
	private String suc;
	private Long anio;
	private Long mes;
	private String nroafi;
	private String erroranupla;
	private String erroranucom;
	private List<PlanillaDetalleHost> pladet;
	private List<PlanillaDetalleHost> pladeteli;
	private Planillas pla;
	private List<SelectItem> anios;
	private List<SelectItem> meses;
	private FuncionarioHandler funcionario;	
	private boolean okOperacion = false;
	private boolean	verPopUpGenerar = false;
	
	public void init(){
		numregpladet = null;
		valorpladet = null;
		observacion = null;
		plaanu=false;
		comanu=false;
		existeplanilla = null;
		ruc = null;
		suc = null;
		anio = null;
		mes = null;
		nroafi = null;
		erroranupla = null;
		
		pladet = null;
		pladeteli = null;
		pla = null;
		anios = null;
		meses = null;	
		okOperacion = false;
		verPopUpGenerar = false;
	}

	public String AnularPlanilla(){
		plaanu=true;
		if (erroranucom!=null)	
			erroranucom=null;
		try {
			funcionario=(FuncionarioHandler)getSession().getAttribute("funcionario");
			log.info("Detalle de planilla cargado"+funcionario.getCedula());
			anulaPlanilla.AnularPlanillaHost(pla, observacion,funcionario.getCedula());
			pla.setEsttippla("ANU");
			erroranupla="OK: LA PLANILLA SE ANULO CORRECTAMENTE";
			okOperacion = true;
		} catch (AnulaPlanillaHostException e) {
			// TODO Auto-generated catch block
			erroranupla="ERROR: NO SE PUDO ANULAR LA PLANILLA PORQUE "+e.getMessage();
			okOperacion = false;
			e.printStackTrace();
		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String AnularComprobante(){
		comanu=true;
		try {
			funcionario=(FuncionarioHandler)getSession().getAttribute("funcionario");
			log.info("Detalle de planilla cargado"+funcionario.getCedula());
			cambioEstadoServicioLocalA.CambioEstadoComprobante(pla, observacion,"DIVPREHO",funcionario.getCedula());
			erroranucom="OK: EL COMPROBANTE SE ANULO CORRECTAMENTE";
			okOperacion = true;
			pla=null;
		} catch (CambioEstadoComprobanteException e) {
			// TODO Auto-generated catch block
			erroranucom="ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE "+e.getMessage();
			okOperacion = false;
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			erroranucom="ERROR: NO SE PUDO ANULAR EL COMPROBANTE PORQUE HAY INCONSISTENCIAS ENTRE LA CABECERA E HISTORICO DE LA PLANILLA";
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
	
	public String ConsultarPlanilla(){
		verPopUpGenerar = true;
		erroranupla = null;
		erroranucom = null;
		plaanu = false;		
		
		if (!validarRucSuc()) 
			return null;
		
		if (existeplanilla!=null){
			existeplanilla=null;
		}		
		try {	
			Planillas plani;
			plani = buscarPlanillas();
			if (plani.getEsttippla().equals("TGL")){			
				existeplanilla="LA PLANILLA SE ENCUENTRA EN GLOSA, NO PUEDE MODIFICAR LA PLANILLA MIENTRAS SE ENCUENTRE EN ESE ESTADO";
				return null;
			}
			pla=null;
			pladet=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			existeplanilla="NO EXISTE LA PLANILLA PARA ESE RUC O SUCURSAL O PERIODO, O YA SE ENCUENTRA CANCELADA O ANULADA";
			e.printStackTrace();
			verPopUpGenerar = true;
			return null;
		}
		return "buscarplanillahost";		
	}
	
	public String Salir(){
		removeBackingBean("anuplahost");
		return "principal";
	}
	
	public String Reset(){

		ruc=null;
		suc=null;
		anio=null;
		mes=null;
		if (existeplanilla!=null){
			existeplanilla=null;
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
	
	public List<PlanillaDetalleHost> getPladet() {
		if (pladet==null){
			PlanillaDetalleHostPK pladetpk=new PlanillaDetalleHostPK();
				pladetpk.setAniper(anio);
				pladetpk.setCodsuc(suc);
				pladetpk.setCodtippla("PH");
				pladetpk.setMesper(mes);
				pladetpk.setTipper("M");
				pladetpk.setRucemp(ruc);
				pladetpk.setSecpla(pla.getPk().getSecpla());
				pladet=new ArrayList<PlanillaDetalleHost>();
				try{	
					pladet=planillaServicioLocalA.buscarPorPlanilla(pladetpk);	
				} catch (PlanillaHostException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en la consulta al detalle de la planilla del Host");				
					e.printStackTrace();
				}

		}	
		return pladet;
	}
	public void setPladet(List<PlanillaDetalleHost> pladet) {
		this.pladet = pladet;
	}
	public Planillas getPla() {
		if (pla==null){
				pla = buscarPlanillas();
				if (pla != null) {
					numregpladet=pla.getNumdetpla();
					valorpladet=pla.getValnorpla();
				}
		}	
		return pla;
	}
	public Planillas buscarPlanillas(){
			PlanillasPK key=new PlanillasPK();
				key.setRucemp(ruc);
				key.setCodsuc(suc);
				key.setAniper(anio);
				key.setMesper(mes);
				key.setCodtippla("PH");
				key.setTipper("M");
				try {
					return planillaServicioLocalA.buscarPlanillaPorRuc(key).get(0);
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
	public List<PlanillaDetalleHost> getPladeteli() {
		return pladeteli;
	}
	public void setPladeteli(List<PlanillaDetalleHost> pladeteli) {
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
		if (anios==null){
			anios=new ArrayList<SelectItem>();
			anios.add(new SelectItem(year-9,String.valueOf(year-9)));
			anios.add(new SelectItem(year-8,String.valueOf(year-8)));
			anios.add(new SelectItem(year-7,String.valueOf(year-7)));
			anios.add(new SelectItem(year-6,String.valueOf(year-6)));
			anios.add(new SelectItem(year-5,String.valueOf(year-5)));
			anios.add(new SelectItem(year-4,String.valueOf(year-4)));
			anios.add(new SelectItem(year-3,String.valueOf(year-3)));
			anios.add(new SelectItem(year-2,String.valueOf(year-2)));
			anios.add(new SelectItem(year-1,String.valueOf(year-1)));
			anios.add(new SelectItem(year,String.valueOf(year)));
		}
		return anios;
	}

	public void setAnios(List<SelectItem> anios) {
		this.anios = anios;
	}

	public List<SelectItem> getMeses() {
		if (meses==null){
			meses=new ArrayList<SelectItem>();
			meses.add(new SelectItem(new Long(1),"Enero"));
			meses.add(new SelectItem(new Long(2),"Febrero"));
			meses.add(new SelectItem(new Long(3),"Marzo"));
			meses.add(new SelectItem(new Long(4),"Abril"));
			meses.add(new SelectItem(new Long(5),"Mayo"));
			meses.add(new SelectItem(new Long(6),"Junio"));
			meses.add(new SelectItem(new Long(7),"Julio"));
			meses.add(new SelectItem(new Long(8),"Agosto"));
			meses.add(new SelectItem(new Long(9),"Septiembre"));
			meses.add(new SelectItem(new Long(10),"Octubre"));
			meses.add(new SelectItem(new Long(11),"Noviembre"));
			meses.add(new SelectItem(new Long(12),"Diciembre"));
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

	public boolean isComanu() {
		return comanu;
	}

	public void setComanu(boolean comanu) {
		this.comanu = comanu;
	}
	public String getErroranucom() {
		return erroranucom;
	}
	public void setErroranucom(String erroranucom) {
		this.erroranucom = erroranucom;
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
