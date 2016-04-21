package com.selonj;

import java.util.List;
import java.util.Map;

/**
 * Created by L.x on 16-4-21.
 */
public interface OrderProjection<G> {
  Map<G, List<Item>> grouping(Item... items);
}
