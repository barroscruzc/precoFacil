package com.projetoFinal.precoFacil.Models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
	
	//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	/*@JoinTable(name = "medida_ingrediente", 
			joinColumns= {@JoinColumn(name = "ingrediente_id", referencedColumnName = "id")},
			inverseJoinColumns= {@JoinColumn(name = "medida_id", referencedColumnName = "id")})*/
	//private List<Medida> medida;
	
	public Ingrediente() {
		super();
	}
	
	public Ingrediente(String nome, Float preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(preco, other.preco);
	}
	
}
