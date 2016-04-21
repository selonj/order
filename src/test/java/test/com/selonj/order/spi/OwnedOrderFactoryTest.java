package test.com.selonj.order.spi;

import com.selonj.order.Item;
import com.selonj.order.Owner;
import com.selonj.order.Order;
import com.selonj.order.spi.OwnedOrderFactory;
import com.selonj.order.spi.OrderFactory;
import com.selonj.order.spi.OrderNumberGenerator;
import com.selonj.mocks.SequenceOrderNumberGenerator;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.owner;
import static com.selonj.matchers.OrderMatchers.belongsTo;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OwnedOrderFactoryTest {
  final private OrderNumberGenerator orderNumberGenerator = SequenceOrderNumberGenerator.starts(1);
  final private OrderFactory factory = new OwnedOrderFactory(orderNumberGenerator);

  @Test public void createOrderFromItems() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));

    Order order = factory.create(owner, asList(item1, item2));

    assertThat(order, belongsTo(owner));
    assertThat(order.getOrderNumber(), equalTo("order-1"));
    assertThat(order.getLines(), hasSize(2));
    assertThat(order.getLines().get(0).getItemId(), equalTo(item1.getItemId()));
    assertThat(order.getLines().get(1).getItemId(), equalTo(item2.getItemId()));
  }
}