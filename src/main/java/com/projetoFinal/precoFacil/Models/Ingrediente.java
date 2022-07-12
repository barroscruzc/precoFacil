package com.projetoFinal.precoFacil.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "ingrediente", uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Ingrediente {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name = "preco", nullable = false)
	private Float preco;
	
	//cria uma nova tabela "ingredientesReceita", gerando um relacionamento muitos para muitos  | https://www.baeldung.com/jpa-cascade-types 
	//@ManyToMany(mappedBy = "ingredientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)    // | https://www.devmedia.com.br/lazy-e-eager-loading-com-hibernate/29554
	//private Colle<Receita, Float> receitas = new HashMap<>();
	
	public Ingrediente() {
		super();
	}
	
	public Ingrediente(String nome, Float preco) {
		super();
		this.nome = nome;
		this.preco = preco;
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
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	public void setpreco(Float preco) {
		this.preco = preco;
	}

}
