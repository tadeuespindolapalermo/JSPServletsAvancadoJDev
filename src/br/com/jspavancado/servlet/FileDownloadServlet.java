package br.com.jspavancado.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jspavancado.dao.UsuarioDao;
import br.com.jspavancado.entity.Usuario;
import br.com.jspavancado.service.RelatorioService;

@WebServlet("/pages/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RelatorioService relatorioService = new RelatorioService();

	private UsuarioDao usuarioDao = new UsuarioDao();

	public FileDownloadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext context = request.getServletContext();
			
			String tipoExportar = request.getParameter("tipoExportar");

			List<Usuario> usuarios = usuarioDao.getUsuarios();		
			
			String fileUrl = relatorioService.gerarRelatorio(usuarios, new HashMap(), 
					"rel_usuario", "rel_usuario", context, tipoExportar);
			
			// Construir caminho completo e absoluto do arquivo
			File downloadFile = new File(fileUrl);			
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			// Obter o tipo MIME do arquivo
			String mimeType = context.getMimeType(fileUrl);
			if (mimeType == null) {
				// define como tipo binário (download de qualquer coisa)
				mimeType = "application/octet-stream";
			}
			
			// define atributos para resposta
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			
			// Cabeçalhos da resposta
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			
			response.setHeader(headerKey, headerValue);
			
			// Obter fluxo de saída da resposta
			OutputStream os = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesReader = -1;
			
			// Escrever bytes lidos a partir do fluxo de entrada para o fluxo de saída
			while ((bytesReader = inputStream.read(buffer)) != -1) {
				os.write(buffer, 0, bytesReader);
			}
			
			inputStream.close();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
