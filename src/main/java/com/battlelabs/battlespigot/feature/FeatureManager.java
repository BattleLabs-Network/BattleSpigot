package com.battlelabs.battlespigot.feature;

import com.battlelabs.battlespigot.feature.features.CustomItemDrop;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;

import java.util.List;

/**
 * You could say that this is a custom eventbus implementation
 */
public class FeatureManager {

  private final List<Feature> features = List.of(FeatureFactory.create(CustomItemDrop.class));

  public void hook(EventNode<Event> instance) {
    for (Feature feature : features) {
      feature.hook(instance);
    }
  }

  public List<Feature> features() {
    return features;
  }
}
