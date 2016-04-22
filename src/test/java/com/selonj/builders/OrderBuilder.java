package com.selonj.builders;

import com.selonj.order.Order;
import com.selonj.order.OrderLine;
import com.selonj.order.Owner;
import com.selonj.order.ShippingAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-04-22.
 */
public class OrderBuilder implements Builder<Order> {
  private Integer id=IDs.next();
  private String orderNumber;
  private Owner owner;
  private ShippingAddress shippingAddress;
  private List<OrderLine> lines = new ArrayList<>();

  @Override public Order build() {
    Order order = new Order(id,owner, orderNumber);
    order.shippingAddress = shippingAddress;
    for (OrderLine line : lines) order.add(line);
    return order;
  }

  public OrderBuilder from(Order source) {
    id=source.getId();
    owner = source.getOwner();
    orderNumber = source.getOrderNumber();
    lines = source.getLines();
    return this;
  }

  public OrderBuilder shippingAddress(ShippingAddress shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }
}
