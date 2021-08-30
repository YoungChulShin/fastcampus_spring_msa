package dev.practice.order.domain.item;

import com.google.common.collect.Lists;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@Table(name = "item_option_groups")
public class ItemOptionGroup extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  private Integer Ordering;
  private String itemOptionGroupName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
  private List<ItemOption> itemOptionList = Lists.newArrayList();

  public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
    if (item == null) throw new InvalidParamException("ItemOptionGroup.item");
    if (ordering == null) throw new InvalidParamException("ItemOptionGroup.ordering");
    if (StringUtils.isEmpty(itemOptionGroupName)) throw new InvalidParamException("ItemOptionGroup.itemOptionGroupName");

    this.item = item;
    this.Ordering = ordering;
    this.itemOptionGroupName = itemOptionGroupName;
  }
}
