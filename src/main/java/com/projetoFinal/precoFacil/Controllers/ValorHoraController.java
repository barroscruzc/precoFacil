package com.projetoFinal.precoFacil.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoFinal.precoFacil.Models.ValorHora;
import com.projetoFinal.precoFacil.Repositories.ValorHoraRepository;

@Controller
@RequestMapping(path = "/precofacil/expense")
public class ValorHoraController {

	
	@Autowired //Comunica com ValorHoraRepository
	private ValorHoraRepository valorHoraRepository;
	
	
	//READ
	@GetMapping("/view")
	public String viewCost(Model model) {
		model.addAttribute("custoFixo", valorHoraRepository.findAll());
		return "viewExpense";
	}
	
	//UPDATE
	@GetMapping("/update/{id}")
	public String updateExpense(@PathVariable("id") Long id, Model model) {
		Optional<ValorHora> custoFixoOpt = valorHoraRepository.findById(id);
		if (!custoFixoOpt.isPresent()) {
			throw new IllegalArgumentException("Cadastro inválido.");
		}
		model.addAttribute("custoFixo", custoFixoOpt.get());
		return "editExpense";
	}
	
	@PostMapping("/update/save")
	public String saveExpense(@ModelAttribute("custoFixo") ValorHora valorHora, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "editExpense";
		}
		valorHora.setValorHora(valorHora.getSalario(), valorHora.getHorasDia());
		valorHoraRepository.save(valorHora);
		return "redirect:/precofacil/expense/view";
	}
	
}
