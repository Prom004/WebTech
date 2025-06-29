package rw.ac.auca.ecommerce.core.order.service;

import lombok.RequiredArgsConstructor;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import rw.ac.auca.ecommerce.core.order.model.Order;
import rw.ac.auca.ecommerce.core.order.repository.IOrderRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        order.setActive(Boolean.FALSE);
        orderRepository.save(order);
    }

    @Override
    public Order findOrderById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
