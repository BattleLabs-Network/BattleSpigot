package com.battlelabs.battlespigot.operator;

import com.battlelabs.battlespigot.config.Configuration;
import com.battlelabs.battlespigot.config.DataSet;

import java.nio.file.Path;

public class OperatorConfiguration extends Configuration {
  public OperatorConfiguration() {
    super(Path.of("configurations"), "operators.json", DataSet.construct().append("players", DataSet.construct().jsonObject()));
  }
}
