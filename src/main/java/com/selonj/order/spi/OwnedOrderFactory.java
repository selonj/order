package com.selonj.order.spi;

import com.selonj.order.Item;
import com.selonj.order.Order;
import com.selonj.order.Owner;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OwnedOrderFactory implements OrderFactory<Owner> {
  private OrderNumberGenerator orderNumberGenerator;

  public OwnedOrderFactory(OrderNumberGenerator orderNumberGenerator) {
    this.orderNumberGenerator = orderNumberGenerator;
  }

  @Override public Order create(Owner owner, List<Item> items) {
    Order order = new Order(owner, orderNumberGenerator.generate());
    for (Item item : items) order.add(item);
    return order;
  }
}
