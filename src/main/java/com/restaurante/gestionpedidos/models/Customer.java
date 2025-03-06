package com.restaurante.gestionpedidos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity class representing a customer in the system.
 * This class maps to the "customers" table in the database.
 */
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * Primary key of the customer entity.
     * The ID is auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Customer's name. This field is required (cannot be null).
     */
    @Column(nullable = false)
    private String name;

    /**
     * Customer's email address.
     * Must be unique and cannot be null.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Customer's phone number. This field is required (cannot be null).
     */
    @Column(nullable = false)
    private String phone;

    /**
     * One-to-Many relationship with the Order entity.
     * A customer can have multiple orders.
     * The "customer" field in Order is the mapped entity.
     *
     * `@JsonManagedReference` helps handle bidirectional relationships
     * and prevents infinite recursion when serializing customer orders.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    /**
     * Default constructor.
     * Required by JPA.
     */
    public Customer() {
    }

    /**
     * Parameterized constructor to initialize a Customer object with values.
     *
     * @param name  The customer's name.
     * @param email The customer's email address.
     * @param phone The customer's phone number.
     */
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters

    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the customer ID.
     *
     * @param id The new customer ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the customer.
     *
     * @return The customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new customer name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the customer.
     *
     * @return The customer email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the customer.
     *
     * @param email The new customer email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the customer.
     *
     * @return The customer phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone The new customer phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the list of orders associated with this customer.
     *
     * @return The list of orders.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Sets the list of orders for the customer.
     *
     * @param orders The new list of orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
