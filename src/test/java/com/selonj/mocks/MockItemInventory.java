package com.selonj.mocks;

import com.selonj.order.Item;
import com.selonj.order.spi.ItemInventory;

/**
 * Created by Administrator on 2016-04-21.
 */
public class MockItemInventory implements ItemInventory {
  private int totalQuantity;

  public static MockItemInventory totalQuantityOfAnyItems(int quantity) {
    MockItemInventory inventory = new MockItemInventory();
    inventory.setTotalQuantity(quantity);
    return inventory;
  }

  @Override public boolean hasNoEnough(Item item) {
    return item.getQuantity() > totalQuantity;
  }

  @Override public Integer getStockQuantityOf(Item item) {
    return totalQuantity;
  }

  public void setTotalQuantity(int totalQuantity) {
    this.totalQuantity = totalQuantity;
  }
}
