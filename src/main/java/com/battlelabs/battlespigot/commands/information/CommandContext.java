package com.battlelabs.battlespigot.commands.information;

import net.minestom.server.command.CommandSender;

public record CommandContext(CommandSender sender, String rawInput, String command, String[] args) {

}
