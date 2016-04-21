package com.selonj.order.spi;

import com.selonj.order.ItemViolation;

/**
 * Created by Administrator on 2016-04-21.
 */
public class NoEnoughItems implements ItemViolation {
  private Integer itemId;

  private Integer stockQuantity;

  NoEnoughItems(Integer itemId, Integer stockQuantity) {
    this.itemId = itemId;
    this.stockQuantity = stockQuantity;
  }

  @Override public Integer getItemId() {
    return itemId;
  }

  public Integer getStockQuantity() {
    return stockQuantity;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NoEnoughItems that = (NoEnoughItems) o;

    if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
    return stockQuantity != null ? stockQuantity.equals(that.stockQuantity) : that.stockQuantity == null;
  }

  @Override public int hashCode() {
    int result = itemId != null ? itemId.hashCode() : 0;
    result = 31 * result + (stockQuantity != null ? stockQuantity.hashCode() : 0);
    return result;
  }
}
