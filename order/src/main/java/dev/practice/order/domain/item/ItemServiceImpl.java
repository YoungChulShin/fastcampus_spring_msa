package dev.practice.order.domain.item;

import dev.practice.order.domain.item.ItemCommand.RegisterItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

  @Override
  public String registerItem(RegisterItemRequest request, String partnerToken) {



    return null;
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
