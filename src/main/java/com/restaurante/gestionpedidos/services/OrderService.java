package com.restaurante.gestionpedidos.services;

import com.restaurante.gestionpedidos.models.Customer;
import com.restaurante.gestionpedidos.models.Order;
import com.restaurante.gestionpedidos.repositories.CustomerRepository;
import com.restaurante.gestionpedidos.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Create a new order (ensure the customer exists)
    public Order createOrder(Order order) {
        Optional<Customer> customer = customerRepository.findById(order.getCustomer().getId());

        if (customer.isPresent()) {
            order.setCustomer(customer.get());  // Load full Customer object
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Customer with ID " + order.getCustomer().getId() + " not found.");
        }
    }

    //Update an order

    public Order updateOrder(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            if (orderDetails.getOrderDate() != null) {
                order.setOrderDate(orderDetails.getOrderDate());
            }

            if (orderDetails.getCustomer() != null && orderDetails.getCustomer().getId() != null) {
                Customer customer = customerRepository.findById(orderDetails.getCustomer().getId())
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
                order.setCustomer(customer);
            }

            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Delete an order
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}