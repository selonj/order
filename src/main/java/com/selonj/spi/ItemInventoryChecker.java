package com.selonj.spi;

import com.selonj.Item;
import com.selonj.ItemViolation;
import com.selonj.OrderPolicy;
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
