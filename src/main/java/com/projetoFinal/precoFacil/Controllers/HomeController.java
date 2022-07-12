package com.projetoFinal.precoFacil.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/precofacil")
public class HomeController {

	@GetMapping("/")
	public String precoFacil() {
		return "precoFacil";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
}
