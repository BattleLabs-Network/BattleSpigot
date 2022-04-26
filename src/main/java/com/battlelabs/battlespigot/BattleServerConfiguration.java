package com.battlelabs.battlespigot;

import com.battlelabs.battlespigot.config.Configuration;
import com.battlelabs.battlespigot.config.DataSet;

import java.nio.file.Path;

public class BattleServerConfiguration extends Configuration {
  public BattleServerConfiguration() {
    super(Path.of("configurations"), "config.json", DataSet.construct().append("host", "127.0.0.1").append("port", 25565).append("motd", "A default Battle Spigot Server").append("proxy_support", DataSet.construct().append("bungeecord", false).append("velocity", DataSet.construct().append("enabled", false).append("secret_key", "").jsonObject()).jsonObject()));
  }
}
