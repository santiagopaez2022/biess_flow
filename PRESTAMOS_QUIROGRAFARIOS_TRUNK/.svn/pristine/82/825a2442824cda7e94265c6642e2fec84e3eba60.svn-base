package ec.gov.iess.planillaspq.web.handler;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.servicio.BusquedasServicioLocal;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;






/**
 * Bean para verificar los roles de un funcionario
 * 
 * @author Daniel Cardenas
 * @author William Valencia
 * 
 */
public class FuncionarioHandler extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Log4j
	private static final LoggerBiess log = LoggerBiess.getLogger(FuncionarioHandler.class);
	
	@EJB(name = "BusquedasServicioImpl/local")
	private  BusquedasServicioLocal busquedaServicio;
	
	private String cedula;
	private String nombreCompleto;

	/**
	 * Constructor vacio por defecto
	 */
	public FuncionarioHandler() {
		super();
	}
	
	/**
	 * Metodo que se ejecuta despues del constructor
	 */
	@PostConstruct
	private void init() {
		this.guardarAuditoria();
	}
	
	/**
	 * Guarda la auditoria de login
	 */
	private void guardarAuditoria() {
		ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosVacio();
		super.guardarAuditoria(OperacionEnum.LOGIN_PQ_INTRANET, parametroEvento, super.getRemoteCI());
	}

	public String getCedula() {
		cedula = super.getHttpServletRequest().getRemoteUser();
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * <b>
	 * Obtiene el nombre del funcionario logeado desde la tabla de afiliados
	 * </b>
	 * <p>[Author: Gabriel Eguiguren, Date: 23/05/2011]</p>
	 *
	 * @return nombre completo del afiliado
	 */ 
	public String getNombreCompleto() {
		try {
			 if (getRequest().getRemoteUser() != null && nombreCompleto == null){
				 nombreCompleto = busquedaServicio.buscarAfiliadoPorId(getRequest().getRemoteUser());
			 }
		} catch (Exception e) {
			log.error("Error al Obtener Nombre del Funcionario: ",e);
		}
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreFuncionario) {
		this.nombreCompleto = nombreFuncionario;
	}

}