package com.battlelabs.battlespigot.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.BossBarPacket;

/**
 * Custom implementation of {@link BossBar}.
 * Implemented for only one player, so we can easily update everything for only one player and don't have to call an extra BossBar builder
 */
public class CustomBossBar {

  private final Player player;
  private String text;
  private float stage;
  private BossBar.Overlay overlay;
  private BossBar.Color color;

  public CustomBossBar(Player player, String text, float stage, BossBar.Overlay overlay, BossBar.Color color) {
    this.player = player;
    this.text = text;
    this.stage = stage;
    this.overlay = overlay;
    this.color = color;
  }

  public void display() {
    this.player.sendPacket(new BossBarPacket(player.getUuid(), new BossBarPacket.AddAction(Component.text(text), stage, color, overlay, (byte) 0)));
  }

  public void remove() {
    this.player.sendPacket(new BossBarPacket(player.getUuid(), new BossBarPacket.RemoveAction()));
  }

  public void recreate() {
    remove();
    display();
  }

  public void updateStage() {
    this.player.sendPacket(new BossBarPacket(player.getUuid(), new BossBarPacket.UpdateHealthAction(stage)));
  }

  public void updateTitle() {
    this.player.sendPacket(new BossBarPacket(player.getUuid(), new BossBarPacket.UpdateTitleAction(Component.text(text))));
  }

  public Player player() {
    return player;
  }

  public String text() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public float stage() {
    return stage;
  }

  public void setStage(float stage) {
    this.stage = stage;
  }

  public BossBar.Overlay overlay() {
    return overlay;
  }

  public void setOverlay(BossBar.Overlay overlay) {
    this.overlay = overlay;
  }

  public BossBar.Color color() {
    return color;
  }

  public void setColor(BossBar.Color color) {
    this.color = color;
  }
}
