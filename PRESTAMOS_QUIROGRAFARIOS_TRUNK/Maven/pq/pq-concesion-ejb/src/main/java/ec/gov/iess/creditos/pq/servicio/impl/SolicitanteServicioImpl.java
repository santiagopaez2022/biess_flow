package ec.gov.iess.creditos.pq.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.fin.biess.conozcasucliente.exception.ClienteExcepcion;
import ec.fin.biess.conozcasucliente.modelo.persistence.Cliente;
import ec.fin.biess.conozcasucliente.service.ClienteServicioLocal;
import ec.fin.biess.creditos.pq.excepcion.RegistroCivilExtranjeroException;
import ec.fin.biess.creditos.pq.servicio.DatosPersonalesAfiliadoBiessServicio;
import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.constante.ConstantesCreditos;
import ec.gov.iess.creditos.enumeracion.Genero;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.TipoDocumentoExtranjero;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.modelo.dto.DatosPersonales;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.persistencia.RegistroCivilExtranjero;
import ec.gov.iess.creditos.pq.excepcion.DeterminarEmpleadorParaDescontarPrestamoException;
import ec.gov.iess.creditos.pq.excepcion.NoServicioPrestadoSucursalException;
import ec.gov.iess.creditos.pq.excepcion.ObtenerRolesExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteExcepcion;
import ec.gov.iess.creditos.pq.excepcion.SolicitanteNoEncontradoExcepcion;
import ec.gov.iess.creditos.pq.helper.reglas.Messages;
import ec.gov.iess.creditos.pq.servicio.RegistroCivilExtranjeroServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicio;
import ec.gov.iess.creditos.pq.servicio.SolicitanteServicioRemote;
import ec.gov.iess.creditos.pq.util.SucursalIessHelper;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancaria;
import ec.gov.iess.cuentabancaria.modelo.CuentaBancariaAfiliado;
import ec.gov.iess.cuentabancaria.servicio.CuentaBancariaAfiliadoServicio;
import ec.gov.iess.hl.exception.AfiliadoException;
import ec.gov.iess.hl.exception.AfiliadoNoEncontradoException;
import ec.gov.iess.hl.exception.RegistroCivilException;
import ec.gov.iess.hl.exception.ServicioPrestadoException;
import ec.gov.iess.hl.exception.SucursalException;
import ec.gov.iess.hl.modelo.Afiliado;
import ec.gov.iess.hl.modelo.DivisionPolitica;
import ec.gov.iess.hl.modelo.Empleador;
import ec.gov.iess.hl.modelo.RegistroCivil;
import ec.gov.iess.hl.modelo.ServicioPrestado;
import ec.gov.iess.hl.modelo.Sucursal;
import ec.gov.iess.hl.servicio.AfiliadoServicio;
import ec.gov.iess.hl.servicio.EmpleadorServicio;
import ec.gov.iess.hl.servicio.RegistroCivilServicio;
import ec.gov.iess.hl.servicio.ServicioPrestadoServicio;
import ec.gov.iess.hl.servicio.SucursalServicio;
import ec.gov.iess.pensiones.exception.PensionException;
import ec.gov.iess.servicio.pensiones.servicio.PrestacionServicio;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless
public class SolicitanteServicioImpl implements SolicitanteServicio, SolicitanteServicioRemote {

	LoggerBiess log = LoggerBiess.getLogger(SolicitanteServicioImpl.class);

	@EJB
	private AfiliadoServicio afiliadoServicio;

	@EJB
	private ServicioPrestadoServicio servicioPrestadoServicio;

	@EJB
	private SucursalServicio sucursalServicio;

	@EJB
	private CuentaBancariaAfiliadoServicio cuentaBancariaAfiliadoServicio;

	@EJB
	private PrestacionServicio prestacionServicio;

	@EJB
	private EmpleadorServicio empleadorServicio;

	@EJB
	private RegistroCivilServicio registroCivilServicio;

	@EJB
	private DatosPersonalesAfiliadoBiessServicio datosPersonalesAfiliadoBiessServicio;

	// INC-2148: Refugiados.
	@EJB
	private RegistroCivilExtranjeroServicio registroCivilExtranjeroServicio;
	
	// INC-2300 Requerimiento R24.
	@EJB
	private ClienteServicioLocal clienteServicioLocal;
	
	/**
	 * Obtiene datos del solicitante incluida informacion de cargas familiares
	 * 
	 * @param cedula
	 * @param tipoPrestamista
	 * @return
	 * @throws SolicitanteExcepcion
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Solicitante obtenerSolicitanteMasCargas(String cedula, TipoPrestamista tipoPrestamista)
			throws SolicitanteExcepcion {
		return obtenerSolicitanteMasCargas(cedula, tipoPrestamista, false);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitanteServicio#obtenerSolicitanteMasCargas(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista, boolean)
	 */
	public Solicitante obtenerSolicitanteMasCargas(String cedula, TipoPrestamista tipoPrestamista, boolean esProductoEmergente)
			throws SolicitanteExcepcion {
		Solicitante solicitante = obtenerSolicitante(cedula, tipoPrestamista, esProductoEmergente);
		/* Fijar numero de cargas del afiliado */
		solicitante.setNumeroCargas(datosPersonalesAfiliadoBiessServicio.consultarNumeroCargas(cedula));

		return solicitante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitanteServicio#obtenerSolicitante(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Solicitante obtenerSolicitante(String cedula, TipoPrestamista tipoPrestamista) throws SolicitanteExcepcion {
		return obtenerSolicitante(cedula, tipoPrestamista, false);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitanteServicio#obtenerSolicitante(java.lang.String,
	 * ec.gov.iess.creditos.enumeracion.TipoPrestamista, boolean)
	 */
	public Solicitante obtenerSolicitante(String cedula, TipoPrestamista tipoPrestamista, boolean esProductoEmergente) throws SolicitanteExcepcion {
		Solicitante solicitante = new Solicitante();

		DatosPersonales datosPersonales = new DatosPersonales();

		try {
			Afiliado afiliado = afiliadoServicio.consultarDatosAfiliado(cedula);

			// Datos personales
			datosPersonales.setCedula(cedula);
			DivisionPolitica parroquia = null;// afiliado.getDivisionPolitica();
			DivisionPolitica canton = null; // parroquia.getDivisionPolitica();
			DivisionPolitica provincia = null;// canton.getDivisionPolitica();

			parroquia = afiliado.getDivisionPolitica();

			canton = parroquia == null ? null : parroquia.getDivisionPolitica();

			provincia = canton == null ? null : canton.getDivisionPolitica();

			datosPersonales.setParroquiaId(parroquia == null ? "" : parroquia.getCoddivpol());
			datosPersonales.setCantonId(canton == null ? "" : canton.getCoddivpol());
			datosPersonales.setProvinciaId(provincia == null ? "" : provincia.getCoddivpol());

			datosPersonales.setDireccionDomicilio(afiliado.getDirafi() == null ? "" : afiliado.getDirafi().trim());
			datosPersonales.setEmail(afiliado.getMaiafi() == null ? "" : afiliado.getMaiafi().trim());

			RegistroCivil registroCivil = null;
			try {
				registroCivil = registroCivilServicio.consultarRegistroCivil(cedula);
				datosPersonales.setFechaNacimiento(registroCivil.getFecnacper());
			} catch (RegistroCivilException e) {
				throw new SolicitanteExcepcion("NO EXISTEN DATOS EN REGISTRO CIVIL: ".concat(cedula));
			} catch (NullPointerException e) {
				throw new SolicitanteExcepcion("NO EXISTEN DATOS EN REGISTRO CIVIL: ".concat(cedula));
			}

			datosPersonales.setEstadoCivil(registroCivil.getEstadoCivil().getDescripcion());

			datosPersonales.setEstadoCivilId(registroCivil.getEstadoCivil().getCodigo());

			datosPersonales.setFechaUltimaActualizacion(afiliado.getFecactafi());
			
			if (registroCivil.getCodGeneroLey()!=null && registroCivil.getCodGeneroLey().toString().equals(Genero.FEMENINO_CODLEY.getCodigo())) {
				datosPersonales.setGenero(Genero.FEMENINO_CODLEY);
				    } else if(registroCivil.getCodGeneroLey()!=null && registroCivil.getCodGeneroLey().toString().equals(Genero.MASCULINO_CODLEY.getCodigo())) {
				    	datosPersonales.setGenero(Genero.MASCULINO_CODLEY);
				   }else{
					   if (registroCivil.getGenper().equals(Genero.FEMENINO.getCodigo()))
							datosPersonales.setGenero(Genero.FEMENINO);
						else
							datosPersonales.setGenero(Genero.MASCULINO);
				   }

			datosPersonales.setNacionalidad(afiliado.getNacionalidad().getDesnac());
			
			// INC-2300 Requerimiento R24
			datosPersonales.setNacionalidadCodigo(afiliado.getNacionalidad().getCodnac());

			datosPersonales.setApellidosNombres(registroCivil.getNomper());

			datosPersonales.setTelefono(afiliado.getTelafi() == null ? "" : afiliado.getTelafi().trim());

			datosPersonales.setCelular(afiliado.getCelafi() == null ? "" : afiliado.getCelafi().trim());

			if (afiliado.getPosviv() == null) {
				datosPersonales.setPoseeVivienda(null);
			} else if (afiliado.getPosviv().equals("0")) {
				datosPersonales.setPoseeVivienda(false);
			} else {
				datosPersonales.setPoseeVivienda(true);
			}
	
			solicitante.setDatosPersonales(datosPersonales);

			solicitante.setCuentaBancaria(obtenerCuentaBancaria(cedula));

			if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista)) {

			}
			// TODO si es jubilado posetear origen

			// INC-2292 Mejora Refugiados.
			RegistroCivilExtranjero registroCivilExtranjero = this.obtenerDocumentoRefugiado(cedula);

			if (registroCivilExtranjero != null) {
				datosPersonales.setRegistroCivilExtranjero(registroCivilExtranjero);
			}
			// INC 2300 requerimiento R24. - Obtienen el cliente.
			Cliente cliente = this.obtenerCliente(cedula);
			if (cliente == null) {
				cliente = new Cliente();
				cliente.setFechaActualizacionPatrimonio(new Date());
			}
			solicitante.setCliente(cliente);

		} catch (ClienteExcepcion e) {
			throw new SolicitanteExcepcion(e.getMessage(), e);
		} catch (RegistroCivilExtranjeroException e) {
			throw new SolicitanteExcepcion(e.getMessage(), e);
		} catch (AfiliadoException e) {
			if (e instanceof AfiliadoNoEncontradoException) {
				throw new SolicitanteNoEncontradoExcepcion("Solicitante no encontrado");
			} else
				throw new SolicitanteExcepcion(e.getCause().getMessage(), e.getCause());
		}
		return solicitante;

	}

	/**
	 * M�todo que obtiene la cuenta bancaria del asegurado.
	 * 
	 * @param cedula
	 * @return
	 * @throws SolicitanteExcepcion
	 */
	private CuentaBancaria obtenerCuentaBancaria(String cedula)
			throws SolicitanteExcepcion {
		/* INC-1800. Control PDA para jubilados. Se consulta solamente ctas bancarias registradas en HL */
		CuentaBancariaAfiliado cuentaBancariaAfiliado = cuentaBancariaAfiliadoServicio.findCuentaValidaAfiliado(cedula);

		return cuentaBancariaAfiliado != null ? cuentaBancariaAfiliado.getCuentaBancaria() : null;
	}

	private ec.gov.iess.creditos.modelo.dto.Empleador obtenerEmpleadorJubilado(OrigenJubilado origenJubilado) {
		// El empleador de un jubilado es el IESS
		Empleador empleadorHl = empleadorServicio.getEmpleador(ConstantesCreditos.RUC_IESS);
		ec.gov.iess.creditos.modelo.dto.Empleador empleadorDelJubilado = new ec.gov.iess.creditos.modelo.dto.Empleador();
		empleadorDelJubilado.setEmpleadorActual(empleadorHl.getNomemp());
		// La sucursal depende del origen del jubilado
		empleadorDelJubilado.setCodigoSucursal(SucursalIessHelper.getSucursalJubilado(origenJubilado));
		empleadorDelJubilado.setNombreSucursal(empleadorHl.getNomemp());
		empleadorDelJubilado.setRucEmpleador(empleadorHl.getRucemp());
		empleadorDelJubilado.setTipoEmpresa(empleadorHl.getCodtipemp());
		return empleadorDelJubilado;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ec.gov.iess.creditos.pq.servicio.SolicitanteServicio#determinarEmpleador(
	 * java.lang.String, ec.gov.iess.creditos.enumeracion.TipoPrestamista,
	 * ec.gov.iess.creditos.modelo.dto.Solicitante, boolean)
	 */
	public void determinarEmpleador(String cedula, TipoPrestamista tipo, Solicitante solicitante, boolean esEmergente)
			throws SucursalException, ServicioPrestadoException, NoServicioPrestadoSucursalException {
		ec.gov.iess.creditos.modelo.dto.Empleador empleador = null;
		try {
			if (TipoPrestamista.AFILIADO.equals(tipo) || TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipo)) {
				empleador = obtenerEmpleador(cedula, tipo, esEmergente);
			} else if (TipoPrestamista.JUBILADO.equals(tipo)) {
				// Para jubilados solamente
				if (TipoPrestamista.JUBILADO.equals(tipo)) {
				solicitante.setOrigenJubilado(OrigenJubilado.HL);
				empleador = obtenerEmpleadorJubilado(solicitante.getOrigenJubilado());
				}
			}			
		} catch (DeterminarEmpleadorParaDescontarPrestamoException e) {
			throw new SucursalException(e.getMessage());
		}
		solicitante.setEmpleador(empleador);
	}

	@SuppressWarnings("rawtypes")
	private ec.gov.iess.creditos.modelo.dto.Empleador obtenerEmpleador(String cedula, TipoPrestamista tipo, boolean esProductoEmergente)
			throws ServicioPrestadoException, DeterminarEmpleadorParaDescontarPrestamoException, NoServicioPrestadoSucursalException {
		List<ServicioPrestado> listaServicioPrestado = null;
		Map resultado = null;
		ServicioPrestado servicioPrestado = null;

		// Datos empleador
		// Solo para afiliados

		if (TipoPrestamista.AFILIADO.equals(tipo)) {
			listaServicioPrestado = servicioPrestadoServicio.consultarActivoPorCedula(cedula);
		} else if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipo)) {
			List<String> relacionTrabajoList = new ArrayList<String>();
			relacionTrabajoList.add(String.valueOf(15));
			relacionTrabajoList.add(String.valueOf(55));
			listaServicioPrestado = servicioPrestadoServicio.consultaActivoPorCedulaRelacionTrabajo(cedula,
					relacionTrabajoList);
		}
		
		resultado = this.determinarEmpleador(listaServicioPrestado, esProductoEmergente, cedula);
		Sucursal sucursal = (Sucursal) resultado.get("sucursal");
		servicioPrestado = (ServicioPrestado) resultado.get("servicioPrestado");

		Empleador empleador = sucursal.getEmpleador();
		
		return llenarDatosEmpleadorPresentacion(servicioPrestado, sucursal, empleador);
	}

	/**
	 * Determina un epleador que no este en mora
	 * 
	 * @param listaServiciPrestado
	 * @param esPrestamoEmergente
	 * @return
	 * @throws SucursalException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	private Map determinarEmpleador(List<ServicioPrestado> listaServiciPrestado, boolean esProductoEmergente, String cedula)
			throws DeterminarEmpleadorParaDescontarPrestamoException, NoServicioPrestadoSucursalException {

		Map resultado = new HashMap();
		Sucursal sucursal = null;

		// Ordenamos la lista de servicios prestados

		 // Ordenamos la lista de servicios prestados
		if(listaServiciPrestado!=null) {
			Collections.sort(listaServiciPrestado, new Comparator<ServicioPrestado>() {
	            public int compare(ServicioPrestado o1, ServicioPrestado o2) {
	                // Comparamos los valores de los sueldos para organizarlos de mayor a menor
	                return o2.getSalafi().compareTo(o1.getSalafi());
	            }
	        });
			
	        for (ServicioPrestado servicioPrestado : listaServiciPrestado) {
	            try {	            	
	            	sucursal = sucursalServicio.consultarSucursal(servicioPrestado.getServicioPrestadoPk()
	                        .getRucEmpleador(), servicioPrestado.getServicioPrestadoPk().getCodigoSucursal());
	            } catch (SucursalException e) {
	                throw new DeterminarEmpleadorParaDescontarPrestamoException(e.getMessage(), e);
	            }
	           
	            resultado.put("sucursal", sucursal);
	            resultado.put("servicioPrestado", servicioPrestado);
	            log.info("Se va descontar a RUC " + sucursal.getSucursalPK().getRucemp());
	            break;
	        }
		}
        
		if (resultado.get("sucursal") == null || resultado.get("servicioPrestado") == null) {
			log.info("Datos de sucursal y servicio prestado null: " + cedula);
			RegistroCivil registroCivil = null;
			try {
				registroCivil = registroCivilServicio.consultarRegistroCivil(cedula);
			} catch (RegistroCivilException e) {
				throw new DeterminarEmpleadorParaDescontarPrestamoException(e.getMessage(), e);
			}
			String mensaje = String.format(Messages.getString("mensaje.afiliado.cesante"), registroCivil.getNomper(), cedula);
			throw new NoServicioPrestadoSucursalException(mensaje);
		}
		return resultado;
	}

	/**
	 * Determina la cedula del representante legal del establecimiento principal en caso de evaluar agencias o
	 * sucursales.
	 * 
	 * @param sucursal
	 * @return C�dula del Representante Legal.
	 */
	private String determinarCedulaRepresentanteLegal(Sucursal sucursal) throws SucursalException {
		if (sucursal == null)
			throw new SucursalException(
					"NO SE PUEDE DETERMINAR SI EL EMPLEADOR ESTA EN MORA PROQUE NO SE HA PODIDO ENCONTRAR LA C�DULA DEL REPRESENTANTE LEGAL. LA SUCURSAL ES NULL");
		if (sucursal.getCedrepleg() == null) {
			return determinarCedulaRepresentanteLegal(sucursal.getSucursalPadre());
		} else {
			return sucursal.getCedrepleg();
		}
	}

	private ec.gov.iess.creditos.modelo.dto.Empleador llenarDatosEmpleadorPresentacion(
			ServicioPrestado servicioPrestado, Sucursal sucursal, Empleador empleador) {
		ec.gov.iess.creditos.modelo.dto.Empleador empleadorPresentacion = new ec.gov.iess.creditos.modelo.dto.Empleador();
		empleadorPresentacion.setCodigoSucursal(sucursal.getSucursalPK().getCodsuc());
		empleadorPresentacion.setEmpleadorActual(empleador.getNomemp());
		empleadorPresentacion.setFechaAfiliacion(servicioPrestado.getFecingafi());
		empleadorPresentacion.setNombreSucursal(sucursal.getDessuc());
		empleadorPresentacion.setRucEmpleador(sucursal.getSucursalPK().getRucemp());
		empleadorPresentacion.setTipoEmpresa(servicioPrestado.getCodtipemp());
		return empleadorPresentacion;
	}

	/**
	 * Permite determinar en qu� nomina recibe la mayor pensi�n.
	 * 
	 * @author jvaca
	 * @param cedula
	 * @return
	 * @throws SolicitanteExcepcion
	 */
	// private TotalRentaPorNominaOrigen obtenerMayorRentaEnNomina(String cedula) {
	//
	// /*
	// * Se determian en qu� nomina recibe el mayor valor de renta sumando
	// * la renta de todas las prestaciones que percibe el jubilado. En donde
	// * reciba el mator valor, ese ser� su origen
	// */
	//
	// TotalRentaPorNominaOrigen totalRentaPorNominaOrigen = null;
	// totalRentaPorNominaOrigen = origenJubiladoServicio
	// .obtenerOrigenJubilado(cedula);
	// return totalRentaPorNominaOrigen;
	// }

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<TipoPrestamista> obtenerRoles(String cedula) throws ObtenerRolesExcepcion {

		List<TipoPrestamista> listaTipo = new ArrayList<TipoPrestamista>();
		// Determinamos si puede ingresar como afiliado
		boolean tieneRolesAfiliado = false;
		// tieneRolesAfiliado = tieneRol(TipoRol.AF.toString(), cedula);
		try {
			tieneRolesAfiliado = servicioPrestadoServicio.consultarEsActivoOCesante(cedula);
		} catch (ServicioPrestadoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Determinamos si puede ingresar como jubilado
		boolean tieneRolesJubilado = false;

		// tieneRolesJubilado = tieneRol(TipoRol.JU.toString(), cedula);
		try {
			tieneRolesJubilado = prestacionServicio.esJubilado(cedula);
		} catch (PensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Determinamos si puede ingresar como zafrero
		boolean tieneRolesZafreroTemporal = false;
		List<Integer> relacionTrabajoList = new ArrayList<Integer>();
		relacionTrabajoList.add(15);
		relacionTrabajoList.add(55);
		tieneRolesZafreroTemporal = servicioPrestadoServicio.afiliadoTrabajaEnEmpresaConRelacionDeTrabajo(
				relacionTrabajoList, cedula);

		if (tieneRolesAfiliado && !tieneRolesZafreroTemporal) {
			listaTipo.add(TipoPrestamista.AFILIADO);
		}

		if (tieneRolesJubilado) {
			listaTipo.add(TipoPrestamista.JUBILADO);
		}

		if (tieneRolesZafreroTemporal) {
			listaTipo.add(TipoPrestamista.ZAFRERO_TEMPORAL);
		}

		if (listaTipo.isEmpty()) {
			throw new ObtenerRolesExcepcion("NO SE HA PODIDO DETERMINAR SI SE TRATA DE UN JUBILADO O UN AFILIADO");
		}

		return listaTipo;
	}

	/** 
	 * @see ec.gov.iess.creditos.pq.servicio.SolicitanteServicio#validarCedulaRefugiado(java.lang.String)
	 */
	public boolean validarCedulaRefugiado(String cedulaRefugiado) {
		// INC-2292 Mejora Refugiados.
		if (cedulaRefugiado == null || cedulaRefugiado.trim().length() < 10) {
			return false;
		}

		String validarCedula = cedulaRefugiado.substring(0, 2);
		if (validarCedula.equals("61")) {
			return true;
		}

		return false;
	}	
	
	/**
	 * Obtienen informacion del documento de identidad registrado por un refugiado/extranjero.
	 * 
	 * @param cedula
	 *            - documento de identificacion.
	 * 
	 * @return <RegistroCivilExtranjero>.
	 * 
	 * @throws RegistroCivilExtranjeroExcepcion.
	 */
	public RegistroCivilExtranjero obtenerDocumentoRefugiado(String cedula) throws RegistroCivilExtranjeroException {

		RegistroCivilExtranjero registroCivilExtranjero = null;

		if (validarCedulaRefugiado(cedula)) {
			try {
				List<RegistroCivilExtranjero> listaDocumentos = this.registroCivilExtranjeroServicio
						.obtenerPorCedula(cedula);
				if (listaDocumentos != null && !listaDocumentos.isEmpty()) {
					Map<TipoDocumentoExtranjero, RegistroCivilExtranjero> documentos = new HashMap<TipoDocumentoExtranjero, RegistroCivilExtranjero>();
					for (RegistroCivilExtranjero extranjero : listaDocumentos) {
						documentos.put(extranjero.getTipoDocumento(), extranjero);
					}
					if (documentos.containsKey(TipoDocumentoExtranjero.PAS)) {
						return documentos.get(TipoDocumentoExtranjero.PAS);
					}
					if (documentos.containsKey(TipoDocumentoExtranjero.CDR)) {
						return documentos.get(TipoDocumentoExtranjero.CDR);
					}
					return documentos.get(TipoDocumentoExtranjero.CLB);
				}
			} catch (Exception e) {
				throw new RegistroCivilExtranjeroException(
						"No se pudo obtener informacion del documento de identificacion del refugiado/extranjero.", e);
			}
		}

		return registroCivilExtranjero;
	}
	
	/**
	 * Obtiene informacion de clientes.
	 * 
	 * @param cedula
	 *            - cedula del cliente.
	 * 
	 * @return Cliente.
	 * 
	 * @throws ClienteExcepcion
	 *             - excepcion.
	 */
	private Cliente obtenerCliente(String cedula) throws ClienteExcepcion {
		Cliente cliente = null;
		if (cedula != null && cedula.trim().length() > 0) {
			List<Cliente> listaClientes = this.clienteServicioLocal.obtenerPorCedula(cedula);
			if (listaClientes != null && !listaClientes.isEmpty()) {
				cliente = listaClientes.get(0);
			}
		}
		return cliente;
	}
	
}