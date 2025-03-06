package com.restaurante.gestionpedidos.controllers;

import com.restaurante.gestionpedidos.models.Customer;
import com.restaurante.gestionpedidos.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing customers.
 * Provides endpoints for CRUD operations on customers.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor for injecting the CustomerService dependency.
     *
     * @param customerService The service responsible for handling customer operations.
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves a list of all customers.
     * Optionally includes orders if the "includeOrders" parameter is set to true.
     *
     * @param includeOrders Boolean flag to include associated orders in the response.
     * @return A list of all customers.
     */
    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(required = false, defaultValue = "false") boolean includeOrders) {
        if (includeOrders) {
            return customerService.getAllCustomers();
        } else {
            return customerService.getAllCustomers();
        }
    }

    /**
     * Retrieves a specific customer by its ID.
     * Optionally includes orders if the "includeOrders" parameter is set to true.
     *
     * @param id            The ID of the customer to retrieve.
     * @param includeOrders Boolean flag to include associated orders in the response.
     * @return ResponseEntity containing the customer if found, or a 404 Not Found response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean includeOrders) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new customer.
     *
     * @param customer The customer details to be created.
     * @return The created customer.
     */
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * Updates an existing customer.
     *
     * @param id       The ID of the customer to update.
     * @param customer The updated customer details.
     * @return ResponseEntity containing the updated customer if successful,
     *         or a 404 Not Found response if the customer does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a customer by its ID.
     *
     * @param id The ID of the customer to delete.
     * @return ResponseEntity with a 204 No Content response if deletion was successful,
     *         or 404 Not Found if the customer does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
