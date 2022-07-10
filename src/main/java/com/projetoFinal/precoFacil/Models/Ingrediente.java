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
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private Float preco;
	
	//cria uma nova tabela "ingredientesReceita", gerando um relacionamento muitos para muitos  | https://www.baeldung.com/jpa-cascade-types 
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "receitas", cascade = CascadeType.PERSIST)    // | https://www.devmedia.com.br/lazy-e-eager-loading-com-hibernate/29554
	@JsonIgnore
	private List<Receita> receitas;
	
	public Ingrediente() {
		super();
	}
	
	public Ingrediente(String nome, Float preco, List<Receita> receitas) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.receitas = receitas;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPreco() {
		return preco;
	}
	public void setpreco(Float preco) {
		this.preco = preco;
	}
	
	public List<Receita> getReceitas(){
		return receitas;
	}
	
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}
	
}
