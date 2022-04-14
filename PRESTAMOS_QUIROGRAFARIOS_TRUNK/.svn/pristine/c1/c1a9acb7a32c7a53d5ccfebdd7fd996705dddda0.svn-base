/**
 * 
 */
package ec.gov.iess.creditos.dinamico.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Paul.Sampedro <paul.sampedro@biess.fin.ec>
 *
 */
public class DataRespGenericaResponseDto extends RespuestaError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234404042240237037L;
	
	/**
	 * Total del valor a financiar enviada desde el servicio
	 */
	private BigDecimal totalFinanciar;

	/**
	 * Codigo categoria PREGRADO POSTGRADO,ETC
	 */
	private String codigoCategoria;

	/**
	 * Datos de cabecera que se mostrara en el pantalla
	 */
	private List<Campo> header;

	/**
	 * Datos de cuerpo que se mostrara en la la pantalla
	 */
	private List<Campo> body;

	/**
	 * Proveedor asociado a la peticion realizada
	 */
	private ProveedorResponseDto proveedor;

	public BigDecimal getTotalFinanciar() {
		return totalFinanciar;
	}

	public void setTotalFinanciar(final BigDecimal totalFinanciar) {
		this.totalFinanciar = totalFinanciar;
	}

	public List<Campo> getHeader() {
		return header;
	}

	public void setHeader(final List<Campo> header) {
		this.header = header;
	}

	public List<Campo> getBody() {
		return body;
	}

	public void setBody(final List<Campo> body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "DataRespGenericaResponseDto [totalFinanciar=" + totalFinanciar + ", header=" + header.toString()
				+ ", body=" + body.toString() + "]";
	}

	public ProveedorResponseDto getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorResponseDto proveedor) {
		this.proveedor = proveedor;
	}
	
	public String getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

}
