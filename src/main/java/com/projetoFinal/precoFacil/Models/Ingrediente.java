package com.projetoFinal.precoFacil.Models;

public class Ingrediente {

	private int id;
	private float valor;
	private int quantidade;
	
	public Ingrediente() {
		super();
	}
	
	public Ingrediente(float valor, int quantidade) {
		super();
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
