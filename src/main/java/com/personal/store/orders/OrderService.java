package com.personal.store.orders;

import com.personal.store.repositories.OrderRepository;
import com.personal.store.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getOrders() {
        var user = authService.getCurrentUser();
        var order = orderRepository.getOrdersWithItems(user);
        return order.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItems(orderId).orElseThrow(OrderNotFoundException::new);

        var user = authService.getCurrentUser();
        if (!order.isPlacedBy(user)) {
            throw new AccessDeniedException("You are not allowed to access this order.");
        }

        return orderMapper.toDto(order);
    }
}
