package com.almoxarifado.repository;

import com.almoxarifado.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Procurar por preco maior
    List<Product> findByPriceGreaterThan(Double price);

    boolean existsByName(String nome);

}
