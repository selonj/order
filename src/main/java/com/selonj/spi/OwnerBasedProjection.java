package com.selonj.spi;

import com.selonj.Item;
import com.selonj.Owner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L.x on 16-4-21.
 */
public class OwnerBasedProjection implements OrderProjection<Owner> {

  private OwnerBasedProjection() {
  }

  public static OrderProjection<Owner> getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {
    private static final OrderProjection<Owner> INSTANCE = new OwnerBasedProjection();
  }

  @Override public Map<Owner, List<Item>> grouping(Item... items) {
    Map<Owner, List<Item>> groups = new LinkedHashMap<>();
    Map<Integer, List<Item>> refs = new HashMap<>();

    for (Item item : items) {
      Owner owner = item.getOwner();
      List<Item> group = refs.get(owner.getId());
      if (group == null) {
        group = new ArrayList<>();
        groups.put(owner, group);
      }

      group.add(item);

      refs.put(owner.getId(), group);
    }

    return groups;
  }
}
