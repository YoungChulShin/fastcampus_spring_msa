package dev.practice.order.interfaces.item;

import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

  @Mappings({
      @Mapping(source = "request.itemOptionGroupList", target = "itemOptionGroupRequestList")})
  ItemCommand.RegisterItemRequest of(ItemDto.RegisterItemRequest request);

  @Mappings({@Mapping(source = "request.itemOptionList", target = "itemOptionRequestList")})
  ItemCommand.RegisterItemOptionGroupRequest of(ItemDto.RegisterItemOptionGroupRequest request);

  ItemCommand.RegisterItemOptionRequest of(ItemDto.RegisterItemOptionRequest request);

  ItemDto.RegisterResponse of(String itemToken);

  @Mappings({@Mapping(source = "main.itemOptionGroupInfoList", target = "itemOptionGroupList")})
  ItemDto.Main of(ItemInfo.Main main);

  @Mappings({@Mapping(source = "itemOptionGroup.itemOptionList", target = "itemOptionList")})
  ItemDto.ItemOptionGroupInfo of(ItemInfo.ItemOptionGroupInfo itemOptionGroup);

  ItemDto.ItemOptionInfo of(ItemInfo.ItemOptionInfo itemOption);
}
