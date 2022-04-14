package ec.gov.iess.planillaspq.web.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;

import ec.gov.iess.planillaspq.web.backing.AprobacionPdaBacking;

//import ec.gov.iess.ph.intranet.backing.PlanillasArchivoMemoBean;

/**
 * Clase para realizar la descarga de archivos desde la base de datos
 * 
 * @author hugo.mora
 * 
 */
public class DBFileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DBFileDownloadServlet() {
		super();
	}

	// size of byte buffer to send file
	private static final int BUFFER_SIZE = 8192;

	/*
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AprobacionPdaBacking aprobacionBean = (AprobacionPdaBacking) request.getSession().getAttribute(
					"aprobacionbean");
			if (aprobacionBean.getAdjuntoSeleccionado().getArchivo() != null) {
				InputStream inputStream = new ByteArrayInputStream(ArrayUtils.toPrimitive(aprobacionBean
						.getAdjuntoSeleccionado().getArchivo()));
				int fileLength = inputStream.available();
				ServletContext context = getServletContext();
				String nombreArchivo = aprobacionBean.getAdjuntoSeleccionado().getNombreArchivo() == null ? "formulario"
						: aprobacionBean.getAdjuntoSeleccionado().getNombreArchivo();
				// sets MIME type for the file download
				String mimeType = context.getMimeType(nombreArchivo);
				if (mimeType == null) {
					mimeType = "application/pdf";
				}
				// set content properties and header attributes for the response
				response.setContentType(mimeType);
				response.setContentLength(fileLength);
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
				response.setHeader(headerKey, headerValue);
				// writes the file to the client
				OutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {
				// no file found
				response.getWriter()
						.print("No se encontr\u00F3 el archivo: "
								+ aprobacionBean.getAdjuntoSeleccionado().getNombreArchivo());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
