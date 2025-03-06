package com.restaurante.gestionpedidos.services;

import com.restaurante.gestionpedidos.models.OrderItem;
import com.restaurante.gestionpedidos.models.Product;
import com.restaurante.gestionpedidos.repositories.OrderItemRepository;
import com.restaurante.gestionpedidos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    // Get all order items
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get an order item by ID
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    // Create a new order item
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Update an order item
    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        return orderItemRepository.findById(id).map(orderItem -> {
            orderItem.setQuantity(orderItemDetails.getQuantity());

            if (orderItemDetails.getProduct() != null && orderItemDetails.getProduct().getId() != null) {
                Product product = productRepository.findById(orderItemDetails.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                orderItem.setProduct(product);
            }

            return orderItemRepository.save(orderItem);
        }).orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    // Delete an order item
    public boolean deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

