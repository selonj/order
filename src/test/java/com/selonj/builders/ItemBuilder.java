package com.selonj.builders;

import com.selonj.Item;

/**
 * Created by Administrator on 2016-04-21.
 */
public class ItemBuilder implements Builder<Item> {
  private Integer id = IDs.next();
  private int quantity = 1;

  public Item build() {
    Item item = new Item();
    item.setItemId(id);
    item.setQuantity(quantity);
    return item;
  }

  public ItemBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }
}
