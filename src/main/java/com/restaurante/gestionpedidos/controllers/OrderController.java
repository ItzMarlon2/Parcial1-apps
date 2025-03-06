package com.restaurante.gestionpedidos.controllers;

import com.restaurante.gestionpedidos.models.Customer;
import com.restaurante.gestionpedidos.models.Order;
import com.restaurante.gestionpedidos.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing orders.
 * Provides endpoints for CRUD operations on orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructor for injecting the OrderService dependency.
     *
     * @param orderService The service responsible for handling order operations.
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of all orders.
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return ResponseEntity containing the order if found, or a 404 Not Found response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new order.
     *
     * @param order The order details to be created.
     * @return The created order.
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    /**
     * Updates an existing order.
     *
     * @param id    The ID of the order to update.
     * @param order The updated order details.
     * @return ResponseEntity containing the updated order if successful,
     *         or a 400 Bad Request response if an error occurs.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            Order updatedOrder = orderService.updateOrder(id, order);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to delete.
     * @return ResponseEntity with a 204 No Content response if deletion was successful,
     *         or 404 Not Found if the order does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
