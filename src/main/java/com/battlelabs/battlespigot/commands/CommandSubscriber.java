package com.battlelabs.battlespigot.commands;

import com.battlelabs.battlespigot.commands.information.CommandContext;

public interface CommandSubscriber {

  void run(CommandContext context);

}
