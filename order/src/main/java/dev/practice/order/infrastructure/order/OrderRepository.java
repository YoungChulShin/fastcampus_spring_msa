package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
