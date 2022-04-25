package com.battlelabs.battlespigot.config;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DataSet {

  private JsonObject jsonObject = new JsonObject();

  @Contract(" -> new")
  public static @NotNull DataSet construct() {
    return new DataSet();
  }

  public DataSet append(String ident, String value) {
    this.jsonObject.addProperty(ident, value);
    return this;
  }

  public DataSet append(String ident, Number value) {
    this.jsonObject.addProperty(ident, value);
    return this;
  }

  public DataSet append(String ident, boolean value) {
    this.jsonObject.addProperty(ident, value);
    return this;
  }

  public DataSet append(String ident, Character value) {
    this.jsonObject.addProperty(ident, value);
    return this;
  }

  public DataSet append(String ident, JsonObject value) {
    this.jsonObject.add(ident, value);
    return this;
  }

  public String getString(String ident) {
    return this.jsonObject.get(ident).getAsString();
  }

  public Number getNumber(String ident) {
    return this.jsonObject.get(ident).getAsNumber();
  }

  public boolean getBoolean(String ident) {
    return this.jsonObject.get(ident).getAsBoolean();
  }

  public char getCharacter(String ident) {
    return this.jsonObject.get(ident).getAsCharacter();
  }

  public JsonObject getObject(String ident) {
    return this.jsonObject.get(ident).getAsJsonObject();
  }

  public void update(JsonObject object) {
    this.jsonObject = object;
  }

  public JsonObject jsonObject() {
    return jsonObject;
  }
}
