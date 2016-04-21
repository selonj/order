package test.com.selonj;

import com.selonj.OrderProjection;
import com.selonj.spi.OwnedOrderFactory;
import com.selonj.Item;
import com.selonj.Owner;
import com.selonj.ItemViolation;
import com.selonj.Order;
import com.selonj.OrderBooking;
import com.selonj.OrderException;
import com.selonj.OrderFactory;
import com.selonj.OrderPolicy;
import com.selonj.builders.Builders;
import com.selonj.mocks.MockItemInventory;
import com.selonj.mocks.SequenceOrderNumberGenerator;
import com.selonj.spi.ItemInventoryChecker;
import com.selonj.spi.OwnerBasedProjection;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.owner;
import static com.selonj.matchers.OrderMatchers.belongsTo;
import static com.selonj.matchers.OrderMatchers.sameItem;
import static com.selonj.mocks.MockItemInventory.totalQuantityOfAnyItems;
import static com.selonj.spi.ItemViolations.hasNoEnoughItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderBookingTest {
  final private MockItemInventory itemInventory = totalQuantityOfAnyItems(5);
  final private OrderProjection projection = new OwnerBasedProjection();
  final private OrderFactory orderFactory = new OwnedOrderFactory(new SequenceOrderNumberGenerator(1));
  final private OrderPolicy orderPolicy = new ItemInventoryChecker(itemInventory);
  final private OrderBooking booking = new OrderBooking(projection, orderFactory, orderPolicy);

  @Test public void createOrderFromItemsHasEnoughInventoryQuantity() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));

    List<Order> orders = booking.create(item1, item2);

    assertThat(orders, hasSize(1));
    assertThat(orders.get(0), belongsTo(owner));
    assertThat(orders.get(0).getLines(), hasItems(sameItem(item1), sameItem(item2)));
  }

  @Test public void reportsItemViolationsWhenHaveLessInventoryQuantity() throws Exception {
    Item item1 = an(item().quantity(10));
    Item item2 = an(item().quantity(1));
    Item item3 = an(item().quantity(15));

    try {
      booking.create(item1, item2, item3);
      fail("should failed");
    } catch (OrderException expected) {
      List<? super ItemViolation> violations = expected.getViolations();

      assertThat(violations, hasSize(2));
      assertThat(violations, hasItem(hasNoEnoughItems(item1.getItemId(), itemInventory.getStockQuantityOf(item1))));
      assertThat(violations, hasItem(hasNoEnoughItems(item3.getItemId(), itemInventory.getStockQuantityOf(item3))));
    }
  }

  @Test public void createOrderGroupedByOwner() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));
    Item other = an(item());

    List<Order> orders = booking.create(item1, other, item2);

    assertThat(orders, hasSize(2));
    assertThat(orders.get(0).getLines(), hasItems(sameItem(item1), sameItem(item2)));
    assertThat(orders.get(1).getLines(), hasItems(sameItem(other)));
  }
}
