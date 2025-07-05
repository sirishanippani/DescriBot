package com.siri.descriBot.service;

import com.siri.descriBot.model.Product;
import com.siri.descriBot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OpenAIService openAIService;

    public ProductService(ProductRepository productRepository, OpenAIService openAIService) {
        this.productRepository = productRepository;
        this.openAIService = openAIService;
    }

    public Product createProduct(Product product) {
        product.setDescription("");
        Product savedProduct = productRepository.save(product);

        String description = openAIService.generateDescription(product.getName(), product.getCategory());

        savedProduct.setDescription(description);
        return productRepository.save(savedProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
