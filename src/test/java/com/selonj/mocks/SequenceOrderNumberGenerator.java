package com.selonj.mocks;

import com.selonj.order.spi.OrderNumberGenerator;

/**
 * Created by Administrator on 2016-04-21.
 */
public class SequenceOrderNumberGenerator implements OrderNumberGenerator {
  private int id;

  public SequenceOrderNumberGenerator() {
  }

  private SequenceOrderNumberGenerator(int start) {
    this.id = start;
  }

  public static SequenceOrderNumberGenerator starts(int start) {
    return new SequenceOrderNumberGenerator(start);
  }

  @Override public String generate() {
    return "order-" + nextId();
  }

  private int nextId() {
    return id++;
  }
}
