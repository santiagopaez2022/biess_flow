package ec.gov.iess.creditos.dao.impl;

import javax.ejb.Stateless;

import ec.gov.iess.creditos.dao.DestinoCreditoDao;
import ec.gov.iess.creditos.modelo.persistencia.DestinoCredito;
import ec.gov.iess.creditos.modelo.persistencia.pk.DestinoCreditoPk;
import ec.gov.iess.dao.ejb.GenericEjbDao;
/**
 * @author Ricardo Tituaña 03/10/2011
 * 
 */
@Stateless
public class DestinoCreditoDaoImpl extends GenericEjbDao<DestinoCredito, DestinoCreditoPk> implements
DestinoCreditoDao{

	public  DestinoCreditoDaoImpl() {
		super(DestinoCredito.class);
		
	}
	
	/*public void guardardestinocredito(DestinoCredito d){
		//this.insert(d);
		

			String sql1="insert into cre_destino (NUMPREAFI,ORDPREAFI,CODPRETIP,CODPRECLA,COPQ_ID,DPQ_OBSERVACION,DPQ_FEC_REGISTRO)" +
				"values(:numpreafi,:ordpreafi,:codpretip,:codprecla,:copqid,:observacion,:fecha)";
		
			Query query = em.createNativeQuery(sql1);
			query.setParameter("numpreafi", d.getPk().getCodPreAfi());
			query.setParameter("codprecla", d.getPk().getCodPreCla());
			query.setParameter("codpretip", d.getPk().getCodPreTip());
			query.setParameter("ordpreafi", d.getPk().getOrdPreAfi());
			query.setParameter("copqid", d.getPk().getCopqid());
			query.setParameter("observacion", d.getObservacion());
			query.setParameter("fecha", d.getFechaRegistro());
			query.executeUpdate();

	}*/
	
}
