package com.selonj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderBooking {
  private OrderProjection<?> projection;
  private OrderFactory<Object> orderFactory;
  private OrderPolicy orderPolicy;

  public <G> OrderBooking(OrderProjection<G> projection, OrderFactory<G> orderFactory, OrderPolicy orderPolicy) {
    this.projection = projection;
    this.orderFactory = (OrderFactory<Object>) orderFactory;
    this.orderPolicy = orderPolicy;
  }

  public List<Order> create(Item... items) throws OrderException {
    validate(items);
    Map<?, List<Item>> groups = projection.grouping(items);
    List<Order> orders = new ArrayList<>();
    for (Map.Entry<?, List<Item>> group : groups.entrySet()) {
      orders.add(orderFactory.create(group.getKey(), group.getValue()));
    }
    return orders;
  }

  private void validate(Item[] items) {
    List<ItemViolation> violations = new ArrayList<>();
    for (Item item : items) orderPolicy.checking(item, violations);
    if (!violations.isEmpty()) throw new OrderException(violations);
  }
}
