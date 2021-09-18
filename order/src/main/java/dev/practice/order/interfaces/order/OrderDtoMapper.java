package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

  OrderCommand.RegisterOrder of(OrderDto.RegisterOrderRequest request);

  OrderCommand.RegisterOrderItem of(OrderDto.RegisterOrderItemRequest request);

  OrderCommand.RegisterOrderItemOptionGroup of(OrderDto.RegisterOrderItemOptionGroupRequest request);

  OrderCommand.RegisterOrderItemOption of(OrderDto.RegisterOrderItemOptionRequest request);

  OrderDto.RegisterOrderResponse of(String orderToken);

  @Mappings({
      @Mapping(source = "orderedAt", target = "orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
  })
  OrderDto.Main of(OrderInfo.Main mainResult);

  OrderDto.DeliveryInfo of(OrderInfo.DeliveryInfo deliveryInfoResult);

  OrderDto.OrderItem of(OrderInfo.OrderItem orderItemResult);

  OrderDto.OrderItemOptionGroup of(OrderInfo.OrderItemOptionGroup orderItemOptionGroupResult);

  OrderDto.OrderItemOption of(OrderInfo.OrderItemOption orderItemOptionResult);

  OrderCommand.PaymentRequest of(OrderDto.PaymentRequest request);
}
