package br.com.jspavancado.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jspavancado.entity.Authentication;
import br.com.jspavancado.util.LogUtil;

@WebServlet("/pages/autenticacaoUsuario")
public class AutenticacaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String URL_AUTENTICACAO = "autenticacao.jsp";

	public AutenticacaoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			if (Boolean.parseBoolean(request.getParameter("deslogar"))) {
				request.getSession().invalidate();
				response.sendRedirect("../index.jsp");
			}
		} catch (Exception e) {
			LogUtil.getLogger(AutenticacaoServlet.class).error(e.getCause().toString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {					
			
			String login = request.getParameter("login");
			String password = request.getParameter("passwd");	
			String url = request.getParameter("url");
			
			if (Boolean.TRUE.equals(authenticateUser(request, login, password))) {
				redirectUser(request, response, url);
				return;
			}			
			redirectUser(request, response, URL_AUTENTICACAO);
		} catch (Exception e) {
			LogUtil.getLogger(AutenticacaoServlet.class).error(e.getCause().toString());
		}
	}
	private Boolean authenticateUser(HttpServletRequest request, String login, String password)  {		
		if (validateCredentials(login, password)) {
			addUserSession(request, login, password);					
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private boolean validateCredentials(String login, String password) {
		return login.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123");
	}
	
	private void addUserSession(HttpServletRequest request, String login, String password) {
		request.getSession().setAttribute("aut", new Authentication(login, password));
	}
	
	private void redirectUser(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		request.getRequestDispatcher("/" + page).forward(request, response);
	}

}
