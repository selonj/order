package com.selonj.spi;

import com.selonj.Item;
import com.selonj.Order;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public interface OrderFactory<G> {
  Order create(G owner, List<Item> items);
}
