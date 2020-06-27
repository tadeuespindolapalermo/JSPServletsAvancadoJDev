package br.com.jspavancado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jspavancado.connection.SingleConnection;
import br.com.jspavancado.entity.Usuario;

public class UsuarioDao {
	
	private static Connection connection;
	
	public UsuarioDao() {
		connection = SingleConnection.getConnection();
	}
	
	public List<Usuario> getUsuarios() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		
		String sql = "SELECT * FROM usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while (result.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(result.getLong("id"));
			usuario.setLogin(result.getString("login"));
			usuario.setSenha(result.getString("senha"));
			usuarios.add(usuario);
		}
		return usuarios;		
	}

}
