package com.selonj;

/**
 * Created by Administrator on 2016-04-21.
 */
public interface ItemInventory {
  boolean hasNoEnough(Item item);

  Integer getStockQuantityOf(Item item);
}
