package ec.fin.biess.unlock.excepciones;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Clase que maneja las excepciones de unlock-ejb
 * 
 * @author edwin.maza
 * 
 */
public class UnlockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2980427646687870393L;
	private String codigoExcepcion;
	private String mensaje;
	private List<String> listaMensajes = new ArrayList<String>();

	public UnlockException() {
		super();
	}

	public UnlockException(String codigoExcepcion, String... argumentosMessage) {
		this(getMensaje(codigoExcepcion, argumentosMessage));
		this.codigoExcepcion = codigoExcepcion;
	}

	public UnlockException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
		listaMensajes.add(mensaje);
	}

	private static String getMensaje(String codigoExcepcion, String... argumentosMessage) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ec.fin.biess.unlock.resources.mensajes");
		String mensaje = resourceBundle.getString(codigoExcepcion);
		if (argumentosMessage != null) {
			MessageFormat messageFormat = new MessageFormat("");
			messageFormat.applyPattern(mensaje);
			mensaje = messageFormat.format(argumentosMessage);
		}
		return mensaje;
	}

	/*
	 * Getters and Setters
	 */
	public String getCodigoExcepcion() {
		return codigoExcepcion;
	}

	public void setCodigoExcepcion(String codigoExcepcion) {
		this.codigoExcepcion = codigoExcepcion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<String> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<String> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

}
