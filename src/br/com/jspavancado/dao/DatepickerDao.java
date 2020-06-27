package br.com.jspavancado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.jspavancado.connection.SingleConnection;

public class DatepickerDao {

	private Connection connection;
	
	public DatepickerDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void gravarDataFinal(String date) throws SQLException {
		String sql = "insert into datapicker (datafinal) values (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, date);
		statement.execute();
		connection.commit();
	}
}
