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
  private final ItemOptionGroupStore itemOptionGroupStore;
  private final ItemOptionStore itemOptionStore;

  @Override
  public String registerItem(RegisterItemRequest command, String partnerToken) {
    // 1. Get Partner Id
    var partner = partnerReader.getPartner(partnerToken);
    var partnerId = partner.getId();

    // 2. item store
    var initItem = command.toEntity(partnerId);
    Item item = itemStore.store(initItem);

    // 3. itemOptionGroup + itemOption store
    command.getItemOptionGroupRequestList().forEach(requestItemOptionGroup -> {
      // itemOptionGroup store
      var initItemOptionGroup = ItemOptionGroup.builder()
          .item(item)
          .ordering(requestItemOptionGroup.getOrdering())
          .itemOptionGroupName(requestItemOptionGroup.getItemOptionGroupName())
          .build();
      var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

      // ItemOption store
      requestItemOptionGroup.getItemOptionRequestList().forEach(requestItemOption -> {
        var initItemOption = ItemOption.builder()
            .itemOptionGroup(itemOptionGroup)
            .ordering(requestItemOption.getOrdering())
            .itemOptionName(requestItemOption.getItemOptionName())
            .itemOptionPrice(requestItemOption.getItemOptionPrice())
            .build();
        itemOptionStore.store(initItemOption);
      });
    });

    // 4. Return itemtoken
    return item.getItemToken();
  }

  @Override
  public void changeOnSale(String itemToken) {

  }

  @Override
  public void changeEndOfSale(String itemToken) {

  }

  @Override
  public void retrieveItemInfo(String itemToken) {

  }
}
