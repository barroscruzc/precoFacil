package com.projetoFinal.precoFacil.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="valorHora")
public class ValorHora {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "valorHora")
	private Float valorHora;
	
	@Column(name = "salario")
	private Float salario;
	
	@Column(name = "horasDia")
	private Integer horasDia;
	
	public ValorHora() {
		super();
	}

	public ValorHora(Long id, Float salario, Integer horasDia) {
		super();
		this.id = id;
		this.salario = salario;
		this.horasDia = horasDia;
		this.valorHora = salario / 20 / horasDia;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
