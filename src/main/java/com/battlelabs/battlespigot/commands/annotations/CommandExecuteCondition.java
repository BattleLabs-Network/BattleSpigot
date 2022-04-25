package com.battlelabs.battlespigot.commands.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CommandExecuteCondition {

  Class<? extends net.minestom.server.command.builder.condition.CommandCondition> condition();

}
