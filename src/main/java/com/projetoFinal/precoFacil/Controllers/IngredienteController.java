package com.projetoFinal.precoFacil.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetoFinal.precoFacil.Repositories.IngredienteRepository;
import com.projetoFinal.precoFacil.Models.Ingrediente;

@Controller
@RequestMapping(path = "/precofacil/ingredient")
public class IngredienteController {

	@Autowired // Comunica com o IngredienteRepository
	private IngredienteRepository ingredienteRepository;

	// CRUD - Read (query 'select * from ingrediente')
	@RequestMapping(path = "/all")
	public String listaIngredientes(Model model) {
		model.addAttribute("ingrediente", ingredienteRepository.findAll());
		return "allIngredients";
	}

	// CRUD - Create
	@GetMapping("/new")
	public String newIngredient(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "newIngredient";
	}

	@PostMapping("/new/add")
	public String addIngrediente(@RequestParam String nome, @RequestParam Long id, @RequestParam Float preco, Model model) {

		Ingrediente ingNome = ingredienteRepository.findByNome(nome);
		if (ingNome != null) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Ingrediente já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "newRecipe";
		} else {
			Ingrediente ingrediente = new Ingrediente();
			
			//setando elementos descritivos e identificadores do Ingrediente
			ingrediente.setNome(nome);
			ingrediente.setPreco(preco);
			
			//salvar ingrediente no banco de dados
			ingredienteRepository.save(ingrediente);
			model.addAttribute("salvoSucesso", "Ingrediente salvo com sucesso!");
			return "redirect:/precofacil/allIngredients";
				}
	}


	// CRUD - update
	@GetMapping(path="/update/{id}")
	public String editIngredient(@PathVariable("id") Long id, Model model) {
		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(id);
		if (!ingredienteOpt.isPresent()) {
			throw new IllegalArgumentException("Cadastro inválido.");
		}
		model.addAttribute("ingrediente", ingredienteOpt.get());
		
		return "updateIngredient";
	}
	
	@PostMapping("/update/{id}/save")
	public String saveIngrediente(@Valid @ModelAttribute("pessoa") Ingrediente ingrediente, @PathVariable("id") Long id, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "updateIngredient";
		}
		
		/*Verificação do nome
		 * se for encontrado cadastro com o mesmo nome preenchido no formulario, 
		 * mas com id diferente, recarrega a pagina e exibe mensagem de erro*/
		Ingrediente ingNome = ingredienteRepository.findByNome(ingrediente.getNome());
		if ((ingNome != null) && (ingNome.getId() != ingrediente.getId())) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Ingrediente já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "newRecipe";
		} else {
			//salva ingrediente no banco de dados
			ingredienteRepository.save(ingrediente);
			model.addAttribute("salvoSucesso", "Ingrediente salvo com sucesso!");
			return "redirect:/precofacil/allIngredients";
				}
	}
	
	// CRUD - Delete
	@RequestMapping(path="/delete/{id}")
	public String deleteIngrediente(@PathVariable Long id) {
		ingredienteRepository.deleteById(id);
		return "redirect:/precofacil/allIngredients";
	}
	
}
