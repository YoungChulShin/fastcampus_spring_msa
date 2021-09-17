package dev.practice.order.domain.order;

import dev.practice.order.domain.order.OrderCommand.PaymentRequest;
import dev.practice.order.domain.order.OrderCommand.RegisterOrder;
import dev.practice.order.domain.order.OrderInfo.Main;

public class OrderServiceImpl implements OrderService {

  @Override
  public String registerOrder(RegisterOrder registerOrder) {
    return null;
  }

  @Override
  public void paymentOrder(PaymentRequest paymentRequest) {

  }

  @Override
  public Main retrieveOrder(String orderToken) {
    return null;
  }
}
