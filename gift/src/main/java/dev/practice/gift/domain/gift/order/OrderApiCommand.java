package dev.practice.gift.domain.gift.order;

import dev.practice.gift.domain.gift.GiftCommand;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderApiCommand {

  @Getter
  @Builder
  @ToString
  public static class Register {

    private final Long buyerUserId;
    private final String payMethod;
    private final List<GiftCommand.RegisterOrderItem> orderItemList;
  }

  @Getter
  @Builder
  @ToString
  public static class RegisterOrderItem {

    private final String orderCount;
    private final String itemToken;
    private final String itemName;
    private final String itemPrice;
    private final List<GiftCommand.RegisterOrderItemOptionGroup> orderItemOptionGroupList;
  }

  @Getter
  @Builder
  @ToString
  public static class RegisterOrderItemOptionGroup {

    private final Integer ordering;
    private final String itemOptionGroupName;
    private final List<GiftCommand.RegisterOrderItemOption> orderItemOptionList;
  }

  @Getter
  @Builder
  @ToString
  public static class RegisterOrderItemOption {

    private final Integer ordering;
    private final String itemOptionName;
    private final Long itemOptionPrice;
  }
}
