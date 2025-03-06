package com.restaurante.gestionpedidos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity class representing an order in the system.
 * This class maps to the "orders" table in the database.
 */
@Entity
@Table(name = "orders")
public class Order {

    /**
     * Primary key of the order entity.
     * The ID is auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many-to-One relationship with the Customer entity.
     * Each order is associated with one customer.
     * The "customer_id" column in the orders table serves as the foreign key.
     *
     * `@JsonBackReference` prevents infinite recursion when serializing customer orders.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Timestamp of when the order was created.
     * The `@Temporal(TemporalType.TIMESTAMP)` annotation ensures that Hibernate
     * maps this field as a timestamp in the database.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    /**
     * One-to-Many relationship with OrderItem entity.
     * Each order can have multiple order items.
     * The "order" field in OrderItem is the mapped entity.
     *
     * `@JsonIgnore` prevents infinite recursion and lazy initialization issues
     * when serializing the order along with its items.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    /**
     * Default constructor.
     * Automatically sets the order date to the current date when an order is created.
     */
    public Order() {
        this.orderDate = new Date();
    }

    /**
     * Parameterized constructor to initialize an Order object with a customer.
     *
     * @param customer The customer placing the order.
     */
    public Order(Customer customer) {
        this.customer = customer;
        this.orderDate = new Date();
    }

    // Getters and Setters

    /**
     * Gets the order ID.
     *
     * @return The order ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the order ID.
     *
     * @param id The new order ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the customer who placed the order.
     *
     * @return The customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer for the order.
     *
     * @param customer The new customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the order date.
     *
     * @return The order date.
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     *
     * @param orderDate The new order date.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the list of items associated with this order.
     *
     * @return The list of order items.
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Sets the list of items for the order.
     *
     * @param orderItems The new list of order items.
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
