package com.battlelabs.battlespigot.feature;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class FeatureFactory {

  public static @NotNull Feature create(@NotNull Class<? extends Feature> featureClass) {
    try {
      return featureClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Contract(" -> new")
  public static @NotNull FeatureManager featureManager() {
    return new FeatureManager();
  }

}
