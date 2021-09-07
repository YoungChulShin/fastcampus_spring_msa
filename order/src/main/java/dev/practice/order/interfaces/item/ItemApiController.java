package dev.practice.order.interfaces.item;

import dev.practice.order.application.item.ItemFacade;
import dev.practice.order.common.response.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemApiController {

  private final ItemFacade itemFacade;

  @PostMapping
  public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {

  }

  @PostMapping("/change-on-sales")
  public CommonResponse changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {

  }

  @PostMapping("/change-end-of-sales")
  public CommonResponse changeEndOfSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request) {


  }

  @GetMapping("/{itemToken}")
  public CommonResponse retrieve(@PathVariable("itemToken") String itemToken) {

  }
}
