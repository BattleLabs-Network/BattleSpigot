package com.battlelabs.battlespigot.world;

import net.minestom.server.instance.IChunkLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class WorldFactory {

  @Contract("_ -> new")
  public static @NotNull World create(String path) {
    return new World(path);
  }

  @Contract("_, _ -> new")
  public static @NotNull World create(String path, IChunkLoader chunkLoader) {
    return new World(path, chunkLoader);
  }

  @Contract(" -> new")
  public static @NotNull WorldLoader worldLoader() {
    return new WorldLoader();
  }

}
