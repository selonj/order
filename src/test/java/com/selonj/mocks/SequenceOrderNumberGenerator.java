package com.selonj.mocks;

import com.selonj.OrderNumberGenerator;

/**
 * Created by Administrator on 2016-04-21.
 */
public class SequenceOrderNumberGenerator implements OrderNumberGenerator {
  private int id;

  public SequenceOrderNumberGenerator(int start) {
    this.id = start;
  }

  @Override public String generate() {
    return "order-" + nextId();
  }

  private int nextId() {
    return id++;
  }
}
