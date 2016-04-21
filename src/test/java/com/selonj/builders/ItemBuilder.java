package com.selonj.builders;

import com.selonj.Item;
import com.selonj.Owner;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.owner;

/**
 * Created by Administrator on 2016-04-21.
 */
public class ItemBuilder implements Builder<Item> {
  private Integer id = IDs.next();
  private int quantity = 1;
  private Owner owner = an(owner());

  public Item build() {
    Item item = new Item();
    item.setItemId(id);
    item.setQuantity(quantity);
    item.setOwner(owner);
    return item;
  }

  public ItemBuilder quantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  public Builder<Item> of(Owner owner) {
    this.owner = owner;
    return this;
  }
}
