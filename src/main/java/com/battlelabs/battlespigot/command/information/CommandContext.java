package com.battlelabs.battlespigot.command.information;

import net.minestom.server.command.CommandSender;

public record CommandContext(CommandSender sender, String rawInput, String command, String[] args) {

}
