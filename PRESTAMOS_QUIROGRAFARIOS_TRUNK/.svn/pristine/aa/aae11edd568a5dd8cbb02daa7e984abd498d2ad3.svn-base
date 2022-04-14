/*
 * Copyright 2014 BIESS - ECUADOR
 * 
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.filtros;

import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.gov.iess.pq.concesion.web.backing.RolesBean;

/**
 * Filtro para bloquear paginas mientras se esta ejecutando procesos de BD.
 *
 */
public class ProcesoMigracionFilter implements Filter {

	private String[] paginasBloqueadas;

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
	}

	/*
	 * Se valida parametro de ejecucion de procesos para bloquear paginas.
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 * javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		RolesBean rolesBean = (RolesBean) request.getSession().getAttribute("roles");
		String url = request.getServletPath();
		if (blockPages(rolesBean)) {
			/* Verifica si el url debe ser bloqueado */
			if (Arrays.binarySearch(paginasBloqueadas, url) >= 0) {
				response.sendRedirect(request.getContextPath() + "/pages/concesion/bloqueoProceso.jsf");
				return;
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
	 * Verifica si hay algun proceso ejecutandose.
	 * 
	 * @param rolesBean Bean de sesion
	 * @return boolean indica si hay algun proceso ejecutandose.
	 */
	private boolean blockPages(RolesBean rolesBean) {
		if (null == rolesBean) {
			return false;
		}
		/* Verifica si existe un proceso de conciliacion para este cliente*/
		if (rolesBean.getDatos().isProcesoConciliacionMig()) {
			return true;
		}
		
		
		return false;
	}
	
}
