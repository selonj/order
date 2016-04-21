package com.selonj;

import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class BasicOrderFactory implements OrderFactory {
  private OrderNumberGenerator orderNumberGenerator;

  public BasicOrderFactory(OrderNumberGenerator orderNumberGenerator) {
    this.orderNumberGenerator = orderNumberGenerator;
  }

  @Override public Order create(List<Item> items) {
    Order order = new Order(orderNumberGenerator.generate());

    for (Item item : items) {
      order.add(item);
    }
    return order;
  }
}
