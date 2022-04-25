package com.battlelabs.battlespigot.config;

import java.nio.file.Path;

public class Configuration {

  private final Path folderPath;
  private final String fileName;

  private DataSet dataSet;

  public Configuration(Path folder, String file, DataSet dataSet) {
    this.folderPath = folder;
    this.fileName = file;
    this.dataSet = dataSet;
  }

  public Path folderPath() {
    return folderPath;
  }

  public String fileName() {
    return fileName;
  }

  public void setDataSet(DataSet dataSet) {
    this.dataSet = dataSet;
  }

  public DataSet dataSet() {
    return dataSet;
  }
}
