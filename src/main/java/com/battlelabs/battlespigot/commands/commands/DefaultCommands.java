package com.battlelabs.battlespigot.commands.commands;

import com.battlelabs.battlespigot.BattleServer;
import com.battlelabs.battlespigot.commands.CommandSubscriber;
import com.battlelabs.battlespigot.commands.ProcessableCommands;
import com.battlelabs.battlespigot.commands.annotations.CommandArgument;
import com.battlelabs.battlespigot.commands.annotations.CommandExecuteCondition;
import com.battlelabs.battlespigot.commands.annotations.CommandMethod;
import com.battlelabs.battlespigot.commands.condition.OperatorCondition;
import com.battlelabs.battlespigot.operator.OperatorRepository;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.color.Color;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.condition.CommandCondition;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultCommands implements ProcessableCommands {

  @CommandArgument(identifier = "player", validator = String.class, needed = true)
  @CommandMethod(name = "op", aliases = {"operator", "operators"})
  @CommandExecuteCondition(condition = OperatorCondition.class)
  private final CommandSubscriber opCommand = context -> {
    final OperatorRepository operatorRepository = BattleServer.singleton().operatorRepository();
    final CommandSender sender = context.sender();
    final String playerName = context.args()[0];
    final Player target = MinecraftServer.getConnectionManager().getPlayer(playerName);

    if (target == null) {
      sender.sendMessage("Player %s not found!".formatted(playerName));
      return;
    }

    if (operatorRepository.hasOp(target)){
      sender.sendMessage("Player %s has no longer operator permission.".formatted(playerName));
      operatorRepository.operators().remove(target.getUuid());
    } else {
      sender.sendMessage("Player %s has now operator permission.".formatted(playerName));
      operatorRepository.operators().add(target.getUuid());
    }
  };

  @CommandMethod(name = "stop")
  @CommandExecuteCondition(condition = StopCondition.class)
  private final CommandSubscriber stopCommand = context -> {
    context.sender().sendMessage(Component.text("Stopping server...").color(TextColor.color(new Color(255, 25, 25).asRGB())));
    BattleServer.singleton().stop();
  };

  private static class StopCondition implements CommandCondition {
    @Override
    public boolean canUse(@NotNull CommandSender sender, @Nullable String commandString) {
      return sender.isConsole();
    }
  }

}
