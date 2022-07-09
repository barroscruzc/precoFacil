package com.projetoFinal.precoFacil.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "receitas")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name ="codigo", nullable = false)
	private Integer codigo;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name="descricao", length = 100)
	private String descricao;
	
	@Column(name="rendimento", nullable = false)
	private Integer rendimento;
	
	@Column(name="custoEmbalagem", nullable = false)
	private Float custoEmbalagem;
	
	@Column(name="lucro", nullable = false)
	private Integer lucro;
	
	@Column(name="categoria", nullable=false, length = 15)
	private String categoria;
	
	@Column(name="tempoPreparo", nullable=false)
	private Integer tempoPreparo;
	
	@Column(name="custoInicial", nullable=false)
	private Float custoInicial;
	
	@Column(name="custoFinal", nullable=false)
	private Float custoFinal;
	
	
	@ManyToOne
	private CustoFixo custoFixo;
	
	@ManyToMany(fetch = FetchType.LAZY) // https://www.devmedia.com.br/lazy-e-eager-loading-com-hibernate/29554
	@JoinTable(  //Sobre anotações JoinTable e JoinColumn -> https://www.devmedia.com.br/hibernate-mapping-mapeando-relacionamentos-entre-entidades/29445
			name = "ingredientes_receitas", 
			joinColumns = {
					@JoinColumn(name = "receita_id", referencedColumnName = "id", nullable=false, updatable = false)}, 
			inverseJoinColumns = {
					@JoinColumn(name = "ingrediente_id", referencedColumnName = "id", nullable=false, updatable = false)}
			)
	private List<Ingrediente> ingredientes; //lista que armazena os ingredientes usados na receita
	
	public Receita() {
		super();
	}

	public Receita(Long id, Integer codigo, String nome, String descricao, Integer rendimento, Float custoEmbalagem,
			Integer lucro, String categoria, Integer tempoPreparo, Float custoInicial, Float custoFinal,
			List<Ingrediente> ingredientes) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.rendimento = rendimento;
		this.custoEmbalagem = custoEmbalagem;
		this.lucro = lucro;
		this.categoria = categoria;
		this.tempoPreparo = tempoPreparo;
		this.custoInicial = custoInicial;
		this.custoFinal = custoFinal;
		this.ingredientes = ingredientes;
	}

	public Long getId() {
		return id;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getRendimento() {
		return rendimento;
	}
	public void setRendimento(Integer rendimento) {
		this.rendimento = rendimento;
	}
	public Float getCustoEmbalagem() {
		return custoEmbalagem;
	}
	public void setCustoEmbalagem(Float custoEmbalagem) {
		this.custoEmbalagem = custoEmbalagem;
	}
	public Integer getLucro() {
		return lucro;
	}
	public void setLucro(Integer lucro) {
		this.lucro = lucro;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getTempoPreparo() {
		return tempoPreparo;
	}
	public void setTempoPreparo(Integer tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}
	
	public Float custoInicial(){
		return custoInicial;
	}

	public void setCustoInicial(Float custoInicial) {
		this.custoInicial = custoInicial;
	}
	
	public Float custoFinal(){
		return custoInicial;
	}

	public void setCustoFinal(Float custoInicial) {
		this.custoInicial = custoInicial;
	}
	
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
}