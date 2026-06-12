package com.example.labour.controller;

import com.example.labour.dto.SignupRequest;
import com.example.labour.dto.SigninRequest;
import com.example.labour.dto.AuthResponse;
import com.example.labour.entity.User;
import com.example.labour.entity.Labour;
import com.example.labour.repository.UserRepository;
import com.example.labour.repository.LabourRepository;
import com.example.labour.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LabourRepository labourRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().body("Email already exists");
    }

    User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getName());
    User savedUser = userRepository.save(user);

    Labour labour = new Labour(savedUser, request.getPhone(), request.getSkills());
    labourRepository.save(labour);

    String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    return ResponseEntity.ok(new AuthResponse(token, savedUser.getId(), savedUser.getName(), savedUser.getEmail()));
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
    Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
    if (userOpt.isEmpty()) {
      return ResponseEntity.badRequest().body("Invalid email or password");
    }

    User user = userOpt.get();
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return ResponseEntity.badRequest().body("Invalid email or password");
    }

    String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getName());
    return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getName(), user.getEmail()));
  }
}
