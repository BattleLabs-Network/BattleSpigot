package com.battlelabs.battlespigot.operator;

import com.battlelabs.battlespigot.BattleServer;
import com.battlelabs.battlespigot.config.ConfigurationProvider;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Bukkit Operator implementation.
 */
public class OperatorRepository {

  private final List<UUID> operators = new ArrayList<>();
  private OperatorConfiguration configuration = new OperatorConfiguration();

  public void loadConfig() {
    final ConfigurationProvider configurationProvider = BattleServer.singleton().configurationProvider();
    configurationProvider.createIfNotExists(this.configuration);
    this.configuration = configurationProvider.load(this.configuration);

    final JsonObject players = this.configuration.dataSet().getObject("players");
    if (players.size() == 0) return;
    for (Map.Entry<String, JsonElement> stringJsonElementEntry : players.entrySet()) {
      System.out.println("Found...");
      final String uniqueID = stringJsonElementEntry.getKey();
      final String name = stringJsonElementEntry.getValue().getAsString();
      this.operators.add(UUID.fromString(uniqueID));
    }
  }

  public void saveConfig() {
    this.configuration = new OperatorConfiguration();
    for (UUID operator : this.operators) {
      final Player player = MinecraftServer.getConnectionManager().getPlayer(operator);
      this.configuration.dataSet().getObject("players").addProperty(operator.toString(), player == null ? "none" : player.getName().toString());
    }
    BattleServer.singleton().configurationProvider().save(this.configuration);
  }

  public boolean hasOp(@NotNull Player player) {
    return this.operators.contains(player.getUuid());
  }

  public List<UUID> operators() {
    return operators;
  }

  public OperatorConfiguration configuration() {
    return configuration;
  }
}
