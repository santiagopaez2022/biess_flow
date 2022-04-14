package ec.fin.biess.creditos.pq.filtros;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.fin.biess.conozcasucliente.modelo.persistence.Cliente;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.pq.concesion.conozcacliente.backing.ConozcaClienteBacking;
import ec.gov.iess.pq.concesion.web.backing.RolesBean;

/**
 * Filtro para redireccionamiento en bloqueo de fines de semana, bloqueo de novaciones y conozca su cliente
 * 
 * @author hugo.mora
 * @date 2015/06/10
 * 
 */
public class BloqueosAccesosFilter implements Filter {

	private static final LoggerBiess log = LoggerBiess.getLogger(BloqueosAccesosFilter.class);

	private String[] paginasBloqueadas;

	private String[] paginasBloqueadasNov;

	private String[] paginasBloqueadasConCli;
	
	private String[] paginasBloqueadasAmaCasa;

	/*
	 * Referencia
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		/* Obtener la lista de paginas ha ser bloquedas mientras corre el proceso de planillas */
		if (paginasBloqueadas == null) {
			final StringTokenizer st = new StringTokenizer(filterConfig.getInitParameter("paginasBloqueadas"), ",");
			paginasBloqueadas = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				paginasBloqueadas[i++] = st.nextToken();
			}
			Arrays.sort(paginasBloqueadas);
		}

		if (paginasBloqueadasNov == null) {
			final StringTokenizer st = new StringTokenizer(filterConfig.getInitParameter("paginasBloqueadasNov"), ",");
			paginasBloqueadasNov = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				paginasBloqueadasNov[i++] = st.nextToken();
			}
			Arrays.sort(paginasBloqueadasNov);
		}

		if (paginasBloqueadasConCli == null) {
			final StringTokenizer st = new StringTokenizer(filterConfig.getInitParameter("paginasBloqueadasConCli"), ",");
			paginasBloqueadasConCli = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				paginasBloqueadasConCli[i++] = st.nextToken();
			}
			Arrays.sort(paginasBloqueadasConCli);
		}
		
		// paginasBloqueadasAmaCasa
		if (paginasBloqueadasAmaCasa == null) {
			final StringTokenizer st = new StringTokenizer(filterConfig.getInitParameter("paginasBloqueadasAmaCasa"), ",");
			paginasBloqueadasAmaCasa = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				paginasBloqueadasAmaCasa[i++] = st.nextToken();
			}
			Arrays.sort(paginasBloqueadasAmaCasa);
		}

	}

	/*
	 * Se valida parametro de ejecucion de procesos para bloquear paginas.
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 * javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
			ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getServletPath();

		
		int mes = determinarMes();
		int dia = determinarDiaMes();
		RolesBean rolesBean = (RolesBean) request.getSession().getAttribute("roles");

		if (rolesBean != null && rolesBean.getDatos() != null) {
			if (url.contains("/pages/novacion/informacionNovacion")) {
				rolesBean.getDatos().setMostrarBotonNovacion(true);
				rolesBean.getDatos().setMostrarBotonConcesion(false);
			} else if (url.contains("/pages/concesion/tipoProductos")) {
				rolesBean.getDatos().setMostrarBotonNovacion(false);
				rolesBean.getDatos().setMostrarBotonConcesion(true);
			} 
		}
		// Si se cambian estos dias tambien se debe cambiar en RolesBean en el metodo seteaVariableBloqueoFinesSemana()
		if ((rolesBean != null && rolesBean.getDiaFeriado())) {
			// Bloqueo del aplicativo de concesion y novacion de credito durante los fines de semana
			/* Verifica si el url debe ser bloqueado */
			if (Arrays.binarySearch(paginasBloqueadas, url) >= 0) {
				response.sendRedirect(request.getContextPath() + "/pages/concesion/bloqueoFinSemana.jsf");
				return;
			}
		} else if (rolesBean != null && rolesBean.getDatos() != null && rolesBean.getDatos().getDiaBloqEnNov() != null
				&& rolesBean.getDatos().getDiaBloqDic() != null) {
			if (rolesBean.getDatos().isEsAmaDeCasa() && rolesBean.getDatos().isPrestamoVigenteAmaCasa() && Arrays.binarySearch(paginasBloqueadasAmaCasa, url) >= 0) {
				response.sendRedirect(request.getContextPath() + "/pages/concesion/bloqueoAmaCasa.jsf");
				return;
			} else if ((mes != 12) && (dia >= Integer.parseInt(rolesBean.getDatos().getDiaBloqEnNov())) && (dia <= ultimoDiaMes())) {
				// Bloqueo de novacion desde el 27 a fin de mes (enero a noviembre, el valor del dia toma de una tabla
				// de parametros)
				/* Verifica si el url debe ser bloqueado */
				if (Arrays.binarySearch(paginasBloqueadasNov, url) >= 0) {
					response.sendRedirect(request.getContextPath() + "/pages/novacion/bloqueoNovacion.jsf");
					return;
				} else if (blockPagesConozcaCliente(getDatosConozcaClienteRoles(request), getDatosConozcaClienteConozca(request)) && Arrays.binarySearch(paginasBloqueadasConCli, url) >= 0) {
					response.sendRedirect(request.getContextPath() + "/pages/conozcasucliente/informativo.jsf");
					return;
				}
			} else if ((mes == 12) && (dia >= Integer.parseInt(rolesBean.getDatos().getDiaBloqDic())) && (dia <= ultimoDiaMes())) {
				// Bloqueo de novacion desde el 25 al 31 de diciembre (el valor del dia toma de una tabla de parametros)
				/* Verifica si el url debe ser bloqueado */
				if (Arrays.binarySearch(paginasBloqueadasNov, url) >= 0) {
					response.sendRedirect(request.getContextPath() + "/pages/novacion/bloqueoNovacion.jsf");
					return;
				} else if (blockPagesConozcaCliente(getDatosConozcaClienteRoles(request), getDatosConozcaClienteConozca(request)) && Arrays.binarySearch(paginasBloqueadasConCli, url) >= 0) {
					response.sendRedirect(request.getContextPath() + "/pages/conozcasucliente/informativo.jsf");
					return;
				}
			} else if (blockPagesConozcaCliente(getDatosConozcaClienteRoles(request), getDatosConozcaClienteConozca(request))) {
				/* Verifica si el url debe ser bloqueado */
				if (Arrays.binarySearch(paginasBloqueadasConCli, url) >= 0) {
					response.sendRedirect(request.getContextPath() + "/pages/conozcasucliente/informativo.jsf");
					return;
				}
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	/*
	 * Referencia
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * Obtiene el día actual
	 * 
	 * @return
	 */
	private int determinarDiaMes() {
		Calendar clFechaActual = Calendar.getInstance();
		clFechaActual.setTime(new Date());

		return clFechaActual.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Obtiene el mes actual
	 * 
	 * @return
	 */
	private int determinarMes() {
		Calendar clFechaActual = Calendar.getInstance();
		clFechaActual.setTime(new Date());
		return clFechaActual.get(Calendar.MONTH) + 1;
	}

	/**
	 * Obtiene el ultimo dia del mes
	 * 
	 * @return
	 */
	private int ultimoDiaMes() {
		Calendar cal = Calendar.getInstance();
		int ultimoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		return ultimoDia;
	}

	/**
	 * Determina bajo qué circunstancia el aplicativo debe ser bloqueado por conozca a su cliente
	 * 
	 * @return
	 */
	private boolean blockPagesConozcaCliente(Cliente clienteRoles, Cliente clienteConozca) {
		boolean resp = false;
		
		if (clienteRoles == null || clienteRoles.getFechaActualizacion() == null) {
			resp = true;
		} else if (this.debeActualizarFormulario(clienteRoles.getFechaActualizacion())
				&& (clienteConozca == null || "0".equals(clienteConozca.getEstadoRegistro()))) {
			// Si el valor del EstadoRegistro es igual a 0 significa que aún no se ha guardado la actualización de datos
			resp = true;
		}
		// Bloquea solo si el registro no esta actualizado
		return resp;
	}
	
	/**
	 * Indica si la fecha actual tiene un anio de diferencia con la fecha de actualizacion
	 * 
	 * @param fechaActualiza
	 * @return
	 */
	private boolean debeActualizarFormulario(Date fechaActualiza) {
		boolean actualizar = false;
		Calendar fechaActualizaCalendar = Calendar.getInstance();
		fechaActualizaCalendar.setTime(fechaActualiza);
		fechaActualizaCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fechaActualizaCalendar.set(Calendar.MINUTE, 0);
		fechaActualizaCalendar.set(Calendar.SECOND, 0);
		fechaActualizaCalendar.set(Calendar.MILLISECOND, 0);

		Calendar fechaActualRestada = Calendar.getInstance();
		fechaActualRestada.set(Calendar.HOUR_OF_DAY, 0);
		fechaActualRestada.set(Calendar.MINUTE, 0);
		fechaActualRestada.set(Calendar.SECOND, 0);
		fechaActualRestada.set(Calendar.MILLISECOND, 0);

		fechaActualRestada.add(Calendar.YEAR, -1);

		if (fechaActualRestada.equals(fechaActualizaCalendar) || fechaActualRestada.after(fechaActualizaCalendar)) {
			actualizar = true;
		}

		return actualizar;
	}

	private Cliente getDatosConozcaClienteRoles(HttpServletRequest request) {
		Cliente cliente = null;
		try {
			RolesBean datosBeanRoles = (RolesBean) request.getSession().getAttribute("roles");
			if (datosBeanRoles != null) {
				cliente = datosBeanRoles.getCliente();
			}
		} catch (Exception e) {
			log.error("Error al obtener bean de session roles", e);
		}
		return cliente;
	}
	
	private Cliente getDatosConozcaClienteConozca(HttpServletRequest request) {
		Cliente cliente = null;
		try {
			ConozcaClienteBacking datosBeanRoles = (ConozcaClienteBacking) request.getSession().getAttribute("conozcaCliente");
			if (datosBeanRoles != null) {
				cliente = datosBeanRoles.getCliente();
			}
		} catch (Exception e) {
			log.error("Error al obtener bean de session cliente", e);
		}
		return cliente;
	}

}
