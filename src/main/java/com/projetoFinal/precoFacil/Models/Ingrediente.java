package com.projetoFinal.precoFacil.Models;

public class Ingrediente {

	private Long id;
	private Float valor;
	private Integer quantidade;
	
	public Ingrediente() {
		super();
	}
	
	public Ingrediente(Float valor, Integer quantidade) {
		super();
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}
	
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
