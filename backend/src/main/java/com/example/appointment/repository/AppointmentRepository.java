package com.example.appointment.repository;

import com.example.appointment.model.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepository {
  private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();
  private final AtomicLong nextId = new AtomicLong(1);

  public Appointment save(Appointment appointment) {
    Long id = nextId.getAndIncrement();
    Appointment newAppointment = new Appointment(id, appointment.getTitle(), appointment.getDate(),
        appointment.getTime(), appointment.getLocation());
    appointments.put(id, newAppointment);
    return newAppointment;
  }

  public List<Appointment> findAll() {
    return new ArrayList<>(appointments.values());
  }

  public List<Appointment> findByDate(LocalDate date) {
    if (date == null) {
      return findAll();
    }
    return appointments.values().stream()
        .filter(appointment -> appointment.getDate().equals(date))
        .collect(Collectors.toList());
  }

  public boolean deleteById(Long id) {
    return appointments.remove(id) != null;
  }

  public boolean existsById(Long id) {
    return appointments.containsKey(id);
  }
}
