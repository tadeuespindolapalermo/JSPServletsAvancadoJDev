package br.com.jspavancado.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pages/capturarExcecao")
public class ExcecaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExcecaoServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {			
			//System.out.println(request.getParameter("valorParam"));	
			
			String valor = request.getParameter("valorParam");
			
			Integer.parseInt(valor);
			
			response.setStatus(200); // OK
			response.getWriter().write("Processado com sucesso! 200");
		} catch (Exception e) {
			response.setStatus(500); // Internal Error Server
			response.getWriter().write("Erro ao processar! 500 - " + e.getMessage());
		}
		
	}

}
