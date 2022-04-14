package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import ec.gov.iess.consolidado.modelo.ResumenConsolidado;
import ec.gov.iess.consolidado.servicio.ResumenConsolidadoServicio;
import ec.gov.iess.creditos.dao.CatalogoTipoPrestacionDao;
import ec.gov.iess.creditos.dao.CatalogoTipoSeguroDao;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.excepcion.ParametroNoEncontradoException;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoPrestacion;
import ec.gov.iess.creditos.modelo.persistencia.CatalogoTipoSeguro;
import ec.gov.iess.creditos.pq.excepcion.DeterminarRolException;
import ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal;
import ec.gov.iess.creditos.pq.servicio.ParametroServicio;
import ec.gov.iess.hl.exception.NoTieneRelacionDeDependenciaException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.modelo.ServicioPrestado;
import ec.gov.iess.hl.servicio.ImposicionServicio;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;
import ec.gov.iess.pensiones.exception.PensionException;
import ec.gov.iess.servicio.pensiones.modelo.TipoPrestacionSeguro;
import ec.gov.iess.servicio.pensiones.servicio.PrestacionServicio;

/**
 * Inplementacion del servicio para definicion de roles
 * 
 * @author hugo.mora
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DefinirRolServicioImpl implements DefinirRolServicioLocal {

	private transient static final Logger LOG = Logger.getLogger(DefinirRolServicioImpl.class);

	@EJB
	private ServicioPrestadoServicio servicioPrestadoServicio;
	@EJB
	private ParametroServicio parametroServicio;
	@EJB
	private ResumenConsolidadoServicio resumenConsolidadoServicio;
	@EJB
	private ImposicionServicio imposicionServicio;
	@EJB
	private PrestacionServicio prestacionServicio;
	@EJB
	private CatalogoTipoPrestacionDao tipoPrestacionDao;
	@EJB
	private CatalogoTipoSeguroDao tipoSeguroDao;
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal#determinarTipoRol(java.lang.String)
	 */
	public TipoPrestamista determinarTipoRol(String cedula) throws DeterminarRolException, PensionException, ParametroNoEncontradoException {
		LOG.debug("Determina tipo rol : " + cedula);

		boolean esAfiliado = false;
		try {

			esAfiliado = servicioPrestadoServicio.consultarEsActivoPorCedula(cedula);
			LOG.debug(" Afiliado s/n : " + esAfiliado);

		} catch (ServicioPrestadoException e) {
			LOG.error("Error alo consultar si es activo : ", e);
			throw new DeterminarRolException(e);
		}

		// 2 determina si tiene 60 imposiciones
		int totalImposiciones = parametroServicio.obtenerParametroPorId("11").getValor().intValue();
		LOG.debug(" Tiene " + totalImposiciones + " ?");
		boolean tieneImposiciones = false;

		ResumenConsolidado resumenConsolidado = resumenConsolidadoServicio.getResumenByCedula(cedula);

		if (resumenConsolidado == null) {
			StringBuffer msg = new StringBuffer();
			msg.append("La cedula : " + cedula + " consta como activo y no se encuentra datos de resumen");
			LOG.error(msg.toString());
			throw new DeterminarRolException(msg.toString());
		}

		LOG.debug(" Total de imposiciones : " + resumenConsolidado.getNumeroImposiciones());

		if (resumenConsolidado.getNumeroImposiciones().compareTo(Long.valueOf(totalImposiciones)) >= 0) {
			tieneImposiciones = true;
		}

		// 3 determina si tiene 12 imposiciones consecutivas
		int imposicionesConsecutivas = parametroServicio.obtenerParametroPorId("20").getValor().intValue();
		LOG.debug(" Imposiciones Consecutivas " + imposicionesConsecutivas + " ?");
		boolean tieneConsecutivas = false;
		int impoConsecutivas = 0;

		if (esAfiliado) {
			Calendar fechaDesde = Calendar.getInstance();
			fechaDesde.setTime(new Date());

			Date fechaConsultaImposicionesConsecutivas = fechaDesde.getTime();
			impoConsecutivas = imposicionServicio.consultarnumeroImposicionesConcecutivas(cedula, fechaConsultaImposicionesConsecutivas);

			if (impoConsecutivas >= imposicionesConsecutivas) {
				tieneConsecutivas = true;
			}
		}

		LOG.debug(" Tiene consecutivas : " + impoConsecutivas + " cumple : " + tieneConsecutivas);

		// 4. determinar si es jubilado
		LOG.debug("determinar si es jubilado");
		boolean esJubilado = false;

		if (prestacionServicio.getListaPrestaciones(cedula, getPrestacionesValidas()).size() > 0) {
			esJubilado = true;
		}

		LOG.debug(" Es Jubilado : " + esJubilado);

		TipoPrestamista tipoPrestamista = null;
		// 5 califica el tipo de rol en el sistema
		if (esAfiliado && tieneImposiciones && tieneConsecutivas && esJubilado) {
			tipoPrestamista = TipoPrestamista.AFILIADO_JUBILADO;
		} else if (esAfiliado && tieneConsecutivas && tieneImposiciones) {
			tipoPrestamista = TipoPrestamista.AFILIADO;
		} else if (esJubilado) {
			tipoPrestamista = TipoPrestamista.JUBILADO;
		} else if (esAfiliado) {
			tipoPrestamista = TipoPrestamista.AFILIADO;
		} else {
			LOG.debug("NO CALIFICO COMO AFILIADO JUBILADO");
			if (prestacionServicio.esJubilado(cedula)) {
				tipoPrestamista = TipoPrestamista.JUBILADO;
			} else {

				StringBuffer msg = new StringBuffer();
				msg.append("La cedula : " + cedula + " no esta registrado como Afiliado Activo, y tampoco como Jubilado de Vejez.");
				throw new DeterminarRolException(msg.toString());
			}

		}

		LOG.debug("Tipo Rol : " + tipoPrestamista);
		return tipoPrestamista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal#getPrestacionesValidas()
	 */
	public List<TipoPrestacionSeguro> getPrestacionesValidas() {
		List<TipoPrestacionSeguro> listaPrestaciones = new ArrayList<TipoPrestacionSeguro>();

		List<CatalogoTipoPrestacion> listaTiposPrestaciones = tipoPrestacionDao.obtenerTiposPrestacionActivos();

		List<CatalogoTipoSeguro> listaTiposSeguros = tipoSeguroDao.obtenerTiposSeguroActivos();

		TipoPrestacionSeguro jubilacion;
		for (CatalogoTipoPrestacion prestacion : listaTiposPrestaciones) {
			for (CatalogoTipoSeguro seguro : listaTiposSeguros) {
				jubilacion = new TipoPrestacionSeguro();
				jubilacion.setTipoPrestacion(prestacion.getTipoPrestacion());
				jubilacion.setTipoSeguro(seguro.getTipoSeguro());
				listaPrestaciones.add(jubilacion);
			}
		}

		return listaPrestaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal#determinaVoluntarioCesante(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public TipoPrestamista determinaVoluntarioCesante(String cedula, TipoPrestamista tipoPrestamista)
			throws ServicioPrestadoException, NoTieneRelacionDeDependenciaException {
		TipoPrestamista prestamista = tipoPrestamista;

		// Determina si es cesante
		boolean esActivo = servicioPrestadoServicio.consultarEsActivoPorCedula(cedula);
		if (!esActivo) {
			prestamista = TipoPrestamista.CESANTE;
		}else {
			// Determina si es voluntario
			List<String> codigoRelTrab = new ArrayList<String>();
			codigoRelTrab.add("89");
			codigoRelTrab.add("90");
			List<ServicioPrestado> listaServicioPrestado = servicioPrestadoServicio.consultaActivoPorCedulaRelacionTrabajo(cedula, codigoRelTrab);
			if (listaServicioPrestado != null && !listaServicioPrestado.isEmpty()) {
				prestamista = TipoPrestamista.VOLUNTARIO;
			}
		}
		
		return prestamista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.DefinirRolServicioLocal#determinarTipoPrestamistaCesanteVoluntarioDobleRol(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@Override
	public TipoPrestamista determinarTipoPrestamistaCesanteVoluntarioDobleRol(String cedula, TipoPrestamista tipoPrestamista)
			throws ServicioPrestadoException, NoTieneRelacionDeDependenciaException {
		TipoPrestamista prestamista = tipoPrestamista;
		
		// Determina si es cesante
		boolean esActivo = servicioPrestadoServicio.consultarEsActivoPorCedula(cedula);
		boolean esVoluntario = false;
		boolean esUnipersonal = false;
		
		// Determina si es cesante			
		if (!esActivo) {
			prestamista = TipoPrestamista.CESANTE;
		}else if (TipoPrestamista.AFILIADO.equals(prestamista)) {
			
			// Determina si es Unipersonal
			esUnipersonal = servicioPrestadoServicio.consultarEsUnipersonal(cedula);
						
			List<String> listaCodRelTra = servicioPrestadoServicio.afiliadoTrabajaEnEmpresaConRelacionDeTrabajoL(cedula);
			// Determina si es voluntario
			if(listaCodRelTra.size()==1) {
				if(listaCodRelTra.contains("89")||listaCodRelTra.contains("90")) {
					prestamista = TipoPrestamista.VOLUNTARIO;
				}else if(esUnipersonal) {
					prestamista = TipoPrestamista.UNIPERSONAL;
				}	
			// Determina si es voluntario_afiliado o unipersonal_afiliado
			}else if(listaCodRelTra.size()==2) {
				if(listaCodRelTra.contains("89")|listaCodRelTra.contains("90")) {
					prestamista = TipoPrestamista.VOLUNTARIO_AFILIADO;
				}else if(esUnipersonal) {
					prestamista = TipoPrestamista.UNIPERSONAL_AFILIADO;
				}
			}	
		}else if(TipoPrestamista.AFILIADO_JUBILADO.equals(prestamista)){
			
			// Determina si es Voluntario
			List<String> codigoRelTrab = new ArrayList<String>();
			codigoRelTrab.add("89");
			codigoRelTrab.add("90");
			esVoluntario = servicioPrestadoServicio.consultarEsVoluntario(cedula, codigoRelTrab);
			
			// Determina si es Unipersonal
			esUnipersonal = servicioPrestadoServicio.consultarEsUnipersonal(cedula);
			
			if(esVoluntario) {
				prestamista = TipoPrestamista.VOLUNTARIO_JUBILADO;
			}else if(esUnipersonal) {
				prestamista = TipoPrestamista.UNIPERSONAL_JUBILADO;
			}
			
		}
		return prestamista;
	}

}
