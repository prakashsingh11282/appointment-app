package com.example.appointment.controller;

import com.example.appointment.model.Appointment;
import com.example.appointment.model.AppointmentRequest;
import com.example.appointment.repository.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

  private final AppointmentRepository repository;

  public AppointmentController(AppointmentRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Appointment> getAppointments(@RequestParam(name = "date", required = false) LocalDate date) {
    return repository.findByDate(date);
  }

  @PostMapping
  public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest request) {
    Appointment appointment = new Appointment(null, request.getTitle(), request.getDate(), request.getTime(),
        request.getLocation());
    Appointment saved = repository.save(appointment);
    return ResponseEntity.created(URI.create("/api/appointments/" + saved.getId())).body(saved);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
    if (!repository.deleteById(id)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }
}
