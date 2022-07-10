package com.projetoFinal.precoFacil.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoFinal.precoFacil.Models.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
	List<Receita> findByNomeContains(String nome);
	Receita findByNome(String nome);
	Receita findByCodigo(Integer codigo);
}
