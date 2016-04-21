package com.selonj.spi;

import com.selonj.Item;
import com.selonj.Order;
import com.selonj.OrderFactory;
import com.selonj.OrderNumberGenerator;
import com.selonj.Owner;
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
