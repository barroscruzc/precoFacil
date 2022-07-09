package com.projetoFinal.precoFacil.Models;

public class CustoFixo {

	private int id;
	private float valorHora;
	private float salario;
	private Integer horasDia;
	
	
	public CustoFixo() {
		super();
	}
	
	public CustoFixo(float valorHora, float salario, Integer horasDia) {
		super();
		this.valorHora = valorHora;
		this.salario = salario;
		this.horasDia = horasDia;
	}

	public int getId() {
		return id;
	}
	
	public float getValorHora() {
		return valorHora;
	}
	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public Integer getHorasDia() {
		return horasDia;
	}
	public void setHorasDia(Integer horasDia) {
		this.horasDia = horasDia;
	}
}
