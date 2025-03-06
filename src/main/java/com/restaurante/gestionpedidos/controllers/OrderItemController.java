package com.restaurante.gestionpedidos.controllers;

import com.restaurante.gestionpedidos.models.OrderItem;
import com.restaurante.gestionpedidos.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing order items.
 * Provides endpoints for CRUD operations on order items.
 */
@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    /**
     * Constructor for injecting the OrderItemService dependency.
     *
     * @param orderItemService The service responsible for handling order item operations.
     */
    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Retrieves a list of all order items.
     *
     * @return A list of all order items.
     */
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    /**
     * Retrieves a specific order item by its ID.
     *
     * @param id The ID of the order item to retrieve.
     * @return ResponseEntity containing the order item if found, or a 404 Not Found response if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new order item.
     *
     * @param orderItem The order item details to be created.
     * @return The created order item.
     */
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.createOrderItem(orderItem);
    }

    /**
     * Updates an existing order item.
     *
     * @param id The ID of the order item to update.
     * @param orderItem The updated order item details.
     * @return ResponseEntity containing the updated order item if successful,
     *         or a 400 Bad Request response if an error occurs.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        try {
            OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItem);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Deletes an order item by its ID.
     *
     * @param id The ID of the order item to delete.
     * @return ResponseEntity with a 204 No Content response if deletion was successful,
     *         or 404 Not Found if the order item does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        return orderItemService.deleteOrderItem(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
