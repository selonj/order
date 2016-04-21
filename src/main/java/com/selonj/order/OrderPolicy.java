package com.selonj.order;

import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public interface OrderPolicy {
  void checking(Item item, List<ItemViolation> violations);
}
