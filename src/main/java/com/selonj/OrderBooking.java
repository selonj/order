package com.selonj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderBooking {
  private OrderFactory orderFactory;
  private OrderPolicy orderPolicy;

  public OrderBooking(OrderFactory orderFactory, OrderPolicy orderPolicy) {
    this.orderFactory = orderFactory;
    this.orderPolicy = orderPolicy;
  }

  public List<Order> create(Item... items) throws OrderException {
    validate(items);
    List<Order> orders = new ArrayList<>();
    orders.add(orderFactory.create(Arrays.asList(items)));
    return orders;
  }

  private void validate(Item[] items) {
    List<ItemViolation> violations = new ArrayList<>();
    for (Item item : items) orderPolicy.checking(item, violations);
    if (!violations.isEmpty()) throw new OrderException(violations);
  }
}
