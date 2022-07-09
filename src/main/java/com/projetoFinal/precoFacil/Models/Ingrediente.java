package com.projetoFinal.precoFacil.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ingredientes", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Ingrediente {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco", nullable = false)
	private Float preco;
	
	@Column(name = "undMedida", nullable = false, length=7)
	private String undMedida;
	
	@Column(name="quantidade", nullable = false)
	private Integer quantidade;
	
	//cria uma nova tabela "ingredientesReceita", gerando um relacionamento muitos para muitos  | https://www.baeldung.com/jpa-cascade-types 
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "receitas", cascade = CascadeType.ALL)    // | https://www.devmedia.com.br/lazy-e-eager-loading-com-hibernate/29554
	@JsonIgnore
	List<Receita> receitas;
	
	public Ingrediente() {
		super();
	}
	
	public Ingrediente(Float preco, Integer quantidade) {
		super();
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}
	
	public Float getpreco() {
		return preco;
	}
	public void setpreco(Float preco) {
		this.preco = preco;
	}
	
	public String getUndMedida() {
		return undMedida;
	}
	public void setUndMedida(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
