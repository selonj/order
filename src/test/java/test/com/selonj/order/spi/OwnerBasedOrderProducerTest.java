package test.com.selonj.order.spi;

import com.selonj.order.Item;
import com.selonj.order.Order;
import com.selonj.order.OrderProducer;
import com.selonj.order.Owner;
import com.selonj.mocks.SequenceOrderNumberGenerator;
import com.selonj.order.spi.OwnerBasedOrderProducer;
import java.util.List;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.owner;
import static com.selonj.matchers.OrderMatchers.belongsTo;
import static com.selonj.matchers.OrderMatchers.sameItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by L.x on 16-4-21.
 */
public class OwnerBasedOrderProducerTest {
  private final OrderProducer producer = OwnerBasedOrderProducer.newInstance(SequenceOrderNumberGenerator.starts(1));

  @Test public void createOrderFromItemsHasEnoughInventoryQuantity() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));

    List<Order> orders = producer.produce(item1, item2);

    assertThat(orders, hasSize(1));
    assertThat(orders.get(0), belongsTo(owner));
    assertThat(orders.get(0).getLines(), hasItems(sameItem(item1), sameItem(item2)));
  }

  @Test public void createOrderGroupedByOwner() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));
    Item other = an(item());

    List<Order> orders = producer.produce(item1, other, item2);

    assertThat(orders, hasSize(2));
    assertThat(orders.get(0).getLines(), hasItems(sameItem(item1), sameItem(item2)));
    assertThat(orders.get(1).getLines(), hasItems(sameItem(other)));
  }
}