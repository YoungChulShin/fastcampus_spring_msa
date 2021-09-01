package dev.practice.order.infrastructure.item;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemCommand.RegisterItemRequest;
import dev.practice.order.domain.item.ItemOptionSeriesFactory;
import dev.practice.order.domain.item.option.ItemOptionStore;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroupStore;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {

  private final ItemOptionGroupStore itemOptionGroupStore;
  private final ItemOptionStore itemOptionStore;

  @Override
  public List<ItemOptionGroup> store(RegisterItemRequest request, Item item) {
    var itemOptionGroupRequestList = request.getItemOptionGroupRequestList();
    if (CollectionUtils.isEmpty(itemOptionGroupRequestList)) return Collections.emptyList();

    return itemOptionGroupRequestList.stream()
        .map(requestItemOptionGroup -> {
          // itemOptionGroup store
          var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
          var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

          requestItemOptionGroup.getItemOptionRequestList().stream().forEach(requestItemOption -> {
            var initItemOption = requestItemOption.toEntity(itemOptionGroup);
            itemOptionStore.store(initItemOption);
          });

          return itemOptionGroup;
        }).collect(Collectors.toList());
  }
}
