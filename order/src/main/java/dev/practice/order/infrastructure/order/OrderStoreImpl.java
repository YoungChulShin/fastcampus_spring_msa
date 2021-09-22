package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

  private final OrderRepository orderRepository;

  @Override
  public Order store(Order order) {
    return orderRepository.save(order);
  }
}
