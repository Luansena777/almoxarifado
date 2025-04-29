package com.almoxarifado.service;

import com.almoxarifado.dto.ProductDTO;
import com.almoxarifado.exception.DuplicateProductException;
import com.almoxarifado.exception.ProductNotFoundException;
import com.almoxarifado.model.Product;
import com.almoxarifado.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<ProductDTO> showProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    BeanUtils.copyProperties(product, productDTO);
                    return productDTO;
                })
                .collect(Collectors.toList());
    }


    public Product findByCode(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com o código: " + code));
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productRepository.existsByCode(productDTO.getCode())) {
            throw new DuplicateProductException("Já existe um produto com este código!");
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product = productRepository.save(product);

        ProductDTO savedProductDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        return productDTO;
    }

    @Transactional
    public void updateProduct(String code, ProductDTO updatedProduct) {
        Product existingProduct = findByCode(code);

        if (existingProduct == null) {
            throw new ProductNotFoundException("Produto não encontrado com o código: " + code);
        }

        if (updatedProduct.getName() == null || updatedProduct.getQuantity() == null) {
            throw new IllegalArgumentException("Nome e quantidade são obrigatórios.");
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        productRepository.save(existingProduct);

    }

    @Transactional
    public void deleteProduct(String code) {
        if (!productRepository.existsByCode(code)) {
            throw new ProductNotFoundException("Produto não encontrado com o código: " + code);
        }
        productRepository.delete(findByCode(code));
    }

}
