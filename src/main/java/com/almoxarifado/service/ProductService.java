package com.almoxarifado.service;

import com.almoxarifado.dto.ProductDTO;
import com.almoxarifado.exception.DuplicateProductException;
import com.almoxarifado.exception.ProductNotFoundException;
import com.almoxarifado.model.Product;
import com.almoxarifado.repository.ProductRepository;
<<<<<<< HEAD
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
=======
>>>>>>> origin
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

<<<<<<< HEAD
    public List<ProductDTO> showProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductDTO dto = new ProductDTO();
                    BeanUtils.copyProperties(product, dto);
                    return dto;
=======
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
>>>>>>> origin
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

<<<<<<< HEAD
    @Transactional
    public void deleteProduct(String code) {
        if (!productRepository.existsByCode(code)) {
            throw new ProductNotFoundException("Produto não encontrado com o código: " + code);
        }
        productRepository.delete(findByCode(code));
    }
=======
>>>>>>> origin


}
