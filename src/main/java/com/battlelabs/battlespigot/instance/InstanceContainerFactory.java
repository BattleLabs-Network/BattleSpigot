package com.battlelabs.battlespigot.instance;

import net.minestom.server.instance.InstanceContainer;
import org.jetbrains.annotations.NotNull;

public final class InstanceContainerFactory {

  public static @NotNull InstanceContainer create(@NotNull Class<? extends InstanceContainer> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

}
