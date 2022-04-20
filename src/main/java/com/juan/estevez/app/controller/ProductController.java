package com.juan.estevez.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juan.estevez.app.model.Product;

/**
 * Controlador de tipo producto.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	/**
	 * Muestra la lista de productos almacenados en la base de datos.
	 * 
	 * @return vista don los productos encontrados.
	 */
	@GetMapping("")
	public String show() {
		return "products/show";
	}

	/**
	 * Vista con los campos de texto a rellenar con la información del producto a
	 * registrar.
	 * 
	 * @return vista para crear el producto.
	 */
	@GetMapping("/create")
	public String create() {
		return "products/create";
	}

	/**
	 * Guarda un producto en el sistema de información.
	 * 
	 * @param product a insertar en el sistema.
	 * @return redirección a la vista productos donde se mustran los productos
	 *         existentes en el mercado.
	 */
	@PostMapping("/save")
	public String save(Product product) {
		LOGGER.info("objeto de producto {}", product.toString());
		return "redirect:/products";
	}

}
