package com.selonj.policy;

import com.selonj.Item;
import com.selonj.ItemInventory;
import com.selonj.ItemViolation;
import com.selonj.OrderPolicy;
import java.util.List;

import static com.selonj.violation.ItemViolations.hasNoEnoughItems;

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
      violations.add(hasNoEnoughItems(item.getItemId(), inventory.getStockQuantityOf(item)));
    }
  }
}
