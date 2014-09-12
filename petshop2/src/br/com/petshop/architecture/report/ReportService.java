package br.com.petshop.architecture.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petshop.architecture.util.SystemException;

@Service
public class ReportService implements Serializable {

	private static final long serialVersionUID = -6934369574081575720L;
	
	@Autowired
	private transient DownloadManager downloadManager;

	public byte[] buildReportPDFToArrayByte(final String pathRelatorio, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) {
		try {
			final JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(sourceList);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(getPathReal(pathRelatorio), parametrosRelatorio, jrDataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (final JRException e) {
			throw new SystemException("Erro ao montar o relatório", e);
		}
	}

	public byte[] buildReportXLSToArrayByte(final String pathRelatorio, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) {
		try {
			return buildReportXLS(pathRelatorio, parametrosRelatorio, sourceList);
		} catch (final IOException e) {
			throw new SystemException("Erro ao fechar o ByteArrayOutputStream", e);
		}
	}

	public void buildReportPDFInLine(final String pathRelatorio, final String nomeArquivoCaixaDownload, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) {
		buildReportPDF(pathRelatorio, nomeArquivoCaixaDownload, parametrosRelatorio, false, sourceList);
	}

	public void buildReportPDFAttachment(final String pathRelatorio, final String nomeArquivoCaixaDownload, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) {
		buildReportPDF(pathRelatorio, nomeArquivoCaixaDownload, parametrosRelatorio, true, sourceList);
	}

	public void buildReportXLSInLine(final String pathRelatorio, final String nomeArquivoCaixaDownload, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) {
		try {
			buildReportXLS(pathRelatorio, nomeArquivoCaixaDownload, parametrosRelatorio, false, sourceList);
		} catch (final IOException e) {
			throw new SystemException("Erro ao fechar o ByteArrayOutputStream", e);
		}
	}

	public void buildReportXLSAttachment(final String pathRelatorio, final String nomeArquivoCaixaDownload, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) {
		try {
			buildReportXLS(pathRelatorio, nomeArquivoCaixaDownload, parametrosRelatorio, true, sourceList);
		} catch (final IOException e) {
			throw new SystemException("Erro ao disponibilizar o ByteArrayOutputStream", e);
		}
	}

	private byte[] buildReportXLS(final String pathRelatorio, final Map<String, Object> parametrosRelatorio, final List<?> sourceList) throws IOException {

		final ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();

		try {

			final JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(sourceList);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(getPathReal(pathRelatorio), parametrosRelatorio, jrDataSource);

			final JExcelApiExporter exporter = new JExcelApiExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
			exporter.exportReport();

			return xlsReport.toByteArray();
		} catch (final JRException e) {
			throw new SystemException("Erro ao montar o relatório", e);
		} finally {
			xlsReport.close();
		}
	}

	private void buildReportXLS(final String pathRelatorio, final String nomeArquivoCaixaDownload, final Map<String, Object> parametrosRelatorio, final boolean exibeCaixaDialogo, final List<?> sourceList) throws IOException {

		final byte[] conteudo = buildReportXLS(pathRelatorio, parametrosRelatorio, sourceList);

		try {
			downloadManager.disponibilizarArquivoDownload(conteudo, nomeArquivoCaixaDownload + ".xls", exibeCaixaDialogo, "application/vnd.ms-excel");
		} catch (final IOException e) {
			throw new SystemException("Erro ao disponibilizar o relatório", e);
		}
	}

	private void buildReportPDF(final String pathRelatorio, final String nomeArquivoCaixaDownload, final Map<String, Object> parametrosRelatorio, final boolean exibeCaixaDialogo, final List<?> sourceList) {

		try {
			final JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(sourceList);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(getPathReal(pathRelatorio), parametrosRelatorio, jrDataSource);
			final byte[] bytesConteudo = JasperExportManager.exportReportToPdf(jasperPrint);
			downloadManager.disponibilizarArquivoDownload(bytesConteudo, nomeArquivoCaixaDownload + ".pdf", exibeCaixaDialogo, "application/pdf");
		} catch (final JRException e) {
			throw new SystemException("Erro ao montar o relatório", e);
		} catch (final IOException e) {
			throw new SystemException("Erro ao disponibilizar o relatório", e);
		}
	}

	public String getPathReal(final String pathRelatorio) {
		final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return servletContext.getRealPath(pathRelatorio);
	}

}
