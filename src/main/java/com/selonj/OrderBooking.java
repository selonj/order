package com.selonj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderBooking {
  private OrderProducer producer;
  private OrderPolicy orderPolicy;

  public OrderBooking(OrderProducer producer, OrderPolicy orderPolicy) {
    this.producer = producer;
    this.orderPolicy = orderPolicy;
  }

  public List<Order> create(Item... items) throws OrderException {
    validate(items);
    return producer.produce(items);
  }

  private void validate(Item[] items) {
    List<ItemViolation> violations = new ArrayList<>();
    for (Item item : items) orderPolicy.checking(item, violations);
    if (!violations.isEmpty()) throw new OrderException(violations);
  }
}
