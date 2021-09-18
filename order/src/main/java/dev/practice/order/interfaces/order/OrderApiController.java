package dev.practice.order.interfaces.order;

import dev.practice.order.application.order.OrderFacade;
import dev.practice.order.common.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderApiController {

  private final OrderFacade orderFacade;
  private final OrderDtoMapper orderDtoMapper;

  @PostMapping("/init")
  public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request) {
    var command = orderDtoMapper.of(request);
    String orderToken = orderFacade.registerOrder(command);
    var response = orderDtoMapper.of(orderToken);
    return CommonResponse.success(response);
  }

  @GetMapping("/{orderToken}")
  public CommonResponse retrieveOrder(@PathVariable String orderToken) {
    var orderResult = orderFacade.retriveOrder(orderToken);
    var response = orderDtoMapper.of(orderResult);
    return CommonResponse.success(response);
  }

  @PostMapping("/payment-order")
  public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest request) {
    var commnad = orderDtoMapper.of(request);
    orderFacade.paymentOrder(commnad);
    return CommonResponse.success("OK");
  }


}
