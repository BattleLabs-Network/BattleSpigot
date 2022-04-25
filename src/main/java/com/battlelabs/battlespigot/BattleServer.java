package com.battlelabs.battlespigot;

import com.battlelabs.battlespigot.commands.CommandRepository;
import com.battlelabs.battlespigot.commands.commands.DefaultCommands;
import com.battlelabs.battlespigot.config.ConfigurationProvider;
import com.battlelabs.battlespigot.feature.FeatureFactory;
import com.battlelabs.battlespigot.feature.FeatureManager;
import com.battlelabs.battlespigot.listener.PlayerLoginEventListener;
import com.battlelabs.battlespigot.operator.OperatorRepository;
import com.battlelabs.battlespigot.world.WorldFactory;
import com.battlelabs.battlespigot.world.WorldLoader;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;

public final class BattleServer {

  private static final BattleServer SINGLETON = new BattleServer();

  private final MinecraftServer minecraftServer = MinecraftServer.init();
  private final GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
  private final FeatureManager featureManager = FeatureFactory.featureManager();
  private final WorldLoader worldLoader = WorldFactory.worldLoader();
  private final OperatorRepository operatorRepository = new OperatorRepository();
  private final CommandRepository commandRepository = new CommandRepository();
  private final ConfigurationProvider configurationProvider = new ConfigurationProvider();

  public static BattleServer singleton() {
    return SINGLETON;
  }

  public void start() {
    // enable proxy support
//    BungeeCordProxy.enable();
    this.registerDefaults();
    this.minecraftServer.start("127.0.0.1", 25565);
  }

  public void stop() {
    MinecraftServer.stopCleanly();
  }

  private void registerDefaults() {
    final GlobalEventHandler eventHandler = this.eventHandler;

    // events
    final PlayerLoginEventListener playerLoginEventListener = new PlayerLoginEventListener();
    eventHandler.addListener(PlayerLoginEvent.class, playerLoginEventListener::onLogin);

    // feature hook
    this.featureManager.hook(eventHandler);

    // commands
    final CommandManager commandManager = MinecraftServer.getCommandManager();
    this.commandRepository.register(commandManager, DefaultCommands.class);


    // world manager
    this.worldLoader.loadMap(WorldFactory.create("world"));
  }

  public MinecraftServer minecraftServer() {
    return minecraftServer;
  }

  public GlobalEventHandler eventHandler() {
    return eventHandler;
  }

  public FeatureManager featureManager() {
    return featureManager;
  }

  public WorldLoader worldLoader() {
    return worldLoader;
  }

  public OperatorRepository operatorRepository() {
    return operatorRepository;
  }

  public CommandRepository commandRepository() {
    return commandRepository;
  }

  public ConfigurationProvider configurationProvider() {
    return configurationProvider;
  }
}
