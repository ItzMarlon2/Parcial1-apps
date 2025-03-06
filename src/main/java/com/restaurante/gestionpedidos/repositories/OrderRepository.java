package com.restaurante.gestionpedidos.repositories;

import com.restaurante.gestionpedidos.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
