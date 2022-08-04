package com.projetoFinal.precoFacil.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetoFinal.precoFacil.Models.Ingrediente;
import com.projetoFinal.precoFacil.Models.Medida;
import com.projetoFinal.precoFacil.Models.Receita;
import com.projetoFinal.precoFacil.Repositories.IngredienteRepository;
import com.projetoFinal.precoFacil.Repositories.ReceitaRepository;
//import com.projetoFinal.precoFacil.Repositories.MedidaRepository;
import com.projetoFinal.precoFacil.Repositories.ValorHoraRepository;


@Controller // Informa que classe UserController eh o nosso controller
@RequestMapping(path = "/precofacil/recipe")
public class ReceitaController {

	@Autowired // Comunica com o UserRepository
	private ReceitaRepository receitaRepository;
	@Autowired
	private IngredienteRepository ingredienteRepository;
	@Autowired
	private ValorHoraRepository valorHoraRepository;

	//Controller Receitas
	// CRUD - Read
	@RequestMapping(path = "/all")
	public String listaUsers(Model model) {
		model.addAttribute("receita", receitaRepository.findAll());
		return "allRecipes";
	}

	// CRUD - Create
	@GetMapping("/new")
	public String newRecipe(Model model) {
		model.addAttribute("receita", new Receita());
		//O cálculo do valorHora é feito pela Model ValorHora(linha 50), e pode ser representado pela expressão: ValorHora = Salário / 20 / HorasDia
		model.addAttribute("valorHora", valorHoraRepository.findById((long)1).get().getValorHora());
		return "newRecipe";
	}

	
	@PostMapping("/new")
	public String addRecipe(@RequestParam Integer codigo,@RequestParam String nome,@RequestParam String descricao,@RequestParam Integer rendimento,@RequestParam Float embalagem,
			@RequestParam Float lucro, @RequestParam String categoria, @RequestParam Integer tempoPreparo, Model model) {
		
		//verifica se já consta receita cadastrada com os dados preenchidos no formulário de cadastro
		
		//Verificação por nome
		Receita recNome = receitaRepository.findByNome(nome);
		if (recNome != null) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Receita já cadastrada!");
			//recarrega a página, exibindo a mensagem de erro
			return "newRecipe";
		}

		//Verificação por Codigo
		Receita recCodigo = receitaRepository.findByCodigo(codigo);
		if (recCodigo != null) {
			//mensagem de erro
			model.addAttribute("codigoExiste", "Código de receita já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "newRecipe";
		} else {
			Receita receita = new Receita();
			
			//setando elementos descritivos e identificadores da receita
			receita.setCodigo(codigo);
			receita.setNome(nome);
			receita.setDescricao(descricao);
			receita.setCategoria(categoria);
			
			//setando valores que serão utilizados para cálculo dos custos e preço de venda da receita
			receita.setRendimento(rendimento);
			receita.setEmbalagem(embalagem);
			receita.setTempoPreparo(tempoPreparo);
			receita.setLucro(lucro);
			receita.setValorHora(valorHoraRepository.findById((long)1).get());
			
			//salvar receita no banco de dados
			receitaRepository.saveAndFlush(receita);
			
			//envia os dados da nova receita para a próxima página;
			model.addAttribute("todosIngredientes", ingredienteRepository.findAll());
			model.addAttribute("medida", new Medida());
			model.addAttribute("receitaNome", receita.getNome());
			model.addAttribute("receitaId", receita.getId());
			model.addAttribute("receitaDescricao", receita.getDescricao());
			
			return "addIngredientToRecipe";
				}
		}

	@PostMapping(path="/add/ingredient/save")
	public String addMedida(@RequestParam Long idIngrediente, @RequestParam Float quantidade, @RequestParam Long idReceita, Model model) {
		
		receitaRepository.insertMedida(idReceita, quantidade, idIngrediente);
		
		Receita receita = receitaRepository.findById(idReceita).get();
		model.addAttribute("todosIngredientes", ingredienteRepository.findAll());
		model.addAttribute("medida", new Medida());
		model.addAttribute("receitaNome", receita.getNome());
		model.addAttribute("receitaId", idReceita);
		model.addAttribute("receitaDescricao", receita.getDescricao());
		model.addAttribute("listaIng", receitaRepository.findById(idReceita).get().getMedida());		
		return "addIngredientToRecipe";
		}
	
	@RequestMapping(path="add/ingredient/delete/{idIngrediente}/{idReceita}")
	public String removeIng(@PathVariable String idIngrediente, @PathVariable Long idReceita, Model model) {
		System.out.println("Ingrediente: " + idIngrediente + " receita: " + idReceita);
		receitaRepository.deleteMedida(idReceita, Long.parseLong(idIngrediente));
		
		
		Receita receita = receitaRepository.findById(idReceita).get();
		model.addAttribute("todosIngredientes", ingredienteRepository.findAll());
		model.addAttribute("medida", new Medida());
		model.addAttribute("receitaNome", receita.getNome());
		model.addAttribute("receitaId", idReceita);
		model.addAttribute("receitaDescricao", receita.getDescricao());
		model.addAttribute("listaIng", receitaRepository.findById(idReceita).get().getMedida());		
		return "addIngredientToRecipe";
	}
	
	@GetMapping(path="/new/{idReceita}/save")
	public String setPrice(@PathVariable("idReceita") String idReceita, Model model) {
		
		Receita receita = receitaRepository.findById(Long.parseLong(idReceita)).get();
		System.out.println("Receita: " + receita.getNome());
		
		
		/* CUSTO INICIAL - calculando o valor dos ingredientes conforme as quantidades de cada item usadas na receita e o custo da embalagem por total de unidades produzidas
		 * ITERANDO SOBRE O HASHMAP PARA SOMAR OS VALORES DOS INGREDIENTES USADOS
		 *
		 *O custo de cada ingrediente é igual ao seu preço multiplicado pelo custo por unidade ou medida .
		 * Deste modo, por exemplo, o custo de 500g de chocolate será igual ao valor do kg do produto 
		 * (que é o atributo preco do objeto ingrediente) multiplicado pela quantidade usada na receita
		 * (que será um valor Float 0.5, inserido no hashMap como value para a key Ingrediente
		 * Ingredientes que possuem unidade de medida por peso ou líquido serão recebidos como Float da seguinte maneira:
		 * cada algarismo na parte inteira do Float corresponte a 1kg ou 1L, enquanto os algarismos da parte fracionária 
		 * (após a vígula) são frações de Litro ou kg. Assim, o chocolate do exemplo acima seria representado 
		 * por 0.5, ou, se fossem 250g, 0.25, e assim por diante
		 * Para entender melhor a iteração sobre o Map abaixo --> https://www.alura.com.br/artigos/iterando-por-um-hashmap-em-java
		 * */
		
		List<Float>valorIng = new ArrayList<>();
		valorIng.add((float) 0);
		Float somaIngredientes = (float) 0;
		Map<Ingrediente, Float> medida = receita.getMedida();
		Float embalagem = receita.getEmbalagem();
		Integer rendimento = receita.getRendimento();
		medida.forEach((k, v) -> {
			valorIng.add(k.getPreco() * v);  //for(Map.Entry<Ingrediente, Float> item : medida.entrySet()) {
		});
		
		for (int i = 0; i < valorIng.size(); i++) {
			somaIngredientes += valorIng.get(i);
		}
           
		
		Float custoInicial = somaIngredientes + (embalagem * rendimento);
		receita.setCustoInicial(custoInicial);
		
		/*VALOR DE VENDA 
		 * é necessário considerar os custos fixos devido aos gastos com contas como gás, água, eletricidade etc, por isso o acréscimo de 1/3 ao preco de venda */
		Integer tempoPreparo = receita.getTempoPreparo();
		Float valorHora = receita.getValorHora();
		Float lucro = receita.getLucro();
		Float custosFixos = custoInicial / 3;
		Float precoVenda = (custoInicial * lucro + tempoPreparo * valorHora ) + custosFixos;
		receita.setPrecoVenda(precoVenda);
		
		
		receitaRepository.save(receita);
		model.addAttribute("receita", receita);
		
		return "finalizeRegistration";
	}
	
	@PutMapping("/new/save")
	public String finalAddRecipe(@ModelAttribute("receita") Receita receita, Model model) {
		
		receitaRepository.save(receita);
		
		return "redirect:/precofacil/recipe/all";
	}
	
	@PostMapping(path="/new/{idReceita}/cancel")
	
	
	// CRUD - update
	@GetMapping(path="/update/{id}")
	public String alterarReceita(@PathVariable("id") Long id, Model model) {
		Optional<Receita> receitaOpt = receitaRepository.findById(id);
		if (!receitaOpt.isPresent()) {
			throw new IllegalArgumentException("Cadastro invállido.");
		}
		model.addAttribute("receita", receitaOpt.get());
		return "updateRecipe";
	}
	

	// CRUD - Delete
	@RequestMapping(path="/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		receitaRepository.deleteById(id);
		return "redirect:/precofacil/recipe/all";
	}
	
}
