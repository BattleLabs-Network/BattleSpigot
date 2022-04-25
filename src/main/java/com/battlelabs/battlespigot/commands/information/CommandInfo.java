package com.battlelabs.battlespigot.commands.information;

import com.battlelabs.battlespigot.commands.CommandSubscriber;
import net.minestom.server.command.builder.condition.CommandCondition;

import java.util.List;

public record CommandInfo(String name, String[] aliases, List<CommandArgumentInfo> arguments, String permissionMessage,
                          CommandCondition condition, CommandSubscriber subscriber) {

}
