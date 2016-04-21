package com.selonj.order.spi;

import com.selonj.order.ItemViolation;

/**
 * Created by Administrator on 2016-04-21.
 */
public class ItemViolations {
  public static ItemViolation hasNoEnoughItems(Integer itemId, Integer stockQuantity) {
    return new NoEnoughItems(itemId, stockQuantity);
  }
}
