package com.battlelabs.battlespigot.commands.information;

public record CommandArgumentInfo(String identifier, Class<?> validator, boolean needed) {
}
