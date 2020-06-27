package br.com.jspavancado.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String banco = "jdbc:postgresql://localhost:5432/projeto-jsp-avancado?autoReconnect=true";
	private static String password = "postgres1985";
	private static String user = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
