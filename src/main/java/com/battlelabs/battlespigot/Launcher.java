package com.battlelabs.battlespigot;

public class Launcher {

  public static void main(String[] args) {
    BattleServer.singleton().start();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> BattleServer.singleton().stop()));
  }

}
