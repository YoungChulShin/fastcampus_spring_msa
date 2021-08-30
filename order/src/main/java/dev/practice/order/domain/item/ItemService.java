package dev.practice.order.domain.item;

public interface ItemService {

  String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken);
  void changeOnSale(String itemToken);
  void changeEndOfSale(String itemToken);
  void retrieveItemInfo(String itemToken);
}
