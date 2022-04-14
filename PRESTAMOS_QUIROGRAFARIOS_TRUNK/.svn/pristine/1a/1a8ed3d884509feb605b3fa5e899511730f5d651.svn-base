package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import ec.fin.biess.auditoria.enumeraciones.OperacionEnum;
import ec.fin.biess.auditoria.util.ParametroEvento;
import ec.fin.biess.unlock.excepciones.UnlockException;
import ec.fin.biess.unlock.modelo.BloqueoUsuario;
import ec.fin.biess.unlock.modelo.BloqueoUsuarioHistorico;
import ec.fin.biess.unlock.servicio.DesbloqueoCuentaServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.planillaspq.web.helper.AuditoriaHelper;
import ec.gov.iess.planillaspq.web.util.BaseBean;

public class DesbloqueoCuentaBacking extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5476184671591566169L;
	private static final LoggerBiess log = LoggerBiess.getLogger(DesbloqueoCuentaBacking.class);

	@EJB(name = "DesbloqueoCuentaServicioImpl/local")
	private transient DesbloqueoCuentaServicio desbloqueoCuentaServicio;
	@EJB(name = "AfiliadoServicioImpl/local")
	private transient AfiliadoServicio afiliadoServicio;

	private String cedula;
	private BloqueoUsuario bloqueoUsuario;
	private Afiliado afiliado;
	private List<BloqueoUsuarioHistorico> listaBloqueoUsuarioHistorico;
	private String mensaje = null;
	private String okmensaje = null;

	/**
	 * 
	 */
	public String consultarEstadoCuenta() {
		try {
			if (cedula == null) {
				bloqueoUsuario = null;
				okmensaje = null;
				mensaje = "Favor ingrese el n\u00FAmero de cedula";
			} else if (cedula.isEmpty() || cedula.length() < 10) {
				bloqueoUsuario = null;
				okmensaje = null;
				mensaje = "El n\u00FAmero de cedula debe contener 10 d\u00EDgitos";

			} else {
				afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);
				if (afiliado != null) {
					
					// Inicio Auditoria
					ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosCedula(this.cedula);
					super.guardarAuditoria(OperacionEnum.CONSULTAR_DESBLOQUEO_CUENTA, parametroEvento, this.cedula);
					// Fin auditoria

					bloqueoUsuario = desbloqueoCuentaServicio.getBloqueoUsuario(cedula);

					if (bloqueoUsuario == null) {
						mensaje = null;
						okmensaje = "No hay informaci\u00F3n para desbloqueo por ventanilla.";
					} else {
						bloqueoUsuario.setObservacion("");
						okmensaje = null;
						mensaje = null;
					}

				} else {
					addInfoMessage("El Afiliado no existe!");
					log.info("AFILIADO NO EXISTE");
					bloqueoUsuario = null;
				}
				
			}
		} catch (AfiliadoException e) {
			bloqueoUsuario = null;
			afiliado = null;
			mensaje = "Afiliado no encontrado!";
			okmensaje = null;
			log.error("Afiliado no encontrado!", e);
		}
		return "";

	}

	/**
	 * 
	 */
	public void desbloquearEstadoCuenta() {
		/* Validar que observacion no este en blanco */
		if (null == bloqueoUsuario.getObservacion() || bloqueoUsuario.getObservacion().isEmpty()) {
			mensaje = "Observaci\u00F3n requerida";
			return;
		}
		/* Desbloquear cuenta */
		try {
			if (bloqueoUsuario != null) {
				// Inicio Auditoria
				ParametroEvento parametroEvento = AuditoriaHelper.obtenerParametrosDesbloquearCuenta(this.cedula, this.bloqueoUsuario.getObservacion());
				super.guardarAuditoria(OperacionEnum.DESBLOQUEAR_CUENTA, parametroEvento, this.cedula);
				// Fin Auditoria
				
				// bloqueoUsuario = desbloqueoCuentaServicio.getBloqueoUsuario(cedula);
				bloqueoUsuario.setIp(super.getHttpServletRequest().getRemoteAddr());
				bloqueoUsuario.setCedulaFuncionario(super.getHttpServletRequest().getRemoteUser());
				desbloqueoCuentaServicio.desbloquearCuenta(bloqueoUsuario);
				
				// Limpia Data
				bloqueoUsuario = null;
				okmensaje = "Cuenta desbloqueada satisfactoriamente!";
				mensaje = null;
				
			}
		} catch (UnlockException e) {
			log.debug("Error al desbloquear cuenta", e);
		}

	}

	/**
	 * @return
	 */
	public List<BloqueoUsuarioHistorico> getListaBloqueoUsuarioHistorico() {
		listaBloqueoUsuarioHistorico = new ArrayList<BloqueoUsuarioHistorico>();
		if (bloqueoUsuario != null) {
			listaBloqueoUsuarioHistorico = desbloqueoCuentaServicio.getListaBloqueoUsuarioHistorico(cedula);
		}
		return listaBloqueoUsuarioHistorico;

	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedula() {
		return cedula;
	}

	public BloqueoUsuario getBloqueoUsuario() {
		return bloqueoUsuario;
	}

	public void setBloqueoUsuario(BloqueoUsuario bloqueoUsuario) {
		this.bloqueoUsuario = bloqueoUsuario;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getOkmensaje() {
		return okmensaje;
	}

	public void setOkmensaje(String okmensaje) {
		this.okmensaje = okmensaje;
	}

}