package dev.practice.order.infrastructure.order;

import dev.practice.order.domain.item.ItemReader;
import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand.RegisterOrder;
import dev.practice.order.domain.order.OrderItemSeriesFactory;
import dev.practice.order.domain.order.OrderStore;
import dev.practice.order.domain.order.item.OrderItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {

  private final ItemReader itemReader;
  private final OrderStore orderStore;

  @Override
  public List<OrderItem> store(Order order, RegisterOrder requestOrder) {
//    requestOrder.getOrderItemList().stream()
//        .map(orderItemRequest -> {
//          var item = itemReader.getItemBy()
//        })
//

    return null;
  }
}
