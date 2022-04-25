package com.battlelabs.battlespigot.commands.annotations;

import java.lang.annotation.*;

@Repeatable(CommandArguments.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandArgument {

  String identifier();

  Class<?> validator();

  boolean needed() default false;

}

