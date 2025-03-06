package com.restaurante.gestionpedidos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

/**
 * Entity class representing a product category in the system.
 * This class maps to the "categories" table in the database.
 */
@Entity
@Table(name = "categories")
public class Category {

    /**
     * Primary key of the category entity.
     * The ID is auto-generated using the identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     * It must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * One-to-Many relationship with the Product entity.
     * A category can have multiple products.
     * The "category" field in Product is the mapped entity.
     *
     * `@JsonIgnore` prevents infinite recursion and lazy initialization issues
     * when serializing a category along with its products.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products;

    /**
     * Default constructor.
     * Required by JPA.
     */
    public Category() {
    }

    /**
     * Parameterized constructor to initialize a Category object with values.
     *
     * @param name The category name.
     */
    public Category(String name) {
        this.name = name;
    }

    // Getters and Setters

    /**
     * Gets the category ID.
     *
     * @return The category ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the category ID.
     *
     * @param id The new category ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return The category name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The new category name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of products associated with this category.
     *
     * @return The list of products.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products for the category.
     *
     * @param products The new list of products.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
