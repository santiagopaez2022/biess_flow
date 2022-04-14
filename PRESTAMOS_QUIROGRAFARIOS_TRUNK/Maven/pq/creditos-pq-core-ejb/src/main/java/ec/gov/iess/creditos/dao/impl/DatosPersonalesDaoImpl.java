/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR 
 * Todos los derechos reservados
 */
package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DatosPersonalesDao;
import ec.gov.iess.creditos.modelo.persistencia.DatosPersonalesAfiliado;
import ec.gov.iess.dao.ejb.GenericEjbDao;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 * 
 * @author jsanchez
 * @version $Revision: 1.0 $
 *          <p>
 *          [$Author: jsanchez $, $Date: 03/10/2011 $]
 *          </p>
 */
@Stateless
public class DatosPersonalesDaoImpl extends GenericEjbDao<DatosPersonalesAfiliado, Long>
		implements DatosPersonalesDao {
	
	private static final LoggerBiess log = LoggerBiess.getLogger(DatosPersonalesDaoImpl.class);

	public DatosPersonalesDaoImpl() {
		super(DatosPersonalesAfiliado.class);
		
	}

	

	
	
	public void actualizarDatosUsuarios(DatosPersonalesAfiliado datosPersonalesUsuario)
	{
		
		
		String sql = "select * from CRE_DATOSPERSONALES_TBL t where t.dp_cedula=:cedula";
		Query query =em.createNativeQuery(sql);
		query.setParameter("cedula", datosPersonalesUsuario.getCedula());
		if(query.getResultList().size()!=0 )
		{		
			String sql3="update CRE_DATOSPERSONALES_TBL t set " +
			 "t.DP_NOMBRE= :nombre,t.DP_CODDIVPOL= :codDivPol,t.DP_DIRECCION= :direccion,t.DP_TELEFONO= :telefono," +
			 "t.DP_CELULAR_REF = :celular,t.DP_GENERO= :genero,t.DP_NACIONALIDAD= :nacionalidad,t.DP_FECHANACIMIENTO= :fechanacimiento," +
			 "t.DP_POSEEVIVIENDA= :vivienda,t.DP_PROVINCIA= :provincia,t.DP_CANTON= :canton,t.DP_PARROQUIA= :parroquia,t.DP_CARGAFAMILIAR= :cargaFamiliar,t.DP_ESTADOCIVIL= :estadoCivil,t.DP_EMAIL= :email,t.DP_ULTIMAFECHACTUALIZACION= :ultimaFecha " +
  			 "where t.DP_CEDULA= :cedula";
			Query query1 =em.createNativeQuery(sql3);
			query1.setParameter("nombre",datosPersonalesUsuario.getNombre());
			query1.setParameter("codDivPol",datosPersonalesUsuario.getCodDivPol());
			query1.setParameter("direccion",datosPersonalesUsuario.getDireccion());
			query1.setParameter("telefono",datosPersonalesUsuario.getTelefono());
			query1.setParameter("celular",datosPersonalesUsuario.getCelular());
			query1.setParameter("genero",datosPersonalesUsuario.getGenero());
			query1.setParameter("nacionalidad",datosPersonalesUsuario.getNacionalidad());
			query1.setParameter("fechanacimiento",datosPersonalesUsuario.getFechanacimiento());
			query1.setParameter("vivienda",datosPersonalesUsuario.getpVivienda());
			query1.setParameter("provincia",datosPersonalesUsuario.getProvincia());
			query1.setParameter("canton",datosPersonalesUsuario.getCanton());
			query1.setParameter("parroquia",datosPersonalesUsuario.getParroquia());
			query1.setParameter("cargaFamiliar",datosPersonalesUsuario.getCargaFamiliar());
			query1.setParameter("estadoCivil",datosPersonalesUsuario.getEstadoCivil());
			query1.setParameter("email",datosPersonalesUsuario.getEmail());
			query1.setParameter("ultimaFecha",datosPersonalesUsuario.getUltimaFecha());
			query1.setParameter("cedula",datosPersonalesUsuario.getCedula());
			
			
			query1.executeUpdate();
		}
		else
			this.insert(datosPersonalesUsuario);
	
		
		
				
		
	}
}
