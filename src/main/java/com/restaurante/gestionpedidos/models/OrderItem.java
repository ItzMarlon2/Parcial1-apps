package com.restaurante.gestionpedidos.models;

import jakarta.persistence.*;

/**
 * Entity class representing an item in an order.
 * This class maps to the "order_items" table in the database.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    /**
     * Primary key of the order item entity.
     * The ID is auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many-to-One relationship with the Order entity.
     * Each order item belongs to one order.
     * The "order_id" column in the order_items table serves as the foreign key.
     * This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Many-to-One relationship with the Product entity.
     * Each order item is associated with one product.
     * The "product_id" column in the order_items table serves as the foreign key.
     * This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Quantity of the product in the order.
     * This field cannot be null.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Default constructor.
     * Required by JPA.
     */
    public OrderItem() {
    }

    /**
     * Parameterized constructor to initialize an OrderItem object with values.
     *
     * @param order   The order to which this item belongs.
     * @param product The product associated with this order item.
     * @param quantity The quantity of the product in the order.
     */
    public OrderItem(Order order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters

    /**
     * Gets the order item ID.
     *
     * @return The order item ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the order item ID.
     *
     * @param id The new order item ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the order to which this item belongs.
     *
     * @return The order.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order for this item.
     *
     * @param order The new order.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the product associated with this order item.
     *
     * @return The product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product for this order item.
     *
     * @param product The new product.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the quantity of the product in the order.
     *
     * @return The quantity.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity The new quantity.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
