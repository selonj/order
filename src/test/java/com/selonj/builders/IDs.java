package com.selonj.builders;

/**
 * Created by Administrator on 2016-04-21.
 */
public class IDs {
  private static int identifier = 1;

  public static Integer next() {
    return identifier++;
  }
}
