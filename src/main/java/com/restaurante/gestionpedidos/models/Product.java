package com.restaurante.gestionpedidos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * Entity class representing a product in the system.
 * This class maps to the "products" table in the database.
 */
@Entity
@Table(name = "products")
public class Product {

    /**
     * Primary key of the product entity.
     * The ID is auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product. It is a required field (cannot be null).
     */
    @Column(nullable = false)
    private String name;

    /**
     * Price of the product. It is a required field (cannot be null).
     */
    @Column(nullable = false)
    private Double price;

    /**
     * Description of the product. It is a required field (cannot be null).
     */
    @Column(nullable = false)
    private String description;

    /**
     * Many-to-One relationship with the Category entity.
     * Each product belongs to one category.
     * The "category_id" column in the products table serves as the foreign key.
     * The category field cannot be null.
     */
    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * Default constructor.
     * Required by JPA.
     */
    public Product() {
    }

    /**
     * Parameterized constructor to initialize a Product object with values.
     *
     * @param name        The name of the product.
     * @param price       The price of the product.
     * @param description The description of the product.
     * @param category    The category to which the product belongs.
     */
    public Product(String name, Double price, String description, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    // Getters and Setters

    /**
     * Gets the product ID.
     *
     * @return The product ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the product ID.
     *
     * @param id The new product ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The new product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product.
     *
     * @return The product price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new product price.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the description of the product.
     *
     * @return The product description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description The new product description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the category of the product.
     *
     * @return The category to which the product belongs.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     *
     * @param category The new category for the product.
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
