package com.projetoFinal.precoFacil.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="custoFixo")
public class CustoFixo {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY) //número do id é autoincrementado
	private final Long id = null;
	
	@Column(name = "valorHora")
	private Float valorHora;
	
	@Column(name = "salario")
	private Float salario;
	
	@Column(name = "horasDia")
	private Integer horasDia;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Receita> receitas;
	
	
	public CustoFixo() {
		super();
	}
	
	public CustoFixo(Float salario, Integer horasDia, List<Receita> receitas) {
		super();
		this.salario = salario;
		this.horasDia = horasDia;
		Float valorHora = salario / 20 / horasDia;
		this.valorHora = valorHora;
		this.receitas = receitas;
	}

	public Long getId() {
		return id;
	}
	
	public Float getValorHora() {
		return valorHora;
	}
	public void setValorHora(Float salario, Integer horasDia) {
		Float valorHora = salario / 20 / horasDia;
		this.valorHora = valorHora;
	}
	public Float getSalario() {
		return salario;
	}
	public void setSalario(Float salario) {
		this.salario = salario;
	}
	public Integer getHorasDia() {
		return horasDia;
	}
	public void setHorasDia(Integer horasDia) {
		this.horasDia = horasDia;
	}
}
