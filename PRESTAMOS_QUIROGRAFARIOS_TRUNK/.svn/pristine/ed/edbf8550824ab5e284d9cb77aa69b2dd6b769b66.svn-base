/*
 * © Banco del Instituto Ecuatoriano de Seguridad Social 2020
 */
package ec.gov.iess.creditos.pq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ec.fin.biess.creditos.pq.enumeracion.TipoIdentificacionSacEnum;
import ec.gov.iess.creditos.enumeracion.EstadoPrestamoEnum;
import ec.gov.iess.creditos.enumeracion.TipoCuenta;
import ec.gov.iess.creditos.enumeracion.TipoPrestamista;
import ec.gov.iess.creditos.enumeracion.TipoTablaEnum;
import ec.gov.iess.creditos.modelo.dto.DatosCredito;
import ec.gov.iess.creditos.pq.dto.ClienteRequestDto;

/**
 * Contiene funciones utilitarias para obtener informacion de SAC.
 *
 * @author hugo.mora
 * @date 2018/09/04
 */
public final class FuncionesUtilesSac {

	/** Constante FORMATO_FECHA_SAC. */
	private static final String FORMATO_FECHA_SAC = "yyyy.MM.dd";

	/**
	 * Instantiates a new funciones utiles sac.
	 */
	private FuncionesUtilesSac() {
		// Clase utilitaria no se instancia
	}

	/**
	 * Indica el tipo de identificacion requerido por los servicios del SAC.
	 *
	 * @param numeroIdentificacion the numero identificacion
	 * @return the string
	 */
	public static String obtenerTipoIdentificacionSac(final String numeroIdentificacion) {
		String tipoIdentificacion = TipoIdentificacionSacEnum.CEDULA.getCodigo();
		final String validarCedula = numeroIdentificacion.substring(0, 2);
		if (validarCedula.equals("61")) {
			tipoIdentificacion = TipoIdentificacionSacEnum.REFUGIADO.getCodigo();
		}

		return tipoIdentificacion;
	}

	/**
	 * Dado el String de fecha del SAC en formato aaaa.mm.dd lo transforma a Date
	 *
	 * @param fechaDato Fecha desde SAC en formato aaaa.mm.dd
	 * @return Devuelve un tipo Date
	 * @throws ParseException the parse exception
	 */
	public static Date obtenerFechaSac(final String fechaDato) throws ParseException {
		final SimpleDateFormat sdf2 = new SimpleDateFormat(FORMATO_FECHA_SAC);
		return  sdf2.parse(fechaDato);
		
	}

	/**
	 * Dado la fecha en Date transforma en formato aaa.mm.dd string
	 *
	 * @param fechaDato the fecha dato
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String obtenerFechaSac(final Date fechaDato) throws ParseException {
		final SimpleDateFormat sdf2 = new SimpleDateFormat(FORMATO_FECHA_SAC);
		final String fecha = sdf2.format(fechaDato);
		return fecha;
	}

	/**
	 * Permite verificar si es ultimo dia del mes.
	 *
	 * @return true, if successful
	 */
	public static boolean hoyEsUltimoDiaDelMes() {

		final Calendar calendar = Calendar.getInstance();

		final int dia = calendar.get(Calendar.DAY_OF_MONTH);

		if (dia == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			return true;

		} else {

			return false;
		}

	}

	/**
	 * obtiene le codigo a enviar al sac partir de datos de pq.
	 *
	 * @param tipoTablaPq the tipo tabla pq
	 * @return the string
	 */
	public static String obtenerTipoTablaSac(final String tipoTablaPq) {
		final TipoTablaEnum tipo = TipoTablaEnum.valueOf(tipoTablaPq);
		String codigoSacTabla = null;
		if (TipoTablaEnum.FRANCESA.equals(tipo)) {
			codigoSacTabla = "FRA";
		} else if (TipoTablaEnum.ALEMANA.equals(tipo)) {
			codigoSacTabla = "ALE";
		}

		return codigoSacTabla;

	}

	/**
	 * Obtener tipo cuenta sac.
	 *
	 * @param tipoCuentaPq the tipo cuenta pq
	 * @return the string
	 */
	public static String obtenerTipoCuentaSac(final String tipoCuentaPq) {
		String tipoCuentaSac = null;
		final TipoCuenta tipo = TipoCuenta.valueOf(tipoCuentaPq);
		if (TipoCuenta.AHO.equals(tipo)) {
			tipoCuentaSac = "AH";
		} else if (TipoCuenta.COR.equals(tipo)) {
			tipoCuentaSac = "CC";
		}
		return tipoCuentaSac;

	}

	/**
	 * Permite obtener la descripcion del estado de un prestamo.
	 *
	 * @param estado the estado
	 * @return the string
	 */
	public static String obtenerEstadoPrestamo(final String estado) {
		String descripcionEstado = null;
		for (final EstadoPrestamoEnum valor : EstadoPrestamoEnum.values()) {
			if (estado.equals(valor.name())) {
				descripcionEstado = valor.getEstado();
				break;
			}
		}
		return descripcionEstado;
	}

	/**
	 * Permite construir la entidad cliente que va a ser enviada en la peticion al
	 * core SAC.
	 *
	 * @param credito the credito
	 * @return the cliente request dto
	 * @throws ParseException the parse exception
	 */
	public static ClienteRequestDto fabricarCliente(final DatosCredito credito) throws ParseException {
		final ClienteRequestDto clienteRequestDto = new ClienteRequestDto();
		clienteRequestDto.setFechaNacimiento(obtenerFechaSac(credito.getFechaNacimiento()));
		clienteRequestDto.setNombre(credito.getNombreAsegurado());
		clienteRequestDto.setNumeroDocumento(credito.getCedulaAfiliado());
		clienteRequestDto.setTipoDocumento(obtenerTipoIdentificacionSac(credito.getCedulaAfiliado()));
		clienteRequestDto.setTipoCliente(obtenerTipoPrestamista(credito.getTipoPrestamista()));
		return clienteRequestDto;
	}

	/**
	 * Permite validar el tipo de prestamista.
	 *
	 * @param tipoPrestamista the tipo prestamista
	 * @return the string
	 */
	public static String obtenerTipoPrestamista(final TipoPrestamista tipoPrestamista) {
		String tipo = null;
		if (TipoPrestamista.AFILIADO.equals(tipoPrestamista)) {
			tipo = ConstantesSAC.AFILIADO;
		} else if (TipoPrestamista.AFILIADO_JUBILADO.equals(tipoPrestamista)
				|| TipoPrestamista.JUBILADO.equals(tipoPrestamista)) {
			tipo = ConstantesSAC.AFILIADO_JUBILADO;
		} else if (TipoPrestamista.ZAFRERO_TEMPORAL.equals(tipoPrestamista)) {
			tipo = ConstantesSAC.ZAFRERO_TEMPORAL;
		}
		return tipo;
	}

	/**
	 * Funcion que permite validar los tres errores internos del aplicativo para
	 * poder asignar el mensaje a mostrar en el aplicativo.
	 *
	 * @param codigo  the codigo
	 * @param mensaje the mensaje
	 * @return the string
	 */
	public static String asignarMensaje(final String codigo, final String mensaje) {
		String mensajeAplicativo;
		if (ConstantesSAC.COD_VALOR_NULO.equals(codigo) || ConstantesSAC.COD_VALOR_VACIO.equals(codigo)
				|| ConstantesSAC.COD_PERDIDA_RESPUESTA.equals(codigo)) {
			mensajeAplicativo = mensaje;
		} else if(ConstantesSAC.COD_MNT_MINIMO.equals(codigo) || "1".equals(codigo) ){
			mensajeAplicativo=ConstantesSAC.MENSAJE_MONTO_MINIMO;
		} else {
			mensajeAplicativo = ConstantesSAC.MENSAJE_GENERICO_ERROR_SAC;
		}
		return mensajeAplicativo;
	}
}
