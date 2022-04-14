package ec.gov.iess.creditos.pq.util;
import ec.gov.iess.creditos.enumeracion.OrigenJubilado;
import ec.gov.iess.creditos.enumeracion.SucursalIess;

public class SucursalIessHelper {
	public static String getSucursalJubilado(OrigenJubilado origen){
		if(OrigenJubilado.HL.equals(origen)){
			return SucursalIess.HL.getCodigo();
		}
		
		if(OrigenJubilado.HOGYE.equals(origen)){
			return SucursalIess.HOGYE.getCodigo();
		}
		
		if(OrigenJubilado.HOUIO.equals(origen)){
			return SucursalIess.HOUIO.getCodigo();
		}
		
		return null;
	}
}
