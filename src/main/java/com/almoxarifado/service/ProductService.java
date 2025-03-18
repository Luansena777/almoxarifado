package com.almoxarifado.service;

import com.almoxarifado.exception.DuplicateProductException;
import com.almoxarifado.model.Product;
import com.almoxarifado.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        if ((product.getId() != null && productRepository.existsById(product.getId())) ||
            (product.getName() != null && productRepository.existsByName(product.getName()))) {
            throw new DuplicateProductException("Produto com o mesmo ID ou Nome já existe!");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setQuantity(updatedProduct.getQuantity());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado!");
        }
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


}
