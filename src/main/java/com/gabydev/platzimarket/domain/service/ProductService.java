package com.gabydev.platzimarket.domain.service;

import com.gabydev.platzimarket.domain.Product;
import com.gabydev.platzimarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Tambien se podria utilizar @Component, pero @Service es más semántico
@Service
public class ProductService {
    // con Autowired utilizará la clase ProductoRepository, que es su implementación
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        //como getProduct utiliza Optional, podemos hacer uso de el método isPresent()
        if(getProduct(productId).isPresent()) {
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }
        // Otra opción seria utilizando map. Si existe, se ejecuta sino se ejecuta orElse
        /*
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
        */
    }
}
