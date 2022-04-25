package com.battlelabs.battlespigot.dimension;

import net.minestom.server.world.DimensionType;

/**
 * Just a code cleanup, so we don't have something like 20 utility classes with Dimension singletons
 */
public interface Dimension {

  DimensionType dimension();

}
