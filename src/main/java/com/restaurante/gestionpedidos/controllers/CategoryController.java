package com.restaurante.gestionpedidos.controllers;

import com.restaurante.gestionpedidos.models.Category;
import com.restaurante.gestionpedidos.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing product categories.
 * Provides endpoints for CRUD operations on categories.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Constructor for injecting the CategoryService dependency.
     *
     * @param categoryService The service responsible for handling category operations.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves a list of all categories.
     * Optionally includes associated products if the "includeProducts" parameter is set to true.
     *
     * @param includeProducts Boolean flag to include associated products in the response.
     * @return A list of all categories.
     */
    @GetMapping
    public List<Category> getAllCategories(@RequestParam(required = false, defaultValue = "false") boolean includeProducts) {
        return categoryService.getAllCategories(includeProducts);
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return ResponseEntity containing the category if found, or a 404 Not Found response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new category.
     *
     * @param category The category details to be created.
     * @return The created category.
     */
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Updates an existing category.
     *
     * @param id       The ID of the category to update.
     * @param category The updated category details.
     * @return ResponseEntity containing the updated category if successful,
     *         or a 404 Not Found response if the category does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return ResponseEntity with a 204 No Content response if deletion was successful,
     *         or 404 Not Found if the category does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
