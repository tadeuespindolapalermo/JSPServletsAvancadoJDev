package br.com.jspavancado.service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = -623117803792778535L;	
	
	private static final String FOLDER_RELATORIOS = "/relatorios";	
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";	
	private String separator = File.separator;	
	private String caminhoArquivoRelatorio = null;	
	private JRExporter exporter = null;	
	private String caminhoSubReport_Dir = "";	
	private File arquivoGerado = null;
	
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar) throws JRException {
		
		// Dados relatório
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + separator + nomeRelatorioJasper + ".jasper");
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty())
				|| !file.exists()) {
			
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			separator = "";
			
		}
		
		// Caminho imagem relatório
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		// Caminho completo até o relatório compilado indicado
		String caminhoArquivoJasper = caminhoRelatorio + separator + nomeRelatorioJasper + ".jasper";
		
		// Faz carregamento do relatório
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
		
		// Seta parâmetros SUBREPORT_DIR com o caminho físico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + separator;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		// Carrega o arquivo
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		if (tipoExportar.equalsIgnoreCase("pdf")) {
			exporter = new JRPdfExporter();
		} else if (tipoExportar.equalsIgnoreCase("xls")) {
			exporter = new JRXlsExporter();
		}
		
		// Caminho relatório exportado
		caminhoArquivoRelatorio = caminhoRelatorio + separator + nomeRelatorioSaida + "." + tipoExportar;
		
		// Criar novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		// Prepara a impressão
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		// Executa a exportação
		exporter.exportReport();
		
		// Remove o arquivo do servidor após ser feito o download
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}

}
