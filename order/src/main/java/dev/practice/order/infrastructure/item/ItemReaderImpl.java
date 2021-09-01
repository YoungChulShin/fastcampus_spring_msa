package dev.practice.order.infrastructure.item;

import dev.practice.order.common.exception.EntityNotFoundException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemInfo.ItemOptionGroupInfo;
import dev.practice.order.domain.item.ItemInfo.ItemOptionInfo;
import dev.practice.order.domain.item.ItemReader;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {

  private final ItemRepository itemRepository;

  @Override
  public Item getItemBy(String itemToken) {
    return itemRepository.findbyItemToken(itemToken)
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public List<ItemOptionGroupInfo> getItemOptionSeries(Item item) {
    var itemOptionGroupList = item.getItemOptionGroupList();
    return itemOptionGroupList.stream()
        .map(itemOptionGroup -> {
          var itemOptionList = itemOptionGroup.getItemOptionList();
          var itemOptionInfoList = itemOptionList.stream()
              .map(ItemOptionInfo::new)
              .collect(Collectors.toList());

          return new ItemOptionGroupInfo(itemOptionGroup, itemOptionInfoList);
        }).collect(Collectors.toList());
  }
}
