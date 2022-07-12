package com.projetoFinal.precoFacil.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetoFinal.precoFacil.Models.CustoFixo;
import com.projetoFinal.precoFacil.Repositories.CustoFixoRepository;

@Controller
@RequestMapping(path = "/precofacil/expense")
public class CustoFixoController {

	
	@Autowired //Comunica com CustoFixoRepository
	private CustoFixoRepository custoFixoRepository;
	
	
	//READ
	@GetMapping("/view")
	public String viewCost(Model model) {
		model.addAttribute("custoFixo", custoFixoRepository.findById((long)1));
		return "viewExpense";
	}
	
	//create
	@GetMapping("/add")
	public String addExpense(Model model) {
		CustoFixo custoFixo = new CustoFixo();
		model.addAttribute("custoFixo", custoFixo);
		return "addExpense";
	}
	
	
	@PostMapping("/add")
	public String saveExpense(@RequestParam Float salario, @RequestParam Integer horasDia, Model model) {

		// realiza o encapsulamento dos dados
		CustoFixo custoFixo = new CustoFixo();
		custoFixo.setSalario(salario);
		custoFixo.setHorasDia(horasDia);
		custoFixo.setValorHora(salario, horasDia);
		// salvar o novo Usuario no banco
		custoFixoRepository.save(custoFixo);;
		return "viewExpense";
	}
	
	//UPDATE
	@GetMapping("/update")
	public String updateExpense(Model model) {
		model.addAttribute("custoFixo", custoFixoRepository.findById((long)1));
		return "editExpense";
	}
	
	@PostMapping("/update/save")
	public String saveExpense(CustoFixo custoFixo) {
		custoFixoRepository.save(custoFixo);
		return "redirect:/precofacil/expense/view";
	}
	
}
