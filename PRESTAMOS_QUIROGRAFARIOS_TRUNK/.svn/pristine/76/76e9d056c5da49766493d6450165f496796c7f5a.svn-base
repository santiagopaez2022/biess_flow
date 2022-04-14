/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.RegistroCivilExtranjeroDao;
import ec.gov.iess.creditos.modelo.persistencia.RegistroCivilExtranjero;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * Implementacion para la entidad RegistroCivilExtranjero.
 * 
 * @author diego.iza.
 * 
 * @version 1.0
 */
@Stateless
public class RegistroCivilExtranjeroDaoImpl extends GenericEjbDao<RegistroCivilExtranjero, String> implements
		RegistroCivilExtranjeroDao {

	LoggerBiess log = LoggerBiess.getLogger(PrestamoDaoImpl.class);

	/**
	 * Constructor.
	 */
	public RegistroCivilExtranjeroDaoImpl() {
		super(RegistroCivilExtranjero.class);
	}

	/**
	 * @see ec.gov.iess.creditos.dao.RegistroCivilExtranjeroDao#obtenerPorCedula(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RegistroCivilExtranjero> obtenerPorCedula(String cedula) {
		Query query = em.createNamedQuery("RegistroCivilExtranjero.obtenerPorCedula");
		query.setParameter("cedula", cedula);
		return query.getResultList();
	}
}
