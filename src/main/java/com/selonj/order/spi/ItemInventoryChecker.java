package com.selonj.order.spi;

import com.selonj.order.Item;
import com.selonj.order.ItemViolation;
import com.selonj.order.OrderPolicy;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class ItemInventoryChecker implements OrderPolicy {
  private ItemInventory inventory;

  public ItemInventoryChecker(ItemInventory inventory) {
    this.inventory = inventory;
  }

  @Override public void checking(Item item, List<ItemViolation> violations) {
    if (inventory.hasNoEnough(item)) {
      violations.add(ItemViolations.hasNoEnoughItems(item.getItemId(), inventory.getStockQuantityOf(item)));
    }
  }
}
