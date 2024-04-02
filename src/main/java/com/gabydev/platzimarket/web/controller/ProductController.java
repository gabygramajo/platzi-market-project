package com.gabydev.platzimarket.web.controller;

import com.gabydev.platzimarket.domain.Product;
import com.gabydev.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

// @RestController -> indica que será un controlador de una apirest
// @RequestMapping -> indica en qué ruta se realizará las peticiones, en este caso "/products"
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    public List<Product> getAll() {
        return productService.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productService.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productService.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productService.save(product);
    }
    public boolean delete(int productId) {
        return productService.delete(productId);
    }
}
