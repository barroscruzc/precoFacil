package com.projetoFinal.precoFacil.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "receita")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name ="codigo", length = 50, unique = true)
	private Integer codigo;
	
	@Column(name ="nome", length = 20, nullable = false, unique = true)
	private String nome;
	
	@Column(name="descricao", length = 100, nullable = false)
	private String descricao;
	
	@Column(name="rendimento")
	private Integer rendimento;
	
	@Column(name="embalagem")
	private Float embalagem;
	
	@Column(name="categoria", length = 20)
	private String categoria;
	
	@Column(name="tempoPreparo")
	private Integer tempoPreparo;
	
	@Column(name="lucro")
	private Float lucro;
	
	@Column(name="precoVenda")
	private Float precoVenda;
	
	@Column(name="custoInicial")
	private Float custoInicial;
	
	@ManyToOne
	private ValorHora valorHora;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "receita_ingrediente", 
					joinColumns = @JoinColumn (name = "receita_id", referencedColumnName = "id"))
	@MapKeyJoinColumn(name = "ingrediente_id")
	@Column(name = "medida")
	private Map<Ingrediente, Float> medida = new HashMap<>();
	
	public Receita() {
		super();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	public Float getEmbalagem() {
		return embalagem;
	}
	public void setEmbalagem(Float embalagem) {
		this.embalagem = embalagem;
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
	
	public Float getLucro() {
		return lucro;
	}

	public void setLucro(Float lucro) {
		this.lucro = lucro;
	}

	public void setValorHora(ValorHora valorHora) {
		this.valorHora = valorHora;
	}
	
	public Float getValorHora() {
		return this.valorHora.getValorHora();
	}
	
	public Map<Ingrediente, Float> getMedida() {
		return medida;
	}

	public void setMedida(Ingrediente ingrediente, Float quantidade) {
		this.medida.put(ingrediente, quantidade);
	}
	
	public void setCustoInicial(Float custoInicial) {
		this.custoInicial = custoInicial;
	}
	
	public Float getCustoInicial(){
		return custoInicial;
	}

	public Float getPrecoVenda(){
		return precoVenda;
	}

	//calcula o valor de venda
	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	@Override
	public String toString() {
		return "Receita [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao
				+ ", rendimento=" + rendimento + ", embalagem=" + embalagem + ", categoria=" + categoria
				+ ", tempoPreparo=" + tempoPreparo + ", lucro=" + lucro + ", precoVenda=" + precoVenda
				+ ", custoInicial=" + custoInicial + ", valorHora=" + valorHora + ", medida=" + medida + "]";
	}
	
}