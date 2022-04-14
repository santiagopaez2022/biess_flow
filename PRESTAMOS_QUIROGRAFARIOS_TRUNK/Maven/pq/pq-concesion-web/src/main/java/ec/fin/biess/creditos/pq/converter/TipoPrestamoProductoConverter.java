/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.fin.biess.creditos.pq.servicio.TipoPrestamoServicio;
import ec.gov.iess.creditos.modelo.persistencia.TipoPrestamo;

/**
 * Convertidor para la entidad TipoPrestamoProducto
 * 
 * @author edwin.maza
 * 
 */
public class TipoPrestamoProductoConverter implements Converter {

	@EJB(name = "TipoPrestamoServicioImpl/local")
	private TipoPrestamoServicio tipoPrestamoServicio;

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 *      , javax.faces.component.UIComponent, java.lang.String)
	 */
	// @Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value.isEmpty())
			return null;
		return tipoPrestamoServicio.load(Long.valueOf(value));
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 *      , javax.faces.component.UIComponent, java.lang.Object)
	 */
	// @Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		System.out.println(value);
		if (value == null)
			return "";
		return ((TipoPrestamo) value).getCodigo().toString();
	}

}
