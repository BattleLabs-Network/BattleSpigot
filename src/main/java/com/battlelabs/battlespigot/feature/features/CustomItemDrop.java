package com.battlelabs.battlespigot.feature.features;

import com.battlelabs.battlespigot.feature.Feature;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class CustomItemDrop implements Feature {
  @Override
  public void hook(@NotNull EventNode<Event> node) {
    node.addListener(ItemDropEvent.class, event -> {
      final ItemStack item = event.getItemStack();
      ItemEntity itemEntity = new ItemEntity(item);
      itemEntity.setPickupDelay(250, TimeUnit.MILLISECOND);
      itemEntity.setInstance(event.getInstance(), event.getPlayer().getPosition().add(.0, 1.5, .0));
      itemEntity.setVelocity(event.getPlayer().getPosition().direction().mul(6.0));
    });
  }
}
