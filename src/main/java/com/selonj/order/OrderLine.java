package com.selonj.order;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderLine {
  private Integer itemId;

  public OrderLine(Item item) {
    itemId = item.getItemId();
  }

  public Integer getItemId() {
    return itemId;
  }

  public boolean sameItem(Item item) {
    return itemId.equals(item.getItemId());
  }
}
