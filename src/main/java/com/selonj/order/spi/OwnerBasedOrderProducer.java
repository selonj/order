package com.selonj.order.spi;

import com.selonj.order.Item;
import com.selonj.order.Order;
import com.selonj.order.OrderProducer;
import com.selonj.order.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by L.x on 16-4-21.
 */
public class OwnerBasedOrderProducer implements OrderProducer {
  private OrderFactory<Owner> orderFactory;
  private OrderProjection<Owner> projection;

  public OwnerBasedOrderProducer(OrderFactory<Owner> orderFactory, OrderProjection<Owner> projection) {
    this.orderFactory = orderFactory;
    this.projection = projection;
  }

  public static OwnerBasedOrderProducer newInstance(OrderNumberGenerator orderNumberGenerator) {
    return new OwnerBasedOrderProducer(new OwnedOrderFactory(orderNumberGenerator), OwnerBasedProjection.getInstance());
  }

  @Override public List<Order> produce(Item... items) {
    Map<Owner, List<Item>> groups = projection.grouping(items);
    List<Order> orders = new ArrayList<>();
    for (Map.Entry<Owner, List<Item>> group : groups.entrySet()) {
      orders.add(orderFactory.create(group.getKey(), group.getValue()));
    }
    return orders;
  }
}
