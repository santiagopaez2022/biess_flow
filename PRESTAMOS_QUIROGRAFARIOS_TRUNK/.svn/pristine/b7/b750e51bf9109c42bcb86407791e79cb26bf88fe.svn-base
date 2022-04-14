/* 
 * Copyright 2007 INSTITUTO ECUATORIANO DE SEGURIDAD
 * SOCIAL - ECUADOR Licensed under the IESS License, Version 1.0 (the
 * "License"); you may not use this file. You may obtain a copy of the License
 * at http://www.iess.gov.ec Unless required by applicable law or agreed to in
 * writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.gov.iess.creditos.pq.servicio;

import javax.ejb.Local;

import ec.gov.iess.creditos.modelo.persistencia.DetalleCatalogos;

/**
 * @author pjarrin
 * 
 */
@Local
public interface DetalleCatalogoServicio {

	/**
	 * Comprueba si el parentezco es o no de beneficiario
	 * 
	 * @param parentezco
	 * @return
	 */
	boolean compruebaParentezcoBeneficiario(String parentezco);
	
	/**
	 * Encuentra el detalle catalogo por el catalogo y el codigo de
	 * detalleCatalogo
	 * 
	 * @param codigoCatalogo
	 * @param codigoDetalleCatalogo
	 * @return
	 */
	DetalleCatalogos encontrarDetalleCatalogo(String codigoCatalogo,
			String codigoDetalleCatalogo);
}