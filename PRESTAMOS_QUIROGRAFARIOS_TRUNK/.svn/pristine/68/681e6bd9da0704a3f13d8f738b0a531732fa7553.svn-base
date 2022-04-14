package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.web.enumeration.EstadoParametrizacionPQEnum;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.util.BaseBean;
import ec.gov.iess.planillaspq.web.util.DireccionIPUtil;

/**
 * Bean para aprobar o rechazar los parametros de pq
 * 
 * @author hugo.mora
 *
 */
public class AprobacionParametrizacionBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 8040670274206333088L;

	private static final LoggerBiess log = LoggerBiess.getLogger(AprobacionParametrizacionBacking.class);

	@EJB(name = "ParametrizacionPQServicioImpl/local")
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;

	private ParametrizacionPQ parametrizacionPQSeleccionado;

	private List<ParametrizacionPQ> listaParametrizacionPQ = new ArrayList<ParametrizacionPQ>();

	private Long codParametro;

	private FuncionarioHandler funcionarioHandler;

	@PostConstruct
	public void init() {
		try {
			obtenerListaDatos();
		} catch (Exception e) {
			log.error("Error al obtener el listado de parametrizacion pendiente de aprobación: " + e.getMessage());
		}
	}

	/**
	 * Carga la lista con parametros pendientes de aprobacion
	 */
	private void obtenerListaDatos() {
		List<String> listaEstados = new ArrayList<String>();
		listaEstados.add(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion());
		listaEstados.add(EstadoParametrizacionPQEnum.PENDIENTE_ELIMINACION.getOpcion());
		listaParametrizacionPQ = parametrizacionPQServicio.obtenerListaParametrizacionPQPorListaEstados(listaEstados);
	}

	/**
	 * Setea a null la variable parametrizacionPQSeleccionado
	 * 
	 * @return
	 */
	public String resetearSeleccionado() {
		parametrizacionPQSeleccionado = null;
		return "";
	}

	/**
	 * Obtiene el parametro seleccionado
	 * 
	 * @param event
	 */
	public void seleccionarParametro(ActionEvent event) {
		parametrizacionPQSeleccionado = parametrizacionPQServicio.buscarPorId(codParametro);
	}

	/**
	 * Cambia a estado rechazado (R) el parametro seleccionado
	 * 
	 * @return
	 */
	public String rechazar() {
		try {
			if (parametrizacionPQSeleccionado.getFuncionarioIngresa().equals(getRequest().getRemoteUser())) {
				addGlobalErrorMessage(
						"El funcionario que realiz\u00F3 el ingreso del par\u00E1metro no puede ser la misma persona que realiza el rechazo", "");
			} else {
				parametrizacionPQSeleccionado.setFechaInicio(new Date());
				if (parametrizacionPQSeleccionado.getEstado().equals(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion())) {
					parametrizacionPQSeleccionado.setEstado(EstadoParametrizacionPQEnum.RECHAZADO.getOpcion());
				} else if (parametrizacionPQSeleccionado.getEstado().equals(EstadoParametrizacionPQEnum.PENDIENTE_ELIMINACION.getOpcion())) {
					parametrizacionPQSeleccionado.setEstado(EstadoParametrizacionPQEnum.APROBADO.getOpcion());
				}
				parametrizacionPQSeleccionado.setFuncionarioAprueba(getRequest().getRemoteUser());
				parametrizacionPQSeleccionado.setFecHorAprueba(new Date());
				parametrizacionPQSeleccionado.setDirIpAprueba(DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()));
				parametrizacionPQSeleccionado.setSistemaIngreso("PQ Intranet");
				parametrizacionPQSeleccionado.setFuncionarioApruebaNombre(funcionarioHandler.getNombreCompleto());

				parametrizacionPQServicio.ingresar(parametrizacionPQSeleccionado);
				obtenerListaDatos();
				addGlobalInfoMessage("Registro rechazado con exito", "");
				parametrizacionPQSeleccionado = new ParametrizacionPQ();
			}

		} catch (Exception e) {
			addGlobalErrorMessage("Error al guardar datos de parametrizaci\u00F3n.", "");
			log.error("Error al guardar datos de parametrizacion", e);
		}

		return "";
	}

	/**
	 * Cambia a estado aprobado (R) el parametro seleccionado
	 * 
	 * @return
	 */
	public String aprobar() {
		try {
			if (parametrizacionPQSeleccionado.getFuncionarioIngresa().equals(getRequest().getRemoteUser())) {
				addGlobalErrorMessage(
						"El funcionario que realiz\u00F3 el ingreso del par\u00E1metro no puede ser la misma persona que realiza la aprobaci\u00F3n",
						"");
			} else {
				if (parametrizacionPQSeleccionado.getEstado().equals(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion())) {
					parametrizacionPQSeleccionado.setEstado(EstadoParametrizacionPQEnum.APROBADO.getOpcion());
				} else if (parametrizacionPQSeleccionado.getEstado().equals(EstadoParametrizacionPQEnum.PENDIENTE_ELIMINACION.getOpcion())) {
					parametrizacionPQSeleccionado.setEstado(EstadoParametrizacionPQEnum.ELIMINADO.getOpcion());
					parametrizacionPQSeleccionado.setFechaFin(new Date());
				}
				parametrizacionPQSeleccionado.setFuncionarioAprueba(getRequest().getRemoteUser());
				parametrizacionPQSeleccionado.setFecHorAprueba(new Date());
				parametrizacionPQSeleccionado.setDirIpAprueba(DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()));
				parametrizacionPQSeleccionado.setSistemaIngreso("PQ Intranet");
				parametrizacionPQSeleccionado.setFuncionarioApruebaNombre(funcionarioHandler.getNombreCompleto());

				parametrizacionPQServicio.ingresar(parametrizacionPQSeleccionado);
				obtenerListaDatos();
				addGlobalInfoMessage("Registro aprobado con exito", "");
				parametrizacionPQSeleccionado = new ParametrizacionPQ();
			}
		} catch (Exception e) {
			addGlobalErrorMessage("Error al guardar datos de parametrizaci\u00F3n.", "");
			log.error("Error al guardar datos de parametrizacion", e);
		}
		return "";
	}

	// Getters and setters

	public List<ParametrizacionPQ> getListaParametrizacionPQ() {
		obtenerListaDatos();
		return listaParametrizacionPQ;
	}

	public void setListaParametrizacionPQ(List<ParametrizacionPQ> listaParametrizacionPQ) {
		this.listaParametrizacionPQ = listaParametrizacionPQ;
	}

	public ParametrizacionPQ getParametrizacionPQSeleccionado() {
		return parametrizacionPQSeleccionado;
	}

	public void setParametrizacionPQSeleccionado(ParametrizacionPQ parametrizacionPQSeleccionado) {
		this.parametrizacionPQSeleccionado = parametrizacionPQSeleccionado;
	}

	public Long getCodParametro() {
		return codParametro;
	}

	public void setCodParametro(Long codParametro) {
		this.codParametro = codParametro;
	}

	public FuncionarioHandler getFuncionarioHandler() {
		return funcionarioHandler;
	}

	public void setFuncionarioHandler(FuncionarioHandler funcionarioHandler) {
		this.funcionarioHandler = funcionarioHandler;
	}

}
