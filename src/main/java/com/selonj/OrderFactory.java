package com.selonj;

import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public interface OrderFactory {
  Order create(List<Item> items);
}
