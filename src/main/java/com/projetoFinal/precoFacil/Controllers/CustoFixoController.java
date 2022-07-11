package com.projetoFinal.precoFacil.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
