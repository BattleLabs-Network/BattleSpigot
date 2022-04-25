package com.battlelabs.battlespigot.commands.condition;

import com.battlelabs.battlespigot.BattleServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.condition.CommandCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Default condition just to represent how its working
 */
public class OperatorCondition implements CommandCondition {
  @Override
  public boolean canUse(@NotNull CommandSender sender, @Nullable String commandString) {
    return sender.isConsole() || BattleServer.singleton().operatorRepository().hasOp(sender.asPlayer());
  }
}
