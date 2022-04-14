package ec.gov.iess.planillaspq.web.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class UtilReport extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected <T> void report(final Map<String, Object> map,
			final List<T> list, final String fileName) throws JRException,
			IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);

		String ctxPath = getServletContext().getRealPath("/");
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				ctxPath.concat("reportes/").concat(fileName).concat(".jasper"),
				map, jrDataSource);
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		String header = "attachment; filename=\"" + fileName + ".pdf\"";
		response.setHeader("Content-Disposition", header);
		response.setContentType("application/pdf");
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				response.getOutputStream());
		context.responseComplete();

	}

	/**
	 * Funcion para exportar a excel
	 * 
	 * @param <T>
	 * @param map
	 * @param list
	 * @param fileName
	 * @throws Exception
	 */
	protected <T>  void exportXlsReport(final Map<String, Object> map,
			final List<T> list, final String fileName) throws Exception {
		try {
			String ctxPath = getServletContext().getRealPath("/");
			JRDataSource cds = new JRBeanCollectionDataSource(list);
			JasperPrint reportJasperPrint = null;
			reportJasperPrint = JasperFillManager.fillReport(
					ctxPath.concat("reportes/").concat(fileName)
							.concat(".jasper"), map, cds);
			exportXlsReport(reportJasperPrint, fileName);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Funcion para exportar a excel
	 * 
	 * @param reportJasperPrint
	 * @param outputFileName
	 * @throws Exception
	 */
	private void exportXlsReport(JasperPrint reportJasperPrint,
			String outputFileName) throws Exception {
		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-Disposition", "attachment; filename="
				+ outputFileName);
		response.setContentType("application/vnd.ms-excel");

		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				reportJasperPrint);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
				Boolean.TRUE);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
				response.getOutputStream());

		exporter.exportReport();

		FacesContext.getCurrentInstance().responseComplete();
	}

	/**
	 * Exports to pdf format.
	 * 
	 * @param jasperPrint
	 *            value
	 * @throws JRException
	 *             the JR exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void exportPdf(final JasperPrint jasperPrint,
			final String fileName, FacesContext context) throws JRException,
			IOException {
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		String header = "inline; filename=\"" + fileName + ".pdf\"";
		response.setHeader("Content-Disposition", header);
		response.setContentType("application/pdf");
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				response.getOutputStream());
	}

}
