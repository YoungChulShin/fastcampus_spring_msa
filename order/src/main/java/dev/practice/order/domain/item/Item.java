package dev.practice.order.domain.item;

import dev.practice.order.domain.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String itemToken;
  private Long partnerId;
  private String itemName;
  private Long itemPrice;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Getter
  @RequiredArgsConstructor
  public enum Status {
    PREPARE("판매준비중"),
    ON_SALES("판매중"),
    END_OF_SALES("판매종료");

    private final String description;
  }

  public void changePrepare() {
    this.status = Status.PREPARE;
  }

  public void changeOnSales() {
    this.status = Status.ON_SALES;
  }

  public void changeEndOfSales() {
    this.status = status.END_OF_SALES;
  }

  // 27분
}
