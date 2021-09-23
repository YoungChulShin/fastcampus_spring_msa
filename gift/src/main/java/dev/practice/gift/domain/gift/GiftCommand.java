package dev.practice.gift.domain.gift;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class GiftCommand {

  @Getter
  @Builder
  @ToString
  public static class Accept {

    private final String giftToken;
    private final String receiverName;
    private final String receiverPhone;
    private final String receiverZipcode;
    private final String receiverAddress1;
    private final String receiverAddress2;
    private final String etcMessage;
  }
}
