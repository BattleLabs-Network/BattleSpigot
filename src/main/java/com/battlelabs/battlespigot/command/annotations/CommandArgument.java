package com.battlelabs.battlespigot.command.annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(CommandArguments.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandArgument {

  String identifier();

  Class<?> validator();

  boolean needed() default false;

}

