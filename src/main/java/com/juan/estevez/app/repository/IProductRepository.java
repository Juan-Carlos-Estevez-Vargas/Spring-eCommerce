package com.juan.estevez.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.juan.estevez.app.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
