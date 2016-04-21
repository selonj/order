package com.selonj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-04-21.
 */
public class Order {
  private List<OrderLine> lines;

  public void add(Item item) {
    add(new OrderLine(item));
  }

  private void add(OrderLine orderLine) {
    if (lines == null) {
      lines = new ArrayList<>();
    }
    lines.add(orderLine);
  }

  public List<OrderLine> getLines() {
    return lines;
  }
}
