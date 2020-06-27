package br.com.jspavancado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.jspavancado.connection.SingleConnection;
import br.com.jspavancado.entity.Imagem;

public class ImagemDao {

	private static Connection connection;

	public ImagemDao() {
		connection = SingleConnection.getConnection();
	}

	public List<Imagem> getImagens() throws SQLException {
		List<Imagem> imagems = new ArrayList<>();

		String sql = "SELECT * FROM imagem";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Imagem imagem = new Imagem();
			imagem.setId(result.getLong("id"));	
			imagem.setBase64(result.getString("base64"));			
			imagems.add(imagem);
		}
		return imagems;
	}

	public void gravarImagem(String imagem) throws SQLException {
		String tipofile = imagem.split(",")[0].split(";")[0].split("/")[1];
		String sql = "INSERT INTO imagem (base64, tipofile) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, imagem);
		statement.setString(2, tipofile);
		statement.execute();
		connection.commit();
	}

	public String buscarImagem(String idImagem) throws SQLException {
		String sql = "SELECT base64 FROM imagem WHERE id = " + idImagem;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			return result.getString("base64");
		}
		return null;
	}
	
	public Imagem findImagem(String idImagem) throws SQLException {
		
		Imagem imagem = null;
		String sql = "SELECT * FROM imagem WHERE id = " + idImagem;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			imagem = new Imagem();
			imagem.setId(result.getLong("id"));	
			imagem.setBase64(result.getString("base64"));	
			imagem.setTipofile(result.getString("tipofile"));
		}
		return imagem;	
	}

}
