package com.juan.estevez.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador de tipo Administrador.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Controller
@RequestMapping("/administrator")
public class AdministratorController {

	/***
	 * Muestra la página principal del aplicativo.
	 * 
	 * @return vista home.html.
	 */
	@GetMapping("")
	public String home() {
		return "administrator/home";
	}

}
