package com.selonj.order.spi;

import com.selonj.order.Item;
import java.util.List;
import java.util.Map;

/**
 * Created by L.x on 16-4-21.
 */
public interface OrderProjection<G> {
  Map<G, List<Item>> grouping(Item... items);
}
