package com.battlelabs.battlespigot.feature;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Feature {
  void hook(@NotNull EventNode<Event> node);
}