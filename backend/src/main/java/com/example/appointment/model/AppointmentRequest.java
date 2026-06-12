package com.example.appointment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {
  private String title;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime time;
  private String location;

  public AppointmentRequest() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
