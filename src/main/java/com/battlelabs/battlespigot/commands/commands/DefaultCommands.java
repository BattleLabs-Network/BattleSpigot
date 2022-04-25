package com.battlelabs.battlespigot.commands.commands;

import com.battlelabs.battlespigot.BattleServer;
import com.battlelabs.battlespigot.commands.CommandSubscriber;
import com.battlelabs.battlespigot.commands.ProcessableCommands;
import com.battlelabs.battlespigot.commands.annotations.CommandArgument;
import com.battlelabs.battlespigot.commands.annotations.CommandExecuteCondition;
import com.battlelabs.battlespigot.commands.annotations.CommandMethod;
import com.battlelabs.battlespigot.commands.condition.OperatorCondition;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.color.Color;

public class DefaultCommands implements ProcessableCommands {

  @CommandArgument(identifier = "player", validator = String.class, needed = true)
  @CommandMethod(name = "op", aliases = {"operator", "operators"})
  @CommandExecuteCondition(condition = OperatorCondition.class)
  private final CommandSubscriber opCommand = context -> {
    final var operatorRepository = BattleServer.singleton().operatorRepository();
    final var sender = context.sender();
    final var playerName = context.args()[0];
    final var target = MinecraftServer.getConnectionManager().getPlayer(playerName);

    if (target == null) {
      sender.sendMessage("Player %s not found!".formatted(playerName));
      return;
    }

    if (operatorRepository.hasOp(target)) {
      sender.sendMessage("Player %s has no longer operator permission.".formatted(playerName));
      operatorRepository.operators().remove(target.getUuid());
    } else {
      sender.sendMessage("Player %s has now operator permission.".formatted(playerName));
      operatorRepository.operators().add(target.getUuid());
    }
  };

  @CommandMethod(name = "stop")
  @CommandExecuteCondition(condition = OperatorCondition.class)
  private final CommandSubscriber stopCommand = context -> {
    context.sender().sendMessage(Component.text("Stopping server...").color(TextColor.color(new Color(255, 25, 25).asRGB())));
    BattleServer.singleton().stop();
  };

  @CommandMethod(name = "tps", aliases = "performance")
  @CommandExecuteCondition(condition = OperatorCondition.class)
  private final CommandSubscriber tpsCommand = context -> {
    final var runtime = Runtime.getRuntime();
    final var tickPerSecond = MinecraftServer.TICK_PER_SECOND;
    final var ramUsage = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
    context.sender().sendMessage(Component.newline()
        .append(Component.text("---------------------------------", NamedTextColor.GRAY)
            .append(Component.newline())
            .append(Component.text(" » ", NamedTextColor.DARK_GRAY))
            .append(Component.text("TPS: ", NamedTextColor.GRAY))
            .append(Component.text(tickPerSecond, NamedTextColor.GOLD))
            .append(Component.newline())
            .append(Component.text(" » ", NamedTextColor.DARK_GRAY))
            .append(Component.text("Ram usage: ", NamedTextColor.GRAY))
            .append(Component.text(ramUsage + "mb", NamedTextColor.GOLD))
            .append(Component.newline())
            .append(Component.text("---------------------------------", NamedTextColor.GRAY))));
  };

}
