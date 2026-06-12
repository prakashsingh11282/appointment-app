package com.example.appointment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
  private final Long id;
  private final String title;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private final LocalDate date;

  @JsonFormat(pattern = "HH:mm")
  private final LocalTime time;
  private final String location;

  public Appointment(Long id, String title, LocalDate date, LocalTime time, String location) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.time = time;
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getDate() {
    return date;
  }

  public LocalTime getTime() {
    return time;
  }

  public String getLocation() {
    return location;
  }
}
