package com.personal.store.repositories;

import com.personal.store.orders.Order;
import com.personal.store.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> getOrdersWithItems(@Param("customer") User customer);

    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);

}