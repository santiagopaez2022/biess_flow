/*
 * Copyright 2013 BIESS - ECUADOR
 * Licensed under the BIESS License, Version 1.0 (the "License"); you may not use this
 * file. You may obtain a copy of the License at http://www.biess.fin.ec Unless required
 * by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ec.gov.iess.pq.concesion.web.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;

import org.richfaces.model.DataProvider;

import ec.fin.biess.creditos.pq.dao.PrestamoBiessDao;
import ec.fin.biess.creditos.pq.modelo.persistencia.ClasePrestamo;
import ec.fin.biess.creditos.pq.modelo.persistencia.EstadoPrestamo;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiess;
import ec.fin.biess.creditos.pq.modelo.persistencia.PrestamoBiessPK;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.enumeracion.EstadoPrestamoEnum;
import ec.gov.iess.creditos.pq.dto.CreditoExigibleDto;
import ec.gov.iess.creditos.pq.excepcion.PQExigibleException;
import ec.gov.iess.creditos.pq.servicio.CreditoPQExigibleSacServicioLocal;
import ec.gov.iess.creditos.pq.util.FuncionesUtilesSac;

/**
 * Clase que ayuda con la paginación de la lista de prestamos.
 * 
 * @author Omar Villanueva
 * @version 1.0
 *
 */
public class PrestamoDataProvider implements DataProvider<PrestamoBiess> {

	private static final LoggerBiess LOG = LoggerBiess.getLogger(PrestamoDataProvider.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PrestamoBiessDao prestamoBiessDao;

	private List<String> estadosCredito;

	private List<Long> tiposCredito;

	private CreditoPQExigibleSacServicioLocal creditoPQExigibleServicio;

	private List<CreditoExigibleDto> creditosExigibleDto;
	
	private String identificacion;



	/**
	 * Constructor.
	 * 
	 * @param prestamoBiessDao
	 * @param cedula
	 * @param estadosCredito
	 * @param tiposCredito
	 * @throws PQExigibleException
	 */
	public PrestamoDataProvider(CreditoPQExigibleSacServicioLocal creditoPQExigibleServicio,
			PrestamoBiessDao prestamoBiessDao, String cedula, List<String> estadosCredito, List<Long> tiposCredito)
			throws PQExigibleException {
		this.creditoPQExigibleServicio = creditoPQExigibleServicio;
		this.prestamoBiessDao = prestamoBiessDao;
		this.estadosCredito = estadosCredito;
		this.tiposCredito = tiposCredito;
		this.identificacion=cedula;
		this.creditosExigibleDto = this.creditoPQExigibleServicio.obtenerListaPQExigibles(cedula);
		Collections.sort(creditosExigibleDto);
	}

	/* (non-Javadoc)
	 * @see org.richfaces.model.DataProvider#getItemByKey(java.lang.Object)
	 */
	public PrestamoBiess getItemByKey(Object key) {
		return prestamoBiessDao.load((PrestamoBiessPK) key);
	}

	/* (non-Javadoc)
	 * @see org.richfaces.model.DataProvider#getItemsByRange(int, int)
	 */
	public List<PrestamoBiess> getItemsByRange(int firstRow, int endRow) throws NoResultException {
		List<PrestamoBiess> listadoPrestamo = new ArrayList<PrestamoBiess>();
		if (firstRow < 0) {
			firstRow = 0;
		}
		int maxResults = endRow - firstRow;
		if (maxResults <= 0) {
			endRow = creditosExigibleDto.size();
		}
		for (int i = firstRow; i < endRow; i++) {
			listadoPrestamo.add(fabricarPrestamoBiess(creditosExigibleDto.get(i)));
	}

		return listadoPrestamo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.richfaces.model.DataProvider#getKey(java.lang.Object)
	 */
	public Object getKey(PrestamoBiess prestamo) {
		return prestamo;
	}

	/* (non-Javadoc)
	 * @see org.richfaces.model.DataProvider#getRowCount()
	 */
	public int getRowCount() {
		return creditosExigibleDto.size();
	}


	/**
	 * Permite armar la endidad PrestamoBiess a partir de una entidad de
	 * creditoExigibleDto que retorna el servicio SAC
	 * 
	 * @param creditosExigibleDto
	 * @return
	 * @throws PQExigibleException
	 */
	private PrestamoBiess fabricarPrestamoBiess(CreditoExigibleDto creditosExigibleDto) throws NoResultException {
		
		
		PrestamoBiess prestamo=prestamoBiessDao.buscarPorOpIdent(creditosExigibleDto.getOperacionSAC(), identificacion);
		
		if(prestamo==null) {
			LOG.error("El credito exigible del SAC no coincide con el credito registrado en Biess no.op:"+creditosExigibleDto.getOperacionSAC()+" id:"+identificacion);
			throw new NoResultException("No se encuentra el credito");
		}
		PrestamoBiess prestamoBiess = new PrestamoBiess();
	    
		prestamoBiess.setPlzmes(creditosExigibleDto.getTotalCuotas().longValue());
		ClasePrestamo clasePrestamo = new ClasePrestamo();
		clasePrestamo.setDescripcion(creditosExigibleDto.getDestinoComercial());
		prestamoBiess.setClasePrestamo(clasePrestamo);
		PrestamoBiessPK prestamoBiessPK = new PrestamoBiessPK();
		prestamoBiessPK.setNumpreafi(prestamo.getCreditoPk().getNumpreafi());
		prestamoBiess.setNut(creditosExigibleDto.getNut());
		prestamoBiess.setCreditoPk(prestamoBiessPK);
		EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		estadoPrestamo.setDesestpre(prestamo.getEstadoPrestamo().getDesestpre());
		prestamoBiess.setEstadoPrestamo(estadoPrestamo);
		prestamoBiess.setNut(creditosExigibleDto.getNut());
		prestamoBiess.setDiasMora(creditosExigibleDto.getDiasMora());
		prestamoBiess.setNumOperacionSAC(creditosExigibleDto.getOperacionSAC());
		try {
			prestamoBiess.setFecpreafi(FuncionesUtilesSac.obtenerFechaSac(creditosExigibleDto.getFechaConcesion()));
			prestamoBiess
					.setFechaFinPrestamo(FuncionesUtilesSac.obtenerFechaSac(creditosExigibleDto.getFechaVencimiento()));

		} catch (ParseException e) {
			LOG.error("Error al realizar el parseo d las fechas", e);
		}
		prestamoBiess.setValsalcap(creditosExigibleDto.getMontoConcedido().doubleValue());

		return prestamoBiess;
	}

	public List<String> getEstadosCredito() {
		return estadosCredito;
	}

	public void setEstadosCredito(List<String> estadosCredito) {
		this.estadosCredito = estadosCredito;
	}

	public List<Long> getTiposCredito() {
		return tiposCredito;
	}

	public void setTiposCredito(List<Long> tiposCredito) {
		this.tiposCredito = tiposCredito;
	}

	public List<CreditoExigibleDto> getCreditosExigibleDto() {
		return creditosExigibleDto;
	}

	public void setCreditosExigibleDto(List<CreditoExigibleDto> creditosExigibleDto) {
		this.creditosExigibleDto = creditosExigibleDto;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

}
