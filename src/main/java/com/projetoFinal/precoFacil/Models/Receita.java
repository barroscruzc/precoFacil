package com.projetoFinal.precoFacil.Models;

public class Receita {

	private Long id;
	private Integer codigo;
	private String nome;
	private String descricao;
	private Integer rendimento;
	private Float custoEmbalagem;
	private Integer lucro;
	private String categoria;
	private Integer tempoPreparo;
	
	public Receita() {
		super();
	}
	
	public Receita(Integer codigo, String nome, String descricao, Integer rendimento, Float custoEmbalagem, Integer lucro,
			String categoria, Integer tempoPreparo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.rendimento = rendimento;
		this.custoEmbalagem = custoEmbalagem;
		this.lucro = lucro;
		this.categoria = categoria;
		this.tempoPreparo = tempoPreparo;
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

}