package com.selonj.spi;

import com.selonj.ItemViolation;

/**
 * Created by Administrator on 2016-04-21.
 */
public class ItemViolations {
  public static ItemViolation hasNoEnoughItems(Integer itemId, Integer stockQuantity) {
    return new NoEnoughItems(itemId, stockQuantity);
  }
}
