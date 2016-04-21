package com.selonj.builders;

/**
 * Created by Administrator on 2016-04-21.
 */
public class Builders {
  public static <T> T an(Builder<T> builder) {
    return builder.build();
  }

  public static ItemBuilder item() {
    return new ItemBuilder();
  }

  public static OwnerBuilder owner() {
    return new OwnerBuilder();
  }

  public static OwnerBuilder owner(int id) {
    return new OwnerBuilder().id(id);
  }
}
