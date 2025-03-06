package com.restaurante.gestionpedidos.controllers;

import com.restaurante.gestionpedidos.models.Product;
import com.restaurante.gestionpedidos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing products.
 * Provides endpoints for CRUD operations on products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructor for injecting the ProductService dependency.
     *
     * @param productService The service responsible for handling product operations.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves a list of all products.
     * Optionally includes category details if "includeCategory" parameter is set to true.
     *
     * @param includeCategory Boolean flag to include category information.
     * @return A list of products.
     */
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false, defaultValue = "false") boolean includeCategory) {
        if(includeCategory){
            return productService.getAllProducts();
        }else{
            return productService.getAllProducts();
        }
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return ResponseEntity containing the product if found, or a 404 Not Found response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new product.
     *
     * @param product The product details to be created.
     * @return The created product.
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id The ID of the product to update.
     * @param product The updated product details.
     * @return ResponseEntity containing the updated product if found, or a 404 Not Found response if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return ResponseEntity with a 204 No Content response if deletion was successful, or 404 Not Found if the product does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
