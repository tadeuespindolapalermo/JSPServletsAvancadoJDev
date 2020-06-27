package br.com.jspavancado.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.jspavancado.dao.UsuarioDao;
import br.com.jspavancado.entity.Usuario;


@WebServlet("/pages/carregarDadosDataTable")
public class DataTableServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
       
   
    public DataTableServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {			
			List<Usuario> usuarios = usuarioDao.getUsuarios();
			
			if (!usuarios.isEmpty()) {
				
				StringBuilder data = new StringBuilder();
				int totalUsuarios = usuarios.size();
				int index = 1;
				
				for (Usuario usuario : usuarios) {
					
					data
					.append(" [")
					.append("\"")
					.append(usuario.getId())
					.append("\", ")
					.append("\"")
					.append(usuario.getLogin())
					.append("\"")
					.append("]");	
					
					if (index < totalUsuarios) {
						data.append(",");
					}
					
					index++;					
				}
				
				StringBuilder json = new StringBuilder();
					json
					.append("{")
					.append("\"draw\": 1,")
					.append("\"recordsTotal\": ")
					.append(totalUsuarios)
					.append(",")
					.append("\"recordsFiltered\": ")
					.append(totalUsuarios)
					.append(",")
					.append("\"data\": [")
					.append(data.toString())
					.append("]")
					.append("}");
				
				response.setStatus(200);
				response.getWriter().write(json.toString());
			}
			
			
		} catch (SQLException e) {
			response.setStatus(500);
			e.printStackTrace();
		}		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
