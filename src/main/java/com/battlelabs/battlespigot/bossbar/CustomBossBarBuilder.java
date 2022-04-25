package com.battlelabs.battlespigot.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import net.minestom.server.entity.Player;

public class CustomBossBarBuilder {

  private final Player player;
  private String text = "Test";
  private float stage = 1;
  private BossBar.Overlay overlay = BossBar.Overlay.PROGRESS;
  private BossBar.Color color = BossBar.Color.WHITE;

  public CustomBossBarBuilder(Player player) {
    this.player = player;
  }

  public CustomBossBarBuilder setText(String text) {
    this.text = text;
    return this;
  }

  public CustomBossBarBuilder setStage(float stage) {
    this.stage = stage;
    return this;
  }

  public CustomBossBarBuilder setOverlay(BossBar.Overlay overlay) {
    this.overlay = overlay;
    return this;
  }

  public CustomBossBarBuilder setColor(BossBar.Color color) {
    this.color = color;
    return this;
  }

  public CustomBossBar build() {
    return new CustomBossBar(this.player, this.text, this.stage, this.overlay, this.color);
  }
}
