package com.battlelabs.battlespigot.dimension;

import net.minestom.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

public final class DimensionTypePublisher {

  public static void publish(@NotNull Dimension dimension) {
    MinecraftServer.getDimensionTypeManager().addDimension(dimension.dimension());
  }

}
