package dev.practice.order.domain.item;

import dev.practice.order.domain.item.ItemCommand.RegisterItemRequest;
import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.option.ItemOptionStore;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroupStore;
import dev.practice.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final PartnerReader partnerReader;
  private final ItemStore itemStore;
  private final ItemOptionSeriesFactory itemOptionSeriesFactory;

  @Override
  public String registerItem(RegisterItemRequest command, String partnerToken) {
    var partner = partnerReader.getPartner(partnerToken);
    var initItem = command.toEntity(partner.getId());
    Item item = itemStore.store(initItem);
    itemOptionSeriesFactory.store(command, item);
    return item.getItemToken();
  }

  @Override
  public void changeOnSale(String itemToken) {

    // 20:22
  }

  @Override
  public void changeEndOfSale(String itemToken) {

  }

  @Override
  public void retrieveItemInfo(String itemToken) {

  }
}
