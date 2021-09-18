package dev.practice.order.domain.order;

import dev.practice.order.domain.order.OrderCommand.PaymentRequest;
import dev.practice.order.domain.order.OrderCommand.RegisterOrder;
import dev.practice.order.domain.order.OrderInfo.Main;
import dev.practice.order.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderStore orderStore;
  private final OrderReader orderReader;
  private final OrderItemSeriesFactory orderItemSeriesFactory;
  private final PaymentProcessor paymentProcessor;
  private final OrderInfoMapper orderInfoMapper;

  @Override
  @Transactional
  public String registerOrder(RegisterOrder registerOrder) {
    // Command로부터 오더 생성
    var order = registerOrder.toEntity();
    // 오더 저장
    orderStore.store(order);
    // 오더 아이템 저장
    orderItemSeriesFactory.store(order, registerOrder);
    // 회신
    return order.getOrderToken();
  }

  @Override
  @Transactional
  public void paymentOrder(PaymentRequest paymentRequest) {
    // order token 확인
    var orderToken = paymentRequest.getOrderToken();
    // order 조회
    var order = orderReader.getOrder(orderToken);
    // 결제
    paymentProcessor.pay(order, paymentRequest);
    // 완료 처리
    order.orderComplete();
  }

  @Override
  @Transactional(readOnly = true)
  public Main retrieveOrder(String orderToken) {
    // order 조회
    var order = orderReader.getOrder(orderToken);
    // order item list 조회
    var orderItemList = order.getOrderItemList();
    // 응답 생성
    return orderInfoMapper.of(order, orderItemList);
  }
}
