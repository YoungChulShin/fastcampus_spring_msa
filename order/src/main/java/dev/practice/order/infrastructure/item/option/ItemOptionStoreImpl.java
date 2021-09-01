package dev.practice.order.infrastructure.item.option;

import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.option.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOptionStoreImpl implements ItemOptionStore {

  private final ItemOptionRepository itemOptionRepository;

  @Override
  public void store(ItemOption initItemOption) {
    itemOptionRepository.save(initItemOption);
  }
}
