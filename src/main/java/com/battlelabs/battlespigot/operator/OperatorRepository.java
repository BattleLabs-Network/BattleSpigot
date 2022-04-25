package com.battlelabs.battlespigot.operator;

import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Bukkit Operator implementation.
 */
public class OperatorRepository {

  private final List<UUID> operators = new ArrayList<>();

  public boolean hasOp(@NotNull Player player) {
    return this.operators.contains(player.getUuid());
  }

  public List<UUID> operators() {
    return operators;
  }
}
