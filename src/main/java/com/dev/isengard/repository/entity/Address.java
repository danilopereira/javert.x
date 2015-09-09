package com.dev.isengard.repository.entity;

import java.io.Serializable;

public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cidade;
	private String logradouro;
	private String bairro;
	private String cep;
	private String tp_logradouro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTp_logradouro() {
		return tp_logradouro;
	}
	public void setTp_logradouro(String tp_logradouro) {
		this.tp_logradouro = tp_logradouro;
	}
	@Override
	public String toString() {
		return "Address [id=" + id.toString() + ", cidade=" + cidade + ", logradouro=" + logradouro + ", bairro=" + bairro
				+ ", cep=" + cep + ", tp_logradouro=" + tp_logradouro + "]";
	}
	
	

}
