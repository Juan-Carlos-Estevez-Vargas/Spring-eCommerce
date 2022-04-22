package com.juan.estevez.app.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.juan.estevez.app.model.Product;
import com.juan.estevez.app.model.User;
import com.juan.estevez.app.service.ProductService;
import com.juan.estevez.app.service.UploadFileService;

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

	@Autowired
	private UploadFileService uploadFileService;

	@Autowired
	private ProductService productService;

	/**
	 * Muestra la lista de productos almacenados en la base de datos.
	 * 
	 * @return vista don los productos encontrados.
	 */
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("products", productService.findAll());
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
	 * @throws IOException
	 */
	@PostMapping("/save")
	public String save(Product product, @RequestParam("img") MultipartFile file) throws IOException {
		LOGGER.info("objeto de producto {}", product.toString());
		User user = new User(1, "", "", "", "", "", "", "");
		product.setUser(user);

		/**
		 * Cuando se crea un nuevo producto.
		 */
		if (product.getId() == null) {
			String nameImg = uploadFileService.saveImage(file);
			product.setImg(nameImg);
		}

		productService.save(product);
		return "redirect:/products";
	}

	/**
	 * Obtiene un producto a editar en el sistema.
	 * 
	 * @param id    por el cuál se buscará el producto.
	 * @param model
	 * @return vista html para editar el producto.
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product product = new Product();
		Optional<Product> optionalProduct = productService.get(id);
		product = optionalProduct.get();
		LOGGER.info("Producto buscado: {}", product);
		model.addAttribute("product", product);
		return "products/edit";
	}

	/**
	 * Actualiza un producto en la base de datos.
	 * 
	 * @param product a actualizar.
	 * @return redirección a la vista donde se listan los productos.
	 * @throws IOException
	 */
	@PostMapping("/update")
	public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {

		Product p = new Product();
		p = productService.get(product.getId()).get();

		/**
		 * Cuando editamos el producto pero no cambiamos la imagen.
		 */
		if (file.isEmpty()) {
			product.setImg(p.getImg());
		} else {

			/**
			 * Eliminar la imagen en caso tal que no sea la imagen por defecto.
			 */
			if (!p.getImg().equals("default.jpg")) {
				uploadFileService.deleteImage(p.getName());
			}
			String nameImg = uploadFileService.saveImage(file);
			product.setImg(nameImg);
		}
		product.setUser(p.getUser());
		productService.update(product);
		return "redirect:/products";
	}

	/**
	 * Se encarga de eliminar un producto del sistema.
	 * 
	 * @param id por el cuál se eliminará el producto.
	 * @return redirección a la vista donde se listan los productos.
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Product p = new Product();
		p = productService.get(id).get();

		/**
		 * Eliminar la imagen en caso tal que no sea la imagen por defecto.
		 */
		if (!p.getImg().equals("default.jpg")) {
			uploadFileService.deleteImage(p.getName());
		}
		productService.delete(id);
		return "redirect:/products";
	}
}
