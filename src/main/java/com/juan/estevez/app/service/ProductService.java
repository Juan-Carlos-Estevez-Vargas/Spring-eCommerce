package com.juan.estevez.app.service;

import java.util.Optional;
import com.juan.estevez.app.model.Product;

public interface ProductService {

	/**
	 * Inserta un producto en la base de datos.
	 * 
	 * @param product a insertar.
	 * @return producto insertado.
	 */
	public Product save(Product product);

	/**
	 * Obtiene un producto en específico desde la base de datos.
	 * 
	 * @param id por el cuál se buscará y recuperará el producto.
	 * @return producto encontrado.
	 */
	public Optional<Product> get(Integer id);

	/**
	 * Actualiza un producto en la base de datos.
	 * 
	 * @param product a actualizar.
	 */
	public void update(Product product);

	/**
	 * Elimina un producto de la base de datos.
	 * 
	 * @param id por el cuál se eliminará el producto.
	 */
	public void delete(Integer id);

}
