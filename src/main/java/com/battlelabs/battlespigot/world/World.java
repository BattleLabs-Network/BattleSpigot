package com.battlelabs.battlespigot.world;

import com.battlelabs.battlespigot.dimension.dimensions.FullbrightDimension;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.IChunkLoader;
import net.minestom.server.instance.InstanceContainer;

import java.nio.file.Path;
import java.util.UUID;

public class World extends InstanceContainer {

  private final String world;

  public World(String world) {
    super(UUID.randomUUID(), FullbrightDimension.singleton());
    this.world = world;
    this.setChunkLoader(new AnvilLoader(Path.of(world)));
    this.setTimeRate(0);
  }

  public World(String world, IChunkLoader chunkLoader) {
    super(UUID.randomUUID(), FullbrightDimension.singleton());
    this.world = world;
    this.setChunkLoader(chunkLoader);
    this.setTimeRate(0);
  }

  public String world() {
    return world;
  }
}
