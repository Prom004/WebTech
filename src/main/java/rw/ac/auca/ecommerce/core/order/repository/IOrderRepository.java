package rw.ac.auca.ecommerce.core.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.auca.ecommerce.core.order.model.Order;

import java.util.UUID;

public interface IOrderRepository extends JpaRepository<Order, UUID> {
}
