package com.selonj.order;

import java.util.List;

/**
 * Created by L.x on 16-4-21.
 */
public interface OrderProducer {
  List<Order> produce(Item... items);
}
