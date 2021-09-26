package dev.practice.gift.domain.gift;

import dev.practice.gift.domain.gift.GiftCommand.Accept;
import dev.practice.gift.domain.gift.GiftCommand.Register;
import dev.practice.gift.domain.gift.order.OrderApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

  private final GiftStore giftStore;
  private final GiftReader giftReader;
  private final OrderApiCaller orderApiCaller;
  private final GiftToOrderMapper giftToOrderMapper;

  @Override
  public GiftInfo getGiftInfo(String giftToken) {
    var gift = giftReader.getGiftBy(giftToken);
    return new GiftInfo(gift);
  }

  /**
   * 주문 등록
   * 선물하기 서비스에서 주문 서비스로 주문 등록을 요청하고 결과를 선물하기 서비스에 저장
   * - request를 주문 등록을 위한 command로 변경
   * - 주문 등록 -> orderToken
   * - 선물하기 정보 저장 -> gift
   * - gift -> giftInfo
   *
   * @param request
   * @return
   */
  @Override
  @Transactional
  public GiftInfo registerOrder(Register request) {
    var orderCommand = giftToOrderMapper.of(request);
    var orderToken = orderApiCaller.registerGiftOrder(orderCommand);
    var initGift = request.toEntity(orderToken);
    var gift = giftStore.store(initGift);
    return new GiftInfo(gift);
  }

  /**
   * Gift를 조회해서
   * 결제 진행 중 상태로 변경
   *
   * @param giftToken
   */
  @Override
  public void requestPaymentProcessing(String giftToken) {
    var gift = giftReader.getGiftBy(giftToken);
    gift.inPayment();
  }

  /**
   * 주문 서비스에서 결제가 완료되면 OrderToken을 메시지로 발행
   * 선물하기 서비스에서 주문의 결제 상태를 완료로 변경
   * @param orderToken
   */
  @Override
  public void completePayment(String orderToken) {
    var gift = giftReader.getGiftByOrderToken(orderToken);
    gift.completePayment();
  }

  /**
   * 선물 수령자가 배송지 정보를 입력하고 [선물하기] 버튼을 클릭하면
   * 선물 상태를 수락으로 변경하고, 주문 API를 호출해서 배송지 주소를 업데이트
   * @param request
   */
  @Override
  public void acceptGift(Accept request) {
    var giftToken = request.getGiftToken();
    var gift = giftReader.getGiftBy(giftToken);
    gift.accept(request);

    orderApiCaller.updateReceiverInfo(gift.getOrderToken(), request);
  }
}
