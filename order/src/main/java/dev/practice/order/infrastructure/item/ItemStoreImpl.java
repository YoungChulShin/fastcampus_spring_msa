package dev.practice.order.infrastructure.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {

  private final ItemRepository itemRepository;

  @Override
  public Item store(Item initItem) {
    if (StringUtils.isEmpty(initItem.getItemToken())) {
      throw new InvalidParamException("Item.itemToken");
    }
    if (StringUtils.isEmpty(initItem.getItemName())) {
      throw new InvalidParamException("Item.itemName");
    }
    if (initItem.getPartnerId() == null) {
      throw new InvalidParamException("Item.partnerId");
    }
    if (initItem.getItemPrice() == null) {
      throw new InvalidParamException("Item.itemPrice");
    }
    if (initItem.getStatus() == null) {
      throw new InvalidParamException("Item.status");
    }

    return itemRepository.save(initItem);
  }
}
