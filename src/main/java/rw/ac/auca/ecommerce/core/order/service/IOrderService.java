package rw.ac.auca.ecommerce.core.order.service;

import rw.ac.auca.ecommerce.core.order.model.Order;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Order order);
    Order findOrderById(UUID id);
    List<Order> findAllOrders();
}
