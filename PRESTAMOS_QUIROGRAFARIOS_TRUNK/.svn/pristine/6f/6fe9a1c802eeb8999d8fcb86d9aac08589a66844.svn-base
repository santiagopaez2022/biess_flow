package ec.gov.iess.creditos.pq.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.DatosPersonalesDao;
import ec.gov.iess.creditos.modelo.dto.Solicitante;
import ec.gov.iess.creditos.modelo.persistencia.DatosPersonalesAfiliado;
import ec.gov.iess.creditos.pq.servicio.DatosPersonalesUsuarioServicio;
import ec.gov.iess.creditos.pq.servicio.DatosPersonalesUsuarioServicioRemote;
@Stateless
public class DatosPersonalesUsuarioServicioImpl implements DatosPersonalesUsuarioServicio,
DatosPersonalesUsuarioServicioRemote {
	@EJB
	private DatosPersonalesDao datosPersonalesUsuarioDao;
	public void actualizarUsuario(Solicitante solicitante)
	{
		
		DatosPersonalesAfiliado datosPersonalesUsuario=new DatosPersonalesAfiliado();
		datosPersonalesUsuario.setCedula(solicitante.getDatosPersonales().getCedula());
		datosPersonalesUsuario.setNombre(solicitante.getDatosPersonales().getApellidosNombres());
		datosPersonalesUsuario.setDireccion(solicitante.getDatosPersonales().getDireccionDomicilio());
		datosPersonalesUsuario.setTelefono(solicitante.getDatosPersonales().getTelefono());
		datosPersonalesUsuario.setNacionalidad(solicitante.getDatosPersonales().getNacionalidad());
		//datosPersonalesUsuario.setGenero(solicitante.getDatosPersonales().getGenero());
		datosPersonalesUsuario.setFechanacimiento(solicitante.getDatosPersonales().getFechaNacimiento());
		datosPersonalesUsuario.setEstadoCivil( solicitante.getDatosPersonales().getEstadoCivil());
		datosPersonalesUsuario.setCelular(solicitante.getDatosPersonales().getCelular());
		datosPersonalesUsuario.setEmail(solicitante.getDatosPersonales().getEmail());
		datosPersonalesUsuario.setCanton(solicitante.getDatosPersonales().getCantonId());
		datosPersonalesUsuario.setParroquia(solicitante.getDatosPersonales().getParroquiaId());
		datosPersonalesUsuario.setProvincia(solicitante.getDatosPersonales().getProvinciaId());
		if(solicitante.getDatosPersonales().getPoseeVivienda())
			datosPersonalesUsuario.setpVivienda("SI");
		else
			datosPersonalesUsuario.setpVivienda("NO");
		datosPersonalesUsuario.setCargaFamiliar(solicitante.getNumeroCargas());
		datosPersonalesUsuario.setUltimaFecha(solicitante.getDatosPersonales().getFechaUltimaActualizacion());
		datosPersonalesUsuarioDao.actualizarDatosUsuarios(datosPersonalesUsuario);
	}

}
