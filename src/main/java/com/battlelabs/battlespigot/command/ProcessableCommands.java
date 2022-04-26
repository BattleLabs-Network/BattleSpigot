package com.battlelabs.battlespigot.command;

import com.battlelabs.battlespigot.command.annotations.CommandArgument;
import com.battlelabs.battlespigot.command.annotations.CommandExecuteCondition;
import com.battlelabs.battlespigot.command.annotations.CommandMethod;
import com.battlelabs.battlespigot.command.annotations.CommandPermission;
import com.battlelabs.battlespigot.command.information.CommandArgumentInfo;
import com.battlelabs.battlespigot.command.information.CommandInfo;
import net.minestom.server.command.builder.condition.CommandCondition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface ProcessableCommands {


  /**
   * Generating command infos for each command found in a class implementing {@link ProcessableCommands}
   */
  default List<CommandInfo> process() {
    final List<CommandInfo> infos = new ArrayList<>();
    for (Field declaredField : getClass().getDeclaredFields()) {
      declaredField.setAccessible(true);
      try {
        Object object = declaredField.get(this);
        if (object instanceof CommandSubscriber subscriber) {
          // Is every time given if we find a new command
          final CommandMethod commandMethod = declaredField.getAnnotation(CommandMethod.class);
          final String name = commandMethod.name();
          final String[] aliases = commandMethod.aliases();

          final List<CommandArgumentInfo> commandArguments = new ArrayList<>();
          for (CommandArgument annotation : declaredField.getAnnotationsByType(CommandArgument.class)) {
            commandArguments.add(new CommandArgumentInfo(annotation.identifier(), annotation.validator(), annotation.needed()));
          }

          // Default permission message
          String permissionMessage = "No Permission!";
          if (declaredField.isAnnotationPresent(CommandPermission.class)) {
            final CommandPermission commandPermission = declaredField.getAnnotation(CommandPermission.class);
            permissionMessage = commandPermission.message();
          }

          CommandCondition condition = (sender, commandString) -> true;
          if (declaredField.isAnnotationPresent(CommandExecuteCondition.class)) {
            condition = declaredField.getAnnotation(CommandExecuteCondition.class).condition().newInstance();
          }

          infos.add(new CommandInfo(name, aliases, commandArguments, permissionMessage, condition, subscriber));
        }
      } catch (IllegalAccessException | InstantiationException ignored) {
      }
    }
    return infos;
  }

}
