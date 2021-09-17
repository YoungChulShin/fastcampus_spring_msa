package dev.practice.order.application.order;

import dev.practice.order.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

  private final OrderService orderService;

}
