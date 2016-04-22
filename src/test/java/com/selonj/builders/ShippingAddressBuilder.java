package com.selonj.builders;

import com.selonj.order.ShippingAddress;

/**
 * Created by Administrator on 2016-04-22.
 */
public class ShippingAddressBuilder implements Builder<ShippingAddress> {
  private String user;
  private String mobile;
  private String postCode;
  private String address;

  @Override public ShippingAddress build() {
    ShippingAddress address = new ShippingAddress();
    address.setUser(user);
    address.setMobile(mobile);
    address.setPostCode(postCode);
    address.setAddress(this.address);
    return address;
  }
}
