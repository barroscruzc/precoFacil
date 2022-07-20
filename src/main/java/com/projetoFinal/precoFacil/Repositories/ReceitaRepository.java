package com.projetoFinal.precoFacil.Repositories;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.projetoFinal.precoFacil.Models.Ingrediente;
import com.projetoFinal.precoFacil.Models.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
	List<Receita> findByNomeContains(String nome);
	Receita findByNome(String nome);
	Receita findByCodigo(Integer codigo);
	
	@Transactional
	@Modifying
	@Query(value = "insert into receita_ingrediente (receita_id, medida, ingrediente_id) values (?1, ?2, ?3)",
	  nativeQuery = true)
	void insertMedida(Long idReceita, Float quantidade, Long idIngredente);
	
	@Transactional
	@Query(value = "select * from receita_ingrediente where receita_id = ?1",
	  nativeQuery = true)
	HashMap<Ingrediente, Float> selectMedida(Long idReceita);
	
	@Transactional
	@Modifying
	@Query(value = "update receita_ingrediente set medida = ?1 where (receita_id = ?2 and ingrediente_id = ?3)",
	  nativeQuery = true)
	void updateMedida(Float quantidade, Long idReceita, Long idIngredente);
	
	@Transactional
	@Modifying
	@Query(value = "delete from receita_ingrediente where (receita_id = ?1 and ingrediente_id = ?2)",
	  nativeQuery = true)
	void deleteMedida(Long idReceita, Long idIngredente);
	
}
