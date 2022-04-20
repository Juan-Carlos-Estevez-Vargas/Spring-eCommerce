package com.juan.estevez.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.juan.estevez.app.model.Product;

/**
 * Repositorio (DAO) de la entidad Producto manejada por Data JPA.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
