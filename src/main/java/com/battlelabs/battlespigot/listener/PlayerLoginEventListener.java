package com.battlelabs.battlespigot.listener;

import com.battlelabs.battlespigot.BattleServer;
import com.battlelabs.battlespigot.bossbar.CustomBossBar;
import com.battlelabs.battlespigot.bossbar.CustomBossBarBuilder;
import com.battlelabs.battlespigot.world.World;
import net.kyori.adventure.bossbar.BossBar;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLoginEventListener {

  public void onLogin(@NotNull PlayerLoginEvent event) {
    final Player player = event.getPlayer();

    final World world = BattleServer.singleton().worldLoader().getMap("world");
    if (world != null) {
      event.setSpawningInstance(world);
      player.setRespawnPoint(Pos.ZERO.add(0, 100, 0));
    }

    CustomBossBar bossBar = new CustomBossBarBuilder(player).setText("Yes").setStage(1).setOverlay(BossBar.Overlay.NOTCHED_12).setColor(BossBar.Color.BLUE).build();
    bossBar.display();

    player.setGameMode(GameMode.CREATIVE);
  }

}
