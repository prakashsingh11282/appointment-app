package com.example.labour.controller;

import com.example.labour.dto.LabourProfileResponse;
import com.example.labour.entity.Labour;
import com.example.labour.repository.LabourRepository;
import com.example.labour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/labour")
@CrossOrigin(origins = "*")
public class LabourController {

  @Autowired
  private LabourRepository labourRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/all")
  public ResponseEntity<?> getAllLabour() {
    List<Labour> labours = labourRepository.findAll();
    List<LabourProfileResponse> responses = labours.stream()
        .map(l -> new LabourProfileResponse(
            l.getId(),
            l.getUser().getName(),
            l.getUser().getEmail(),
            l.getPhone(),
            l.getSkills(),
            l.getCreatedAt()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(responses);
  }

  @GetMapping("/profile")
  public ResponseEntity<?> getMyProfile(@RequestAttribute(value = "userId", required = false) Long userId) {
    if (userId == null) {
      return ResponseEntity.badRequest().body("Unauthorized");
    }

    Optional<Labour> labour = labourRepository.findByUserId(userId);
    if (labour.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Labour l = labour.get();
    LabourProfileResponse response = new LabourProfileResponse(
        l.getId(),
        l.getUser().getName(),
        l.getUser().getEmail(),
        l.getPhone(),
        l.getSkills(),
        l.getCreatedAt());
    return ResponseEntity.ok(response);
  }
}
