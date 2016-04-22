package com.selonj.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class Order {
  private Integer id;
  private String orderNumber;
  private Owner owner;
  private ShippingAddress shippingAddress;
  private List<OrderLine> lines;

  public Order(Owner owner, String orderNumber) {
    this(null, owner, orderNumber);
  }

  public Order(Integer id, Owner owner, String orderNumber) {
    this.id = id;
    this.owner = owner;
    this.orderNumber = orderNumber;
  }

  public void add(Item item) {
    add(new OrderLine(item));
  }

  public void add(OrderLine orderLine) {
    if (lines == null) {
      lines = new ArrayList<>();
    }
    lines.add(orderLine);
  }

  public List<OrderLine> getLines() {
    return lines;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public ShippingAddress getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(ShippingAddress shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public void merge(Order other) {
    shippingAddress = other.shippingAddress;
    lines = other.lines;
  }
}
