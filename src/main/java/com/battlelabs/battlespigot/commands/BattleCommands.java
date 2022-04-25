package com.battlelabs.battlespigot.commands;

import com.battlelabs.battlespigot.commands.information.CommandArgumentInfo;
import com.battlelabs.battlespigot.commands.information.CommandContext;
import com.battlelabs.battlespigot.commands.information.CommandInfo;
import net.minestom.server.command.builder.Command;
import org.jetbrains.annotations.NotNull;

public class BattleCommands extends Command implements ProcessableCommands {
  public BattleCommands(@NotNull CommandInfo info) {
    super(info.name(), info.aliases());
    this.setCondition((sender, commandString) -> {
      boolean condition = info.condition().canUse(sender, commandString);
      if (!condition) {
        sender.sendMessage(info.permissionMessage());
      }
      return condition;
    });
    this.setDefaultExecutor((sender, context) -> {
      final String input = context.getInput();
      final String[] rawArgs = input.split(" ");
      final String[] args = new String[rawArgs.length - 1];
      System.arraycopy(rawArgs, 1, args, 0, args.length);

      if (info.arguments().stream().filter(CommandArgumentInfo::needed).toList().size() != args.length) {
        sender.sendMessage("Invalid command. Try: /%s".formatted(this.commandWithArguments(info)));
        return;
      }

      info.subscriber().run(new CommandContext(sender, input, rawArgs[0], args));
    });
  }

  private @NotNull String commandWithArguments(@NotNull CommandInfo info) {
    final StringBuilder stringBuilder = new StringBuilder(info.name());
    if (!info.arguments().isEmpty()) {
      stringBuilder.append(" <");
      for (int i = 0; i < info.arguments().size(); i++) {
        CommandArgumentInfo argument = info.arguments().get(i);
        stringBuilder.append(argument.identifier()).append("(").append(argument.validator().getSimpleName()).append(")").append(i != info.arguments().size() - 1 ? "/" : "");
      }
      stringBuilder.append(">");

    }
    return stringBuilder.toString();
  }
}
