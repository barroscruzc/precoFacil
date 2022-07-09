package com.projetoFinal.precoFacil.Models;

public class Receita {

	private int id;
	private int codigo;
	private String nome;
	private String descricao;
	private int rendimento;
	private float custoEmbalagem;
	private int lucro;
	private String categoria;
	private int tempoPreparo;
	
	public Receita() {
		super();
	}
	
	public Receita(int codigo, String nome, String descricao, int rendimento, float custoEmbalagem, int lucro,
			String categoria, int tempoPreparo) {
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

	public int getId() {
		return id;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
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
	public int getRendimento() {
		return rendimento;
	}
	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}
	public float getCustoEmbalagem() {
		return custoEmbalagem;
	}
	public void setCustoEmbalagem(float custoEmbalagem) {
		this.custoEmbalagem = custoEmbalagem;
	}
	public int getLucro() {
		return lucro;
	}
	public void setLucro(int lucro) {
		this.lucro = lucro;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getTempoPreparo() {
		return tempoPreparo;
	}
	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

}