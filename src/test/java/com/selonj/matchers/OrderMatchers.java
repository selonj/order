package com.selonj.matchers;

import com.selonj.order.Item;
import com.selonj.order.Owner;
import com.selonj.order.Order;
import com.selonj.order.OrderLine;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by L.x on 16-4-21.
 */
public class OrderMatchers {
  public static Matcher<? super OrderLine> sameItem(Item item) {
    return new FeatureMatcher<OrderLine, Integer>(equalTo(item.getItemId()), "item", "but") {
      @Override protected Integer featureValueOf(OrderLine orderLine) {
        return orderLine.getItemId();
      }
    };
  }

  public static Matcher<Order> belongsTo(Owner owner) {
    return new FeatureMatcher<Order, Integer>(equalTo(owner.getId()), "owner", "but") {
      @Override protected Integer featureValueOf(Order order) {
        return order.getOwner().getId();
      }
    };
  }
}
