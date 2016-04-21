package test.com.selonj.spi;

import com.selonj.Item;
import com.selonj.spi.OrderProjection;
import com.selonj.Owner;
import com.selonj.spi.OwnerBasedProjection;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import static com.selonj.builders.Builders.an;
import static com.selonj.builders.Builders.item;
import static com.selonj.builders.Builders.owner;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

/**
 * Created by L.x on 16-4-21.
 */
public class OwnerBasedProjectionTest {
  final OrderProjection<Owner> projection = OwnerBasedProjection.getInstance();

  @Test public void sameOwnerOfAllItems() throws Exception {
    Owner owner = an(owner());
    Item item1 = an(item().of(owner));
    Item item2 = an(item().of(owner));

    Map<Owner, List<Item>> groups = projection.grouping(item1, item2);

    assertThat(groups.size(), equalTo(1));
    assertThat(groups.values(), hasItem(asList(item1, item2)));
  }

  @Test public void allItemsWithSameOwnerId() throws Exception {
    Item item1 = an(item().of(an(owner(1))));
    Item item2 = an(item().of(an(owner(1))));

    Map<Owner, List<Item>> groups = projection.grouping(item1, item2);

    assertThat(groups.size(), equalTo(1));
    assertThat(groups.values(), hasItem(asList(item1, item2)));
  }

  @Test public void diffOwnersOfEveryItem() throws Exception {
    Item item1 = an(item());
    Item item2 = an(item());

    Map<Owner, List<Item>> groups = projection.grouping(item1, item2);

    assertThat(groups.size(), equalTo(2));
    assertThat(groups.get(item1.getOwner()), equalTo(asList(item1)));
    assertThat(groups.get(item2.getOwner()), equalTo(asList(item2)));
  }
}