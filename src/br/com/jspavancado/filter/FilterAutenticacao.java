package br.com.jspavancado.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.jspavancado.connection.SingleConnection;

@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter {	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		
		HttpServletRequest req = (HttpServletRequest) request;		
		
		String urlAutenticacao = req.getServletPath();
		
		if (req.getSession().getAttribute("aut") == null 
				&& !urlAutenticacao.equalsIgnoreCase("pages/autenticacaoUsuario")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticacao.jsp?url=" + urlAutenticacao);
			dispatcher.forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SingleConnection.getConnection();
	}

}
