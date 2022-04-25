package com.battlelabs.battlespigot.dimension.dimensions;

import com.battlelabs.battlespigot.dimension.Dimension;
import com.battlelabs.battlespigot.dimension.DimensionTypePublisher;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;

public final class FullbrightDimension implements Dimension {

  private static final DimensionType SINGLETON = DimensionType.builder(NamespaceID.from("minestom:full_bright"))
      .ambientLight(2.0f)
      .build();

  static {
    DimensionTypePublisher.publish(FullbrightDimension::singleton);
  }

  public static DimensionType singleton() {
    return SINGLETON;
  }

  @Override
  public DimensionType dimension() {
    return SINGLETON;
  }
}