package br.com.petshop.base.vo;

import java.util.Date;


public class LogAcessoVO implements Comparable<LogAcessoVO> {

	private Long quantidade;

	private String usuario;
	
	private Date data;
	
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Number quantidade) {
		this.quantidade = quantidade.longValue();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int compareTo(LogAcessoVO o) {
		return o.getQuantidade().compareTo(this.quantidade);
	}
}