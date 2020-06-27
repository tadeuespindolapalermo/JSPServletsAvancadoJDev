package br.com.jspavancado.entity;

import java.io.Serializable;

public class Authentication implements Serializable {

	private static final long serialVersionUID = -2749109235240535213L;

	private String login;
	private String password;

	public Authentication(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
