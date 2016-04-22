package test.com.selonj.order;

import com.selonj.mocks.MemoryOrderRepository;
import com.selonj.order.OrderProducer;
import com.selonj.order.Item;
import com.selonj.order.Owner;
import com.selonj.order.ItemViolation;
import com.selonj.order.Order;
import com.selonj.order.OrderBooking;
import com.selonj.order.OrderException;
import com.selonj.order.OrderPolicy;
import com.selonj.mocks.MockItemInventory;
import com.selonj.mocks.SequenceOrderNumberGenerator;
import com.selonj.order.ShippingAddress;
import com.selonj.order.spi.ItemInventoryChecker;
import com.selonj.order.spi.OwnerBasedOrderProducer;
import java.util.List;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static com.selonj.builders.Builders.a;
import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.order;
import static com.selonj.builders.Builders.owner;
import static com.selonj.builders.Builders.shippingAddress;
import static com.selonj.matchers.OrderMatchers.sameItem;
import static com.selonj.mocks.MockItemInventory.totalQuantityOfAnyItems;
import static com.selonj.order.spi.ItemViolations.hasNoEnoughItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Administrator on 2016-04-21.
 */
public class OrderBookingTest {
  private static final int TOTAL_QUANTITY = 5;
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  final private MemoryOrderRepository orderRepository = new MemoryOrderRepository();
  final private MockItemInventory itemInventory = totalQuantityOfAnyItems(TOTAL_QUANTITY);
  final private OrderProducer producer = OwnerBasedOrderProducer.newInstance(SequenceOrderNumberGenerator.starts(1));
  final private OrderPolicy orderPolicy = new ItemInventoryChecker(itemInventory);
  final private OrderBooking booking = new OrderBooking(producer, orderRepository, orderPolicy);

  @Test public void createOrderFromItemsHasEnoughInventoryQuantity() throws Exception {
    final Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));

    List<Order> orders = booking.create(item1, item2);

    assertThat(orders, hasSize(1));
    assertTrue(orderRepository.contains(orders.get(0)));
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
      assertThat(violations, hasItem(hasNoEnoughItems(item1.getItemId(), TOTAL_QUANTITY)));
      assertThat(violations, hasItem(hasNoEnoughItems(item3.getItemId(), TOTAL_QUANTITY)));
    }
  }

  @Test public void createOrderGroupedByOwner() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));
    Item other = an(item());

    List<Order> orders = booking.create(item1, other, item2);

    assertThat(orders, hasSize(2));
  }

  @Test public void confirmOrderWithShippingAddress() throws Exception {
    Item item1 = an(item());
    ShippingAddress shippingAddress = a(shippingAddress());
    Order created = first(booking.create(item1));

    booking.confirm(an(order().from(created).shippingAddress(shippingAddress)));

    Order merged = orderRepository.getOrderById(created.getId());
    assertThat(merged.getShippingAddress(), equalTo(shippingAddress));
  }

  @Test public void removeOrderLineWhenOrderUnconfirmed() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));
    Order created = first(booking.create(item1, item2));

    booking.confirm(an(order().from(created).without(item1)));

    Order merged = orderRepository.getOrderById(created.getId());
    assertThat(merged.getLines(), hasSize(1));
    assertThat(merged.getLines(), hasItem(sameItem(item2)));
  }

  private static <T> T first(List<T> items) {
    if (items.isEmpty()) return null;
    return items.get(0);
  }
}
