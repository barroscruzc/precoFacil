package com.projetoFinal.precoFacil.Models;

public class Medida {
	
	private Float quantidade;
	
	private Long idIngrediente;
	
	private Long idReceita;
	
	public Medida() {
		super();
	}

	public Medida(Float quantidade, Long idIngrediente, Long idReceita) {
		super();
		this.quantidade = quantidade;
		this.idIngrediente = idIngrediente;
		this.idReceita = idReceita;
	}

	public Long getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(Long idReceita) {
		this.idReceita = idReceita;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
	
	
}
