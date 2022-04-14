package ec.gov.iess.creditos.pq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO con informacion de respuesta de listado de creditos exigibles, si esta en
 * mora y otros datos
 * 
 * @author hugo.mora
 * @date 03/09/2018
 *
 */
public class InformacionPQExigible implements Serializable {

	private static final long serialVersionUID = 21210547899600056L;

	private List<CreditoExigibleDto> listaCreditosExigible;

	private boolean tieneMora;

	private BigDecimal valorSaldoCreditos;
	
	private BigDecimal valorSaldosEmp;

	private BigDecimal valorEndeudamientoPQ;

	public CreditoExigibleDto obtenerCreditoPorNut(Long nut) {
		if (nut == null) {

			return null;
		} else {
			for (CreditoExigibleDto creditoExigible : listaCreditosExigible) {
				if (creditoExigible.getNut().equals(nut)) {
					return creditoExigible;
				}
			}
		}
		return null;
	}
	
	public CreditoExigibleDto obtenerCreditoPorOperacion(Long operacion) {
		if (operacion == null) {

			return null;
		} else {
			for (CreditoExigibleDto creditoExigible : listaCreditosExigible) {
				if (creditoExigible.getOperacionSAC().equals(operacion)) {
					return creditoExigible;
				}
			}
		}
		return null;
	}
	

	// Getters and setters
	public List<CreditoExigibleDto> getListaCreditosExigible() {
		return listaCreditosExigible;
	}

	public void setListaCreditosExigible(List<CreditoExigibleDto> listaCreditosExigible) {
		this.listaCreditosExigible = listaCreditosExigible;
	}

	public boolean isTieneMora() {
		return tieneMora;
	}

	public void setTieneMora(boolean tieneMora) {
		this.tieneMora = tieneMora;
	}

	public BigDecimal getValorSaldoCreditos() {
		return valorSaldoCreditos;
	}

	public void setValorSaldoCreditos(BigDecimal valorSaldoCreditos) {
		this.valorSaldoCreditos = valorSaldoCreditos;
	}

	public BigDecimal getValorEndeudamientoPQ() {
		return valorEndeudamientoPQ;
	}

	public void setValorEndeudamientoPQ(BigDecimal valorEndeudamientoPQ) {
		this.valorEndeudamientoPQ = valorEndeudamientoPQ;
	}

	public BigDecimal getValorSaldosEmp() {
		return valorSaldosEmp;
	}

	public void setValorSaldosEmp(BigDecimal valorSaldosEmp) {
		this.valorSaldosEmp = valorSaldosEmp;
	}

}
