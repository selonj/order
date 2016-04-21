package com.selonj;

import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class BasicOrderFactory implements OrderFactory {
  @Override public Order create(List<Item> items) {
    Order order = new Order();
    for (Item item : items) {
      order.add(item);
    }
    return order;
  }
}
