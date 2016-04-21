package com.selonj.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderBooking {
  private OrderProducer producer;
  private OrderRepository orderRepository;
  private OrderPolicy orderPolicy;

  public OrderBooking(OrderProducer producer, OrderRepository orderRepository, OrderPolicy orderPolicy) {
    this.producer = producer;
    this.orderRepository = orderRepository;
    this.orderPolicy = orderPolicy;
  }

  public List<Order> create(Item... items) throws OrderException {
    validate(items);
    List<Order> orders = producer.produce(items);
    for (Order order : orders) {
      orderRepository.create(order);
    }
    return orders;
  }

  private void validate(Item[] items) {
    List<ItemViolation> violations = new ArrayList<>();
    for (Item item : items) orderPolicy.checking(item, violations);
    if (!violations.isEmpty()) throw new OrderException(violations);
  }
}
