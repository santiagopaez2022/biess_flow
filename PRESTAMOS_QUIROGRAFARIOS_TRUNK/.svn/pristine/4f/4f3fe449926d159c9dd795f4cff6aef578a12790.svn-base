package ec.gov.iess.pq.concesion.web.backing;

import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;


import ec.fin.biess.alerts.enumeracion.AlertType;
import ec.fin.biess.alerts.helper.AlertUserHelper;
import ec.fin.biess.alerts.modelo.InformacionAfiliado;
import ec.fin.biess.creditos.pq.alertas.util.AlertUtil;
import ec.fin.biess.creditos.pq.excepcion.EmailException;
import ec.fin.biess.creditos.pq.modelo.persistencia.DatosPersonalesAfiliadoBiess;
import ec.fin.biess.creditos.pq.servicio.AfiliadoServicioBiess;
import ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio;
import ec.fin.biess.unlock.dao.BloqueoUsuarioDao;
import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.pq.concesion.web.util.BaseBean;

public class ActualizarDatosPersonalesBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient static final LoggerBiess log = LoggerBiess.getLogger(ActualizarDatosPersonalesBean.class);

	/* Servicio para actualizar datos del afiliado */
	@EJB(name = "AfiliadoServicioBiessImpl/local")
	private AfiliadoServicioBiess afiliadoServicioBiess;

	@EJB(name = "BloqueoUsuarioDaoImpl/local")
	BloqueoUsuarioDao bloqueoUsuarioDao;

	@EJB(name = "DatosPersonalesAfiliadoBiessServicioImpl/local")
	DatosPersonalesAfiliadoBiessServicio dpAfiliadoBiessServicio;

	@EJB(name = "AfiliadoServicioImpl/local")
	AfiliadoServicio afiliadoServicio;

	@EJB(name = "AlertUserHelperImpl/local")
	private AlertUserHelper alertUserHelper;	

	private String celular;

	private String email;

	/**
	 * Metodo que genera URL de debloqueo y envia e-mail al usuario.
	 * 
	 * @throws UnlockException
	 */
	public void updatePersonalData() {
		try {
			/* validar session */
			if (!getRequest().isRequestedSessionIdValid() || getRequest().isRequestedSessionIdFromURL()) {
				throw new Exception("Sesion invalida.");
			}
			/* Actualizar datos afiliado tabla iess */
			String cedula = getRequest().getRemoteUser();
			Afiliado afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);
			/* Mantener antiguo e-mail */
			String oldEmail = null != afiliado.getMaiafi() ? new String(afiliado.getMaiafi()) : null;
			afiliado.setMaiafi(cleanString(email));
			afiliado.setCelafi(celular);
			//afiliado.setFecactafi(new Date());
			try {
				afiliadoServicioBiess.reActualizarDatosAfiliado(afiliado, super.getIpUser());
			} catch (RuntimeException e) {
				Throwable causeException = e.getCause().getCause().getCause().getCause();
				if (causeException instanceof BatchUpdateException) {
					BatchUpdateException bue = (BatchUpdateException) causeException;
					if (bue.getErrorCode() == 20500) {
						throw new EmailException("La direcci\u00F3n de e-mail no es v\u00E1lida.");
					}
				}
			}
			/* Enviar notificacion de actualizacion de datos */
			enviarAlertaActualizacionDatos(oldEmail, afiliado);
			/* Actualizar datos afiliado tabla biess */			
			/*DatosPersonalesAfiliadoBiess afiliadoBiess;
			try {
				afiliadoBiess = dpAfiliadoBiessServicio.consultarPorCedula(cedula);
			} catch (Exception e) {
				afiliadoBiess = null;
			}
			if (null != afiliadoBiess) {
				afiliadoBiess.setEmail(cleanString(email));
				afiliadoBiess.setCelular(celular);
				afiliadoBiess.setUltimaFecha(new Date());
				dpAfiliadoBiessServicio.actualizar(afiliadoBiess);
			} else {
				createAfiliadoBiess(afiliado);
			}*/
			/* Actualizar indicador de datos actualizados en tabla de bloqueos */
			BloqueoUsuario blkusr = bloqueoUsuarioDao.getBloqueoUsuarioPorId(getRequest().getRemoteUser());
			blkusr.setIndicadorActualizacionDatos("S");
			blkusr.setFechaActualizacionDatos(new Date());
			bloqueoUsuarioDao.actualizar(blkusr);
			/* Redireccionar a pagina de seleccion de roles */
			getResponse().sendRedirect(getContextPath() + "/pages/concesion/roles.jsf");
			resetLoginSessionVariables();
		} catch (EmailException e) {
			log.error(e.getMessage(), e);
			errorMessage = e.getMessage();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errorMessage = "Error al actualizar datos.";
		}
	}

	/**
	 * Metodo que crea nueva instancia de datos personales en table biess.
	 * 
	 * @param afiIess
	 *            DatosPersonalesAfiliadoBiess
	 * @return
	 */
	private void createAfiliadoBiess(Afiliado afiIess) {
		DatosPersonalesAfiliadoBiess afiBiess = new DatosPersonalesAfiliadoBiess();
		afiBiess.setCedula(afiIess.getCedulaAfiliado());
		afiBiess.setNombre(afiIess.getApenomafi());
		afiBiess.setDireccion(afiIess.getDirafi());
		afiBiess.setTelefono(afiIess.getTelafi());
		afiBiess.setNacionalidad(afiIess.getNacionalidad().getDesnac());
		afiBiess.setFechanacimiento(afiIess.getFecnacafi());
		afiBiess.setEstadoCivil(afiIess.getEstadoCivil().getDescripcion());
		DivisionPolitica parroquia = afiIess.getDivisionPolitica();
		DivisionPolitica canton = null == parroquia ? null : parroquia.getDivisionPolitica();
		DivisionPolitica provincia = null == canton ? null : canton.getDivisionPolitica();
		afiBiess.setParroquia(parroquia.getCoddivpol());
		afiBiess.setCanton(canton.getCoddivpol());
		afiBiess.setProvincia(provincia.getCoddivpol());
		String posviv = afiIess.getPosviv();
		if (null != posviv) {
			posviv = posviv.compareTo("1") == 0 ? "SI" : "NO";
		}
		afiBiess.setpVivienda(posviv);
		afiBiess.setUltimaFecha(afiIess.getFecactafi());
		afiBiess.setEmail(afiIess.getMaiafi());
		afiBiess.setCelular(celular);
		afiBiess.setUltimaFecha(new Date());
		dpAfiliadoBiessServicio.nuevo(afiBiess);
	}

	/**
	 * Metodo que limpia las variables de session.
	 */
	private void resetLoginSessionVariables() {
		getSession().removeAttribute("detadpuatad");
		getSession().removeAttribute("galfdetadpuatad");
	}

	/**
	 * Metodo que elimina espacios de un string.
	 * 
	 * @param str
	 * @return String
	 */
	private String cleanString (String str) {
		return str.trim().replaceAll(" ", "");
	}
	
	/**
	 * Metodo que envia una alerta al usuario cuando se ha generado una liquidacion de credito.
	 * 
	 * @param oldEmail
	 * @param afiliado
	 */
	private void enviarAlertaActualizacionDatos(String oldEmail, Afiliado afiliado) {
		try {
			String templatePath = "ec/fin/biess/creditos/pq/alertas/templates/email/actdatos.ftl";                    
	        /* Parametros del mensaje */
	        Map<String, Object> alertData = new HashMap<String, Object>();
	        alertData.put("msg_usuario", afiliado.getApenomafi());
	        alertData.put("msg_fecha", AlertUtil.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss"));
        	/* Fijar informacion para envio de e-mail */
	        InformacionAfiliado dp = new InformacionAfiliado();
        	dp.setCedula(afiliado.getCedulaAfiliado());	        	
	        /* Se envia alerta al antiguo e-mail si ha sido actualizado */
	        if (null != oldEmail && !oldEmail.isEmpty() && oldEmail.compareToIgnoreCase(afiliado.getMaiafi()) != 0) {
	        	dp.setEmail(oldEmail);	        	
	        	AlertUtil.enviarAlerta(alertUserHelper, dp, templatePath, alertData, null, AlertType.EMAIL);
	        }
	        /* Se envia actualizacion al nuevo e-mail */
        	dp.setEmail(afiliado.getMaiafi());
	        AlertUtil.enviarAlerta(alertUserHelper, dp, templatePath, alertData, null, AlertType.EMAIL);
		} catch (Exception e) {
			log.error("Error al enviar alerta de actualizacion de datos al afiliado: " + afiliado.getCedulaAfiliado(), e);
		}
	}		

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelular() {
		return celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
