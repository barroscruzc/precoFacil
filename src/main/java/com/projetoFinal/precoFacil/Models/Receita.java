package com.projetoFinal.precoFacil.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
	
	@Column(name = "nome", length = 30, nullable = false)
	private String nome;
	
	@Column(name="descricao", length = 100, nullable = false)
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
	
	@Column(name="precoVenda", nullable=false)
	private Float precoVenda;
	
	@Column(name="custoInicial", nullable=false)
	private Float custoInicial;
	
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
	private HashMap<Ingrediente, Float> ingredientes; //lista que armazena os ingredientes usados na receita
	//https://www.baeldung.com/java-hashmap
	
	public Receita() {
		super();
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
		this.custoEmbalagem = custoEmbalagem * rendimento;
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
	
	public void setCustoFixo(CustoFixo custoFixo) {
		this.custoFixo = custoFixo;
	}
	
	public CustoFixo getCustoFixo() {
		return custoFixo;
	}
	
	
	public void setCustoInicial() {
		Float somaIngredientes = (float) 0;
		
		/*ITERANDO SOBRE O HASHMAP PARA SOMAR OS VALORES DOS INGREDIENTES USADOS
		 *O custo de cada ingrediente é igual ao seu preço multiplicado pelo custo por unidade ou medida .
		 * Deste modo, por exemplo, o custo de 500g de chocolate será igual ao valor do kg do produto 
		 * (que é o atributo preco do objeto ingrediente) multiplicado pela quantidade usada na receita
		 * (que será um valor Float 0.5, inserido no hashMap como value para a key Ingrediente
		 * Ingredientes que possuem unidade de medida por peso ou líquido serão recebidos como Float da seguinte maneira:
		 * cada algarismo na parte inteira do Float corresponte a 1kg ou 1L, enquanto os algarismos da parte fracionária 
		 * (após a vígula) são frações de Litro ou kg. Assim, o chocolate do exemplo acima seria representado 
		 * por 0.5, ou, se fossem 250g, 0.25, e assim por diante*/
		for(Map.Entry<Ingrediente, Float> item : this.ingredientes.entrySet()) {
			somaIngredientes += item.getKey().getPreco() * item.getValue();
		}
		
		//calculando o valor dos ingredientes conforme as quantidades de cada item usadas na receita
		this.custoInicial = somaIngredientes + (this.tempoPreparo * this.custoFixo.getValorHora()) + (this.custoEmbalagem * this.rendimento);
		
	}
	
	public Float getPrecoVenda(){
		return precoVenda;
	}

	public void setPrecoVenda() {
		this.precoVenda = this.custoInicial * this.lucro;
	}
	
	public HashMap<Ingrediente, Float> getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(Ingrediente ingrediente, Float quantidade) {
		this.ingredientes.put(ingrediente, quantidade);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ingredientes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receita other = (Receita) obj;
		return Objects.equals(ingredientes, other.ingredientes);
	}

	
}