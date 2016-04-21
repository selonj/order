package com.selonj.builders;

import com.selonj.order.Owner;

/**
 * Created by L.x on 16-4-21.
 */
public class OwnerBuilder implements Builder<Owner> {
  private Integer id = IDs.next();

  public OwnerBuilder id(int id) {
    this.id = id;
    return this;
  }

  @Override public Owner build() {
    return new Owner() {
      @Override public Integer getId() {
        return id;
      }
    };
  }
}
