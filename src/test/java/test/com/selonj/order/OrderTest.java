package test.com.selonj.order;

import com.selonj.order.Item;
import com.selonj.order.Order;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.order;
import static com.selonj.builders.Builders.shippingAddress;
import static com.selonj.matchers.OrderMatchers.sameItem;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016-04-22.
 */
public class OrderTest {

  @Test public void mergeShippingAddress() throws Exception {
    Order order = an(order());
    Order other = an(order().shippingAddress(shippingAddress()));

    order.merge(other);

    assertThat(order.getShippingAddress(), equalTo(other.getShippingAddress()));
  }

  @Test public void mergeOrderLines() throws Exception {
    Item item1 = an(item());
    Item item2 = an(item());

    Order order = an(order().withLines(item1, item2));
    Order other = an(order().withLines(item1));

    order.merge(other);

    assertThat(order.getLines(), hasSize(1));
    assertThat(order.getLines(), hasItem(sameItem(item1)));
  }
}