package com.projetoFinal.precoFacil.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoFinal.precoFacil.Models.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
	List<Ingrediente> findByNomeContains(String nome);
	Ingrediente findByNome(String nome);
}
