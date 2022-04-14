/*
 * Copyright 2011 INSTITUTO ECUATORIANO DE SEGURIDAD SOCIAL - ECUADOR.
 * Todos los derechos reservados.
 */
package ec.gov.iess.creditos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ec.gov.biess.util.log.LoggerBiess;
import ec.gov.iess.creditos.dao.DividendoPrestamoHistoricoDao;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamo;
import ec.gov.iess.creditos.modelo.persistencia.DividendoPrestamoHistorico;
import ec.gov.iess.creditos.modelo.persistencia.pk.DividendoPrestamoHistoricoPK;
import ec.gov.iess.dao.ejb.GenericEjbDao;

 
 /**
 * 
 * <b> Representacion que permite manejo de historico del prestamo del dividendo
 * 
 * @author Ricardo Tituaña
 * @version $Revision: 1.2 $
 *          <p>
 *          [$Author: dimbacuanl $, $Date: 2011/10/03 $]
 *          </p>
 */
 
@Stateless
public class DividendoPrestamoHistoricoDaoImpl
extends GenericEjbDao<DividendoPrestamoHistorico, DividendoPrestamoHistoricoPK> 
implements DividendoPrestamoHistoricoDao{

	LoggerBiess log = LoggerBiess.getLogger(DividendoPrestamoHistoricoDaoImpl.class);
	
	
	private static final String consultaParaBuscarPorPKDividendoPrestamo= 
		"SELECT dph FROM DividendoPrestamoHistorico as dph " +
		"WHERE dph.dividendoPrestamoHistoricoPK.numdiv=:ainnumdiv " +
		"AND dph.dividendoPrestamoHistoricoPK.numpreafi=:ainnumpreafi " +
        "AND dph.dividendoPrestamoHistoricoPK.ordpreafi=:ainordpreafi " +
        "AND dph.dividendoPrestamoHistoricoPK.codpretip=:aincodpretip " +
        "AND dph.dividendoPrestamoHistoricoPK.codprecla=:aincodprecla " +
        "AND dph.dividendoPrestamoHistoricoPK.codestdiv=:aiccodestantdiv " +
        "AND dph.fecfin IS NULL " ;
	private static final String consultaParaBuscarTodosDividendoHist= 
		"SELECT dph FROM DividendoPrestamoHistorico as dph " +
		"WHERE dph.dividendoPrestamoHistoricoPK.numdiv=:ainnumdiv " +
		"AND dph.dividendoPrestamoHistoricoPK.numpreafi=:ainnumpreafi " +
        "AND dph.dividendoPrestamoHistoricoPK.ordpreafi=:ainordpreafi " +
        "AND dph.dividendoPrestamoHistoricoPK.codpretip=:aincodpretip " +
        "AND dph.dividendoPrestamoHistoricoPK.codprecla=:aincodprecla ";
	
	
	public DividendoPrestamoHistoricoDaoImpl() {
		super(DividendoPrestamoHistorico.class);
	}

	/**
	 *  @see ec.gov.iess.creditos.dao.DividendoPrestamoHistoricoDao#contarPorSeleccionEspecifica(DividendoPrestamo)
	 *  
	 */
	public Long contarPorSeleccionEspecifica(DividendoPrestamo dividendoPrestamo) {
		Long contadorResultado = 0l;	
		String consultaParaContarPorSeleccionEspecifica = 
			"SELECT COUNT(*) FROM DividendoPrestamoHistorico dph " +
			"WHERE dph.dividendoPrestamoHistoricoPK.numdiv=:ainnumdiv " +
			"AND dph.dividendoPrestamoHistoricoPK.numpreafi=:ainnumpreafi " +
	        "AND dph.dividendoPrestamoHistoricoPK.ordpreafi=:ainordpreafi " +
	        "AND dph.dividendoPrestamoHistoricoPK.codpretip=:aincodpretip " +
	        "AND dph.dividendoPrestamoHistoricoPK.codprecla=:aincodprecla " +
	        "AND dph.dividendoPrestamoHistoricoPK.codestdiv=:aiccodestantdiv " +
	        "AND dph.fecfin IS NULL " ;		
		Query query=(Query) em.createQuery(consultaParaContarPorSeleccionEspecifica);
		query.setParameter("ainnumdiv", dividendoPrestamo.getDividendoPrestamoPk().getNumdiv().longValue());
		query.setParameter("ainnumpreafi", dividendoPrestamo.getDividendoPrestamoPk().getNumpreafi());
		query.setParameter("ainordpreafi", dividendoPrestamo.getDividendoPrestamoPk().getOrdpreafi());
		query.setParameter("aincodpretip", dividendoPrestamo.getDividendoPrestamoPk().getCodpretip());
		query.setParameter("aincodprecla", dividendoPrestamo.getDividendoPrestamoPk().getCodprecla());
		query.setParameter("aiccodestantdiv", dividendoPrestamo.getEstadoDividendoPrestamo().getCodestdiv());
		List<DividendoPrestamoHistorico> resultado=(List<DividendoPrestamoHistorico>) query.getResultList();	
		contadorResultado = (long)resultado.size();
		return contadorResultado;
	}
	
	
	public List<DividendoPrestamoHistorico> obtenerUltimosHistoricosDividendo(DividendoPrestamo dividendoPrestamo) {
		List<DividendoPrestamoHistorico> listaHistoricos=new ArrayList<DividendoPrestamoHistorico>();
		String sql = "SELECT dph FROM DividendoPrestamoHistorico dph " +
		"WHERE dph.dividendoPrestamoHistoricoPK.numdiv=:ainnumdiv " +
		"AND dph.dividendoPrestamoHistoricoPK.numpreafi=:ainnumpreafi " +
		"AND dph.dividendoPrestamoHistoricoPK.ordpreafi=:ainordpreafi " +
		"AND dph.dividendoPrestamoHistoricoPK.codpretip=:aincodpretip " +
		"AND dph.dividendoPrestamoHistoricoPK.codprecla=:aincodprecla " +
		"AND dph.fecfin IS NULL ";		
		Query query=(Query) em.createQuery(sql);
		query.setParameter("ainnumdiv", dividendoPrestamo.getDividendoPrestamoPk().getNumdiv().longValue());
		query.setParameter("ainnumpreafi", dividendoPrestamo.getDividendoPrestamoPk().getNumpreafi());
		query.setParameter("ainordpreafi", dividendoPrestamo.getDividendoPrestamoPk().getOrdpreafi());
		query.setParameter("aincodpretip", dividendoPrestamo.getDividendoPrestamoPk().getCodpretip());
		query.setParameter("aincodprecla", dividendoPrestamo.getDividendoPrestamoPk().getCodprecla());
		try{
			listaHistoricos =query.getResultList();
		}catch (NoResultException e){
			log.info("No se encontró el dividendo a actualizar al buscar el historico a actualizar...");
		}				
		return listaHistoricos;
	}
	
	public void cambiarFinfecYobstraPorDividendoPrestamoHistorico(Date fecfin, String obstra, DividendoPrestamoHistorico dividendoPrestamoHistorico){
		String consultacambiarFinfecYobstraPorSeleccionEspecifica = 
			"UPDATE DividendoPrestamoHistorico dph " +
			"set dph.fecfin=:fecfin, dph.obstra=:obstra " +
			"WHERE dph.dividendoPrestamoHistoricoPK.numdiv=:ainnumdiv " +
			"AND dph.dividendoPrestamoHistoricoPK.numpreafi=:ainnumpreafi " +
			"AND dph.dividendoPrestamoHistoricoPK.fecfin is null " +
	        "AND dph.dividendoPrestamoHistoricoPK.ordpreafi=:ainordpreafi " +
	        "AND dph.dividendoPrestamoHistoricoPK.codpretip=:aincodpretip " +
	        "AND dph.dividendoPrestamoHistoricoPK.codprecla=:aincodprecla " +
	        "AND dph.dividendoPrestamoHistoricoPK.codestdiv=:aiccodestantdiv ";
		String nativeConsultacambiarFinfecYobstraPorSeleccionEspecifica=
			"UPDATE KSCRETDIVPREESTHIS dph " +
			"set dph.fecfin=:fecfin, dph.obstra=:obstra " +
			"WHERE dph.numdiv=:ainnumdiv " +
			"AND dph.numpreafi=:ainnumpreafi " +
			"AND dph.fecfin is null " +
	        "AND dph.ordpreafi=:ainordpreafi " +
	        "AND dph.codpretip=:aincodpretip " +
	        "AND dph.codprecla=:aincodprecla " +
	        "AND dph.codestdiv=:aiccodestantdiv ";
		String sql="UPDATE KSCRETDIVPREESTHIS dph set dph.obstra='asdfsdf'  " +
				" WHERE  NUMPREAFI=920772 AND " +
				"NUMDIV=34 and codpretip=1 and " +
				"codprecla=20 and ordpreafi=1 " +
				"and codestdiv='REG'AND fecfin is null";
		em.createNativeQuery(sql).executeUpdate();
	}
	
	public DividendoPrestamoHistorico paraBuscarPorPKDividendoPrestam(DividendoPrestamo dividendoPrestamo) {
		DividendoPrestamoHistorico dividendoPrestamoHistorico;		
		Query query=(Query) em.createQuery(consultaParaBuscarPorPKDividendoPrestamo);
		query.setParameter("ainnumdiv", dividendoPrestamo.getDividendoPrestamoPk().getNumdiv());
		query.setParameter("ainnumpreafi", dividendoPrestamo.getDividendoPrestamoPk().getNumpreafi());
		query.setParameter("ainordpreafi", dividendoPrestamo.getDividendoPrestamoPk().getOrdpreafi());
		query.setParameter("aincodpretip", dividendoPrestamo.getDividendoPrestamoPk().getCodpretip());
		query.setParameter("aincodprecla", dividendoPrestamo.getDividendoPrestamoPk().getCodprecla());
		query.setParameter("aiccodestantdiv", dividendoPrestamo.getEstadoDividendoPrestamo().getCodestdiv());
		dividendoPrestamoHistorico = (DividendoPrestamoHistorico)query.getSingleResult();		
		return dividendoPrestamoHistorico;
	}
	
	/**
	 * 
	 * <b> Lista del préstamos historico </b>
	 * <p>
	 * [Author: Ricardo Tituana, Date: 19/04/2011]
	 * </p>
	 * 
	 * @param dividendoPrestamo
	 *            : dividendo del prestamo
	 * 
	 * @return List<DividendoPrestamoHistorico>: lista de historicos del dividendo
	 */
	public List<DividendoPrestamoHistorico> paraBuscarTodosDividendoHist(DividendoPrestamo dividendoPrestamo) {
		List<DividendoPrestamoHistorico> listdividendoPrestamoHistorico;		
		Query query=(Query) em.createQuery(consultaParaBuscarTodosDividendoHist);
		query.setParameter("ainnumdiv", dividendoPrestamo.getDividendoPrestamoPk().getNumdiv());
		query.setParameter("ainnumpreafi", dividendoPrestamo.getDividendoPrestamoPk().getNumpreafi());
		query.setParameter("ainordpreafi", dividendoPrestamo.getDividendoPrestamoPk().getOrdpreafi());
		query.setParameter("aincodpretip", dividendoPrestamo.getDividendoPrestamoPk().getCodpretip());
		query.setParameter("aincodprecla", dividendoPrestamo.getDividendoPrestamoPk().getCodprecla());		
		listdividendoPrestamoHistorico = (List<DividendoPrestamoHistorico>)query.getResultList();		
		return listdividendoPrestamoHistorico;
	}

}
