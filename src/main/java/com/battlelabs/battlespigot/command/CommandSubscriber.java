package com.battlelabs.battlespigot.command;

import com.battlelabs.battlespigot.command.information.CommandContext;

public interface CommandSubscriber {

  void run(CommandContext context);

}
