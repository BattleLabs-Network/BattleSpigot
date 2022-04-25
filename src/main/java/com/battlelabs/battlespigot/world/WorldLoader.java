package com.battlelabs.battlespigot.world;

import net.minestom.server.MinecraftServer;

import java.util.HashMap;

/**
 * Start of a simple World loader. I will probably work on this soon.
 */
public class WorldLoader {

  private final HashMap<Class<? extends World>, World> worldMap = new HashMap<>();

  public void loadMap(World world) {
    this.worldMap.put(world.getClass(), world);
    MinecraftServer.getInstanceManager().registerInstance(world);
  }

  public World getMap(String world) {
    return this.worldMap.values().stream().filter(w -> w.world().equalsIgnoreCase(world)).findFirst().orElse(null);
  }

}
