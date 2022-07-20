package com.projetoFinal.precoFacil.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoFinal.precoFacil.Repositories.IngredienteRepository;
import com.projetoFinal.precoFacil.Models.Ingrediente;

@Controller
@RequestMapping(path = "/precofacil/ingredient")
public class IngredienteController {

	@Autowired // Comunica com o IngredienteRepository
	private IngredienteRepository ingredienteRepository;

	// CRUD - Read (query 'select * from ingrediente')
	@GetMapping(path = "/all")
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
	public String addIngrediente(@ModelAttribute Ingrediente ingrediente, Model model) {

		Ingrediente ingNome = ingredienteRepository.findByNome(ingrediente.getNome());
		if (ingNome != null) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Ingrediente já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "newIngredient";
		} else {
			//salvar ingrediente no banco de dados
			ingredienteRepository.save(ingrediente);
			//model.addAttribute("salvoSucesso", "Ingrediente salvo com sucesso!");
			model.addAttribute("ingrediente", ingredienteRepository.findAll());
			return "allIngredients";
				}
	}

	// CRUD - update
	@GetMapping(path="/update/{id}")
	public String editIngredient(@PathVariable Long id, Model model) {
		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(id);
		if (!ingredienteOpt.isPresent()) {
			throw new IllegalArgumentException("Cadastro inválido.");
		}
		model.addAttribute("ingrediente", ingredienteOpt.get());
		
		return "updateIngredient";
	}
	
	@PostMapping("/update/save")
	public String saveIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, Model model) {
		/*Verificação do nome
		 * se for encontrado cadastro com o mesmo nome preenchido no formulario, 
		 * mas com id diferente, recarrega a pagina e exibe mensagem de erro*/
		Ingrediente ingNome = ingredienteRepository.findByNome(ingrediente.getNome());
		if ((ingNome != null) && (ingNome.getId() != ingrediente.getId())) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Ingrediente já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "updateIngredient";
		} else {
			//salva ingrediente no banco de dados
			Ingrediente novoIngrediente = ingredienteRepository.findById(ingrediente.getId()).get();
			novoIngrediente.setNome(ingrediente.getNome());
			novoIngrediente.setPreco(ingrediente.getPreco());
			ingredienteRepository.save(novoIngrediente);
			//model.addAttribute("salvoSucesso", "Ingrediente salvo com sucesso!");
			
			return "redirect:/precofacil/ingredient/all";
				}
	}
	
	// CRUD - Delete
	@RequestMapping(path="/delete/{id}")
	public String deleteIngrediente(@PathVariable Long id, Model model) {
		ingredienteRepository.deleteById(id);
		model.addAttribute("ingrediente", ingredienteRepository.findAll());
		return "allIngredients";
	}

}
