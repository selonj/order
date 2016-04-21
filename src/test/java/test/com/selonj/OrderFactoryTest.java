package test.com.selonj;

import com.selonj.Item;
import com.selonj.Order;
import com.selonj.BasicOrderFactory;
import com.selonj.OrderFactory;
import com.selonj.OrderNumberGenerator;
import com.selonj.mocks.SequenceOrderNumberGenerator;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderFactoryTest {
  final private OrderNumberGenerator orderNumberGenerator = new SequenceOrderNumberGenerator(1);
  final private OrderFactory factory = new BasicOrderFactory(orderNumberGenerator);

  @Test public void createOrderFromItems() throws Exception {
    Item item1 = an(item());
    Item item2 = an(item());

    Order order = factory.create(asList(item1, item2));

    assertThat(order.getOrderNumber(), equalTo("order-1"));
    assertThat(order.getLines(), hasSize(2));
    assertThat(order.getLines().get(0).itemId, equalTo(item1.getItemId()));
    assertThat(order.getLines().get(1).itemId, equalTo(item2.getItemId()));
  }
}