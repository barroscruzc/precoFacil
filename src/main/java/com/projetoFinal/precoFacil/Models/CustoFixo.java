package com.projetoFinal.precoFacil.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	public CustoFixo() {
		super();
	}
	
	public CustoFixo(Float valorHora, Float salario, Integer horasDia) {
		super();
		this.valorHora = valorHora;
		this.salario = salario;
		this.horasDia = horasDia;
	}

	public Long getId() {
		return id;
	}
	
	public Float getValorHora() {
		return valorHora;
	}
	public void setValorHora(Float valorHora) {
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
