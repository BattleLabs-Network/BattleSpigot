package com.battlelabs.battlespigot.command.information;

import com.battlelabs.battlespigot.command.CommandSubscriber;
import net.minestom.server.command.builder.condition.CommandCondition;

import java.util.List;

public record CommandInfo(String name, String[] aliases, List<CommandArgumentInfo> arguments, String permissionMessage,
                          CommandCondition condition, CommandSubscriber subscriber) {

}
