package com.selonj.builders;

import com.selonj.order.Item;
import com.selonj.order.Order;
import com.selonj.order.OrderLine;
import com.selonj.order.Owner;
import com.selonj.order.ShippingAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.selonj.builders.Builders.the;

/**
 * Created by Administrator on 2016-04-22.
 */
public class OrderBuilder implements Builder<Order> {
  private Integer id = IDs.next();
  private String orderNumber;
  private Owner owner;
  private ShippingAddress shippingAddress;
  private List<OrderLine> lines = new ArrayList<>();

  @Override public Order build() {
    Order order = new Order(id, owner, orderNumber);
    order.setShippingAddress(shippingAddress);
    for (OrderLine line : lines) order.add(line);
    return order;
  }

  public OrderBuilder from(Order source) {
    id = source.getId();
    owner = source.getOwner();
    orderNumber = source.getOrderNumber();
    lines = source.getLines();
    return this;
  }

  public OrderBuilder shippingAddress(ShippingAddress shippingAddress) {
    this.shippingAddress = shippingAddress;
    return this;
  }

  public OrderBuilder without(Item item) {
    for (Iterator<OrderLine> it = lines.iterator(); it.hasNext(); ) {
      OrderLine candidate = it.next();
      if (candidate.sameItem(item)) {
        it.remove();
        break;
      }
    }
    return this;
  }

  public OrderBuilder shippingAddress(Builder<ShippingAddress> shippingAddress) {
    this.shippingAddress = the(shippingAddress);
    return this;
  }

  public OrderBuilder withLines(Item... items) {
    for (Item item : items) lines.add(new OrderLine(item));
    return this;
  }
}
