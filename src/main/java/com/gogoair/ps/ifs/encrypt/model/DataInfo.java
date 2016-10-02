package com.gogoair.ps.ifs.encrypt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by cjemison on 9/30/16.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataInfo {
  private String data;
  @JsonProperty("uxdId")
  private String uxdId;
  @JsonProperty("byPass")
  private String byPass;
  @JsonProperty("date")
  private long date;

  public String getData() {
    return data;
  }

  public void setData(final String data) {
    this.data = data;
  }

  public String getUxdId() {
    return uxdId;
  }

  public void setUxdId(final String uxdId) {
    this.uxdId = uxdId;
  }

  public String getByPass() {
    return byPass;
  }

  public void setByPass(final String byPass) {
    this.byPass = byPass;
  }

  public long getDate() {
    return date;
  }

  public void setDate(final long date) {
    this.date = date;
  }
}
