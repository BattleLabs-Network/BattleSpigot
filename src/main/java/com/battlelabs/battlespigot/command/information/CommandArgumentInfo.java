package com.battlelabs.battlespigot.command.information;

public record CommandArgumentInfo(String identifier, Class<?> validator, boolean needed) {
}
