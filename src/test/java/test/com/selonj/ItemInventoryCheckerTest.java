package test.com.selonj;

import com.selonj.Item;
import com.selonj.ItemInventory;
import com.selonj.policy.ItemInventoryChecker;
import com.selonj.ItemViolation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.mocks.MockItemInventory.totalQuantityOfAnyItems;
import static com.selonj.violation.ItemViolations.hasNoEnoughItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016-04-21.
 */
public class ItemInventoryCheckerTest {
  final ItemInventory inventory = totalQuantityOfAnyItems(5);
  final ItemInventoryChecker checker = new ItemInventoryChecker(inventory);

  @Test public void markViolationWhenItemNotEnough() throws Exception {
    Item item = an(item().quantity(10));
    List<ItemViolation> violations = new ArrayList<>();

    checker.checking(item, violations);

    assertThat(violations, hasSize(1));
    assertThat(violations, hasItem(hasNoEnoughItems(item.getItemId(), inventory.getStockQuantityOf(item))));
  }
}