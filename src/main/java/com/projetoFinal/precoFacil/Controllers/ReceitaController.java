package com.projetoFinal.precoFacil.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetoFinal.precoFacil.Models.Ingrediente;
import com.projetoFinal.precoFacil.Models.Receita;
import com.projetoFinal.precoFacil.Repositories.CustoFixoRepository;
import com.projetoFinal.precoFacil.Repositories.IngredienteRepository;
import com.projetoFinal.precoFacil.Repositories.ReceitaRepository;

import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


@Controller // Informa que classe UserController eh o nosso controller
@RequestMapping(path = "/precofacil")
public class ReceitaController {

	@Autowired // Comunica com o UserRepository
	private ReceitaRepository receitaRepository;
	private IngredienteRepository ingredienteRepository;
	private CustoFixoRepository custoFixoRepository;

	@GetMapping(path = "/home")
	public String inicio() {
		return "home";
	}

	//Controller Receitas
	// CRUD - Read
	@RequestMapping(path = "/allRecipes")
	public String listaUsers(Model model) {
		model.addAttribute("receita", receitaRepository.findAll());
		return "allRecipes";
	}

	// CRUD - Create
	@GetMapping("/newRecipe")
	public String cadastrar(Model model) {
		model.addAttribute("receita", new Receita());
		//O cálculo do valorHora é feito pela Model CustoFixo(linha 44), e pode ser representado pela expressão: ValorHora = Salário / 20 / HorasDia
		model.addAttribute("valorHora", custoFixoRepository.findById((long)1).get().getValorHora());
		model.addAttribute("todosIngredientes", ingredienteRepository.findAll());
		model.addAttribute("ingredientes", new HashMap<Ingrediente, Float>());
		return "newRecipe";
	}

	@PostMapping("/newRecipe/add")
	public String addRecipe(@RequestParam Integer codigo,@RequestParam String nome,@RequestParam String descricao,@RequestParam Integer rendimento,@RequestParam Float custoEmbalagem,
			@RequestParam Integer lucro, @RequestParam String categoria, @RequestParam Integer tempoPreparo, @RequestParam HashMap<Ingrediente, Float> ingredientes, Model model) {
		
		//verifica se já consta receita cadastrada com os dados preenchidos no formulário de cadastro
		
		//Verificação por nome
		Receita recNome = receitaRepository.findByNome(nome);
		if (recNome != null) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Receita já cadastrada!");
			//recarrega a página, exibindo a mensagem de erro
			return "/newRecipe";
		}

		//Verificação por Codigo
		Receita recCodigo = receitaRepository.findByCodigo(codigo);
		if (recCodigo != null) {
			//mensagem de erro
			model.addAttribute("codigoExiste", "Código de receita já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "/newRecipe";
		} else {
			Receita receita = new Receita();
			
			//setando elementos descritivos e identificadores da receita
			receita.setCodigo(codigo);
			receita.setNome(nome);
			receita.setDescricao(descricao);
			receita.setCategoria(categoria);
			
			//setando valores que serão utilizados para cálculo dos custos e preço de venda da receita
			receita.setRendimento(rendimento);
			receita.setCustoEmbalagem(custoEmbalagem);
			receita.setLucro(lucro);
			receita.setTempoPreparo(tempoPreparo);
			receita.setCustoInicial();
			receita.setPrecoVenda();
			
			//salvar receita no banco de dados
			receitaRepository.save(receita);
			return "redirect:/precofacil/allRecipes";
				}
		}

	// CRUD - update
	@GetMapping(path="/recipe/update/{id}")
	public String alterarReceita(@PathVariable("id") Long id, Model model) {
		Optional<Receita> receitaOpt = receitaRepository.findById(id);
		if (!receitaOpt.isPresent()) {
			throw new IllegalArgumentException("Cadastro invï¿½lido.");
		}
		model.addAttribute("receita", receitaOpt.get());
		return "updateRecipe";
	}
	
	@PostMapping("/recipe/update/{id}/save")
	public String salvarReceita(@Valid @ModelAttribute("receita") Receita receita, @PathVariable("id") Long id, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "updateRecipe";
		}
		Receita recNome = receitaRepository.findByNome(receita.getNome());

		/*Verificação do nome
		 * se for encontrado cadastro com o mesmo nome preenchido no formulario, 
		 * mas com id diferente, recarrega a pagina e exibe mensagem de erro*/
		if ((recNome != null ) && (recNome.getId() != receita.getId())){
			//mensagem de erro
			model.addAttribute("nomeExiste", "Nome já cadastrado para outra receita!");
			//recarrega a página, exibindo a mensagem de erro
			return "updateRecipe";
		}

		/*Verificação por codigo:
		 * se for encontrado cadastro com o mesmo nome preenchido no formulario, mas com id diferente,
		 * recarrega a pagina e exibe mensagem de erro*/
		Receita recCodigo = receitaRepository.findByNome(receita.getNome());
		if ((recCodigo != null ) && (recCodigo.getId() != receita.getId())) {
			//mensagem de erro
			model.addAttribute("codigoExiste", "Código já cadastrado para outra receita!");
			//recarrega a página, exibindo a mensagem de erro
			return "updateRecipe";
		} else {
		receitaRepository.save(receita);
		return "redirect:/precofacil/allRecipes";
		}
	}

	// CRUD - Delete
	@RequestMapping(path="/receita/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		receitaRepository.deleteById(id);
		return "redirect:/precofacil/allRecipes";
	}
}

