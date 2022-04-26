package com.battlelabs.battlespigot.command;

import com.battlelabs.battlespigot.command.information.CommandInfo;
import net.minestom.server.command.CommandManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Repository for every registered command. Optional you can register already initialized classes
 */
public class CommandRepository {


  @SafeVarargs
  public final void register(CommandManager commandManager, Class<? extends ProcessableCommands> @NotNull ... commandClasses) {
    for (Class<? extends ProcessableCommands> commandClass : commandClasses) {
      try {
        // you can register command singletons manually or just auto generate them
        final ProcessableCommands processableCommands = commandClass.newInstance();
        this.register(commandManager, processableCommands);
      } catch (InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }


  public void register(CommandManager commandManager, @NotNull ProcessableCommands processableCommands) {
    final List<CommandInfo> processes = processableCommands.process();
    if (processes.isEmpty()) return;

    for (CommandInfo process : processes) {
      BattleCommands command = new BattleCommands(process);
      commandManager.register(command);
    }
  }

}
