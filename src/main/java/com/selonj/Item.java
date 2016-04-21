package com.selonj;

/**
 * Created by Administrator on 2016-04-21.
 */
public class Item {
  private Integer itemId;
  private int quantity;
  private Owner owner;

  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }
}
