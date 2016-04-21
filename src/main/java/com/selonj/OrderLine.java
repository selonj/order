package com.selonj;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderLine {
  public Integer itemId;

  public OrderLine(Item item) {
    itemId = item.getItemId();
  }
}
