package br.com.jspavancado.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.jspavancado.dao.ImagemDao;
import br.com.jspavancado.entity.Imagem;

@WebServlet("/pages/fileUpload")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImagemDao imagemDao = new ImagemDao();

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao.equalsIgnoreCase("carregar")) {
				RequestDispatcher view = request.getRequestDispatcher("upload.jsp");
				request.setAttribute("imagens", imagemDao.getImagens());
				view.forward(request, response);				
			}
			
			if (acao.equalsIgnoreCase("download")) {
				String idImagem = request.getParameter("idImagem");
				Imagem imagem = imagemDao.findImagem(idImagem);
				
				if (imagem != null) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + imagem.getTipofile());
					
					String imagemPura = imagem.getBase64().split(",")[1];
					
					new Base64();
					byte [] imagemBytes = Base64.decodeBase64(imagemPura);
					
					InputStream is = new ByteArrayInputStream(imagemBytes);
					
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();					
				}			
			}									
		} catch (Exception e) {
			e.printStackTrace();
		}	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String fileUpload = request.getParameter("fileUpload");

			imagemDao.gravarImagem(fileUpload);

			response.getWriter().write("Upload realizado com sucesso!");
		} catch (Exception e) {
			response.getWriter().write("Erro fatal ao realizar upload! " + e.getMessage());
		}

	}

}
