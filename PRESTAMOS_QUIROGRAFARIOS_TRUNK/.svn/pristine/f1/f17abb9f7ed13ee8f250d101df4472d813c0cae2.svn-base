package ec.gov.iess.planillaspq.web.backing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import ec.fin.biess.creditos.pq.enumeracion.TipoProductoEnum;
import ec.fin.biess.creditos.pq.servicio.ParametroCreditoServicio;
import ec.fin.biess.modelo.persistencia.ParametrizacionPQ;
import ec.fin.biess.service.ParametrizacionPQServicioLocal;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.planillaspq.web.enumeration.EstadoParametrizacionPQEnum;
import ec.gov.iess.planillaspq.web.handler.FuncionarioHandler;
import ec.gov.iess.planillaspq.web.util.BaseBean;
import ec.gov.iess.planillaspq.web.util.DireccionIPUtil;

/**
 * Backing para administrar el ingreso de parametros de PQ
 * 
 * @author hugo.mora
 *
 */
public class IngresoParametrizacionBacking extends BaseBean implements Serializable {

	private static final long serialVersionUID = 5758350122159623503L;

	private static final LoggerBiess log = LoggerBiess.getLogger(IngresoParametrizacionBacking.class);

	@EJB(name = "ParametrizacionPQServicioImpl/local")
	private ParametrizacionPQServicioLocal parametrizacionPQServicio;

	@EJB(name = "ParametroCreditoServicioImpl/local")
	private ParametroCreditoServicio parametroCreditoServicio;

	private ParametrizacionPQ parametrizacionPQ;

	private List<ParametrizacionPQ> listaParametrizacionPQ = new ArrayList<ParametrizacionPQ>();

	private boolean nuevo = false;

	private Long codParametro;

	private FuncionarioHandler funcionarioHandler;

	private List<SelectItem> listaTipoProducto;

	@PostConstruct
	public void init() {
		try {
			parametrizacionPQ = new ParametrizacionPQ();
			cargarListaParametros();

		} catch (Exception e) {
			log.error("Error al cargar la informacion de parametrizacion" + e.getMessage());
		}
	}

	/**
	 * Inicializa la lista de parametros
	 */
	private void cargarListaParametros() {
		List<String> listaEstados = new ArrayList<String>();
		listaEstados.add(EstadoParametrizacionPQEnum.APROBADO.getOpcion());
		listaEstados.add(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion());
		listaEstados.add(EstadoParametrizacionPQEnum.RECHAZADO.getOpcion());
		listaParametrizacionPQ = parametrizacionPQServicio.obtenerListaParametrizacionPQPorListaEstados(listaEstados);
	}

	/**
	 * Obtiene el parametro seleccionado
	 * 
	 * @param event
	 */
	public void seleccionarParametro(ActionEvent event) {
		parametrizacionPQ = parametrizacionPQServicio.buscarPorId(codParametro);
		nuevo = true;
	}

	/**
	 * Agrega un nuevo parametro
	 * 
	 * @return
	 */
	public String agregar() {
		parametrizacionPQ = new ParametrizacionPQ();

		nuevo = true;
		return "";
	}

	/**
	 * No muestra el panel de edicion
	 * 
	 * @return
	 */
	public String noMuestraPanel() {
		cancelar();
		return "";
	}

	/**
	 * Metodo que permite verificar si el rango de plazo minimo y maximo ya esta en otro rango existente
	 * 
	 * @param plazoMinimoDato
	 * @param plazoMaximoDato
	 * @param tipoProducto
	 * @param codParametro
	 * @param edadMinimoDato
	 * @param edadMaximoDato
	 * @return Devuelve verdadero si ya existe un rango o falso en caso contrario
	 */
	private boolean verificarRangoExiste(BigDecimal plazoMinimoDato, BigDecimal plazoMaximoDato, String tipoProducto, Long codParametro,
			BigDecimal edadMinimoDato, BigDecimal edadMaximoDato) {
		boolean resp = false;

		List<String> listaEstados = new ArrayList<String>();
		listaEstados.add(EstadoParametrizacionPQEnum.APROBADO.getOpcion());
		listaEstados.add(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion());
		List<ParametrizacionPQ> listaParametrizacionPorProducto = this.parametrizacionPQServicio
				.obtenerListaParametrizacionPQPorListaEstadosProducto(listaEstados, tipoProducto);
		for (ParametrizacionPQ param : listaParametrizacionPorProducto) {
			if (codParametro == null || codParametro.intValue() != param.getCodParametro().intValue()) {
				if (codParametro != null && codParametro.intValue() == param.getCodParametro().intValue()
						&& plazoMaximoDato.compareTo(param.getPlazoMaximo()) == 0 && plazoMinimoDato.compareTo(param.getPlazoMinimo()) == 0) {
					resp = false;
					break;
				}

				if (this.existeRango(plazoMinimoDato, plazoMaximoDato, param.getPlazoMinimo(), param.getPlazoMaximo())) {
					if (TipoProductoEnum.JUB_PEN.getDescripcion().equals(tipoProducto)) {
						if (this.existeRango(edadMinimoDato, edadMaximoDato, param.getRangoEdadInicial(), param.getRangoEdadFinal())) {
							resp = true;
						}
					} else {
						resp = true;
					}

					if (resp) {
						break;
					}
				} 

			}
		}

		return resp;
	}

	/**
	 * Metodo para verificar si el rango ingresado ya existe en los rangos ya existentes
	 * 
	 * @param inicio
	 * @param fin
	 * @param inicioLista
	 * @param finLista
	 * @return
	 */
	private boolean existeRango(BigDecimal inicio, BigDecimal fin, BigDecimal inicioLista, BigDecimal finLista) {
		boolean rangoExistente = true;
		if ((inicio.compareTo(inicioLista) < 0 && fin.compareTo(inicioLista) < 0)
				|| (inicio.compareTo(finLista) > 0 && fin.compareTo(finLista) > 0)) {
			rangoExistente = false;
		}
		return rangoExistente;
	}

	/**
	 * Verifica que el plazo minimo sea menor al plazo maximo
	 * 
	 * @param plazoMinimoDado
	 * @param plazoMaximoDato
	 * @return
	 */
	private boolean verificarPlazoMinimoMaximo(BigDecimal plazoMinimoDato, BigDecimal plazoMaximoDato) {
		boolean resp = false;
		if (plazoMinimoDato.compareTo(plazoMaximoDato) >= 0) {
			resp = true;
		}

		return resp;
	}

	/**
	 * Verifica que los plazos minimo y maximo no sean negativos
	 * 
	 * @return
	 */
	private boolean verificarPlazosNegativos(BigDecimal plazoMinimoDato, BigDecimal plazoMaximoDato) {
		boolean resp = false;
		if (plazoMinimoDato.compareTo(BigDecimal.ZERO) < 0 || plazoMaximoDato.compareTo(BigDecimal.ZERO) < 0) {
			resp = true;
		}
		return resp;
	}

	/**
	 * Cancelar la edicion de un proveedor
	 * 
	 * @return {@link String}
	 */
	public String cancelar() {
		nuevo = false;
		parametrizacionPQ = null;

		return "";
	}

	/**
	 * Guarda un registro de tipo ParametrizacionPQ
	 * 
	 * @return
	 */
	public String guardar() {
		try {
			if (verificarPlazoMinimoMaximo(parametrizacionPQ.getPlazoMinimo(), parametrizacionPQ.getPlazoMaximo())) {
				addGlobalErrorMessage(getResource("mensajes", "param.plazo.minimo.maximo"), "");
			} else if (verificarRangoExiste(parametrizacionPQ.getPlazoMinimo(), parametrizacionPQ.getPlazoMaximo(),
					parametrizacionPQ.getTipoProducto(), parametrizacionPQ.getCodParametro(), parametrizacionPQ.getRangoEdadInicial(),
					parametrizacionPQ.getRangoEdadFinal())) {
				addGlobalErrorMessage(getResource("mensajes", "param.plazo.existe"), "");
			} else if (parametrizacionPQ.getTasaInteres().compareTo(BigDecimal.ONE) <= 0) {
				addGlobalErrorMessage(getResource("mensajes", "param.tasa.interes.uno"), "");
			} else if (verificarPlazosNegativos(parametrizacionPQ.getPlazoMinimo(), parametrizacionPQ.getPlazoMaximo())) {
				addGlobalErrorMessage(getResource("mensajes", "param.plazos.negativos"), "");
			} else {
				parametrizacionPQ.setFechaInicio(new Date());
				parametrizacionPQ.setEstado(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion());
				parametrizacionPQ.setFuncionarioIngresa(getRequest().getRemoteUser());
				parametrizacionPQ.setFecHorIngreso(new Date());
				parametrizacionPQ.setDirIpIngreso(DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()));
				parametrizacionPQ.setSistemaIngreso("PQ Intranet");
				parametrizacionPQ.setFuncionarioIngresaNombre(funcionarioHandler.getNombreCompleto());
				parametrizacionPQServicio.ingresar(parametrizacionPQ);
				cargarListaParametros();
				addGlobalInfoMessage(getResource("mensajes", "param.guardado.exito"), "");
				parametrizacionPQ = new ParametrizacionPQ();
				nuevo = false;
			}

		} catch (Exception e) {
			addGlobalErrorMessage(getResource("mensajes", "param.guardado.error"), "");
			log.error("Error al guardar datos de parametrizacion", e);
		}
		return "";
	}

	/**
	 * Cambia el estado de un registro a eliminado
	 * 
	 * @return
	 */
	public String eliminar() {
		try {
			String mensajeEliminado = "";
			if (parametrizacionPQ == null) {
				addGlobalErrorMessage(getResource("mensajes", "param.seleccione.eliminar"), "");
			} else {
				parametrizacionPQ.setFechaFin(new Date());
				if (parametrizacionPQ.getEstado().equals(EstadoParametrizacionPQEnum.PENDIENTE.getOpcion())
						|| parametrizacionPQ.getEstado().equals(EstadoParametrizacionPQEnum.RECHAZADO.getOpcion())) {
					parametrizacionPQ.setEstado(EstadoParametrizacionPQEnum.ELIMINADO.getOpcion());
					mensajeEliminado = "param.eliminado.exito";
				} else if (parametrizacionPQ.getEstado().equals(EstadoParametrizacionPQEnum.APROBADO.getOpcion())) {
					parametrizacionPQ.setEstado(EstadoParametrizacionPQEnum.PENDIENTE_ELIMINACION.getOpcion());
					mensajeEliminado = "param.eliminado.pendiente";
				}
				parametrizacionPQ.setFuncionarioIngresa(getRequest().getRemoteUser());
				parametrizacionPQ.setFecHorIngreso(new Date());
				parametrizacionPQ.setDirIpIngreso(DireccionIPUtil.obtenerDireccionIPCliente(getHttpServletRequest()));
				parametrizacionPQ.setSistemaIngreso("PQ Intranet");
				parametrizacionPQ.setFuncionarioIngresaNombre(funcionarioHandler.getNombreCompleto());

				parametrizacionPQServicio.ingresar(parametrizacionPQ);
				cargarListaParametros();
				addGlobalInfoMessage(getResource("mensajes", mensajeEliminado), "");

				cancelar();
			}
		} catch (Exception e) {
			addGlobalErrorMessage(getResource("mensajes", "param.eliminado.error"), "");
			log.error("Error al guardar datos de parametrizacion", e);
			parametrizacionPQ = new ParametrizacionPQ();
		}
		parametrizacionPQ = new ParametrizacionPQ();
		return "";
	}

	// Getters and setters

	public ParametrizacionPQ getParametrizacionPQ() {
		return parametrizacionPQ;
	}

	public void setParametrizacionPQ(ParametrizacionPQ parametrizacionPQ) {
		this.parametrizacionPQ = parametrizacionPQ;
	}

	public List<ParametrizacionPQ> getListaParametrizacionPQ() {
		cargarListaParametros();
		return listaParametrizacionPQ;
	}

	public void setListaParametrizacionPQ(List<ParametrizacionPQ> listaParametrizacionPQ) {
		this.listaParametrizacionPQ = listaParametrizacionPQ;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
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

	/**
	 * @return
	 */
	public List<SelectItem> getListaTipoProducto() {
		if (listaTipoProducto == null) {
			listaTipoProducto = new ArrayList<SelectItem>();
			for (TipoProductoEnum tipoProducto : TipoProductoEnum.values()) {
				listaTipoProducto.add(getItem(tipoProducto));
			}
		}
		return listaTipoProducto;
	}
	
	/**
	 * Crea un nuevo item a partir de la enumeracion TipoProductoEnum
	 * 
	 * @param tipoProducto
	 * @return
	 */
	private SelectItem getItem(TipoProductoEnum tipoProducto) {
		return new SelectItem(tipoProducto.getDescripcion(), tipoProducto.getDescripcion());
	}

	/**
	 * @param listaTipoProducto
	 */
	public void setListaTipoProducto(List<SelectItem> listaTipoProducto) {
		this.listaTipoProducto = listaTipoProducto;
	}

}
