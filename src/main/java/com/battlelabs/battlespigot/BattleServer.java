package com.battlelabs.battlespigot;

import com.battlelabs.battlespigot.command.CommandRepository;
import com.battlelabs.battlespigot.command.commands.DefaultCommands;
import com.battlelabs.battlespigot.config.ConfigurationProvider;
import com.battlelabs.battlespigot.feature.FeatureFactory;
import com.battlelabs.battlespigot.feature.FeatureManager;
import com.battlelabs.battlespigot.listener.PlayerLoginEventListener;
import com.battlelabs.battlespigot.operator.OperatorRepository;
import com.battlelabs.battlespigot.world.WorldFactory;
import com.battlelabs.battlespigot.world.WorldLoader;
import com.google.gson.JsonObject;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.bungee.BungeeCordProxy;
import net.minestom.server.extras.velocity.VelocityProxy;

public final class BattleServer {

  private static final BattleServer SINGLETON = new BattleServer();

  private final MinecraftServer minecraftServer = MinecraftServer.init();
  private final GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
  private final FeatureManager featureManager = FeatureFactory.featureManager();
  private final WorldLoader worldLoader = WorldFactory.worldLoader();
  private final OperatorRepository operatorRepository = new OperatorRepository();
  private final CommandRepository commandRepository = new CommandRepository();
  private final ConfigurationProvider configurationProvider = new ConfigurationProvider();
  private BattleServerConfiguration configuration = new BattleServerConfiguration();

  public static BattleServer singleton() {
    return SINGLETON;
  }

  public void start() {
    this.configurationProvider.createIfNotExists(this.configuration);
    this.configuration = this.configurationProvider.load(this.configuration);
    this.setupProxySupport();
    this.registerDefaults();
    this.minecraftServer.start(this.configuration.dataSet().getString("host"), this.configuration.dataSet().getNumber("port").intValue());
  }

  public void stop() {
    MinecraftServer.stopCleanly();
  }

  private void setupProxySupport() {
    JsonObject proxy_support = configuration.dataSet().getObject("proxy_support");
    if (proxy_support.get("bungeecord").getAsBoolean()) {
      BungeeCordProxy.enable();
    } else {
      if (proxy_support.get("velocity").getAsJsonObject().get("enabled").getAsBoolean()) {
        VelocityProxy.enable(proxy_support.get("velocity").getAsJsonObject().get("secret_key").getAsString());
      }
    }
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
