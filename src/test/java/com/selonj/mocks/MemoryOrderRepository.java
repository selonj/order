package com.selonj.mocks;

import com.selonj.order.Order;
import com.selonj.order.OrderRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-04-22.
 */
public class MemoryOrderRepository implements OrderRepository {
  private Map<Integer, Order> orders = new HashMap<>();

  @Override public void create(Order order) {
    orders.put(order.getId(), order);
  }

  @Override public Order getOrderById(Integer id) {
    return orders.get(id);
  }

  public boolean contains(Order order) {
    return orders.containsKey(order.getId());
  }
}
