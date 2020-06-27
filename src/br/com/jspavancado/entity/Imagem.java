package br.com.jspavancado.entity;

public class Imagem {

	private Long id;
	private String base64;
	private String tipofile;
	
	public void setTipofile(String tipofile) {
		this.tipofile = tipofile;
	}
	
	public String getTipofile() {
		return tipofile;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getBase64() {
		return base64;
	}

}
