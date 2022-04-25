package com.battlelabs.battlespigot.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

public class ConfigurationProvider {

  private static final Gson GSON =
      new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

  public <T extends Configuration> T load(@NotNull T configuration) {
    try (Reader reader = new FileReader(configuration.folderPath() + "/" + configuration.fileName())) {
      configuration.dataSet().update((JsonObject) new JsonParser().parse(reader));
      return configuration;
    } catch (IOException ignored) {
    }
    return configuration;
  }

  public void save(@NotNull Configuration configuration) {
    try {
      Files.createDirectories(configuration.folderPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    try (FileWriter fileWriter =
             new FileWriter(configuration.folderPath() + "/" + configuration.fileName())) {
      fileWriter.write(GSON.toJson(configuration.dataSet().jsonObject()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void createIfNotExists(@NotNull Configuration configuration) {
    File file = new File(configuration.folderPath() + "/" + configuration.fileName());
    if (!file.exists()) {
      save(configuration);
    }
  }
}
