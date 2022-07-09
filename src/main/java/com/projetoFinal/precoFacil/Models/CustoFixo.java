package com.projetoFinal.precoFacil.Models;

public class CustoFixo {

	private Long id;
	private Float valorHora;
	private Float salario;
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
