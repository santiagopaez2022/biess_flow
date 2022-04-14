/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.fin.biess.creditos.pq.dao;

import java.util.Date;

import javax.ejb.Local;

import ec.fin.biess.creditos.pq.excepcion.AfiliadoExceptionBiess;
import ec.gov.iess.dao.GenericDao;
import ec.gov.iess.hl.modelo.Afiliado;

/**
 * DAO para la clase Afiliado
 * 
 * @author Omar Villanueva
 * @version 1.0.0
 * 
 */
@Local
public interface AfiliadoDaoBiess extends GenericDao<Afiliado, String> {

	/**
	 * @param numafi
	 * @param coddivpol
	 * @param dirafi
	 * @param telafi
	 * @param celafi
	 * @param maiafi
	 * @param posviv
	 * @param fecactafi
	 * @param dpcodigo
	 * @return
	 * @throws AfiliadoExceptionBiess 
	 */
	public void actualizarDatosAfiliado(String numafi, String coddivpol, String dirafi, String telafi, String celafi,
			String maiafi, String posviv, Date fecactafi, String dpcodigo) throws AfiliadoExceptionBiess;

}
