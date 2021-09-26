package dev.practice.gift.infrastructure.gift.order;

import dev.practice.gift.common.response.CommonResponse;
import dev.practice.gift.domain.gift.GiftCommand.Accept;
import dev.practice.gift.domain.gift.order.OrderApiCaller;
import dev.practice.gift.domain.gift.order.OrderApiCommand.Register;
import dev.practice.gift.infrastructure.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderApiCallerImpl implements OrderApiCaller {

  private final RetrofitUtils retrofitUtils;
  private final RetrofitOrderApi retrofitOrderApi;

  @Override
  public String registerGiftOrder(Register request) {
    var call = retrofitOrderApi.registerOrder(request);
    return retrofitUtils.responseSync(call)
        .map(CommonResponse::getData)
        .map(RetrofitOrderApiResponse.Register::getOrderToken)
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public void updateReceiverInfo(String orderToken, Accept request) {
    var call = retrofitOrderApi.updateReceiverInfo(orderToken, request);
    retrofitUtils.responseVoid(call);
  }
}
